package fr.umlv.hmm2000.engine.event;

import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.element.MapBackgroundElement;

public class MapChangeEvent {

  private final Location location;

  private final MapBackgroundElement oldElement;

  private final MapBackgroundElement newElement;

  public MapChangeEvent(Location location, MapBackgroundElement oldElement,
      MapBackgroundElement newElement) {
    this.location = location;
    this.oldElement = oldElement;
    this.newElement = newElement;
  }

  public Location getLocation() {
    return this.location;
  }

  public MapBackgroundElement getOldElement() {
    return this.oldElement;
  }

  public MapBackgroundElement getNewElement() {
    return this.newElement;
  }

}
