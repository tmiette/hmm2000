package fr.umlv.hmm2000.warrior.skill;

import java.util.ArrayList;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.LocationSelectionRequester;
import fr.umlv.hmm2000.engine.LocationSelectionRequester.LocationSelection;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.war.BattlePositionMap;
import fr.umlv.hmm2000.warrior.Container;
import fr.umlv.hmm2000.warrior.Heroe;
import fr.umlv.hmm2000.warrior.Warrior;
import fr.umlv.hmm2000.warrior.exception.WarriorDeadException;
import fr.umlv.hmm2000.warrior.exception.WarriorNotReachableException;

public class AttackFirstLineSkill implements SkillAction {

	private static AttackFirstLineSkill instance;

	public static AttackFirstLineSkill getInstance() {

		if (AttackFirstLineSkill.instance == null) {
			AttackFirstLineSkill.instance = new AttackFirstLineSkill();
		}
		return AttackFirstLineSkill.instance;
	}

	private AttackFirstLineSkill() {

	}

	@Override
	public void perform(final Container attacker) {

		// TODO Auto-generated method stub
		CoreEngine.requestLocationSelection(new LocationSelectionRequester(
				new LocationSelection(LocationSelectionRequester.RECHEABLE_LOCATION,
						"Qui voulez vous attaquer ?")) {

			@Override
			public void perform(Location... locations) {
				Location l = locations[0];
				Map map = CoreEngine.map();
				MapForegroundElement e = map.getMapForegroundElementAtLocation(l);
				
				if (attacker instanceof Heroe && e instanceof Warrior) {
					Heroe hero = (Heroe) attacker;
					Warrior warrior = (Warrior) e;
					BattlePositionMap pMap = warrior.getContainer().getBattlePositionManager();
					if (pMap.isInFirstLine(warrior)) {
						try {
							for (Warrior w : pMap.getWarriorsOnFirstLine()) {
								hero.performAttack(w);
							}
						}
						catch (WarriorDeadException e1) {
							map.removeMapForegroundElement(map.getLocationForMapForegroundElement(e));
						}
						catch (WarriorNotReachableException e1) {
							// TODO nothing
						}
					}
					
				}
			}
		});
	}

}
