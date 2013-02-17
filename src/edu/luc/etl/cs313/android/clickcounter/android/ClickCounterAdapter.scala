package edu.luc.etl.cs313.android.clickcounter
package android

import _root_.android.app.Activity
import _root_.android.os.Bundle
import _root_.android.util.Log
import _root_.android.view.Menu
import _root_.android.view.View
import _root_.android.widget.Button
import _root_.android.widget.TextView
import edu.luc.etl.cs313.android.clickcounter.R
import model.Counter
import edu.luc.etl.cs313.android.clickcounter.model.StatelessBoundedCounter

class MainActivity extends Activity {

  // TODO slider and additional textview for max counter value
  // TODO enable assertions

  private def TAG = "clickcounter-android-activity"

  /**
   * Explicit dependency on the model. (The dependency on the view is
   * implicit.)
   */

  private var behavior: Option[Counter] = None

  private var state: Option[Int] = None

  protected def transform(transformer: Int => Option[Int]) {
    val old = state
    state = state flatMap transformer
    if (state != old) updateView()
  }

  protected def check(checker: Int => Boolean) = state map checker getOrElse false

  /**
   * Setter for the model.
   */
  def setModel(model: Counter) { this.behavior = Some(model) }

  override protected def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    Log.i(TAG, "onCreate")
    // inject the (implicit) dependency on the view
    setContentView(R.layout.activity_click_counter)
    // self-inject the dependency on the model
    // FIXME
    // setModel(createModelFromClassName())
    setModel(new StatelessBoundedCounter())
    state = Some(behavior.get.min)
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
  protected def updateView() {
    // update display
    findViewById(R.id.textview_value).asInstanceOf[TextView].setText(state.getOrElse(-1).toString)
    // afford controls according to model state
    findViewById(R.id.button_increment).asInstanceOf[Button].setEnabled(!check { behavior.get.isFull })
    findViewById(R.id.button_decrement).asInstanceOf[Button].setEnabled(!check { behavior.get.isEmpty })
  }
}