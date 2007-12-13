package fr.umlv.hmm2000.engine;

import java.io.FileNotFoundException;
import java.io.IOException;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.engine.guiinterface.LocationSelectionRequester;
import fr.umlv.hmm2000.engine.guiinterface.UIEngine;
import fr.umlv.hmm2000.engine.manager.EncounterManager;
import fr.umlv.hmm2000.engine.manager.MoveManager;
import fr.umlv.hmm2000.engine.manager.RoundManager;
import fr.umlv.hmm2000.engine.manager.SelectionManager;
import fr.umlv.hmm2000.map.InvalidPlayersNumberException;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.map.MapBuilder;
import fr.umlv.hmm2000.map.MapLevel;

public class Engine {

  private static Engine currentEngine;

  private final Map map;

  private final UIEngine uiManager;

  private final MoveManager moveManager;

  private final SelectionManager selectionManager;

  private final EncounterManager encounterManager;

  private final RoundManager roundManager;

  private LocationSelectionRequester locationRequester;

  public static void startNewEngine(MapLevel level, UIEngine uiEngine,
      Player... players) throws InvalidPlayersNumberException,
      FileNotFoundException, IOException {

    if (players.length < level.getMinPlayerNumber()
        || players.length > level.getMaxPlayerNumber()) {
      throw new InvalidPlayersNumberException(level, players.length);
    }

    //TODO builder
    Map map = MapBuilder.createMapTESTVERSION(level, players[0], players[1]);
    Engine.currentEngine = new Engine(map, uiEngine, players);
    uiEngine.drawMap(map);
  }

  public static Engine currentEngine() {
    return Engine.currentEngine;
  }

  private Engine(Map map, UIEngine uiManager, Player... players)
      throws InvalidPlayersNumberException {
    this.map = map;
    this.uiManager = uiManager;
    this.selectionManager = new SelectionManager();
    this.encounterManager = new EncounterManager();
    this.moveManager = new MoveManager();
    this.roundManager = new RoundManager(players);
  }

  public void locationClicked(int x, int y, int button) {
    Location l = new Location(x, y);

    if (this.locationRequester != null) {
      this.locationRequester.submitLocation(l);
    } else {
      if (button == 1) {
        this.moveManager.clearCurrentMoveEvent();
        this.selectionManager.perform(l);
      } else if (button == 3) {
        this.moveManager.perform(l);
      }
    }
  }

  public void nextDay() {
    this.roundManager.nextDay();

  }

  public Map map() {
    return this.map;
  }

  public UIEngine uiManager() {
    return this.uiManager;
  }

  public SelectionManager selectionManager() {
    return this.selectionManager;
  }

  public MoveManager moveManager() {
    return this.moveManager;
  }

  public EncounterManager encounterManager() {
    return this.encounterManager;
  }

  public RoundManager roundManager() {
    return this.roundManager;
  }

  public void requestLocationSelection(LocationSelectionRequester requester) {
    this.locationRequester = requester;
  }

  public void clearLocationSelection() {
    this.locationRequester = null;
  }
}
