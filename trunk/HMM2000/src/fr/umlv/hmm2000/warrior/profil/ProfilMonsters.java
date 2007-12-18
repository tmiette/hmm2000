package fr.umlv.hmm2000.warrior.profil;

import java.util.HashMap;

import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.warrior.Attack;
import fr.umlv.hmm2000.warrior.ElementAbility;
import fr.umlv.hmm2000.warrior.Fightable;
import fr.umlv.hmm2000.warrior.attack.elementary.ElementaryEnum;

public enum ProfilMonsters implements ProfilWarrior {

	GREMLIN(10,
					10,
					100,
					20,
					null,
					new ElementAbility().addAbility(ElementaryEnum.FIRE, 10, 10)
					.addAbility(ElementaryEnum.LIGHTNING, 10, 10)){
		@Override
		public boolean isAttackable(Fightable attacker, Fightable defender) {

			return false;
		}
	},
	OGRE(	10,
				10,
				100,
				20,
				null,
				new ElementAbility().addAbility(ElementaryEnum.FIRE, 10, 10)
				.addAbility(ElementaryEnum.LIGHTNING, 10, 10)){
		@Override
		public boolean isAttackable(Fightable attacker, Fightable defender) {

			return false;
		}
	},
	SITH(	10,
				10,
				100,
				20,
				null,
				new ElementAbility().addAbility(ElementaryEnum.FIRE, 10, 10)
				.addAbility(ElementaryEnum.LIGHTNING, 10, 10)){
		@Override
		public boolean isAttackable(Fightable attacker, Fightable defender) {

			return false;
		}
	};

	private double attackValue;

	private double defenseValue;

	private double health;

	private int speed;

	private Sprite sprite;
	
	private HashMap<ElementaryEnum, Attack> elements;
	
	private ElementAbility abilities;

	private ProfilMonsters(	double attackValue,
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
		this.elements = new HashMap<ElementaryEnum, Attack>();
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
	public Sprite getSprite() {

		return this.sprite;
	}

	@Override
	public boolean isAttackable(Fightable attacker, Fightable defender) {

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
