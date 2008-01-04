package fr.umlv.hmm2000.building;

import java.util.ArrayList;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.warrior.Hero;

public class ExitHeroItem implements CastleItem {

	private final Castle castle;

	public ExitHeroItem(Castle castle) {

		this.castle = castle;
	}

	@Override
	public String getSuggestion() {

		return "Prendre un Hero";
	}

	@Override
	public void perform() {

		if (this.castle.getHeroes().size() == 0) {
			CoreEngine.fireMessage("Aucun hero disponible");
		}
		else {
			ArrayList<CastleItem> items = new ArrayList<CastleItem>();
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
							Hero h = castle.getHero(hero);

							// adding hero on map
							CoreEngine.map().addMapForegroundElement(h, freeLocation);
							CoreEngine.fireSpriteAdded(freeLocation, h.getSprite());
						}
						else {
							CoreEngine.fireMessage("Aucune place disponible");
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
								if (CoreEngine.map().getMapBackgroundElementAtLocation(
										newLocation).getWeight() > 0
										&& CoreEngine.map().getMapForegroundElementAtLocation(
												newLocation) == null) {
									return newLocation;
								}
							}
						}
						return newLocation;
					}

				});
			}
			CastleItem item = CoreEngine.requestCastleItem(items);
			if (item != CastleItem.defaultItem) {
				item.perform();
			}
		}

	}

}
