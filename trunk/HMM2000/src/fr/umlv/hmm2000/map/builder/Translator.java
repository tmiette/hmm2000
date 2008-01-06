package fr.umlv.hmm2000.map.builder;

import fr.umlv.hmm2000.resource.Resource.Kind;
import fr.umlv.hmm2000.salesentity.SalesEntity.SalesEntityEnum;
import fr.umlv.hmm2000.salesentity.spell.Spell;
import fr.umlv.hmm2000.unit.profil.Level;
import fr.umlv.hmm2000.unit.profil.ProfilHero;
import fr.umlv.hmm2000.unit.profil.ProfilMonster;
import fr.umlv.hmm2000.unit.profil.ProfilWarrior;

/**
 * This class enables to translate some characters to java object using during
 * the parsing of a map file.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class Translator {

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
   * Translates a character to a profile.
   * 
   * @param c
   *            the character.
   * @return the profile.
   */
  protected static ProfilWarrior decodeWarriorProfile(char c) {
    switch (c) {
    case 'G':
      return ProfilWarrior.GRUNT;
    case 'W':
      return ProfilWarrior.WIZZARD;
    case 'F':
      return ProfilWarrior.FLIGHT;
    default:
      return ProfilWarrior.GRUNT;
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
   * Translates a character to a monster profile.
   * 
   * @param c
   *            the character.
   * @return the monster profile.
   */
  protected static ProfilMonster decodeMonsterProfile(char c) {
    switch (c) {
    case 'T':
      return ProfilMonster.TROLL;
    case 'M':
      return ProfilMonster.MUMMY;
    case 'Z':
      return ProfilMonster.ZOMBIE;
    default:
      return ProfilMonster.TROLL;
    }
  }
  
  /**
   * Translates a character to a sales entity kind.
   * 
   * @param c
   *            the character.
   * @return the sales entity kind.
   */
  public static SalesEntityEnum decodeSalesEntity(char c) {
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
   * Translates a character to a hero profile.
   * 
   * @param c
   *            the character.
   * @return the hero profile.
   */
  protected static ProfilHero decodeHeroProfile(char c) {
    switch (c) {
    case 'L':
      return ProfilHero.LORD_OF_WAR;
    case 'A':
      return ProfilHero.ARCHER;
    case 'S':
      return ProfilHero.SORCERER;
    default:
      return ProfilHero.LORD_OF_WAR;
    }
  }

}
