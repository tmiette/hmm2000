package fr.umlv.hmm2000.map.builder;

import java.io.IOException;
import java.io.LineNumberReader;

import fr.umlv.hmm2000.salesentity.SalesEntity;
import fr.umlv.hmm2000.salesentity.SalesEntity.SalesEntityEnum;

/**
 * This class defines an initializer of sales entities elements.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class SalesEntityInitializer implements MapForegroundElementInitializer {

  @Override
  public SalesEntity initialize(LineNumberReader lnr, String[] data) {
    if (data.length >= 1) {
      try {
        SalesEntity sales = new SalesEntity(decodeKind(data[0].charAt(0)));
        String s;
        while ((s = lnr.readLine()) != null && s.charAt(0) == '-') {

        }
        return sales;
      } catch (IndexOutOfBoundsException e) {
      } catch (IOException e) {
      } catch (NumberFormatException e) {
        new IllegalArgumentException("Syntax error on line "
            + lnr.getLineNumber() + ".", e);
      }
    }
    return null;
  }

  /**
   * Translates a character to a sales entity kind.
   * 
   * @param c
   *            the character.
   * @return the sales entity kind.
   */
  private SalesEntityEnum decodeKind(char c) {
    switch (c) {
    case 'M':
      return SalesEntityEnum.MERCHANT;
    case 'B':
      return SalesEntityEnum.BARRACKS;
    default:
      return SalesEntityEnum.MERCHANT;
    }
  }

}
