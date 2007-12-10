package fr.umlv.hmm2000.warriors;

import java.util.ArrayList;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.warriors.profils.ProfilWarrior;
import fr.umlv.hmm2000.warriors.profils.ProfilHeroe;

public class WarriorFactory {

	public static Warrior createWarrior(ProfilWarrior p) {

		// TODO recuperer le player
		Player player = null;
		return new Warrior(player, p.getHealth(), p.getSpeed(), p
				.getListOfAttacks(), null, p.getSprite(), p.getDefenseValue(), p
				.getAttackValue());
	}

	public static Warrior createWarrior(ProfilHeroe p) {

		// TODO recuperer le player et le nom
		Player player = null;
		String name = null;
		return new Heroe(player, p.getHealth(), p.getSpeed(), p.getListOfAttacks(),
				null, p.getSprite(), p.getDefenseValue(), p.getAttackValue(), name, p
						.getTroop());
	}

	public static ArrayList<Warrior> createWarriors(ProfilWarrior... p) {

		ArrayList<Warrior> warriors = new ArrayList<Warrior>();
		if (p != null) {
			for (int i = 0; i < p.length; i++) {
				warriors.add(WarriorFactory.createWarrior(p[i]));
			}
		}
		return warriors;
	}
}
