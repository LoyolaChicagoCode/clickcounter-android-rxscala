package edu.luc.etl.cs313.scala.rxclickcounter.ui

import org.junit.runner.RunWith
import org.robolectric.{Robolectric, RobolectricTestRunner}
import org.scalatest.junit.JUnitSuite

/**
 * Concrete Robolectric test subclass. This test will not work in sbt.
 * Before you can run it in Eclipse as an ordinary JUnit test, you should
 * perform the steps outlined in the documentation.
 *
 * @author laufer
 * @see http://pivotal.github.com/robolectric
 */
@RunWith(classOf[RobolectricTestRunner])
class RobolectricFunctionalTest extends JUnitSuite with AbstractFunctionalTest {

  override protected lazy val activity =
    Robolectric.buildActivity(classOf[MainActivity]).create().start().get
}
