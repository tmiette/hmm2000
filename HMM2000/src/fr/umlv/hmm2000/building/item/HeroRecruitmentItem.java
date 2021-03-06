package fr.umlv.hmm2000.building.item;

import java.util.ArrayList;

import fr.umlv.hmm2000.building.Castle;
import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.salesentity.PriceFactory;
import fr.umlv.hmm2000.unit.UnitFactory;
import fr.umlv.hmm2000.unit.profile.HeroProfile;

/**
 * This class permits to the player to buy a new hero from castle
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class HeroRecruitmentItem implements CastleItem {

	// Player's castle
	private final Castle castle;

	public HeroRecruitmentItem(Castle castle) {

		this.castle = castle;
	}

	@Override
	public String getSuggestion() {

		return "Buy a new hero";
	}

	@Override
	public void perform() {

		ArrayList<CastleItem> items = new ArrayList<CastleItem>();
		items.add(CastleItem.defaultItem);
		for (final HeroProfile profile : HeroProfile.values()) {
			items.add(new CastleItem() {

				@Override
				public String getSuggestion() {

					
					return profile.name() + ", price : " + PriceFactory.getHeroPrice(profile);
				}

				@Override
				public void perform() {

					if (castle.getPlayer().spend(PriceFactory.getHeroPrice(profile))) {
						castle.addHero(UnitFactory.createHero(castle.getPlayer(), profile));
					}
					else {
						CoreEngine.fireMessage("You don't have enough resources.",
								HMMUserInterface.WARNING_MESSAGE);
					}
				}

			});
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
