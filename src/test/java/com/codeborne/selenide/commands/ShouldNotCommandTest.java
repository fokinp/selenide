package com.codeborne.selenide.commands;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.WebElementSource;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShouldNotCommandTest {
  private SelenideElement proxy;
  private WebElementSource locator;
  private ShouldNot shouldNotCommand;
  private WebElement mockedFoundElement;

  @Before
  public void setup() {
    shouldNotCommand = new ShouldNot();
    proxy = mock(SelenideElement.class);
    locator = mock(WebElementSource.class);
    mockedFoundElement = mock(WebElement.class);
    when(locator.getWebElement()).thenReturn(mockedFoundElement);
  }

  @Test
  public void testDefaultConstructor() throws NoSuchFieldException, IllegalAccessException {
    ShouldNot shouldNot = new ShouldNot();
    Field prefixField = shouldNot.getClass().getDeclaredField("prefix");
    prefixField.setAccessible(true);
    String prefix = (String) prefixField.get(shouldNot);
    assertTrue(prefix.isEmpty());
  }

  @Test
  public void testExecuteMethodWithNonStringArgs() {
    SelenideElement returnedElement = shouldNotCommand.execute(proxy, locator, new Object[]{Condition.disabled});
    assertEquals(proxy, returnedElement);
  }

  @Test
  public void testExecuteMethodWithStringArgs() {
    SelenideElement returnedElement = shouldNotCommand.execute(proxy, locator, new Object[]{"hello"});
    assertEquals(proxy, returnedElement);
  }
}
