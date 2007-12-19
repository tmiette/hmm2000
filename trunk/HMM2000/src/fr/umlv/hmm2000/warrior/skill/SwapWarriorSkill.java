package fr.umlv.hmm2000.warrior.skill;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.LocationSelectionRequester;
import fr.umlv.hmm2000.engine.LocationSelectionRequester.LocationSelection;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.warrior.FightableContainer;

public class SwapWarriorSkill implements SkillAction {

	private static SwapWarriorSkill instance;

	public static SwapWarriorSkill getInstance() {

		if (SwapWarriorSkill.instance == null) {
			SwapWarriorSkill.instance = new SwapWarriorSkill();
		}
		return SwapWarriorSkill.instance;
	}

	private SwapWarriorSkill() {

	}

	@Override
	public void perform(final FightableContainer attacker) {

		// TODO Auto-generated method stub
		CoreEngine.requestLocationSelection(new LocationSelectionRequester(
				new LocationSelection(
						LocationSelectionRequester.MOVABLE_ELEMENT_LOCATION,
						"Quel guerrier bouger ?")) {

			@Override
			public void perform(Location... locations) {

				final Location from = locations[0];

				CoreEngine.requestLocationSelection(new LocationSelectionRequester(
						new LocationSelection(
								LocationSelectionRequester.RECHEABLE_LOCATION,
								"S�lectionner la destination")) {

					@Override
					public void perform(Location... locations) {

						Location to = locations[0];

						attacker.getBattlePositionManager().moveMapForegroundElement(from, to);
					}

				});

			}
		});
	}
}
