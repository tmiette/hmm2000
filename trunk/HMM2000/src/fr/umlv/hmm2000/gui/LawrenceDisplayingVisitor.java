package fr.umlv.hmm2000.gui;

import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.resource.Resource;
import fr.umlv.hmm2000.salesentity.SalesEntity;
import fr.umlv.hmm2000.warrior.Hero;
import fr.umlv.hmm2000.warrior.Monster;
import fr.umlv.hmm2000.warrior.Warrior;

public class LawrenceDisplayingVisitor implements UIDisplayingVisitor {

  @Override
  public void visit(Hero hero) {
    System.out.println(hero.getName());
    System.out.println(hero.getPlayer().getResources());
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
    System.out.println(warrior.getLabel() + "health : " + warrior.getHealth());
  }

  @Override
  public void visit(Monster monster) {
    System.out.println("monster");
  }
}
