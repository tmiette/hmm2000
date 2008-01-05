package fr.umlv.hmm2000.map.element;

import fr.umlv.hmm2000.engine.guiinterface.Spritable;

/**
 * This interface represents map background element. Map background elements are
 * elements that are visible on map like montain, water, plain...
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public interface MapBackgroundElement extends Spritable {

	/**
	 * Returns the map background element weight.
	 * 
	 * @return map background element weight
	 */
	public double getWeight();

}
