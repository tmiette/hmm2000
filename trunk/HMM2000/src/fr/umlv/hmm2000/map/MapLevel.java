package fr.umlv.hmm2000.map;

/**
 * This enum contains elements to create a new map.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public enum MapLevel {

  MAP1("map/map1.map", "Sins", Difficulty.EASY, 2, 2),
  MAP2("map/map2.map", "Zarock", Difficulty.NORMAL, 2, 3);

  public enum Difficulty {
    EASY,
    NORMAL,
    HARD;
  }

  // Map file to decode
  private final String mapFile;

  // Map name to display in game menu
  private final String name;

  // Map difficulty
  private final Difficulty difficulty;

  // Minimum player number
  private final int minPlayerNumber;

  // Maximum player number
  private final int maxPlayerNumber;

  private MapLevel(String mapFile, String name, Difficulty difficulty,
      int minPlayerNumber, int maxPlayerNumber) {

    this.mapFile = mapFile;
    this.name = name;
    this.difficulty = difficulty;
    this.minPlayerNumber = minPlayerNumber;
    this.maxPlayerNumber = maxPlayerNumber;
  }

  /**
   * Gets the map file string.
   * 
   * @return file name
   */
  public String getMapFile() {

    return this.mapFile;
  }

  /**
   * Gets the map name.
   * 
   * @return map name
   */
  public String getName() {

    return this.name;
  }

  /**
   * Gets the map difficulty
   * 
   * @return map difficulty
   */
  public Difficulty getDifficulty() {

    return this.difficulty;
  }

  /**
   * Gets minimum player value the map can contain.
   * 
   * @return minimun player number
   */
  public int getMinPlayerNumber() {

    return this.minPlayerNumber;
  }

  /**
   * Gets maximum player value the map can contain.
   * 
   * @return maximum player value
   */
  public int getMaxPlayerNumber() {

    return this.maxPlayerNumber;
  }

  @Override
  public String toString() {

    return this.name;
  }
}
