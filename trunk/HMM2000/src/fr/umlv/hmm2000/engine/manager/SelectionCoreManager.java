package fr.umlv.hmm2000.engine.manager;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.guiinterface.Sprite;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.element.MapForegroundElement;

/**
 * This class is the manager of selection.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class SelectionCoreManager {

  private Location selectedLocation;

  /**
   * Perform action depending of the location.
   * 
   * @param l
   *            the location.
   */
  public void perform(Location l) {
    MapForegroundElement element = CoreEngine.map()
        .getMapForegroundElementAtLocation(l);
    if (element != null) {
      if (this.selectedLocation != null) {
        CoreEngine.fireSpriteRemoved(this.selectedLocation, Sprite.SELECTION);
        this.selectedLocation = null;
      }
      CoreEngine.fireSpriteAdded(l, Sprite.SELECTION);
      CoreEngine.displayMapForegroundElement(element);
      this.selectedLocation = l;
    }
  }

  /**
   * Returns the selected element or null.
   * 
   * @return the selected element or null.
   */
  public MapForegroundElement getSelectedElement() {
    if (this.selectedLocation != null) {
      return CoreEngine.map().getMapForegroundElementAtLocation(
          this.selectedLocation);
    } else {
      return null;
    }
  }

  /**
   * Returns the selected location.
   * 
   * @return the selected location
   */
  public Location getSelectedLocation() {
    return this.selectedLocation;
  }
}
