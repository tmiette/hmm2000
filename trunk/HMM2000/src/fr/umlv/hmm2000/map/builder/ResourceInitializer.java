package fr.umlv.hmm2000.map.builder;

import java.io.LineNumberReader;

import fr.umlv.hmm2000.resource.Resource;

/**
 * This class defines an initializer of resources elements.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class ResourceInitializer implements MapForegroundElementInitializer {

  @Override
  public Resource initialize(LineNumberReader lnr, String[] data) {
    if (data.length >= 6) {
      try {
        return new Resource(CharacterTranslator.decodeResourceKind(data[0].charAt(0)), Integer
            .parseInt(data[1]), Integer.parseInt(data[2]), Integer
            .parseInt(data[3]), Integer.parseInt(data[4]),
            decodeBehaviour(Integer.parseInt(data[5])));
      } catch (IndexOutOfBoundsException e) {
      } catch (NumberFormatException e) {
        new IllegalArgumentException("Syntax error on line "
            + lnr.getLineNumber() + ".", e);
      }
    }
    return null;
  }

  /**
   * Translates an integer to a resource behaviour.
   * 
   * @param b
   *            the integer.
   * @return the resource behaviour.
   */
  private int decodeBehaviour(int b) {
    if (b < 0 && b > 1) {
      return Resource.NON_RELOADABLE;
    } else {
      return b;
    }
  }
}
