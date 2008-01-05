package fr.umlv.hmm2000.engine.guiinterface;

import java.util.List;

import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.util.Pair;

public interface HMMUserInterface {

  public static final int INFO_MESSAGE = 1;
  public static final int WARNING_MESSAGE = 2;
  public static final int ERROR_MESSAGE = 4;

  public void drawMap(Map map);

  public void eraseMap();

  public UIChoicesManager choicesManager();

  public UIDisplayingVisitor displayingVisitor();

  public void displayMessage(String message, int level);

  public void displaySprite(Location location, Sprite sprite);

  public void displaySprites(List<Pair<Location, Sprite>> sprites);

  public void eraseSprite(Location location, Sprite sprite);

  public void eraseSprites(List<Pair<Location, Sprite>> sprites);

}
