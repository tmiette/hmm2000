package fr.umlv.hmm2000.building;

public interface CastleItem {

  public String getSuggestion();

  public void perform();

  public static final CastleItem defaultItem = new CastleItem() {

    @Override
    public String getSuggestion() {
      return "Ne rien faire";
    }

    @Override
    public void perform() {
      throw new UnsupportedOperationException();
    }
  };
}
