package com.rm.autocompletion;

/**
 * Public interface for auto-completion module.
 * <p>
 * Intended to serve a general purpose module for implementing steps when a user
 * of a document types some text, gets a selection of possible completion items,
 * and replaces text according to a selected completion item.
 * </p>
 * <p>
 * Clients can either use accessors from this class or changed properties of
 * collaborators {@linkplain Document} or {@linkplain CompletionItemsDisplay}
 * with the same steps taking place in either case, the difference being
 * procedural vs event driven.
 * </p>
 *
 * @author rmarquez
 */
public class AutoCompletion {

  private final Document document;
  private final ManagedSet managedSet;
  private final CompletionItemsDisplay display;

  /**
   * Public constructor.
   *
   * @param document A {@linkplain Document} instance.
   * @param managedSet A {@linkplain ManagedSet} instance.
   * @param display A {@linkplain CompletionItemsDisplay} instance.
   * @throws NullPointerException if any argument is null.
   */
  public AutoCompletion(Document document, ManagedSet managedSet,
          CompletionItemsDisplay display) {
    if (document == null) {
      throw new NullPointerException("Document is undefined");
    }
    if (managedSet == null) {
      throw new NullPointerException("Managed Set is undefined");
    }
    if (display == null) {
      throw new NullPointerException("Managed Set is undefined");
    }
    this.document = document;
    this.managedSet = managedSet;
    this.display = display;
    this.document.cursorWordProperty().addListener((obs, old, change)
            -> onCharacterTypedEvent());
    this.display.getCompletionItemSelected().addListener((obs, old, change)
            -> onCompletionItemSelectedEvent());
  }

  /**
   * Sets the cursor word by delegating to the {@linkplain Document}
   * collaborator, then returning the list of candidate completion items to the
   * client.
   *
   * @param cursorWord A cursor word (obtained by text typed by a user)
   * @see Document#cursorWordProperty()
   * @see CompletionItemsDisplay#candidateCompletionItemsProperty()
   * @return
   */
  public CandidateCompletionItems setCursorWord(CursorWord cursorWord) {
    this.document.cursorWordProperty().setValue(cursorWord);
    this.display.getCompletionItemSelected().setValue(null);
    return this.display.candidateCompletionItemsProperty().getValue();
  }

  /**
   * Selects the completion item. Alternatively, the action can be obtained
   * through {@linkplain CompletionItemsDisplay#getCompletionItemSelected()}
   *
   * @param completionItem
   */
  public void selectCompletionItem(CompletionItem completionItem) {
    this.display.getCompletionItemSelected().setValue(completionItem);
  }

  /**
   * Invoked on cursor word changed event.
   */
  private void onCharacterTypedEvent() {
    CursorWord cursorWord = (CursorWord) this.document
            .cursorWordProperty().getValue();
    CandidateCompletionItems candidateCompletionItems
            = this.managedSet.getCandidateCompletionItems(cursorWord);
    this.display.display(candidateCompletionItems);
  }

  /**
   * Invoked on completionItemSelected change event.
   */
  private void onCompletionItemSelectedEvent() {
    CompletionItem completionItem = this.display.getCompletionItemSelected()
            .getValue();
    this.document.replaceCursorWord(completionItem);
  }
}
