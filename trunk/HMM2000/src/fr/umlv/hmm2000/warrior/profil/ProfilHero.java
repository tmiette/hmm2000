package fr.umlv.hmm2000.warrior.profil;

import java.util.ArrayList;

import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.warrior.Fightable;

public enum ProfilHero {

  ARCHER(Sprite.ARCHER),
  LORD_OF_WAR(Sprite.LORDOFWAR),
  SORCERER(Sprite.SORCERER);

  private final Sprite sprite;
  
  private final ArrayList<Fightable> units;
  
  private ProfilHero(Sprite sprite, ArrayList<Fightable> units) {
    this.sprite = sprite;
  }

  public Sprite getSprite() {
    return this.sprite;
  }

}
