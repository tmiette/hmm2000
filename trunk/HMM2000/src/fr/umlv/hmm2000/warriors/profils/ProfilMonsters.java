package fr.umlv.hmm2000.warriors.profils;

import fr.umlv.hmm2000.gui.Sprite;

public enum ProfilMonsters implements ProfilWarrior {

	GREMLIN(10,
					10,
					100,
					20,
					null),
	OGRE(	10,
				10,
				100,
				20,
				null),
	SITH(	10,
				10,
				100,
				20,
				null);

	private double attackValue;

	private double defenseValue;

	private double health;

	private int speed;

	private Sprite sprite;

	private ProfilMonsters(	double attackValue,
													double defenseValue,
													double health,
													int speed,
													Sprite sprite) {

		this.attackValue = attackValue;
		this.defenseValue = defenseValue;
		this.health = health;
		this.speed = speed;
		this.sprite = sprite;
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

}
