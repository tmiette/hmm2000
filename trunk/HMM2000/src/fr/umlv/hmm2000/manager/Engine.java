package fr.umlv.hmm2000.manager;

import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;

public class Engine {

	private final Map map;

	private final UIEventManager uiManager;

	private final MoveManager movementsManager;

	private final SelectionManager selectionManager;
	
	public Engine(Map map, UIEventManager uiManager) {
		this.map = map;
		this.uiManager = uiManager;
		this.selectionManager = new SelectionManager(this.map, this.uiManager);
		this.movementsManager = new MoveManager(this.map, this.uiManager,
				this.selectionManager);
	}

	public void locationClicked(int x, int y, int button) {
		Location l = new Location(x, y);

		if (button == 1) {
			this.movementsManager.clearCurrentMoveEvent();
			this.selectionManager.perform(l);
		} else if (button == 3) {
			this.movementsManager.perform(l);
		}
	}
}
