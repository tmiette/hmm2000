package fr.umlv.hmm2000.engine.guiinterface;

import java.net.URL;

import fr.umlv.lawrence.svg.SVGImageProvider;

/**
 * This enum represents all the sprite used in the game.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
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
  YOURTURN("yourturn"),
  MUMMY("mummy"),
  ZOMBIE("zombie"),
  VAMPIRE("vampire"),
  DEAMON("deamon");

  private final String spritePath;

  /**
   * Constructor of the enum.
   * 
   * @param spritePath
   *            name of the sprite.
   */
  private Sprite(String spritePath) {
    this.spritePath = spritePath;
  }

  /**
   * Registers the SVG image to the provider.
   * 
   * @param provider
   *            the provider.
   */
  public void register(SVGImageProvider<Sprite> provider) {
    provider.registerImage(this, Sprite.class.getResource("/sprites/"
        + spritePath + ".svg"));
  }

  /**
   * Returns the path of the PNG icon.
   * 
   * @return the path of the PNG icon.
   */
  public URL getIconPath() {
    return Sprite.class.getResource("/icons/" + this.spritePath + ".png");
  }

}
