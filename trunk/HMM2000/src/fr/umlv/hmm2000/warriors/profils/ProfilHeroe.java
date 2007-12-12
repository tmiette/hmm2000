package fr.umlv.hmm2000.warriors.profils;

import java.util.ArrayList;
import java.util.HashMap;

import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.warriors.Heroe;
import fr.umlv.hmm2000.warriors.Warrior;
import fr.umlv.hmm2000.warriors.WarriorFactory;
import fr.umlv.hmm2000.warriors.elements.Element;
import fr.umlv.hmm2000.warriors.elements.ElementEnum;

public enum ProfilHeroe implements ProfilWarrior {

	ARCHER(	10,
					10,
					100,
					20,
					Sprite.HEROE,
					new ProfilWarrior[] { ProfilCreatures.GRUNT },
					new Element[] {}),
	LORD_OF_WAR(10,
							10,
							100,
							20,
							Sprite.HEROE,
							new ProfilWarrior[] { ProfilCreatures.GRUNT },
							new Element[] {}),
	SORCERER(	10,
						10,
						100,
						20,
						Sprite.HEROE,
						new ProfilWarrior[] { ProfilCreatures.GRUNT },
						new Element[] {});

	private double attackValue;

	private double defenseValue;

	private double health;

	private int speed;

	private Sprite sprite;

	private ArrayList<Warrior> troop;
	
	private HashMap<ElementEnum, Element> elements;

	private ProfilHeroe(double attackValue,
											double defenseValue,
											double health,
											int speed,
											Sprite sprite,
											ProfilWarrior[] pw,
											Element[] ee) {

		this.attackValue = attackValue;
		this.defenseValue = defenseValue;
		this.health = health;
		this.speed = speed;
		this.sprite = sprite;
		
		this.troop = new ArrayList<Warrior>(Heroe.MAX_TROOP_SIZE);
		int warriors = 0;
		for (ProfilWarrior profilWarrior : pw) {
			if (warriors >= Heroe.MAX_TROOP_SIZE) {
				break;
			}
			warriors++;
			this.troop.add(WarriorFactory.createWarrior(profilWarrior, null));
		}
		
		this.elements = new HashMap<ElementEnum, Element>();
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

	public ArrayList<Warrior> getTroop() {

		return this.troop;
	}

	public void setTroop(ArrayList<Warrior> troop) {

		this.troop = troop;
	}

	@Override
	public HashMap<ElementEnum, Element> getElements() {

		return this.elements;
	}

}
