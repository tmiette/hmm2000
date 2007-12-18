package fr.umlv.hmm2000.warrior;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.event.EncounterEvent;
import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.map.MovableElement;
import fr.umlv.hmm2000.resource.Resource.Kind;
import fr.umlv.hmm2000.salesentity.Price;
import fr.umlv.hmm2000.salesentity.Sellable;
import fr.umlv.hmm2000.util.Pair;
import fr.umlv.hmm2000.warrior.exception.MaxNumberOfTroopsReachedException;
import fr.umlv.hmm2000.warrior.exception.WarriorDeadException;
import fr.umlv.hmm2000.warrior.exception.WarriorNotReachableException;
import fr.umlv.hmm2000.warrior.profil.ProfilWarrior;

public class Warrior implements Sellable, Fightable {

	private static double WARRIORS_COUNT = 0;

	private double id;

	private double health;

	private Sprite sprite;

	private double defenseValue;

	private double attackValue;

	private final ElementAbility abilities;

	private Container container;

	private ProfilWarrior profil;

	Warrior(ProfilWarrior profil) {

		this.profil = profil;
		this.health = this.profil.getHealth();
		this.sprite = this.profil.getSprite();
		this.defenseValue = this.profil.getDefenseValue();
		this.attackValue = this.profil.getAttackValue();
		this.id = WARRIORS_COUNT++;
		this.abilities = profil.getAbilities();
	}

	@Override
	public void accept(UIDisplayingVisitor visitor) {

		visitor.visit(this);

	}

	@Override
	public void acquire(EncounterEvent event) {

		MovableElement element = event.getSender();
		if (element instanceof Container) {
			try {
				((Container) element).addWarrior(this);
			}
			catch (MaxNumberOfTroopsReachedException e) {
				for (Pair<Kind, Integer> pair : this.getPrice().getResourcesList()) {
					element.getPlayer().addResource(pair.getFirstElement(),
							pair.getSecondElement());
				}
				CoreEngine.uiManager().displayMessage("Vous avez trop d'unit√©.");
			}
		}
	}

	@Override
	public boolean encounter(EncounterEvent event) {

		return true;
	}

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Warrior)) {
			return false;
		}
		return ((Warrior) obj).id == this.id;
	}

	@Override
	public double getAttackValue() {

		return this.attackValue;
	}

	@Override
	public Container getContainer() {

		return this.container;
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
	public double getId() {

		return this.id;
	}

	@Override
	public String getLabel() {

		return this.profil.getLabel();
	}

	@Override
	public Price getPrice() {

		Price price = new Price();
		// TODO trouver une methode de calcul du prix
		int gold = (2 * (int) this.profil.getHealth() + 3 * (int) this.profil
				.getDefenseValue()) / 10;
		price.addResource(Kind.GOLD, gold);
		return price;
	}

	public ProfilWarrior getProfil() {

		return this.profil;
	}

	public String getProfilName() {

		return this.profil.getProfilName();
	}

	@Override
	public Sprite getSprite() {

		return this.sprite;
	}

	@Override
	public int hashCode() {

		return (int) this.id;
	}

	@Override
	public void nextDay(int day) {

		// do nothing
	}

	@Override
	public void performAttack(Warrior defender) throws WarriorDeadException,
			WarriorNotReachableException {

		if (!profil.isAttackable(this, defender)) {
			throw new WarriorNotReachableException("This warrior is not reachable");
		}
		double damage = this.abilities.getDamage(defender.abilities);
		double health = defender.health
				- (this.attackValue + damage - this.defenseValue);
		defender.setHealth(health);
		// double damage = ((this.getAttackValue() + attack.getDamage()
		// * ((100 - defender.getAttacks().get(attack.getType()).getResistance()) /
		// 100)) - defender
		// .getDefenseValue());
		// defender.setHealth(damage);
	}

	@Override
	public void setAttackValue(double attackValue) {

		this.attackValue = attackValue;
	}

	@Override
	public void setContainer(Container container) {

		this.container = container;
	}

	@Override
	public void setDefenseValue(double defenseValue) {

		this.defenseValue = defenseValue;
	}

	@Override
	public void setHealth(double health) throws WarriorDeadException {

		this.health = health;
		if (this.health <= 0) {
			throw new WarriorDeadException("Warrior is dead");
		}
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("Warrior n° " + this.id);
		sb.append(" ");
		return sb.toString();
	}

}
