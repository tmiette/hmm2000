package fr.umlv.hmm2000.engine;

import java.util.ArrayList;
import java.util.List;

import fr.umlv.hmm2000.resource.ResourcesCollection;
import fr.umlv.hmm2000.resource.Resource.Kind;
import fr.umlv.hmm2000.salesentity.Price;
import fr.umlv.hmm2000.unit.Hero;

public class Player {

	public static final Player AI = new Player(-1);

	private final int id;

	private final ResourcesCollection resources;

	private final ArrayList<Hero> heroes;

	public Player(int id) {

		this.id = id;
		this.resources = new ResourcesCollection();
		this.heroes = new ArrayList<Hero>();
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

	public List<Hero> getHeroes() {

		return this.heroes;
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
