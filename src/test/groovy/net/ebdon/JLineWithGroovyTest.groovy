package net.ebdon.jlinewithgroovy

import groovy.test.GroovyTestCase

 /**
  * JLineWithGroovy
  *
  * Test the JLineWithGroovy class
  */
@groovy.util.logging.Log4j2('logger')
class JLineWithGroovyTest extends GroovyTestCase {

  private JLineWithGroovy jlwg

  @Override
  void setUp() {
    logger.info 'Setting up'
    super.setUp()
    jlwg = new JLineWithGroovy()
  }

  void testHasGreeting() {
    logger.info '> testHasGreeting'
    assert jlwg.greeting?.contains('\33')
    logger.info '< testHasGreeting'
  }
}
