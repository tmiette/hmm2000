package fr.umlv.hmm2000.engine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import fr.umlv.hmm2000.building.Castle;
import fr.umlv.hmm2000.building.CastleItem;
import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.engine.guiinterface.Sprite;
import fr.umlv.hmm2000.engine.guiinterface.UIChoicesManager;
import fr.umlv.hmm2000.engine.manager.BattleCoreManager;
import fr.umlv.hmm2000.engine.manager.BattlePositionCoreManager;
import fr.umlv.hmm2000.engine.manager.DayCoreManager;
import fr.umlv.hmm2000.engine.manager.MoveCoreManager;
import fr.umlv.hmm2000.engine.manager.SelectionCoreManager;
import fr.umlv.hmm2000.engine.manager.SwapCoreManager;
import fr.umlv.hmm2000.map.InvalidPlayersNumberException;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.map.MapLevel;
import fr.umlv.hmm2000.map.WorldMap;
import fr.umlv.hmm2000.map.battle.BattleMap;
import fr.umlv.hmm2000.map.battle.BattlePositionMap;
import fr.umlv.hmm2000.map.builder.MapBuilder;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.salesentity.Sellable;
import fr.umlv.hmm2000.unit.FightableContainer;
import fr.umlv.hmm2000.unit.skill.Skill;
import fr.umlv.hmm2000.util.Pair;

public class CoreEngine {

  public static final int WORLD_CONFIG = 1;
  public static final int BATTLE_POSITION_CONFIG = 2;
  public static final int BATTLE_CONFIG = 4;
  public static final int SWAP_CONFIG = 8;

  private static MapLevel currentMapLevel;

  private static int numberOfPlayers;

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

  private static SwapCoreManager swapManager;

  private static boolean battleAlreadyStarted;

  private static FightableContainer managerContainer;

  private static FightableContainer firstContainer;

  private static FightableContainer secondContainer;

  private static LocationSelectionRequester locationRequester;

  private static Game game;

  private CoreEngine() {

  }

  public static void startNewCoreEngine(MapLevel level,
      HMMUserInterface uiEngine, Player... players)
      throws InvalidPlayersNumberException, FileNotFoundException, IOException {

    if (players.length < level.getMinPlayerNumber()
        || players.length > level.getMaxPlayerNumber()) {
      throw new InvalidPlayersNumberException(level, players.length);
    }

    WorldMap worldMap = MapBuilder.createMap(level, players);

    CoreEngine.currentMapLevel = level;
    CoreEngine.numberOfPlayers = players.length;
    CoreEngine.worldMap = worldMap;
    CoreEngine.uiEngine = uiEngine;
    CoreEngine.selectionManager = new SelectionCoreManager();
    CoreEngine.moveManager = new MoveCoreManager();
    CoreEngine.roundManager = new DayCoreManager(players);
    CoreEngine.battlePositionManager = new BattlePositionCoreManager();
    CoreEngine.swapManager = new SwapCoreManager();

    CoreEngine.currentMap = worldMap;
    CoreEngine.uiEngine.drawMap(worldMap);
    CoreEngine.selectionManager.perform(CoreEngine.currentMap
        .getLocationForMapForegroundElement(CoreEngine.currentMap
            .getMapForegroundElements().get(0)));
    CoreEngine.game = new Game(players);
  }

  private static void changeCurrentMap(Map map) {
    CoreEngine.uiEngine.eraseMap();
    CoreEngine.currentMap = map;
    CoreEngine.uiEngine.drawMap(map);
    CoreEngine.selectionManager.perform(CoreEngine.currentMap
        .getLocationForMapForegroundElement(CoreEngine.currentMap
            .getMapForegroundElements().get(0)));
  }

  public static void backToWorldMap() {
    if (CoreEngine.currentMap != CoreEngine.worldMap
        && CoreEngine.currentConfiguration() != CoreEngine.BATTLE_CONFIG) {
      if (CoreEngine.battleAlreadyStarted) {
        CoreEngine.startBattle(CoreEngine.firstContainer,
            CoreEngine.secondContainer);
      } else {
        CoreEngine.changeCurrentMap(CoreEngine.worldMap);
      }
    }
  }

