package fr.umlv.hmm2000.engine.manager;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.battle.BattleMap;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.unit.Fightable;

/**
 * This class is the manager of the swap map.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class SwapCoreManager {

  /**
   * Perform action depending of the location.
   * 
   * @param toLocation
   *            the location.
   */
  public void perform(Location toLocation) {

    Location fromLocation = CoreEngine.selectionManager().getSelectedLocation();

    if (CoreEngine.selectionManager().getSelectedElement() instanceof Fightable
        && !fromLocation.equals(toLocation)) {
      BattleMap map = (BattleMap) CoreEngine.map();
      if (map.getFightableContainerAtLocation(fromLocation) != null
          && map.getFightableContainerAtLocation(toLocation) != null) {
        if (map.swapBetweenFightableContainers(fromLocation, toLocation)) {
          this.swap(fromLocation, toLocation);
          CoreEngine.selectionManager().perform(toLocation);
        }
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
