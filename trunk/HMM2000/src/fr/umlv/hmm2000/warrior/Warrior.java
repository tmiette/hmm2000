package fr.umlv.hmm2000.warrior;

import java.util.HashMap;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.engine.event.EncounterEvent;
import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.map.MovableElement;
import fr.umlv.hmm2000.salesentity.Price;
import fr.umlv.hmm2000.salesentity.Sellable;
import fr.umlv.hmm2000.warrior.element.Element;
import fr.umlv.hmm2000.warrior.element.ElementEnum;
import fr.umlv.hmm2000.warrior.profil.ProfilWarrior;

public class Warrior extends MovableElement implements ProfilWarrior, Sellable {

	private double health;

	private int speed;

	private Sprite sprite;

	private double defenseValue;

	private double attackValue;

	private HashMap<ElementEnum, Element> elements;
	
	private String label;
	
	private Price price;

	Warrior(Player player,
					double health,
					int speed,
					Sprite sprite,
					double defenseValue,
					double attackValue,
					HashMap<ElementEnum, Element> elements) {

		super(player);
		this.health = health;
		this.speed = speed;
		this.sprite = sprite;
		this.defenseValue = defenseValue;
		this.attackValue = attackValue;
		this.elements = elements;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("Warrior : \n(Health = ");
		sb.append(this.health);
		sb.append(",StepCount = ");
		sb.append(this.speed);
		sb.append(",Attacks = ");
		sb.append(")");
		return sb.toString();
	}

	@Override
	public Sprite getSprite() {

		return this.sprite;
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

	public void setSpeed(int speed) {

		this.speed = speed;
	}

	@Override
	public int getStepCount() {

		return getSpeed();
	}

	@Override
	public HashMap<ElementEnum, Element> getElements() {

		return this.elements;
	}

	@Override
	public void accept(UIDisplayingVisitor visitor) {

		visitor.visit(this);

	}

	@Override
	public boolean encounter(EncounterEvent event) {

		System.err.println("Combat");
		return false;
	}

	@Override
	public void acquire(EncounterEvent event) {

		// TODO Auto-generated method stub
		
	}

	@Override
	public String getLabel() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Price getPrice() {

		return this.price;
	}
	
	
	public void setPrice() {

	}

	@Override
	public void nextDay() {

		// TODO Auto-generated method stub
		//do nothing
//		this.getPlayer().spend(price); for heroe
	}
	

}
