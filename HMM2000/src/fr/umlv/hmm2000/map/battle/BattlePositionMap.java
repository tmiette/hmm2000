package fr.umlv.hmm2000.map.battle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.map.battle.exception.LocationAlreadyOccupedException;
import fr.umlv.hmm2000.map.battle.exception.NoPlaceAvailableException;
import fr.umlv.hmm2000.map.element.MapBackgroundElement;
import fr.umlv.hmm2000.map.element.MapBackgroundEnum;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.map.graph.CheckerboardGraph;
import fr.umlv.hmm2000.unit.Fightable;

/**
 * This class represents the disposition of fightable units before battle event.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class BattlePositionMap implements Map {

	// Locations corresponding to a fightable
	private final HashMap<Location, Fightable> units;

	// Line numbers for troop
	public static final int LINE_NUMBER = 2;

	// Second line number id
	private static final int SECOND_LINE = 1;

	// Map width
	private final int width;

	// Free locations list
	private final ArrayList<Location> freeLocations;

	// Matrix representing background elements on map
	private MapBackgroundElement[][] mbe;

	public BattlePositionMap(int width) {

		this.width = width;
		this.units = new HashMap<Location, Fightable>(LINE_NUMBER * width);
		this.freeLocations = initFreeLocations();
		initMatrix();
	}

	/**
	 * Gets fightable list which are on the first line.
	 * 
	 * @return first line units
	 */
	public List<Fightable> getFightableOnFirstLine() {

		ArrayList<Fightable> list = new ArrayList<Fightable>(this.width);
		for (int line = 0; line < LINE_NUMBER; line++) {
			for (Entry<Location, Fightable> entries : this.getUnits()) {
				if (entries.getKey().getX() == line) {
					// Adding to the list fightables on firt line
					list.add(entries.getValue());
				}
			}
			if (list.size() != 0) {
				return list;
			}
		}
		return list;
	}

	/**
	 * Gets fightable list which are on the second line.
	 * 
	 * @return second line units
	 */
	public List<Fightable> getFightableOnSecondLine() {

		ArrayList<Fightable> list = new ArrayList<Fightable>(this.width);
		for (Entry<Location, Fightable> entries : this.getUnits()) {
			if (entries.getKey().getX() == SECOND_LINE) {
				list.add(entries.getValue());
			}
		}
		return list;
	}

	/**
	 * Initializes the map background matrix with map background elements.
	 */
	private void initMatrix() {

		this.mbe = new MapBackgroundEnum[LINE_NUMBER][this.width];
		for (int i = 0; i < this.mbe.length; i++) {
			for (int j = 0; j < this.mbe[0].length; j++) {
				this.mbe[i][j] = MapBackgroundEnum.PLAIN;
			}
		}
	}

	/**
	 * Gets list of free locations on map.
	 * 
	 * @return list of free locations
	 */
	private ArrayList<Location> initFreeLocations() {

		ArrayList<Location> l = new ArrayList<Location>(LINE_NUMBER * width);
		for (int i = 0; i < LINE_NUMBER; i++) {
			for (int j = 0; j < this.width; j++) {
				l.add(new Location(i, j));
			}
		}
		return l;
	}

	/**
	 * Gets the first free location found on map.
	 * 
	 * @return free location
	 * @throws NoPlaceAvailableException
	 *           exception thrown if no free place left on map
	 */
	public Location getFirstFreeLocation() throws NoPlaceAvailableException {

		if (this.freeLocations.size() == 0) {
			throw new NoPlaceAvailableException("There is no place free left");
		}
		return this.freeLocations.get(0);
	}

	/**
	 * Place a fightable on map. If he is already contained in the map, he is
	 * swapped, otherwise he is added.
	 * 
	 * @param w
	 *          unit to add
	 * @param location
	 *          to place unit
	 * @throws LocationAlreadyOccupedException
	 *           exception thrown if location given is not free
	 * @throws ArrayIndexOutOfBoundsException
	 *           exception thrown if location is not valid
	 * @throws NoPlaceAvailableException
	 *           exception thrown if map is full
	 */
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

	/**
	 * Resolves if a location is a valid map location
	 * 
	 * @param location
	 *          to valid
	 * @return if location is valid
	 */
	private boolean isValid(Location location) {

		final int x = location.getX();
		final int y = location.getY();
		return (x < LINE_NUMBER && x >= 0 && y < this.width && y >= 0);
	}

	/**
	 * Returns the fightable at given location.
	 * 
	 * @param l
	 *          location to explore
	 * @return the fightable at given location
	 */
	public Fightable getFightableAtLocation(Location l) {

		return this.units.get(l);
	}

	@Override
	public void moveMapForegroundElement(Location from, Location to) {

		if (isValid(from) || isValid(to)) {

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
	}

	/**
	 * Return the location on map of a given fightable
	 * @param w warrior on map
	 * @return warrior location
	 */
	public Location getLocation(Fightable w) {

		for (Entry<Location, Fightable> it : getUnits()) {
			if (w.equals(it.getValue())) {
				return it.getKey();
			}
		}
		return null;
	}

	/**
	 * Return a set of units contained on map.
	 * @return map units
	 */
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

	/**
	 * Checks if a given fightable is on the fist map line.
	 * @param fightable on first line
	 * @return if the given fightable is on first line
	 */
	public boolean isInFirstLine(Fightable fightable) {

		List<Fightable> list = getFightableOnFirstLine();
		return list.contains(fightable);
	}

	/**
	 * Checks if a given figtable is on second map line.
	 * @param fightable on second line
	 * @return if the given fightable is on second line
	 */
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

		this.units.remove(l);
		this.freeLocations.add(l);
	}

	@Override
	public Location getLocationForMapForegroundElement(
			MapForegroundElement element) {

		return (element instanceof Fightable ? this
				.getLocation((Fightable) element) : null);
	}

	@Override
	public void addMapForegroundElement(MapForegroundElement element, Location l) {
	}
}
