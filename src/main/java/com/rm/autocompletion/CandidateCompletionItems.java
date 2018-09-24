package com.rm.autocompletion;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rmarquez
 */
public class CandidateCompletionItems {

  private final List<CompletionItem> items = new ArrayList<>();

  /**
   *
   * @param items
   */
  public CandidateCompletionItems(List<CompletionItem> items) {
    this.items.addAll(items);
  }

  /**
   *
   * @return
   */
  public List<CompletionItem> asList() {
    ArrayList<CompletionItem> result = new ArrayList<>(this.items);
    return result;
  }
}
