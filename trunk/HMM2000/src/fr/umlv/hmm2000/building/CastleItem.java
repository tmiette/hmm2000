package fr.umlv.hmm2000.building;



public interface CastleItem {

	public String getName();

  public void perform(Castle castle);

  public String getToolTipText();
  
  public static final CastleItem defaultItem = new CastleItem(){
    @Override
    public String getName() {
      return "Aucun choix.";
    }

    @Override
    public String getToolTipText() {
      return null;
    }

    @Override
    public void perform(Castle castle) {
      throw new UnsupportedOperationException();      
    }
  };
}
