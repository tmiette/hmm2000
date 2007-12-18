package fr.umlv.hmm2000.gui;

import fr.umlv.hmm2000.map.element.MapBackgroundEnum;
import fr.umlv.lawrence.svg.SVGImageProvider;

public enum Sprite {

  DEFAULT("black"),
  TREE("tree"),
  WATER("water"),
  MOUNTAIN("mountain"),
  PLAIN("plain"),
  PATH("path"),
  HEROE("heroe"),
  GOLD_RESOURCE("goldresource"),
  RECHEABLE("recheable"),
  UNRECHEABLE("unrecheable"),
  SELECTION("selection"),
  BACKGROUND("background"),
  POINTER("pointer"),
  MERCHANT("merchant2"),
  BARRACKS("barracks"),
  FLIGHT("flight"),
  WIZZARD("wizzard"),
  GRUNT("grunt"),
  SORCERER("sorcerer"),
  LORDOFWAR("lordofwar"),
  ARCHER("archer");

  private final String spritePath;

  private Sprite(String spritePath) {
    this.spritePath = spritePath;
  }

  public void register(SVGImageProvider<Sprite> provider) {
    provider.registerImage(this, MapBackgroundEnum.class
        .getResource("/sprites/" + spritePath + ".svg"));
  }

}
