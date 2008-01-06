package fr.umlv.hmm2000.building;

import java.util.ArrayList;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.engine.manager.MoveCoreManager;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.salesentity.PriceFactory;
import fr.umlv.hmm2000.unit.Fightable;
import fr.umlv.hmm2000.unit.UnitFactory;
import fr.umlv.hmm2000.unit.profile.Level;
import fr.umlv.hmm2000.unit.profile.WarriorProfile;

/**
 * This class enables player to buy a new warrior in castle.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class WarriorRecruitmentItem implements CastleItem {

	// Player's castle
	private final Castle castle;

	public WarriorRecruitmentItem(Castle castle) {

		this.castle = castle;
	}

	@Override
	public String getSuggestion() {

		return "Buy new warrior";
	}

	@Override
	public void perform() {

		ArrayList<CastleItem> items = new ArrayList<CastleItem>();
		items.add(CastleItem.defaultItem);
		for (final WarriorProfile profile : WarriorProfile.values()) {
			if (castle.canProduceWarrior(profile)) {
				items.add(new CastleItem() {

					@Override
					public String getSuggestion() {

						Level level = castle.getFactoryLevel(profile);
						return profile.toString() + ", level : " + level.toString()
								+ ", price : "
								+ PriceFactory.getWarriorPrice(profile, level).toString();
					}

					@Override
					public void perform() {

						Level level = castle.getFactoryLevel(profile);
						if (castle.getPlayer().spend(
								PriceFactory.getWarriorPrice(profile, level))) {

							Fightable warrior = UnitFactory.createWarrior(profile, level);
							Location castleLocation = CoreEngine.map()
									.getLocationForMapForegroundElement(castle);
							// warrior is acquired by castle
							warrior.acquire(new MoveCoreManager.Encounter(castleLocation,
									castle, castleLocation));
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
