package fr.umlv.hmm2000.salesentity.spell;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.LocationSelectionRequester;
import fr.umlv.hmm2000.engine.LocationSelectionRequester.LocationSelection;
import fr.umlv.hmm2000.engine.manager.MoveCoreManager.Encounter;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.element.MapBackgroundElement;
import fr.umlv.hmm2000.map.element.MapBackgroundEnum;

/**
 * This class defines the action which is performed when a obstacle construction
 * occurs.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class ObstacleConstructionSpellAction implements SpellAction {

  private static ObstacleConstructionSpellAction instance;

  /**
   * Returns the single element of this action.
   * 
   * @return singleton element.
   */
  public static ObstacleConstructionSpellAction getInstance() {
    if (ObstacleConstructionSpellAction.instance == null) {
      ObstacleConstructionSpellAction.instance = new ObstacleConstructionSpellAction();
    }
    return ObstacleConstructionSpellAction.instance;
  }

  /**
   * Default constructor.
   */
  private ObstacleConstructionSpellAction() {
  }

  @Override
  public void perform(final Encounter encounter) {
    // Request a recheable location
    CoreEngine.requestLocationSelection(new LocationSelectionRequester(
        new LocationSelection(LocationSelectionRequester.RECHEABLE_LOCATION,
            "Where do you want to add an obstacle ?")) {
      @Override
      public void perform(Location... locations) {
        Location l = locations[0];
        MapBackgroundElement oldElement = CoreEngine.map()
            .getMapBackgroundElementAtLocation(l);
        MapBackgroundElement newElement = MapBackgroundEnum.TREE;
        // Change the background element to tree
        CoreEngine.map().changeMapBackgroundElement(l, newElement);
        CoreEngine.fireSpriteRemoved(l, oldElement.getSprite());
        CoreEngine.fireSpriteAdded(l, newElement.getSprite());
      }
    });
  }

}
