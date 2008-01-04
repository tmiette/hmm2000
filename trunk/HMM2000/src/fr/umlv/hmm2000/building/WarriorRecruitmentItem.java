package fr.umlv.hmm2000.building;

import java.util.ArrayList;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.manager.MoveCoreManager;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.warrior.Fightable;
import fr.umlv.hmm2000.warrior.UnitFactory;
import fr.umlv.hmm2000.warrior.Warrior;
import fr.umlv.hmm2000.warrior.exception.MaxNumberOfTroopsReachedException;
import fr.umlv.hmm2000.warrior.profil.ProfilWarrior;

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
			if (castle.canBuyWarrior(profil)) {
				items.add(new CastleItem() {

					@Override
					public String getSuggestion() {

						// TODO un tostring de profil et level + prix
						return profil.name() + castle.getFactoryLevel(profil);
					}

					@Override
					public void perform() {

						Fightable warrior = UnitFactory.createWarrior(profil, castle
								.getFactoryLevel(profil));
						Location castleLocation = CoreEngine.map()
								.getLocationForMapForegroundElement(castle);
						// warrior is acquired by castle
						warrior.acquire(new MoveCoreManager.Encounter(castleLocation,
								castle, castleLocation));
					}

				});
			}
		}
		CastleItem item = CoreEngine.requestCastleItem(items);
		if (item != CastleItem.defaultItem) {
			item.perform();
		}
	}
}
