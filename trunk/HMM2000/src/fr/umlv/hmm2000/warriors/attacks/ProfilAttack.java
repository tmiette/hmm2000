package fr.umlv.hmm2000.warriors.attacks;

public enum ProfilAttack implements Attack {
	FIRE(10, 10), LIGHTNING(10, 10), WATER(10, 10);

	private double damage;

	private double resistance;

	private ProfilAttack(double damage, double resistance) {

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
