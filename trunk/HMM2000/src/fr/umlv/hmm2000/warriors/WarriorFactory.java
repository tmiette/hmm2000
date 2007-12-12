package fr.umlv.hmm2000.warriors;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.warriors.exceptions.MaxNumberOfTroopsReachedException;
import fr.umlv.hmm2000.warriors.profils.ProfilHeroe;
import fr.umlv.hmm2000.warriors.profils.ProfilWarrior;

public class WarriorFactory {

	public static Warrior createWarrior(ProfilWarrior p, Player player) {

		return new Warrior(	player,
												p.getHealth(),
												p.getSpeed(),
												p.getSprite(),
												p.getDefenseValue(),
												p.getAttackValue(),
												p.getElements());
	}
	
	public static Warrior createWarrior(ProfilHeroe p, Player player, String name) {

		Heroe heroe = new Heroe(player,
														p.getHealth(),
														p.getSpeed(),
														p.getSprite(),
														p.getDefenseValue(),
														p.getAttackValue(),
														p.getElements(),
														name);

		for (ProfilWarrior profilWarrior : p.getProfilWarrior()) {
			try {
				heroe.addWarrior(WarriorFactory.createWarrior(profilWarrior,
																											player));
			}
			catch (MaxNumberOfTroopsReachedException e) {
				break;
			}
		}

		return (Warrior) heroe;

	}

}
