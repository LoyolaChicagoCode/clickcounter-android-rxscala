package edu.luc.etl.cs313.android.scala.clickcounter.model

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FunSpec

class StatelessBoundedCounterSpecs extends AbstractCounterSpecs {

  override val fixture = new StatelessBoundedCounter(0, 10)
}
