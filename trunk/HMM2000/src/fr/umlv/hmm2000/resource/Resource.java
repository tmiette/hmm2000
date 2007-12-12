package fr.umlv.hmm2000.resource;

import fr.umlv.hmm2000.engine.event.EncounterEvent;
import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.map.MapForegroundElement;

public class Resource implements MapForegroundElement {

	private final Kind kind;
	private int currentValue;
	private final int maxValue;
	private final int period;
	private final int addition;
	private int periodCounter;

	public Resource(Kind kind, int startValue, int maxValue, int period,
			int addition) {
		this.kind = kind;
		this.currentValue = startValue;
		this.maxValue = maxValue > startValue ? maxValue : startValue;
		this.period = period > 0 ? period : 1;
		this.addition = addition > 0 ? addition : maxValue;
		this.periodCounter = 0;
	}

	public void fill() {
		this.periodCounter++;
		if (this.periodCounter == this.period) {
			if (this.currentValue != this.maxValue) {
				this.currentValue += this.addition % this.maxValue;
			}
			this.periodCounter = 0;
		}
	}

	public void drain() {
		this.currentValue = 0;
	}

	public Kind getKind() {
		return this.kind;
	}

	public int getCurrentValue() {
		return this.currentValue;
	}

	public int getMaxValue() {
		return this.maxValue;
	}

	public int getPeriod() {
		return this.period;
	}

	public int getAddition() {
		return this.addition;
	}

	public int getPeriodCounter() {
		return this.periodCounter;
	}

	@Override
	public Sprite getSprite() {
		return this.kind.getSprite();
	}

	@Override
	public boolean encounter(EncounterEvent event) {
		event.getSender().getPlayer().addResource(this.kind, this.currentValue);
		this.drain();
		return true;
	}

	@Override
	public void accept(UIDisplayingVisitor visitor) {
		visitor.visit(this);
	}

	public enum Kind {
		GOLD(Sprite.GOLD_RESOURCE);

		private final Sprite sprite;

		private Kind(Sprite sprite) {
			this.sprite = sprite;
		}

		private Sprite getSprite() {
			return this.sprite;
		}

	}

}
