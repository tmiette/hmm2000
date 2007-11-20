package fr.umlv.hmm2000.map;

import java.util.HashMap;

import fr.umlv.hmm2000.map.graph.CheckerboardGraph;

public class Map {

	private final CheckerboardGraph graph;

	private final HashMap<Location, MapForegroundElement> elements;

	public Map(MapBackgroundElement[][] backgroundElements) {
		this.graph = new CheckerboardGraph(backgroundElements);
		this.elements = new HashMap<Location, MapForegroundElement>();
	}

	public CheckerboardGraph getGraph() {
		return this.graph;
	}

	public void addMapElement(MapForegroundElement element, Location l) {
		this.elements.put(l, element);
	}

	public MapForegroundElement getElementAtLocation(Location l) {
		return this.elements.get(l);
	}

	public void moveElement(Location from, Location to) {
		MapForegroundElement element = this.elements.get(from);
		if (element != null) {
			this.elements.remove(from);
			this.elements.put(to, element);
		}
	}

}
