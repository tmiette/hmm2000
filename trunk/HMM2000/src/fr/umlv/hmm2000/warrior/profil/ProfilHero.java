package fr.umlv.hmm2000.warrior.profil;

import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.warrior.Fightable;
import fr.umlv.hmm2000.warrior.UnitFactory;

public enum ProfilHero {

	ARCHER(	Sprite.ARCHER,
					new Fightable[] { UnitFactory.createWarrior(ProfilWarrior.FLIGHT,
							Level.LEVEL_1) }),
	LORD_OF_WAR(Sprite.LORDOFWAR,
							new Fightable[] { UnitFactory.createWarrior(ProfilWarrior.FLIGHT,
									Level.LEVEL_1) }),
	SORCERER(	Sprite.SORCERER,
						new Fightable[] { UnitFactory.createWarrior(ProfilWarrior.FLIGHT,
								Level.LEVEL_1) });

	private final Sprite sprite;

	private final Fightable[] units;

	private ProfilHero(	Sprite sprite,
											Fightable[] units) {

		this.sprite = sprite;
		this.units = units;
		
	}

	public Sprite getSprite() {

		return this.sprite;
	}

	
	public Fightable[] getUnits() {
	
		return this.units;
	}
	


}
