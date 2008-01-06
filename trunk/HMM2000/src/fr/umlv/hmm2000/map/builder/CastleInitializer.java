package fr.umlv.hmm2000.map.builder;

import java.io.LineNumberReader;

import fr.umlv.hmm2000.building.Castle;
import fr.umlv.hmm2000.engine.Player;
import fr.umlv.hmm2000.unit.profil.ProfilWarrior;

/**
 * This class defines an initializer of castles elements.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class CastleInitializer implements MapForegroundElementInitializer {

  @Override
  public Castle initialize(LineNumberReader lnr, String[] data) {
    if (data.length >= 1) {
      try {
        return new Castle(Player.AI, decodeProfile(data[0].charAt(0)));
      } catch (IndexOutOfBoundsException e) {
      } catch (NumberFormatException e) {
        new IllegalArgumentException("Syntax error on line "
            + lnr.getLineNumber() + ".", e);
      }
    }
    return null;
  }

  /**
   * Translates a character to a profile.
   * 
   * @param c
   *            the character.
   * @return the profile.
   */
  private ProfilWarrior decodeProfile(char c) {
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

}
