package fr.umlv.hmm2000.unit.profile;

import java.util.HashMap;
import java.util.Map.Entry;

import fr.umlv.hmm2000.util.Pair;

/**
 * This class represent elementaries attack/defense abilities for a specific
 * fightable
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class ElementAbility {

	// To a ability name corresponding a pair of attack/defense values
	private final HashMap<ElementaryEnum, Pair<Integer, Integer>> abilities;

	public ElementAbility() {

		this.abilities = initAbilities();
	}

	/**
	 * Adds a new ability to the hashmap
	 * 
	 * @param element
	 *          name ability
	 * @param attackAbility
	 *          attack ability value
	 * @param defenseAbility
	 *          defense ability value
	 * @return new ability
	 */
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

	/**
	 * Initializes abilities with all elementary enum name and defaults
	 * attack/defense values
	 * 
	 * @return default abilities
	 */
	private HashMap<ElementaryEnum, Pair<Integer, Integer>> initAbilities() {

		HashMap<ElementaryEnum, Pair<Integer, Integer>> abilities = new HashMap<ElementaryEnum, Pair<Integer, Integer>>();
		for (ElementaryEnum elt : ElementaryEnum.values()) {
			abilities.put(elt, new Pair<Integer, Integer>(0, 0));
		}
		return abilities;
	}

	/**
	 * Gets a pair attack/defense value corresponding ability name
	 * @param element ability name
	 * @return pair attack/defense corresponding
	 */
	public Pair<Integer, Integer> getAbility(ElementaryEnum element) {

		return this.abilities.get(element);
	}

	/**
	 * Gets damage value corresponding to an attack with ability given
	 * @param ability 
	 * @return damage value
	 */
	public int getDamage(ElementAbility ability) {

		int damageCount = 0;
		for (Entry<ElementaryEnum, Pair<Integer, Integer>> entry : this.abilities
				.entrySet()) {
			Pair<Integer, Integer> defensePair = ability.getAbility(entry.getKey());
			damageCount += entry.getValue().getFirstElement()
					* (100 - defensePair.getSecondElement()) / 100;
		}
		return damageCount;
	}
}
