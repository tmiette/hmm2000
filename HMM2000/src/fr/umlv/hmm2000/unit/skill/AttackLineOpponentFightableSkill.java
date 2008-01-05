package fr.umlv.hmm2000.unit.skill;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.LocationSelectionRequester;
import fr.umlv.hmm2000.engine.LocationSelectionRequester.LocationSelection;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.unit.Fightable;
import fr.umlv.hmm2000.unit.exception.WarriorDeadException;
import fr.umlv.hmm2000.unit.profil.ElementAbility;

/**
 * Capacity to attack either a second line unit or entire first line
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class AttackLineOpponentFightableSkill implements Skill {

	// Additionnal elementaries attacks
	private final ElementAbility abilities;

	// Skill's physical damage capacity
	private final double physical;

	public AttackLineOpponentFightableSkill(ElementAbility abilities,
																					double physical) {

		this.abilities = abilities;
		this.physical = physical;
	}

	@Override
	public void perform() {

		// Requesting unit location to attack
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

				if (defender.getFightableContainer().getBattlePositionManager()
						.isInSecondLine(defender)) {
					try {
						// Hurt one second line unit
						defender.hurt(physical + elementaryDamage
								- defender.getPhysicalDefenseValue());

					}
					catch (WarriorDeadException e) {
						// Removing dead unit from view
						Location l = CoreEngine.map().getLocationForMapForegroundElement(
								defender);
						CoreEngine.battleManager().kill(l, defender);
					}
				}
				else {
					// Hurting first line units
					for (Fightable f : defender.getFightableContainer()
							.getBattlePositionManager().getFightableOnFirstLine()) {
						try {
							f.hurt(physical + elementaryDamage - f.getPhysicalDefenseValue());

						}
						catch (WarriorDeadException e) {
						// Removing dead unit from view
							Location l = CoreEngine.map().getLocationForMapForegroundElement(
									f);
							CoreEngine.battleManager().kill(l, f);
						}
					}
				}
				// Next round
				CoreEngine.battleManager().roundManager().nextDay();
			}
		});
	}

	@Override
	public String getName() {

		return "Multi-Attack";
	}

	@Override
	public String getToolTipText() {

		return "This skill enables to attack either all opponent units in the first line of one opponent unit in the second line.";
	}
}
