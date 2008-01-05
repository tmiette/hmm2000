package fr.umlv.hmm2000.unit.skill;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.unit.Fightable;
import fr.umlv.hmm2000.unit.exception.WarriorDeadException;
import fr.umlv.hmm2000.warrior.profil.ElementAbility;

public class AttackAllFightablesSkill implements Skill {

  private final ElementAbility abilities;

  private final double physical;

  public AttackAllFightablesSkill(ElementAbility abilities, double physical) {

    this.abilities = abilities;
    this.physical = physical;
  }

  @Override
  public void perform() {
    for (MapForegroundElement mfe : CoreEngine.map().getMapForegroundElements()) {
      if (mfe instanceof Fightable) {
        Fightable defender = (Fightable) mfe;
        double elementaryDamage = this.abilities.getDamage(defender
            .getAbilities());
        try {
          defender.hurt(this.physical + elementaryDamage
              - defender.getPhysicalDefenseValue());
        } catch (WarriorDeadException e) {
          Location l = CoreEngine.map().getLocationForMapForegroundElement(
              defender);
          CoreEngine.battleManager().kill(l, defender);
        }
      }
    }
    CoreEngine.battleManager().roundManager().nextDay();
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
