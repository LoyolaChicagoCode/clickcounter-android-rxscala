package edu.luc.etl.cs313.android.clickcounter.model

class DefaultBoundedCounter(min: Int = 0, max: Int = 10) extends Counter{

  require { min < max }

  /**
   * The current value of the counter.
   */
  private var value = min

  /**
   * Indicates whether this counter currently satisfies its internal data
   * invariant: the counter value is within the bounds.
   *
   * @return whether the data invariant is satisfied
   */
  protected def dataInvariant = min <= value && value <= max

  override def increment() {
    require { dataInvariant && !isFull }
    value += 1
    require { dataInvariant }
  }

  override def decrement() {
    require { dataInvariant && !isEmpty }
    value -= 1
    require { dataInvariant }
  }

  override def reset() {
    value = min;
    assert { dataInvariant && isEmpty }
  }

  override def get = value

  override def isFull = value >= max

  override def isEmpty = value <= min
}
