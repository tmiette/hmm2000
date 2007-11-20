package fr.umlv.hmm2000.map.graph;

import fr.umlv.hmm2000.map.MapBackgroundElement;

/**
 * Represents a vertex containing in a checkerboard graph.
 * 
 * @author Tom MIETTE
 * @author Sébastien MOURET
 * @version 1.0
 */
public class CheckerboardVertex {

	/*
	 * The x coordinate of the vertex.
	 */
	private final int xCoordinate;

	/*
	 * The x coordinate of the vertex.
	 */
	private final int yCoordinate;

	private final MapBackgroundElement backgroundElement;

	public CheckerboardVertex(int x, int y,
			MapBackgroundElement backgroundlElement) {
		this.xCoordinate = x;
		this.yCoordinate = y;
		this.backgroundElement = backgroundlElement;
	}

	public MapBackgroundElement getMapBackgroundElement() {
		return this.backgroundElement;
	}

	/**
	 * Gets the entry cost of the vertex.
	 * 
	 * @return the entry cost.
	 */
	public double getWeight() {
		return this.backgroundElement.getWeight();
	}

	/**
	 * Gets the x coordinate.
	 * 
	 * @return the x coordinate.
	 */
	public int getXCoordinate() {
		return this.xCoordinate;
	}

	/**
	 * Gets the y coordinate.
	 * 
	 * @return the y coordinate.
	 */
	public int getYCoordinate() {
		return this.yCoordinate;
	}

	/**
	 * Indicates whether some other object is "equal to" this one.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (!(o instanceof CheckerboardVertex)) {
			return false;
		}

		CheckerboardVertex cv = (CheckerboardVertex) o;
		return ((this.xCoordinate == cv.xCoordinate) && (this.yCoordinate == cv.yCoordinate));// &&
		// (this.weight
		// ==
		// cv.weight));
	}

	/**
	 * Returns a hash code for this checkboard vertex.
	 * 
	 * @return a hash code for the checkboard vertex.
	 */
	@Override
	public int hashCode() {
		return (int) (this.xCoordinate + this.yCoordinate);// + this.weight);
	}

	/**
	 * Returns a string representation of the object.
	 * 
	 * @return a string representation of the object..
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("CheckerboardVertex[x=");
		sb.append(this.xCoordinate);
		sb.append(";y=");
		sb.append(this.yCoordinate);
		sb.append(";weight=");
		sb.append(this.getWeight());
		sb.append("]");
		return sb.toString();
	}

}
