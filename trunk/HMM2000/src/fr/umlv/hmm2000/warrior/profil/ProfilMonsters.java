package fr.umlv.hmm2000.warrior.profil;

import java.util.HashMap;

import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.warrior.Attack;
import fr.umlv.hmm2000.warrior.Warrior;
import fr.umlv.hmm2000.warrior.attack.elementary.ElementaryEnum;
import fr.umlv.hmm2000.warrior.exception.WarriorDeadException;

public enum ProfilMonsters implements ProfilWarrior {

	GREMLIN(10,
					10,
					100,
					20,
					null,
					new Attack[] {}),
	OGRE(	10,
				10,
				100,
				20,
				null,
				new Attack[] {}),
	SITH(	10,
				10,
				100,
				20,
				null,
				new Attack[] {});

	private double attackValue;

	private double defenseValue;

	private double health;

	private int speed;

	private Sprite sprite;
	
	private HashMap<ElementaryEnum, Attack> elements;

	private ProfilMonsters(	double attackValue,
													double defenseValue,
													double health,
													int speed,
													Sprite sprite,
													Attack[] ee) {

		this.attackValue = attackValue;
		this.defenseValue = defenseValue;
		this.health = health;
		this.speed = speed;
		this.sprite = sprite;
		this.elements = new HashMap<ElementaryEnum, Attack>();
		for (Attack element : ee) {
			this.elements.put(element.getType(), element);
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
	public HashMap<ElementaryEnum, Attack> getElements() {

		return this.elements;
	}

	@Override
	public void attack(Warrior warrior, Attack attack)
			throws WarriorDeadException {

		// TODO Auto-generated method stub
		
	}

}
