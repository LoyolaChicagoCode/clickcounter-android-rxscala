package edu.luc.etl.cs313.android.scala.clickcounter.model

/**
 * A concrete testcase subclass. Run in sbt or Eclipse.
 */
class StatelessBoundedCounterSpecs extends AbstractCounterSpecs {
  override val fixture = new StatelessBoundedCounter(0, 10)
}
