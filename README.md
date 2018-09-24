# autocompletion

A module for simple auto completion.  The purpose of this module is to satisfy the use case steps: 
  1. User enters text and invokes autocompletion
  1. A list of candidate completion items is returned based on the word preceding the cursor position
  1. The user selects one of the completion items from the list of candidate completion items
  1. The word preceding the cursor postion is replaced based on the insertion text of the selected completion item
This module provides the <code>AutoCompletion</code> object as the public controller class and <code>Document</code> and <code>CompletionItemsDisplayer</code> as interfaces. 

![Autocompletion activity diagram](http://ricardo-marquez.com/rm/assets/images/activity-diagram-page-6.svg | height=250)
