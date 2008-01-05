package fr.umlv.hmm2000.building;

import java.util.ArrayList;

import fr.umlv.hmm2000.PriceFactory;
import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.unit.UnitFactory;
import fr.umlv.hmm2000.warrior.profil.ProfilHero;

public class HeroRecruitmentItem implements CastleItem {

	private final Castle castle;

	public HeroRecruitmentItem(Castle castle) {

		this.castle = castle;
	}

	@Override
	public String getSuggestion() {

		return "Recruter un héros";
	}

	@Override
	public void perform() {

		ArrayList<CastleItem> items = new ArrayList<CastleItem>();
		items.add(CastleItem.defaultItem);
		for (final ProfilHero profil : ProfilHero.values()) {
			items.add(new CastleItem() {

				@Override
				public String getSuggestion() {

					return profil.name();
				}

				@Override
				public void perform() {

					if (!castle.getPlayer().spend(PriceFactory.getHeroPrice(profil))) {
						castle.addHero(UnitFactory.createHero(castle.getPlayer(), profil));
					}
					else {
						CoreEngine.fireMessage("Vous n'avez pas assez d'argent");
					}
				}

			});
		}
		CastleItem item = CoreEngine.requestCastleItem(items);
		if (item != CastleItem.defaultItem) {
			item.perform();
		}
	}

}
