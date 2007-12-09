package fr.umlv.hmm2000.map;

import fr.umlv.hmm2000.gui.Spritable;
import fr.umlv.hmm2000.manager.EncounterEvent;
import fr.umlv.hmm2000.manager.UIDisplayingVisitor;
import fr.umlv.hmm2000.manager.UIEventManager;

public interface MapForegroundElement extends Spritable {
	
	public boolean encounter(EncounterEvent event, Map map, UIEventManager uiManager);
	
	public void accept(UIDisplayingVisitor visitor);
}
