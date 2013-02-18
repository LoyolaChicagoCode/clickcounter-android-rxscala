package edu.luc.etl.cs313.android.model

/**
 * A mediator between a world of mutable state `S` and a world of immutable
 * state transformed by purely functional, stateless behavior `B`.
 * Typically, `B` includes methods for accessing attributes of and
 * transforming instances of `S`.
 */
trait ModelMediator[S, B] {

  /**
   * The mutable state.
   */
  protected var state: Option[S] = None

  /**
   * The behavior for transforming the state. Typical behaviors expose
   * suitable transformers and accessors.
   */
  protected var behavior: Option[B] = None

  /**
   * Performs one state transformation followed by an update.
   */
  protected def transform(transformer: S => Option[S])(implicit update: () => Unit) {
    val old = state
    state = state flatMap transformer
    if (state != old) update()
  }

  /**
   * Accesses an attribute of the state.
   */
  protected def access[T](accessor: S => T)(implicit orElse: T): T =
    state map accessor getOrElse orElse

  protected def setBehavior(behavior: B) { this.behavior = Some(behavior) }

  protected def setState(state: S) { this.state = Some(state) }
}