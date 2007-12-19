package fr.umlv.hmm2000.warrior.profil;

import fr.umlv.hmm2000.gui.Sprite;

public enum ProfilHero {

  ARCHER(Sprite.ARCHER),
  LORD_OF_WAR(Sprite.LORDOFWAR),
  SORCERER(Sprite.SORCERER);

  private final Sprite sprite;
  
  private ProfilHero(Sprite sprite) {
    this.sprite = sprite;
  }

  public Sprite getSprite() {
    return this.sprite;
  }

}
