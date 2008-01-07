package fr.umlv.hmm2000.map.builder;

import fr.umlv.hmm2000.building.Castle;
import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.Player;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.resource.Resource;
import fr.umlv.hmm2000.salesentity.SalesEntity;
import fr.umlv.hmm2000.unit.FightableContainer;
import fr.umlv.hmm2000.unit.Hero;
import fr.umlv.hmm2000.unit.Monster;
import fr.umlv.hmm2000.unit.Warrior;

public class MapForegroundElementSaver {

  public static String save(Resource e) {
    StringBuilder sb = new StringBuilder();
    appendStandardInfos('R', sb, e);
    appendSemicolon(sb);
    sb.append(CharacterTranslator.encodeResourceKind(e.getKind()));
    appendSemicolon(sb);
    sb.append(e.getCurrentValue());
    appendSemicolon(sb);
    sb.append(e.getMaxValue());
    appendSemicolon(sb);
    sb.append(e.getPeriod());
    appendSemicolon(sb);
    sb.append(e.getAddition());
    appendSemicolon(sb);
    if (e.getBehaviour() == Resource.NON_RELOADABLE) {
      sb.append(1);
    } else {
      sb.append(0);
    }
    return sb.toString();
  }

  public static String save(SalesEntity e) {
    return "";
  }

  public static String save(Hero e) {
    return "";
  }

  public static String save(Warrior e) {
    return "";
  }

  public static String save(Monster e) {
    return "";
  }

  public static String save(Castle e) {
    return "";
  }

  private static void appendStandardInfos(char elementChar, StringBuilder sb,
      MapForegroundElement element) {
    sb.append(elementChar);
    appendSemicolon(sb);
    appendLocation(sb, element);
    appendSemicolon(sb);
    appendPlayer(sb, element);
  }

  private static void appendPlayer(StringBuilder sb,
      MapForegroundElement element) {
    if (element instanceof FightableContainer) {
      sb.append(((FightableContainer) element).getPlayer());
    } else {
      sb.append(Player.AI.getId());
    }
  }

  private static void appendLocation(StringBuilder sb,
      MapForegroundElement element) {
    Location l = CoreEngine.map().getLocationForMapForegroundElement(element);
    if (l != null) {
      sb.append(l.getX());
      appendSemicolon(sb);
      sb.append(l.getY());
    }
  }

  private static void appendSemicolon(StringBuilder sb) {
    sb.append(";");
  }
}
