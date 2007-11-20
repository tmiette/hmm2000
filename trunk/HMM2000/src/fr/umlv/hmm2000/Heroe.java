package fr.umlv.hmm2000;

import fr.umlv.hmm2000.gui.Sprite;

public class Heroe extends MovableElement {

	private final String name;

	public Heroe(String name) {
		this.name = name;
	}

	@Override
	public Event isSelected() {
		System.out.println(this.name);
		return null;
	}

	@Override
	public Sprite getSprite() {
		return Sprite.HEROE;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getStepCount() {
		return 2;
	}

}
