package fr.umlv.hmm2000.unit.profil;

import fr.umlv.hmm2000.engine.guiinterface.Sprite;
import fr.umlv.hmm2000.unit.Fightable;
import fr.umlv.hmm2000.unit.UnitFactory;

public enum ProfilMonster {

	TROLL(	Sprite.TROLL,
					new Fightable[] {
							UnitFactory.createWarrior(ProfilWarrior.FLIGHT, Level.LEVEL_1),
							UnitFactory.createWarrior(ProfilWarrior.FLIGHT, Level.LEVEL_1),
							UnitFactory.createWarrior(ProfilWarrior.GRUNT, Level.LEVEL_1),
							UnitFactory.createWarrior(ProfilWarrior.FLIGHT, Level.LEVEL_1),
							UnitFactory.createWarrior(ProfilWarrior.GRUNT, Level.LEVEL_1),
							UnitFactory.createWarrior(ProfilWarrior.WIZZARD, Level.LEVEL_1) }),
	ZOMBIE(Sprite.DEFAULT,
							new Fightable[] {
									UnitFactory
											.createWarrior(ProfilWarrior.FLIGHT, Level.LEVEL_1),
									UnitFactory.createWarrior(ProfilWarrior.GRUNT, Level.LEVEL_1),
									UnitFactory.createWarrior(ProfilWarrior.WIZZARD,
											Level.LEVEL_1) }),
	MUMMY(	Sprite.DEFAULT,
						new Fightable[] {
								UnitFactory.createWarrior(ProfilWarrior.FLIGHT, Level.LEVEL_1),
								UnitFactory.createWarrior(ProfilWarrior.GRUNT, Level.LEVEL_1),
								UnitFactory.createWarrior(ProfilWarrior.WIZZARD, Level.LEVEL_1) });

	private final Sprite sprite;

	private final Fightable[] units;

	private ProfilMonster(Sprite sprite, Fightable[] units) {

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
