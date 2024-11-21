package edu.ntnu.idi.idatt.console;

import java.util.List;

/**
 * Represents a table data with headers and data.
 * Used to display data in a table found in DisplayManager.
 *
 * @see DisplayManager
 * @since 0.0.1
 * @author yazanzarka
 */
public record TableData(List<String> headers, List<List<String>> data) {

}