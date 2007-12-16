package fr.umlv.hmm2000.warrior.skill;

import fr.umlv.hmm2000.engine.Engine;
import fr.umlv.hmm2000.engine.LocationSelectionRequester;
import fr.umlv.hmm2000.engine.LocationSelectionRequester.LocationSelection;
import fr.umlv.hmm2000.map.Location;

public class SkillAttackOneMoreTime implements SkillAction {

	@Override
	public void perform() {

		// TODO Auto-generated method stub
		Engine.currentEngine()
					.requestLocationSelection(new LocationSelectionRequester(new LocationSelection(	LocationSelectionRequester.RECHEABLE_LOCATION,
																																													"Où voulez-vous vous téléportez ?")) {

						@Override
						public void perform(Location... locations) {

						}
					});
	}
}
