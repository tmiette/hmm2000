package fr.umlv.hmm2000.engine.manager;

import fr.umlv.hmm2000.engine.Engine;
import fr.umlv.hmm2000.engine.event.EncounterEvent;
import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.map.Map;

public class EncounterManager {

	private final HMMUserInterface uiManager;

	private final Map map;

	public EncounterManager() {
		this.map = Engine.currentEngine().map();
		this.uiManager = Engine.currentEngine().uiManager();
	}

	public boolean perform(EncounterEvent event) {

		if (event.getRecipient().encounter(event)) {
			//this.map.removeMapForegroundElement(event.getRecipientLocation());
			//this.uiManager.eraseForegroundElement(event);
			return true;
		}

		return false;
	}
}
