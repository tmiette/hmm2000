package fr.umlv.hmm2000.gui;

import javax.swing.JFrame;

import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.gui.panel.PanelFactory;
import fr.umlv.hmm2000.resource.Resource;
import fr.umlv.hmm2000.salesentity.SalesEntity;
import fr.umlv.hmm2000.warrior.Hero;
import fr.umlv.hmm2000.warrior.Monster;
import fr.umlv.hmm2000.warrior.Warrior;

public class LawrenceDisplayingVisitor implements UIDisplayingVisitor {

  private final JFrame frame;

  public LawrenceDisplayingVisitor(JFrame frame) {
    this.frame = frame;
  }

  @Override
  public void visit(Hero hero) {
    this.frame.setContentPane(PanelFactory.getHeroPanel(hero));
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
    this.frame.setContentPane(PanelFactory.getWarriorPanel(warrior));
  }

  @Override
  public void visit(Monster monster) {
    this.frame.setContentPane(PanelFactory.getMonsterPanel(monster));
  }
}
