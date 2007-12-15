package fr.umlv.hmm2000.warrior.profil;

import java.util.HashMap;

import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.warrior.Attack;
import fr.umlv.hmm2000.warrior.Warrior;
import fr.umlv.hmm2000.warrior.attack.elementary.ElementaryEnum;

public enum ProfilHeroe implements ProfilWarrior {

	ARCHER(	10,
					10,
					100,
					20,
					Sprite.HEROE,
					new ProfilWarrior[] { ProfilCreatures.GRUNT },
					new Attack[] {}),
	LORD_OF_WAR(10,
							10,
							100,
							20,
							Sprite.HEROE,
							new ProfilWarrior[] { ProfilCreatures.GRUNT },
							new Attack[] {}),
	SORCERER(	10,
						10,
						100,
						20,
						Sprite.HEROE,
						new ProfilWarrior[] { ProfilCreatures.GRUNT },
						new Attack[] {});

	private double attackValue;

	private double defenseValue;

	private double health;

	private int speed;

	private Sprite sprite;

	private HashMap<ElementaryEnum, Attack> attacks;

	private ProfilWarrior[] profilWarrior;

	public ProfilWarrior[] getProfilWarrior() {

		return this.profilWarrior;
	}

	private ProfilHeroe(double attackValue,
											double defenseValue,
											double health,
											int speed,
											Sprite sprite,
											ProfilWarrior[] pw,
											Attack[] attacks) {

		this.attackValue = attackValue;
		this.defenseValue = defenseValue;
		this.health = health;
		this.speed = speed;
		this.sprite = sprite;
		this.profilWarrior = pw;
		this.attacks = new HashMap<ElementaryEnum, Attack>();
		for (Attack attack : attacks) {
			this.attacks.put(	attack.getType(),
												attack);
		}
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
	public int getSpeed() {

		return this.speed;
	}

	@Override
	public Sprite getSprite() {

		return this.sprite;
	}

	@Override
	public HashMap<ElementaryEnum, Attack> getAttacks() {

		return this.attacks;
	}

	@Override
	public ProfilWarrior getProfil() {

		return valueOf(this.name());
	}

	@Override
	public boolean isAttackable(Warrior attacker, Warrior defender) {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getLabel() {

		return this.toString();
	}
}
