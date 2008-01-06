package fr.umlv.hmm2000.engine.manager;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.element.MapForegroundElement;

/**
 * This class is the manager of the battle position map.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class BattlePositionCoreManager {

  /**
   * Perform action depending of the location.
   * 
   * @param l
   *            the location.
   */
  public void perform(Location l) {

    if (!CoreEngine.selectionManager().getSelectedLocation().equals(l)) {
      MapForegroundElement element = CoreEngine.selectionManager()
          .getSelectedElement();

      if (element != null && !element.equals(l)) {
        Location selectedLocation = CoreEngine.selectionManager()
            .getSelectedLocation();
        CoreEngine.map().moveMapForegroundElement(selectedLocation, l);
        this.swap(selectedLocation, l);
        CoreEngine.selectionManager().perform(l);
      }
    }
  }

  /**
   * Swap two different sprites from the user interface manager/
   * 
   * @param from
   *            from location.
   * @param to
   *            to location.
   */
  private void swap(Location from, Location to) {
    MapForegroundElement fromElement = CoreEngine.map()
        .getMapForegroundElementAtLocation(to);
    MapForegroundElement toElement = CoreEngine.map()
        .getMapForegroundElementAtLocation(from);
    if (toElement != null) {
      CoreEngine.fireSpriteRemoved(to, toElement.getSprite());
    }
    CoreEngine.fireSpriteRemoved(from, fromElement.getSprite());
    CoreEngine.fireSpriteAdded(to, fromElement.getSprite());
    if (toElement != null) {
      CoreEngine.fireSpriteAdded(from, toElement.getSprite());
    }
  }

}
