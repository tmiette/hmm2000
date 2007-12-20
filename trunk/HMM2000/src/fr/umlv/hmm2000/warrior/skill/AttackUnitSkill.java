package fr.umlv.hmm2000.warrior.skill;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.LocationSelectionRequester;
import fr.umlv.hmm2000.engine.LocationSelectionRequester.LocationSelection;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.warrior.Fightable;
import fr.umlv.hmm2000.warrior.exception.WarriorDeadException;
import fr.umlv.hmm2000.warrior.profil.ElementAbility;

public class AttackUnitSkill implements Skill {

	private final ElementAbility abilities;

	private final double physical;

	public AttackUnitSkill(ElementAbility abilities,
																		double physical) {

		this.abilities = abilities;
		this.physical = physical;
	}
	
	@Override
	public void perform() {

		CoreEngine.requestLocationSelection(new LocationSelectionRequester(
				new LocationSelection(LocationSelectionRequester.DEFENDER_LOCATION,
						"Qui voulez vous attaquer ?")) {

			@Override
			public void perform(Location... locations) {

				Location defenderLocation = locations[0];

				Map map = CoreEngine.map();
				MapForegroundElement e = map
						.getMapForegroundElementAtLocation(defenderLocation);

				if (e instanceof Fightable) {
					Fightable defender = (Fightable) e;

					double elementaryDamage = abilities
							.getDamage(defender.getAbilities());

					try {
						defender.hurt(physical + elementaryDamage
								- defender.getPhysicalDefenseValue());

					}
					catch (WarriorDeadException e1) {
						map.removeMapForegroundElement(map
								.getLocationForMapForegroundElement(defender));
					}
				}
				
				
			}
		});
		
	}

	@Override
	public String getName() {

		return this.getName();
	}

	@Override
	public String getToolTipText() {

		return "This skil permits to an unit to attack";
	}
}
