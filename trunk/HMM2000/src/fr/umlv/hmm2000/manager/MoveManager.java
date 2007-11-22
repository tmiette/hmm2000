package fr.umlv.hmm2000.manager;

import java.util.ArrayList;

import fr.umlv.hmm2000.MovableElement;
import fr.umlv.hmm2000.Pair;
import fr.umlv.hmm2000.astar.AStar;
import fr.umlv.hmm2000.astar.AStarPath;
import fr.umlv.hmm2000.astar.heuristic.CheckboardEuclideanAStarHeuristic;
import fr.umlv.hmm2000.manager.MoveEvent.Step;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.map.MapForegroundElement;
import fr.umlv.hmm2000.map.graph.CheckerboardVertex;

public class MoveManager {

	private final UIEventManager uiManager;

	private final Map map;

	private final SelectionManager selectionManager;

	private final CheckboardEuclideanAStarHeuristic heuristic;

	private final EncounterManager encounterManager;

	private MovableElement currentSource;

	private MoveEvent currentMoveEvent;

	public MoveManager(Map map, UIEventManager uiManager,
			SelectionManager selectionManager) {
		this.map = map;
		this.uiManager = uiManager;
		this.selectionManager = selectionManager;
		this.heuristic = new CheckboardEuclideanAStarHeuristic();
		this.encounterManager = new EncounterManager();
	}

	public void perform(Location location) {

		if (this.currentMoveEvent != null) {
			if (this.currentMoveEvent.getMoves().get(
					this.currentMoveEvent.getMoves().size() - 1).getEnd()
					.equals(location)) {
				this.performCurrentMoveEvent();
				this.clearCurrentMoveEvent();
			} else {
				this.createCurrentMoveEvent(location);
			}
		} else {
			this.createCurrentMoveEvent(location);
		}
	}

	private void createCurrentMoveEvent(Location location) {
		Location start = this.selectionManager.getSelectedLocation();
		if (start != null) {
			MapForegroundElement element = this.selectionManager
					.getSelectedElement();
			if (element instanceof MovableElement) {
				this.currentSource = (MovableElement) element;

				AStarPath<CheckerboardVertex> path = AStar.aStarAlgorithm(
						this.map.getGraph(), this.heuristic, this.map
								.getGraph().getCheckerboardVertex(start.getX(),
										start.getY()), this.map.getGraph()
								.getCheckerboardVertex(location.getX(),
										location.getY()));

				if (path != null) {
					ArrayList<Pair<Location, Double>> weightedLocation = new ArrayList<Pair<Location, Double>>();
					for (CheckerboardVertex vertex : path.getPath()) {
						weightedLocation
								.add(new Pair<Location, Double>(new Location(
										vertex.getXCoordinate(), vertex
												.getYCoordinate()), vertex
										.getWeight()));
					}

					this.clearCurrentMoveEvent();
					MoveEvent moves = new MoveEvent(this.currentSource,
							weightedLocation);
					this.currentMoveEvent = moves;
					this.uiManager.displayMove(moves);
				}
			}
		}
	}

	private void performCurrentMoveEvent() {
		for (Step move : this.currentMoveEvent.getMoves()) {
			if (move.isRecheable()) {
				Location l = move.getEnd();
				MapForegroundElement element = this.map.getElementAtLocation(l);
				if (element != null) {
					EncounterEvent event = new EncounterEvent(
							this.currentSource, element, l);
					this.encounterManager.perform(event);
					break;
					//TODO gestion rencontre
				} else {
					this.map.moveElement(move.getStart(), move.getEnd());
					this.uiManager.performStep(move);
					this.selectionManager.perform(move.getEnd());
				}
			}
		}
	}

	public void clearCurrentMoveEvent() {
		if (this.currentMoveEvent != null) {
			this.uiManager.removeMove(currentMoveEvent);
			this.currentMoveEvent = null;
		}
	}

}
