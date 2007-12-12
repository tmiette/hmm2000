package fr.umlv.hmm2000.engine;

import fr.umlv.hmm2000.engine.guiinterface.LocationSelectionRequester;
import fr.umlv.hmm2000.engine.guiinterface.UIEventManager;
import fr.umlv.hmm2000.engine.manager.EncounterManager;
import fr.umlv.hmm2000.engine.manager.MoveManager;
import fr.umlv.hmm2000.engine.manager.SelectionManager;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;

public class Engine {

	private static Engine currentEngine;

	private final Map map;

	private final UIEventManager uiManager;

	private MoveManager moveManager;

	private SelectionManager selectionManager;

	private EncounterManager encounterManager;

	private LocationSelectionRequester locationRequester;

	public static Engine createEngine(Map map, UIEventManager uiManager) {
		if (Engine.currentEngine == null) {
			Engine.currentEngine = new Engine(map, uiManager);
			Engine.currentEngine.initialize();
		}
		return Engine.currentEngine;
	}

	public static Engine currentEngine() {
		return Engine.currentEngine;
	}

	private Engine(Map map, UIEventManager uiManager) {
		this.map = map;
		this.uiManager = uiManager;
	}

	private void initialize() {
		this.selectionManager = new SelectionManager();
		this.encounterManager = new EncounterManager();
		this.moveManager = new MoveManager();
	}

	public void locationClicked(int x, int y, int button) {
		Location l = new Location(x, y);

		if (this.locationRequester != null) {
			this.locationRequester.submitLocation(l);
		} else {
			if (button == 1) {
				this.moveManager.clearCurrentMoveEvent();
				this.selectionManager.perform(l);
			} else if (button == 3) {
				this.moveManager.perform(l);
			}
		}
	}

	public Map map() {
		return this.map;
	}

	public UIEventManager uiManager() {
		return this.uiManager;
	}

	public SelectionManager selectionManager() {
		return this.selectionManager;
	}

	public MoveManager moveManager() {
		return this.moveManager;
	}

	public EncounterManager encounterManager() {
		return this.encounterManager;
	}

	public void requestLocationSelection(LocationSelectionRequester requester) {
		this.locationRequester = requester;
	}

	public void clearLocationSelection(){
		this.locationRequester = null;
	}
}
