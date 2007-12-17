package fr.umlv.hmm2000.war;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.map.element.MapBackgroundElement;
import fr.umlv.hmm2000.map.element.MapBackgroundEnum;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.map.graph.CheckerboardGraph;
import fr.umlv.hmm2000.util.Pair;
import fr.umlv.hmm2000.warrior.Container;

public class BattleMap implements Map {

	private enum Team {
		TOP,
		BOTTOM;
	}

	public static final int LINE_BETWEEN_TROOP = 4;

	public static final int LINE_FOR_HEROE = 1;

	public static final int FIRST_LINE_TROOP = 0;

	public static final int LAST_LINE_TROOP = FIRST_LINE_TROOP
			+ (BattlePositionMap.LINE_NUMBER + LINE_FOR_HEROE) * 2
			+ LINE_BETWEEN_TROOP - 1;

	private HashMap<Team, Pair<Location, Container>> container;

	private int height;

	private int width;

	private final MapBackgroundElement[][] mbe;

	public BattleMap(	Container c1,
										Container c2) {

		this.height = c1.getBattlePositionManager().getHeight()
				+ c2.getBattlePositionManager().getHeight() + LINE_BETWEEN_TROOP + 2
				* LINE_FOR_HEROE;
		this.width = Math.max(c1.getBattlePositionManager().getSlots(), c2
				.getBattlePositionManager().getSlots());

		this.container = new HashMap<Team, Pair<Location, Container>>();
		initContainer(c1, c2);
		this.mbe = new MapBackgroundEnum[this.height][this.width];
		initBackGroundElements();
	}

	/**
	 * Initializes a matrix which represents the map background.
	 */
	private void initBackGroundElements() {

		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				this.mbe[i][j] = MapBackgroundEnum.PLAIN;
			}
		}
	}

	private void initContainer(Container c1, Container c2) {

		Container min;
		Container max;
		if (c1.getBattlePositionManager().getSlots() >= c2
				.getBattlePositionManager().getSlots()) {
			min = c2;
			max = c1;
		}
		else {
			min = c1;
			max = c2;
		}
		System.out.println(LAST_LINE_TROOP);
		Location l1 = new Location(FIRST_LINE_TROOP, this.width / 2);
		Location l2 = new Location(LAST_LINE_TROOP, this.width / 2);
		this.container.put(Team.TOP, new Pair<Location, Container>(l1, min));
		this.container.put(Team.BOTTOM, new Pair<Location, Container>(l2, max));
	}

	private Team getTeam(Location l) {

		if (l.getX() >= FIRST_LINE_TROOP
				&& l.getX() <= (BattlePositionMap.LINE_NUMBER + LINE_FOR_HEROE + FIRST_LINE_TROOP)) {
			return Team.TOP;
		}
		else if (l.getX() >= (FIRST_LINE_TROOP + BattlePositionMap.LINE_NUMBER
				+ LINE_FOR_HEROE + LINE_BETWEEN_TROOP)
				&& l.getX() <= LAST_LINE_TROOP) {
			return Team.BOTTOM;
		}
		else {
			return null;
		}
	}

	/**
	 * Translates a BattleMapLocation location to a BattlePositionMap.
	 * 
	 * @param l
	 *          battle position map location
	 * @param team
	 *          which own the location
	 * @return the battle map location
	 */
	private Location getBattlePositionLocation(Location l, Team team) {

		Container c = this.container.get(team).getSecondElement();
		switch (team) {
			case TOP:
				int minWidth = c.getBattlePositionManager().getWidth();
				int maxHeight = c.getBattlePositionManager().getHeight();
				int newY = l.getY() - (this.width - minWidth) / 2;
				int newX = maxHeight - (l.getX() + 1) + LINE_FOR_HEROE;
				return new Location(newX, newY);
			case BOTTOM:
				int height = c.getBattlePositionManager().getHeight();
				int newXX = l.getX() - this.height + height + LINE_FOR_HEROE;
				int newYY = l.getY();
				return new Location(newXX, newYY);
			default:
				return null;
		}
	}

	/**
	 * Translates a BattlePositionMap location to a BattleMapLocation.
	 * 
	 * @param l
	 *          battle position map location
	 * @param team
	 *          which own the location
	 * @return the battle map location
	 */
	private Location getBattleMapLocation(Location l, Team team) {

		Container c = this.container.get(team).getSecondElement();
		switch (team) {
			case TOP:
				int minWidth = c.getBattlePositionManager().getWidth();
				int maxHeight = c.getBattlePositionManager().getHeight();
				int newY = l.getY() + (this.width - minWidth) / 2;
				int newX = maxHeight - (l.getX() + 1) + LINE_FOR_HEROE;
				return new Location(newX, newY);
			case BOTTOM:
				int height = c.getBattlePositionManager().getHeight();
				int newXX = l.getX() + this.height - height - LINE_FOR_HEROE;
				int newYY = l.getY();
				return new Location(newXX, newYY);
			default:
				return null;
		}
	}

	@Override
	public void changeMapBackgroundElement(Location l,
			MapBackgroundElement element) {

		this.mbe[l.getX()][l.getY()] = element;

	}

	@Override
	public int getHeight() {

		return this.height;
	}

	@Override
	public MapBackgroundElement getMapBackgroundElementAtLocation(Location l) {

		return this.mbe[l.getX()][l.getY()];
	}

	@Override
	public MapForegroundElement getMapForegroundElementAtLocation(Location l) {

		Team team = getTeam(l);
		if (team == null) {
			System.out.println("location = " + l + "retourne null");
			return null;
		}
		else if (this.container.get(team).getFirstElement().equals(l)) {
			return (MapForegroundElement) this.container.get(team).getSecondElement();
		}
		else {
			Location newLocation = getBattlePositionLocation(l, team);
			Container c = this.container.get(team).getSecondElement();
			return c.getBattlePositionManager().getMapForegroundElementAtLocation(
					newLocation);
		}

	}

	@Override
	public List<MapForegroundElement> getMapForegroundElements() {

		ArrayList<MapForegroundElement> list = new ArrayList<MapForegroundElement>();
		Container c1 = this.container.get(Team.TOP).getSecondElement();
		Container c2 = this.container.get(Team.BOTTOM).getSecondElement();
		list.addAll(c1.getBattlePositionManager().getMapForegroundElements());
		list.addAll(c2.getBattlePositionManager().getMapForegroundElements());
		list.add((MapForegroundElement) c1);
		list.add((MapForegroundElement) c2);
		return list;
	}

	@Override
	public int getWidth() {

		return this.width;
	}

	@Override
	public CheckerboardGraph graph() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void moveMapForegroundElement(Location from, Location to) {

		// TODO Auto-generated method stub

	}

	@Override
	public void removeMapForegroundElement(Location l) {

		// TODO Auto-generated method stub

	}

}
