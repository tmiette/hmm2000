package fr.umlv.hmm2000.map.builder;

import java.io.LineNumberReader;

import fr.umlv.hmm2000.salesentity.SalesEntity;
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
        SalesEntity sales = new SalesEntity(CharacterTranslator.decodeSalesEntity(data[0]
            .charAt(0)));
        for (int i = 1; i < data.length; i++) {
          decodeSellable(sales, data[i].split(","));
        }
        return sales;
      } catch (IndexOutOfBoundsException e) {
      } catch (NumberFormatException e) {
        new IllegalArgumentException("Syntax error on line "
            + lnr.getLineNumber() + ".", e);
      }
    }
    return null;
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
        sales.addProduct(CharacterTranslator.decodeSpell(data[0].charAt(0)), q);
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
        int q = Integer.parseInt(data[2]);
        Fightable w = UnitFactory.createWarrior(CharacterTranslator
            .decodeWarriorProfile(data[0].charAt(0)), CharacterTranslator
            .decodeLevel(data[1].charAt(0)));
        sales.addProduct(w, q);
      } catch (NumberFormatException e) {
      } catch (IndexOutOfBoundsException e) {
        // do nothing
      }
    }
  }

}
