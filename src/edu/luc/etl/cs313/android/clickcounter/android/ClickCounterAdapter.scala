package edu.luc.etl.cs313.android
package clickcounter.android

import _root_.android.app.Activity
import _root_.android.os.Bundle
import _root_.android.util.Log
import _root_.android.view.Menu
import _root_.android.view.View
import _root_.android.widget.Button
import _root_.android.widget.TextView
import edu.luc.etl.cs313.android.model._
import clickcounter.R
import clickcounter.model._


class ClickCounterAdapter extends Activity with ModelMediator[Int, Counter] with DefaultOrElseValues {

  // TODO testing
  // TODO slider and additional textview for max counter value
  // TODO enable assertions

  private def TAG = "clickcounter-android-activity"

  override protected def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    Log.i(TAG, "onCreate")
    // inject the (implicit) dependency on the view
    setContentView(R.layout.activity_click_counter)
    // self-inject the dependency on the model
    // FIXME
    // setModel(createModelFromClassName())
    setBehavior(new StatelessBoundedCounter())
    setState(behavior.get.min)
  }

  override protected def onStart() {
    super.onStart()
    Log.i(TAG, "onStart")
    updateView()
  }

  override def onCreateOptionsMenu(menu: Menu): Boolean = {
    Log.i(TAG, "onCreateOptionsMenu")
    getMenuInflater.inflate(R.menu.click_counter, menu)
    true
  }

  /**
   * Creates a model instance from the class name provided as the string value
   * of the external model_class resource.
   *
   * @return
   */
  protected def createModelFromClassName(): Counter = {
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
   *
   * @param view
   *            the event source
   */
  def onIncrement(view: View) { transform { behavior.get.increment } }

  /**
   * Handles the semantic decrement event.
   *
   * @param view
   *            the event source
   */
  def onDecrement(view: View) { transform { behavior.get.decrement } }

  /**
   * Handles the semantic decrement event.
   *
   * @param view
   *            the event source
   */
  def onReset(view: View) { transform { behavior.get.reset } }

  /**
   * Updates the view from the model.
   */
  protected implicit def updateView() {
    // update display
    findViewById(R.id.textview_value).asInstanceOf[TextView].setText(access { identity } toString)
    // afford controls according to model state
    findViewById(R.id.button_increment).asInstanceOf[Button].setEnabled(!access { behavior.get.isFull })
    findViewById(R.id.button_decrement).asInstanceOf[Button].setEnabled(!access { behavior.get.isEmpty })
  }
}