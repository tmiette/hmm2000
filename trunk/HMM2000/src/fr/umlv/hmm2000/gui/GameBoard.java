package fr.umlv.hmm2000.gui;

import java.util.ArrayList;

import fr.umlv.hmm2000.manager.Engine;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.map.MapForegroundElement;
import fr.umlv.hmm2000.map.graph.CheckerboardGraph;
import fr.umlv.lawrence.Application;
import fr.umlv.lawrence.CursorListener;
import fr.umlv.lawrence.DefaultGridModel;
import fr.umlv.lawrence.GridPane;
import fr.umlv.lawrence.InputListener;
import fr.umlv.lawrence.Key;
import fr.umlv.lawrence.svg.SVGImageProvider;

public class GameBoard {

	private final DefaultGridModel<Sprite> model;

	private final SVGImageProvider<Sprite> provider;

	private final GridPane<Sprite> pane;

	private fr.umlv.lawrence.Location pointerLocation;

	private final Map map;

	private final Engine engine;

	public GameBoard(Map map) {
		this.map = map;
		CheckerboardGraph graph = this.map.getGraph();
		this.model = new DefaultGridModel<Sprite>(graph.getWidth(), graph
				.getHeight());
		this.provider = new SVGImageProvider<Sprite>();
		this.provider.setDPI(93);
		this.registerImages();
		this.pane = new GridPane<Sprite>(this.model, this.provider, 50, 50);
		this.pane.addInputListener(this.inputListener);
		this.pointerLocation = new fr.umlv.lawrence.Location(0, 0);
		this.pane.addCursorListener(this.pointerCursorListener);
		this.engine = new Engine(this.map, new LawrenceEventManager(this.model));
		this.initGrid();
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

	private void setCellMapBackgroundElements(ArrayList<Sprite> elements,
			int x, int y) {

		elements.add(Sprite.BACKGROUND);
		elements.add(this.map.getGraph().getMapBackgroundElement(y, x)
				.getSprite());
	}

	private void setCellMapElements(ArrayList<Sprite> elements, int x, int y) {
		MapForegroundElement element = this.map
				.getElementAtLocation(new Location(y, x));
		if (element != null) {
			elements.add(element.getSprite());
		}
	}

	private void setPointer(fr.umlv.lawrence.Location location) {
		model.setHighligthElement(pointerLocation, null);
		pointerLocation = location;
		model.setHighligthElement(pointerLocation, Sprite.POINTER);
	}

	public void display() {
		Application.display(this.pane, "HMM2000", false, true);
	}

	private final InputListener inputListener = new InputListener() {

		public void mouseClicked(int x, int y, int button) {
			GameBoard.this.engine.locationClicked(y, x, button);
		}

		public void keyTyped(int x, int y, Key keyCode) {
			// TODO
		}
	};

	private final CursorListener pointerCursorListener = new CursorListener() {
		public void mouseExited(int x, int y) {
			// nothing
		}

		public void mouseEntered(int x, int y) {
			fr.umlv.lawrence.Location location = new fr.umlv.lawrence.Location(
					x, y);
			GameBoard.this.setPointer(location);
		}
	};

}
