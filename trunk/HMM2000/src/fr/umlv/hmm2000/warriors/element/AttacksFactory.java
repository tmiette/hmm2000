package fr.umlv.hmm2000.warriors.elements;

import java.util.ArrayList;

public class AttacksFactory {

	public static ArrayList<Element> createAttacks(Element... attacks) {

		ArrayList<Element> a = new ArrayList<Element>();
		if (attacks != null) {
			for (Element attack : attacks) {
				a.add(attack);
			}
		}

		return a;
	}
	
	public static Element createAttack(ProfilAttack p) {

		return new AttackImpl(p.getDamage(), p.getResistance());
	}
}
