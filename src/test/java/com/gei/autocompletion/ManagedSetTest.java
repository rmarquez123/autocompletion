package com.gei.autocompletion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author rmarquez
 */
@RunWith(JUnitParamsRunner.class)
public class ManagedSetTest {
  
  private static final List<String> data = new ArrayList<String>() {
    {
      add("a.1.1");
      add("a.1.2");
      add("a.2");
      add("a.3");
      add("b.1");
      add("b.2");
    }
  };

  @Test
  @Parameters({
    "a,a",
    "a.,a.1;a.2;a.3",  
    "b,b",
    "b.,b.1;b.2",
    "a.1,a.1", 
    "a.1.,a.1.1;a.1.2"
  })
  public void testGetCompletionItems(String typed, String expected) {
    ManagedSet managedSet = new ManagedSet(this.data.stream()
            .map((s)->new CompletionItem(s, "", null))
            .collect(Collectors.toList()));
    CursorWord cursorWord = new CursorWord(typed);
    CandidateCompletionItems items = managedSet.getCandidateCompletionItems(cursorWord);
    List<String> itemsList = items.asList()
            .stream().map((i) -> i.getKey())
            .collect(Collectors.toList());
    String result = String.join(";", itemsList);
    Assert.assertEquals("matching expected completion items", expected, result);
  }
}
