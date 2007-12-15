package fr.umlv.hmm2000.map;

import fr.umlv.hmm2000.map.element.MapBackgroundElement;
import fr.umlv.hmm2000.map.element.MapForegroundElement;

public interface Map {
  
  public int getWidth();
  
  public int getHeight();

  public MapBackgroundElement getMapBackgroundElementAtLocation(Location l);
  
  public MapForegroundElement getMapForegroundElementAtLocation(Location l);
  
}
