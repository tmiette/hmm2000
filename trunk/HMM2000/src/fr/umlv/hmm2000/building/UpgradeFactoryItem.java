package fr.umlv.hmm2000.building;

import java.util.ArrayList;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.salesentity.PriceFactory;
import fr.umlv.hmm2000.unit.profil.Level;
import fr.umlv.hmm2000.unit.profil.ProfilWarrior;

public class UpgradeFactoryItem implements CastleItem {

	private final Castle castle;

	public UpgradeFactoryItem(Castle castle) {

		this.castle = castle;
	}

	@Override
	public String getSuggestion() {

		return "Quelle batiment souhaitez vous am�liorer";
	}

	@Override
	public void perform() {

		ArrayList<CastleItem> items = new ArrayList<CastleItem>();
		items.add(CastleItem.defaultItem);
		for (final ProfilWarrior profil : ProfilWarrior.values()) {
			if (UpgradeFactoryItem.this.castle.canBuyWarrior(profil)) {
				items.add(new CastleItem() {

					@Override
					public String getSuggestion() {

						return "Usine de " + profil.name();
					}

					@Override
					public void perform() {

						Level level = castle.getFactoryLevel(profil);
						if (castle.getPlayer().spend(
								PriceFactory.getWarriorFactoryPrice(profil, level))) {
							UpgradeFactoryItem.this.castle.upgradeFactory(profil);
						}
						// TODO pas assez argent
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
