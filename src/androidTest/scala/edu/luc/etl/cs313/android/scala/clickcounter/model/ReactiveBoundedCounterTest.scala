package edu.luc.etl.cs313.android.scala.clickcounter.model

import scala.language.reflectiveCalls
import org.scalatest.{Matchers, WordSpec}
import org.scalamock.scalatest.MockFactory
import rx.lang.scala.Observer

/**
 * Tests for the reactive bounded counter model.
 */
class ReactiveBoundedCounterTest extends WordSpec with Matchers with MockFactory {

  val min = 0
  val max = 5

  def fixture = new ReactiveBoundedCounter(min, max)

  "A reactive bounded counter" when {
    "ever" should {
      "have the right min" in {
        fixture.min should equal (min)
      }
      "have the right max" in {
        fixture.max should equal (max)
      }
    }
    "observed" should {
      "respond correctly to increment from min" in {
        verifyCounterResponse((min, Increment), (min + 1, Counting))
      }
      "respond correctly to increment to max" in {
        verifyCounterResponse((max - 1, Increment), (max, Full))
      }
    }
  }

  // TODO turn into a more complete test suite

  def verifyCounterResponse(eIn: (Int, InputEvent), eOut: (Int, ModelState)): Unit = {
    val f = fixture
    val observer = stub[Observer[(Int, ModelState)]]
    f.observable.subscribe(observer)
    f.onNext(eIn)
    (observer.onNext _).verify(eOut)
  }
}
