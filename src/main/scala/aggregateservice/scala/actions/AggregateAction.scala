package aggregateservice.scala.actions

trait AggregateAction {

  def sum(values: String): Any

  def mean(values: String): Any

  def max(values: String): Any

}



