package fr.umlv.hmm2000.map.builder;

import java.io.IOException;
import java.io.LineNumberReader;

import fr.umlv.hmm2000.salesentity.SalesEntity;
import fr.umlv.hmm2000.salesentity.SalesEntity.SalesEntityEnum;
import fr.umlv.hmm2000.unit.Fightable;
import fr.umlv.hmm2000.unit.UnitFactory;

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
          decodeSellable(sales, s.split(";"));
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

  /**
   * Decodes and add an item to the sales entity from a data array.
   * 
   * @param sales
   *            the sales entity.
   * @param data
   *            the data array.
   */
  private void decodeSellable(SalesEntity sales, String[] data) {
    switch (sales.getType()) {
    case MERCHANT:
      decodeMarchantItem(sales, data);
      break;
    case BARRACKS:
      decodeBarracksItem(sales, data);
      break;
    default:
      break;
    }
  }

  /**
   * Decodes and add an item to the merchant from a data array.
   * 
   * @param sales
   *            the sales entity.
   * @param data
   *            the data array.
   */
  private void decodeMarchantItem(SalesEntity sales, String[] data) {
    if (data.length >= 2) {
      try {
        int q = Integer.parseInt(data[1]);
        sales.addProduct(Translator.decodeSpell(data[0].charAt(0)), q);
      } catch (NumberFormatException e) {
      } catch (IndexOutOfBoundsException e) {
        // do nothing
      }
    }
  }

  /**
   * Decodes and add an item to the barracks from a data array.
   * 
   * @param sales
   *            the sales entity.
   * @param data
   *            the data array.
   */
  private void decodeBarracksItem(SalesEntity sales, String[] data) {
    if (data.length >= 3) {
      try {
        int q = Integer.parseInt(data[1]);
        Fightable w = UnitFactory.createWarrior(Translator
            .decodeProfile(data[0].charAt(0)), Translator.decodeLevel(data[1]
            .charAt(0)));
        sales.addProduct(w, q);
      } catch (NumberFormatException e) {
      } catch (IndexOutOfBoundsException e) {
        // do nothing
      }
    }
  }

}
