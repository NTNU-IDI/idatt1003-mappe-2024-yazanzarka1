package edu.ntnu.idi.idatt.console;

/**
 * Interface for classes that can be represented as a table.
 *
 * @author yazanzarka
 * @see TableData
 * @since 0.0.5
 */
public interface TableRepresentable {

  /**
   * Serialize an object to a tableData object with headers and rows.
   *
   * @return TableData with headers and rows
   * @see TableData
   */
  TableData toTableData();
}
