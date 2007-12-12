package fr.umlv.hmm2000.salesentity.spell;

import fr.umlv.hmm2000.engine.event.EncounterEvent;

public interface SpellAction {

	public void perform(final EncounterEvent event);
	
}
