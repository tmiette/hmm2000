package fr.umlv.hmm2000.unit.skill;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.LocationSelectionRequester;
import fr.umlv.hmm2000.engine.LocationSelectionRequester.LocationSelection;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.unit.Fightable;

/**
 * Capacity to an unit to attack one more time during battle
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class AttackOneMoreTimeSkill implements Skill {

	@Override
	public void perform() {

		// Requesting location selection to apply skill
		CoreEngine.requestLocationSelection(new LocationSelectionRequester(
				new LocationSelection(
						LocationSelectionRequester.BATTLE_CURRENT_FIGHTABLE_LOCATION,
						"Qui doit réattaquer ?")) {

			@Override
			public void perform(Location... locations) {

				Location l = locations[0];
				// Tagging element to not already played
				CoreEngine.battleManager().roundManager().tagAsNotAlreadyPlayed(
						(Fightable) CoreEngine.map().getMapForegroundElementAtLocation(l));
				// Next round
				CoreEngine.battleManager().roundManager().nextDay();
			}
		});

	}

	@Override
	public String getName() {

		return "Again-Attack";
	}

	@Override
	public String getToolTipText() {

		return "This skill enables an unit to attack one more time.";
	}
}
