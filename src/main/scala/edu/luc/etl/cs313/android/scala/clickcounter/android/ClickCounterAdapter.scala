package edu.luc.etl.cs313.android.scala.clickcounter
package android

import _root_.android.app.Activity
import _root_.android.os.Bundle
import _root_.android.util.Log
import _root_.android.view.Menu
import _root_.android.view.View
import _root_.android.widget.Button
import _root_.android.widget.TextView
import scala.language.postfixOps
import edu.luc.etl.cs313.android.scala.model._
import model._

/**
 * The Adapter in the Model-View-Adapter pattern. It connects the
 * Android GUI view with the model, consisting of behavior and state,
 * by mapping semantic events to state transformations.
 */
class ClickCounterAdapter extends Activity with ModelMediator[Int, Counter] with DefaultOrElseValues {

  // TODO testing
  // TODO slider and additional textview for max counter value
  // TODO enable assertions

  private def TAG = "clickcounter-android-activity"

  override protected def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    Log.i(TAG, "onCreate")
    // inject the (implicit) dependency on the view
    setContentView(R.layout.main)
    // inject the dependency on the model
    // FIXME
    // setModel(createBehaviorFromClassName())
    setBehavior(new StatelessBoundedCounter())
    setState(behavior.get.min)
  }

  override protected def onStart() {
    super.onStart()
    Log.i(TAG, "onStart")
    updateView()
  }

  /**
   * Creates a model instance from the class name provided as the string value
   * of the external model_class resource.
   */
  protected def createBehaviorFromClassName(): Counter = {
    // for flexibility, instantiate model based on externally configured
    // class name
    // FIXME newInstance fails
    Class
      .forName(getResources().getString(R.string.model_class))
      .asSubclass(classOf[Counter]).newInstance()
  }

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
  protected implicit def updateView() {
    // update display
    findViewById(R.id.textview_value).asInstanceOf[TextView].setText(access { identity } toString)
    // afford controls according to model state
    findViewById(R.id.button_increment).asInstanceOf[Button].setEnabled(!access { behavior.get.isFull })
    findViewById(R.id.button_decrement).asInstanceOf[Button].setEnabled(!access { behavior.get.isEmpty })
  }
}