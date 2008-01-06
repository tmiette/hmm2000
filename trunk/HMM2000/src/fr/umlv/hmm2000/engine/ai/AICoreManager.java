package fr.umlv.hmm2000.engine.ai;

import java.util.List;

import fr.umlv.hmm2000.unit.Fightable;

/**
 * This interface defines the behaviour of a battle artificial intelligence.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public interface AICoreManager {

  /**
   * 
   */
  public void performAttack();

  /**
   * Returns the unit which has to attack.
   * 
   * @param warriors
   *            list of units available.
   * @return the unit which has to attack.
   */
  public Fightable selectAttacker(List<Fightable> warriors);

  /**
   * Returns the unit which is attacked.
   * 
   * @param warrior
   *            the attacker unit.
   * @param warriors
   *            the list of attackable units.
   * @return the unit which is attacked.
   */
  public Fightable selectDefender(Fightable warrior, List<Fightable> warriors);
}
