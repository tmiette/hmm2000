package fr.umlv.hmm2000.engine.guiinterface;

import java.net.URL;

import fr.umlv.lawrence.svg.SVGImageProvider;

public enum Sprite {

  ALREADY("alreadyAttack"),
  ARCHER("archer"),
  BACKGROUND("background"),
  BARRACKS("barracks"),
  CASTLE("castle"),
  DEFAULT("white"),
  FLIGHT("flight"),
  GOLD_RESOURCE("goldresource"),
  GRUNT("grunt"),
  LORDOFWAR("lordofwar"),
  MERCHANT("merchant"),
  MOUNTAIN("mountain"),
  PATH("path"),
  PLAIN("plain"),
  POINTER("pointer"),
  RECHEABLE("recheable"),
  SELECTION("selection"),
  SORCERER("sorcerer"),
  TREE("tree"),
  TROLL("troll"),
  UNATTACKABLE("unattackable"),
  UNRECHEABLE("unrecheable"),
  WATER("water"),
  WIZZARD("wizzard"),
  YOURTURN("yourturn");

  private final String spritePath;

  private Sprite(String spritePath) {
    this.spritePath = spritePath;
  }

  public void register(SVGImageProvider<Sprite> provider) {
    provider.registerImage(this, Sprite.class
        .getResource("/sprites/" + spritePath + ".svg"));
  }

  public URL getIconPath() {
    return Sprite.class.getResource("/icons/" + this.spritePath + ".png");
  }

}
