package fr.umlv.hmm2000.warrior.profil;

import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.warrior.ElementAbility;
import fr.umlv.hmm2000.warrior.Fightable;
import fr.umlv.hmm2000.warrior.attack.elementary.ElementaryEnum;

public enum ProfilHeroe {

	ARCHER(	20,
					Sprite.ARCHER,
					new ElementAbility().addAbility(ElementaryEnum.FIRE, 10, 10)
					.addAbility(ElementaryEnum.LIGHTNING, 10, 10)),
	LORD_OF_WAR(20,
							Sprite.LORDOFWAR,
							new ElementAbility().addAbility(ElementaryEnum.FIRE, 10, 10)
							.addAbility(ElementaryEnum.LIGHTNING, 10, 10)),
	SORCERER(	20,
						Sprite.SORCERER,
						new ElementAbility().addAbility(ElementaryEnum.FIRE, 10, 10)
						.addAbility(ElementaryEnum.LIGHTNING, 10, 10));

	private int speed;

	private Sprite sprite;

	private ElementAbility abilities;

	private ProfilHeroe(int speed,
											Sprite sprite,
											ElementAbility abilities) {

		this.speed = speed;
		this.sprite = sprite;
		this.abilities = abilities;
	}

	public String getLabel() {

		return this.toString();
	}


	public int getSpeed() {

		return this.speed;
	}

	public Sprite getSprite() {

		return this.sprite;
	}

	public boolean isAttackable(Fightable attacker, Fightable defender) {

		// TODO Auto-generated method stub
		return false;
	}

	public String getProfilName() {

		return this.name();
	}

	public ElementAbility getAbilities() {
	
		return this.abilities;
	}
}
