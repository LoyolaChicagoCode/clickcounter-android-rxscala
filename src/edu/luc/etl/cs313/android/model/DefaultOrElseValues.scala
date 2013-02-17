package edu.luc.etl.cs313.android.model

trait DefaultOrElseValues {

  protected implicit val orElseBoolean = false

  protected implicit val orElseInt = -1
}