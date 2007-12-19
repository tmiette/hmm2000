package fr.umlv.hmm2000.warrior.profil;

import java.util.ArrayList;

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

	private final ArrayList<Fightable> units;

	private ProfilHero(	Sprite sprite,
											Fightable[] units) {

		this.sprite = sprite;
		this.units = new ArrayList<Fightable>();
		for (Fightable fightable : units) {
			this.units.add(fightable);
		}
	}

	public Sprite getSprite() {

		return this.sprite;
	}

}
