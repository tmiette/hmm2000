package fr.umlv.hmm2000.manager;

import fr.umlv.hmm2000.map.Map;

public class EncounterManager {

	private final UIEventManager uiManager;

	private final Map map;

	public EncounterManager(Map map, UIEventManager uiManager) {
		this.map = map;
		this.uiManager = uiManager;
	}

	public boolean perform(EncounterEvent event) {

		if (event.getRecipient().encounter(event, map, uiManager)) {
			this.map.removeMapForegroundElement(event.getRecipientLocation());
			this.uiManager.removeForegroundElement(event);
			return true;
		}

		return false;
	}
}
