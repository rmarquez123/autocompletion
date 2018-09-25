# autocompletion
<h4>See also - https://github.com/rmarquez123/autocompletionNb</h4> 
<h3> Purpose</h3> 
A module for simple auto completion.  The purpose of this module is to satisfy the use case steps: 
  1. User enters text and invokes autocompletion
  1. A list of candidate completion items is returned based on the word preceding the cursor position
  1. The user selects one of the completion items from the list of candidate completion items
  1. The word preceding the cursor postion is replaced based on the insertion text of the selected completion item
<br> 
<h3> Diagrams </h3> 
<strong> Activity Diagram </strong> 
<p> 
  Below is the use case represented as an activity diagram.  
  <div align="center" width="500px"> 
    <img src="http://ricardo-marquez.com/rm/assets/images/activity-diagram-page-6.svg" alt="" height="400">
  </div> 
  The are actually two entry points in the sequence.  The first part of the use case ends with completion items being displayed.  The second part of the use case ends with the cursor word (word preceding the cursor position) with the completion item insertion text. 
</p> 
<strong> Sequence Diagram </strong> 
<p> 
 The sequence diagram, shown below, shows the interactions of the primary classes involved. This module provides the <code>AutoCompletion</code> object as the public controller class and <code>Document</code> and <code>CompletionItemsDisplayer</code> as interfaces which should also be implemented by the client.

  <div align="center" width="1200px"> 
    <img src="http://ricardo-marquez.com/rm/assets/images/activity-diagram-page-5.svg" alt="" height="450px">
  </div> 
</p> 
