package fr.umlv.hmm2000.war;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.war.exception.LocationAlreadyOccupedException;
import fr.umlv.hmm2000.war.exception.NoPlaceAvailableException;
import fr.umlv.hmm2000.warrior.Warrior;

public class BattlePosition {

	private final HashMap<Location, Warrior> units;

	public static final int LINE_NUMBER = 2;

	private int freePlaces;

	private final int slots;
	
	private final ArrayList<Location> freeLocations;

	public BattlePosition(int slots) {

		this.units = new HashMap<Location, Warrior>(LINE_NUMBER * slots);
		this.freeLocations = initFreeLocations();
		this.freePlaces = LINE_NUMBER * slots;
		this.slots = slots;
	}
	
	private ArrayList<Location> initFreeLocations() {

		ArrayList<Location> l = new ArrayList<Location>(LINE_NUMBER * slots);
		for (int i = 0; i < LINE_NUMBER; i++) {
			for (int j = 0; j < this.slots; j++) {
				l.add(new Location(i, j));
			}
		}
		return l;
	}
	
	public Location getFirstFreeLocation() throws NoPlaceAvailableException {

		if (this.freePlaces == 0) {
			throw new NoPlaceAvailableException("There is no place free left");
		}
		return this.freeLocations.get(0);
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
			this.freeLocations.remove(location);
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
			this.freeLocations.remove(to);
			this.freeLocations.add(from);
		}
	}

	public Location getLocation(Warrior w) {

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
