package fr.umlv.hmm2000.warrior.attack.elementary;

import fr.umlv.hmm2000.warrior.Attack;

public class Elementary implements Attack {

	private double damage;

	private double resistance;
	
	private ElementaryEnum type;
	
	
	public Elementary(ElementaryEnum type) {

		this.type = type;
		this.damage = 0;
		this.resistance = 0;
	}

	public Elementary(ElementaryEnum type, double damage, double resistance) {

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

	
	public ElementaryEnum getType() {
	
		return this.type;
	}

	
	public void setType(ElementaryEnum type) {
	
		this.type = type;
	}

}
