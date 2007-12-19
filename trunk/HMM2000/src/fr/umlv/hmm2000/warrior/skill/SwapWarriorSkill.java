package fr.umlv.hmm2000.warrior.skill;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.LocationSelectionRequester;
import fr.umlv.hmm2000.engine.LocationSelectionRequester.LocationSelection;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.warrior.Fightable;
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
	public void perform(final FightableContainer container) {

		// TODO Auto-generated method stub
		CoreEngine.requestLocationSelection(new LocationSelectionRequester(
				new LocationSelection(
						LocationSelectionRequester.TWO_UNITS_SAME_TEAM_LOCATION
						"Quel guerrier bouger ?")) {

			@Override
			public void perform(Location... locations) {

				Location from = locations[0];
				Location to = locations[1];
				
				container.getBattlePositionManager().moveMapForegroundElement(from, to);
			}
			
		});
	}
}
