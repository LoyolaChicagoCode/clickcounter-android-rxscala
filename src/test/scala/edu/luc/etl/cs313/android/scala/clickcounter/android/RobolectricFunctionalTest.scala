package edu.luc.etl.cs313.android.scala.clickcounter
package android

import org.junit.{Before,Ignore}
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Concrete Robolectric test subclass. This test will not work in sbt.
 * Before you can run it in Eclipse as an ordinary JUnit test, you should
 * perform the steps outlined in the documentation.
 *
 * @author laufer
 * @see http://pivotal.github.com/robolectric
 */
@Ignore
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