package edu.luc.etl.cs313.android.scala.clickcounter
package android

import scala.language.reflectiveCalls
import org.mockito.Mockito._
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FunSpec
import org.scalatest.mock.MockitoSugar
import model.Counter
import org.mockito.InOrder

/**
 * A unit test of AbstractClickCounterAdapter that uses mocking to
 * satisfy the dependencies (collaborators).
 *
 * Run this test in sbt as follows:
 * > test-only edu.luc.etl.cs313.android.scala.clickcounter.android.ClickCounterAdapterSpecs
 */
class ClickCounterAdapterSpecs extends FunSpec with ShouldMatchers with MockitoSugar {

  /**
   * Trait for mock views.
   */
  trait View {
    def update(): Unit
  }

  /**
   * Factory method for test fixtures.
   */
  def fixture() = new {
    // create mock instances of the collaborators
    val min = 0
    val max = 10
    val model = mock[Counter]
    val view = mock[View]
    val order = inOrder(model, view)
    // stub certain methods
    when(model.min).thenReturn(min)
    when(model.max).thenReturn(max)
    // create subject-under-test (SUT)
    val adapter = new AbstractClickCounterAdapter {
      override def updateView() {
        view.update() // hard-coded dependency
      }
    }
    adapter.setBehavior(model) // injected dependency
  }

  describe("A clickcounter adapter") {
    it("handles onIncrement") {
      // create and import fixture
      val f = fixture()
      import f._
      // exercise SUT
      adapter.setState(min)
      adapter.onIncrement(null)
      // verify interaction with collaborators
      order.verify(model).increment(min)
      order.verify(view).update()
    }
    it("handles onDecrement") {
      // create and import fixture
      val f = fixture()
      import f._
      // exercise SUT
      adapter.setState(max)
      adapter.onDecrement(null)
      // verify interaction with collaborators
      order.verify(model).decrement(max)
      order.verify(view).update()
    }
    it("handles onReset") {
      // create and import fixture
      val f = fixture()
      import f._
      // exercise subject-under-test
      adapter.setState(max)
      adapter.onReset(null)
      // verify interaction with collaborators
      order.verify(model).reset(max)
      order.verify(view).update()
    }
  }
}
