package fr.umlv.hmm2000.gui;

import fr.umlv.hmm2000.engine.event.EncounterEvent;
import fr.umlv.hmm2000.engine.event.MapChangeEvent;
import fr.umlv.hmm2000.engine.event.MoveEvent;
import fr.umlv.hmm2000.engine.event.SelectionEvent;
import fr.umlv.hmm2000.engine.event.MoveEvent.Step;
import fr.umlv.hmm2000.engine.guiinterface.UIChoicesManager;
import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.engine.guiinterface.UIEventManager;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.lawrence.DefaultGridModel;

public class LawrenceEventManager implements UIEventManager {

  private final DefaultGridModel<Sprite> model;
  private final LawrenceDisplayingVisitor displayingVisitor;
  private final LawrenceChoicesManager choicesManager;

  private static void slow() {
    try {
      Thread.sleep(200);
    } catch (InterruptedException e) {
      throw new AssertionError();
    }
  }

  public LawrenceEventManager(DefaultGridModel<Sprite> model) {
    this.model = model;
    this.displayingVisitor = new LawrenceDisplayingVisitor();
    this.choicesManager = new LawrenceChoicesManager();
  }

  @Override
  public UIChoicesManager choicesManager() {
    return this.choicesManager;
  }

  @Override
  public UIDisplayingVisitor displayingVisitor() {
    return this.displayingVisitor;
  }

  @Override
  public void displayMessage(String message) {
    System.out.println(message);
  }

  @Override
  public void displaySelection(SelectionEvent event) {
    Location location = event.getLocation();
    this.model.addDeffered(location.getY(), location.getX(), Sprite.SELECTION);
    this.model.swap();
  }

  @Override
  public void eraseSelection(SelectionEvent event) {
    Location location = event.getLocation();
    this.model.removeDeffered(location.getY(), location.getX(),
        Sprite.SELECTION);
    this.model.swap();
  }

  @Override
  public void displayMoveSteps(MoveEvent event) {

    for (Step move : event.getMoves()) {
      Location l = move.getEnd();
      if (move.isRecheable()) {
        this.model.addDeffered(l.getY(), l.getX(), Sprite.RECHEABLE);
      } else {
        this.model.addDeffered(l.getY(), l.getX(), Sprite.UNRECHEABLE);
      }
    }
    this.model.swap();
  }

  @Override
  public void eraseMoveSteps(MoveEvent event) {

    for (Step move : event.getMoves()) {
      Location l = move.getEnd();
      this.model.removeDeffered(l.getY(), l.getX(), Sprite.RECHEABLE);
      this.model.removeDeffered(l.getY(), l.getX(), Sprite.UNRECHEABLE);
    }
    this.model.swap();
  }

  @Override
  public void displayStep(Step event) {
    Location start = event.getStart();
    Location end = event.getEnd();
    this.model.removeDeffered(start.getY(), start.getX(), event.getSource()
        .getSprite());
    this.model.removeDeffered(end.getY(), end.getX(), Sprite.RECHEABLE);
    this.model.removeDeffered(end.getY(), end.getX(), Sprite.UNRECHEABLE);
    this.model.addDeffered(end.getY(), end.getX(), event.getSource()
        .getSprite());
    LawrenceEventManager.slow();
    this.model.swap();
  }

  @Override
  public void eraseForegroundElement(EncounterEvent event) {
    Location l = event.getRecipientLocation();
    this.model.removeDeffered(l.getY(), l.getX(), event.getRecipient()
        .getSprite());
    this.model.swap();
  }

  @Override
  public void changeBackgroundElement(MapChangeEvent event) {
    Location l = event.getLocation();
    this.model.removeDeffered(l.getY(), l.getX(), event.getOldElement()
        .getSprite());
    this.model.addDeffered(l.getY(), l.getX(), event.getNewElement()
        .getSprite());
    this.model.swap();
  }

}
