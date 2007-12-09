package fr.umlv.hmm2000;

import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.manager.EncounterEvent;
import fr.umlv.hmm2000.manager.UIDisplayingVisitor;
import fr.umlv.hmm2000.manager.UIEventManager;
import fr.umlv.hmm2000.map.Map;

public class Heroe extends MovableElement {

	private final String name;

	public Heroe(Player player, String name) {
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
	public boolean encounter(EncounterEvent event, Map map,
			UIEventManager uiManager) {
		System.err.println("Combat");
		return false;
	}

	@Override
	public void accept(UIDisplayingVisitor visitor) {
		visitor.visit(this);
	}

}
