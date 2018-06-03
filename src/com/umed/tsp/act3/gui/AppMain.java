package com.umed.tsp.act3.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Vector;

public class AppMain {
	private JFrame frmAcondicionamientoF;
	private JTextField txtNombreProducto;
	private JTextField txtPeso;
	private JTextField txtCosto;
	private JTextField txtCantidad;
	private JTable tblDatos;
	private JList<String> lstUnidadMedida;
	private JFileChooser fileChooser;

	/**
	 * Returns the Application Frame
	 * @return JFrame
	 */
	public JFrame getFrame() {
		return frmAcondicionamientoF;
	}

	/**
	 * Sets a instance of the Frame
	 * @param frame The frame instance
	 */
	public void setFrame(JFrame frame) {
		this.frmAcondicionamientoF = frame;
	}

	/**
	 * Create the application.
	 */
	public AppMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		fileChooser = new JFileChooser(new File("."));
		frmAcondicionamientoF = new JFrame();
		frmAcondicionamientoF.setTitle("Tabla de Datos");
		frmAcondicionamientoF.setBounds(100, 100, 700, 679);
		frmAcondicionamientoF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAcondicionamientoF.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel pnlDatos = new JPanel();
		pnlDatos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frmAcondicionamientoF.getContentPane().add(pnlDatos, BorderLayout.NORTH);
		pnlDatos.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.MIN_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(82dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.MIN_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(79dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.MIN_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.MIN_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.MIN_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.MIN_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.MIN_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.MIN_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.MIN_ROWSPEC,
				RowSpec.decode("5dlu"),}));
		
		JLabel banner = new JLabel("");
		banner.setIcon(new ImageIcon(AppMain.class.getResource("/resources/gym-membership-banner.jpg")));
		pnlDatos.add(banner, "2, 2, 9, 1");
		
		JLabel lblNombreDelProducto = new JLabel("Nombre del Producto");
		pnlDatos.add(lblNombreDelProducto, "2, 4, 3, 1, right, default");
		
		txtNombreProducto = new JTextField();
		pnlDatos.add(txtNombreProducto, "6, 4, 3, 1");
		txtNombreProducto.setColumns(10);
		
		JButton btnAbrir = new JButton("Abrir");
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showLoadDialog();
			}
		});
		pnlDatos.add(btnAbrir, "10, 4");
		
		JLabel lblPeso = new JLabel("Peso");
		pnlDatos.add(lblPeso, "2, 6, right, default");
		
		txtPeso = new JTextField();
		txtPeso.setToolTipText("Peso del Producto");
		pnlDatos.add(txtPeso, "4, 6");
		txtPeso.setColumns(10);
		
		JLabel lblUnidadDeMedida = new JLabel("Unidad de Medida");
		pnlDatos.add(lblUnidadDeMedida, "6, 6");
		
		lstUnidadMedida = new JList<String>();
		lstUnidadMedida.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lstUnidadMedida.setModel(new UnidadMedidaModel<String>());
		pnlDatos.add(lstUnidadMedida, "8, 6, 1, 7, fill, fill");
		
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!validateForm()) {
					return;
				}
				ProductosModel modelo = (ProductosModel) tblDatos.getModel();
				modelo.addRow(new Object[] {
					txtNombreProducto.getText(),
					txtPeso.getText(),
					lstUnidadMedida.getSelectedIndex(),
					txtCantidad.getText(),
					txtCosto.getText()
				});
				
				clearFields();
				JOptionPane.showMessageDialog(null, "Los datos fueron insertados correctamente", "Mensaje de Advertencia", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		pnlDatos.add(btnInsertar, "10, 6");
		
		JLabel lblCosto = new JLabel("Costo");
		pnlDatos.add(lblCosto, "2, 8, right, default");
		
		txtCosto = new JTextField();
		txtCosto.setToolTipText("Costo del Producto");
		pnlDatos.add(txtCosto, "4, 8");
		txtCosto.setColumns(10);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductosModel modelo = (ProductosModel) tblDatos.getModel();
				int rowSelected = validateTableSelection();
				if (rowSelected >= 0) {
					modelo.removeRow(rowSelected);
					
					clearFields();
					JOptionPane.showMessageDialog(null, "Los datos fueron eliminados correctamente", "Mensaje de Advertencia", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		pnlDatos.add(btnEliminar, "10, 8");
		
		JLabel lblCantidad = new JLabel("Cantidad");
		pnlDatos.add(lblCantidad, "2, 10, right, default");
		
		txtCantidad = new JTextField();
		pnlDatos.add(txtCantidad, "4, 10");
		txtCantidad.setColumns(10);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!validateForm()) {
					return;
				}
				ProductosModel modelo = (ProductosModel) tblDatos.getModel();
				int rowSelected = validateTableSelection();
				if (rowSelected >= 0) {
					modelo.setValueAt(txtNombreProducto.getText(), rowSelected, 0);
					modelo.setValueAt(txtPeso.getText(), rowSelected, 1);
					modelo.setValueAt(lstUnidadMedida.getSelectedIndex(), rowSelected, 2);
					modelo.setValueAt(txtCantidad.getText(), rowSelected, 3);
					modelo.setValueAt(txtCosto.getText(), rowSelected, 4);
					
					clearFields();
					JOptionPane.showMessageDialog(null, "Los datos fueron actualizados correctamente", "Mensaje de Advertencia", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		pnlDatos.add(btnActualizar, "10, 10");
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSaveDialog();
			}
		});
		pnlDatos.add(btnGuardar, "10, 12");
		
		JPanel pnlPrincipal = new JPanel();
		pnlPrincipal.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frmAcondicionamientoF.getContentPane().add(pnlPrincipal);
		pnlPrincipal.setLayout(new BorderLayout(0, 0));
		
		tblDatos = new JTable();
		tblDatos.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JTable table = (JTable) e.getSource();
		        if (table.getSelectedRow() != -1) {
		            int selectedRow = table.getSelectedRow();
		            
		            txtNombreProducto.setText((String) table.getModel().getValueAt(selectedRow, 0));
		    		txtPeso.setText((String) table.getModel().getValueAt(selectedRow, 1));
		    		lstUnidadMedida.setSelectedIndex((int) table.getModel().getValueAt(selectedRow, 2));
		    		txtCantidad.setText((String) table.getModel().getValueAt(selectedRow, 3));
		    		txtCosto.setText((String) table.getModel().getValueAt(selectedRow, 4));
		        }
			}
		});
		tblDatos.setCellSelectionEnabled(true);
		tblDatos.setModel(new ProductosModel());
		
		JScrollPane scrollPane = new JScrollPane(tblDatos);
		pnlPrincipal.add(scrollPane, BorderLayout.CENTER);
	}
	
	private void showSaveDialog() {
		if (fileChooser.showSaveDialog(frmAcondicionamientoF) == JFileChooser.APPROVE_OPTION ) {
            saveTableData(fileChooser.getSelectedFile());
        }
	}
	
	private void showLoadDialog() {
		if (fileChooser.showOpenDialog(frmAcondicionamientoF) == JFileChooser.APPROVE_OPTION ) {
			loadTableData(fileChooser.getSelectedFile());
        }
	}
	
	private void saveTableData(File file) {
		ProductosModel tableModel = (ProductosModel) tblDatos.getModel();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(tableModel.getDataVector());
            out.close();
            JOptionPane.showMessageDialog(null, "Los datos fueron guardados correctamente", "Mensaje de Advertencia", JOptionPane.INFORMATION_MESSAGE);
            clearTableData();
        } catch (Exception ex) {
        	JOptionPane.showMessageDialog(null, "No se pudo guardar la información", "Mensaje de Advertencia", JOptionPane.ERROR_MESSAGE);
        	ex.printStackTrace();
        }
    }
	
	@SuppressWarnings("unchecked")
	private void loadTableData(File file) {
		ProductosModel tableModel = (ProductosModel) tblDatos.getModel();
		clearTableData();
        try {
        	ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			Vector<Object> rowData = (Vector<Object>) in.readObject();
        	Iterator<Object> itr = rowData.iterator();
        	while (itr.hasNext()) {
        		tableModel.addRow((Vector<Object>) itr.next());
        	}
        	in.close();
        }
        catch (Exception ex) {
        	JOptionPane.showMessageDialog(null, "No se pudo cargar la información", "Mensaje de Advertencia", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
	
	private void clearTableData() {
		ProductosModel tableModel = (ProductosModel) tblDatos.getModel();
		tableModel.setRowCount(0);
	}
	
	private int validateTableSelection() {
		if (tblDatos.getSelectedRow() == -1) {
			if (tblDatos.getRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "No hay datos para actualizar", "Mensaje de Advertencia", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Seleccione un registro", "Mensaje de Advertencia", JOptionPane.ERROR_MESSAGE);
			}
			return -1;
		}
		
		return tblDatos.getSelectedRow();
	}
	
	private boolean validateForm() {
		if (txtNombreProducto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El campo nombre del producto esta vacio", "Mensaje de Advertencia", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (txtPeso.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El campo peso esta vacío", "Mensaje de Advertencia", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (txtCosto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El campo costo esta vacío", "Mensaje de Advertencia", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (txtCantidad.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El campo cantidad esta vacío", "Mensaje de Advertencia", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (lstUnidadMedida.getSelectedIndex() < 0) {
			JOptionPane.showMessageDialog(null, "Seleccione una unidad de medida", "Mensaje de Advertencia", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	}

	private void clearFields() {
		txtNombreProducto.setText("");
		txtPeso.setText("");
		txtCantidad.setText("");
		txtCosto.setText("");
		lstUnidadMedida.clearSelection();
	}
}
