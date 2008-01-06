package fr.umlv.hmm2000.map;

/**
 * This interface provides behaviour of an element which can move on map.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public interface Movable {

	/**
	 * Gets movements value an element can move.
	 * 
	 * @return movement value
	 */
	public double getStepCount();

	/**
	 * Sets the movements value an element can do to move.
	 * 
	 * @param stepCount
	 *          movements value permited
	 */
	public void setStepCount(double stepCount);
}
