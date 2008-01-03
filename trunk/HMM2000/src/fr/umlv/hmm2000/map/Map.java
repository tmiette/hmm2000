package fr.umlv.hmm2000.map;

import java.util.List;

import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.map.element.MapBackgroundElement;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.map.graph.CheckerboardGraph;

public interface Map {

  public int getWidth();

  public int getHeight();

  public CheckerboardGraph graph();

  public MapBackgroundElement getMapBackgroundElementAtLocation(Location l);

  public MapForegroundElement getMapForegroundElementAtLocation(Location l);

  public void removeMapForegroundElement(Location l);

  public void changeMapBackgroundElement(Location l,
      MapBackgroundElement element);

  public void moveMapForegroundElement(Location from, Location to);

  public List<MapForegroundElement> getMapForegroundElements();

  public Location getLocationForMapForegroundElement(
      MapForegroundElement element);

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
