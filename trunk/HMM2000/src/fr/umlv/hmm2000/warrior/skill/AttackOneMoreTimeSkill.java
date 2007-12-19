package fr.umlv.hmm2000.warrior.skill;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.LocationSelectionRequester;
import fr.umlv.hmm2000.engine.LocationSelectionRequester.LocationSelection;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.Map;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.war.BattlePositionMap;
import fr.umlv.hmm2000.warrior.Fightable;
import fr.umlv.hmm2000.warrior.FightableContainer;
import fr.umlv.hmm2000.warrior.exception.WarriorDeadException;

public class AttackOneMoreTimeSkill implements SkillAction {

	private static AttackOneMoreTimeSkill instance;

	public static AttackOneMoreTimeSkill getInstance() {

		if (AttackOneMoreTimeSkill.instance == null) {
			AttackOneMoreTimeSkill.instance = new AttackOneMoreTimeSkill();
		}
		return AttackOneMoreTimeSkill.instance;
	}

	private AttackOneMoreTimeSkill() {

	}

	@Override
	public void perform(final FightableContainer container) {

		CoreEngine.requestLocationSelection(new LocationSelectionRequester(
				new LocationSelection(LocationSelectionRequester.ATTACKER_DEFENDER_LOCATIONS,
						"Qui voulez vous attaquer ?")) {

			@Override
			public void perform(Location... locations) {
				
				Location attackerLocation = locations[0];
				Location defenderLocation = locations[1];

				Map map = CoreEngine.map();
				MapForegroundElement e1 = map
						.getMapForegroundElementAtLocation(defenderLocation);
				MapForegroundElement e2 = map
				.getMapForegroundElementAtLocation(attackerLocation);

				if (e1 instanceof Fightable && e2 instanceof Fightable) {
					Fightable defender = (Fightable) e1;
					Fightable attacker = (Fightable) e2;

				
				attacker = (Fightable)CoreEngine.map().getMapForegroundElementAtLocation(attackerLocation);
				defender = (Fightable)CoreEngine.map().getMapForegroundElementAtLocation(defenderLocation);
				
				attacker.performAttack(defender);
				}
			}
		});
		
	}
}
