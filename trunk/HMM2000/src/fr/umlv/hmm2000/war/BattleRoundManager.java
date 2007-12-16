package fr.umlv.hmm2000.war;

import java.util.Random;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.warrior.Container;
import fr.umlv.hmm2000.warrior.Warrior;
import fr.umlv.hmm2000.warrior.profil.ProfilHeroe;


public class BattleRoundManager {

	private Player currentPlayer;
	
	private Player p1;
	
	private Player p2;
	
	
	public BattleRoundManager(Container attacker, Container defender) {

		this.p1 = attacker.getPlayer();
		this.p2 = defender.getPlayer();
	}
	
	private Player chooseCurrentPlayer(Container attacker, Container c2) {

		if (attacker.getProfilName().equals(ProfilHeroe.LORD_OF_WAR.name())) {
			return this.p1;
		}
		return this.p2;
	}
	
	private Player chooseOnePlayer(){
		int n = new Random().nextInt(10);
		return (n % 2 == 0 ? this.p1 : this.p2);
	}
}
