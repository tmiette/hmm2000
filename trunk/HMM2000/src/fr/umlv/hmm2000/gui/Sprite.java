package fr.umlv.hmm2000.gui;

import fr.umlv.hmm2000.map.element.MapBackgroundEnum;
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
    provider.registerImage(this, MapBackgroundEnum.class
        .getResource("/sprites/" + spritePath + ".svg"));
  }

}
