package fr.umlv.hmm2000.warrior.profil;

import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.warrior.Fightable;
import fr.umlv.hmm2000.warrior.UnitFactory;

public enum ProfilHero {

	ARCHER(	Sprite.ARCHER,
					new Fightable[] { UnitFactory.createWarrior(ProfilWarrior.FLIGHT,
							Level.LEVEL_1) },
					new ElementAbility().addAbility(ElementaryEnum.POISON, 10, 10),
					10),
	LORD_OF_WAR(Sprite.LORDOFWAR,
							new Fightable[] { UnitFactory.createWarrior(ProfilWarrior.FLIGHT,
									Level.LEVEL_1) },
							new ElementAbility().addAbility(ElementaryEnum.POISON, 10, 10),
							10),
	SORCERER(	Sprite.SORCERER,
						new Fightable[] { UnitFactory.createWarrior(ProfilWarrior.FLIGHT,
								Level.LEVEL_1) },
						new ElementAbility().addAbility(ElementaryEnum.POISON, 10, 10),
						0);

	private final Sprite sprite;

	private final Fightable[] units;

	private final ElementAbility abilities;

	private final double attackValue;

	private ProfilHero(	Sprite sprite,
											Fightable[] units,
											ElementAbility abilities,
											double attackvalue) {

		this.sprite = sprite;
		this.units = units;
		this.abilities = abilities;
		this.attackValue = attackvalue;

	}

	public Sprite getSprite() {

		return this.sprite;
	}

	public Fightable[] getUnits() {

		return this.units;
	}

	
	public ElementAbility getAbilities() {
	
		return this.abilities;
	}

	
	public double getAttackValue() {
	
		return this.attackValue;
	}
	
	

}
