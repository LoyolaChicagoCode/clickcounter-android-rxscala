package edu.luc.etl.cs313.android.scala.clickcounter
package android

import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(classOf[RobolectricTestRunner])
class ClickCounterRobolectric extends AbstractClickCounterFunctionalTest {

  protected def activity() = _activity.get

  private var _activity: Option[ClickCounterAdapter] = None

  @Before
  def setUp() {
    _activity = Some(new ClickCounterAdapter())
    activity.onCreate(null)
    activity.onStart()
  }
}