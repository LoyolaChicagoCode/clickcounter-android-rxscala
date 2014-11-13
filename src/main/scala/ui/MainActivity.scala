package edu.luc.etl.cs313.scala.rxclickcounter
package ui

import android.app.Activity
import android.os.Bundle
import android.util.Log

import rx.lang.scala.Observer

/**
 * The main Android activity, which provides the required lifecycle methods.
 * By mixing in the reactive view behaviors, this class serves as the Adapter
 * in the Model-View-Adapter pattern. It connects the Android GUI view with the
 * reactive model.
 */
class MainActivity extends Activity with TypedActivity with ObservableView with ViewUpdater {

  private def TAG = "clickcounter-android-activity"

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)
    Log.i(TAG, "onCreate")
    // inject the (implicit) dependency on the view
    setContentView(R.layout.main)
    // create the other components
    val holder = new ValueHolder { }
    val counter = new model.ReactiveBoundedCounter(0, 5)
    // connect everything in several steps using RxScala
    // use map to add the current value to the button press
    this.subject.map((holder.value, _)).subscribe(counter)
    // extract only the integer value
    counter.observable.map(_._1).subscribe(holder)
    // connect the observable view directly to the model
    counter.observable.subscribe(this)
  }

  override def onStart() = {
    super.onStart()
    Log.i(TAG, "onStart")
    this.subject.onNext(model.Reset)
  }
}

/** An Observable that updates the value it holds. */
trait ValueHolder extends Observer[Int] {

  var value = 0

  override def onNext(newValue: Int): Unit = {
    value = newValue
  }
}