package edu.luc.etl.cs313.android.scala.clickcounter
package android

import _root_.android.view.View
import edu.luc.etl.cs313.android.scala.model._
import model._

/**
 * An abstract Adapter in the Model-View-Adapter pattern. It maps semantic
 * events to state transformations in the model. To enable unit testing,
 * this has no class-level dependencies on Android and leaves the updateView
 * method abstract.
 */
trait AbstractAdapter extends ModelMediator[Int, BoundedCounter] with DefaultOrElseValues {

  /**
   * Handles the semantic increment event. (Semantic as opposed to, say, a
   * concrete button press.) This and similar events are connected to the
   * corresponding onClick events (actual button presses) in the view itself,
   * usually with the help of the graphical layout editor; the connection also
   * shows up in the XML source of the view layout.
   */
  def onIncrement(view: View) { transform { behavior.get.increment } }

  /**
   * Handles the semantic decrement event.
   */
  def onDecrement(view: View) { transform { behavior.get.decrement } }

  /**
   * Handles the semantic decrement event.
   */
  def onReset(view: View) { transform { behavior.get.reset } }

  /**
   * Updates the view from the model. Implicit so `transform` picks it up.
   */
  protected implicit def updateView()
}