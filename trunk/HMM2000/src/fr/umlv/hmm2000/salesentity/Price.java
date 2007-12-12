package fr.umlv.hmm2000.salesentity;

import java.util.List;

import fr.umlv.hmm2000.resource.ResourcesCollection;
import fr.umlv.hmm2000.resource.Resource.Kind;
import fr.umlv.hmm2000.util.Pair;

public class Price {

	private final ResourcesCollection resources;

	public Price() {
		this.resources = new ResourcesCollection();
	}

	public Price addResource(Kind kind, int value) {
		this.resources.addResource(kind, value);
		return this;
	}

	public List<Pair<Kind, Integer>> getResourcesList() {
		return this.resources.notNullResourceList();
	}
	
	@Override
	public String toString() {
		return this.resources.toString();
	}

}
