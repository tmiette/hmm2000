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

public class AttackFirstLineSkill implements SkillAction {

	private AttackFirstLineSkill() {

	}

	@Override
	public void perform(final FightableContainer attacker) {

		// TODO Auto-generated method stub
		CoreEngine.requestLocationSelection(new LocationSelectionRequester(
				new LocationSelection(LocationSelectionRequester.RECHEABLE_LOCATION,
						"Qui voulez vous attaquer ?")) {

			@Override
			public void perform(Location... locations) {

				Location l = locations[0];
				Map map = CoreEngine.map();
				MapForegroundElement mfe = map.getMapForegroundElementAtLocation(l);

				if (mfe instanceof Fightable) {
					Fightable defender = (Fightable) mfe;
					BattlePositionMap pMap = defender.getFightableContainer()
							.getBattlePositionManager();
					if (pMap.isInFirstLine(defender)) {
						try {
							for (Fightable w : pMap.getFightableOnFirstLine()) {

								double elementaryDamage = attacker.getAbilities().getDamage(
										w.getAbilities());
								w.hurt(attacker.getPhysicalAttackValue()
										+ elementaryDamage - w.getPhysicalDefenseValue());

							}
						}
						catch (WarriorDeadException e1) {
							map.removeMapForegroundElement(map
									.getLocationForMapForegroundElement(defender));
						}
					}

				}
			}
		});
	}

}
