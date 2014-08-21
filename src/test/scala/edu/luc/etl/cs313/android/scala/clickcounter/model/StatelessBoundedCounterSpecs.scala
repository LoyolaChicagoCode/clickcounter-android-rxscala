package edu.luc.etl.cs313.android.scala.clickcounter.model

/**
 * A concrete testcase subclass for StatelessBoundedCounter.
 * Run in sbt or Eclipse as a ScalaTest.
 */
class StatelessBoundedCounterSpecs extends AbstractBoundedCounterSpecs {
  override val fixture = new ReactiveBoundedCounter(0, 10)
}
