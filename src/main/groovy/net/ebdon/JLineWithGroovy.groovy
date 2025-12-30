package net.ebdon.jlinewithgroovy

import org.jline.reader.*
import org.jline.terminal.*

/**
 * JLineWithGroovy
 *
 * Demonstrates a minimal integration of JLine 3 with a Groovy application.
 * Methods:
 *  - main(): creates a Terminal and LineReader, prints the greeting,
 *    prompts the user with "JLine > ", reads one line of input, prints it,
 *    then closes the terminal.
 *
 * This class is intended as a lightweight example for experimenting with
 * terminal input/output using the JLine library from Groovy.
 *
 * @since 2025-12-05
 * @see org.jline.terminal.Terminal
 * @see org.jline.reader.LineReader
 */
@groovy.util.logging.Log4j2
class JLineWithGroovy {
  /**
   * ANSI-greeting. Includes escape sequence to clear screen and & home cursor.
   */
  static final String greeting = '\33[1;1H\33[2JJLineWithGroovy'

  /**
   * Program entry point.
   *
   * @param args command-line arguments (ignored)
   */
  static void main(String[] args) {
    Terminal terminal = null
    try {
      terminal = TerminalBuilder.builder()
        .system(true)
        .build()

      terminal.writer().println greeting

      LineReader reader = LineReaderBuilder.builder()
        .terminal(terminal)
        .build()

      String line = reader.readLine('JLine > ')
      terminal.with {
        writer().println("You entered: $line")
        flush()
      }
    } catch (org.jline.reader.UserInterruptException | org.jline.reader.EndOfFileException e) {
      log.info("Input aborted: ${e.message}")
    } catch (IOException exc) {
      log.error('Terminal error', exc)
    } finally {
      try {
        terminal?.close()
      } catch (IOException closeExc) {
        log.warn('Failed to close terminal', closeExc)
      }
    }
  }
}
