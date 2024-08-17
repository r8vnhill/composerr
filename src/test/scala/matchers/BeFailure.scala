package cl.ravenhill.composerr
package matchers

import org.scalatest.matchers.{MatchResult, Matcher}

import scala.util.Try

/** A custom matcher that checks if a `Try` is a failure.
 *
 * This matcher verifies whether a `Try` instance represents a failed result. It produces a `MatchResult` indicating
 * whether the `Try` was a failure or not.
 */
def beFailure: Matcher[Try[?]] = (left: Try[?]) => MatchResult(
  left.isFailure,
  s"$left was not a failure",
  s"$left was a failure"
)
