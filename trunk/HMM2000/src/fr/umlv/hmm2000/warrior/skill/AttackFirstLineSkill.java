package fr.umlv.hmm2000.warrior.skill;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.LocationSelectionRequester;
import fr.umlv.hmm2000.engine.LocationSelectionRequester.LocationSelection;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.war.BattlePositionMap;
import fr.umlv.hmm2000.warrior.Fightable;
import fr.umlv.hmm2000.warrior.exception.WarriorDeadException;
import fr.umlv.hmm2000.warrior.profil.ElementAbility;

public class AttackFirstLineSkill implements Skill {

  private final ElementAbility abilities;

  private final double physical;

  public AttackFirstLineSkill(ElementAbility abilities, double physical) {

    this.abilities = abilities;
    this.physical = physical;
  }

  @Override
  public void perform() {

    CoreEngine.requestLocationSelection(new LocationSelectionRequester(
        new LocationSelection(
            LocationSelectionRequester.BATTLE_FIRST_LINE_LOCATION,
            "Qui voulez vous attaquer ?")) {

      @Override
      public void perform(Location... locations) {

        Location l = locations[0];
        MapForegroundElement mfe = CoreEngine.map()
            .getMapForegroundElementAtLocation(l);

        Fightable fightable = (Fightable) mfe;
        BattlePositionMap pMap = fightable.getFightableContainer()
            .getBattlePositionManager();

        for (Fightable defender : pMap.getFightableOnFirstLine()) {

          double elementaryDamage = abilities
              .getDamage(defender.getAbilities());

          try {
            defender.hurt(physical + elementaryDamage
                - defender.getPhysicalDefenseValue());

          } catch (WarriorDeadException e1) {
            Location defenderLocation = CoreEngine.map()
                .getLocationForMapForegroundElement(defender);
            CoreEngine.map().removeMapForegroundElement(defenderLocation);
            CoreEngine
                .fireSpriteRemoved(defenderLocation, defender.getSprite());
          }
        }
      }
    });
  }

  @Override
  public String getName() {
    return this.getClass().toString();
  }

  @Override
  public String getToolTipText() {
    return "This skill permits to attack all units on the first line";
  }
}
