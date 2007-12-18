package fr.umlv.hmm2000.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import fr.umlv.hmm2000.map.element.MapBackgroundElement;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.map.graph.CheckerboardGraph;

public class WorldMap implements Map {

  private final CheckerboardGraph graph;

  private final HashMap<Location, MapForegroundElement> elements;

  public WorldMap(MapBackgroundElement[][] backgroundElements) {
    this.graph = new CheckerboardGraph(backgroundElements);
    this.elements = new HashMap<Location, MapForegroundElement>();
  }

  @Override
  public CheckerboardGraph graph() {
    return this.graph;
  }

  @Override
  public MapBackgroundElement getMapBackgroundElementAtLocation(Location l) {
    return this.graph.getMapBackgroundElement(l.getX(), l.getY());
  }

  public void addMapForegroundElement(MapForegroundElement element, Location l) {
    this.elements.put(l, element);
  }

  @Override
  public void removeMapForegroundElement(Location l) {
    this.elements.remove(l);
  }

  @Override
  public void changeMapBackgroundElement(Location l,
      MapBackgroundElement element) {
    this.graph.changeMapBackgroundElement(l.getX(), l.getY(), element);
  }

  @Override
  public MapForegroundElement getMapForegroundElementAtLocation(Location l) {
    return this.elements.get(l);
  }

  @Override
  public void moveMapForegroundElement(Location from, Location to) {
    MapForegroundElement element = this.elements.get(from);
    if (element != null) {
      this.elements.remove(from);
      this.elements.put(to, element);
    }
  }

  @Override
  public List<MapForegroundElement> getMapForegroundElements() {
    ArrayList<MapForegroundElement> elements = new ArrayList<MapForegroundElement>();
    for (Entry<Location, MapForegroundElement> entry : this.elements.entrySet()) {
      elements.add(entry.getValue());
    }
    return elements;
  }

  @Override
  public int getHeight() {
    return this.graph.getHeight();
  }

  @Override
  public int getWidth() {
    return this.graph.getWidth();
  }

  @Override
  public Location getLocationForMapForegroundElement(
      MapBackgroundElement element) {
    for (Entry<Location, MapForegroundElement> entry : this.elements.entrySet()) {
      if (entry.getValue().equals(element)) {
        return entry.getKey();
      }
    }
    return null;
  }
}
