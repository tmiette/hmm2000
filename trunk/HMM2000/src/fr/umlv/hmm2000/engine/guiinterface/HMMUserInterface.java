package fr.umlv.hmm2000.engine.guiinterface;

import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.map.element.MapBackgroundElement;
import fr.umlv.hmm2000.map.element.MapForegroundElement;

public interface HMMUserInterface {
  
  public void drawMap(Map map);
  
  public void eraseMap();

  public UIChoicesManager choicesManager();

  public UIDisplayingVisitor displayingVisitor();

  public void displayMessage(String message);
  
  public void swap(Location from, Location to);
  
  public void displaySprite(Location location, Sprite sprite);
  
  public void eraseSprite(Location location, Sprite sprite);
  
  public void elementAdded(Location location, MapForegroundElement element);

  public void elementRemoved(Location location, MapForegroundElement element);
  
  public void elementAdded(Location location, MapBackgroundElement element);

  public void elementRemoved(Location location, MapBackgroundElement element);
  
}
