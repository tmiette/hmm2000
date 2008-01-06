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

/**
 * This class represent the lawrence user interface.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class LawrenceUIEngine implements HMMUserInterface {

  private LawrenceChoicesManager choicesManager;

  private int currentHeight;

  private int currentWidth;

  private LawrenceDisplayingVisitor displayingVisitor;

  private final JFrame frame;

  /**
   * The input listener used to manage mouse and keyboard.
   */
  private final InputListener inputListener = new InputListener() {

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

    public void mouseClicked(int x, int y, int button) {
      if (LawrenceUIEngine.this.areInCurrentMap(x, y)) {
        CoreEngine.locationClicked(y, x, button);
      }
    }
  };

  private LawrenceJFrame lawrenceFrame;

  private Map map;

  private DefaultGridModel<Sprite> model;

  private int originalHeight;

  private int originalWidth;

  private GridPane<Sprite> pane;

  /**
   * The cursor listener used to manage the mouse moves.
   */
  private final CursorListener pointerCursorListener = new CursorListener() {
    public void mouseEntered(int x, int y) {
      if (LawrenceUIEngine.this.areInCurrentMap(x, y)) {
        fr.umlv.lawrence.Location location = new fr.umlv.lawrence.Location(x, y);
        LawrenceUIEngine.this.setPointer(location);
      }
    }

    public void mouseExited(int x, int y) {
      // nothing
    }
  };

  private fr.umlv.lawrence.Location pointerLocation;

  private SVGImageProvider<Sprite> provider;

  /**
   * Constructor of the lawrence user interface.
   * 
   * @param frame
   *            the swing frame used with the standard frame.
   */
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

  /**
   * Adds the listeners to the pane.
   */
  private void addListeners() {
    this.pane.addInputListener(this.inputListener);
    this.pointerLocation = new fr.umlv.lawrence.Location(0, 0);
    this.pane.addCursorListener(this.pointerCursorListener);
  }

  /**
   * Returns if two coordinates are contained in the current map.
   * 
   * @param x
   *            the x coordinate.
   * @param y
   *            the y coordinate.
   * @return if two coordinates are contained in the current map.
   */
  private boolean areInCurrentMap(int x, int y) {
    if (x <= this.currentWidth - 1 && y <= this.currentHeight - 1) {
      return true;
    }
    return false;
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
      this.displayingVisitor = new LawrenceDisplayingVisitor(this.lawrenceFrame);
    }
    return this.displayingVisitor;
  }

  @Override
  public void displayMessage(String message, int level) {
    this.lawrenceFrame.displayMessage(message, level);
  }

  @Override
  public void displaySprite(Location location, Sprite sprite) {
    this.model.addDeffered(location.getY(), location.getX(), sprite);
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
  public void drawMap(Map map) {
    if (this.map == Map.defaultMap) {
      this.addListeners();
      this.initFrame();
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

  @Override
  public void eraseSprite(Location location, Sprite sprite) {
    this.model.removeDeffered(location.getY(), location.getX(), sprite);
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

  /**
   * Initialize the lawrence frame.
   */
  private void initFrame() {
    this.lawrenceFrame = new LawrenceJFrame(this.frame);
  }

  /**
   * Initialize the grid model with the different sprites.
   */
  private void initGrid() {
    for (int x = 0; x < this.currentWidth; x++) {
      for (int y = 0; y < this.currentHeight; y++) {
        this.setCellElements(x, y);
      }
    }
    this.model.swap();
  }

  /**
   * Initialize the grid pane.
   */
  private void initGridPane() {
    this.pane = new GridPane<Sprite>(this.model, this.provider, 40, 40);
  }

  /**
   * Initialize the SVG provider.
   */
  private void initProvider() {
    this.provider = new SVGImageProvider<Sprite>();
    this.provider.setDPI(93);
    this.registerImages();
  }

  /**
   * Register the image in the provider.
   */
  private void registerImages() {
    for (Sprite s : Sprite.values()) {
      s.register(this.provider);
    }
  }

  /**
   * Initialize a location with the different sprites.
   * 
   * @param x
   *            the x coordinate.
   * @param y
   *            the y coordinate.
   */
  private void setCellElements(int x, int y) {
    ArrayList<Sprite> elements = new ArrayList<Sprite>();
    this.setCellMapBackgroundElements(elements, x, y);
    this.setCellMapElements(elements, x, y);
    this.model.setDeffered(x, y, elements);
  }

  /**
   * Initialize a location with the different background sprites.
   * 
   * @param elements
   *            the current sprites list.
   * @param x
   *            the x coordinate.
   * @param y
   *            the y coordinate.
   */
  private void setCellMapBackgroundElements(ArrayList<Sprite> elements, int x,
      int y) {
    elements.add(Sprite.BACKGROUND);
    elements.add(this.map.getMapBackgroundElementAtLocation(new Location(y, x))
        .getSprite());
  }

  /**
   * Initialize a location with the different foregrounds sprites.
   * 
   * @param elements
   *            the current sprites list.
   * @param x
   *            the x coordinate.
   * @param y
   *            the y coordinate.
   */
  private void setCellMapElements(ArrayList<Sprite> elements, int x, int y) {
    MapForegroundElement element = this.map
        .getMapForegroundElementAtLocation(new Location(y, x));
    if (element != null) {
      elements.add(element.getSprite());
    }
  }

  /**
   * Sets the pointer.
   * 
   * @param location
   *            pointer location.
   */
  private void setPointer(fr.umlv.lawrence.Location location) {
    model.setHighligthElement(pointerLocation, null);
    pointerLocation = location;
    model.setHighligthElement(pointerLocation, Sprite.POINTER);
  }

}
