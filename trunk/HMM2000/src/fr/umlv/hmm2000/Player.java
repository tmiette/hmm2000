package fr.umlv.hmm2000;

import fr.umlv.hmm2000.resource.ResourcesCollection;
import fr.umlv.hmm2000.resource.Resource.Kind;
import fr.umlv.hmm2000.salesentity.Price;

public class Player {

	private final int id;
	private final ResourcesCollection resources;

	public Player(final int id) {
		this.id = id;
		this.resources = new ResourcesCollection();
	}

	public int getId() {
		return this.id;
	}

	public void addResource(Kind kind, int value) {
		this.resources.addResource(kind, value);
	}

	public ResourcesCollection getResources() {
		return this.resources;
	}

	public boolean spend(Price price) {
		return this.resources.removeAllResources(price.getResourcesList());
	}

}
