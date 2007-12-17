package fr.umlv.hmm2000.engine;

import java.io.FileNotFoundException;
import java.io.IOException;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.engine.manager.BattleCoreManager;
import fr.umlv.hmm2000.engine.manager.BattlePositionCoreManager;
import fr.umlv.hmm2000.engine.manager.DayCoreManager;
import fr.umlv.hmm2000.engine.manager.EncounterCoreManager;
import fr.umlv.hmm2000.engine.manager.MoveCoreManager;
import fr.umlv.hmm2000.engine.manager.SelectionCoreManager;
import fr.umlv.hmm2000.map.InvalidPlayersNumberException;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.map.MapBuilder;
import fr.umlv.hmm2000.map.MapLevel;
import fr.umlv.hmm2000.map.MovableElement;
import fr.umlv.hmm2000.map.WorldMap;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.war.BattleMap;
import fr.umlv.hmm2000.war.BattlePositionMap;
import fr.umlv.hmm2000.warrior.Container;
import fr.umlv.hmm2000.warrior.Heroe;

public class CoreEngine {

  private static Map currentMap;

  private static WorldMap worldMap;

  private static BattlePositionMap battlePositionMap;

  private static BattleMap battleMap;

  private static HMMUserInterface uiEngine;

  private static MoveCoreManager moveManager;

  private static SelectionCoreManager selectionManager;

  private static EncounterCoreManager encounterManager;

  private static DayCoreManager roundManager;

  private static BattlePositionCoreManager battlePositionManager;

  private static BattleCoreManager battleManager;

  private static LocationSelectionRequester locationRequester;

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
    CoreEngine.encounterManager = new EncounterCoreManager();
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
    if (CoreEngine.currentMap != CoreEngine.worldMap) {
      CoreEngine.changeCurrentMap(CoreEngine.worldMap);
    }
  }

  public static void locationClicked(int x, int y, int button) {
    Location l = new Location(x, y);

    if (CoreEngine.currentMap == CoreEngine.worldMap) {

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
    } else if (CoreEngine.currentMap == CoreEngine.battlePositionMap) {
      if (button == 1) {
        CoreEngine.selectionManager.perform(l);
      } else if (button == 3) {
        CoreEngine.battlePositionManager.perform(l);
      }
    } else if (CoreEngine.currentMap == CoreEngine.battleMap) {
      if (button == 1) {
        CoreEngine.selectionManager.perform(l);
      } else if (button == 3) {
        CoreEngine.battleManager.perform(l);
      }
    }
  }

  public static void manageBattlePosition() {
    MapForegroundElement element = CoreEngine.selectionManager
        .getSelectedElement();

    if (element != null && element instanceof MovableElement
        && roundManager.isCurrentPlayer(((MovableElement) element).getPlayer())
        && element instanceof Heroe) {
      CoreEngine.battlePositionMap = ((Heroe) element)
          .getBattlePositionManager();
      CoreEngine.changeCurrentMap(CoreEngine.battlePositionMap);
    }

  }

  public static void nextDay() {
    CoreEngine.roundManager.nextDay();
  }

  public static void startBattle(Container attacker, Container defender) {
    CoreEngine.battleMap = new BattleMap(attacker, defender);
    CoreEngine.battleManager = new BattleCoreManager(attacker, defender);
    CoreEngine.changeCurrentMap(CoreEngine.battleMap);
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

  public static EncounterCoreManager encounterManager() {
    return CoreEngine.encounterManager;
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
}
