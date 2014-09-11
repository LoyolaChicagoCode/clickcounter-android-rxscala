package edu.luc.etl.cs313.android.scala.rxclickcounter
package android

import _root_.android.view.View

import rx.lang.scala._

import model._

/**
 * The part of the Adapter in the Model-View-Adapter pattern that
 * propagates user input coming from the view components.
 * It maps actual button presses to semantic events
 * that the model understands.
 */
trait ObservableView {

  val subject = Subject[InputEvent]

  /**
   * Emits a semantic increment event. This method is connected to the
   * corresponding onClick event (actual button presses) in the view itself,
   * usually with the help of the graphical layout editor; the connection also
   * shows up in the XML source of the view layout.
   */
  def onIncrement(view: View) { subject.onNext(Increment) }

  /**
   * Emits a semantic decrement event.
   */
  def onDecrement(view: View) { subject.onNext(Decrement) }

  /**
   * Emits a semantic reset event.
   */
  def onReset(view: View) { subject.onNext(Reset) }
}

/**
 * The part of the Adapter in the Model-View-Adapter pattern that
 * keeps the view updated.
 * It maps semantic update events to actual view changes visible
 * to the user.
 */
trait ViewUpdater extends Observer[(Int, ModelState)] with TypedViewHolder {

  /**
   * Updates the concrete view from a model response event.
   */
  override def onNext(arg: (Int, ModelState)): Unit = {
    val (value, state) = arg
    // update counter value display
    findView(TR.textview_value).setText(value.toString)
    // afford (enable/disable) controls according to abstract model state
    findView(TR.button_increment).setEnabled(state != Full)
    findView(TR.button_decrement).setEnabled(state != Empty)
  }
}

