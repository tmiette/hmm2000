package fr.umlv.hmm2000.gui;

import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.resource.Resource;
import fr.umlv.hmm2000.salesentity.SalesEntity;
import fr.umlv.hmm2000.warriors.Heroe;
import fr.umlv.hmm2000.warriors.Warrior;

public class LawrenceDisplayingVisitor implements UIDisplayingVisitor {

  @Override
  public void visit(Heroe heroe) {
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

  @Override
  public void visit(Warrior warrior) {
    System.out.println("warrior");

  }
}
