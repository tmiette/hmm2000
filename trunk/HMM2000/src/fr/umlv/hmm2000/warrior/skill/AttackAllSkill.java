package fr.umlv.hmm2000.warrior.skill;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.warrior.Fightable;
import fr.umlv.hmm2000.warrior.exception.WarriorDeadException;
import fr.umlv.hmm2000.warrior.profil.ElementAbility;

public class AttackAllSkill implements Skill {

  private final ElementAbility abilities;

  private final double physical;

  public AttackAllSkill(ElementAbility abilities, double physical) {

    this.abilities = abilities;
    this.physical = physical;
  }

  @Override
  public void perform() {

    Map map = CoreEngine.map();
    for (MapForegroundElement mfe : map.getMapForegroundElements()) {
      try {
        if (mfe instanceof Fightable) {
          Fightable defender = (Fightable) mfe;
          double elementaryDamage = this.abilities.getDamage(defender
              .getAbilities());
          defender.hurt(this.physical + elementaryDamage
              - defender.getPhysicalDefenseValue());
        }
      } catch (WarriorDeadException e) {
        map.removeMapForegroundElement(map
            .getLocationForMapForegroundElement(mfe));
      }
    }
  }

  @Override
  public String getName() {
    return this.getClass().toString();
  }

  @Override
  public String getToolTipText() {

    return "This skill permits to attack all units on the battle map";
  }
}
