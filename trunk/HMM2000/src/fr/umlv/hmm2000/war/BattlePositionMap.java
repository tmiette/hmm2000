package fr.umlv.hmm2000.war;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.map.element.MapBackgroundElement;
import fr.umlv.hmm2000.map.element.MapBackgroundEnum;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.war.exception.LocationAlreadyOccupedException;
import fr.umlv.hmm2000.war.exception.NoPlaceAvailableException;
import fr.umlv.hmm2000.warrior.Warrior;

public class BattlePositionMap implements Map {

	private final HashMap<Location, Warrior> units;

	public static final int LINE_NUMBER = 2;

	private int freePlaces;

	private final int slots;
	
	private final ArrayList<Location> freeLocations;
	
	private MapBackgroundElement[][] mbe;

	public BattlePositionMap(int slots) {

		this.slots = slots;
		this.units = new HashMap<Location, Warrior>(LINE_NUMBER * slots);
		this.freeLocations = initFreeLocations();
		this.freePlaces = LINE_NUMBER * slots;
		initMatrix();
	}
	
	public ArrayList<Warrior> getWarriorsOnLine(int line) {

		if (line < LINE_NUMBER && line >= 0) {
			
			ArrayList<Warrior> w = new ArrayList<Warrior>(this.slots);
			for (Entry<Location, Warrior> entries : this.getUnits()) {
				if (entries.getKey().getX() == line) {
					w.add(entries.getValue());
				}
			}
			return w;
		}
		return null;
	}
	
	private void initMatrix() {

		this.mbe = new MapBackgroundEnum[LINE_NUMBER][this.slots];
		for (int i = 0; i < this.mbe.length; i++) {
			for (int j = 0; j < this.mbe[0].length; j++) {
				this.mbe[i][j] = MapBackgroundEnum.PLAIN;
			}
		}
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
			move(	oldLocation,
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
		return (x < LINE_NUMBER && x >= 0 && y < this.slots && y >= 0);
	}
	
	public Warrior getWarriorAtLocation(Location l) {

		return this.units.get(l);
	}

	public void move(Location from, Location to) {

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

	@Override
	public int getHeight() {

		return LINE_NUMBER;
	}

	@Override
	public MapBackgroundElement getMapBackgroundElementAtLocation(Location l) {

		if (isValid(l)) {
			return this.mbe[l.getX()][l.getY()];
		}
		return null;
	}

	@Override
	public MapForegroundElement getMapForegroundElementAtLocation(Location l) {

		return this.getWarriorAtLocation(l);
	}

	@Override
	public int getWidth() {

		return this.slots;
	}
}
