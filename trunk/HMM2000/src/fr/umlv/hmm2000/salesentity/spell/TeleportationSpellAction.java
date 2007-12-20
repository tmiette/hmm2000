package fr.umlv.hmm2000.salesentity.spell;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.LocationSelectionRequester;
import fr.umlv.hmm2000.engine.LocationSelectionRequester.LocationSelection;
import fr.umlv.hmm2000.engine.manager.MoveCoreManager.Encounter;
import fr.umlv.hmm2000.map.Location;

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
  public void perform(final Encounter encounter) {
    CoreEngine.requestLocationSelection(new LocationSelectionRequester(
        new LocationSelection(LocationSelectionRequester.RECHEABLE_LOCATION,
            "Où voulez-vous vous téléportez ?")) {
      @Override
      public void perform(Location... locations) {
        Location l = locations[0];
        CoreEngine.map().moveMapForegroundElement(
            encounter.getSenderLocation(), l);
        CoreEngine.selectionManager().perform(l);
      }
    });
  }
}
