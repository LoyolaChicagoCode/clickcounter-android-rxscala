package edu.luc.etl.cs313.android.scala.model

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FunSpec

class ModelMediatorSpecs extends FunSpec with ShouldMatchers {

  def fixture() = new {
    var u = 0
    implicit def update() { u += 1 }
    object mm extends ModelMediator[Int, Int => Option[Int]] {
      state = Some(0)
      behavior = Some(x => Some(x + 1))
      def inc() { transform { behavior.get } }
      def get() = state.get
    }
  }

  describe("A model mediator") {
    it("just works") {
      val f = fixture
      f.mm.get should be (0)
      f.u should be (0)
      f.mm.inc()
      f.mm.get should be (1)
      f.u should be (1)
    }
  }
}
