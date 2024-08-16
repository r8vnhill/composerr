package cl.ravenhill.composerr
package matchers

import org.scalatest.matchers.*

/** A custom matcher that checks if a given Boolean value is false.
 *
 * The matcher returns a [[MatchResult]] indicating whether the Boolean value was false. If the value is true, it will
 * produce a failure message stating that the value was not false.
 */
def beFalse: Matcher[Boolean] = (left: Boolean) => MatchResult(
  !left,
  s"$left was not false",
  s"$left was false"
)
