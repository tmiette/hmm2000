package fr.umlv.hmm2000.map;

public enum MapLevel {

  MAP1("map/map1.map", "TestMap", Difficulty.EASY, 2, 4),
  MAP2("map/map1.map", "NoneMap1", Difficulty.NORMAL, 2, 6),
  MAP3("map/map1.map", "NoneMap2", Difficulty.HARD, 2, 12),
  MAP4("map/map1.map", "NoneMap1", Difficulty.EASY, 2, 2),
  MAP5("map/map1.map", "NoneMap1", Difficulty.HARD, 2, 4),
  MAP6("map/map1.map", "NoneMap1", Difficulty.NORMAL, 2, 8),
  MAP7("map/map1.map", "NoneMap1", Difficulty.HARD, 2, 4),
  MAP8("map/map1.map", "NoneMap1", Difficulty.EASY, 2, 3),
  MAP9("map/map1.map", "NoneMap1", Difficulty.HARD, 2, 4),
  MAP10("map/map1.map", "NoneMap1", Difficulty.EASY, 2, 4),
  MAP11("map/map1.map", "NoneMap1", Difficulty.EASY, 2, 4),
  MAP12("map/map1.map", "NoneMap1", Difficulty.NORMAL, 2, 4),
  MAP13("map/map1.map", "NoneMap1", Difficulty.EASY, 2, 4),;
  
  public enum Difficulty {
    EASY,
    NORMAL,
    HARD;
  }

  private final String mapFile;

  private final String name;

  private final Difficulty difficulty;

  private final int minPlayerNumber;

  private final int maxPlayerNumber;

  private MapLevel(String mapFile, String name, Difficulty difficulty,
      int minPlayerNumber, int maxPlayerNumber) {
    this.mapFile = mapFile;
    this.name = name;
    this.difficulty = difficulty;
    this.minPlayerNumber = minPlayerNumber;
    this.maxPlayerNumber = maxPlayerNumber;
  }

  public String getMapFile() {
    return this.mapFile;
  }

  public String getName() {
    return this.name;
  }

  public Difficulty getDifficulty() {
    return this.difficulty;
  }

  public int getMinPlayerNumber() {
    return this.minPlayerNumber;
  }

  public int getMaxPlayerNumber() {
    return this.maxPlayerNumber;
  }
  
  @Override
  public String toString() {
    return this.name;
  }
}
