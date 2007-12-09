package fr.umlv.hmm2000.manager;

import fr.umlv.hmm2000.manager.MoveEvent.Step;

public interface UIEventManager {

	public UIChoicesManager choicesManager();

	public UIDisplayingVisitor displayingVisitor();
	
	public void performSelection(SelectionEvent event);

	public void performDeselection(SelectionEvent event);

	public void displayMove(MoveEvent event);

	public void removeMove(MoveEvent event);

	public void performStep(Step step);

	public void removeForegroundElement(EncounterEvent event);
}
