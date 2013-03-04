package edu.luc.etl.cs313.android.scala.clickcounter
package android

import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.junit.Ignore

/**
 * Concrete Robolectric test subclass. Run this test in Eclipse. It will
 * not work in sbt.
 *
 * @author laufer
 * @see http://pivotal.github.com/robolectric
 */
@RunWith(classOf[RobolectricTestRunner])
class RobolectricFunctionalTest extends AbstractFunctionalTest {

  override protected def activity() = _activity.get

  private var _activity: Option[MainActivity] = None

  @Before def setUp() {
    _activity = Some(new MainActivity)
    activity.onCreate(null)
    activity.onStart()
  }
}