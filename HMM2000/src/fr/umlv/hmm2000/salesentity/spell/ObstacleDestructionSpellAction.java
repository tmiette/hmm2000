package fr.umlv.hmm2000.salesentity.spell;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.LocationSelectionRequester;
import fr.umlv.hmm2000.engine.LocationSelectionRequester.LocationSelection;
import fr.umlv.hmm2000.engine.manager.MoveCoreManager.Encounter;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.element.MapBackgroundElement;
import fr.umlv.hmm2000.map.element.MapBackgroundEnum;

/**
 * This class defines the action which is performed when a obstacle destruction
 * occurs.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class ObstacleDestructionSpellAction implements SpellAction {

  private static ObstacleDestructionSpellAction instance;

  /**
   * Returns the single element of this action.
   * 
   * @return singleton element.
   */
  public static ObstacleDestructionSpellAction getInstance() {
    if (ObstacleDestructionSpellAction.instance == null) {
      ObstacleDestructionSpellAction.instance = new ObstacleDestructionSpellAction();
    }
    return ObstacleDestructionSpellAction.instance;
  }

  /**
   * Default constructor.
   */
  private ObstacleDestructionSpellAction() {
  }

  @Override
  public void perform(final Encounter encounter) {
    CoreEngine
        .requestLocationSelection(new LocationSelectionRequester(
            new LocationSelection(
                LocationSelectionRequester.UNREACHEABLE_BACKGROUND_ELEMENT_LOCATION,
                "Which obstacle do you want to destroy ?")) {
          @Override
          public void perform(Location... locations) {
            Location l = locations[0];
            MapBackgroundElement oldElement = CoreEngine.map()
                .getMapBackgroundElementAtLocation(l);
            MapBackgroundElement newElement = MapBackgroundEnum.PLAIN;
            CoreEngine.map().changeMapBackgroundElement(l, newElement);
            CoreEngine.fireSpriteRemoved(l, oldElement.getSprite());
            CoreEngine.fireSpriteAdded(l, newElement.getSprite());
          }
        });
  }
}
