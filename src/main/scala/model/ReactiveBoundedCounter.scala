package edu.luc.etl.cs313.android.scala.rxclickcounter
package model

import rx.lang.scala._

/**
 * A semantic input event.
 */
trait InputEvent
case object Increment extends InputEvent
case object Decrement extends InputEvent
case object Reset extends InputEvent

/**
 * An abstract model state.
 */
trait ModelState
case object Empty extends ModelState
case object Counting extends ModelState
case object Full extends ModelState

/**
 * A stateless, reactive bounded counter.
 */
class ReactiveBoundedCounter(val min: Int, val max: Int) extends Observer[(Int, InputEvent)] {

  // Reflection in conjunction with default argument value is very messy.
  // This explicit default constructor makes it very easy to create
  // new instances through reflection.
  def this() = this(0, 10)

  require { min < max }

  /**
   * The internal subject for emitting response events.
   */
  private lazy val subject = Subject[(Int, ModelState)]

  /**
   * The observable through which this counter emits response events.
   * @return the observable
   */
  def observable: Observable[(Int, ModelState)] = subject

  /**
   * Indicates whether this counter currently satisfies its internal data
   * invariant: the counter value is within the bounds.
   *
   * @return whether the data invariant is satisfied
   */
  protected def dataInvariant(value: Int) = min <= value && value <= max

  /**
   * Provides the reactive behavior of this bounded counter.
   * @param arg the current value along with an input event
   */
  override def onNext(arg: (Int, InputEvent)): Unit = {
    val (oldValue, event) = arg
    // figure out the new value
    val value = event match {
      case Increment => oldValue + 1
      case Decrement => oldValue - 1
      case Reset => min
    }
    // ignore any input event that would violate the data invariant
    if (dataInvariant(value)) {
      // figure out the corresponding abstract state
      val state = if (isFull(value)) Full else if (isEmpty(value)) Empty else Counting
      // fire the response event
      subject.onNext((value, state))
    }
  }

  protected def isFull(value: Int) = value >= max

  protected def isEmpty(value: Int) = value <= min
}
