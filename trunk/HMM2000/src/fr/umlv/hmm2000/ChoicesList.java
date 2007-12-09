package fr.umlv.hmm2000;

import java.util.ArrayList;
import java.util.List;

public class ChoicesList<E> {

	private final String request;

	private final ArrayList<Pair<E, String>> choices;

	public ChoicesList(String title) {
		this.request = title;
		this.choices = new ArrayList<Pair<E, String>>();
	}

	public String getRequest() {
		return this.request;
	}

	public List<Pair<E, String>> getChoices() {
		return this.choices;
	}

	public void addChoice(E element, String proposition) {
		this.choices.add(new Pair<E, String>(element, proposition));
	}

}
