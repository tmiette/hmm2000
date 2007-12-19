package fr.umlv.hmm2000.war;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.map.element.MapBackgroundElement;
import fr.umlv.hmm2000.map.element.MapBackgroundEnum;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.map.graph.CheckerboardGraph;
import fr.umlv.hmm2000.war.exception.LocationAlreadyOccupedException;
import fr.umlv.hmm2000.war.exception.NoPlaceAvailableException;
import fr.umlv.hmm2000.warrior.Fightable;

public class BattlePositionMap implements Map {

	private final HashMap<Location, Fightable> units;

	public static final int LINE_NUMBER = 2;
	
	private static final int FIRST_LINE = 0;
	
	private static final int SECOND_LINE = 1;

	private final int width;

	private final ArrayList<Location> freeLocations;
	
	private MapBackgroundElement[][] mbe;

	public BattlePositionMap(int slots) {

		this.width = slots;
		this.units = new HashMap<Location, Fightable>(LINE_NUMBER * slots);
		this.freeLocations = initFreeLocations();
		initMatrix();
	}

	public List<Fightable> getFightableOnFirstLine() {

		ArrayList<Fightable> list = new ArrayList<Fightable>(this.width);
		for (int line = 0; line < LINE_NUMBER; line++) {
			for (Entry<Location, Fightable> entries : this.getUnits()) {
				if (entries.getKey().getX() == line) {
					list.add(entries.getValue());
				}
			}
			if (list.size() != 0) {
				return list;
			}
		}
		return list;
	}
	
	public List<Fightable> getFightableOnSecondLine() {

		ArrayList<Fightable> list = new ArrayList<Fightable>(this.width);
		for (Entry<Location, Fightable> entries : this.getUnits()) {
			if (entries.getKey().getX() == SECOND_LINE) {
				list.add(entries.getValue());
			}
		}
		return list;
	}

	private void initMatrix() {

		this.mbe = new MapBackgroundEnum[LINE_NUMBER][this.width];
		for (int i = 0; i < this.mbe.length; i++) {
			for (int j = 0; j < this.mbe[0].length; j++) {
				this.mbe[i][j] = MapBackgroundEnum.PLAIN;
			}
		}
	}

	// list of free locations a the map
	private ArrayList<Location> initFreeLocations() {

		ArrayList<Location> l = new ArrayList<Location>(LINE_NUMBER * width);
		for (int i = 0; i < LINE_NUMBER; i++) {
			for (int j = 0; j < this.width; j++) {
				l.add(new Location(i, j));
			}
		}
		return l;
	}

	// returns the first free location founded
	public Location getFirstFreeLocation() throws NoPlaceAvailableException {

		if (this.freeLocations.size() == 0) {
			throw new NoPlaceAvailableException("There is no place free left");
		}
		return this.freeLocations.get(0);
	}

	public void placeFightable(Fightable w, Location location)
			throws LocationAlreadyOccupedException, ArrayIndexOutOfBoundsException,
			NoPlaceAvailableException {

		if (!isValid(location)) {
			throw new ArrayIndexOutOfBoundsException("This location doesn't exist");
		}

		Location oldLocation;
		// warrior is already contained
		if ((oldLocation = getLocation(w)) != null) {
			moveMapForegroundElement(oldLocation, location);
		}
		else {
			if (this.freeLocations.size() == 0) {
				throw new NoPlaceAvailableException("There is no place free left");
			}
			if (this.units.containsKey(location)) {
				throw new LocationAlreadyOccupedException("This location is not free");
			}
			this.units.put(location, w);
			this.freeLocations.remove(location);
		}

	}

	private boolean isValid(Location location) {

		final int x = location.getX();
		final int y = location.getY();
		return (x < LINE_NUMBER && x >= 0 && y < this.width && y >= 0);
	}

	public Fightable getFightableAtLocation(Location l) {

		return this.units.get(l);
	}

	@Override
	public void moveMapForegroundElement(Location from, Location to) {

		// TODO
		// if (!isValid(from) || !isValid(to)) {
		// throw new IllegalMoveException("This is not a valid movement");
		// }
		if (this.units.containsKey(to)) {
			Fightable w = this.units.get(to);
			this.units.put(to, this.units.get(from));
			this.units.put(from, w);
		}
		else {
			this.units.put(to, this.units.get(from));
			this.units.remove(from);
			this.freeLocations.remove(to);
			this.freeLocations.add(from);
		}
	}

	public Location getLocation(Fightable w) {

		for (Entry<Location, Fightable> it : getUnits()) {
			if (w.equals(it.getValue())) {
				return it.getKey();
			}
		}
		return null;
	}

	public Set<Entry<Location, Fightable>> getUnits() {

		return this.units.entrySet();
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

		return this.getFightableAtLocation(l);
	}

	@Override
	public int getWidth() {

		return this.width;
	}

	public boolean isInFirstLine(Fightable fightable) {

		List<Fightable> list = getFightableOnFirstLine();
		return list.contains(fightable);
	}
	
	public boolean isInSecondLine(Fightable fightable) {

		List<Fightable> list = getFightableOnSecondLine();
		return list.contains(fightable);
	}

	@Override
	public void changeMapBackgroundElement(Location l,
			MapBackgroundElement element) {

		this.mbe[l.getX()][l.getY()] = element;

	}

	@Override
	public List<MapForegroundElement> getMapForegroundElements() {

		ArrayList<MapForegroundElement> list = new ArrayList<MapForegroundElement>();
		list.addAll(this.units.values());
		return list;
	}

	@Override
	public CheckerboardGraph graph() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeMapForegroundElement(Location l) {

		System.out.println("Suprrsion de la battleposition de " + this.units.get(l));
		this.units.remove(l);
		this.freeLocations.remove(l);
	}

	@Override
	public Location getLocationForMapForegroundElement(
			MapForegroundElement element) {

		return (element instanceof Fightable
				? this.getLocation((Fightable) element)
				: null);
	}
}
