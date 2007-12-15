package fr.umlv.hmm2000.map.graph;

/**
 * Interface to represent generics graphs.
 * 
 * @author Tom MIETTE
 * @author Sébastien MOURET
 * @version 1.0
 */
public interface Graph<V> {

	/**
	 * Returns if an edge exists between start and end.
	 * 
	 * @param start
	 *            start of the edge.
	 * @param end
	 *            end of the edge.
	 * @return if an edge exists between start and end.
	 */
	public boolean isEdge(V start, V end);

	/**
	 * Returns the weigth of the edge connecting start and end, or 0 if this
	 * edge does not exists.
	 * 
	 * @param start
	 *            start of the edge.
	 * @param end
	 *            end of the edge.
	 * @return the weight of the edge connecting the two vertices, or 0 if the
	 *         edge does not exists.
	 */
	public double edgeValue(V start, V end);

	/**
	 * Returns the number of successors of the vertex, or -1 if the vertex does
	 * not exists.
	 * 
	 * @param vertex
	 *            the vertex.
	 * @return the number of successors of the vertex, or -1 if the vertex does
	 *         not exists.
	 */
	public int numberOfSuccessors(V vertex);

	/**
	 * Returns the successor in position n of the vertex, or null if there is no
	 * more successor.
	 * 
	 * @param vertex
	 *            the vertex.
	 * @param n
	 *            the position.
	 * @return the successor in position n of the vertex, or null if there is no
	 *         more successor.
	 */
	public V getSuccessor(V vertex, int n);

}