  public static int currentConfiguration() {
    if (CoreEngine.currentMap == CoreEngine.worldMap) {
      return CoreEngine.WORLD_CONFIG;
    } else if (CoreEngine.currentMap == CoreEngine.battlePositionMap) {
      return CoreEngine.BATTLE_POSITION_CONFIG;
    } else if (CoreEngine.currentMap == CoreEngine.battleMap
        && CoreEngine.battleManager != null) {
      return CoreEngine.BATTLE_CONFIG;
    } else if (CoreEngine.currentMap == CoreEngine.battleMap
        && CoreEngine.battleManager == null) {
      return CoreEngine.SWAP_CONFIG;
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
      if (CoreEngine.locationRequester != null) {
        CoreEngine.locationRequester.submitLocation(l);
      } else {
        if (button == 1) {
          CoreEngine.selectionManager.perform(l);
          CoreEngine.battleManager.select();
        } else if (button == 3) {
          CoreEngine.battleManager.perform(l);
        }
      }
      break;
    case CoreEngine.SWAP_CONFIG:
      if (button == 1) {
        CoreEngine.selectionManager.perform(l);
      } else if (button == 3) {
        CoreEngine.swapManager.perform(l);
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

  public static void manageCastle() {
    if (CoreEngine.currentConfiguration() == CoreEngine.WORLD_CONFIG) {
      MapForegroundElement element = CoreEngine.selectionManager
          .getSelectedElement();
      if (element != null && element instanceof Castle) {
        Castle castle = (Castle) element;
        if (CoreEngine.roundManager.isCurrentPlayer(castle.getPlayer())) {
          CastleItem item = CoreEngine.requestCastleItem(castle.getItems());
          if (item != null && item != CastleItem.defaultItem) {
            item.perform();
          }
        }
      }
    }
  }

  public static void nextDay() {
    if (CoreEngine.currentConfiguration() != CoreEngine.WORLD_CONFIG) {
      CoreEngine.backToWorldMap();
    }
    CoreEngine.roundManager.nextDay();
    CoreEngine.displayMapForegroundElement(CoreEngine.selectionManager
        .getSelectedElement());
  }

  public static void startBattle(FightableContainer attacker,
      FightableContainer defender) {
    CoreEngine.battleMap = new BattleMap(attacker, defender);
    CoreEngine.changeCurrentMap(CoreEngine.battleMap);

    if ((CoreEngine.battleAlreadyStarted = CoreEngine
        .manageBattlePositionBeforeBattle(attacker, defender))) {
      CoreEngine.battlePositionMap = CoreEngine.managerContainer
          .getBattlePositionManager();
      CoreEngine.changeCurrentMap(CoreEngine.battlePositionMap);
    } else {
      CoreEngine.battleAlreadyStarted = false;
      CoreEngine.battleManager = new BattleCoreManager(attacker, defender);
    }
  }

  private static boolean manageBattlePositionBeforeBattle(
      FightableContainer attacker, FightableContainer defender) {
    if (attacker.getAttackPriority() == FightableContainer.PRIORITY_MEDIUM
        || defender.getAttackPriority() == FightableContainer.PRIORITY_MEDIUM) {

      CoreEngine.firstContainer = attacker;
      CoreEngine.secondContainer = defender;
      if (attacker.getAttackPriority() == FightableContainer.PRIORITY_MEDIUM) {
        CoreEngine.managerContainer = CoreEngine.firstContainer;
      } else {
        CoreEngine.managerContainer = CoreEngine.secondContainer;
      }

      if (CoreEngine.battleAlreadyStarted) {
        return false;
      } else {
        if (CoreEngine.uiEngine.choicesManager().askQuestion(
            CoreEngine.managerContainer.getPlayer()
                + " : do you want do manage your units ?") == UIChoicesManager.YES_RESPONSE) {
          return true;
        } else {
          return false;
        }
      }
    } else {
      return false;
    }
  }

  public static void endBattle(FightableContainer winner,
      FightableContainer looser) {
    CoreEngine.battleManager = null;
    CoreEngine.changeCurrentMap(CoreEngine.worldMap);
    Location l = CoreEngine.map().getLocationForMapForegroundElement(looser);
    if (l != null) {
    	CoreEngine.map().removeMapForegroundElement(l);
      CoreEngine.uiEngine.eraseSprite(l, looser.getSprite());
		}
    if (looser instanceof Castle) {
      CoreEngine.game.playerLost(looser.getPlayer());
    }
  }

  public static void startSwap(FightableContainer container1,
      FightableContainer container2) {
    CoreEngine.battleMap = new BattleMap(container1, container2);
    CoreEngine.changeCurrentMap(CoreEngine.battleMap);
  }

  public static MapLevel mapLevel() {
    return CoreEngine.currentMapLevel;
  }

  public static int numberOfPlayers() {
    return CoreEngine.numberOfPlayers;
  }

  public static Map map() {
    return CoreEngine.currentMap;
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

  public static Sellable requestPurchase(List<Pair<Sellable, Integer>> items) {
    return CoreEngine.uiEngine.choicesManager().submit(
        "Which item do you want to buy ?", items);
  }

  public static Skill requestSkill(List<Skill> skills) {
    return CoreEngine.uiEngine.choicesManager().submit(
        "Which skill do you want to use ?", skills);
  }

  public static CastleItem requestCastleItem(List<CastleItem> items) {
    return CoreEngine.uiEngine.choicesManager().submit(
        "What do you want do to ?", items);
  }

  public static void displayMapForegroundElement(MapForegroundElement element) {
    element.accept(CoreEngine.uiEngine.displayingVisitor());
    element.accept(CoreEngine.uiEngine.displayingVisitor());
  }

  public static void fireMessage(String message, int level) {
    CoreEngine.uiEngine.displayMessage(message, level);
  }

  public static void fireSpriteAdded(Location location, Sprite sprite) {
    CoreEngine.uiEngine.displaySprite(location, sprite);
  }

  public static void fireSpritesAdded(List<Pair<Location, Sprite>> sprites) {
    CoreEngine.uiEngine.displaySprites(sprites);
  }

  public static void fireSpriteRemoved(Location location, Sprite sprite) {
    CoreEngine.uiEngine.eraseSprite(location, sprite);
  }

  public static void fireSpritesRemoved(List<Pair<Location, Sprite>> sprites) {
    CoreEngine.uiEngine.eraseSprites(sprites);
  }
}
