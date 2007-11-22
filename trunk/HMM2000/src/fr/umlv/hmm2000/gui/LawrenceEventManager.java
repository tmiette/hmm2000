package fr.umlv.hmm2000.gui;

import fr.umlv.hmm2000.manager.MoveEvent;
import fr.umlv.hmm2000.manager.SelectionEvent;
import fr.umlv.hmm2000.manager.UIEventManager;
import fr.umlv.hmm2000.manager.MoveEvent.Step;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.lawrence.DefaultGridModel;

public class LawrenceEventManager implements UIEventManager {

	private final DefaultGridModel<Sprite> model;

	public LawrenceEventManager(DefaultGridModel<Sprite> model) {
		this.model = model;
	}

	public void performSelection(SelectionEvent event) {
		Location location = event.getLocation();
		this.model.addDeffered(location.getY(), location.getX(),
				Sprite.SELECTION);
		this.model.swap();
	}

	public void performDeselection(SelectionEvent event) {
		Location location = event.getLocation();
		this.model.removeDeffered(location.getY(), location.getX(),
				Sprite.SELECTION);
		this.model.swap();
	}

	public void displayMove(MoveEvent event) {

		for (Step move : event.getMoves()) {
			Location l = move.getEnd();
			if (move.isRecheable()) {
				this.model.addDeffered(l.getY(), l.getX(), Sprite.RECHEABLE);
			} else {
				this.model.addDeffered(l.getY(), l.getX(), Sprite.UNRECHEABLE);
			}
		}
		this.model.swap();
	}

	public void removeMove(MoveEvent event) {

		for (Step move : event.getMoves()) {
			Location l = move.getEnd();
			this.model.removeDeffered(l.getY(), l.getX(), Sprite.RECHEABLE);
			this.model.removeDeffered(l.getY(), l.getX(), Sprite.UNRECHEABLE);
		}
		this.model.swap();
	}

	public void performStep(Step event) {
		Location start = event.getStart();
		Location end = event.getEnd();
		this.model.removeDeffered(start.getY(), start.getX(), event.getSource()
				.getSprite());
		this.model.removeDeffered(end.getY(), end.getX(), Sprite.RECHEABLE);
		this.model.removeDeffered(end.getY(), end.getX(), Sprite.UNRECHEABLE);
		this.model.addDeffered(end.getY(), end.getX(), event.getSource()
				.getSprite());
		try {
			// TODO a changer
			Thread.sleep(250);
		} catch (InterruptedException e) {
			//nothing
		}
		this.model.swap();
	}

}
