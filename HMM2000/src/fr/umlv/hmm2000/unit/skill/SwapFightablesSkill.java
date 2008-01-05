package fr.umlv.hmm2000.unit.skill;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.LocationSelectionRequester;
import fr.umlv.hmm2000.engine.LocationSelectionRequester.LocationSelection;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.element.MapForegroundElement;

/**
 * Represents the capability to a hero to swap location unit on map during
 * battle
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class SwapFightablesSkill implements Skill {

    @Override
    public void perform() {

	// Requesting locations selections to apply skill
	CoreEngine
		.requestLocationSelection(new LocationSelectionRequester(
			new LocationSelection(
				LocationSelectionRequester.BATTLE_CURRENT_FIGHTABLE_LOCATION,
				"Quel guerrier bouger ?"),
			new LocationSelection(
				LocationSelectionRequester.BATTLE_CURRENT_POSITION_LOCATION,
				"A quel endroit ?")) {

		    @Override
		    public void perform(Location... locations) {

			// Getting the two location requested
			Location from = locations[0];
			Location to = locations[1];

			// Swapping element on both location
			CoreEngine.map().moveMapForegroundElement(from, to);

			MapForegroundElement fromElement = CoreEngine.map()
				.getMapForegroundElementAtLocation(to);
			MapForegroundElement toElement = CoreEngine.map()
				.getMapForegroundElementAtLocation(from);

			// Updating view
			if (toElement != null) {
			    CoreEngine.fireSpriteRemoved(to, toElement
				    .getSprite());
			}
			CoreEngine.fireSpriteRemoved(from, fromElement
				.getSprite());
			CoreEngine.fireSpriteAdded(to, fromElement.getSprite());
			if (toElement != null) {
			    CoreEngine.fireSpriteAdded(from, toElement
				    .getSprite());
			}

			// Next round
			CoreEngine.battleManager().roundManager().nextDay();
		    }

		});
    }

    @Override
    public String getName() {
	return "Swap-Move";
    }

    @Override
    public String getToolTipText() {
	return "This skill enables to move an unit.";
    }
}
