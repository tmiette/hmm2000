package fr.umlv.hmm2000.salesentity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.guiinterface.Spritable;
import fr.umlv.hmm2000.engine.guiinterface.Sprite;
import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.engine.manager.MoveCoreManager.Encounter;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.util.Pair;

public class SalesEntity implements MapForegroundElement {

  private final SalesEntityEnum type;
  private final HashMap<Sellable, Integer> items;

  public SalesEntity(SalesEntityEnum type) {
    this.type = type;
    this.items = new HashMap<Sellable, Integer>();
  }

  public void addProduct(Sellable item, int quantity) {
    this.items.put(item, quantity);
  }

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
  
  public SalesEntityEnum getType() {
    return this.type;
  }

  private static final Sellable defaultSellable = new Sellable() {

    @Override
    public String getLabel() {
      return "Ne rien acheter";
    }

    @Override
    public Price getPrice() {
      return null;
    }

    @Override
    public void acquire(Encounter encounter) {
      throw new UnsupportedOperationException();
    }

  };

  public static ArrayList<Pair<Sellable, Integer>> createItemsList(
      HashMap<Sellable, Integer> itemsMap) {

    ArrayList<Pair<Sellable, Integer>> itemsList = new ArrayList<Pair<Sellable, Integer>>();

    itemsList.add(new Pair<Sellable, Integer>(SalesEntity.defaultSellable, 0));
    for (Entry<Sellable, Integer> entry : itemsMap.entrySet()) {
      Sellable item = entry.getKey();
      int quantity = entry.getValue();
      itemsList.add(new Pair<Sellable, Integer>(item, quantity));
    }
    return itemsList;
  }

  public enum SalesEntityEnum implements Spritable {
    MERCHANT(Sprite.MERCHANT),
    BARRACKS(Sprite.BARRACKS);

    private final Sprite sprite;

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
    
    if (item != null && item != SalesEntity.defaultSellable) {
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
        CoreEngine.fireMessage("Vous n'avez pas assez de ressource.");
      }
    } else {
      CoreEngine.fireMessage("Vous n'avez rien acheter.");
    }

    return false;
  }
}
