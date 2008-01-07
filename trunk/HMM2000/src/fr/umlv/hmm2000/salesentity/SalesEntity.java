package fr.umlv.hmm2000.salesentity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.engine.guiinterface.Spritable;
import fr.umlv.hmm2000.engine.guiinterface.Sprite;
import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.engine.manager.MoveCoreManager.Encounter;
import fr.umlv.hmm2000.map.builder.MapForegroundElementSaver;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.util.Pair;

/**
 * This class representes a sales entity.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class SalesEntity implements MapForegroundElement {

  private final SalesEntityEnum type;
  private final HashMap<Sellable, Integer> items;

  /**
   * Constructor of a sales entity.
   * 
   * @param type
   *            the type of the entity.
   */
  public SalesEntity(SalesEntityEnum type) {
    this.type = type;
    this.items = new HashMap<Sellable, Integer>();
  }

  /**
   * Add the item to the sellable items list that the sale entity sales with the
   * specified quantity.
   * 
   * @param item
   *            the item.
   * @param quantity
   *            the quantity.
   */
  public void addProduct(Sellable item, int quantity) {
    this.items.put(item, quantity);
  }

  /**
   * Returns the map of the items that the sale entity sales.
   * 
   * @return the map of the items that the sale entity sales.
   */
  public HashMap<Sellable, Integer> getItems() {
    return this.items;
  }

  @Override
  public void accept(UIDisplayingVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public Sprite getSprite() {
    return this.type.getSprite();
  }

  /**
   * Returns the type of the sales entity.
   * 
   * @return the type of the sales entity
   */
  public SalesEntityEnum getType() {
    return this.type;
  }

  /**
   * Returns the list of the items that the sale entity sales corresponding to
   * the hash map.
   * 
   * @param itemsMap
   *            the hash map.
   * @return the list of the items.
   */
  public static ArrayList<Pair<Sellable, Integer>> createItemsList(
      HashMap<Sellable, Integer> itemsMap) {

    ArrayList<Pair<Sellable, Integer>> itemsList = new ArrayList<Pair<Sellable, Integer>>();

    itemsList.add(new Pair<Sellable, Integer>(Sellable.defaultSellable, 0));
    for (Entry<Sellable, Integer> entry : itemsMap.entrySet()) {
      Sellable item = entry.getKey();
      int quantity = entry.getValue();
      itemsList.add(new Pair<Sellable, Integer>(item, quantity));
    }
    return itemsList;
  }

  /**
   * This enum defines all the types of sales entities.
   * 
   * @author MIETTE Tom
   * @author MOURET Sebastien
   * 
   */
  public enum SalesEntityEnum implements Spritable {
    MERCHANT(Sprite.MERCHANT),
    BARRACKS(Sprite.BARRACKS);

    private final Sprite sprite;

    /**
     * Constructor of the enum.
     * 
     * @param sprite
     *            sprite of the enum.
     */
    private SalesEntityEnum(Sprite sprite) {
      this.sprite = sprite;
    }

    @Override
    public Sprite getSprite() {
      return this.sprite;
    }
  }

  @Override
  public void nextDay(int day) {
    // do nothing
  }

  @Override
  public boolean encounter(Encounter encounter) {

    ArrayList<Pair<Sellable, Integer>> purchases = SalesEntity
        .createItemsList(this.items);
    Sellable item = CoreEngine.requestPurchase(purchases);

    if (item != null && item != Sellable.defaultSellable) {
      if (encounter.getSender().getPlayer().spend(item.getPrice())) {
        int quantity = this.items.get(item);
        quantity--;
        if (quantity == 0) {
          this.items.remove(item);
        } else {
          this.items.put(item, quantity);
        }
        item.acquire(encounter);
      } else {
        CoreEngine.fireMessage("You don't have enough resources.",
            HMMUserInterface.WARNING_MESSAGE);
      }
    }

    return false;
  }
  
  @Override
  public String getSaveString() {
    return MapForegroundElementSaver.save(this);
  }
}
