package fr.umlv.hmm2000.war;

import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.warriors.Warrior;

public class BattlePositionManager {

	private final Warrior[][] units;

	public static final int LINE_NUMBER = 2;

	private int freePlaces;

	public BattlePositionManager(int nbSlots) {

		this.units = new Warrior[LINE_NUMBER][nbSlots];
		this.freePlaces = LINE_NUMBER * nbSlots;
	}

	public void placeUnit(Warrior w, Location location)
			throws LocationAlreadyOccupedException, ArrayIndexOutOfBoundsException,
			NoPlaceAvailableException {

		int lineNb = location.getX();
		int slot = location.getY();
		
		if (isPlaced(w)) {
			moveUnit(	w,
								location);
		}
		
		if (this.freePlaces == 0) {
			throw new NoPlaceAvailableException("There is no place free left");
		}
		if (!isFreeLocation(location)) {
			throw new LocationAlreadyOccupedException("This location is not free");
		}
		if (lineNb <= LINE_NUMBER || slot <= this.units[0].length) {
			throw new ArrayIndexOutOfBoundsException("This location doesn't exist");
		}
		this.units[lineNb][slot] = w;
		this.freePlaces--;
	}

	public void unplaceUnit(Warrior w) {

		for (int i = 0; i < this.units.length; i++) {
			for (int j = 0; j < this.units[i].length; j++) {
				Warrior w2 = this.units[i][j];
				if (w2 != null && w2.equals(w)) {
					this.units[i][j] = null;
				}
			}
		}
	}

	public void moveUnit(Warrior w, Location location) {

	}

	public boolean isPlaced(Warrior w) {

		for (int i = 0; i < this.units.length; i++) {
			for (int j = 0; j < this.units[i].length; j++) {
				Warrior w2 = this.units[i][j];
				if (w2 != null && w2.equals(w)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isFreeLocation(Location location) {

		return (this.units == null ? true : false);
	}
}
