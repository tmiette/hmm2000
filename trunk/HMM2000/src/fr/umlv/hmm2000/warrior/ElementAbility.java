package fr.umlv.hmm2000.warrior;

import java.util.HashMap;
import java.util.Map.Entry;

import fr.umlv.hmm2000.util.Pair;
import fr.umlv.hmm2000.warrior.attack.elementary.ElementaryEnum;

public class ElementAbility {

	private final HashMap<ElementaryEnum, Pair<Integer, Integer>> abilities;

	public ElementAbility() {

		this.abilities = initAbilities();
	}

	public ElementAbility addAbility(ElementaryEnum element, int attackAbility,
			int defenseAbility) {

		Pair<Integer, Integer> pair = this.abilities.get(element);
		if (pair != null) {
			this.abilities.remove(element);
			this.abilities.put(element, new Pair<Integer, Integer>(attackAbility,
					defenseAbility));
		}
		return this;
	}

	private HashMap<ElementaryEnum, Pair<Integer, Integer>> initAbilities() {

		HashMap<ElementaryEnum, Pair<Integer, Integer>> abilities = new HashMap<ElementaryEnum, Pair<Integer, Integer>>();
		for (ElementaryEnum elt : ElementaryEnum.values()) {
			abilities.put(elt, new Pair<Integer, Integer>(0, 0));
		}
		return abilities;
	}

	public Pair<Integer, Integer> getAbility(ElementaryEnum element) {

		return this.abilities.get(element);
	}

	public int getDamage(ElementAbility ability) {

		int damageCount = 0;
		for (Entry<ElementaryEnum, Pair<Integer, Integer>> entry : this.abilities
				.entrySet()) {
			Pair<Integer, Integer> defensePair = ability.getAbility(entry.getKey());
			damageCount += entry.getValue().getFirstElement() * (100 - defensePair.getSecondElement())
					/ 100;
		}
		return damageCount;
	}
}
