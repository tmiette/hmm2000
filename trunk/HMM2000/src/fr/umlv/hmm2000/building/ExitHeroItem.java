package fr.umlv.hmm2000.building;

import java.util.ArrayList;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.map.element.MapBackgroundElement;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.unit.Hero;

/**
 * This class permits to the castle's player to take its heroes out on map.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class ExitHeroItem implements CastleItem {

	// Player's castle
	private final Castle castle;

	public ExitHeroItem(Castle castle) {

		this.castle = castle;
	}

	@Override
	public String getSuggestion() {

		return "Take a hero out";
	}

	@Override
	public void perform() {

		if (this.castle.getHeroes().size() == 0) {
			CoreEngine.fireMessage("No hero available.",
					HMMUserInterface.WARNING_MESSAGE);
		}
		else {
			ArrayList<CastleItem> items = new ArrayList<CastleItem>();
			// Heroes avalaible
			for (final Hero hero : this.castle.getHeroes()) {
				items.add(new CastleItem() {

					@Override
					public String getSuggestion() {

						return hero.getName();
					}

					@Override
					public void perform() {

						Location castleLocation = CoreEngine.map()
								.getLocationForMapForegroundElement(castle);
						Location freeLocation = this.freeLocationNearOf(castleLocation);
						if (freeLocation != null) {
							// taking hero from castle
							Hero h = castle.removeHero(hero);

							// adding hero on map
							CoreEngine.map().addMapForegroundElement(h, freeLocation);
							CoreEngine.fireSpriteAdded(freeLocation, h.getSprite());
						}
						else {
							CoreEngine.fireMessage("No place available",
									HMMUserInterface.WARNING_MESSAGE);
						}

					}

					/**
					 * Looks for a location on map without background or foreground
					 * element near of location given
					 * 
					 * @param location
					 * @return free location
					 */
					private Location freeLocationNearOf(Location location) {

						Location newLocation = null;
						int X = location.getX();
						int Y = location.getY();
						for (int x = -2; x < 2; x++) {
							for (int y = -2; y < 2; y++) {
								newLocation = new Location(X + x, Y + y);
								Map map = CoreEngine.map();
								MapBackgroundElement withoutBGElt = map
										.getMapBackgroundElementAtLocation(newLocation);
								MapForegroundElement withoutFBElt = map
										.getMapForegroundElementAtLocation(newLocation);

								if (validLocationOnMap(newLocation, map)
										&& withoutBGElt != null && withoutFBElt == null
										&& withoutBGElt.getWeight() > 0) {
									// Free location avalaible
									return newLocation;
								}
							}
						}
						return null;
					}

					/**
					 * Tests location validity on specific map
					 * 
					 * @param l
					 *          location to test
					 * @param map
					 * @return if location is contained in map
					 */
					private boolean validLocationOnMap(Location l, Map map) {

						int X = l.getX();
						int Y = l.getY();
						if (X >= 0 && X < map.getHeight() && Y >= 0 && Y < map.getWidth()) {
							return true;
						}
						return false;
					}

				});
			}
			// Adding items to choose manager
			CastleItem item = CoreEngine.requestCastleItem(items);
			if (item != null && item != CastleItem.defaultItem) {
				item.perform();
			}
		}

		// Refreshing the view
		CoreEngine.selectionManager().perform(
				CoreEngine.map().getLocationForMapForegroundElement(castle));

	}

}
