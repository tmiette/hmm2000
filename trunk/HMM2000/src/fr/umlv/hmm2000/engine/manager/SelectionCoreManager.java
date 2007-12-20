package fr.umlv.hmm2000.engine.manager;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.element.MapForegroundElement;

public class SelectionCoreManager {

  private Location selectedLocation;

  public SelectionCoreManager() {
  }

  public void perform(Location l) {
    MapForegroundElement element = CoreEngine.map()
        .getMapForegroundElementAtLocation(l);
    if (element != null) {
      if (this.selectedLocation != null) {
        CoreEngine.fireSpriteRemoved(this.selectedLocation, Sprite.SELECTION);
        this.selectedLocation = null;
      }
      CoreEngine.fireSpriteAdded(l, Sprite.SELECTION);
      element.accept(CoreEngine.uiManager().displayingVisitor());
      this.selectedLocation = l;
    }
  }

  public MapForegroundElement getSelectedElement() {
    if (this.selectedLocation != null) {
      return CoreEngine.map().getMapForegroundElementAtLocation(
          this.selectedLocation);
    } else {
      return null;
    }
  }

  public Location getSelectedLocation() {
    return this.selectedLocation;
  }
}
