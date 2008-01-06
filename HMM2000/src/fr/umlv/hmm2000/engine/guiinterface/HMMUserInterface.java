package fr.umlv.hmm2000.engine.guiinterface;

import java.util.List;

import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.util.Pair;

/**
 * This interface defines a user interface that can be used with the core engine
 * of the game.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public interface HMMUserInterface {

  /**
   * Level for information messages.
   */
  public static final int INFO_MESSAGE = 1;

  /**
   * Level for warning messages.
   */
  public static final int WARNING_MESSAGE = 2;

  /**
   * Level for error messages.
   */
  public static final int ERROR_MESSAGE = 4;

  /**
   * Draws a map. This method must draw all the background elements and the
   * foreground element od the map.
   * 
   * @param map
   *            the map.
   */
  public void drawMap(Map map);

  /**
   * Erases the map.
   */
  public void eraseMap();

  /**
   * Returns the user interface choices manager.
   * 
   * @return the user interface choices manager.
   */
  public UIChoicesManager choicesManager();

  /**
   * Returns the user interface displaying visitor which display features of the
   * different foreground elements.
   * 
   * @return the user interface displaying visitor.
   */
  public UIDisplayingVisitor displayingVisitor();

  /**
   * Displays a message.
   * 
   * @param message
   *            the message.
   * @param level
   *            the level of the message.
   */
  public void displayMessage(String message, int level);

  /**
   * Displays a sprite on the current map at the specified location.
   * 
   * @param location
   *            the location.
   * @param sprite
   *            the sprite.
   */
  public void displaySprite(Location location, Sprite sprite);

  /**
   * Displays a list of sprites at the specified locations.
   * 
   * @param sprites
   *            the list of pairs associating the sprites to their locations.
   */
  public void displaySprites(List<Pair<Location, Sprite>> sprites);

  /**
   * Erases a sprite on the current map at the specified location.
   * 
   * @param location
   *            the location.
   * @param sprite
   *            the sprite.
   */
  public void eraseSprite(Location location, Sprite sprite);

  /**
   * Erases a list of sprites at the specified locations.
   * 
   * @param sprites
   *            the list of pairs associating the sprites to their locations.
   */
  public void eraseSprites(List<Pair<Location, Sprite>> sprites);

}
