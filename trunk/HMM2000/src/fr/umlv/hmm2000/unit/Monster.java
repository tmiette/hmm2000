package fr.umlv.hmm2000.unit;

import java.util.ArrayList;
import java.util.List;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.guiinterface.Sprite;
import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.engine.manager.MoveCoreManager.Encounter;
import fr.umlv.hmm2000.map.battle.BattlePositionMap;
import fr.umlv.hmm2000.map.battle.LocationAlreadyOccupedException;
import fr.umlv.hmm2000.map.battle.NoPlaceAvailableException;
import fr.umlv.hmm2000.warrior.exception.MaxNumberOfTroopsReachedException;

public class Monster implements FightableContainer {

  private final Sprite sprite;

  private final ArrayList<Fightable> troop;

  private final BattlePositionMap battlePosition;

  private Player player;

  public Monster(Player player, Sprite sprite) {

    this.player = player;
    this.sprite = sprite;
    this.battlePosition = new BattlePositionMap(
        FightableContainer.MAX_TROOP_SIZE / BattlePositionMap.LINE_NUMBER);
    this.troop = new ArrayList<Fightable>();
  }

  @Override
  public boolean addFightable(Fightable f)
      throws MaxNumberOfTroopsReachedException {

    if (this.troop.size() == FightableContainer.MAX_TROOP_SIZE) {
      throw new MaxNumberOfTroopsReachedException(
          "The max number of troops, a heroe can contain, is reached");
    }

    try {
      this.battlePosition.placeFightable(f, this.battlePosition
          .getFirstFreeLocation());
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

    return this.battlePosition;
  }

  @Override
  public List<Fightable> getTroop() {

    return this.troop;
  }

  @Override
  public void removeFightable(Fightable f) {

    int index;
    if ((index = this.troop.indexOf(f)) != -1) {
      this.troop.remove(index);
    }
  }

  @Override
  public void accept(UIDisplayingVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public boolean encounter(Encounter encounter) {
    CoreEngine.startBattle(encounter.getSender(),this);
    return false;
  }

  @Override
  public void nextDay(int day) {
    // do nothing
  }

  @Override
  public Sprite getSprite() {

    return this.sprite;
  }

  @Override
  public Player getPlayer() {

    return this.player;
  }

  @Override
  public void setPlayer(Player player) {

    this.player = player;

  }

	@Override
	public int getAttackPriority() {

		return FightableContainer.PRIORITY_VERY_LOW;
	}

}
