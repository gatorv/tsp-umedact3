package com.umed.tsp.act3.gui;

import javax.swing.table.DefaultTableModel;

public class ProductosModel extends DefaultTableModel {
	private static final long serialVersionUID = -6035672284798339661L;
	private String[] columnNames = {
		"Producto", "Peso", "Medida", "Cantidad", "Costo"
	};
	
	@SuppressWarnings("rawtypes")
	private Class[] columnTypes = {
		String.class, String.class, Integer.class, Integer.class, Integer.class
	};

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int col) {
      return columnNames[col];
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int columnIndex) {
		return columnTypes[columnIndex];
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}
}
