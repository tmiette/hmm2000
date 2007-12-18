package fr.umlv.hmm2000.warrior;
import java.util.ArrayList;
import java.util.List;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.engine.event.EncounterEvent;
import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.map.MovableElement;
import fr.umlv.hmm2000.war.BattlePositionMap;
import fr.umlv.hmm2000.war.exception.LocationAlreadyOccupedException;
import fr.umlv.hmm2000.war.exception.NoPlaceAvailableException;
import fr.umlv.hmm2000.warrior.exception.MaxNumberOfTroopsReachedException;
import fr.umlv.hmm2000.warrior.profil.ProfilHeroe;

public class Hero extends MovableElement {

  private final ArrayList<Fightable> troop;

  private final String name;

  private final BattlePositionMap bpm;
  
  private int speed;
  
  private Sprite sprite;

  public Hero(ProfilHeroe profil, Player player, String name) {
    super(player);
    this.name = name;
    this.troop = new ArrayList<Fightable>();
    this.bpm = new BattlePositionMap(FightableContainer.MAX_TROOP_SIZE
        / BattlePositionMap.LINE_NUMBER);
    this.sprite = profil.getSprite();
  }

  @Override
  public boolean addFightable(Fightable f)
      throws MaxNumberOfTroopsReachedException {
    if (this.troop.size() >= FightableContainer.MAX_TROOP_SIZE) {
      throw new MaxNumberOfTroopsReachedException(
          "The max number of troops, a heroe can contain, is reached");
    }

    int speed = f.getSpeed();
    if (this.speed > speed) {
      this.speed = speed;
    }

    try {
      this.bpm.placeWarrior(f, this.bpm.getFirstFreeLocation());
    } catch (ArrayIndexOutOfBoundsException e) {
      return false;
    } catch (LocationAlreadyOccupedException e) {
      return false;
    } catch (NoPlaceAvailableException e) {
      return false;
    }
    this.troop.add(f);
    f.setFightableContainer(this);
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
