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

/**
 * This class contains only static method and defines the game engine which
 * manage all events and units.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class CoreEngine {

  public static final int BATTLE_CONFIG = 4;
  public static final int BATTLE_POSITION_CONFIG = 2;
  private static boolean battleAlreadyStarted;
  private static BattleCoreManager battleManager;

  private static BattleMap battleMap;

  private static BattlePositionCoreManager battlePositionManager;

  private static BattlePositionMap battlePositionMap;

  private static Map currentMap;

  private static MapLevel currentMapLevel;

  private static FightableContainer firstContainer;

  private static Game game;

  private static LocationSelectionRequester locationRequester;

  private static FightableContainer managerContainer;

  private static MoveCoreManager moveManager;

  private static int numberOfPlayers;

  private static DayCoreManager roundManager;

  private static FightableContainer secondContainer;

  private static SelectionCoreManager selectionManager;

  public static final int SWAP_CONFIG = 8;

  private static SwapCoreManager swapManager;

  private static HMMUserInterface uiEngine;

  public static final int WORLD_CONFIG = 1;

  private static WorldMap worldMap;

  /**
   * Change the current configuration to default configuration.
   */
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

  /**
   * Returns the battle manager.
   * 
   * @return the battle manager
   */
  public static BattleCoreManager battleManager() {
    return CoreEngine.battleManager;
  }

  /**
   * Change the current configuration of the engine.
   * 
   * @param map
   *            the new map.
   */
  private static void changeCurrentMap(Map map) {
    CoreEngine.uiEngine.eraseMap();
    CoreEngine.currentMap = map;
    CoreEngine.uiEngine.drawMap(map);
    CoreEngine.selectionManager.perform(CoreEngine.currentMap
        .getLocationForMapForegroundElement(CoreEngine.currentMap
            .getMapForegroundElements().get(0)));
  }

  /**
   * Remove the selection requester.
   */
  public static void clearLocationSelection() {
    CoreEngine.locationRequester = null;
  }

  /**
   * Returns the current configuration of the engine.
   * 
   * @return the current configuration of the engine
   */
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

  /**
   * Display features of a a foreground element.
   * 
   * @param element
   *            the foreground element
   */
  public static void displayMapForegroundElement(MapForegroundElement element) {
    element.accept(CoreEngine.uiEngine.displayingVisitor());
    element.accept(CoreEngine.uiEngine.displayingVisitor());
  }

  /**
   * Method called when a battle ending.
   * 
   * @param winner
   *            the fightable container which won.
   * @param looser
   *            the fightable container which lost.
   */
  public static void endBattle(FightableContainer winner,
      FightableContainer looser) {
    CoreEngine.battleManager = null;
    CoreEngine.changeCurrentMap(CoreEngine.worldMap);
    Location l = CoreEngine.map().getLocationForMapForegroundElement(looser);
    if (l != null) {
      CoreEngine.map().removeMapForegroundElement(l);
      CoreEngine.uiEngine.eraseSprite(l, looser.getSprite());
    } else if (looser instanceof Castle || l == null) {
      CoreEngine.game.playerLost(looser.getPlayer());
    }
  }

  /**
   * Display a message.
   * 
   * @param message
   *            the message.
   * @param level
   *            the level of the message.
   */
  public static void fireMessage(String message, int level) {
    CoreEngine.uiEngine.displayMessage(message, level);
  }

  /**
   * Displays a sprite on the current map at the specified location.
   * 
   * @param location
   *            the location.
   * @param sprite
   *            the sprite.
   */
  public static void fireSpriteAdded(Location location, Sprite sprite) {
    CoreEngine.uiEngine.displaySprite(location, sprite);
  }

  /**
   * Erases a sprite on the current map at the specified location.
   * 
   * @param location
   *            the location.
   * @param sprite
   *            the sprite.
   */
  public static void fireSpriteRemoved(Location location, Sprite sprite) {
    CoreEngine.uiEngine.eraseSprite(location, sprite);
  }

  /**
   * Displays a list of sprites at the specified locations.
   * 
   * @param sprites
   *            the list of pairs associating the sprites to their locations.
   */
  public static void fireSpritesAdded(List<Pair<Location, Sprite>> sprites) {
    CoreEngine.uiEngine.displaySprites(sprites);
  }

  /**
   * Erases a list of sprites at the specified locations.
   * 
   * @param sprites
   *            the list of pairs associating the sprites to their locations.
   */
  public static void fireSpritesRemoved(List<Pair<Location, Sprite>> sprites) {
    CoreEngine.uiEngine.eraseSprites(sprites);
  }

  /**
   * Submit a click with a location to the engine.
   * 
   * @param x
   *            the x coordinate.
   * @param y
   *            the y coordinate.
   * @param button
   *            the mouse button clicked.
   */
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

  /**
   * Change the configuration of the engine to battle position.
   */
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

  /**
   * Change the configuration of the engine to battle position before the battle
   * configuration if the attacker or the defender can do it.
   * 
   * @param attacker
   *            the attacker fightable container.
   * @param defender
   *            the defender fightable container.
   * @return
   */
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

  /**
   * Change the configuration of the engine to castle manager.
   */
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

  /**
   * Returns the current map.
   * 
   * @return the current map.
   */
  public static Map map() {
    return CoreEngine.currentMap;
  }

  /**
   * Returns the map level.
   * 
   * @return the map level.
   */
  public static MapLevel mapLevel() {
    return CoreEngine.currentMapLevel;
  }

  /**
   * Go to the following day.
   */
  public static void nextDay() {
    if (CoreEngine.currentConfiguration() != CoreEngine.WORLD_CONFIG) {
      CoreEngine.backToWorldMap();
    }
    CoreEngine.roundManager.nextDay();
    CoreEngine.displayMapForegroundElement(CoreEngine.selectionManager
        .getSelectedElement());
  }

  /**
   * Returns the number of players.
   * 
   * @return the number of players.
   */
  public static int numberOfPlayers() {
    return CoreEngine.numberOfPlayers;
  }

  /**
   * Asks the user for a castle management item.
   * 
   * @param items
   *            list of castle management items.
   * @return the castle management item selected.
   */
  public static CastleItem requestCastleItem(List<CastleItem> items) {
    return CoreEngine.uiEngine.choicesManager().submit(
        "What do you want do to ?", items);
  }

  /**
   * Request for a list of selections of locations.
   * 
   * @param requester
   *            the requester.
   */
  public static void requestLocationSelection(
      LocationSelectionRequester requester) {
    CoreEngine.locationRequester = requester;
  }

  /**
   * Asks the user for a purchase.
   * 
   * @param items
   *            list of sellable items.
   * @return the selected item.
   */
  public static Sellable requestPurchase(List<Pair<Sellable, Integer>> items) {
    return CoreEngine.uiEngine.choicesManager().submit(
        "Which item do you want to buy ?", items);
  }

  /**
   * Asks the user for a skill.
   * 
   * @param skills
   *            the list of skills
   * @return the skill selected.
   */
  public static Skill requestSkill(List<Skill> skills) {
    return CoreEngine.uiEngine.choicesManager().submit(
        "Which skill do you want to use ?", skills);
  }

  /**
   * Returns the days manager.
   * 
   * @return the days manager.
   */
  public static DayCoreManager roundManager() {
    return CoreEngine.roundManager;
  }

  /**
   * Returns the selection manager.
   * 
   * @return the selection manager.
   */
  public static SelectionCoreManager selectionManager() {
    return CoreEngine.selectionManager;
  }

  /**
   * Change the configuration of the engine to battle.
   * 
   * @param attacker
   *            the attacker fightable container.
   * @param defender
   *            the defender fightable container.
   */
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

  /**
   * Start a new core engine.
   * 
   * @param level
   *            the map level.
   * @param uiEngine
   *            the user interface manager.
   * @param players
   *            list of players.
   * @throws InvalidPlayersNumberException
   *             if the number of players is invalid.
   * @throws FileNotFoundException
   *             if the map file does not exist.
   * @throws IOException
   *             if map file is corrupted.
   */
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

  /**
   * Change the configuration of the engine to swap between two containers of
   * the same player.
   * 
   * @param container1
   *            the first container.
   * @param container2
   *            the second container.
   */
  public static void startSwap(FightableContainer container1,
      FightableContainer container2) {
    CoreEngine.battleMap = new BattleMap(container1, container2);
    CoreEngine.changeCurrentMap(CoreEngine.battleMap);
  }

  /**
   * Default constructor.
   */
  private CoreEngine() {

  }
}
