package fr.umlv.hmm2000.engine;

import java.io.FileNotFoundException;
import java.io.IOException;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.engine.manager.BattleCoreManager;
import fr.umlv.hmm2000.engine.manager.BattlePositionCoreManager;
import fr.umlv.hmm2000.engine.manager.DayCoreManager;
import fr.umlv.hmm2000.engine.manager.MoveCoreManager;
import fr.umlv.hmm2000.engine.manager.SelectionCoreManager;
import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.map.InvalidPlayersNumberException;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.map.MapBuilder;
import fr.umlv.hmm2000.map.MapLevel;
import fr.umlv.hmm2000.map.WorldMap;
import fr.umlv.hmm2000.map.element.MapBackgroundElement;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.war.BattleMap;
import fr.umlv.hmm2000.war.BattlePositionMap;
import fr.umlv.hmm2000.warrior.FightableContainer;

public class CoreEngine {

  public static final int WORLD_CONFIG = 1;
  public static final int BATTLE_POSITION_CONFIG = 2;
  public static final int BATTLE_CONFIG = 4;

  private static Map currentMap;

  private static WorldMap worldMap;

  private static BattlePositionMap battlePositionMap;

  private static BattleMap battleMap;

  private static HMMUserInterface uiEngine;

  private static MoveCoreManager moveManager;

  private static SelectionCoreManager selectionManager;

  private static DayCoreManager roundManager;

  private static BattlePositionCoreManager battlePositionManager;

  private static BattleCoreManager battleManager;

  private static LocationSelectionRequester locationRequester;

  private CoreEngine() {

  }

  public static void startNewCoreEngine(MapLevel level,
      HMMUserInterface uiEngine, Player... players)
      throws InvalidPlayersNumberException, FileNotFoundException, IOException {

    if (players.length < level.getMinPlayerNumber()
        || players.length > level.getMaxPlayerNumber()) {
      throw new InvalidPlayersNumberException(level, players.length);
    }

    // TODO builder
    WorldMap worldMap = MapBuilder.createMapTESTVERSION(level, players[0],
        players[1]);

    CoreEngine.worldMap = worldMap;
    CoreEngine.uiEngine = uiEngine;
    CoreEngine.selectionManager = new SelectionCoreManager();
    CoreEngine.moveManager = new MoveCoreManager();
    CoreEngine.roundManager = new DayCoreManager(players);
    CoreEngine.battlePositionManager = new BattlePositionCoreManager();

    CoreEngine.currentMap = worldMap;
    CoreEngine.uiEngine.drawMap(worldMap);
  }

  private static void changeCurrentMap(Map map) {
    CoreEngine.uiEngine.eraseMap();
    CoreEngine.currentMap = map;
    CoreEngine.uiEngine.drawMap(map);
  }

  public static void backToWorldMap() {
    if (CoreEngine.currentMap != CoreEngine.worldMap
        && CoreEngine.currentConfiguration() != CoreEngine.BATTLE_CONFIG) {
      CoreEngine.changeCurrentMap(CoreEngine.worldMap);
    }
  }

  public static int currentConfiguration() {
    if (CoreEngine.currentMap == CoreEngine.worldMap) {
      return CoreEngine.WORLD_CONFIG;
    } else if (CoreEngine.currentMap == CoreEngine.battlePositionMap) {
      return CoreEngine.BATTLE_POSITION_CONFIG;
    } else if (CoreEngine.currentMap == CoreEngine.battleMap) {
      return BATTLE_CONFIG;
    } else {
      return -1;
    }
  }

