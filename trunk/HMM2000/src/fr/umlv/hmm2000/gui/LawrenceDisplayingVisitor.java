package fr.umlv.hmm2000.gui;

import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.resource.Resource;
import fr.umlv.hmm2000.salesentity.SalesEntity;
import fr.umlv.hmm2000.warrior.Hero;
import fr.umlv.hmm2000.warrior.Warrior;

public class LawrenceDisplayingVisitor implements UIDisplayingVisitor {

  @Override
  public void visit(Hero heroe) {
    System.out.println();
    System.out.println(heroe.getPlayer().getResources());
  }

  @Override
  public void visit(Resource resource) {
    System.out.println(resource.getKind() + " :" + resource.getCurrentValue()
        + "/" + resource.getMaxValue());
  }

  @Override
  public void visit(SalesEntity salesEntity) {
    System.out.println("marchant");
    System.out.println(salesEntity.getItems());
  }

  @Override
  public void visit(Warrior warrior) {
    System.out.println(warrior.getProfilName() + "health : "
        + warrior.getHealth());
  }
}
