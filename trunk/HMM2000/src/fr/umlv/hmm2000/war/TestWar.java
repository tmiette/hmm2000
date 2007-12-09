package fr.umlv.hmm2000.war;

import java.util.ArrayList;

import fr.umlv.hmm2000.warriors.attacks.Attack;
import fr.umlv.hmm2000.warriors.heroes.Archer;
import fr.umlv.hmm2000.warriors.heroes.Heroe;
import fr.umlv.hmm2000.warriors.heroes.LordOfWar;


public class TestWar {

	public static void main(String[] args) {

		Heroe a = new Archer();
		
		Heroe l = new LordOfWar();
		
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
