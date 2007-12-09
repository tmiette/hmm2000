package fr.umlv.hmm2000;

import java.util.HashMap;
import java.util.Map.Entry;

import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.manager.EncounterEvent;
import fr.umlv.hmm2000.manager.UIDisplayingVisitor;
import fr.umlv.hmm2000.manager.UIEventManager;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.map.MapForegroundElement;

public class Merchant implements MapForegroundElement {

	private final String name;
	private final HashMap<Spell, Integer> spells;

	public Merchant(String name) {
		this.name = name;
		this.spells = new HashMap<Spell, Integer>();
	}

	public String getName() {
		return this.name;
	}

	public void addSpell(Spell spell, int quantity) {
		this.spells.put(spell, quantity);
	}

	public HashMap<Spell, Integer> getSpells() {
		return this.spells;
	}

	@Override
	public Sprite getSprite() {
		return Sprite.MERCHANT;
	}

	@Override
	public boolean encounter(EncounterEvent event, Map map,
			UIEventManager uiManager) {
		ChoicesList<Spell> choices = new ChoicesList<Spell>(
				"Que voulez-vous achetez ?");
		choices.addChoice(null, "Ne rien acheter");
		for (Entry<Spell, Integer> spell : this.spells.entrySet()) {
			choices.addChoice(spell.getKey(), spell.getKey().getName() + " - "
					+ spell.getValue());
		}

		Spell spell = uiManager.choicesManager().<Spell> submit(choices);

		if (spell != null) {
			int quantity = this.spells.get(spell);
			quantity--;
			if (quantity == 0) {
				this.spells.remove(spell);
			} else {
				this.spells.put(spell, quantity);
			}
			spell.launch(event, map, uiManager);
		}

		return false;
	}

	@Override
	public void accept(UIDisplayingVisitor visitor) {
		visitor.visit(this);
	}

}
