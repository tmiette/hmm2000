package fr.umlv.hmm2000.warriors.attacks;

public class Water implements Attack {

	private double damage;

	public static final double DEFAULT_DAMAGE = 50;

	private double resistance;

	public static final double DEFAULT_RESISTANCE = 50;

	public Water() {

		this.damage = DEFAULT_DAMAGE;
		this.resistance = DEFAULT_RESISTANCE;
	}
	
	public Water(double damage, double resistance) {

		this.damage = damage;
		this.resistance = resistance;
	}

	public void setDamage(double damage) {

		this.damage = damage;
	}

	public void setResistance(double resistance) {

		this.resistance = resistance;
	}

	@Override
	public double getDamageCount() {

		return this.damage;
	}

	@Override
	public double getResistanceCount() {

		return this.resistance;
	}

	@Override
	public boolean isPhysical() {

		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("Water: ");
		sb.append("(Damage = ");
		sb.append(this.damage);
		sb.append(", Resistance = ");
		sb.append(this.resistance);
		sb.append(")");
		return sb.toString();
	}

}