  public static void locationClicked(int x, int y, int button) {
    Location l = new Location(x, y);
    switch (CoreEngine.currentConfiguration()) {
    case CoreEngine.WORLD_CONFIG:
      if (CoreEngine.locationRequester != null) {
        CoreEngine.locationRequester.submitLocation(l);
      } else {
        if (button == 1) {
          CoreEngine.moveManager.clearCurrentMoveEvent();
          CoreEngine.selectionManager.perform(l);
        } else if (button == 3) {
          CoreEngine.moveManager.perform(l);
        }
      }
      break;
    case CoreEngine.BATTLE_POSITION_CONFIG:
      if (button == 1) {
        CoreEngine.selectionManager.perform(l);
      } else if (button == 3) {
        CoreEngine.battlePositionManager.perform(l);
      }
      break;
    case CoreEngine.BATTLE_CONFIG:
      if (button == 1) {
        CoreEngine.selectionManager.perform(l);
        CoreEngine.battleManager.select();
      } else if (button == 3) {
        CoreEngine.battleManager.perform(l);
      }
      break;
    default:
      break;
    }
  }

  public static void manageBattlePosition() {
    if (CoreEngine.currentConfiguration() == CoreEngine.WORLD_CONFIG) {
      MapForegroundElement element = CoreEngine.selectionManager
          .getSelectedElement();

      if (element != null && element instanceof FightableContainer) {
        FightableContainer container = (FightableContainer) element;
        if (CoreEngine.roundManager.isCurrentPlayer(container.getPlayer())) {
          CoreEngine.battlePositionMap = container.getBattlePositionManager();
          CoreEngine.changeCurrentMap(CoreEngine.battlePositionMap);
        }
      }
    }
  }

  public static void nextDay() {
    if (CoreEngine.currentConfiguration() == CoreEngine.WORLD_CONFIG) {
      CoreEngine.roundManager.nextDay();
    }
  }

  public static void startBattle(FightableContainer attacker,
      FightableContainer defender) {
    CoreEngine.battleMap = new BattleMap(attacker, defender);
    CoreEngine.changeCurrentMap(CoreEngine.battleMap);
    CoreEngine.battleManager = new BattleCoreManager(attacker, defender);
  }

  public static void endBattle(FightableContainer winner,
      FightableContainer looser) {
    CoreEngine.changeCurrentMap(CoreEngine.worldMap);
    Location l = CoreEngine.map().getLocationForMapForegroundElement(looser);
    CoreEngine.map().removeMapForegroundElement(l);
    CoreEngine.uiEngine.eraseSprite(l, looser.getSprite());
  }

  public static Map map() {
    return CoreEngine.currentMap;
  }

  public static HMMUserInterface uiManager() {
    return CoreEngine.uiEngine;
  }

  public static SelectionCoreManager selectionManager() {
    return CoreEngine.selectionManager;
  }

  public static MoveCoreManager moveManager() {
    return CoreEngine.moveManager;
  }

  public static DayCoreManager roundManager() {
    return CoreEngine.roundManager;
  }

  public static BattleCoreManager battleManager() {
    return CoreEngine.battleManager;
  }

  public static void requestLocationSelection(
      LocationSelectionRequester requester) {
    CoreEngine.locationRequester = requester;
  }

  public static void clearLocationSelection() {
    CoreEngine.locationRequester = null;
  }
  
  public static void fireMessage(String message){
    CoreEngine.uiEngine.displayMessage(message);
  }

  public static void fireSpriteAdded(Location location, Sprite sprite) {
    CoreEngine.uiEngine.displaySprite(location, sprite);
  }

  public static void fireSpriteRemoved(Location location, Sprite sprite) {
    CoreEngine.uiEngine.eraseSprite(location, sprite);
  }

  public static void fireElementAdded(Location location,
      MapForegroundElement element) {
    CoreEngine.uiEngine.elementAdded(location, element);
  }

  public static void fireElementRemoved(Location location,
      MapForegroundElement element) {
    CoreEngine.uiEngine.elementRemoved(location, element);
  }

  public static void fireBackgroundElementAdded(Location location,
      MapBackgroundElement element) {
    CoreEngine.uiEngine.elementAdded(location, element);
  }

  public static void fireBackgroundElementRemoved(Location location,
      MapBackgroundElement element) {
    CoreEngine.uiEngine.elementRemoved(location, element);
  }

}
