package fr.umlv.hmm2000.warriors;

import fr.umlv.hmm2000.Player;
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

		return new Heroe(	player,
											p.getHealth(),
											p.getSpeed(),
											p.getSprite(),
											p.getDefenseValue(),
											p.getAttackValue(),
											p.getElements(),
											p.getTroop(),
											name);
	}

}
