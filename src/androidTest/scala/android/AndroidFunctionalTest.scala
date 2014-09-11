package edu.luc.etl.cs313.android.scala.rxclickcounter.android

import android.test.{ActivityInstrumentationTestCase2, UiThreadTest}

/**
 * Concrete Android test subclass. Has to inherit from framework class
 * and mixes in the abstract test superclass as trait.
 *
 * We need to use explicit method forwarding to super just so we can
 * add the @UiThreadTest annotation, which works only in the context
 * of an AITC2!
 */
class AndroidFunctionalTest
  extends ActivityInstrumentationTestCase2[MainActivity](classOf[MainActivity])
  with AbstractFunctionalTest {

  override protected lazy val activity = getActivity

  @UiThreadTest override def testActivityScenarioIncReset() {
    super.testActivityScenarioIncReset()
  }

  @UiThreadTest override def testActivityScenarioIncUntilFull() {
    super.testActivityScenarioIncUntilFull()
  }
}