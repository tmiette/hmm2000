package fr.umlv.hmm2000;

import java.util.HashMap;

import fr.umlv.hmm2000.Resource.Kind;

public class Player {

	private final int id;
	private final HashMap<Resource.Kind, Integer> resources;

	public Player(final int id) {
		this.id = id;
		this.resources = new HashMap<Kind, Integer>();
		for (Kind kind : Kind.values()) {
			this.resources.put(kind, 0);
		}
	}

	public int getId() {
		return this.id;
	}

	public void addResource(Kind kind, int value) {
		int currentValue = this.resources.get(kind);
		currentValue += value;
		this.resources.put(kind, currentValue);
	}

	public HashMap<Resource.Kind, Integer> getResources() {
		return this.resources;
	}

}
