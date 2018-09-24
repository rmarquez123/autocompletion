package com.rm.autocompletion;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of candidate completion items based on
 * {@linkplain CursorWord} value and a {@linkplain ManagedSet} instance.
 *
 * @author rmarquez
 */
public class CandidateCompletionItems {

  private final List<CompletionItem> items = new ArrayList<>();

  /**
   * Public constructor.
   *
   * @param items list of completion items. The list will be copied.
   */
  public CandidateCompletionItems(List<CompletionItem> items) {
    this.items.addAll(items);
  }

  /**
   * Get the list of completion items (defensive copy).
   *
   * @return
   */
  public List<CompletionItem> asList() {
    ArrayList<CompletionItem> result = new ArrayList<>(this.items);
    return result;
  }
}
