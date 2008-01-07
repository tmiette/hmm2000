package fr.umlv.hmm2000.map.builder;

import fr.umlv.hmm2000.map.element.MapBackgroundEnum;
import fr.umlv.hmm2000.resource.Resource.Kind;
import fr.umlv.hmm2000.salesentity.SalesEntity.SalesEntityEnum;
import fr.umlv.hmm2000.salesentity.spell.Spell;
import fr.umlv.hmm2000.unit.profile.HeroProfile;
import fr.umlv.hmm2000.unit.profile.Level;
import fr.umlv.hmm2000.unit.profile.MonsterProfile;
import fr.umlv.hmm2000.unit.profile.WarriorProfile;

/**
 * This class enables to translate some characters to java object using during
 * the parsing of a map file.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class CharacterTranslator {

  /**
   * Translates a character to a map background element.
   * 
   * @param c
   *            the character.
   * @return the map background element.
   */
  protected static MapBackgroundEnum decodeMapBackgroundEnum(char c) {

    switch (c) {
    case 'M':
      return MapBackgroundEnum.MOUNTAIN;
    case 'P':
      return MapBackgroundEnum.PATH;
    case 'L':
      return MapBackgroundEnum.PLAIN;
    case 'T':
      return MapBackgroundEnum.TREE;
    case 'W':
      return MapBackgroundEnum.WATER;
    default:
      return MapBackgroundEnum.PLAIN;
    }
  }

  /**
   * Translates a map background element to a character.
   * 
   * @param e
   *            the map background element.
   * @return the character.
   */
  protected static char encodeMapBackgroundEnum(MapBackgroundEnum e) {
    switch (e) {
    case MOUNTAIN:
      return 'M';
    case PATH:
      return 'P';
    case PLAIN:
      return 'L';
    case TREE:
      return 'T';
    case WATER:
      return 'W';
    default:
      return 'P';
    }
  }

  /**
   * Translates a character to a resource kind.
   * 
   * @param c
   *            the character.
   * @return the resource kind.
   */
  protected static Kind decodeResourceKind(char c) {
    switch (c) {
    case 'G':
      return Kind.GOLD;
    default:
      return Kind.GOLD;
    }
  }

  /**
   * Translates a resource kind to a character.
   * 
   * @param e
   *            the resource kind.
   * @return the character.
   */
  protected static char encodeResourceKind(Kind e) {
    switch (e) {
    case GOLD:
      return 'G';
    default:
      return 'G';
    }
  }

  /**
   * Translates a character to a warrior profile.
   * 
   * @param c
   *            the character.
   * @return the warrior profile.
   */
  protected static WarriorProfile decodeWarriorProfile(char c) {
    switch (c) {
    case 'G':
      return WarriorProfile.GRUNT;
    case 'W':
      return WarriorProfile.WIZZARD;
    case 'F':
      return WarriorProfile.FLIGHT;
    case 'P':
      return WarriorProfile.PIRATE;
    case 'V':
      return WarriorProfile.VAMPIRE;
    case 'D':
      return WarriorProfile.DEAMON;
    default:
      return WarriorProfile.GRUNT;
    }
  }

  /**
   * Translates a warrior profile to a character.
   * 
   * @param e
   *            the warrior profile.
   * @return the character.
   */
  protected static char encodeWarriorProfile(WarriorProfile e) {
    switch (e) {
    case GRUNT:
      return 'G';
    case WIZZARD:
      return 'W';
    case FLIGHT:
      return 'F';
    case PIRATE:
      return 'P';
    case VAMPIRE:
      return 'V';
    case DEAMON:
      return 'D';
    default:
      return 'G';
    }
  }

  /**
   * Translates a character to a spell.
   * 
   * @param c
   *            the character.
   * @return the spell.
   */
  protected static Spell decodeSpell(char c) {
    switch (c) {
    case 'T':
      return Spell.TELEPORTATION;
    case 'D':
      return Spell.OBSTACLE_DESTRUCTION;
    case 'C':
      return Spell.OBSTACLE_CONSTRUCTION;
    default:
      return Spell.TELEPORTATION;
    }
  }

  /**
   * Translates a spell to a character.
   * 
   * @param e
   *            the spell.
   * @return the character.
   */
  protected static char encodeSpell(Spell e) {
    switch (e) {
    case TELEPORTATION:
      return 'T';
    case OBSTACLE_DESTRUCTION:
      return 'D';
    case OBSTACLE_CONSTRUCTION:
      return 'C';
    default:
      return 'T';
    }
  }

  /**
   * Translates a character to a unit level.
   * 
   * @param c
   *            the character.
   * @return the unit level.
   */
  protected static Level decodeLevel(char c) {
    switch (c) {
    case '1':
      return Level.LEVEL_1;
    case '2':
      return Level.LEVEL_2;
    case '3':
      return Level.LEVEL_3;
    default:
      return Level.LEVEL_1;
    }
  }

  /**
   * Translates a level to a character.
   * 
   * @param e
   *            the level.
   * @return the character.
   */
  protected static char encodeLevel(Level e) {
    switch (e) {
    case LEVEL_1:
      return '1';
    case LEVEL_2:
      return '2';
    case LEVEL_3:
      return '3';
    default:
      return '1';
    }
  }

  /**
   * Translates a character to a monster profile.
   * 
   * @param c
   *            the character.
   * @return the monster profile.
   */
  protected static MonsterProfile decodeMonsterProfile(char c) {
    switch (c) {
    case 'T':
      return MonsterProfile.TROLL;
    case 'M':
      return MonsterProfile.MUMMY;
    case 'Z':
      return MonsterProfile.ZOMBIE;
    default:
      return MonsterProfile.TROLL;
    }
  }

  /**
   * Translates a monster profile to a character.
   * 
   * @param e
   *            the monster profile.
   * @return the character.
   */
  protected static char encodeMonsterProfile(MonsterProfile e) {
    switch (e) {
    case TROLL:
      return 'T';
    case MUMMY:
      return 'M';
    case ZOMBIE:
      return 'Z';
    default:
      return 'T';
    }
  }

  /**
   * Translates a character to a sales entity kind.
   * 
   * @param c
   *            the character.
   * @return the sales entity kind.
   */
  protected static SalesEntityEnum decodeSalesEntity(char c) {
    switch (c) {
    case 'M':
      return SalesEntityEnum.MERCHANT;
    case 'B':
      return SalesEntityEnum.BARRACKS;
    default:
      return SalesEntityEnum.MERCHANT;
    }
  }

  /**
   * Translates a sales entity kind to a character.
   * 
   * @param e
   *            the sales entity kind.
   * @return the character.
   */
  protected static char encodeSalesEntity(SalesEntityEnum e) {
    switch (e) {
    case MERCHANT:
      return 'M';
    case BARRACKS:
      return 'B';
    default:
      return 'M';
    }
  }

  /**
   * Translates a character to a hero profile.
   * 
   * @param c
   *            the character.
   * @return the hero profile.
   */
  protected static HeroProfile decodeHeroProfile(char c) {
    switch (c) {
    case 'L':
      return HeroProfile.LORD_OF_WAR;
    case 'A':
      return HeroProfile.ARCHER;
    case 'S':
      return HeroProfile.SORCERER;
    default:
      return HeroProfile.LORD_OF_WAR;
    }
  }

  /**
   * Translates a hero profile to a character.
   * 
   * @param e
   *            the hero profile.
   * @return the character.
   */
  protected static char encodeHeroProfile(HeroProfile e) {
    switch (e) {
    case LORD_OF_WAR:
      return 'L';
    case ARCHER:
      return 'A';
    case SORCERER:
      return 'S';
    default:
      return 'L';
    }
  }

}
