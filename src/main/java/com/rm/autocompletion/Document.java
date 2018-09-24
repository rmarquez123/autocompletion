package com.rm.autocompletion;

import javafx.beans.property.Property;

/**
 * Document object used as a collaborator with an {@linkplain AutoCompletion}
 * instance.
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
