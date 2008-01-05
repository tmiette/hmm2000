package fr.umlv.hmm2000.building;

import java.util.ArrayList;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.manager.MoveCoreManager;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.salesentity.PriceFactory;
import fr.umlv.hmm2000.unit.Fightable;
import fr.umlv.hmm2000.unit.UnitFactory;
import fr.umlv.hmm2000.unit.profil.Level;
import fr.umlv.hmm2000.unit.profil.ProfilWarrior;

public class WarriorRecruitmentItem implements CastleItem {

	private final Castle castle;

	public WarriorRecruitmentItem(Castle castle) {

		this.castle = castle;
	}

	@Override
	public String getSuggestion() {

		return "Recruter un guerrier";
	}

	@Override
	public void perform() {

		ArrayList<CastleItem> items = new ArrayList<CastleItem>();
		items.add(CastleItem.defaultItem);
		for (final ProfilWarrior profil : ProfilWarrior.values()) {
			if (castle.canProduceWarrior(profil)) {
				items.add(new CastleItem() {

					@Override
					public String getSuggestion() {

						// TODO un tostring de profil et level + prix
						return profil.name() + castle.getFactoryLevel(profil);
					}

					@Override
					public void perform() {

						Level level = castle.getFactoryLevel(profil);
						if (castle.getPlayer().spend(
								PriceFactory.getWarriorPrice(profil, level))) {
							
							Fightable warrior = UnitFactory.createWarrior(profil, level);
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
