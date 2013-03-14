package edu.luc.etl.cs313.android.scala.clickcounter
package model

/**
 * An immutable counter abstraction.
 *
 * @author laufer
 */
trait Counter {

  /**
   * Increments the counter value.
   */
  def increment(value: Int): Option[Int]

  /**
   * Decrements the counter value.
   */
  def decrement(value: Int): Option[Int]
}