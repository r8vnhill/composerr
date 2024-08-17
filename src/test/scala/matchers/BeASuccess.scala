package cl.ravenhill.composerr
package matchers

import org.scalatest.matchers.*

import scala.util.Try

/** A custom matcher that checks if a `Try` is a success.
 *
 * This matcher verifies whether a `Try` instance represents a successful result. It produces a `MatchResult` indicating
 * whether the `Try` was successful or not.
 */
def beSuccess: Matcher[Try[?]] = (left: Try[?]) => MatchResult(
  left.isSuccess,
  s"$left was not a success",
  s"$left was a success"
)
