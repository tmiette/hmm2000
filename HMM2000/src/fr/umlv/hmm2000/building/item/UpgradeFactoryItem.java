package fr.umlv.hmm2000.building.item;

import java.util.ArrayList;

import fr.umlv.hmm2000.building.Castle;
import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.salesentity.Price;
import fr.umlv.hmm2000.salesentity.PriceFactory;
import fr.umlv.hmm2000.unit.profile.Level;
import fr.umlv.hmm2000.unit.profile.WarriorProfile;

/**
 * This class permits to a player to upgrade a unit factory level in his castle.
 * Higher factory level is stronger warrior produced are.
 * 
 * @author sebastienmouret
 * 
 */
public class UpgradeFactoryItem implements CastleItem {

	// Player's castle
	private final Castle castle;

	public UpgradeFactoryItem(Castle castle) {

		this.castle = castle;
	}

	@Override
	public String getSuggestion() {

		return "Upgrade a building";
	}

	@Override
	public void perform() {

		// Choose list
		ArrayList<CastleItem> items = new ArrayList<CastleItem>();
		items.add(CastleItem.defaultItem);
		// Avalaible factories
		for (final WarriorProfile profile : WarriorProfile.values()) {
		// Next level possible for factory
			final Level level = castle.getNextFactoryLevel(profile);
			
			if (castle.canProduceWarrior(profile)
					&& !profile.equals(castle.getDefaultFactory()) && level != null) {
				items.add(new CastleItem() {

					@Override
					public String getSuggestion() {

						Price price = PriceFactory.getWarriorFactoryPrice(profile, level);
						return profile.name() + " factory, price : " + price.toString();
					}

					@Override
					public void perform() {

						
							if (castle.getPlayer().spend(
									PriceFactory.getWarriorFactoryPrice(profile, level))) {
								UpgradeFactoryItem.this.castle.upgradeFactory(profile);
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
