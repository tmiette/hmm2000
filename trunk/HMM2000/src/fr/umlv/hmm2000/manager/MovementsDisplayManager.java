package fr.umlv.hmm2000.manager;

import java.util.ArrayList;
import java.util.List;

import fr.umlv.hmm2000.MovableElement;
import fr.umlv.hmm2000.astar.AStar;
import fr.umlv.hmm2000.astar.AStarPath;
import fr.umlv.hmm2000.astar.heuristic.CheckboardEuclideanAStarHeuristic;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.map.MapForegroundElement;
import fr.umlv.hmm2000.map.graph.CheckerboardGraph;
import fr.umlv.hmm2000.map.graph.CheckerboardVertex;

public class MovementsDisplayManager {

	private final UIEventManager uiManager;

	private final Map map;

	private final CheckerboardGraph graph;

	private final SelectionManager selectionManager;

	private final CheckboardEuclideanAStarHeuristic heuristic;

	private final MovementsManager movementsManger;

	private MovableElement source;

	private List<Location> currentPath;

	public MovementsDisplayManager(Map map, UIEventManager uiManager,
			SelectionManager selectionManager) {
		this.map = map;
		this.graph = map.getGraph();
		this.uiManager = uiManager;
		this.selectionManager = selectionManager;
		this.heuristic = new CheckboardEuclideanAStarHeuristic();
		this.movementsManger = new MovementsManager(this.map, this.uiManager,
				this.selectionManager);
	}

	public void perform(Location location) {

		if (!this.haveToPerformMove(location)) {

			Location start = this.selectionManager.getSelectedLocation();
			if (start != null) {
				MapForegroundElement element = this.selectionManager
						.getSelectedElement();
				if (element instanceof MovableElement) {
					this.source = (MovableElement) element;

					AStarPath<CheckerboardVertex> path = AStar.aStarAlgorithm(
							this.graph, this.heuristic, this.graph
									.getCheckerboardVertex(start.getX(), start
											.getY()), this.graph
									.getCheckerboardVertex(location.getX(),
											location.getY()));

					if (path != null) {
						List<Location> locations = new ArrayList<Location>();
						for (CheckerboardVertex vertex : path.getPath()) {
							locations.add(new Location(vertex.getXCoordinate(),
									vertex.getYCoordinate()));
						}

						this.removeCurrentPath();
						this.uiManager.displayPath(new MoveDisplayEvent(
								this.source, locations));

						this.currentPath = locations;
					}

				}
			}
		} else {
			this.movementsManger.performMove(new MoveDisplayEvent(this.source,
					this.currentPath));
			this.currentPath = null;
		}
	}

	private boolean haveToPerformMove(Location location) {
		if (this.currentPath != null) {
			if (this.currentPath.get(this.currentPath.size() - 1).equals(
					location)) {
				return true;
			}
		}
		return false;
	}

	public void removeCurrentPath() {
		if (this.currentPath != null) {
			this.uiManager.removePath(new MoveDisplayEvent(this.source,
					this.currentPath));
			this.currentPath = null;
		}
	}

}
