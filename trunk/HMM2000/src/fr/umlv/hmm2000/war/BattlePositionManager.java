package fr.umlv.hmm2000.war;

import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.war.exception.LocationAlreadyOccupedException;
import fr.umlv.hmm2000.war.exception.NoPlaceAvailableException;
import fr.umlv.hmm2000.warrior.Warrior;

public class BattlePositionManager {

	private final HashMap<Location, Warrior> units;

	public static final int LINE_NUMBER = 2;

	private int freePlaces;

	private final int slots;

	public BattlePositionManager(int slots) {

		this.units = new HashMap<Location, Warrior>(LINE_NUMBER * slots);
		this.freePlaces = LINE_NUMBER * slots;
		this.slots = slots;
	}

	public void placeWarrior(Warrior w, Location location)
			throws LocationAlreadyOccupedException, ArrayIndexOutOfBoundsException,
			NoPlaceAvailableException {

		if (!isValid(location)) {
			throw new ArrayIndexOutOfBoundsException("This location doesn't exist");
		}

		Location oldLocation;
		// warrior is already contained
		if ((oldLocation = getLocation(w)) != null) {
			swap(	oldLocation,
						location);
		}
		else {
			if (this.freePlaces == 0) {
				throw new NoPlaceAvailableException("There is no place free left");
			}
			if (this.units.containsKey(location)) {
				throw new LocationAlreadyOccupedException("This location is not free");
			}
			this.units.put(	location,
											w);
		}

		this.freePlaces--;
	}

	private boolean isValid(Location location) {

		final int x = location.getX();
		final int y = location.getY();
		return (x < this.slots && x >= 0 && y < LINE_NUMBER && y > 0);
	}

	private void swap(Location from, Location to) {

		if (this.units.containsKey(to)) {
			Warrior w = this.units.get(to);
			this.units.put(	to,
											this.units.get(from));
			this.units.put(	from,
											w);
		}
		else {
			this.units.put(	to,
											this.units.get(from));
			this.units.remove(from);
		}
	}

	private Location getLocation(Warrior w) {

		for (Entry<Location, Warrior> it : getUnits()) {
			if (w.equals(it.getValue())) {
				return it.getKey();
			}
		}
		return null;
	}

	public Set<Entry<Location, Warrior>> getUnits() {

		return this.units.entrySet();
	}

	
	public int getSlots() {
	
		return this.slots;
	}
}
