package edu.luc.etl.cs313.android.scala.clickcounter.android
package tests

import junit.framework.Assert._
import _root_.android.test.AndroidTestCase
import _root_.android.test.ActivityInstrumentationTestCase2

/**
 * Concrete Android test subclass. Has to inherit from framework class
 * and uses delegation to concrete subclass of abstract test superclass.
 * IMPORTANT: project must export JUnit 4 to make it available on the
 * device.
 *
 * @author laufer
 * @see http://developer.android.com/tools/testing/activity_testing.html
 */
class ClickCounterActivityInstrumentationTest
  extends ActivityInstrumentationTestCase2(classOf[ClickCounterAdapter]) {

  // TODO add forwarding methods to abstract test
  // TODO fix so it can run in sbt (JUnit4 missing?)

  def testActivityExists() {
    val activity = getActivity
    assertNotNull(activity)
    val t = activity.findView(TR.textview_value)
    assertEquals(0, t.getText.toString.toInt)
  }
}
