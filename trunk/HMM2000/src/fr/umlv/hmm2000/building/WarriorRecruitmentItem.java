package fr.umlv.hmm2000.building;

import java.util.ArrayList;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.manager.MoveCoreManager;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.salesentity.PriceFactory;
import fr.umlv.hmm2000.unit.Fightable;
import fr.umlv.hmm2000.unit.UnitFactory;
import fr.umlv.hmm2000.unit.profile.Level;
import fr.umlv.hmm2000.unit.profile.WarriorProfile;

public class WarriorRecruitmentItem implements CastleItem {

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

						// TODO un tostring de profile et level + prix
						return profile.name() + castle.getFactoryLevel(profile);
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

						//TODO pas assez d'argent
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
