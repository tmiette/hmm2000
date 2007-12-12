package fr.umlv.hmm2000.warriors.elements;

public class ElementImpl implements Element {

	private double damage;

	private double resistance;
	
	
	public ElementImpl() {

		this.damage = 0;
		this.resistance = 0;
	}

	public ElementImpl(double damage, double resistance) {

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
