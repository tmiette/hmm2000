package fr.umlv.hmm2000;

import fr.umlv.hmm2000.manager.EncounterEvent;
import fr.umlv.hmm2000.manager.UIEventManager;
import fr.umlv.hmm2000.map.Map;

public interface SpellAction {

	public void perform(EncounterEvent event, Map map, UIEventManager uiManager);
	
}
