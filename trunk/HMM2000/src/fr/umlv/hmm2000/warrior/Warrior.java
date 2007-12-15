package fr.umlv.hmm2000.warrior;

import java.util.HashMap;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.engine.event.EncounterEvent;
import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.map.MovableElement;
import fr.umlv.hmm2000.salesentity.Price;
import fr.umlv.hmm2000.salesentity.Sellable;
import fr.umlv.hmm2000.warrior.attack.elementary.ElementaryEnum;
import fr.umlv.hmm2000.warrior.exception.WarriorDeadException;
import fr.umlv.hmm2000.warrior.profil.ProfilWarrior;

public class Warrior extends MovableElement implements ProfilWarrior, Sellable {

	private double health;

	private int speed;

	private Sprite sprite;

	private double defenseValue;

	private double attackValue;

	private HashMap<ElementaryEnum, Attack> elements;

	private String label;

	private Price price;

	private Container container;

	Warrior(Player player,
					double health,
					int speed,
					Sprite sprite,
					double defenseValue,
					double attackValue,
					HashMap<ElementaryEnum, Attack> elements) {

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
	public HashMap<ElementaryEnum, Attack> getElements() {

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
	public void nextDay(int day) {

		// TODO Auto-generated method stub

	}

	public Container getContainer() {

		return this.container;
	}

	public void setContainer(Container container) {

		this.container = container;
	}

	@Override
	public void attack(Warrior warrior, Attack attack) throws WarriorDeadException {

		double damage = ((warrior.getAttackValue() + attack.getDamage()
				* ((100 - this.elements	.get(attack.getType())
																.getResistance()) / 100)) - this.getDefenseValue());
		warrior.setHealth(damage);
	}

	public void setHealth(double health) throws WarriorDeadException {

		this.health = health;
		if (this.health <= 0) {
			throw new WarriorDeadException("Warrior is dead");
		}
	}

}
