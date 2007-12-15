package fr.umlv.hmm2000.warrior.profil;

import java.util.HashMap;

import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.warrior.Warrior;
import fr.umlv.hmm2000.warrior.attack.elementary.Element;
import fr.umlv.hmm2000.warrior.attack.elementary.ElementaryEnum;
import fr.umlv.hmm2000.warrior.attack.elementary.Elementary;

public enum ProfilCreatures implements ProfilWarrior {

	FLIGHT(	10,
					10,
					100,
					20,
					Sprite.MERCHANT,
					new Element[] {new Elementary(ElementaryEnum.FIRE, 10, 10)}){
		@Override
		public void attack(Warrior warrior) {
		
			// TODO Auto-generated method stub
			
		}
	},
	GRUNT(10,
				10,
				100,
				20,
				Sprite.HEROE,
				new Element[] {}){
		@Override
		public void attack(Warrior warrior) {
		
			// TODO Auto-generated method stub
			
		}
	},
	WIZZARD(10,
					10,
					100,
					20,
					Sprite.MERCHANT,
					new Element[] {}){
		@Override
		public void attack(Warrior warrior) {
		
			// TODO Auto-generated method stub
			
		}
	};

	private double attackValue;

	private double defenseValue;

	private double health;

	private int speed;

	private Sprite sprite;
	
	private HashMap<ElementaryEnum, Element> elements;

	private ProfilCreatures(double attackValue,
													double defenseValue,
													double health,
													int speed,
													Sprite sprite,
													Element[] ee) {

		this.attackValue = attackValue;
		this.defenseValue = defenseValue;
		this.health = health;
		this.speed = speed;
		this.sprite = sprite;
		this.elements = new HashMap<ElementaryEnum, Element>();
		for (Element element : ee) {
			this.elements.put(element.getType(), element);
		}
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

	@Override
	public Sprite getSprite() {

		return this.sprite;
	}

	@Override
	public HashMap<ElementaryEnum, Element> getElements() {

		return this.elements;
	}
	
	@Override
	public void attack(Warrior warrior) {
	
		// TODO Auto-generated method stub
		
	}

}
