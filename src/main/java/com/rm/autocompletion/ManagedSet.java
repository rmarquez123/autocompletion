package com.rm.autocompletion;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import java.util.Set;
import java.util.stream.Collectors;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

/**
 * A set of completion items. Used in collaboration with
 * {@linkplain AutoCompletion} for providing candidate completion items based on
 * text typed by a user of a document.
 *
 * @author rmarquez
 */
public class ManagedSet {

  private final ListProperty<CompletionItem> textItems = new SimpleListProperty<>();

  /**
   *
   * @param textItems
   */
  public ManagedSet(List<CompletionItem> textItems) {
    this.textItems.setValue(FXCollections.observableArrayList(textItems));
  }

  /**
   * Sets the completion items to a list property. A change event will be
   * triggered.
   *
   * @param items
   */
  public void setCompletionItems(List<CompletionItem> items) {
    this.textItems.setValue(FXCollections.observableArrayList(items));
  }

  /**
   * Gets candidate completion items. Applies grouping when there are groupings
   * (for example a.1, a.2 are a group of a).
   *
   * @param cursorWord Used to get the preliminary set of completion items.
   * @return List of Completion Items obtained from the managed list of
   * completion items based on the cursorWord.
   */
  public CandidateCompletionItems getCandidateCompletionItems(CursorWord cursorWord) {
    CandidateCompletionItems result;
    Set<CompletionItem> preliminary = this.getPreliminaryCandidatesAsSet(cursorWord);
    Set<CompletionItem> items = this.applyGrouping(preliminary);
    List<CompletionItem> itemsList = this.convertCompletionItemsToList(items, cursorWord);
    result = new CandidateCompletionItems(itemsList);
    return result;
  }

  /**
   *
   * @param cursorWord
   * @return
   */
  private Set<CompletionItem> getPreliminaryCandidatesAsSet(CursorWord cursorWord) {
    Set<CompletionItem> preliminary = this.textItems.getValue().stream().filter((completionItems) -> {
      return this.filter(cursorWord, completionItems);
    }).map((ci) -> {
      String sub = ci.getKey().replace(cursorWord.getText(), "");
      int delimiterIndex = sub.indexOf(".");
      if (delimiterIndex != -1) {
        sub = sub.substring(0, delimiterIndex);
      }
      CompletionItem completionItem = new CompletionItem(sub, ci.getInsertionText(), ci.getObject());
      return completionItem;
    }).map((i) -> {
      String key = cursorWord.getText() + i.getKey();
      CompletionItem completionItem = new CompletionItem(key, i.getInsertionText(), i.getObject());
      return completionItem;
    }).collect(Collectors.toSet());
    return preliminary;
  }

  /**
   *
   * @param preliminary
   * @return
   */
  private Set<CompletionItem> applyGrouping(Set<CompletionItem> preliminary) {
    Set<CompletionItem> items = new HashSet<>();
    preliminary.stream().forEach((item) -> {
      if (this.textItems.getValue().contains(item)) {
        items.add(item);
      } else {
        CompletionItem groupCompletionItem = new CompletionItem(item.getKey(), null, null);
        items.add(groupCompletionItem);
      }
    });
    return items;
  }

  /**
   *
   * @param items
   * @param cursorWord
   * @return
   */
  private List<CompletionItem> convertCompletionItemsToList(Set<CompletionItem> items, CursorWord cursorWord) {
    List<CompletionItem> itemsList;
    if ((items.size() == 1 && this.getJoin(items).isEmpty())) {
      itemsList = Arrays.asList(new CompletionItem(cursorWord.getText(), cursorWord.getText(), null));
    } else {
      itemsList = items.stream().sorted().collect(Collectors.toList());
    }
    return itemsList;
  }

  /**
   *
   * @param text
   * @return
   */
  private boolean filter(CursorWord cursorWord, CompletionItem text) {
    boolean startsWith = text.getKey().startsWith(cursorWord.getText());
    return startsWith;
  }

  /**
   *
   * @param items
   * @return
   */
  private String getJoin(Set<CompletionItem> items) {
    return String.join("", items.stream().map((i) -> i.getKey()).collect(Collectors.toList()));
  }
}
