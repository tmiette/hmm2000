package fr.umlv.hmm2000.manager;

import fr.umlv.hmm2000.ChoicesList;

public interface UIChoicesManager {

	public <E> E submit(ChoicesList<E> action);

}
