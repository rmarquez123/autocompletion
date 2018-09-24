package com.rm.autocompletion;

import java.util.Objects;

/**
 * Completion item entity.
 *
 * @author rmarquez
 */
public class CompletionItem implements Comparable<CompletionItem> {

  private final String key;
  private final String insertionText;
  private final Object object;

  /**
   *
   * @param text
   * @param insertionText
   * @param object
   */
  public CompletionItem(String text, String insertionText, Object object) {
    this.key = text;
    this.insertionText = insertionText;
    this.object = object;
  }

  /**
   *
   * @return
   */
  public Object getObject() {
    return object;
  }

  /**
   *
   * @return
   */
  public String getKey() {
    return this.key;
  }

  public String getInsertionText() {
    return insertionText;
  }

  @Override
  public int compareTo(CompletionItem o) {
    return this.getKey().compareTo(o.getKey());
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 71 * hash + Objects.hashCode(this.key);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final CompletionItem other = (CompletionItem) obj;
    if (!Objects.equals(this.key, other.key)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "CompletionItem{" + "key=" + key + ", insertionText=" + insertionText + '}';
  }
}
