package edu.luc.etl.cs313.android.scala.clickcounter.android
package tests

import junit.framework.Assert._
import _root_.android.test.AndroidTestCase
import _root_.android.test.ActivityInstrumentationTestCase2

class ClickCounterActivityInstrumentationTest
  extends ActivityInstrumentationTestCase2(classOf[ClickCounterAdapter]) {

  def testActivityExists() {
    val activity = getActivity
    assertNotNull(activity)
    val t = activity.findView(TR.textview_value)
    assertEquals(0, t.getText.toString.toInt)
  }
}
//  def testActivityInitialValue() {
//    val activity = getActivity
//    val t = activity.findView(TR.textview_value)
//    assertEquals(0, t.getText.toString.toInt)
//  }

/*
  def testActivityScenarioIncReset() {
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

  def testActivityScenarioIncUntilFull() {
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

  def displayedValue2() = getActivity.findView(TR.textview_value).getText.toString.toInt
*/

//  def incButton() = getActivity.findView(TR.button_increment)
//
//  def decButton() = getActivity.findView(TR.button_decrement)
//
//  def resetButton() = getActivity.findView(TR.button_reset)
//}
