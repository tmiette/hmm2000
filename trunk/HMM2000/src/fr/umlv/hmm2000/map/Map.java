package fr.umlv.hmm2000.map;

import java.util.HashMap;

import fr.umlv.hmm2000.map.element.MapBackgroundElement;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
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

	public MapBackgroundElement getMapBackgroundElementAtLocation(Location l) {
		return this.graph.getMapBackgroundElement(l.getX(), l.getY());
	}

	public void addMapForegroundElement(MapForegroundElement element, Location l) {
		this.elements.put(l, element);
	}

	public void removeMapForegroundElement(Location l) {
		this.elements.remove(l);
	}

	public void changeMapBackgroundElement(Location l,
			MapBackgroundElement element) {
		this.graph.changeMapBackgroundElement(l.getX(), l.getY(), element);
	}

	public MapForegroundElement getMapForegroundElementAtLocation(Location l) {
		return this.elements.get(l);
	}

	public void moveMapForegroundElement(Location from, Location to) {
		MapForegroundElement element = this.elements.get(from);
		if (element != null) {
			this.elements.remove(from);
			this.elements.put(to, element);
		}
	}

}
