package fr.umlv.hmm2000.gui;

import fr.umlv.hmm2000.building.Castle;
import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.gui.panel.PanelFactory;
import fr.umlv.hmm2000.resource.Resource;
import fr.umlv.hmm2000.salesentity.SalesEntity;
import fr.umlv.hmm2000.warrior.Hero;
import fr.umlv.hmm2000.warrior.Monster;
import fr.umlv.hmm2000.warrior.Warrior;

public class LawrenceDisplayingVisitor implements UIDisplayingVisitor {

  private final LawrenceJFrame frame;

  public LawrenceDisplayingVisitor(LawrenceJFrame frame) {
    this.frame = frame;
  }

  @Override
  public void visit(Hero hero) {
    this.frame.displayCenterPanel(PanelFactory.getHeroPanel(hero));
  }

  @Override
  public void visit(Resource resource) {
    this.frame.displayCenterPanel(PanelFactory.getResourcePanel(resource));
  }

  @Override
  public void visit(Warrior warrior) {
    this.frame.displayCenterPanel(PanelFactory.getWarriorPanel(warrior));
  }

  @Override
  public void visit(Monster monster) {
    this.frame.displayCenterPanel(PanelFactory.getMonsterPanel(monster));
  }

  @Override
  public void visit(SalesEntity salesEntity) {
    this.frame
        .displayCenterPanel(PanelFactory.getSalesEntityPanel(salesEntity));
  }

  @Override
  public void visit(Castle castle) {
    this.frame.displayCenterPanel(PanelFactory.getCastlePanel(castle));
  }
}
