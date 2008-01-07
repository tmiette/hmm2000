package fr.umlv.hmm2000.map.builder;

import java.util.Iterator;
import java.util.Map.Entry;

import fr.umlv.hmm2000.building.Castle;
import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.Player;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.resource.Resource;
import fr.umlv.hmm2000.salesentity.SalesEntity;
import fr.umlv.hmm2000.salesentity.Sellable;
import fr.umlv.hmm2000.salesentity.spell.Spell;
import fr.umlv.hmm2000.unit.Fightable;
import fr.umlv.hmm2000.unit.FightableContainer;
import fr.umlv.hmm2000.unit.Hero;
import fr.umlv.hmm2000.unit.Monster;
import fr.umlv.hmm2000.unit.UnitFactory;
import fr.umlv.hmm2000.unit.Warrior;
import fr.umlv.hmm2000.unit.profile.Level;
import fr.umlv.hmm2000.unit.profile.WarriorProfile;
import fr.umlv.hmm2000.util.Pair;

/**
 * This class enables to translate map foreground element in a saved string to
 * format the saved map file.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class MapForegroundElementSaver {

  /**
   * Returns the saved string for a resource.
   * 
   * @param e
   *            the foreground element.
   * @return the saved string for a resource.
   */
  public static String save(Resource e) {
    StringBuilder sb = new StringBuilder();
    appendStandardInfos('R', sb, e);
    appendFirstLevelSeparator(sb);
    sb.append(CharacterTranslator.encodeResourceKind(e.getKind()));
    appendFirstLevelSeparator(sb);
    sb.append(e.getCurrentValue());
    appendFirstLevelSeparator(sb);
    sb.append(e.getMaxValue());
    appendFirstLevelSeparator(sb);
    sb.append(e.getPeriod());
    appendFirstLevelSeparator(sb);
    sb.append(e.getAddition());
    appendFirstLevelSeparator(sb);
    if (e.getBehaviour() == Resource.NON_RELOADABLE) {
      sb.append(1);
    } else {
      sb.append(0);
    }
    return sb.toString();
  }

  /**
   * Returns the saved string for a sales entity.
   * 
   * @param e
   *            the foreground element.
   * @return the saved string for a sales entity.
   */
  public static String save(SalesEntity e) {
    StringBuilder sb = new StringBuilder();
    appendStandardInfos('S', sb, e);
    appendFirstLevelSeparator(sb);
    sb.append(CharacterTranslator.encodeSalesEntity(e.getType()));
    if (e.getItems().size() > 0) {
      appendFirstLevelSeparator(sb);
      switch (e.getType()) {
      case MERCHANT:
        for (Iterator<Pair<Sellable, Integer>> it = SalesEntity
            .createItemsList(e.getItems()).iterator(); it.hasNext();) {
          Pair<Sellable, Integer> pair = it.next();
          if (pair.getFirstElement().getPrice() != null) {
            sb.append(CharacterTranslator.encodeSpell((Spell) pair
                .getFirstElement()));
            appendSecondLevelSeparator(sb);
            sb.append(pair.getSecondElement());
            if (it.hasNext()) {
              appendFirstLevelSeparator(sb);
            }
          }
        }

        break;
      case BARRACKS:
        for (Iterator<Pair<Sellable, Integer>> it = SalesEntity
            .createItemsList(e.getItems()).iterator(); it.hasNext();) {
          Pair<Sellable, Integer> pair = it.next();
          if (pair.getFirstElement().getPrice() != null) {
            Warrior w = (Warrior) pair.getFirstElement();
            Pair<WarriorProfile, Level> pair2 = UnitFactory
                .findWarriorProfile(w);
            sb.append(CharacterTranslator.encodeWarriorProfile(pair2
                .getFirstElement()));
            appendSecondLevelSeparator(sb);
            sb
                .append(CharacterTranslator.encodeLevel(pair2
                    .getSecondElement()));
            appendSecondLevelSeparator(sb);
            sb.append(pair.getSecondElement());
            if (it.hasNext()) {
              appendFirstLevelSeparator(sb);
            }
          }
        }
        break;
      default:
        break;
      }
    }
    return sb.toString();
  }

  /**
   * Returns the saved string for a hero.
   * 
   * @param e
   *            the foreground element.
   * @return the saved string for a hero.
   */
  public static String save(Hero e) {
    StringBuilder sb = new StringBuilder();
    appendStandardInfos('H', sb, e);
    appendFirstLevelSeparator(sb);
    sb.append(CharacterTranslator.encodeHeroProfile(UnitFactory
        .findHeroProfile(e)));
    appendFirstLevelSeparator(sb);
    for (Iterator<Fightable> it = e.getTroop().iterator(); it.hasNext();) {
      Warrior w = (Warrior) it.next();
      Pair<WarriorProfile, Level> pair = UnitFactory.findWarriorProfile(w);
      sb.append(CharacterTranslator
          .encodeWarriorProfile(pair.getFirstElement()));
      appendSecondLevelSeparator(sb);
      sb.append(CharacterTranslator.encodeLevel(pair.getSecondElement()));
      if (it.hasNext()) {
        appendFirstLevelSeparator(sb);
      }
    }
    return sb.toString();
  }

  /**
   * Returns the saved string for a warrior.
   * 
   * @param e
   *            the foreground element.
   * @return the saved string for a warrior.
   */
  public static String save(Warrior e) {
    // a warrior cannot be on the world map
    throw new UnsupportedOperationException();
  }

  /**
   * Returns the saved string for a monster.
   * 
   * @param e
   *            the foreground element.
   * @return the saved string for a monster.
   */
  public static String save(Monster e) {
    StringBuilder sb = new StringBuilder();
    appendStandardInfos('M', sb, e);
    appendFirstLevelSeparator(sb);
    sb.append(CharacterTranslator.encodeMonsterProfile(UnitFactory
        .findMonsterProfile(e)));
    appendFirstLevelSeparator(sb);
    for (Iterator<Fightable> it = e.getTroop().iterator(); it.hasNext();) {
      Warrior w = (Warrior) it.next();
      Pair<WarriorProfile, Level> pair = UnitFactory.findWarriorProfile(w);
      sb.append(CharacterTranslator
          .encodeWarriorProfile(pair.getFirstElement()));
      appendSecondLevelSeparator(sb);
      sb.append(CharacterTranslator.encodeLevel(pair.getSecondElement()));
      if (it.hasNext()) {
        appendFirstLevelSeparator(sb);
      }
    }
    return sb.toString();
  }

  /**
   * Returns the saved string for a castle.
   * 
   * @param e
   *            the foreground element.
   * @return the saved string for a castle.
   */
  public static String save(Castle e) {
    StringBuilder sb = new StringBuilder();
    appendStandardInfos('C', sb, e);
    appendFirstLevelSeparator(sb);
    sb.append(CharacterTranslator.encodeWarriorProfile(e.getDefaultFactory()));
    appendFirstLevelSeparator(sb);

    for (Iterator<Entry<WarriorProfile, Level>> it = e.getFactory().entrySet()
        .iterator(); it.hasNext();) {
      Entry<WarriorProfile, Level> entry = it.next();
      sb.append(CharacterTranslator.encodeWarriorProfile(entry.getKey()));
      appendThirdLevelSeparator(sb);
      sb.append(CharacterTranslator.encodeLevel(entry.getValue()));
      if (it.hasNext()) {
        appendSecondLevelSeparator(sb);
      }
    }

    for (Iterator<Fightable> it = e.getTroop().iterator(); it.hasNext();) {
      Warrior w = (Warrior) it.next();
      Pair<WarriorProfile, Level> pair = UnitFactory.findWarriorProfile(w);
      sb.append(CharacterTranslator
          .encodeWarriorProfile(pair.getFirstElement()));
      appendThirdLevelSeparator(sb);
      sb.append(CharacterTranslator.encodeLevel(pair.getSecondElement()));
      if (it.hasNext()) {
        appendSecondLevelSeparator(sb);
      }
    }
    return sb.toString();
  }

  /**
   * Write the standard information for a map foreground element in a saved
   * string. The informations written are the location and the player of the
   * element.
   * 
   * @param elementChar
   *            char to encode the element.
   * @param sb
   *            the save string.
   * @param element
   *            the foreground element.
   */
  private static void appendStandardInfos(char elementChar, StringBuilder sb,
      MapForegroundElement element) {
    sb.append(elementChar);
    appendFirstLevelSeparator(sb);
    appendLocation(sb, element);
    appendFirstLevelSeparator(sb);
    appendPlayer(sb, element);
  }

  /**
   * Write the player informations for a foreground element in a saved string.
   * 
   * @param sb
   *            the save string.
   * @param element
   *            the foreground element.
   */
  private static void appendPlayer(StringBuilder sb,
      MapForegroundElement element) {
    if (element instanceof FightableContainer) {
      sb.append(((FightableContainer) element).getPlayer().getId());
    } else {
      sb.append(Player.AI.getId());
    }
  }

  /**
   * Write the location informations for a foreground element in a saved string.
   * 
   * @param sb
   *            the save string.
   * @param element
   *            the foreground element.
   */
  private static void appendLocation(StringBuilder sb,
      MapForegroundElement element) {
    Location l = CoreEngine.map().getLocationForMapForegroundElement(element);
    if (l != null) {
      sb.append(l.getX());
      appendFirstLevelSeparator(sb);
      sb.append(l.getY());
    }
  }

  /**
   * Write a semicolon separator in a saved string.
   * 
   * @param sb
   *            the save string.
   */
  private static void appendFirstLevelSeparator(StringBuilder sb) {
    sb.append(";");
  }

  /**
   * Write a comma separator in a saved string.
   * 
   * @param sb
   *            the save string.
   */
  private static void appendSecondLevelSeparator(StringBuilder sb) {
    sb.append(",");
  }

  /**
   * Write a dot separator in a saved string.
   * 
   * @param sb
   *            the save string.
   */
  private static void appendThirdLevelSeparator(StringBuilder sb) {
    sb.append(":");
  }

}
