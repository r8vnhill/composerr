package cl.ravenhill.composerr
package config

import matchers.*

class ConfigurationContextTest extends AbstractComposerrTest {
  
  "A ConfigurationContext" - {
    "should have a default value of false for skipChecks" in {
      val ctx = ConfigurationContext(new Configuration {})
      ctx.skipChecks should beFalse
    }

    "should have a default value of false for shortCircuit" in {
      val ctx = ConfigurationContext(new Configuration {})
      ctx.shortCircuit should beFalse
    }
    
    "should be able to set skipChecks to true" in {
      val ctx = ConfigurationContext(new Configuration {})
      ctx.setSkipChecks(true)
      ctx.skipChecks should beTrue
    }
    
    "should be able to set shortCircuit to true" in {
      val ctx = ConfigurationContext(new Configuration {})
      ctx.setShortCircuit(true)
      ctx.shortCircuit should beTrue
    }
  }
}
