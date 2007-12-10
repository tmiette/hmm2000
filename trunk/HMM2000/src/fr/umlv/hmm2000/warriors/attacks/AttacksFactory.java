package fr.umlv.hmm2000.warriors.attacks;

import java.util.ArrayList;

public class AttacksFactory {

	public static ArrayList<Attack> createAttacks(Attack... attacks) {

		ArrayList<Attack> a = new ArrayList<Attack>();
		if (attacks != null) {
			for (Attack attack : attacks) {
				a.add(attack);
			}
		}

		return a;
	}
	
	public static Attack createAttack(ProfilAttack p) {

		return new AttackImpl(p.getDamage(), p.getResistance());
	}
}
