package fr.umlv.hmm2000.engine.manager;

import fr.umlv.hmm2000.engine.Engine;
import fr.umlv.hmm2000.engine.event.SelectionEvent;
import fr.umlv.hmm2000.engine.guiinterface.UIEngine;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.map.element.MapForegroundElement;

public class SelectionManager {

  private final Map map;
  private final UIEngine uiManager;

  private Location selectedLocation;

  public SelectionManager() {
    this.map = Engine.currentEngine().map();
    this.uiManager = Engine.currentEngine().uiManager();
  }

  public void perform(Location l) {
    MapForegroundElement element = this.map
        .getMapForegroundElementAtLocation(l);
    if (element != null) {
      if (this.selectedLocation != null) {
        this.uiManager.eraseSelection(new SelectionEvent(element,
            this.selectedLocation));
        this.selectedLocation = null;
      }
      this.uiManager.displaySelection(new SelectionEvent(element, l));
      element.accept(this.uiManager.displayingVisitor());
      this.selectedLocation = l;
    }
  }

  public MapForegroundElement getSelectedElement() {
    if (this.selectedLocation != null) {
      return this.map.getMapForegroundElementAtLocation(this.selectedLocation);
    } else {
      return null;
    }
  }

  public Location getSelectedLocation() {
    return this.selectedLocation;
  }

}
