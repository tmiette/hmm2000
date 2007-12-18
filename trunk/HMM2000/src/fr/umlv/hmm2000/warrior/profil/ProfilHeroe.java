package fr.umlv.hmm2000.warrior.profil;

import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.warrior.ElementAbility;
import fr.umlv.hmm2000.warrior.Fightable;
import fr.umlv.hmm2000.warrior.attack.elementary.ElementaryEnum;

public enum ProfilHeroe implements ProfilWarrior {

	ARCHER(	10,
					10,
					100,
					20,
					Sprite.ARCHER,
					new ProfilWarrior[] { ProfilCreatures.GRUNT },
					new ElementAbility().addAbility(ElementaryEnum.FIRE, 10, 10)
					.addAbility(ElementaryEnum.LIGHTNING, 10, 10)),
	LORD_OF_WAR(10,
							10,
							100,
							20,
							Sprite.LORDOFWAR,
							new ProfilWarrior[] { ProfilCreatures.GRUNT },
							new ElementAbility().addAbility(ElementaryEnum.FIRE, 10, 10)
							.addAbility(ElementaryEnum.LIGHTNING, 10, 10)),
	SORCERER(	10,
						10,
						100,
						20,
						Sprite.SORCERER,
						new ProfilWarrior[] { ProfilCreatures.GRUNT },
						new ElementAbility().addAbility(ElementaryEnum.FIRE, 10, 10)
						.addAbility(ElementaryEnum.LIGHTNING, 10, 10));

	private double attackValue;

	private double defenseValue;

	private double health;

	private int speed;

	private Sprite sprite;

	private ProfilWarrior[] profilWarrior;
	
	private ElementAbility abilities;

	private ProfilHeroe(double attackValue,
											double defenseValue,
											double health,
											int speed,
											Sprite sprite,
											ProfilWarrior[] pw,
											ElementAbility abilities) {

		this.attackValue = attackValue;
		this.defenseValue = defenseValue;
		this.health = health;
		this.speed = speed;
		this.sprite = sprite;
		this.profilWarrior = pw;
		this.abilities = abilities;
	}

	@Override
	public double getAttackValue() {

		return this.attackValue;
	}

	@Override
	public double getDefenseValue() {

		return this.defenseValue;
	}

	@Override
	public double getHealth() {

		return this.health;
	}

	@Override
	public String getLabel() {

		return this.toString();
	}

	public ProfilWarrior[] getProfilWarrior() {

		return this.profilWarrior;
	}

	@Override
	public int getSpeed() {

		return this.speed;
	}

	@Override
	public Sprite getSprite() {

		return this.sprite;
	}

	@Override
	public boolean isAttackable(Fightable attacker, Fightable defender) {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getProfilName() {

		return this.name();
	}

	@Override
	public ProfilWarrior getProfil() {

		return this;
	}
	
	@Override
	public ElementAbility getAbilities() {
	
		return this.abilities;
	}
}
