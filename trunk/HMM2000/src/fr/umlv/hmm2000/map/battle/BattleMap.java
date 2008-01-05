package fr.umlv.hmm2000.map.battle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.map.element.MapBackgroundElement;
import fr.umlv.hmm2000.map.element.MapBackgroundEnum;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.map.graph.CheckerboardGraph;
import fr.umlv.hmm2000.unit.Fightable;
import fr.umlv.hmm2000.unit.FightableContainer;
import fr.umlv.hmm2000.unit.exception.MaxNumberOfTroopsReachedException;
import fr.umlv.hmm2000.util.Pair;

public class BattleMap implements Map {

  // Specify the team position on the battle map
  private enum Team {
    TOP,
    BOTTOM;
  }

  // Lines number between troops top and bottom
  public static final int LINE_BETWEEN_TROOP = 4;

  // Lines number owned by the hero
  public static final int LINE_FOR_HEROE = 1;

  // First line number on battle map (for troop)
  public static final int FIRST_LINE_TROOP = 0;

  // Last line number on battle map (for troop)
  public static final int LAST_LINE_TROOP = FIRST_LINE_TROOP
      + (BattlePositionMap.LINE_NUMBER + LINE_FOR_HEROE) * 2
      + LINE_BETWEEN_TROOP - 1;

  // This hashmap associate a hero, location pair to a team
  private HashMap<Team, Pair<Location, FightableContainer>> container;

  // Battle map height
  private int height;

  // Battle map width
  private int width;

  // Battle map background elements
  private final MapBackgroundElement[][] mbe;

  /**
   * Constructor which initializes the battle map thanks to two containers
   * (hero, castle, monster...)
   * 
   * @param c1
   *            first container
   * @param c2
   *            second container
   */
  public BattleMap(FightableContainer c1, FightableContainer c2) {

    this.height = c1.getBattlePositionManager().getHeight()
        + c2.getBattlePositionManager().getHeight() + LINE_BETWEEN_TROOP + 2
        * LINE_FOR_HEROE;
    this.width = Math.max(c1.getBattlePositionManager().getWidth(), c2
        .getBattlePositionManager().getWidth());

    this.container = new HashMap<Team, Pair<Location, FightableContainer>>();
    initContainer(c1, c2);
    this.mbe = new MapBackgroundEnum[this.height][this.width];
    initBackGroundElements();
  }

