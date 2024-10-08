package cl.ravenhill.composerr
package config

import matchers.*

class ConfigurationTest extends AbstractComposerrTest {

  "A Configuration" - {
    "should have a default value of false for skipChecks" in {
      val config = new Configuration {}
      config.skipChecks should beFalse
    }

    "should have a default value of false for shortCircuit" in {
      val config = new Configuration {}
      config.shortCircuit should beFalse
    }
    
    "should be able to set skipChecks to true" in {
      val config = new Configuration {}
      config.skipChecks = true
      config.skipChecks should beTrue
    }
    
    "should be able to set shortCircuit to true" in {
      val config = new Configuration {}
      config.shortCircuit = true
      config.shortCircuit should beTrue
    }
  }
}
