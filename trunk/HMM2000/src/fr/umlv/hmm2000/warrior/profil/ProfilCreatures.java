package fr.umlv.hmm2000.warrior.profil;

import java.util.HashMap;

import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.warrior.element.Element;
import fr.umlv.hmm2000.warrior.element.ElementEnum;
import fr.umlv.hmm2000.warrior.element.ElementImpl;

public enum ProfilCreatures implements ProfilWarrior {

	FLIGHT(	10,
					10,
					100,
					20,
					Sprite.MERCHANT,
					new Element[] {new ElementImpl(ElementEnum.FIRE, 10, 10)}),
	GRUNT(10,
				10,
				100,
				20,
				Sprite.MERCHANT,
				new Element[] {}),
	WIZZARD(10,
					10,
					100,
					20,
					Sprite.MERCHANT,
					new Element[] {});

	private double attackValue;

	private double defenseValue;

	private double health;

	private int speed;

	private Sprite sprite;
	
	private HashMap<ElementEnum, Element> elements;

	private ProfilCreatures(double attackValue,
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
