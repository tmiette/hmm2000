package fr.umlv.hmm2000.engine.ai;

import java.util.List;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.unit.Fightable;
import fr.umlv.hmm2000.unit.exception.WarriorDeadException;
import fr.umlv.hmm2000.unit.exception.WarriorNotReachableException;

public abstract class AbstractAICoreManager implements AICoreManager {

  private final List<Fightable> aiTroop;

  private final List<Fightable> otherTroop;

  public AbstractAICoreManager(List<Fightable> aiTroop,
      List<Fightable> otherTroop) {
    this.aiTroop = aiTroop;
    this.otherTroop = otherTroop;
  }

  @Override
  public void performAttack() {
    final Fightable bestAttacker = this.selectAttacker(this.aiTroop);
    final Fightable defender = this.selectDefender(bestAttacker,
        this.otherTroop);
    if (bestAttacker != null && defender != null) {
      try {
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
