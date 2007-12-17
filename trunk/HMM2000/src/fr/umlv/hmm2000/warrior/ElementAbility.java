package fr.umlv.hmm2000.warrior;

import java.util.HashMap;
import java.util.Map.Entry;

import fr.umlv.hmm2000.util.Pair;
import fr.umlv.hmm2000.warrior.attack.elementary.ElementaryEnum;

public class ElementAbility {

  private final HashMap<ElementaryEnum, Pair<Integer, Integer>> abilities;

  public ElementAbility() {
    this.abilities = new HashMap<ElementaryEnum, Pair<Integer, Integer>>();
  }

  public ElementAbility addAbility(ElementaryEnum element, int attackAbility,
      int defenseAbility) {
    attackAbility = attackAbility >= 0 ? attackAbility : 0;
    defenseAbility = defenseAbility <= 100 ? defenseAbility : 100;
    defenseAbility = defenseAbility >= 0 ? defenseAbility : 0;
    this.abilities.put(element, new Pair<Integer, Integer>(attackAbility,
        defenseAbility));
    return this;
  }

  public Pair<Integer, Integer> getAbility(ElementaryEnum element) {
    return this.abilities.get(element);
  }

  public int getDamage(ElementAbility ability) {
    int damageCount = 0;
    for (Entry<ElementaryEnum, Pair<Integer, Integer>> entry : this.abilities
        .entrySet()) {
      Pair<Integer, Integer> defensePair = ability.getAbility(entry.getKey());
      int defenseValue = defensePair != null ? defensePair.getSecondElement()
          : 0;
      damageCount += entry.getValue().getFirstElement() * (100 - defenseValue) / 100;
    }
    return damageCount;
  }
}
