package fr.umlv.hmm2000.salesentity;

import fr.umlv.hmm2000.salesentity.SalesEntity.SalesEntityEnum;
import fr.umlv.hmm2000.util.Pair;

public class SalesEntityFactory {

  public static SalesEntity createSalesEntity(SalesEntityEnum type,
      Pair<Sellable, Integer>... items) {
       
    SalesEntity salesEntity = new SalesEntity(type);
    for (Pair<Sellable, Integer> pair : items) {
      salesEntity.addProduct(pair.getFirstElement(), pair.getSecondElement());
    }
    return salesEntity;
  }

}
