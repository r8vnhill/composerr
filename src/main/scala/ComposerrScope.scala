package cl.ravenhill.composerr

import config.Configuration
import constraints.Constraint
import exceptions.ConstraintException

import scala.util.Try

class ComposerrScope(val configuration: Configuration = new Configuration {}) {
  private val _results = collection.mutable.ListBuffer.empty[Try[?]]

  def results: List[Try[?]] = _results.toList

  def failures: List[Throwable] = _results.collect { case t if t.isFailure => t.failed.get }.toList

  class StringScope(message: String) {
    val outerScope = ComposerrScope.this

    val exceptionGenerator: Option[String => ConstraintException] = None

    private def handleValidation(predicate: => Boolean): Try[_] = {
      if (!predicate) {
        if (configuration.shortCircuit) {
          throw exceptionGenerator.map(_.apply(message)).getOrElse(ConstraintException(message))
        }
        Try(throw exceptionGenerator.map(_.apply(message)).getOrElse(ConstraintException(message)))
      } else {
        Try(())
      }
    }

    extension [T](value: T) {
      infix def must[C <: Constraint[T]](constraint: C): Unit = {
        _results += handleValidation(constraint.validator(value)).map(_ => value)
      }

      infix def mustNot[C <: Constraint[T]](constraint: C): Unit = {
        _results += handleValidation(!constraint.validator(value)).map(_ => value)
      }
    }

    def constraint(predicate: => Boolean): Unit = {
      _results += handleValidation(predicate)
    }

    override def toString: String = s"StringScope($message)"
  }
}
