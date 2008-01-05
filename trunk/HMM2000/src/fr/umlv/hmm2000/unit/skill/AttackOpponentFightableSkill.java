package fr.umlv.hmm2000.unit.skill;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.LocationSelectionRequester;
import fr.umlv.hmm2000.engine.LocationSelectionRequester.LocationSelection;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.unit.Fightable;
import fr.umlv.hmm2000.unit.exception.WarriorDeadException;
import fr.umlv.hmm2000.warrior.profil.ElementAbility;

public class AttackOpponentFightableSkill implements Skill {

  private final ElementAbility abilities;

  private final double physical;

  public AttackOpponentFightableSkill(ElementAbility abilities, double physical) {
    this.abilities = abilities;
    this.physical = physical;
  }

  @Override
  public void perform() {

    CoreEngine.requestLocationSelection(new LocationSelectionRequester(
        new LocationSelection(
            LocationSelectionRequester.BATTLE_OPPONENT_FIGHTABLE_LOCATION,
            "Qui voulez vous attaquer ?")) {

      @Override
      public void perform(Location... locations) {

        Location defenderLocation = locations[0];
        MapForegroundElement mfe = CoreEngine.map()
            .getMapForegroundElementAtLocation(defenderLocation);

        Fightable defender = (Fightable) mfe;
        double elementaryDamage = abilities.getDamage(defender.getAbilities());
        try {
          defender.hurt(physical + elementaryDamage
              - defender.getPhysicalDefenseValue());
        } catch (WarriorDeadException e) {
          Location l = CoreEngine.map().getLocationForMapForegroundElement(
              defender);
          CoreEngine.battleManager().kill(l, defender);
        }
        CoreEngine.battleManager().roundManager().nextDay();
      }
    });

  }

  @Override
  public String getName() {
    return "Strong-Attack";
  }

  @Override
  public String getToolTipText() {
    return "This skill enables to attack one opponent unit.";
  }
}
