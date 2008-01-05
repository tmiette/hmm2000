package fr.umlv.hmm2000.building;

import java.util.ArrayList;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.salesentity.PriceFactory;
import fr.umlv.hmm2000.unit.profil.Level;
import fr.umlv.hmm2000.unit.profil.ProfilWarrior;

/**
 * This class permits to a player to upgrade a unit factory level in his castle.
 * Higher factory level is stronger warrior produced are.
 * 
 * @author sebastienmouret
 * 
 */
public class UpgradeFactoryItem implements CastleItem {

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
		for (final ProfilWarrior profil : ProfilWarrior.values()) {
			if (castle.canProduceWarrior(profil)
					&& !profil.equals(Castle.defaultWarrior)) {
				items.add(new CastleItem() {

					@Override
					public String getSuggestion() {

						return profil.name() + " factory.";
					}

					@Override
					public void perform() {

						// Next level possible for default castle factory
						Level level = castle.getNextFactoryLevel(profil);

						if (level != null) {
							if (castle.getPlayer().spend(
									PriceFactory.getWarriorFactoryPrice(profil, level))) {
								UpgradeFactoryItem.this.castle.upgradeFactory(profil);
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

					}

				});
			}

		}
		// Adding items to choose manager
		CastleItem item = CoreEngine.requestCastleItem(items);
		if (item != null && item != CastleItem.defaultItem) {
			item.perform();
		}
	}

}
