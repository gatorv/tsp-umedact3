package com.umed.tsp.act3.gui;

import javax.swing.AbstractListModel;

public class UnidadMedidaModel<E> extends AbstractListModel<E> {
	private static final long serialVersionUID = 5285518188590830781L;
	private String[] values = new String[] {"Pieza", "Frasco", "Botella", "Caja"};
	
	@Override
	public int getSize() {
		return values.length;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E getElementAt(int index) {
		return (E) values[index];
	}

}
