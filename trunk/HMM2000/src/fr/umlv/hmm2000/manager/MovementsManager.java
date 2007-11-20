package fr.umlv.hmm2000.manager;

import java.util.Iterator;

import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;

public class MovementsManager {

	private final Map map;
	
	private final UIEventManager uiManager;

	private final SelectionManager selectionManager;
	
	public MovementsManager(Map map, UIEventManager uiManager, SelectionManager selectionManager) {
		this.map = map;
		this.uiManager = uiManager;
		this.selectionManager = selectionManager;
	}

	public void performMove(MoveDisplayEvent event) {

		Iterator<Location> iterator = event.getLocations().iterator();

		Location start = null;
		Location end = null;

		while (iterator.hasNext()) {
			start = end;
			end = iterator.next();
			if (start != null && end != null) {
				MoveEvent moveEvent = new MoveEvent(event.getSource(), start,
						end);
				this.map.moveElement(start, end);
				this.uiManager.performMove(moveEvent);
				this.selectionManager.perform(end);
			}
		}

	}

}
