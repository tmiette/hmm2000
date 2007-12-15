package fr.umlv.hmm2000.warrior.profil;

import java.util.HashMap;

import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.war.BattlePositionMap;
import fr.umlv.hmm2000.warrior.Attack;
import fr.umlv.hmm2000.warrior.Warrior;
import fr.umlv.hmm2000.warrior.attack.elementary.Elementary;
import fr.umlv.hmm2000.warrior.attack.elementary.ElementaryEnum;

public enum ProfilCreatures implements ProfilWarrior {

	/*
	 * Enum default values description : TYPE(physicalAttackValue,
	 * physycalDefenseValue, health, speed, sprite, arrayAttacks)
	 */
	FLIGHT(	10,
					10,
					100,
					20,
					Sprite.MERCHANT,
					new Attack[] { new Elementary(ElementaryEnum.FIRE,
																				10,
																				10) }) {

		@Override
		public boolean isAttackable(Warrior attacker, Warrior defender) {

			return true;
		}

	},
	GRUNT(10,
				10,
				100,
				20,
				Sprite.HEROE,
				new Attack[] {}) {

		@Override
		public boolean isAttackable(Warrior attacker, Warrior defender) {

			BattlePositionMap bpm = defender.getContainer()
																			.getBattlePositionManager();
			return bpm.isInFirstLine(	attacker,
																defender);

		}
	},
	WIZZARD(10,
					10,
					100,
					20,
					Sprite.MERCHANT,
					new Attack[] {}) {

		@Override
		public boolean isAttackable(Warrior attacker, Warrior defender) {

			BattlePositionMap bpm = defender.getContainer()
																			.getBattlePositionManager();
			return bpm.isInFirstLine(defender);
		}

	};

	private double attackValue;

	private double defenseValue;

	private double health;

	private int speed;

	private Sprite sprite;

	private HashMap<ElementaryEnum, Attack> attacks;

	private ProfilWarrior profil;

	private ProfilCreatures(double attackValue,
													double defenseValue,
													double health,
													int speed,
													Sprite sprite,
													Attack[] attacks) {

		this.attackValue = attackValue;
		this.defenseValue = defenseValue;
		this.health = health;
		this.speed = speed;
		this.sprite = sprite;
		this.attacks = new HashMap<ElementaryEnum, Attack>();
		for (Attack attack : attacks) {
			this.attacks.put(attack.getType(),
			                 attack);
		}
		this.profil = valueOf(this.name());
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

		return this.profil;
	}

	@Override
	public boolean isAttackable(Warrior attacker, Warrior defender) {

		return false;
	}
	
	@Override
	public String getLabel() {
	
		return this.toString();
	}

}
