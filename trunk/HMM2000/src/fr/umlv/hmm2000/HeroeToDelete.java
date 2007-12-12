package fr.umlv.hmm2000;

import fr.umlv.hmm2000.engine.event.EncounterEvent;
import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.map.MovableElement;

public class HeroeToDelete extends MovableElement {

	private final String name;

	public HeroeToDelete(Player player, String name) {
		super(player);
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public Sprite getSprite() {
		return Sprite.HEROE;
	}

	@Override
	public int getStepCount() {
		return 5;
	}

	@Override
	public boolean encounter(EncounterEvent event) {
		System.err.println("Combat");
		return false;
	}

	@Override
	public void accept(UIDisplayingVisitor visitor) {
		visitor.visit(this);
	}

}
