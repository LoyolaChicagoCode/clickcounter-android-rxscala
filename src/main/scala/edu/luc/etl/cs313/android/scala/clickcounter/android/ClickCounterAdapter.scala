package edu.luc.etl.cs313.android.scala.clickcounter
package android

import _root_.android.app.Activity
import _root_.android.os.Bundle
import _root_.android.util.Log
import model.Counter

/**
 * The concrete Adapter in the Model-View-Adapter pattern. It connects the
 * Android GUI view with the model, consisting of behavior and state,
 * by mixing the abstract Adapter in with the Android activity and its
 * lifecycle methods.
 */
class ClickCounterAdapter extends Activity with TypedActivity
  with AbstractClickCounterAdapter {

  private def TAG = "clickcounter-android-activity"

  override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    Log.i(TAG, "onCreate")
    // inject the (implicit) dependency on the view
    setContentView(R.layout.main)
    // inject the dependency on the model
    setBehavior(createBehaviorFromClassName())
    // set the initial state to the counter's min value
    setState(behavior.get.min)
  }

  override def onStart() {
    super.onStart()
    Log.i(TAG, "onStart")
    updateView()
  }

  /**
   * Creates a model instance from the class name provided as the string value
   * of the external model_class resource.
   */
  protected def createBehaviorFromClassName(): Counter = {
    // for flexibility, instantiate model based on externally configured class name
    Class.
      forName(getResources().getString(R.string.model_class)).
      asSubclass(classOf[Counter]).newInstance
  }

  /**
   * Updates the concrete view from the model.
   */
  override protected def updateView() {
    import scala.language.postfixOps
    // update display
    findView(TR.textview_value).setText(access { identity } toString)
    // afford controls according to model state
    findView(TR.button_increment).setEnabled(!access { behavior.get.isFull })
    findView(TR.button_decrement).setEnabled(!access { behavior.get.isEmpty })
  }
}