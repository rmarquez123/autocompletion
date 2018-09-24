# autocompletion

A simple autocompletion module for satisfying use case steps
<ol> 
  <li>User enters text and invokes autocompletion</li>
  <li>A list of candidate completion items is returned based on the word preceding the cursor position</li> 
  <li>The user selects one of the completion items from the list of candidate completion items</li>
  <li>The word preceding the cursor postion is replaced based on the insertion text of the selected completion item</li>
</ol> 
This module provides the <code>AutoCompletion</code> object as the public controller class and <code>Document</code> and <code>CompletionItemsDisplayer</code> as interfaces. 
