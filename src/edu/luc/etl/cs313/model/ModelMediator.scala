package edu.luc.etl.cs313.model

trait ModelMediator[S, B] {

  protected var state: Option[S] = None

  protected var behavior: Option[B] = None

  protected def transform(transformer: S => Option[S])(implicit update: () => Unit) {
    val old = state
    state = state flatMap transformer
    if (state != old) update()
  }

  protected def access[T](accessor: S => T)(implicit orElse: T): T =
    state map accessor getOrElse orElse

  def setBehavior(behavior: B) { this.behavior = Some(behavior) }

  def setState(state: S) { this.state = Some(state) }
}