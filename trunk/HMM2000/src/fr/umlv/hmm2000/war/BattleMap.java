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

	private enum TEAM {
		TOP,
		BOTTOM
	};

	public static final int LINE_BETWEEN_TROOP = 4;

	public static final int LINE_FOR_HEROE = 1;

	private final int height;

	private final int width;

	private final HashMap<TEAM, BattlePositionMap> battlePosition;

	private final HashMap<TEAM, Rectangle> teamPosition;

	private MapBackgroundElement[][] mbe;

	private final HashMap<TEAM, Pair<Location, Container>> container;

	public BattleMap(	Container attacker,
										Container defender) {

		this.height = attacker.getBattlePositionManager().getHeight()
				+ defender.getBattlePositionManager().getHeight() + LINE_BETWEEN_TROOP
				+ 2 * LINE_FOR_HEROE;
		this.width = Math.max(attacker.getBattlePositionManager().getSlots(),
				defender.getBattlePositionManager().getSlots());
		this.battlePosition = new HashMap<TEAM, BattlePositionMap>(2);
		this.container = new HashMap<TEAM, Pair<Location, Container>>(2);
		if (attacker.getBattlePositionManager().getSlots() >= defender
				.getBattlePositionManager().getSlots()) {
			this.battlePosition.put(TEAM.TOP, defender.getBattlePositionManager());
			this.battlePosition.put(TEAM.BOTTOM, attacker.getBattlePositionManager());
			this.container.put(TEAM.TOP, new Pair<Location, Container>(new Location(
					0, this.width / 2), defender));
			this.container.put(TEAM.BOTTOM, new Pair<Location, Container>(
					new Location(this.height - 1, this.width / 2), attacker));
		}
		else {
			this.battlePosition.put(TEAM.TOP, attacker.getBattlePositionManager());
			this.battlePosition.put(TEAM.BOTTOM, defender.getBattlePositionManager());
			this.container.put(TEAM.TOP, new Pair<Location, Container>(new Location(
					0, this.width / 2), attacker));
			this.container.put(TEAM.BOTTOM, new Pair<Location, Container>(
					new Location(this.height - 1, this.width / 2), defender));
		}
		initBackGroundElements();

		this.teamPosition = new HashMap<TEAM, Rectangle>(2);
		Location p1TOP = new Location(0, 0);
		Location p2TOP = new Location(
				this.battlePosition.get(TEAM.TOP).getHeight(), this.battlePosition.get(
						TEAM.TOP).getWidth());
		Location p1BOTTOM = new Location(0, 0);
		Location p2BOTTOM = new Location(this.battlePosition.get(TEAM.BOTTOM)
				.getHeight(), this.battlePosition.get(TEAM.TOP).getWidth());
		this.teamPosition.put(TEAM.TOP, new Rectangle(p1TOP, p2TOP));
		this.teamPosition.put(TEAM.BOTTOM, new Rectangle(p1BOTTOM, p2BOTTOM));

	}

	private void initBackGroundElements() {

		this.mbe = new MapBackgroundEnum[this.height][this.width];
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				this.mbe[i][j] = MapBackgroundEnum.PLAIN;
			}
		}
	}

	private TEAM getTeamBelongingTo(Location l) {

		if (this.teamPosition.get(TEAM.TOP).isIN(l)) {
			return TEAM.TOP;
		}
		else {
			return TEAM.BOTTOM;
		}
	}

	// translate a battlePositionMap location to a battleMap location
	private Location getBattleMapLocation(Location l, TEAM team) {

		switch (team) {
			case TOP:
				int minWidth = this.battlePosition.get(TEAM.TOP).getWidth();
				int maxHeight = this.battlePosition.get(TEAM.TOP).getHeight();
				int newY = l.getY() + (this.width - minWidth) / 2;
				int newX = maxHeight - (l.getX() + 1) + LINE_FOR_HEROE;
				return new Location(newX, newY);
			case BOTTOM:
				int newXX = l.getX() + this.height
						- this.battlePosition.get(TEAM.BOTTOM).getHeight() - LINE_FOR_HEROE;
				int newYY = l.getY();
				return new Location(newXX, newYY);
			default:
				return null;
		}
	}

	private Location getBattlePositionLocation(Location l, TEAM team) {

		switch (team) {
			case TOP:
				int minWidth = this.battlePosition.get(TEAM.TOP).getWidth();
				int maxHeight = this.battlePosition.get(TEAM.TOP).getHeight();
				int newY = l.getY() - (this.width - minWidth) / 2;
				int newX = maxHeight - (l.getX() + 1) + LINE_FOR_HEROE;
				return new Location(newX, newY);
			case BOTTOM:
				int newXX = l.getX() - this.height
						+ this.battlePosition.get(TEAM.BOTTOM).getHeight() + LINE_FOR_HEROE;
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

		TEAM team = this.getTeamBelongingTo(l);
		Location battlePositionLocation = this.getBattlePositionLocation(l, this
				.getTeamBelongingTo(l));
		if (battlePositionLocation != null) {
			return this.battlePosition.get(team).getMapForegroundElementAtLocation(
					battlePositionLocation);
		}
		else {
			for (TEAM t : TEAM.values()) {
				if (this.container.get(t).getFirstElement().equals(l)) {
					return (MapForegroundElement) this.container.get(t)
							.getSecondElement();
				}
			}
			return null;
		}

	}

	@Override
	public List<MapForegroundElement> getMapForegroundElements() {

		ArrayList<MapForegroundElement> list = new ArrayList<MapForegroundElement>();
		list.addAll(this.battlePosition.get(TEAM.TOP).getMapForegroundElements());
		list
				.addAll(this.battlePosition.get(TEAM.BOTTOM).getMapForegroundElements());
		for (TEAM team : TEAM.values()) {
			list.add((MapForegroundElement) this.container.get(team)
					.getSecondElement());
		}
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

	private class Rectangle {

		private Location p1;

		private Location p2;

		public Rectangle(	Location p1,
											Location p2) {

			this.p1 = p1;
			this.p2 = p2;
		}

		public boolean isIN(Location l) {

			return l.getX() >= this.p1.getX() && l.getX() <= this.p2.getX()
					&& l.getY() >= this.p1.getY() && l.getY() <= this.p2.getY();
		}
	}

}
