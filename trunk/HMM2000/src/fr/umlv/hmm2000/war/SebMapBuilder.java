package fr.umlv.hmm2000.war;

import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.map.element.MapBackgroundEnum;
import fr.umlv.hmm2000.warrior.Container;
import fr.umlv.hmm2000.warrior.Warrior;

public class SebMapBuilder {

	private static final int LINE_BETWEEN_TROOPS = 4;

	public static Map createMap(Container c1, Container c2) {

		final Container maxContainer;
		final Container minContainer;
		if (c1.getBattlePositionManager()
					.getSlots() >= c2	.getBattlePositionManager()
															.getSlots()) {
			maxContainer = c1;
			minContainer = c2;
		}
		else {
			maxContainer = c2;
			minContainer = c1;
		}

		final int maxColumns = maxContainer	.getBattlePositionManager()
																				.getSlots();
		final int minColums = minContainer.getBattlePositionManager()
																			.getSlots();

		final int lines = BattlePositionManager.LINE_NUMBER * 2
				+ LINE_BETWEEN_TROOPS;

		MapBackgroundEnum[][] background = new MapBackgroundEnum[lines][maxColumns];

		for (int i = 0; i < background.length; i++) {
			for (int j = 0; j < background[0].length; j++) {
				background[i][j] = MapBackgroundEnum.PLAIN;
			}
		}

		Map map = new Map(background);

		// xmap = xbattle + (maxColumns - minColums) / 2;
		// ymap = 0;
		for (Warrior w : minContainer.getTroop()) {
			map.addMapForegroundElement(w,
																	new Location(	minContainer.getBattlePositionManager()
																														.getLocation(w)
																														.getX()
																										+ ((maxColumns - minColums) / 2),
																								0));
		}

		// xmap = nbcol - xbattle;
		// ymap = nbline - ybattle;
		for (Warrior w : maxContainer.getTroop()) {
			map.addMapForegroundElement(w,
																	new Location(	maxColumns
																										- maxContainer.getBattlePositionManager()
																																	.getLocation(w)
																																	.getX(),
																								lines
																										- maxContainer.getBattlePositionManager()
																																	.getLocation(w)
																																	.getY()));
		}

		return map;
	}


}
