package fr.umlv.hmm2000.warrior.skill;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.LocationSelectionRequester;
import fr.umlv.hmm2000.engine.LocationSelectionRequester.LocationSelection;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.warrior.Fightable;

public class SwapWarriorSkill implements Skill {

	@Override
	public void perform() {

		// TODO Auto-generated method stub
		CoreEngine.requestLocationSelection(new LocationSelectionRequester(
				new LocationSelection(
						LocationSelectionRequester.TWO_UNITS_SAME_TEAM_LOCATION
						"Quel guerrier bouger ?")) {

			@Override
			public void perform(Location... locations) {

				Location from = locations[0];
				Location to = locations[1];
				
				MapForegroundElement e = CoreEngine.map().getMapForegroundElementAtLocation(from);
				if (e instanceof Fightable) {
					Fightable f = (Fightable)e;
					f.getFightableContainer().getBattlePositionManager().moveMapForegroundElement(from, to);
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

		return "This skill permits to move an unit";
	}
}
