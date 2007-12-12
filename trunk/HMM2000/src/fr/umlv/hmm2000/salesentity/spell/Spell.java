package fr.umlv.hmm2000.salesentity.spell;

import fr.umlv.hmm2000.engine.event.EncounterEvent;
import fr.umlv.hmm2000.resource.Resource.Kind;
import fr.umlv.hmm2000.salesentity.Price;
import fr.umlv.hmm2000.salesentity.Sellable;

public enum Spell implements Sellable {

  TELEPORTATION("Téléportation", new Price().addResource(Kind.GOLD, 2),
      TeleportationSpellAction.getInstance()),
  OBSTACLE_DESTRUCTION("Destruction d'un obstacle", new Price().addResource(
      Kind.GOLD, 5), ObstacleDestructionSpellAction.getInstance());

  private final String name;
  private final Price price;
  private final SpellAction action;

  private Spell(String name, Price price, SpellAction action) {
    this.name = name;
    this.price = price;
    this.action = action;
  }

  @Override
  public String getLabel() {
    return this.name;
  }

  @Override
  public Price getPrice() {
    return this.price;
  }

  @Override
  public void acquire(EncounterEvent event) {
    this.action.perform(event);
  }

}
