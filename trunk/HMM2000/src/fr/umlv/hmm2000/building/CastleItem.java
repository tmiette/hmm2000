package fr.umlv.hmm2000.building;

/**
 * This interface specify an available action when player select the castle.F
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public interface CastleItem {

	/**
	 * Returns a message displayed in choice message list.
	 * @return message
	 */
	public String getSuggestion();

	/**
	 * Performs action when player choose this item.
	 */
	public void perform();

	/**
	 * Default item : do nothing
	 */
	public static final CastleItem defaultItem = new CastleItem() {

		@Override
		public String getSuggestion() {

			return "Nothing";
		}

		@Override
		public void perform() {

			throw new UnsupportedOperationException();
		}
	};
}
