package fr.umlv.hmm2000.warriors;

import java.util.HashMap;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.engine.event.EncounterEvent;
import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.map.MovableElement;
import fr.umlv.hmm2000.warriors.elements.Element;
import fr.umlv.hmm2000.warriors.elements.ElementEnum;
import fr.umlv.hmm2000.warriors.elements.ElementImpl;
import fr.umlv.hmm2000.warriors.profils.ProfilWarrior;

public class Warrior extends MovableElement implements ProfilWarrior {

	private double health;

	private int speed;

	private Sprite sprite;

	private double defenseValue;

	private double attackValue;

	private HashMap<ElementEnum, Element> elements;

	Warrior(Player player,
					double health,
					int speed,
					Sprite sprite,
					double defenseValue,
					double attackValue) {

		super(player);
		this.health = health;
		this.speed = speed;
		this.sprite = sprite;
		this.defenseValue = defenseValue;
		this.attackValue = attackValue;
		this.elements = initElements();
	}

	private HashMap<ElementEnum, Element> initElements() {

		HashMap<ElementEnum, Element> hm = new HashMap<ElementEnum, Element>();
		for(ElementEnum e : ElementEnum.values()){
			hm.put(e, new ElementImpl());
		}
		return hm;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("Warrior : \n(Health = ");
		sb.append(this.health);
		sb.append(",StepCount = ");
		sb.append(this.speed);
		sb.append(",Attacks = ");
		sb.append(")");
		return sb.toString();
	}

	@Override
	public Sprite getSprite() {

		return this.sprite;
	}

	@Override
	public double getAttackValue() {

		return this.attackValue;
	}

	@Override
	public double getDefenseValue() {

		return this.defenseValue;
	}

	@Override
	public double getHealth() {

		return this.health;
	}

	@Override
	public int getSpeed() {

		return this.speed;
	}

	public void setSpeed(int speed) {

		this.speed = speed;
	}

	@Override
	public int getStepCount() {

		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public HashMap<ElementEnum, Element> getElements() {

		// TODO Auto-generated method stub
		return null;
	}

  @Override
  public void accept(UIDisplayingVisitor visitor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public boolean encounter(EncounterEvent event) {
    // TODO Auto-generated method stub
    return false;
  }

}
