package fr.umlv.hmm2000.map.builder;

import java.io.LineNumberReader;

import fr.umlv.hmm2000.map.element.MapForegroundElement;

/**
 * This interface defines an initializer of an element from a map file line.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public interface MapForegroundElementInitializer {

  /**
   * Initialize a foreground element from a data array.
   * 
   * @param lnr
   *            the line number reader.
   * @param data
   *            the data array.
   * @return the foreground element.
   */
  public MapForegroundElement initialize(LineNumberReader lnr, String[] data);

}
