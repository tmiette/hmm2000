package fr.umlv.hmm2000.engine.manager;

import java.util.ArrayList;

import fr.umlv.hmm2000.astar.AStar;
import fr.umlv.hmm2000.astar.AStarResult;
import fr.umlv.hmm2000.astar.heuristic.CheckerboardEuclideanAStarHeuristic;
import fr.umlv.hmm2000.engine.Engine;
import fr.umlv.hmm2000.engine.event.EncounterEvent;
import fr.umlv.hmm2000.engine.event.MoveEvent;
import fr.umlv.hmm2000.engine.event.MoveEvent.Step;
import fr.umlv.hmm2000.engine.guiinterface.UIEngine;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.map.MovableElement;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.map.graph.CheckerboardVertex;
import fr.umlv.hmm2000.util.Pair;

public class MoveManager {

  private final UIEngine uiManager;

  private final Map map;

  private final CheckerboardEuclideanAStarHeuristic heuristic;

  private MovableElement currentSource;

  private MoveEvent currentMoveEvent;

  public MoveManager() {
    this.map = Engine.currentEngine().map();
    this.uiManager = Engine.currentEngine().uiManager();
    this.heuristic = new CheckerboardEuclideanAStarHeuristic();
  }

  public void perform(Location location) {

    if (!Engine.currentEngine().selectionManager().getSelectedLocation()
        .equals(location)) {
      if (this.currentMoveEvent != null) {
        if (this.currentMoveEvent.getMoves().get(
            this.currentMoveEvent.getMoves().size() - 1).getEnd().equals(
            location)) {
          this.performCurrentMoveEvent();
          this.clearCurrentMoveEvent();
        } else {
          this.createCurrentMoveEvent(location);
        }
      } else {
        this.createCurrentMoveEvent(location);
      }
    }
  }

  private void createCurrentMoveEvent(Location location) {
    Location start = Engine.currentEngine().selectionManager()
        .getSelectedLocation();
    if (start != null) {
      MapForegroundElement element = Engine.currentEngine().selectionManager()
          .getSelectedElement();
      if (element instanceof MovableElement
          && Engine.currentEngine().roundManager().isCurrentPlayer(
              ((MovableElement) element).getPlayer())) {
        this.currentSource = (MovableElement) element;

        AStarResult<CheckerboardVertex> result = AStar
            .aStarAlgorithm(this.map.getGraph(), this.heuristic, this.map
                .getGraph().getCheckerboardVertex(start.getX(), start.getY()),
                this.map.getGraph().getCheckerboardVertex(location.getX(),
                    location.getY()));

        if (result != null) {
          ArrayList<Pair<Location, Double>> weightedLocation = new ArrayList<Pair<Location, Double>>();
          for (CheckerboardVertex vertex : result.getPath()) {
            weightedLocation
                .add(new Pair<Location, Double>(new Location(vertex
                    .getXCoordinate(), vertex.getYCoordinate()), vertex
                    .getWeight()));
          }

          this.clearCurrentMoveEvent();
          MoveEvent moves = new MoveEvent(this.currentSource, weightedLocation);
          this.currentMoveEvent = moves;
          this.uiManager.displayMoveSteps(moves);
        }
      }
    }
  }

  public void performMoveEvent(MoveEvent event) {
    for (Step move : event.getMoves()) {
      if (move.isRecheable()) {
        Location l = move.getEnd();
        MapForegroundElement element = this.map
            .getMapForegroundElementAtLocation(l);
        if (element != null) {
          EncounterEvent ecounterEvent = new EncounterEvent(this.currentSource,
              element, move.getStart(), l);
          if (!Engine.currentEngine().encounterManager().perform(ecounterEvent)) {
            break;
          }
        }
        this.map.moveMapForegroundElement(move.getStart(), move.getEnd());
        this.uiManager.displayStep(move);
        Engine.currentEngine().selectionManager().perform(move.getEnd());
      }
    }
  }

  private void performCurrentMoveEvent() {
    this.performMoveEvent(this.currentMoveEvent);
  }

  public void clearCurrentMoveEvent() {
    if (this.currentMoveEvent != null) {
      this.uiManager.eraseMoveSteps(currentMoveEvent);
      this.currentMoveEvent = null;
    }
  }

}
