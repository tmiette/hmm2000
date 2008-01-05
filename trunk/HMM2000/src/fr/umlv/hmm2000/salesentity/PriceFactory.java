package fr.umlv.hmm2000.salesentity;

import fr.umlv.hmm2000.resource.Resource.Kind;
import fr.umlv.hmm2000.unit.profil.Level;
import fr.umlv.hmm2000.unit.profil.ProfilHero;
import fr.umlv.hmm2000.unit.profil.ProfilWarrior;

public class PriceFactory {

	public static Price getWarriorPrice(ProfilWarrior profil, Level level) {

		Price price = new Price();
		int gold = (2 * (int) profil.getHealth() + 3 * (int) profil
				.getPhysicalDefenseValue()) / 10;
		gold = gold * (int) level.getRatio();
		price.addResource(Kind.GOLD, gold);
		return price;
	}

	public static Price getWarriorFactoryPrice(ProfilWarrior profil, Level level) {

		Price price = new Price();
		int gold = (2 * (int) profil.getHealth() + 3 * (int) profil
				.getPhysicalDefenseValue()) / 10;
		gold = gold * (int) level.getRatio();
		gold = gold * 2;
		price.addResource(Kind.GOLD, gold);
		return price;
	}

	public static Price getHeroPrice(ProfilHero profil) {

		Price price = new Price();
		int gold = (2 * (int) profil.getAttackPriority() + 3 * (int) profil
				.getUnits().length) / 10;
		gold = gold * 2;
		price.addResource(Kind.GOLD, gold);
		return price;
	}
}
