package fr.umlv.hmm2000;

import java.util.ArrayList;

import fr.umlv.hmm2000.manager.EncounterEvent;
import fr.umlv.hmm2000.manager.MoveEvent;
import fr.umlv.hmm2000.manager.UIEventManager;
import fr.umlv.hmm2000.manager.MoveEvent.Step;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;

public enum Spell {

	TELEPORTATION("Téléportation", new SpellAction() {
		@Override
		public void perform(EncounterEvent event, Map map,
				UIEventManager uiManager) {
			Location l = new Location(2, 6);
			// TODO recuperer location destination
			ArrayList<Pair<Location, Double>> moves = new ArrayList<Pair<Location, Double>>();
			moves.add(new Pair<Location, Double>(event.getSenderLocation(), 0.0));
			moves.add(new Pair<Location, Double>(l, 0.0));
			MoveEvent moveEvent = new MoveEvent(event.getSender(), moves);
			for (Step move : moveEvent.getMoves()) {
				map.moveMapForegroundElement(move.getStart(), move
						.getEnd());
				uiManager.performStep(move);
				//selectionManager.perform(move.getEnd());
			}
		}
	}), OBSTACLE_DESTRUCTION("Destruction d'un obstacle", new SpellAction() {
		@Override
		public void perform(EncounterEvent event, Map map,
				UIEventManager uiManager) {
			// TODO Auto-generated method stub

		}
	});

	private final String name;
	private final SpellAction action;

	private Spell(String name, SpellAction action) {
		this.name = name;
		this.action = action;
	}

	public String getName() {
		return this.name;
	}

	public void launch(EncounterEvent event, Map map, UIEventManager uiManager) {
		this.action.perform(event, map, uiManager);
	}

}
