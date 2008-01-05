package fr.umlv.hmm2000.unit.profil;

/**
 * This enum defines unit and buiding level. It contains ratio which will be
 * applied on value to improve it. It contains also a reference to the next
 * level.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public enum Level {

	LEVEL_3(1.5,
					null),
	LEVEL_2(1.2,
					Level.LEVEL_3),
	LEVEL_1(1.0,
					Level.LEVEL_2);

	// Ratio specific to the current level
	private final double ratio;

	// Reference to the next level
	private final Level next;

	private Level(double ratio,
								Level next) {

		this.ratio = ratio;
		this.next = next;
	}

	/**
	 * Gets ratio corresponding to the current level
	 * 
	 * @return ratio value
	 */
	public double getRatio() {

		return this.ratio;
	}

	/**
	 * Gets next level following the current one
	 * 
	 * @return next level
	 */
	public Level getNextLevel() {

		return this.next;
	}
}