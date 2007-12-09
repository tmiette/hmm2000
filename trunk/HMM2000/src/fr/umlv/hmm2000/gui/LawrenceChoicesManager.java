package fr.umlv.hmm2000.gui;

import java.util.Scanner;

import fr.umlv.hmm2000.ChoicesList;
import fr.umlv.hmm2000.Pair;
import fr.umlv.hmm2000.manager.UIChoicesManager;

public class LawrenceChoicesManager implements UIChoicesManager {

	private static int readInt(int min, int max) {

		Scanner scanner = new Scanner(System.in);
		try {
			int value = scanner.nextInt();
			while (value < min || value > max) {
				System.out.println("Choix incorrect.");
				value = scanner.nextInt();
			}
			return value;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public <E> E submit(ChoicesList<E> choices) {

		System.out.println(choices.getRequest());
		int i = 0;
		for (Pair<E, String> choice : choices.getChoices()) {
			System.out.println(i++ + "-" + choice.getSecondElement());
		}

		int choice = LawrenceChoicesManager.readInt(0, choices.getChoices()
				.size() - 1);

		Pair<E, String> pair = choices.getChoices().get(choice);
		if (pair == null) {
			return null;
		} else {
			return pair.getFirstElement();
		}
	}

}
