package fr.umlv.hmm2000.warrior.profil;

import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.war.BattlePositionMap;
import fr.umlv.hmm2000.warrior.ElementAbility;
import fr.umlv.hmm2000.warrior.Warrior;
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
					Sprite.FLIGHT,
					new ElementAbility().addAbility(ElementaryEnum.FIRE, 10, 10)
					.addAbility(ElementaryEnum.LIGHTNING, 10, 10)) {

		@Override
		public boolean isAttackable(Warrior attacker, Warrior defender) {

			return true;
		}

	},
	GRUNT(10,
				10,
				100,
				20,
				Sprite.GRUNT,
				new ElementAbility().addAbility(ElementaryEnum.FIRE, 10, 10)
				.addAbility(ElementaryEnum.LIGHTNING, 10, 10)) {

		@Override
		public boolean isAttackable(Warrior attacker, Warrior defender) {

			BattlePositionMap bpm = defender.getContainer()
					.getBattlePositionManager();
			return bpm.isInFirstLine(attacker, defender);

		}
	},
	WIZZARD(10,
					10,
					100,
					20,
					Sprite.WIZZARD,
					new ElementAbility().addAbility(ElementaryEnum.FIRE, 10, 10)
							.addAbility(ElementaryEnum.LIGHTNING, 10, 10)) {

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

	private ElementAbility abilities;

	private ProfilCreatures(double attackValue,
													double defenseValue,
													double health,
													int speed,
													Sprite sprite,
													ElementAbility abilities) {

		this.attackValue = attackValue;
		this.defenseValue = defenseValue;
		this.health = health;
		this.speed = speed;
		this.sprite = sprite;
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
	public int getSpeed() {

		return this.speed;
	}

	@Override
	public Sprite getSprite() {

		return this.sprite;
	}

	@Override
	public boolean isAttackable(Warrior attacker, Warrior defender) {

		return false;
	}

	@Override
	public String getLabel() {

		return this.toString();
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
