package fr.umlv.hmm2000.building;

import java.util.ArrayList;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.salesentity.PriceFactory;
import fr.umlv.hmm2000.unit.profile.WarriorProfile;

public class CreateNewFactoryItem implements CastleItem {

	// Player's castle
	private final Castle castle;

	public CreateNewFactoryItem(Castle castle) {

		this.castle = castle;
	}

	@Override
	public String getSuggestion() {

		return "Build new factory";
	}

	@Override
	public void perform() {

		// Choose list
		ArrayList<CastleItem> items = new ArrayList<CastleItem>();
		items.add(CastleItem.defaultItem);
		// Avalaible factories
		for (final WarriorProfile profile : WarriorProfile.values()) {
			// Factories not yet built in castle
			if (!castle.getFactoryBuilt().contains(profile)) {
				items.add(new CastleItem() {

					@Override
					public String getSuggestion() {

						return profile.name() + " factory.";
					}

					@Override
					public void perform() {

						if (castle.getPlayer().spend(
								PriceFactory
										.getWarriorFactoryPrice(profile, Castle.defaultLevel))) {
							// Build the factory specified
							castle.buildFactory(profile);
						}
						else {
							CoreEngine.fireMessage("You don't have enough resources.",
									HMMUserInterface.WARNING_MESSAGE);
						}
					}

				});
			}

		}
		// Adding items to choose manager
		CastleItem item = CoreEngine.requestCastleItem(items);
		if (item != null && item != CastleItem.defaultItem) {
			item.perform();
		}

		// Refreshing the view
		CoreEngine.selectionManager().perform(
				CoreEngine.map().getLocationForMapForegroundElement(castle));
	}

}
