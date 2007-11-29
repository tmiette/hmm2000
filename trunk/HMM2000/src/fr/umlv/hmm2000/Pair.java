package fr.umlv.hmm2000;

public class Pair<E1, E2> {

	private final E1 firstElement;

	private final E2 secondElement;

	public Pair(E1 firstElement, E2 secondElement) {
		this.firstElement = firstElement;
		this.secondElement = secondElement;
	}

	public E1 getFirstElement() {
		return this.firstElement;
	}

	public E2 getSecondElement() {
		return this.secondElement;
	}

}
