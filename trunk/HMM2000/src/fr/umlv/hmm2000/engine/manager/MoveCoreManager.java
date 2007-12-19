package fr.umlv.hmm2000.engine.manager;

import java.util.ArrayList;

import fr.umlv.hmm2000.astar.AStar;
import fr.umlv.hmm2000.astar.AStarResult;
import fr.umlv.hmm2000.astar.heuristic.CheckerboardEuclideanAStarHeuristic;
import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.event.EncounterEvent;
import fr.umlv.hmm2000.engine.event.MoveEvent;
import fr.umlv.hmm2000.engine.event.MoveEvent.Step;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.MovableElement;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.map.graph.CheckerboardVertex;
import fr.umlv.hmm2000.util.Pair;

public class MoveCoreManager {

  private final CheckerboardEuclideanAStarHeuristic heuristic;

  private MovableElement currentSource;

  private MoveEvent currentMoveEvent;

  public MoveCoreManager() {
    this.heuristic = new CheckerboardEuclideanAStarHeuristic();
  }

  public void perform(Location location) {

    if (CoreEngine.selectionManager().getSelectedLocation() != null
        && !CoreEngine.selectionManager().getSelectedLocation()
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
    Location start = CoreEngine.selectionManager().getSelectedLocation();
    if (start != null) {
      MapForegroundElement element = CoreEngine.selectionManager()
          .getSelectedElement();
      if (element instanceof MovableElement
          && CoreEngine.roundManager().isCurrentPlayer(
              ((MovableElement) element).getPlayer())) {
        this.currentSource = (MovableElement) element;

        AStarResult<CheckerboardVertex> result = AStar.aStarAlgorithm(
            CoreEngine.map().graph(), this.heuristic, CoreEngine.map().graph()
                .getCheckerboardVertex(start.getX(), start.getY()), CoreEngine
                .map().graph().getCheckerboardVertex(location.getX(),
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
          CoreEngine.uiManager().displayMoveSteps(moves);
        }
      }
    }
  }

  public void performMoveEvent(MoveEvent event) {
    for (Step move : event.getMoves()) {
      if (move.isRecheable()) {
        Location l = move.getEnd();
        MapForegroundElement element = CoreEngine.map()
            .getMapForegroundElementAtLocation(l);
        EncounterEvent ecounterEvent = new EncounterEvent(this.currentSource,
            element, move.getStart(), l);
        if (!CoreEngine.encounterManager().perform(ecounterEvent)) {
          break;
        }
      }
      event.getSource().setStepCount(move.getRemainingStepCount());
      CoreEngine.map().moveMapForegroundElement(move.getStart(), move.getEnd());
      CoreEngine.uiManager().displayStep(move);
      CoreEngine.selectionManager().perform(move.getEnd());
    }
  }

  private void performCurrentMoveEvent() {
    this.performMoveEvent(this.currentMoveEvent);
  }

  public void clearCurrentMoveEvent() {
    if (this.currentMoveEvent != null) {
      CoreEngine.uiManager().eraseMoveSteps(currentMoveEvent);
      this.currentMoveEvent = null;
    }
  }

}
