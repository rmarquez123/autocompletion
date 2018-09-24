
package com.gei.autocompletion;

/**
 *
 * @author rmarquez
 */
public class AutoCompletion {
  
  private final Document document;
  private final ManagedSet managedSet;
  private final CompletionItemsDisplay display;

  /**
   * 
   * @param document
   * @param managedSet
   * @param display 
   */
  public AutoCompletion(Document document, ManagedSet managedSet, CompletionItemsDisplay display) {
    this.document = document;
    this.managedSet = managedSet;
    this.display = display;
    this.document.cursorWordProperty().addListener((obs, old, change)->onCharacterTypedEvent());
    this.display.getCompletionItemSelected().addListener((obs, old, change)->onCompletionItemSelectedEvent());
  }
    
  /**
   * 
   * @param cursorWord 
   * @return  
   */
  public CandidateCompletionItems setCursorWord(CursorWord cursorWord) {
    this.document.cursorWordProperty().setValue(cursorWord);
    this.display.getCompletionItemSelected().setValue(null);
    return this.display.candidateCompletionItemsProperty().getValue();
  } 
  
  /**
   * 
   * @param completionItem 
   */
  public void selectCompletionItem(CompletionItem completionItem) {
    this.display.getCompletionItemSelected().setValue(completionItem);
  }
  
  /**
   * 
   */
  public void onCharacterTypedEvent() {
    CursorWord cursorWord = (CursorWord) this.document.cursorWordProperty().getValue(); 
    CandidateCompletionItems candidateCompletionItems = this.managedSet.getCandidateCompletionItems(cursorWord); 
    this.display.display(candidateCompletionItems); 
  }
  
  /**
   * 
   */
  public void onCompletionItemSelectedEvent() { 
    
    CompletionItem completionItem = this.display.getCompletionItemSelected().getValue();
    this.document.replaceCursorWord(completionItem);
  }
}
