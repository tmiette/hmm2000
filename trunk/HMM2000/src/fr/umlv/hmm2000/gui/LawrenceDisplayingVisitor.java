package fr.umlv.hmm2000.gui;

import fr.umlv.hmm2000.HeroeToDelete;
import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.resource.Resource;
import fr.umlv.hmm2000.salesentity.SalesEntity;

public class LawrenceDisplayingVisitor implements UIDisplayingVisitor {

  @Override
  public void visit(HeroeToDelete heroe) {
    System.out.println(heroe.getName());
    System.out.println(heroe.getPlayer().getResources());
  }

  @Override
  public void visit(Resource resource) {
    System.out.println(resource.getKind() + " :" + resource.getCurrentValue()
        + "/" + resource.getMaxValue());
  }

  @Override
  public void visit(SalesEntity salesEntity) {
    System.out.println("Caserne");
    System.out.println(salesEntity.getItems());
  }
}
