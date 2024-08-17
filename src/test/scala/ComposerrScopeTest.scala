package cl.ravenhill.composerr

import constraints.iterable.arbHaveSize

import org.scalacheck.{Arbitrary, Gen}
import matchers.{beSuccess, beFailure}

class ComposerrScopeTest extends AbstractComposerrTest {
  "A ComposerrScope" - {
    "should have a StringScope that" - {
      "can be created with a message" in {
        forAll { (message: String) =>
          val outerScope = ComposerrScope()
          val scope = outerScope.StringScope(message)
          scope.toString should be(s"StringScope($message)")
        }
      }

      "when validating a must clause" - {
        "should add a success or a failure to the outer scope" in {
          forAll(
            Arbitrary.arbString.arbitrary,
            arbHaveSize[Any](),
            Gen.listOfN(10, Gen.listOfN(10, Arbitrary.arbString.arbitrary))
          ) { (message, constraint, vss) =>
            val outerScope = ComposerrScope()
            val scope = outerScope.StringScope(message)
            vss.foreach { vs =>
              import scope.must
              vs must constraint
              if (constraint.validator(vs)) {
                outerScope.results.last should beSuccess
              } else {
                outerScope.results.last should beFailure
              }
            }
          }
        }
      }
      
      "when validating a mustNot clause" - {
        "should add a success or a failure to the outer scope" in {
          forAll(
            Arbitrary.arbString.arbitrary,
            arbHaveSize[Any](),
            Gen.listOfN(10, Gen.listOfN(10, Arbitrary.arbString.arbitrary))
          ) { (message, constraint, vss) =>
            val outerScope = ComposerrScope()
            val scope = outerScope.StringScope(message)
            vss.foreach { vs =>
              import scope.mustNot
              vs mustNot constraint
              if (!constraint.validator(vs)) {
                outerScope.results.last should beSuccess
              } else {
                outerScope.results.last should beFailure
              }
            }
          }
        }
      }
      
      "when validating a constraint" - {
        "should add a success or a failure to the outer scope" in {
          forAll(
            Arbitrary.arbString.arbitrary,
            Gen.listOfN(10, Gen.listOfN(10, Arbitrary.arbString.arbitrary))
          ) { (message, vss) =>
            val outerScope = ComposerrScope()
            val scope = outerScope.StringScope(message)
            vss.foreach { vs =>
              scope.constraint(vs.nonEmpty)
              if (vs.nonEmpty) {
                outerScope.results.last should beSuccess
              } else {
                outerScope.results.last should beFailure
              }
            }
          }
        }
      }
    }
  }
}
