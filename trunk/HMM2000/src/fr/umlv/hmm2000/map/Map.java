package fr.umlv.hmm2000.map;

import java.util.List;

import fr.umlv.hmm2000.map.element.MapBackgroundElement;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.map.graph.CheckerboardGraph;

public interface Map {

  public int getWidth();

  public int getHeight();

  public MapBackgroundElement getMapBackgroundElementAtLocation(Location l);

  public MapForegroundElement getMapForegroundElementAtLocation(Location l);

  public void removeMapForegroundElement(Location l);

  public void changeMapBackgroundElement(Location l,
      MapBackgroundElement element);

  public void moveMapForegroundElement(Location from, Location to);

  public CheckerboardGraph graph();

  public List<MapForegroundElement> getMapForegroundElements();

  public Location getLocationForMapForegroundElement(
      MapBackgroundElement element);

}
