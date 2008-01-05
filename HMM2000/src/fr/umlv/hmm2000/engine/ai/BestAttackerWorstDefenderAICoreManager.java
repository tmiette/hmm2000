package fr.umlv.hmm2000.engine.ai;

import java.util.ArrayList;
import java.util.List;

import fr.umlv.hmm2000.unit.Fightable;

public class BestAttackerWorstDefenderAICoreManager extends
    AbstractAICoreManager {

  public BestAttackerWorstDefenderAICoreManager(List<Fightable> aiTroop,
      List<Fightable> otherTroop) {
    super(aiTroop, otherTroop);
  }

  @Override
  public Fightable selectAttacker(List<Fightable> warriors) {
    if (warriors.size() != 0) {
      // warrior with maximum physical attack value
      Fightable bestWarrior = warriors.get(0);
      for (Fightable warrior : warriors) {
        if (warrior.getPhysicalAttackValue() > bestWarrior
            .getPhysicalAttackValue()) {
          bestWarrior = warrior;
        }
      }
      return bestWarrior;
    }
    return null;
  }

  @Override
  public Fightable selectDefender(Fightable warrior, List<Fightable> warriors) {
    if (warriors.size() != 0) {
      // list of attackable warriors
      ArrayList<Fightable> attackables = new ArrayList<Fightable>();
      for (Fightable w : warriors) {
        if (warrior.isAttackable(w)) {
          attackables.add(w);
        }
      }
      // warrior with lower health
      Fightable lowWarrior = attackables.get(0);
      for (Fightable w : attackables) {
        if (w.getCurrentHealth() < lowWarrior.getCurrentHealth()) {
          lowWarrior = w;
        }
      }
      return lowWarrior;
    }
    return null;
  }

}
