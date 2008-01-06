package fr.umlv.hmm2000.engine;

import fr.umlv.hmm2000.resource.ResourcesCollection;
import fr.umlv.hmm2000.resource.Resource.Kind;
import fr.umlv.hmm2000.salesentity.Price;

public class Player {

	public static final Player AI = new Player(-1);

	private final int id;

	private final ResourcesCollection resources;

	public Player(int id) {

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

	@Override
	public boolean equals(Object o) {

		if (o == null) {
			return false;
		}
		if (!(o instanceof Player)) {
			return false;
		}
		Player p = (Player) o;
		return this.id == p.id;
	}

	@Override
	public String toString() {

		return "Player : " + this.id;
	}
}
