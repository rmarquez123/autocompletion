package com.gei.autocompletion;

import javafx.beans.property.Property;

/**
 *
 * @author rmarquez
 */
public interface Document {

  /**
   *
   * @return
   */
  public Property<? super CursorWord> cursorWordProperty(); 

  /**
   *
   * @param completionItem
   */
  public void replaceCursorWord(CompletionItem completionItem);
}
