package fr.umlv.hmm2000.gui;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.engine.guiinterface.Sprite;
import fr.umlv.hmm2000.engine.guiinterface.UIChoicesManager;
import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.util.Pair;
import fr.umlv.lawrence.Application;
import fr.umlv.lawrence.CursorListener;
import fr.umlv.lawrence.DefaultGridModel;
import fr.umlv.lawrence.GridPane;
import fr.umlv.lawrence.InputListener;
import fr.umlv.lawrence.Key;
import fr.umlv.lawrence.svg.SVGImageProvider;

public class LawrenceUIEngine implements HMMUserInterface {

  private DefaultGridModel<Sprite> model;

  private int originalWidth;

  private int originalHeight;

  private int currentWidth;

  private int currentHeight;

  private LawrenceDisplayingVisitor displayingVisitor;

  private LawrenceChoicesManager choicesManager;

  private SVGImageProvider<Sprite> provider;

  private GridPane<Sprite> pane;

  private fr.umlv.lawrence.Location pointerLocation;

  private Map map;
  
  private final JFrame frame;

  private final InputListener inputListener = new InputListener() {

    public void mouseClicked(int x, int y, int button) {
      if (LawrenceUIEngine.this.areInCurrentMap(x, y)) {
        CoreEngine.locationClicked(y, x, button);
      }
    }

    public void keyTyped(int x, int y, Key keyCode) {
      switch (keyCode) {
      case SPACE:
        CoreEngine.nextDay();
        break;
      case P:
        CoreEngine.manageBattlePosition();
        break;
      case C:
        CoreEngine.manageCastle();
        break;
      case ESCAPE:
        CoreEngine.backToWorldMap();
        break;
      default:
        break;
      }
    }
  };

  private final CursorListener pointerCursorListener = new CursorListener() {
    public void mouseExited(int x, int y) {
      // nothing
    }

    public void mouseEntered(int x, int y) {
      if (LawrenceUIEngine.this.areInCurrentMap(x, y)) {
        fr.umlv.lawrence.Location location = new fr.umlv.lawrence.Location(x, y);
        LawrenceUIEngine.this.setPointer(location);
      }
    }
  };

  private void initProvider() {
    this.provider = new SVGImageProvider<Sprite>();
    this.provider.setDPI(93);
    this.registerImages();
  }

  private void initGridPane() {
    this.pane = new GridPane<Sprite>(this.model, this.provider, 40, 40);
  }

  private void addListeners() {
    this.pane.addInputListener(this.inputListener);
    this.pointerLocation = new fr.umlv.lawrence.Location(0, 0);
    this.pane.addCursorListener(this.pointerCursorListener);
  }

  private boolean areInCurrentMap(int x, int y) {
    if (x <= this.currentWidth - 1 && y <= this.currentHeight - 1) {
      return true;
    }
    return false;
  }

  public LawrenceUIEngine(JFrame frame) {
    this.frame = frame;
    this.map = Map.defaultMap;
    this.model = new DefaultGridModel<Sprite>(this.map.getWidth(), this.map
        .getHeight());
    this.originalWidth = this.map.getWidth();
    this.originalHeight = this.map.getHeight();
    this.currentWidth = this.originalWidth;
    this.currentHeight = this.originalHeight;
    this.initGrid();
    this.initProvider();
    this.initGridPane();
    Application
        .display(this.pane, null, true, 0, 0, 0, new Rectangle(800, 600));
  }

  @Override
  public void drawMap(Map map) {
    if (this.map == Map.defaultMap) {
      this.addListeners();
    }
    this.map = map;
    this.currentWidth = this.map.getWidth();
    this.currentHeight = this.map.getHeight();
    this.initGrid();
  }

  @Override
  public void eraseMap() {
    ArrayList<Sprite> list = new ArrayList<Sprite>();
    list.add(Sprite.DEFAULT);
    for (int x = 0; x < this.currentWidth; x++) {
      for (int y = 0; y < this.currentHeight; y++) {
        this.model.setDeffered(x, y, list);
      }
    }
    this.setPointer(new fr.umlv.lawrence.Location(0, 0));
    this.model.swap();
  }

  private void registerImages() {
    for (Sprite s : Sprite.values()) {
      s.register(this.provider);
    }
  }

  private void initGrid() {
    for (int x = 0; x < this.currentWidth; x++) {
      for (int y = 0; y < this.currentHeight; y++) {
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
      this.displayingVisitor = new LawrenceDisplayingVisitor(this.frame);
    }
    return this.displayingVisitor;
  }

  @Override
  public void displayMessage(String message) {
    System.out.println(message);
  }

  @Override
  public void displaySprite(Location location, Sprite sprite) {
    this.model.addDeffered(location.getY(), location.getX(), sprite);
    this.model.swap();
  }

  @Override
  public void eraseSprite(Location location, Sprite sprite) {
    this.model.removeDeffered(location.getY(), location.getX(), sprite);
    this.model.swap();
  }

  @Override
  public void displaySprites(List<Pair<Location, Sprite>> sprites) {
    for (Pair<Location, Sprite> pair : sprites) {
      this.model.addDeffered(pair.getFirstElement().getY(), pair
          .getFirstElement().getX(), pair.getSecondElement());
    }
    this.model.swap();
  }

  @Override
  public void eraseSprites(List<Pair<Location, Sprite>> sprites) {
    for (Pair<Location, Sprite> pair : sprites) {
      this.model.removeDeffered(pair.getFirstElement().getY(), pair
          .getFirstElement().getX(), pair.getSecondElement());
    }
    this.model.swap();
  }

}
