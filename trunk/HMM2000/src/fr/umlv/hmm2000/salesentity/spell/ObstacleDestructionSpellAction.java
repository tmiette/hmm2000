package fr.umlv.hmm2000.salesentity.spell;

import fr.umlv.hmm2000.engine.Engine;
import fr.umlv.hmm2000.engine.event.EncounterEvent;
import fr.umlv.hmm2000.engine.event.MapChangeEvent;
import fr.umlv.hmm2000.engine.guiinterface.LocationSelectionRequester;
import fr.umlv.hmm2000.engine.guiinterface.LocationSelectionRequester.LocationSelection;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.MapBackgroundEnum;

public class ObstacleDestructionSpellAction implements SpellAction {

  private static ObstacleDestructionSpellAction instance;

  public static ObstacleDestructionSpellAction getInstance() {
    if (ObstacleDestructionSpellAction.instance == null) {
      ObstacleDestructionSpellAction.instance = new ObstacleDestructionSpellAction();
    }
    return ObstacleDestructionSpellAction.instance;
  }

  private ObstacleDestructionSpellAction() {
  }

  @Override
  public void perform(final EncounterEvent event) {
    Engine
        .currentEngine()
        .requestLocationSelection(
            new LocationSelectionRequester(
                new LocationSelection(
                    LocationSelectionRequester.UNREACHEABLE_BACKGROUND_ELEMENT_LOCATION,
                    "Quel obstacle voulez-vous détruire ?")) {
              @Override
              public void perform(Location... locations) {
                Location l = locations[0];

                MapChangeEvent event = new MapChangeEvent(l,
                    Engine.currentEngine().map()
                        .getMapBackgroundElementAtLocation(l),
                    MapBackgroundEnum.PLAIN);

                Engine.currentEngine().map().changeMapBackgroundElement(l,
                    MapBackgroundEnum.PLAIN);

                Engine.currentEngine().uiManager().changeBackgroundElement(
                    event);
              }
            });
  }
}
