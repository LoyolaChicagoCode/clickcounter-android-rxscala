package edu.luc.etl.cs313.android.scala.clickcounter.android
package tests

import junit.framework.Assert._
import _root_.android.test.AndroidTestCase
import _root_.android.test.ActivityInstrumentationTestCase2
import _root_.android.test.UiThreadTest

/**
 * Concrete Android test subclass. Has to inherit from framework class
 * and uses delegation to concrete subclass of abstract test superclass.
 * IMPORTANT: project must export JUnit 4 to make it available on the
 * device.
 *
 * @author laufer
 * @see http://developer.android.com/tools/testing/activity_testing.html
 */
class AndroidFunctionalTest 
  extends ActivityInstrumentationTestCase2(classOf[MainActivity]) {

  // TODO fix so it can run in sbt (JUnit4 missing?)

//  private val actualTest = new AbstractFunctionalTest {
//    override protected def activity() =
//      AndroidFunctionalTest.this.getActivity
//  }
//
//  def testActivityExists() = actualTest.testActivityExists()
//
//  def testActivityInitialValue() = actualTest.testActivityInitialValue()
//
//  @UiThreadTest
//  def testActivityScenarioIncReset() = actualTest.testActivityScenarioIncReset()
//
//  @UiThreadTest
//  def testActivityScenarioIncUntilFull() = actualTest.testActivityScenarioIncUntilFull()
}
