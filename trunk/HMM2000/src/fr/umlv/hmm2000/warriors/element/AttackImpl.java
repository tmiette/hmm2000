package fr.umlv.hmm2000.warriors.elements;

public class AttackImpl implements Element {

	private double damage;

	private double resistance;

	public AttackImpl(double damage, double resistance) {

		this.damage = damage;
		this.resistance = resistance;
	}

	@Override
	public double getDamage() {

		return this.damage;
	}

	@Override
	public double getResistance() {

		return this.resistance;
	}

}
