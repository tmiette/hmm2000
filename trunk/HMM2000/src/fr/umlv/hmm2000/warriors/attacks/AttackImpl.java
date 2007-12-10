package fr.umlv.hmm2000.warriors.attacks;

public class AttackImpl implements Attack {

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
