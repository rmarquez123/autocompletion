package com.rm.autocompletion;

import javafx.beans.property.Property;

/**
 * Intended for displaying and provided user interaction for selection of
 * candidate completion items.
 *
 * @author rmarquez
 */
public interface CompletionItemsDisplay {

  /**
   *
   * @return
   */
  Property<CandidateCompletionItems> candidateCompletionItemsProperty();

  /**
   *
   * @return
   */
  Property<CompletionItem> getCompletionItemSelected();

  /**
   *
   * @param candidateCompletionItems
   */
  void display(CandidateCompletionItems candidateCompletionItems);
}
