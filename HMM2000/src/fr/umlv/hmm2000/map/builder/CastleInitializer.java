package fr.umlv.hmm2000.map.builder;

import java.io.LineNumberReader;

import fr.umlv.hmm2000.building.Castle;
import fr.umlv.hmm2000.engine.Player;

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
        return new Castle(Player.AI, Translator.decodeProfile(data[0].charAt(0)));
      } catch (IndexOutOfBoundsException e) {
      } catch (NumberFormatException e) {
        new IllegalArgumentException("Syntax error on line "
            + lnr.getLineNumber() + ".", e);
      }
    }
    return null;
  }



}
