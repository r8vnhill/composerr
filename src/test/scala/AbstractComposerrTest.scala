package cl.ravenhill.composerr

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should
import org.scalatestplus.scalacheck

abstract class AbstractComposerrTest extends AnyFreeSpec with should.Matchers with scalacheck.ScalaCheckPropertyChecks
