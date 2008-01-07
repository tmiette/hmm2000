package fr.umlv.hmm2000.resource;

import fr.umlv.hmm2000.engine.guiinterface.Sprite;
import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.engine.manager.MoveCoreManager.Encounter;
import fr.umlv.hmm2000.map.builder.MapForegroundElementSaver;
import fr.umlv.hmm2000.map.element.MapForegroundElement;

/**
 * This class represents a resource on the map.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class Resource implements MapForegroundElement {

  public static final int RELOADABLE = 0;
  public static final int NON_RELOADABLE = 1;

  private final Kind kind;
  private int currentValue;
  private final int maxValue;
  private final int period;
  private final int addition;
  private int periodCounter;
  private final int behaviour;

  /**
   * Constructor of a resource with the specified parameters.
   * 
   * @param kind
   *            the kind of the resource.
   * @param startValue
   *            the start value of the resource.
   * @param maxValue
   *            the maximum value of the resource.
   * @param period
   *            the period when the resource reload.
   * @param addition
   *            the value when the resource reload.
   * @param behaviour
   *            the behaviour of the resource.
   */
  public Resource(Kind kind, int startValue, int maxValue, int period,
      int addition, int behaviour) {
    this.kind = kind;
    this.currentValue = startValue;
    this.maxValue = maxValue > startValue ? maxValue : startValue;
    this.period = period > 0 ? period : 1;
    this.addition = addition > 0 ? addition : maxValue;
    this.periodCounter = 0;
    this.behaviour = behaviour;
  }

  /**
   * Reload the resource depending on the period.
   */
  public void fill() {
    // Increments the current period value
    this.periodCounter++;
    // If the period is reached
    if (this.periodCounter == this.period) {
      if (this.currentValue != this.maxValue) {
        // Add the addition value to the current value
        this.currentValue += this.addition % this.maxValue;
      }
      this.periodCounter = 0;
    }
  }

  /**
   * Drain the resource.
   */
  public void drain() {
    this.currentValue = 0;
  }

  /**
   * Returns the kind of the resource.
   * 
   * @return the kind of the resource.
   */
  public Kind getKind() {
    return this.kind;
  }

  /**
   * Returns the current value of the resource.
   * 
   * @return the current value of the resource.
   */
  public int getCurrentValue() {
    return this.currentValue;
  }

  /**
   * Returns the maximum value of the resource.
   * 
   * @return the maximum value of the resource.
   */
  public int getMaxValue() {
    return this.maxValue;
  }

  /**
   * Returns the period of the resource.
   * 
   * @return the period of the resource.
   */
  public int getPeriod() {
    return this.period;
  }

  /**
   * Returns the addition value of the resource.
   * 
   * @return the addition value of the resource.
   */
  public int getAddition() {
    return this.addition;
  }

  /**
   * Returns the current period value of the resource.
   * 
   * @return the current period value of the resource.
   */
  public int getPeriodCounter() {
    return this.periodCounter;
  }

  /**
   * Returns the behaviour of the resource.
   * 
   * @return the behaviour of the resource.
   */
  public int getBehaviour() {
    return this.behaviour;
  }

  @Override
  public Sprite getSprite() {
    return this.kind.getSprite();
  }

  @Override
  public void accept(UIDisplayingVisitor visitor) {
    visitor.visit(this);
  }

  /**
   * This enum represents all the kind of resources.
   * 
   * @author MIETTE Tom
   * @author MOURET Sebastien
   * 
   */
  public enum Kind {
    GOLD(Sprite.GOLD_RESOURCE);

    private final Sprite sprite;

    /**
     * Constructor of the enum.
     * 
     * @param sprite
     *            the sprite of the enum.
     */
    private Kind(Sprite sprite) {
      this.sprite = sprite;
    }

    /**
     * Returns the sprite of the enum.
     * 
     * @return the sprite of the enum.
     */
    private Sprite getSprite() {
      return this.sprite;
    }

  }

  @Override
  public void nextDay(int day) {
    // Fill the resource
    this.fill();
  }

  @Override
  public boolean encounter(Encounter encounter) {
    // Add this resource to the player
    encounter.getSender().getPlayer().addResource(this.kind, this.currentValue);
    this.drain();
    if (this.behaviour == Resource.NON_RELOADABLE) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public String getSaveString() {
    return MapForegroundElementSaver.save(this);
  }

}
