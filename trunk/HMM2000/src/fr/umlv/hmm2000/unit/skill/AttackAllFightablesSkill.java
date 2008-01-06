package fr.umlv.hmm2000.unit.skill;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.unit.Fightable;
import fr.umlv.hmm2000.unit.exception.WarriorDeadException;
import fr.umlv.hmm2000.unit.profile.ElementAbility;

/**
 * Capacity to attack all units on battle map
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class AttackAllFightablesSkill implements Skill {

  // Additionnal elementaries attacks
  private final ElementAbility abilities;

  // Skill's physical damage capacity
  private final double physical;

  public AttackAllFightablesSkill(ElementAbility abilities, double physical) {

    this.abilities = abilities;
    this.physical = physical;
  }

  @Override
  public void perform() {

    // Requesting all units on battle map
    for (MapForegroundElement mfe : CoreEngine.map().getMapForegroundElements()) {
      if (mfe instanceof Fightable) {
        Fightable defender = (Fightable) mfe;
        // Calculating damage
        double elementaryDamage = this.abilities.getDamage(defender
            .getAbilities());
        try {
          // Hurting unit
          defender.hurt(this.physical + elementaryDamage
              - defender.getPhysicalDefenseValue());
        } catch (WarriorDeadException e) {
          // Removing dead unit from view
          Location l = CoreEngine.map().getLocationForMapForegroundElement(
              defender);
          CoreEngine.battleManager().kill(l, defender);
        }
      }
    }
    // Battle ended
    if (CoreEngine.battleManager() != null) {
      // Next round
      CoreEngine.battleManager().roundManager().nextDay();

    }
  }

  @Override
  public String getName() {

    return "Big Multi-Attack";
  }

  @Override
  public String getToolTipText() {

    return "This skill enables to attack all units on the battle map.";
  }
}
