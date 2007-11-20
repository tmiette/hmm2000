package fr.umlv.hmm2000;

import fr.umlv.hmm2000.gui.Sprite;

public class GoldResource implements Resource {
	
	@Override
	public Event isSelected() {
		System.out.println("resource");
		return null;
	}

	@Override
	public Sprite getSprite() {
		return Sprite.GOLD_RESOURCE;
	}

}
