package fr.umlv.hmm2000.engine;

import fr.umlv.hmm2000.engine.event.SelectionEvent;
import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.MainMap;
import fr.umlv.hmm2000.map.element.MapForegroundElement;

public class BattleSelectionManager {
  private final MainMap map;
  private final HMMUserInterface uiManager;
  private Location selectedLocation;

  public BattleSelectionManager(MainMap map, HMMUserInterface uiManager) {
    this.map = map;
    this.uiManager = uiManager;
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
