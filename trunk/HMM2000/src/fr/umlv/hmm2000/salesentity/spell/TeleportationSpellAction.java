package fr.umlv.hmm2000.salesentity.spell;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.LocationSelectionRequester;
import fr.umlv.hmm2000.engine.LocationSelectionRequester.LocationSelection;
import fr.umlv.hmm2000.engine.manager.MoveCoreManager.Encounter;
import fr.umlv.hmm2000.map.Location;

/**
 * This class defines the action which is performed when a obstacle destruction
 * occurs.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class TeleportationSpellAction implements SpellAction {

  private static TeleportationSpellAction instance;

  /**
   * Returns the single element of this action.
   * 
   * @return singleton element.
   */
  public static TeleportationSpellAction getInstance() {
    if (TeleportationSpellAction.instance == null) {
      TeleportationSpellAction.instance = new TeleportationSpellAction();
    }
    return TeleportationSpellAction.instance;
  }

  /**
   * Default constructor.
   */
  private TeleportationSpellAction() {
  }

  @Override
  public void perform(final Encounter encounter) {
    CoreEngine.requestLocationSelection(new LocationSelectionRequester(
        new LocationSelection(LocationSelectionRequester.RECHEABLE_LOCATION,
            "Where do you want to teleport yourself ?")) {
      @Override
      public void perform(Location... locations) {
        Location l = locations[0];
        CoreEngine.map().moveMapForegroundElement(
            encounter.getSenderLocation(), l);
        CoreEngine.fireSpriteRemoved(encounter.getSenderLocation(), encounter
            .getSender().getSprite());
        CoreEngine.fireSpriteAdded(l, encounter.getSender().getSprite());
        CoreEngine.selectionManager().perform(l);
      }
    });
  }
}
