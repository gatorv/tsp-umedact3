package com.umed.tsp.act3;

import java.awt.EventQueue;

import com.umed.tsp.act3.gui.AppMain;

public class Main {
	/**
	 * Starts the application
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppMain window = new AppMain();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
