package fr.umlv.hmm2000.engine.manager;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.element.MapForegroundElement;

public class BattlePositionCoreManager {

  public BattlePositionCoreManager() {
  }

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
