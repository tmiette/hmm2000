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
import fr.umlv.hmm2000.warrior.exception.WarriorNotReachableException;
import fr.umlv.hmm2000.warrior.profil.ProfilWarrior;

public class Warrior2 extends MovableElement implements ProfilWarrior, Sellable {

	private double health;

	private int speed;

	private Sprite sprite;

	private double defenseValue;

	private double attackValue;

	private HashMap<ElementaryEnum, Attack> elements;

	private String label;

	private Price price;

	private Container container;

	private ProfilWarrior profil;

	Warrior2(	Player player,
						ProfilWarrior profil) {

		super(player);
		this.profil = profil;
		this.health = this.profil.getHealth();
		this.speed = this.profil.getSpeed();
		this.sprite = this.profil.getSprite();
		this.defenseValue = this.profil.getDefenseValue();
		this.attackValue = this.profil.getAttackValue();
		this.elements = this.profil.getAttacks();
		this.label = this.profil.getLabel();
	}

	@Override
	public void accept(UIDisplayingVisitor visitor) {

		visitor.visit(this);

	}

	@Override
	public void acquire(EncounterEvent event) {

		// TODO Auto-generated method stub

	}

	@Override
	public boolean encounter(EncounterEvent event) {

		System.err.println("Combat");
		return false;
	}

	@Override
	public double getAttackValue() {

		return this.attackValue;
	}

	public Container getContainer() {

		return this.container;
	}

	@Override
	public double getDefenseValue() {

		return this.defenseValue;
	}

	@Override
	public HashMap<ElementaryEnum, Attack> getAttacks() {

		return this.elements;
	}

	@Override
	public double getHealth() {

		return this.health;
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

	@Override
	public ProfilWarrior getProfil() {

		// TODO Auto-generated method stub
		return null;
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
	public int getStepCount() {

		return getSpeed();
	}

	@Override
	public boolean isAttackable(Warrior attacker, Warrior defender) {

		return this.profil.isAttackable(attacker,
																		defender);
	}

	@Override
	public void nextDay(int day) {

		// TODO Auto-generated method stub

	}

	public void performAttack(Warrior attacker, Warrior defender, Attack attack)
			throws WarriorDeadException, WarriorNotReachableException {

		if (!profil.isAttackable(	attacker,
															defender)) {
			throw new WarriorNotReachableException("This warrior is not reachable");
		}
		double damage = ((attacker.getAttackValue() + attack.getDamage()
				* ((100 - defender.getAttacks()	.get(attack.getType())
																		.getResistance()) / 100)) - defender.getDefenseValue());
		defender.setHealth(damage);
	}

	public void setContainer(Container container) {

		this.container = container;
	}

	public void setHealth(double health) throws WarriorDeadException {

		this.health = health;
		if (this.health <= 0) {
			throw new WarriorDeadException("Warrior is dead");
		}
	}

	public void setPrice() {

	}

	public void setSpeed(int speed) {

		this.speed = speed;
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

}
