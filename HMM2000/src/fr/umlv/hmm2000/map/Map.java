package fr.umlv.hmm2000.map;

import java.util.List;

import fr.umlv.hmm2000.engine.guiinterface.Sprite;
import fr.umlv.hmm2000.map.element.MapBackgroundElement;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.map.graph.CheckerboardGraph;

/**
 * This class represents a generic map. A map contains background and foreground
 * elements. It contains a graph to use the A* star path finding. It contains
 * default methods to manipulate its elements.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public interface Map {

  /**
   * Gets the map width.
   * 
   * @return map width
   */
  public int getWidth();

  /**
   * Gets the map height
   * 
   * @return map height
   */
  public int getHeight();

  /**
   * Adds a map foreground element to the map at given location.
   * 
   * @param element
   *            to add
   * @param l
   *            location where element should be added
   */
  public void addMapForegroundElement(MapForegroundElement element, Location l);

  /**
   * Gets the map representation like a graph.
   * 
   * @return the map representation like a graph.
   */
  public CheckerboardGraph graph();

  /**
   * Gets a map background element at given location.
   * 
   * @param l
   *            location where background should be.
   * @return map background element.
   */
  public MapBackgroundElement getMapBackgroundElementAtLocation(Location l);

  /**
   * Gets a map foreground element at given location.
   * 
   * @param l
   *            location where map foreground should be
   * @return map foreground element
   */
  public MapForegroundElement getMapForegroundElementAtLocation(Location l);

  /**
   * Removes a map foreground element from the given location.
   * 
   * @param l
   *            location where map foreground element should be deleted
   */
  public void removeMapForegroundElement(Location l);

  /**
   * Replaces map background element at given location by the given map
   * background element.
   * 
   * @param l
   *            location of the new map background element
   * @param element
   *            map background element to add to the map
   */
  public void changeMapBackgroundElement(Location l,
      MapBackgroundElement element);

  /**
   * Moves a map foreground from location from to location to.
   * 
   * @param from
   *            source location
   * @param to
   *            destination location
   */
  public void moveMapForegroundElement(Location from, Location to);

  /**
   * Gets map foreground elements list.
   * 
   * @return map foreground elements list
   */
  public List<MapForegroundElement> getMapForegroundElements();

  public Location getLocationForMapForegroundElement(
      MapForegroundElement element);

  /**
   * Default map.
   */
  public final Map defaultMap = new Map() {

    private final MapBackgroundElement mbe = new MapBackgroundElement() {

      @Override
      public double getWeight() {

        return 0;
      }

      @Override
      public Sprite getSprite() {

        return Sprite.DEFAULT;
      }
    };

    @Override
    public void addMapForegroundElement(MapForegroundElement element, Location l) {

      throw new UnsupportedOperationException();
    }

    @Override
    public int getWidth() {

      return 100;
    }

    @Override
    public int getHeight() {

      return 100;
    }

    @Override
    public Location getLocationForMapForegroundElement(
        MapForegroundElement element) {

      throw new UnsupportedOperationException();
    }

    @Override
    public MapBackgroundElement getMapBackgroundElementAtLocation(Location l) {

      return this.mbe;
    }

    @Override
    public MapForegroundElement getMapForegroundElementAtLocation(Location l) {

      return null;
    }

    @Override
    public void changeMapBackgroundElement(Location l,
        MapBackgroundElement element) {

      throw new UnsupportedOperationException();
    }

    @Override
    public List<MapForegroundElement> getMapForegroundElements() {

      throw new UnsupportedOperationException();
    }

    @Override
    public CheckerboardGraph graph() {

      throw new UnsupportedOperationException();
    }

    @Override
    public void moveMapForegroundElement(Location from, Location to) {

      throw new UnsupportedOperationException();

    }

    @Override
    public void removeMapForegroundElement(Location l) {

      throw new UnsupportedOperationException();
    }

  };

}
