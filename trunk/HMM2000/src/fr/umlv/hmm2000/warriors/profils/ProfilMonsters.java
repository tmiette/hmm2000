package fr.umlv.hmm2000.warriors.profils;

import java.util.ArrayList;

import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.warriors.attacks.Attack;
import fr.umlv.hmm2000.warriors.attacks.AttacksFactory;
import fr.umlv.hmm2000.warriors.attacks.ProfilAttack;

public enum ProfilMonsters implements ProfilWarrior {

	GREMLIN(10, 10, 100, 20, null, AttacksFactory.createAttacks(ProfilAttack.LIGHTNING)),
	OGRE(10, 10, 100, 20, null, AttacksFactory.createAttacks(ProfilAttack.LIGHTNING)),
	SITH(10, 10, 100, 20, null, AttacksFactory.createAttacks(ProfilAttack.LIGHTNING));

	private double attackValue;

	private ArrayList<Attack> attacks;

	private double defenseValue;

	private double health;

	private int speed;

	private Sprite sprite;

	private ProfilMonsters(double attackValue, double defenseValue, double health,
			int speed, Sprite sprite, ArrayList<Attack> a){

		this.attackValue = attackValue;
		this.attacks = a;
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
	public ArrayList<Attack> getListOfAttacks() {

		return this.attacks;
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
