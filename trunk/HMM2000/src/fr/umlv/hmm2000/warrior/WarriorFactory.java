package fr.umlv.hmm2000.warrior;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.warrior.exception.MaxNumberOfTroopsReachedException;
import fr.umlv.hmm2000.warrior.profil.ProfilHeroe;
import fr.umlv.hmm2000.warrior.profil.ProfilWarrior;

public class WarriorFactory {

	public static Warrior createWarrior(ProfilWarrior p, Player player,
			Level level) {

		double ratio = level.getRatio();
		return new Warrior(	player,
												applyRatio(	p.getHealth(),
																		ratio),
												(int) applyRatio(	p.getSpeed(),
																					ratio),
												p.getSprite(),
												applyRatio(	p.getDefenseValue(),
																		ratio),
												applyRatio(	p.getAttackValue(),
																		ratio),
												p.getElements());
	}

	public static Heroe createHeroe(ProfilHeroe p, Player player, String name,
			Level level) {

		double ratio = level.getRatio();
		Heroe heroe = new Heroe(player,
														applyRatio(	p.getHealth(),
																				ratio),
														(int) applyRatio(	p.getSpeed(),
																							ratio),
														p.getSprite(),
														applyRatio(	p.getDefenseValue(),
																				ratio),
														applyRatio(	p.getAttackValue(),
																				ratio),
														p.getElements(),
														name);

		for (ProfilWarrior profilWarrior : p.getProfilWarrior()) {
			try {
				heroe.addWarrior(WarriorFactory.createWarrior(profilWarrior,
																											player,
																											level));
			}
			catch (MaxNumberOfTroopsReachedException e) {
				break;
			}
		}

		return heroe;

	}

	private static double applyRatio(double value, double ratio) {

		return (value + (value * ratio));
	}

}
