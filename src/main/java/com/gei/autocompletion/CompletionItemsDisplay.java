package com.gei.autocompletion;

import javafx.beans.property.Property;

/**
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
