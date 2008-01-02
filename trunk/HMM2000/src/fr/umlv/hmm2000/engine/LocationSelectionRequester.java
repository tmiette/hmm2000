package fr.umlv.hmm2000.engine;

import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.MovableElement;
import fr.umlv.hmm2000.map.element.MapBackgroundElement;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.war.BattleMap;
import fr.umlv.hmm2000.warrior.Fightable;
import fr.umlv.hmm2000.warrior.FightableContainer;

public abstract class LocationSelectionRequester {

  public static final int ANY_LOCATION = 1;
  public static final int RECHEABLE_LOCATION = 2;
  public static final int FOREGROUND_ELEMENT_LOCATION = 4;
  public static final int MOVABLE_ELEMENT_LOCATION = 8;
  public static final int UNREACHEABLE_BACKGROUND_ELEMENT_LOCATION = 16;
  public static final int BATTLE_OPPONENT_FIGHTABLE_LOCATION = 32;
  public static final int BATTLE_CURRENT_FIGHTABLE_LOCATION = 64;
  public static final int BATTLE_CURRENT_POSITION_LOCATION = 128;

  private int currentLocationsNumber;

  private Location[] locations;

  private LocationSelection[] selections;

  public LocationSelectionRequester(LocationSelection... selections) {
    if (selections.length == 0) {
      throw new IllegalArgumentException("must request more than 0 selection.");
    }
    this.selections = selections;
    this.locations = new Location[selections.length];
    this.currentLocationsNumber = 0;
    this.askForNextSelection();
  }

  private void askForNextSelection() {
    CoreEngine.fireMessage(this.selections[this.currentLocationsNumber]
        .getLabel());
  }

  public void submitLocation(Location l) {

    MapForegroundElement foregroundElement = CoreEngine.map()
        .getMapForegroundElementAtLocation(l);
    MapBackgroundElement backgroundElement = CoreEngine.map()
        .getMapBackgroundElementAtLocation(l);
    boolean isCorrectLocation = false;

    switch (this.selections[this.currentLocationsNumber].getTypeofSelection()) {
    case LocationSelectionRequester.ANY_LOCATION:
      isCorrectLocation = true;
      break;
    case LocationSelectionRequester.FOREGROUND_ELEMENT_LOCATION:
      if (foregroundElement != null) {
        isCorrectLocation = true;
      }
      break;
    case LocationSelectionRequester.MOVABLE_ELEMENT_LOCATION:
      if (foregroundElement != null
          && foregroundElement instanceof MovableElement) {
        isCorrectLocation = true;
      }
      break;
    case LocationSelectionRequester.RECHEABLE_LOCATION:
      if (foregroundElement == null && backgroundElement.getWeight() != 0.0) {
        isCorrectLocation = true;
      }
      break;
    case LocationSelectionRequester.UNREACHEABLE_BACKGROUND_ELEMENT_LOCATION:
      if (foregroundElement == null && backgroundElement.getWeight() == 0.0) {
        isCorrectLocation = true;
      }
      break;
    case LocationSelectionRequester.BATTLE_OPPONENT_FIGHTABLE_LOCATION:
      if (foregroundElement != null && foregroundElement instanceof Fightable) {
        Fightable f = (Fightable) foregroundElement;
        isCorrectLocation = !CoreEngine.battleManager().roundManager()
            .isCurrentPlayer(f.getFightableContainer().getPlayer());
      }
      break;
    case LocationSelectionRequester.BATTLE_CURRENT_FIGHTABLE_LOCATION:
      if (foregroundElement != null && foregroundElement instanceof Fightable) {
        Fightable f = (Fightable) foregroundElement;
        isCorrectLocation = CoreEngine.battleManager().roundManager()
            .isCurrentPlayer(f.getFightableContainer().getPlayer())
            && CoreEngine.battleManager().roundManager().hasAlreadyPlayed(f);
      }
      break;
    case LocationSelectionRequester.BATTLE_CURRENT_POSITION_LOCATION:
      BattleMap map = (BattleMap) CoreEngine.map();
      FightableContainer container = map.getFightableContainerAtLocation(l);
      if (container != null
          && container.equals(CoreEngine.battleManager().roundManager()
              .currentContainer())) {
        isCorrectLocation = true;
      }
      break;
    default:
      break;
    }

    if (isCorrectLocation) {
      this.locations[this.currentLocationsNumber] = l;
      this.currentLocationsNumber++;
    } else {
      CoreEngine.fireMessage("Selection incorrecte, veuillez recommencer.");
    }

    if (this.currentLocationsNumber != this.selections.length) {
      this.askForNextSelection();
    } else {
      this.perform();
      CoreEngine.clearLocationSelection();
    }

  }

  private void perform() {
    this.perform(this.locations);
    CoreEngine.clearLocationSelection();
  }

  public abstract void perform(Location... locations);

  public static class LocationSelection {

    private final int typeofSelection;
    private final String label;

    public LocationSelection(int typeofSelection, String label) {
      this.typeofSelection = typeofSelection;
      this.label = label;
    }

    public int getTypeofSelection() {
      return this.typeofSelection;
    }

    public String getLabel() {
      return this.label;
    }

  }

}
