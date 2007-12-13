package fr.umlv.hmm2000.warriors.profil;

import java.util.HashMap;

import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.warriors.element.Element;
import fr.umlv.hmm2000.warriors.element.ElementEnum;

public enum ProfilMonsters implements ProfilWarrior {

	GREMLIN(10,
					10,
					100,
					20,
					null,
					new Element[] {}),
	OGRE(	10,
				10,
				100,
				20,
				null,
				new Element[] {}),
	SITH(	10,
				10,
				100,
				20,
				null,
				new Element[] {});

	private double attackValue;

	private double defenseValue;

	private double health;

	private int speed;

	private Sprite sprite;
	
	private HashMap<ElementEnum, Element> elements;

	private ProfilMonsters(	double attackValue,
													double defenseValue,
													double health,
													int speed,
													Sprite sprite,
													Element[] ee) {

		this.attackValue = attackValue;
		this.defenseValue = defenseValue;
		this.health = health;
		this.speed = speed;
		this.sprite = sprite;
		this.elements = new HashMap<ElementEnum, Element>();
		for (Element element : ee) {
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
	public HashMap<ElementEnum, Element> getElements() {

		return this.elements;
	}

}
