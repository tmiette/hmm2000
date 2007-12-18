package fr.umlv.hmm2000.warrior;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.warrior.exception.MaxNumberOfTroopsReachedException;
import fr.umlv.hmm2000.warrior.exception.WarriorDeadException;
import fr.umlv.hmm2000.warrior.profil.ProfilHeroe;
import fr.umlv.hmm2000.warrior.profil.ProfilWarrior;

public class WarriorFactory {

	public static Warrior createWarrior(ProfilWarrior p, Level level) {

		double ratio = level.getRatio();
		Warrior w = new Warrior(p);
		if (ratio != 0) {
			try {
				w.setHealth(applyRatio(w.getHealth(), ratio));
			}
			catch (WarriorDeadException e) {
				// nothing to do
			}
			w.setAttackValue(applyRatio(w.getAttackValue(), ratio));
			w.setDefenseValue(applyRatio(w.getDefenseValue(), ratio));
		}

		return w;
	}

	public static Heroe createHeroe(ProfilHeroe p, Player player, String name,
			Level level) {

		double ratio = level.getRatio();
		Heroe h = new Heroe(player, p, name);
		if (ratio != 0) {
			try {
				h.setHealth(applyRatio(h.getHealth(), ratio));
			}
			catch (WarriorDeadException e) {
				// nothing to do
			}
			h.setSpeed((int) applyRatio(h.getSpeed(), ratio));
			h.setAttackValue(applyRatio(h.getAttackValue(), ratio));
			h.setDefenseValue(applyRatio(h.getDefenseValue(), ratio));
		}

		for (ProfilWarrior profilWarrior : p.getProfilWarrior()) {
			try {
				h
						.addWarrior(WarriorFactory.createWarrior(profilWarrior, player,
								level));
			}
			catch (MaxNumberOfTroopsReachedException e) {
				break;
			}
		}

		return h;

	}

	private static double applyRatio(double value, double ratio) {

		return (value + (value * ratio));
	}

}
