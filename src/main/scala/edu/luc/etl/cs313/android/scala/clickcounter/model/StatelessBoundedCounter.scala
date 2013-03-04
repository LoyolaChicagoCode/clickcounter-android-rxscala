package edu.luc.etl.cs313.android.scala.clickcounter
package model

/**
 * An purely functional, stateless implementation of a bounded counter.
 */
class StatelessBoundedCounter(_min: Int, _max: Int) extends BoundedCounter {

  // Reflection in conjunction with default argument value is very messy.
  // This explicit default constructor makes it very easy to create
  // new instances through reflection.
  def this() = this(0, 10)

  require { _min < _max }

  /**
   * Indicates whether this counter currently satisfies its internal data
   * invariant: the counter value is within the bounds.
   *
   * @return whether the data invariant is satisfied
   */
  protected def dataInvariant(value: Int) = min <= value && value <= max

  override def min = _min

  override def max = _max

  override def increment(value: Int) = for {
    result <- Some(value + 1)
    if dataInvariant(result)
  } yield result

  override def decrement(value: Int) = for {
    result <- Some(value - 1)
    if dataInvariant(result)
  } yield result

  override def reset(value: Int) = for {
    result <- Some(min)
    if dataInvariant(result) && isEmpty(result)
  } yield result

  override def isFull(value: Int) = value >= max

  override def isEmpty(value: Int) = value <= min
}
