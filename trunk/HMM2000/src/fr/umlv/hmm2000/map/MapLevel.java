package fr.umlv.hmm2000.map;

public enum MapLevel {

  MAP1("map/map1.map", "TestMap", Difficulty.EASY, 2, 4);

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
}
