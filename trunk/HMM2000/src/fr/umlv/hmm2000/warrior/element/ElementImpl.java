package fr.umlv.hmm2000.warrior.element;

public class ElementImpl implements Element {

	private double damage;

	private double resistance;
	
	private ElementEnum type;
	
	
	public ElementImpl(ElementEnum type) {

		this.type = type;
		this.damage = 0;
		this.resistance = 0;
	}

	public ElementImpl(ElementEnum type, double damage, double resistance) {

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

	
	public ElementEnum getType() {
	
		return this.type;
	}

	
	public void setType(ElementEnum type) {
	
		this.type = type;
	}

}
