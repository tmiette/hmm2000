package fr.umlv.hmm2000.map;

import fr.umlv.hmm2000.engine.event.EncounterEvent;
import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.gui.Spritable;

public interface MapForegroundElement extends Spritable {
	
	public boolean encounter(EncounterEvent event);
	
	public void accept(UIDisplayingVisitor visitor);
}
