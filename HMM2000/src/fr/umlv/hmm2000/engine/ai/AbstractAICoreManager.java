package fr.umlv.hmm2000.engine.ai;

import java.util.List;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.unit.Fightable;
import fr.umlv.hmm2000.unit.exception.WarriorDeadException;
import fr.umlv.hmm2000.unit.exception.WarriorNotReachableException;

/**
 * This class represents an abstract battle artificial intelligence which use
 * the two abstract methods.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public abstract class AbstractAICoreManager implements AICoreManager {

  private final List<Fightable> aiTroop;

  private final List<Fightable> otherTroop;

  /**
   * Constructor of the AI.
   * 
   * @param aiTroop
   *            list of unit belonging to AI.
   * @param otherTroop
   *            list of units attackable units.
   */
  public AbstractAICoreManager(List<Fightable> aiTroop,
      List<Fightable> otherTroop) {
    this.aiTroop = aiTroop;
    this.otherTroop = otherTroop;
  }

  @Override
  public void performAttack() {
    // Select attacker
    final Fightable bestAttacker = this.selectAttacker(this.aiTroop);
    // Select defender
    final Fightable defender = this.selectDefender(bestAttacker,
        this.otherTroop);
    if (bestAttacker != null && defender != null) {
      try {
        // Perform the attack
        bestAttacker.performAttack(defender);
        CoreEngine.battleManager().roundManager().tagAsAlreadyPlayed(
            bestAttacker);
      } catch (WarriorDeadException e) {
        CoreEngine.battleManager().roundManager().tagAsAlreadyPlayed(
            bestAttacker);
        CoreEngine.battleManager().kill(
            CoreEngine.map().getLocationForMapForegroundElement(defender),
            defender);
      } catch (WarriorNotReachableException e) {
        new AssertionError(e);
      }
    }
  }

}
