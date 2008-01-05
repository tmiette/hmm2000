package fr.umlv.hmm2000.map.element;

import fr.umlv.hmm2000.engine.guiinterface.Sprite;

/**
 * This enum represents kind of map background element.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public enum MapBackgroundEnum implements MapBackgroundElement {

	TREE(	0.0,
				Sprite.TREE),
	WATER(0.0,
				Sprite.WATER),
	MOUNTAIN(	0.0,
						Sprite.MOUNTAIN),
	PLAIN(2.0,
				Sprite.PLAIN),
	PATH(	1.0,
				Sprite.PATH);

	// Map background elemnt weight
	private final double weight;

	// Icon reprensenting the map background element
	private final Sprite sprite;

	private MapBackgroundEnum(double weight,
														Sprite sprite) {

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
