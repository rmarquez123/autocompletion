package com.rm.autocompletion;

/**
 * A cursor word object representing the last word preceding the cursor position
 * of a document. May be sub-classed by clients for adding additional information
 * related to the typed cursor word.
 *
 * @author rmarquez
 */
public class CursorWord {

  private final String text;

  /**
   *
   * @param text
   */
  public CursorWord(String text) {
    this.text = text;
  }

  /**
   *
   * @return
   */
  public String getText() {
    return text;
  }

}
