package fr.umlv.hmm2000.engine.manager;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.war.BattleMap;
import fr.umlv.hmm2000.warrior.Fightable;

public class SwapCoreManager {

  public void perform(Location toLocation) {

    Location fromLocation = CoreEngine.selectionManager().getSelectedLocation();

    if (CoreEngine.selectionManager().getSelectedElement() instanceof Fightable
        && !fromLocation.equals(toLocation)) {
      BattleMap map = (BattleMap) CoreEngine.map();
      if (map.getFightableContainerAtLocation(fromLocation) != null
          && map.getFightableContainerAtLocation(toLocation) != null) {
        map.swapBetweenFightableContainers(fromLocation, toLocation);
        this.swap(fromLocation, toLocation);
        CoreEngine.selectionManager().perform(toLocation);

      }
    }

  }

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
