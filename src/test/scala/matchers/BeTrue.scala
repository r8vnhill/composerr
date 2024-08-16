package cl.ravenhill.composerr
package matchers

import org.scalatest.matchers.*

/** A custom matcher that checks if a given Boolean value is true.
 *
 * The matcher returns a [[MatchResult]] indicating whether the Boolean value was true. If the value is false, it will
 * produce a failure message stating that the value was not true.
 */
def beTrue: Matcher[Boolean] = (left: Boolean) => MatchResult(
  left,
  s"$left was not true",
  s"$left was true"
)
