package fr.umlv.hmm2000.salesentity.spell;

import java.util.ArrayList;

import fr.umlv.hmm2000.engine.Engine;
import fr.umlv.hmm2000.engine.event.EncounterEvent;
import fr.umlv.hmm2000.engine.event.MoveEvent;
import fr.umlv.hmm2000.engine.guiinterface.LocationSelectionRequester;
import fr.umlv.hmm2000.engine.guiinterface.LocationSelectionRequester.LocationSelection;
import fr.umlv.hmm2000.engine.manager.MoveManager;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.util.Pair;

public class TeleportationSpellAction implements SpellAction {

  private static TeleportationSpellAction instance;

  public static TeleportationSpellAction getInstance() {
    if (TeleportationSpellAction.instance == null) {
      TeleportationSpellAction.instance = new TeleportationSpellAction();
    }
    return TeleportationSpellAction.instance;
  }

  private TeleportationSpellAction() {
  }

  @Override
  public void perform(final EncounterEvent event) {

    Engine.currentEngine().requestLocationSelection(
        new LocationSelectionRequester(new LocationSelection(
            LocationSelectionRequester.RECHEABLE_LOCATION,
            "Où voulez-vous vous téléportez ?")) {
          @Override
          public void perform(Location... locations) {
            Location l = locations[0];

            ArrayList<Pair<Location, Double>> moves = new ArrayList<Pair<Location, Double>>();
            moves
                .add(new Pair<Location, Double>(event.getSenderLocation(), 0.0));
            moves.add(new Pair<Location, Double>(l, 0.0));
            MoveEvent moveEvent = new MoveEvent(event.getSender(), moves);

            MoveManager moveManager = Engine.currentEngine().moveManager();
            moveManager.performMoveEvent(moveEvent);
          }
        });

  }
}
