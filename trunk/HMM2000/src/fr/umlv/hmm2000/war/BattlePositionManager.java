package fr.umlv.hmm2000.war;

import java.util.ArrayList;
import java.util.HashMap;

import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.war.exception.LocationAlreadyOccupedException;
import fr.umlv.hmm2000.war.exception.NoPlaceAvailableException;
import fr.umlv.hmm2000.warrior.Warrior;

public class BattlePositionManager {

	private final Warrior[][] units;
	
	public static final int LINE_NUMBER = 2;

	private int freePlaces;
	
	private final int nbSlots;

	public BattlePositionManager(int nbSlots) {

		this.units = new Warrior[LINE_NUMBER][nbSlots];
		this.freePlaces = LINE_NUMBER * nbSlots;
		this.nbSlots = nbSlots;
	}

	public void placeUnit(Warrior w, Location location)
			throws LocationAlreadyOccupedException, ArrayIndexOutOfBoundsException,
			NoPlaceAvailableException {

		int lineNb = location.getX();
		int slot = location.getY();

		if (this.freePlaces == 0) {
			throw new NoPlaceAvailableException("There is no place free left");
		}
		if (!isFreeLocation(location)) {
			throw new LocationAlreadyOccupedException("This location is not free");
		}
		if (!isValidLocation(location)) {
			throw new ArrayIndexOutOfBoundsException("This location doesn't exist");
		}
		if (isPlaced(w)) {
			Location l = getLocation(w);
			unplaceUnit(w);
			swap(	l,
						location);
		}
		else {
			this.units[lineNb][slot] = w;
			this.freePlaces--;
		}
	}

	public void unplaceUnit(Warrior w) {

		for (int i = 0; i < this.units.length; i++) {
			for (int j = 0; j < this.units[i].length; j++) {
				Warrior w2 = this.units[i][j];
				if (w2 != null && w2.equals(w)) {
					this.units[i][j] = null;
					this.freePlaces++;
					return;
				}
			}
		}
	}

	public void unplaceUnit(Warrior w, Location location) {

		this.units[location.getX()][location.getY()] = null;
	}

	private void swap(Location l1, Location l2) {

		int lineNb1 = l1.getX();
		int slot1 = l1.getY();
		int lineNb2 = l2.getX();
		int slot2 = l2.getY();
		Warrior w = this.units[lineNb1][slot1];
		this.units[lineNb1][slot1] = this.units[lineNb2][slot2];
		this.units[lineNb2][slot2] = w;
	}

	public boolean isPlaced(Warrior w) {

		return (getLocation(w) == null ? false : true);
	}

	public Location getLocation(Warrior w) {

		for (int i = 0; i < this.units.length; i++) {
			for (int j = 0; j < this.units[i].length; j++) {
				Warrior w2 = this.units[i][j];
				if (w2 != null && w2.equals(w)) {
					return new Location(i,
															j);
				}
			}
		}
		return null;
	}

	public boolean isFreeLocation(Location location) {

		return (this.units == null ? true : false);
	}

	public Location getFreeLocation() {

		if (freePlacesLeft() > 0) {
			for (int i = 0; i < this.units.length; i++) {
				for (int j = 0; j < this.units[i].length; j++) {
					if (this.units[i][j] == null) {
						return new Location(i,
																j);
					}
				}
			}
		}
		return null;
	}

	public ArrayList<Location> getFreeLocationsList() {

		if (freePlacesLeft() > 0) {
			ArrayList<Location> list = new ArrayList<Location>();
			for (int i = 0; i < this.units.length; i++) {
				for (int j = 0; j < this.units[i].length; j++) {
					if (this.units[i][j] == null) {
						list.add(new Location(i,
																	j));
					}
				}
			}
		}
		return null;
	}

	private boolean isValidLocation(Location location) {

		int lineNb = location.getX();
		int slot = location.getY();

		return (lineNb <= LINE_NUMBER || slot <= this.units[0].length);
	}

	public int freePlacesLeft() {

		return this.freePlaces;
	}

	
	public int getNbSlots() {
	
		return this.nbSlots;
	}
}
