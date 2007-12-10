package fr.umlv.hmm2000.war;

import java.util.ArrayList;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.warriors.Heroe;
import fr.umlv.hmm2000.warriors.attacks.Attack;
import fr.umlv.hmm2000.warriors.heroes.Archer;
import fr.umlv.hmm2000.warriors.heroes.LordOfWar;


public class TestWar {

	public static void main(String[] args) {

		Heroe a = new Archer(new Player(1), "archer");
		
		Heroe l = new LordOfWar(new Player(2), "lord");
		
		System.out.println(a);
		System.out.println(l);
		
		System.out.println("Choix attaques : ");
		System.out.println(a.getListOfAttacks());
		
		ArrayList<Attack> attacks = new ArrayList<Attack>();
		attacks.add(a.getListOfAttacks().get(3));
		attacks.add(a.getListOfAttacks().get(1));
		a.attack(l, attacks);
	}
}
