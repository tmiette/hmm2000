package fr.umlv.hmm2000.resource;

import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.engine.manager.MoveCoreManager.Encounter;
import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.map.element.MapForegroundElement;

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

  public void fill() {
    this.periodCounter++;
    if (this.periodCounter == this.period) {
      if (this.currentValue != this.maxValue) {
        this.currentValue += this.addition % this.maxValue;
      }
      this.periodCounter = 0;
    }
  }

  public void drain() {
    this.currentValue = 0;
  }

  public Kind getKind() {
    return this.kind;
  }

  public int getCurrentValue() {
    return this.currentValue;
  }

  public int getMaxValue() {
    return this.maxValue;
  }

  public int getPeriod() {
    return this.period;
  }

  public int getAddition() {
    return this.addition;
  }

  public int getPeriodCounter() {
    return this.periodCounter;
  }

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

  public enum Kind {
    GOLD(Sprite.GOLD_RESOURCE);

    private final Sprite sprite;

    private Kind(Sprite sprite) {
      this.sprite = sprite;
    }

    private Sprite getSprite() {
      return this.sprite;
    }

  }

  @Override
  public void nextDay(int day) {
    this.fill();
  }

  @Override
  public boolean encounter(Encounter encounter) {
    encounter.getSender().getPlayer().addResource(this.kind, this.currentValue);
    this.drain();
    if (this.behaviour == Resource.NON_RELOADABLE) {
      return true;
    } else {
      return false;
    }
  }

}
