package fr.umlv.hmm2000.unit.skill;

/**
 * This interface defines skill. A skill is a hero specific capability which
 * permit to do something special during battle
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public interface Skill {

	/**
	 * Gets skill name
	 * @return skill name
	 */
	public String getName();

	/**
	 * Performs skill behaviour
	 */
	public void perform();

	/**
	 * Gets skill description
	 * @return skill description
	 */
	public String getToolTipText();

	public static final Skill defaultSkill = new Skill() {

		@Override
		public String getName() {

			return "Aucun skill.";
		}

		@Override
		public String getToolTipText() {

			return null;
		}

		@Override
		public void perform() {

			throw new UnsupportedOperationException();
		}
	};
}
