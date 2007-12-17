package fr.umlv.hmm2000.gui;

import java.util.ArrayList;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.event.EncounterEvent;
import fr.umlv.hmm2000.engine.event.MapChangeEvent;
import fr.umlv.hmm2000.engine.event.MoveEvent;
import fr.umlv.hmm2000.engine.event.SelectionEvent;
import fr.umlv.hmm2000.engine.event.MoveEvent.Step;
import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.engine.guiinterface.UIChoicesManager;
import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.lawrence.Application;
import fr.umlv.lawrence.CursorListener;
import fr.umlv.lawrence.DefaultGridModel;
import fr.umlv.lawrence.GridPane;
import fr.umlv.lawrence.InputListener;
import fr.umlv.lawrence.Key;
import fr.umlv.lawrence.svg.SVGImageProvider;

public class LawrenceUIEngine implements HMMUserInterface {

  private DefaultGridModel<Sprite> model;

  private LawrenceDisplayingVisitor displayingVisitor;

  private LawrenceChoicesManager choicesManager;

  private SVGImageProvider<Sprite> provider;

  private GridPane<Sprite> pane;

  private fr.umlv.lawrence.Location pointerLocation;

  private Map map;

  private static void slow() {
    try {
      Thread.sleep(200);
    } catch (InterruptedException e) {
      throw new AssertionError();
    }
  }

  private final InputListener inputListener = new InputListener() {

    public void mouseClicked(int x, int y, int button) {
      CoreEngine.locationClicked(y, x, button);
    }

    public void keyTyped(int x, int y, Key keyCode) {
      if (keyCode == Key.SPACE) {
        CoreEngine.nextDay();
      } else if (keyCode == Key.P) {
        CoreEngine.manageBattlePosition();
      }
    }
  };

  private final CursorListener pointerCursorListener = new CursorListener() {
    public void mouseExited(int x, int y) {
      // nothing
    }

    public void mouseEntered(int x, int y) {
      fr.umlv.lawrence.Location location = new fr.umlv.lawrence.Location(x, y);
      LawrenceUIEngine.this.setPointer(location);
    }
  };

  private void initProvider() {
    this.provider = new SVGImageProvider<Sprite>();
    this.provider.setDPI(93);
    this.registerImages();
  }

  private void initGridPane() {
    this.pane = new GridPane<Sprite>(this.model, this.provider, 50, 50);
    this.pane.addInputListener(this.inputListener);
    this.pointerLocation = new fr.umlv.lawrence.Location(0, 0);
    this.pane.addCursorListener(this.pointerCursorListener);
  }

  @Override
  public void drawMap(Map map) {
    this.map = map;
    this.model = new DefaultGridModel<Sprite>(map.getWidth(), map.getHeight());
    this.initGrid();
    this.initProvider();
    this.initGridPane();
    Application.display(this.pane, "HMM2000 test", false, true);
  }

  @Override
  public void eraseMap() {
    
  }

  private void registerImages() {
    for (Sprite s : Sprite.values()) {
      s.register(this.provider);
    }
  }

  private void initGrid() {
    for (int x = 0; x < this.model.getWidth(); x++) {
      for (int y = 0; y < this.model.getHeight(); y++) {
        this.setCellElements(x, y);
      }
    }
    this.model.swap();
  }

  private void setCellElements(int x, int y) {
    ArrayList<Sprite> elements = new ArrayList<Sprite>();
    this.setCellMapBackgroundElements(elements, x, y);
    this.setCellMapElements(elements, x, y);
    this.model.setDeffered(x, y, elements);
  }

  private void setCellMapBackgroundElements(ArrayList<Sprite> elements, int x,
      int y) {

    elements.add(Sprite.BACKGROUND);
    elements.add(this.map.getMapBackgroundElementAtLocation(new Location(y, x))
        .getSprite());
  }

  private void setCellMapElements(ArrayList<Sprite> elements, int x, int y) {
    MapForegroundElement element = this.map
        .getMapForegroundElementAtLocation(new Location(y, x));
    if (element != null) {
      elements.add(element.getSprite());
    }
  }

  private void setPointer(fr.umlv.lawrence.Location location) {
    model.setHighligthElement(pointerLocation, null);
    pointerLocation = location;
    model.setHighligthElement(pointerLocation, Sprite.POINTER);
  }

  public LawrenceUIEngine() {
  }

  @Override
  public UIChoicesManager choicesManager() {
    if (this.choicesManager == null) {
      this.choicesManager = new LawrenceChoicesManager();
    }
    return this.choicesManager;
  }

  @Override
  public UIDisplayingVisitor displayingVisitor() {
    if (this.displayingVisitor == null) {
      this.displayingVisitor = new LawrenceDisplayingVisitor();
    }
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
    LawrenceUIEngine.slow();
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

  @Override
  public void swap(Location from, Location to) {
    MapForegroundElement fromElement = this.map
        .getMapForegroundElementAtLocation(to);
    MapForegroundElement toElement = this.map
        .getMapForegroundElementAtLocation(from);
    if (toElement != null) {
      this.model.removeDeffered(to.getY(), to.getX(), toElement.getSprite());
    }
    this.model
        .removeDeffered(from.getY(), from.getX(), fromElement.getSprite());
    this.model.addDeffered(to.getY(), to.getX(), fromElement.getSprite());
    if (toElement != null) {
      this.model.addDeffered(from.getY(), from.getX(), toElement.getSprite());
    }
  }

}
