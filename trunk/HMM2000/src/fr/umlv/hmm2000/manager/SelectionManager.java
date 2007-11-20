package fr.umlv.hmm2000.manager;

import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.map.MapForegroundElement;

public class SelectionManager {

	private final UIEventManager uiManager;

	private final Map map;

	private Location selectedLocation;

	public SelectionManager(Map map, UIEventManager uiManager) {
		this.uiManager = uiManager;
		this.map = map;
	}

	public void perform(Location l) {
		MapForegroundElement element = this.map.getElementAtLocation(l);
		if (element != null) {
			if (this.selectedLocation != null) {
				this.uiManager.performDeselection(new SelectionEvent(element,
						this.selectedLocation));
				this.selectedLocation = null;
			}
			this.uiManager.performSelection(new SelectionEvent(element, l));
			this.selectedLocation = l;
		}
	}

	public MapForegroundElement getSelectedElement() {
		if (this.selectedLocation != null) {
			return this.map.getElementAtLocation(this.selectedLocation);
		} else {
			return null;
		}
	}

	public Location getSelectedLocation() {
		return this.selectedLocation;
	}

}
