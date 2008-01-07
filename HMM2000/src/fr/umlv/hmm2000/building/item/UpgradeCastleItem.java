package fr.umlv.hmm2000.building.item;

import fr.umlv.hmm2000.building.Castle;
import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.salesentity.PriceFactory;
import fr.umlv.hmm2000.unit.profile.Level;

/**
 * This class permit to the player to upgrade default level factory's castle.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class UpgradeCastleItem implements CastleItem {

	// Player's castle
	private final Castle castle;

	public UpgradeCastleItem(Castle castle) {

		this.castle = castle;
	}

	@Override
	public String getSuggestion() {

		// Next level possible for default castle factory
		Level level = castle.getNextFactoryLevel(castle.getDefaultFactory());
		return "Upgrade castle, price : "
				+ PriceFactory
						.getWarriorFactoryPrice(castle.getDefaultFactory(), level);
	}

	@Override
	public void perform() {

		// Next level possible for default castle factory
		Level level = castle.getNextFactoryLevel(castle.getDefaultFactory());
		if (level != null) {
			if (castle.getPlayer().spend(
					PriceFactory
							.getWarriorFactoryPrice(castle.getDefaultFactory(), level))) {
				castle.upgradeFactory(castle.getDefaultFactory());
			}
			else {
				CoreEngine.fireMessage("You don't have enough resources.",
						HMMUserInterface.WARNING_MESSAGE);
			}
		}
		else {
			CoreEngine.fireMessage(
					"Level too high. You have already the best level.",
					HMMUserInterface.WARNING_MESSAGE);
		}

		// Refreshing the view
		CoreEngine.selectionManager().perform(
				CoreEngine.map().getLocationForMapForegroundElement(castle));
	}

}
