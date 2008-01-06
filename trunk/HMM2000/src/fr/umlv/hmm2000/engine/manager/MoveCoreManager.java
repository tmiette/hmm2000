package fr.umlv.hmm2000.engine.manager;

import java.util.ArrayList;
import java.util.Iterator;

import fr.umlv.hmm2000.astar.AStar;
import fr.umlv.hmm2000.astar.AStarResult;
import fr.umlv.hmm2000.astar.heuristic.CheckerboardEuclideanAStarHeuristic;
import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.guiinterface.Sprite;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.MovableElement;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.map.graph.CheckerboardVertex;
import fr.umlv.hmm2000.unit.FightableContainer;
import fr.umlv.hmm2000.util.Pair;

/**
 * This class is the manager of movement of movable units.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class MoveCoreManager {

  private final CheckerboardEuclideanAStarHeuristic heuristic;

  private MovableElement currentSource;

  private final ArrayList<Step> currentSteps;

  private final ArrayList<Pair<Location, Sprite>> sprites;

  /**
   * Default constructor.
   */
  public MoveCoreManager() {
    this.heuristic = new CheckerboardEuclideanAStarHeuristic();
    this.currentSteps = new ArrayList<Step>();
    this.sprites = new ArrayList<Pair<Location, Sprite>>();
  }

  /**
   * Perform action depending of the location.
   * 
   * @param location
   *            the location.
   */
  public void perform(Location location) {

    if (CoreEngine.selectionManager().getSelectedLocation() != null
        && !CoreEngine.selectionManager().getSelectedLocation()
            .equals(location)) {
      if (this.currentSteps.size() != 0) {
        if (this.currentSteps.get(this.currentSteps.size() - 1).getEnd()
            .equals(location)) {
          this.performCurrentMoveEvent();
          this.clearCurrentMoveEvent();
        } else {
          this.createCurrentMoveEvent(location);
        }
      } else {
        this.createCurrentMoveEvent(location);
      }
    }
  }

  /**
   * Creates a movement.
   * 
   * @param location
   *            the recheable location.
   */
  private void createCurrentMoveEvent(Location location) {
    Location start = CoreEngine.selectionManager().getSelectedLocation();
    if (start != null) {
      MapForegroundElement element = CoreEngine.selectionManager()
          .getSelectedElement();
      if (element instanceof MovableElement
          && CoreEngine.roundManager().isCurrentPlayer(
              ((MovableElement) element).getPlayer())) {
        this.currentSource = (MovableElement) element;

        AStarResult<CheckerboardVertex> result = AStar.aStarAlgorithm(
            CoreEngine.map().graph(), this.heuristic, CoreEngine.map().graph()
                .getCheckerboardVertex(start.getX(), start.getY()), CoreEngine
                .map().graph().getCheckerboardVertex(location.getX(),
                    location.getY()));

        if (result != null) {
          ArrayList<Pair<Location, Double>> balancedLocations = new ArrayList<Pair<Location, Double>>();
          for (CheckerboardVertex vertex : result.getPath()) {
            balancedLocations.add(new Pair<Location, Double>(new Location(
                vertex.getXCoordinate(), vertex.getYCoordinate()), vertex
                .getWeight()));
          }

          this.clearCurrentMoveEvent();
          this.initMoves(balancedLocations);
          this.displayCurrentSteps();
        }
      }
    }
  }

  /**
   * Perform the movements and move the unit on the map step by step.
   */
  private void performCurrentMoveEvent() {
    for (Step move : this.currentSteps) {
      if (move.isRecheable()) {
        Location l = move.getEnd();
        MapForegroundElement element = CoreEngine.map()
            .getMapForegroundElementAtLocation(l);
        if (element != null) {
          if (!element.encounter(new Encounter(l, this.currentSource, move
              .getStart()))) {
            CoreEngine.displayMapForegroundElement(CoreEngine
                .selectionManager().getSelectedElement());
            break;
          } else {
            CoreEngine.map().removeMapForegroundElement(l);
            CoreEngine.fireSpriteRemoved(l, element.getSprite());
          }
        }
        this.currentSource.setStepCount(move.getRemainingStepCount());
        CoreEngine.map().moveMapForegroundElement(move.getStart(),
            move.getEnd());
        CoreEngine.fireSpriteRemoved(move.getStart(), this.currentSource
            .getSprite());
        CoreEngine.fireSpriteRemoved(move.getEnd(), Sprite.RECHEABLE);
        CoreEngine.fireSpriteRemoved(move.getEnd(), Sprite.UNRECHEABLE);
        CoreEngine.fireSpriteAdded(move.getEnd(), this.currentSource
            .getSprite());
        CoreEngine.selectionManager().perform(move.getEnd());
        MoveCoreManager.slow();
      }
    }
  }

  /**
   * Erases current movements.
   */
  public void clearCurrentMoveEvent() {
    if (this.currentSteps.size() != 0) {
      this.eraseMoveSteps();
      this.currentSteps.clear();
    }
  }

  /**
   * Initialize the movements with balanced locations returned by a star.
   * 
   * @param balancedLocations
   *            the balanced locations.
   */
  private void initMoves(ArrayList<Pair<Location, Double>> balancedLocations) {

    this.currentSteps.clear();
    Iterator<Pair<Location, Double>> iterator = balancedLocations.iterator();
    Location start = null;
    Location end = null;

    double stepCount = this.currentSource.getStepCount();
    double weight;
    boolean recheable = true;

    while (iterator.hasNext()) {
      Pair<Location, Double> p = iterator.next();
      start = end;
      end = p.getFirstElement();
      weight = p.getSecondElement();
      if (stepCount < weight) {
        recheable = false;
      }
      if (start != null && end != null) {
        stepCount -= weight;
        this.currentSteps
            .add(new Step(start, end, weight, recheable, stepCount));
      }
    }
  }

  /**
   * Displays the sprites of the current movements.
   */
  private void displayCurrentSteps() {
    this.sprites.clear();
    for (Step move : this.currentSteps) {
      Location l = move.getEnd();
      if (move.isRecheable()) {
        this.sprites.add(new Pair<Location, Sprite>(l, Sprite.RECHEABLE));
      } else {
        this.sprites.add(new Pair<Location, Sprite>(l, Sprite.UNRECHEABLE));
      }
    }
    CoreEngine.fireSpritesAdded(this.sprites);
  }

  /**
   * Erases the sprites of the current movements.
   */
  private void eraseMoveSteps() {
    this.sprites.clear();
    for (Step move : this.currentSteps) {
      Location l = move.getEnd();
      this.sprites.add(new Pair<Location, Sprite>(l, Sprite.RECHEABLE));
      this.sprites.add(new Pair<Location, Sprite>(l, Sprite.UNRECHEABLE));
    }
    CoreEngine.fireSpritesRemoved(this.sprites);
  }

  /**
   * Slow the move between two steps.
   */
  private static void slow() {
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      throw new AssertionError();
    }
  }

  /**
   * 
   * This class represents a encounter event when a movable unit encounter
   * another foreground element.
   * 
   * @author MIETTE Tom
   * @author MOURET Sebastien
   * 
   */
  public static class Encounter {

    private final Location recipientLocation;

    private final FightableContainer sender;

    private final Location senderLocation;

    /**
     * Constructor of the encounter event.
     * 
     * @param recipientLocation
     *            the recipient location.
     * @param sender
     *            the movable element.
     * @param senderLocation
     *            the movable element location.
     */
    public Encounter(Location recipientLocation, FightableContainer sender,
        Location senderLocation) {
      this.recipientLocation = recipientLocation;
      this.sender = sender;
      this.senderLocation = senderLocation;

    }

    /**
     * Returns the movable element.
     * 
     * @return the movable element.
     */
    public FightableContainer getSender() {
      return this.sender;
    }

    /**
     * Returns the movable element location.
     * 
     * @return the movable element location.
     */
    public Location getSenderLocation() {
      return this.senderLocation;
    }

    /**
     * Returns the recipient location.
     * 
     * @return the recipient location.
     */
    public Location getRecipientLocation() {
      return this.recipientLocation;
    }
  }

  /**
   * 
   * This class represents a step of a movement.
   * 
   * @author MIETTE Tom
   * @author MOURET Sebastien
   * 
   */
  private static class Step {

    private final Location start;

    private final Location end;

    private final double weight;

    private final boolean recheable;

    private final double remainingStepCount;

    /**
     * Constructor of a step.
     * 
     * @param start
     *            the start location.
     * @param end
     *            the end location.
     * @param weight
     *            the cost of the step.
     * @param recheable
     *            if the step is recheable.
     * @param remainingCost
     *            the remaining cost for the movement.
     */
    private Step(Location start, Location end, double weight,
        boolean recheable, double remainingCost) {
      this.start = start;
      this.end = end;
      this.weight = weight;
      this.recheable = recheable;
      this.remainingStepCount = remainingCost;
    }

    /**
     * Returns the start location.
     * 
     * @return the start location.
     */
    public Location getStart() {
      return this.start;
    }

    /**
     * Returns the end location.
     * 
     * @return the end location.
     */
    public Location getEnd() {
      return this.end;
    }

    /**
     * Returns the cost of the step.
     * 
     * @return the cost of the step.
     */
    public double getWeight() {
      return this.weight;
    }

    /**
     * Returns if the step is reacheable depending of the remaining cost.
     * 
     * @return if the step is reacheable depending of the remaining cost.
     */
    public boolean isRecheable() {
      return this.recheable;
    }

    /**
     * Returns the remaining cost for the movement.
     * 
     * @return the remaining cost for the movement.
     */
    public double getRemainingStepCount() {
      return this.remainingStepCount;
    }

  }

}
