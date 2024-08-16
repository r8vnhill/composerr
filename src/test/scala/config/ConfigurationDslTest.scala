package cl.ravenhill.composerr
package config

import matchers.*
import ConfigurationDsl.*

class ConfigurationDslTest extends AbstractComposerrTest {
  
  "The ConfigurationDsl" - {
    "should be able to access the current configuration" in {
      val config = configuration {
        given ctx: ConfigurationContext = ConfigurationContext(new Configuration {})
        ctx
      }
      config.skipChecks should beFalse
      config.shortCircuit should beFalse
    }

    "should be able to modify the configuration" in {
      given ctx: ConfigurationContext = ConfigurationContext(new Configuration {})
      configuration {
        skipChecks(true)
        shortCircuit(true)
      }
      ctx.skipChecks should beTrue
      ctx.shortCircuit should beTrue
    }
  }
}
