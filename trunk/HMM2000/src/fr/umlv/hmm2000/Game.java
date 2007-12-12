package fr.umlv.hmm2000;

import java.util.ArrayList;

public class Game {

	private final ArrayList<Player> players;
	
	public Game() {
		this.players = new ArrayList<Player>();
	}

	public void addPlayer(Player p) {
		this.players.add(p);
	}

}
