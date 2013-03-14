package edu.luc.etl.cs313.android.scala.clickcounter
package model

/**
 * An immutable bounded counter abstraction.
 *
 * @author laufer
 */
trait BoundedCounter extends Counter {

  def min: Int

  def max: Int

  /**
   * Resets the counter value.
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