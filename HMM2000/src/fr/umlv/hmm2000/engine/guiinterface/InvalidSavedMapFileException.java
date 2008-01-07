package fr.umlv.hmm2000.engine.guiinterface;

/**
 * This exception is thrown when a problem occurs during the loading of a saved
 * map file.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class InvalidSavedMapFileException extends Exception {

  private static final long serialVersionUID = 8327724049953993916L;

  /**
   * Constructor of this exception.
   * 
   * @param message
   *            the exception message.
   */
  public InvalidSavedMapFileException(String message) {
    super(message);
  }

}
