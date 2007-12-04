package fr.umlv.hmm2000.characters.heroes;

import fr.umlv.hmm2000.MovableElement;
import fr.umlv.hmm2000.gui.Sprite;

public class Heroe extends MovableElement {

	private final String name;

	public Heroe(String name) {
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

}
