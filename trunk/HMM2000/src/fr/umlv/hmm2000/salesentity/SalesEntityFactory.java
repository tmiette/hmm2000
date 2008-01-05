package fr.umlv.hmm2000.salesentity;

import fr.umlv.hmm2000.salesentity.SalesEntity.SalesEntityEnum;
import fr.umlv.hmm2000.util.Pair;

/**
 * Factory of the sales entities.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class SalesEntityFactory {

  /**
   * Creates a sales entity.
   * 
   * @param type
   *            type of the entity.
   * @param items
   *            list of the items that the entity sales.
   * @return the sales entity.
   */
  public static SalesEntity createSalesEntity(SalesEntityEnum type,
      Pair<Sellable, Integer>... items) {
    SalesEntity salesEntity = new SalesEntity(type);
    for (Pair<Sellable, Integer> pair : items) {
      salesEntity.addProduct(pair.getFirstElement(), pair.getSecondElement());
    }
    return salesEntity;
  }

}
