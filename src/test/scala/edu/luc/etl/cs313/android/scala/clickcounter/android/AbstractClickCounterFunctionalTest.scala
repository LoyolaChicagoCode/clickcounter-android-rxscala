package edu.luc.etl.cs313.android.scala.clickcounter
package android

import junit.framework.Assert._
import org.junit.Test

abstract class AbstractClickCounterFunctionalTest {

  protected def activity(): ClickCounterAdapter

  @Test def testActivityExists() {
    assertNotNull(activity)
  }

  @Test def testActivityInitialValue() {
    val t = activity.findView(TR.textview_value)
    assertEquals(0, t.getText.toString.toInt)
  }

  @Test def testActivityScenarioIncReset() {
    assertEquals(0, displayedValue)
    assertTrue(incButton.isEnabled)
    assertFalse(decButton.isEnabled)
    assertTrue(resetButton.isEnabled)
    assertTrue(incButton.performClick())
    assertEquals(1, displayedValue)
    assertTrue(incButton.isEnabled)
    assertTrue(decButton.isEnabled)
    assertTrue(resetButton.isEnabled)
    assertTrue(resetButton.performClick())
    assertEquals(0, displayedValue)
    assertTrue(incButton.isEnabled)
    assertFalse(decButton.isEnabled)
    assertTrue(resetButton.isEnabled)
  }

  @Test def testActivityScenarioIncUntilFull() {
    assertEquals(0, displayedValue)
    assertTrue(incButton.isEnabled)
    assertFalse(decButton.isEnabled)
    assertTrue(resetButton.isEnabled)
    while (incButton.isEnabled) {
      val v = displayedValue
      assertTrue(incButton.performClick())
      assertEquals(v + 1, displayedValue)
    }
    assertFalse(incButton.isEnabled)
    assertTrue(decButton.isEnabled)
    assertTrue(resetButton.isEnabled)
  }

  // auxiliary methods for easy access to UI widgets

  def displayedValue() = activity.findView(TR.textview_value).getText.toString.toInt

  def incButton() = activity.findView(TR.button_increment)

  def decButton() = activity.findView(TR.button_decrement)

  def resetButton() = activity.findView(TR.button_reset)
}
