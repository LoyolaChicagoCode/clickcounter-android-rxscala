package edu.luc.etl.cs313.android.clickcounter
package model

/**
 * A counter abstraction.
 *
 * @author laufer
 */
trait Counter {
  /**
   * Increments the counter value. Precondition: counter is not full.
   */
  def increment(): Unit

  /**
   * Decrements the counter value. Precondition: counter is not empty.
   */
  def decrement(): Unit

  /**
   * Resets the counter value. Precondition: true. Postcondition: counter is
   * not empty.
   */
  def reset(): Unit

  /**
   * Returns the current counter value.
   *
   * @return the current counter value
   */
  def get(): Int

  /**
   * Indicates whether the counter is full (at its maximum).
   *
   * @return whether the counter is full
   */
  def isFull(): Boolean

  /**
   * Indicates whether the counter is empty (at its minimum).
   *
   * @return whether the counter is empty
   */
  def isEmpty(): Boolean
}