package fr.umlv.hmm2000.manager;

public interface UIEventManager {

	public void performSelection(SelectionEvent event);

	public void performDeselection(SelectionEvent event);

	public void displayPath(MoveDisplayEvent event);

	public void removePath(MoveDisplayEvent event);

	public void performMove(MoveEvent event);
}
