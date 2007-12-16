package fr.umlv.hmm2000.engine.guiinterface;

import fr.umlv.hmm2000.engine.event.EncounterEvent;
import fr.umlv.hmm2000.engine.event.MapChangeEvent;
import fr.umlv.hmm2000.engine.event.MoveEvent;
import fr.umlv.hmm2000.engine.event.SelectionEvent;
import fr.umlv.hmm2000.engine.event.MoveEvent.Step;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;

public interface HMMUserInterface {

  public void eraseMap();
  
  public void drawMap(Map map);

  public UIChoicesManager choicesManager();

  public UIDisplayingVisitor displayingVisitor();

  public void displayMessage(String message);

  public void displaySelection(SelectionEvent event);

  public void eraseSelection(SelectionEvent event);

  public void displayMoveSteps(MoveEvent event);

  public void eraseMoveSteps(MoveEvent event);

  public void displayStep(Step step);
  
  public void swap(Location from, Location to);

  public void eraseForegroundElement(EncounterEvent event);

  public void changeBackgroundElement(MapChangeEvent event);
}
