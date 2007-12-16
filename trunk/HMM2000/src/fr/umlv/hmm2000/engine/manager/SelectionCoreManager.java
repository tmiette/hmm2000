package fr.umlv.hmm2000.engine.manager;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.event.SelectionEvent;
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
        CoreEngine.uiManager().eraseSelection(
            new SelectionEvent(element, this.selectedLocation));
        this.selectedLocation = null;
      }
      CoreEngine.uiManager().displaySelection(new SelectionEvent(element, l));
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
