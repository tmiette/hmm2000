package fr.umlv.hmm2000.map.element;

import fr.umlv.hmm2000.engine.guiinterface.Sprite;

public enum MapBackgroundEnum implements MapBackgroundElement {

	TREE(0.0, Sprite.TREE), WATER(0.0, Sprite.WATER), MOUNTAIN(0.0,
			Sprite.MOUNTAIN), PLAIN(2.0, Sprite.PLAIN), PATH(1.0, Sprite.PATH);

	private final double weight;

	private final Sprite sprite;

	private MapBackgroundEnum(double weight, Sprite sprite) {
		this.weight = weight;
		this.sprite = sprite;
	}

	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public Sprite getSprite() {
		return this.sprite;
	}
}