  /**
   * Initializes a matrix which represents the battle map background.
   */
  private void initBackGroundElements() {

    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        this.mbe[i][j] = MapBackgroundEnum.PLAIN;
      }
    }
  }

  private void initContainer(FightableContainer c1, FightableContainer c2) {

    FightableContainer min;
    FightableContainer max;
    if (c1.getBattlePositionManager().getWidth() >= c2
        .getBattlePositionManager().getWidth()) {
      min = c2;
      max = c1;
    } else {
      min = c1;
      max = c2;
    }
    System.out.println(LAST_LINE_TROOP);
    Location l1 = new Location(FIRST_LINE_TROOP, this.width / 2);
    Location l2 = new Location(LAST_LINE_TROOP, this.width / 2);
    this.container.put(Team.TOP,
        new Pair<Location, FightableContainer>(l1, min));
    this.container.put(Team.BOTTOM, new Pair<Location, FightableContainer>(l2,
        max));
  }

  private Team getTeam(Location l) {

    if (this.container.get(Team.TOP).getFirstElement().equals(l)
        || l.getX() >= FIRST_LINE_TROOP + LINE_FOR_HEROE
        && l.getX() <= (BattlePositionMap.LINE_NUMBER + LINE_FOR_HEROE + FIRST_LINE_TROOP)) {
      return Team.TOP;
    } else if (this.container.get(Team.BOTTOM).getFirstElement().equals(l)
        || l.getX() >= (FIRST_LINE_TROOP + BattlePositionMap.LINE_NUMBER
            + LINE_FOR_HEROE + LINE_BETWEEN_TROOP)
        && l.getX() <= LAST_LINE_TROOP - LINE_FOR_HEROE) {
      return Team.BOTTOM;
    } else {
      return null;
    }
  }

  // private Team getTeam(Location l) {
  //
  // if (l.getX() >= FIRST_LINE_TROOP
  // && l.getX() <= (BattlePositionMap.LINE_NUMBER + LINE_FOR_HEROE +
  // FIRST_LINE_TROOP)) {
  // return Team.TOP;
  // }
  // else if (l.getX() >= (FIRST_LINE_TROOP + BattlePositionMap.LINE_NUMBER
  // + LINE_FOR_HEROE + LINE_BETWEEN_TROOP)
  // && l.getX() <= LAST_LINE_TROOP) {
  // return Team.BOTTOM;
  // }
  // else {
  // return null;
  // }
  // }

  /**
   * Translates a BattleMapLocation location to a BattlePositionMap.
   * 
   * @param l
   *            battle position map location
   * @param team
   *            which own the location
   * @return the battle map location
   */
  private Location getBattlePositionLocation(Location l, Team team) {

    FightableContainer c = this.container.get(team).getSecondElement();
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
   *            battle position map location
   * @param team
   *            which own the location
   * @return the battle map location
   */
  private Location getBattleMapLocation(Location l, Team team) {

    FightableContainer c = this.container.get(team).getSecondElement();
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

  public FightableContainer getFightableContainerAtLocation(Location l) {

    Team team = getTeam(l);
    if (team != null && !this.container.get(team).getFirstElement().equals(l)) {
      return this.container.get(team).getSecondElement();
    }
    return null;
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
      return null;
    }
    // location is owned by the container (hero...)
    else if (this.container.get(team).getFirstElement().equals(l)) {
      return this.container.get(team).getSecondElement();
    } else {
      Location newLocation = getBattlePositionLocation(l, team);
      FightableContainer c = this.container.get(team).getSecondElement();
      return c.getBattlePositionManager().getMapForegroundElementAtLocation(
          newLocation);
    }

  }

  @Override
  public List<MapForegroundElement> getMapForegroundElements() {

    ArrayList<MapForegroundElement> list = new ArrayList<MapForegroundElement>();
    FightableContainer c1 = this.container.get(Team.TOP).getSecondElement();
    FightableContainer c2 = this.container.get(Team.BOTTOM).getSecondElement();
    list.addAll(c1.getBattlePositionManager().getMapForegroundElements());
    list.addAll(c2.getBattlePositionManager().getMapForegroundElements());
    list.add(c1);
    list.add(c2);
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

    Team team = getTeam(from);
    if (team != null) {
      FightableContainer c = this.container.get(team).getSecondElement();
      Location newFrom = getBattlePositionLocation(from, team);
      Location newTo = getBattlePositionLocation(to, team);
      c.getBattlePositionManager().moveMapForegroundElement(newFrom, newTo);
    }

  }

  public boolean swapBetweenFightableContainers(Location from, Location to) {

    Team fromTeam = getTeam(from);
    Team toTeam = getTeam(to);
    if (fromTeam != null && toTeam != null) {
      if (fromTeam.equals(toTeam)) {
        FightableContainer c = this.container.get(fromTeam).getSecondElement();
        Location newFrom = getBattlePositionLocation(from, fromTeam);
        Location newTo = getBattlePositionLocation(to, fromTeam);
        c.getBattlePositionManager().moveMapForegroundElement(newFrom, newTo);
        return true;
      } else {
        FightableContainer fromContainer = this.container.get(fromTeam)
            .getSecondElement();
        if (fromContainer.getTroop().size() > 1) {
          FightableContainer toContainer = this.container.get(toTeam)
              .getSecondElement();
          Location newFrom = getBattlePositionLocation(from, fromTeam);
          Location newTo = getBattlePositionLocation(to, toTeam);
          Fightable fromFightable = fromContainer.getBattlePositionManager()
              .getFightableAtLocation(newFrom);
          Fightable toFightable = toContainer.getBattlePositionManager()
              .getFightableAtLocation(newTo);
          fromContainer.removeFightable(fromFightable);
          if (toFightable != null) {
            toContainer.removeFightable(toFightable);
          }
          try {
            if (toFightable != null) {
              fromContainer.addFightable(toFightable);
              fromContainer.getBattlePositionManager().placeFightable(
                  toFightable, newFrom);
            }
            toContainer.addFightable(fromFightable);
            toContainer.getBattlePositionManager().placeFightable(
                fromFightable, newTo);
          } catch (ArrayIndexOutOfBoundsException e) {
            new AssertionError(e);
          } catch (LocationAlreadyOccupedException e) {
            new AssertionError(e);
          } catch (NoPlaceAvailableException e) {
            new AssertionError(e);
          } catch (MaxNumberOfTroopsReachedException e) {
            new AssertionError(e);
          }
          return true;
        } else {
          CoreEngine.fireMessage("You have to keep at least one unit.",
              HMMUserInterface.WARNING_MESSAGE);
          return false;
        }
      }

    }
    return false;
  }

  @Override
  public void removeMapForegroundElement(Location l) {

    Team team = getTeam(l);
    if (team != null) {
      FightableContainer c = this.container.get(team).getSecondElement();
      Location newLocation = getBattlePositionLocation(l, team);
      c.removeFightable(c.getBattlePositionManager().getFightableAtLocation(
          newLocation));
      c.getBattlePositionManager().removeMapForegroundElement(newLocation);
    }
  }

  @Override
  public Location getLocationForMapForegroundElement(
      MapForegroundElement element) {

    FightableContainer c1 = this.container.get(Team.TOP).getSecondElement();
    FightableContainer c2 = this.container.get(Team.BOTTOM).getSecondElement();

    if (element instanceof FightableContainer) {
      FightableContainer c = (FightableContainer) element;
      return (c.equals(c1) ? this.container.get(Team.TOP).getFirstElement()
          : this.container.get(Team.BOTTOM).getFirstElement());
    } else {
      Fightable f = (Fightable) element;
      Location l1 = c1.getBattlePositionManager().getLocation(f);
      Location l2 = c2.getBattlePositionManager().getLocation(f);
      if (l1 != null) {
        return getBattleMapLocation(l1, Team.TOP);
      } else if (l2 != null) {
        return getBattleMapLocation(l2, Team.BOTTOM);
      } else {
        return null;
      }
    }
  }

  @Override
  public void addMapForegroundElement(MapForegroundElement element, Location l) {

    // TODO Auto-generated method stub

  }
}
