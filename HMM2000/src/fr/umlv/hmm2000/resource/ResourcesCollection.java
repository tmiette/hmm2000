package fr.umlv.hmm2000.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.umlv.hmm2000.resource.Resource.Kind;
import fr.umlv.hmm2000.util.Pair;

public class ResourcesCollection {

	private final HashMap<Resource.Kind, Integer> resources;

	public ResourcesCollection() {
		this.resources = new HashMap<Kind, Integer>();
		for (Kind kind : Kind.values()) {
			this.resources.put(kind, 0);
		}
	}

	public void addResource(Kind kind, int value) {
		int currentValue = this.resources.get(kind);
		currentValue += value;
		this.resources.put(kind, currentValue);
	}

	public boolean hasEnoughResource(Kind kind, int value) {
		int currentValue = this.resources.get(kind);
		if (currentValue >= value) {
			return true;
		} else {
			return false;
		}
	}

	public boolean removeResource(Kind kind, int value) {
		if (this.hasEnoughResource(kind, value)) {
			this.resources.put(kind, this.resources.get(kind) - value);
			return true;
		} else {
			return false;
		}
	}

	public boolean removeAllResources(List<Pair<Kind, Integer>> list) {
		boolean removable = true;
		for (Pair<Kind, Integer> pair : list) {
			if (!hasEnoughResource(pair.getFirstElement(), pair
					.getSecondElement())) {
				removable = false;
			}
		}

		if (removable) {
			for (Pair<Kind, Integer> pair : list) {
				this.resources.put(pair.getFirstElement(), this.resources
						.get(pair.getFirstElement())
						- pair.getSecondElement());
			}
		}
		return removable;
	}

	public List<Pair<Kind, Integer>> asList() {
		ArrayList<Pair<Kind, Integer>> l = new ArrayList<Pair<Kind, Integer>>();
		for (Kind kind : Kind.values()) {
			l.add(new Pair<Kind, Integer>(kind, this.resources.get(kind)));
		}
		return l;
	}

	public List<Pair<Kind, Integer>> notNullResourceList() {
		ArrayList<Pair<Kind, Integer>> l = new ArrayList<Pair<Kind, Integer>>();
		for (Kind kind : Kind.values()) {
			int value = this.resources.get(kind);
			if (value > 0) {
				l.add(new Pair<Kind, Integer>(kind, value));
			}
		}
		return l;
	}

	@Override
	public String toString() {
		return this.resources.toString();
	}

}
