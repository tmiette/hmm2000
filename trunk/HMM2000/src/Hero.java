import java.util.ArrayList;
import java.util.List;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.engine.event.EncounterEvent;
import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.war.BattlePositionMap;
import fr.umlv.hmm2000.war.exception.LocationAlreadyOccupedException;
import fr.umlv.hmm2000.war.exception.NoPlaceAvailableException;
import fr.umlv.hmm2000.warrior.exception.MaxNumberOfTroopsReachedException;

public class Hero extends MovableElement2 {

  private final ArrayList<Fightable> troop;

  private final String name;

  private final BattlePositionMap bpm;

  public Hero(Player player, String name) {
    super(player);
    this.name = name;
    this.troop = new ArrayList<Fightable>();
    this.bpm = new BattlePositionMap(FightableContainer.MAX_TROOP_SIZE
        / BattlePositionMap.LINE_NUMBER);
  }

  @Override
  public boolean addFightable(Fightable f)
      throws MaxNumberOfTroopsReachedException {
    if (this.troop.size() >= FightableContainer.MAX_TROOP_SIZE) {
      throw new MaxNumberOfTroopsReachedException(
          "The max number of troops, a heroe can contain, is reached");
    }

    int speed = w.getSpeed();
    if (speed < super.getSpeed()) {
      super.setSpeed(speed);
    }

    try {
      this.bpm.placeWarrior(w, this.bpm.getFirstFreeLocation());
    } catch (ArrayIndexOutOfBoundsException e) {
      return false;
    } catch (LocationAlreadyOccupedException e) {
      return false;
    } catch (NoPlaceAvailableException e) {
      return false;
    }
    this.troop.add(w);
    w.setContainer(this);
    return true;
  }

  @Override
  public BattlePositionMap getBattlePositionManager() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Fightable> getTroop() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void removeFightable(Fightable f) {
    // TODO Auto-generated method stub

  }

  @Override
  public double getStepCount() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void setStepCount(double stepCount) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void accept(UIDisplayingVisitor visitor) {
    visitor.visit(this);
    
  }

  @Override
  public boolean encounter(EncounterEvent event) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void nextDay(int day) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Sprite getSprite() {
    // TODO Auto-generated method stub
    return null;
  }

}
