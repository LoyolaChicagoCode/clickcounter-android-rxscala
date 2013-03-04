package edu.luc.etl.cs313.android.scala.clickcounter.model

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FunSpec

/**
 * An abstract unit test for the bounded counter moidel.
 * This follows the XUnit Testcase Superclass pattern.
 */
trait AbstractCounterSpecs extends FunSpec with ShouldMatchers {

  val fixture: Counter

  describe("A counter") {
    it("has at least two values") {
      fixture.min should be < fixture.max
    }
    it("knows when it is empty") {
      fixture.isEmpty(fixture.min) should be (true)
      fixture.isFull(fixture.min) should be (false)
    }
    it("knows when it is full") {
      fixture.isFull(fixture.min) should be (false)
      fixture.isFull(fixture.max) should be (true)
    }
    it("increments correctly") {
      val v = fixture.min
      fixture.isFull(v) should be (false)
      fixture.increment(v) should be (Some(v + 1))
    }
    it("does not increment when full") {
      val v = fixture.max
      fixture.isFull(v) should be (true)
      fixture.increment(v) should be (None)
    }
    it("decrements correctly") {
      val v = fixture.min + 1
      fixture.isEmpty(v) should be (false)
      fixture.decrement(v) should be (Some(v - 1))
    }
    it("does not decrement when empty") {
      val v = fixture.min
      fixture.isEmpty(v) should be (true)
      fixture.decrement(v) should be (None)
    }
    it("resets correctly") {
      val v = fixture.max
      fixture.isEmpty(v) should be (false)
      fixture.reset(v) should be (Some(fixture.min))
    }
  }
}
