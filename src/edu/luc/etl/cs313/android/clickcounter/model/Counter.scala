package edu.luc.etl.cs313.android.clickcounter.model

/**
 * An immutable counter abstraction.
 *
 * @author laufer
 */
trait Counter {

  def min: Int

  def max: Int

  /**
   * Increments the counter value. Precondition: counter is not full.
   */
  def increment(value: Int): Option[Int]

  /**
   * Decrements the counter value. Precondition: counter is not empty.
   */
  def decrement(value: Int): Option[Int]

  /**
   * Resets the counter value. Precondition: true. Postcondition: counter is
   * not empty.
   */
  def reset(value: Int): Option[Int]

  /**
   * Indicates whether the counter is full (at its maximum).
   */
  def isFull(value: Int): Boolean

  /**
   * Indicates whether the counter is empty (at its minimum).
   */
  def isEmpty(value: Int): Boolean
}