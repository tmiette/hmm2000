package fr.umlv.hmm2000.engine.guiinterface;

import java.util.List;

import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.util.Pair;

public interface HMMUserInterface {
  
  public void drawMap(Map map);
  
  public void eraseMap();

  public UIChoicesManager choicesManager();

  public UIDisplayingVisitor displayingVisitor();

  public void displayMessage(String message);
  
  public void displaySprite(Location location, Sprite sprite);
  
  public void displaySprites(List<Pair<Location, Sprite>> sprites);
  
  public void eraseSprite(Location location, Sprite sprite);
  
  public void eraseSprites(List<Pair<Location, Sprite>> sprites);
  
}
