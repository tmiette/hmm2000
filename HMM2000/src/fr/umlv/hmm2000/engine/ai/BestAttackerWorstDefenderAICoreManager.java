package fr.umlv.hmm2000.engine.ai;

import java.util.ArrayList;
import java.util.List;

import fr.umlv.hmm2000.unit.Fightable;

/**
 * This class represents a battle artificial intelligence which select the best
 * attacker to attack the worst defender.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class BestAttackerWorstDefenderAICoreManager extends
    AbstractAICoreManager {

  /**
   * Constructor of the AI.
   * 
   * @param aiTroop
   *            list of unit belonging to AI.
   * @param otherTroop
   *            list of units attackable units.
   */
  public BestAttackerWorstDefenderAICoreManager(List<Fightable> aiTroop,
      List<Fightable> otherTroop) {
    super(aiTroop, otherTroop);
  }

  @Override
  public Fightable selectAttacker(List<Fightable> warriors) {
    if (warriors.size() != 0) {
      // Select the unit with maximum physical to attack
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
      // List of attackable warriors
      ArrayList<Fightable> attackables = new ArrayList<Fightable>();
      for (Fightable w : warriors) {
        if (warrior.isAttackable(w)) {
          attackables.add(w);
        }
      }
      // Select the unit with minimum health (or first unit)
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
