package fr.umlv.hmm2000.map.builder;

import fr.umlv.hmm2000.resource.Resource.Kind;
import fr.umlv.hmm2000.salesentity.spell.Spell;
import fr.umlv.hmm2000.unit.profil.Level;
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
  protected static Kind decodeKind(char c) {
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
  protected static ProfilWarrior decodeProfile(char c) {
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

}
