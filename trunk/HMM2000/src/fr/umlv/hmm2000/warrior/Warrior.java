package fr.umlv.hmm2000.warrior;

import java.util.HashMap;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.event.EncounterEvent;
import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.map.MovableElement;
import fr.umlv.hmm2000.resource.Resource.Kind;
import fr.umlv.hmm2000.salesentity.Price;
import fr.umlv.hmm2000.salesentity.Sellable;
import fr.umlv.hmm2000.warrior.attack.elementary.ElementaryEnum;
import fr.umlv.hmm2000.warrior.exception.MaxNumberOfTroopsReachedException;
import fr.umlv.hmm2000.warrior.exception.WarriorDeadException;
import fr.umlv.hmm2000.warrior.exception.WarriorNotReachableException;
import fr.umlv.hmm2000.warrior.profil.ProfilWarrior;

public class Warrior extends MovableElement implements ProfilWarrior, Sellable {
	
	private static double WARRIORS_COUNT = 0;
	
	private double id;

	private double health;

	private int speed;

	 private double stepCount;
	
	private Sprite sprite;

	private double defenseValue;

	private double attackValue;

	private HashMap<ElementaryEnum, Attack> elements;

	private Container container;

	private ProfilWarrior profil;

	Warrior(	Player player,
						ProfilWarrior profil) {

		super(player);
		this.profil = profil;
		this.health = this.profil.getHealth();
		this.speed = this.profil.getSpeed();
		this.stepCount = this.speed;
		this.sprite = this.profil.getSprite();
		this.defenseValue = this.profil.getDefenseValue();
		this.attackValue = this.profil.getAttackValue();
		this.elements = this.profil.getAttacks();
		this.id = WARRIORS_COUNT++;
	}

	@Override
	public void accept(UIDisplayingVisitor visitor) {

		visitor.visit(this);

	}
	
	
	public void setAttackValue(double attackValue) {

		this.attackValue = attackValue;
	}
	
	
	public void setDefenseValue(double defenseValue) {

		this.defenseValue = defenseValue;
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
	public int getSpeed() {

		return this.speed;
	}

	@Override
	public Sprite getSprite() {

		return this.sprite;
	}

	@Override
	public boolean isAttackable(Warrior attacker, Warrior defender) {

		return this.profil.isAttackable(attacker,
																		defender);
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

		//TODO
	}

	public void setSpeed(int speed) {

		this.speed = speed;
	}
	
	
	@Override
	public int hashCode() {
	
		return (int)this.id;
	}
	
	@Override
	public boolean equals(Object obj) {
	
		if (!(obj instanceof Warrior)) {
			return false;
		}
		return ((Warrior)obj).id == this.id;
	}
	
	@Override
	public String getProfilName() {
	
		return this.profil.getProfilName();
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
	  public double getStepCount() {
	    return this.stepCount;
	  }

	  @Override
	  public void setStepCount(double stepCount) {
	    this.stepCount = stepCount >= 0 ? stepCount : 0;
	  }

	  @Override
	  public String getLabel() {
	    return this.profil.getLabel();
	  }

	  @Override
	  public Price getPrice() {
	    Price price = new Price();
	    // TODO trouver une methode de calcul du prix
	    int gold = (2 * (int)this.profil.getHealth() + 3 * (int)this.profil.getDefenseValue()) / 10;
	    price.addResource(Kind.GOLD, gold);
	    return price;
	  }

	  @Override
	  public void acquire(EncounterEvent event) {
	    if (event.getSender() instanceof Container) {
	      try {
          ((Container) event.getSender()).addWarrior(this);
        } catch (MaxNumberOfTroopsReachedException e) {
          CoreEngine.uiManager().displayMessage("Vous avez trop d'unité.");
        }
	    }
	  }

	  @Override
	  public void nextDay(int day) {
	    this.stepCount = this.speed;
	  }
	
}
