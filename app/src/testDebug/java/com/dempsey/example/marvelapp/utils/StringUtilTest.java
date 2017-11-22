package com.dempsey.example.marvelapp.utils;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StringUtilTest extends TestCase {

  @Before
  public void setUp() {}

  @Test
  public void testWeGetFourWordsFromLongString() {
    final String testString = "This is a test to test this utility";
    final String afterMethod = StringUtil.getWordsFromString(testString);
    assertEquals(StringUtil.trimSpaces(afterMethod), "This is a test");
  }

}