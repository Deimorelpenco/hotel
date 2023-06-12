package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import dao.HuestedDao;
import dao.ReservaDao;
import modelo.Huesped;
import modelo.Reserva;
import utils.JPAUtil;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.util.List;
import java.util.Optional;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(315, 62, 313, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);
		
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();		
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
//		llenarTablaReserva();
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				

			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limpiarTabla();
				llenarTablaReservaP(txtBuscar);				
				llenarTablaReservaH(txtBuscar);
								
						
			}
		});
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tbReservas.getSelectedRow() >= 0) {
					actualizarReserva();
					limpiarTabla();
					llenarTablaReservaP(null);
				}else if (tbHuespedes.getSelectedRow() >= 0) {
					actualizarHuesped();
					limpiarTabla();
					llenarTablaReservaH(txtBuscar);
				}
			}
		});
		
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Integer filaReserva = tbReservas.getSelectedRow();
				Integer filaHuesped = tbHuespedes.getSelectedRow();
				if (filaReserva >= 0) {					
					int confirmar = JOptionPane.showConfirmDialog(null, "Desea eliminar reserva?");
					if (confirmar == JOptionPane.YES_OPTION) {						
						eliminarReserva();
						limpiarTabla();
						llenarTablaReserva();
					}
				}else if (filaHuesped >= 0) {					
					int confirmar = JOptionPane.showConfirmDialog(null, "Desea eliminar reserva?");
					if (confirmar == JOptionPane.YES_OPTION) {						
						eliminarHuesped();
						limpiarTabla();
						llenarTablaHuesped();
					}
				}
			}
		});
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}
	
	private void llenarTablaReserva() {
		EntityManager em = JPAUtil.getEntityManager();
		ReservaDao reservaDao = new ReservaDao(em);		
		List<Reserva> resultado = reservaDao.consultarTodo();
		modelo.setRowCount(0);
		try {			
			for(Reserva i: resultado) {
				modelo.addRow(new Object[] {i.getId(),i.getFechaEntrada(),i.getFechaSalida(),i.getValor(),i.getFormaPago()});
			}
		} catch (Exception e) {
			Exito exito = new Exito("Hubo un error, intentelo de nuevo"); 
			exito.setVisible(true);
			dispose();
		}		
	}
	
	private void llenarTablaHuesped() {
		EntityManager em = JPAUtil.getEntityManager();
		HuestedDao huestedDao = new HuestedDao(em);		
		List<Huesped> resultado = huestedDao.consultarTodo();		
		try {
			for(Huesped i: resultado) {
				modeloHuesped.addRow(new Object[] {i.getId(),i.getNombre(),i.getApellido(),i.getFechaNacimiento(),i.getNacionalidad(),i.getTelefono(),i.getReserva().getId()
						});
			}
		} catch (Exception e) {			
			Exito exito = new Exito("Hubo un error, intentelo de nuevo"); 
			exito.setVisible(true);
			dispose();
		}
	}
	
		
//	private void buscarReservaPorId(JTextField txtBuscar) {
//		Integer idReservaB = Integer.parseInt(txtBuscar.getText());
//		EntityManager em = JPAUtil.getEntityManager();
//		ReservaDao reservaDao = new ReservaDao(em);
//		List<Reserva> resultado = reservaDao.consultarPorId(idReservaB);
//		try {
//			for(Reserva i: resultado) {
//				modelo.addRow(new Object[] {i.getId(),i.getFechaEntrada(),i.getFechaSalida(),i.getValor(),i.getFormaPago()});								
//			}
//		} catch (Exception e) {
//			Exito exito = new Exito("Hubo un error, intentelo de nuevo"); 
//			exito.setVisible(true);
//			dispose();
//		}	
//	}
	
//	private void buscarHuespedPorApellido(JTextField txtBuscar) {
//		String ApellidoHuespedB = txtBuscar.getText();
//		EntityManager em = JPAUtil.getEntityManager();
//		HuestedDao huestedDao = new HuestedDao(em);		
//		List<Huesped> resultado = huestedDao.consultarPorApellido(ApellidoHuespedB);
//		try {
//			for(Huesped i: resultado) {
//				modeloHuesped.addRow(new Object[] {i.getId(),i.getNombre(),i.getApellido(),i.getFechaNacimiento(),i.getNacionalidad(),i.getTelefono(),i.getReserva().getId()});		
//			}
//		} catch (Exception e) {
//			Exito exito = new Exito("Hubo un error, intentelo de nuevo"); 
//			exito.setVisible(true);
//			dispose();
//		}
//		
//	}
	
	private void llenarTablaReservaP(JTextField txtBuscar) {
		Integer idReservaB;		
		try {
			idReservaB = Integer.parseInt(txtBuscar.getText());
		} catch (Exception e) {
			idReservaB=0;
		}
		
		EntityManager em = JPAUtil.getEntityManager();
		ReservaDao reservaDao = new ReservaDao(em);		
		List<Reserva> resultado = reservaDao.consultarPorParametroR(idReservaB, null, null, null, null);		
		try {
			for(Reserva i: resultado) {
				modelo.addRow(new Object[] {i.getId(),i.getFechaEntrada(),i.getFechaSalida(),i.getValor(),i.getFormaPago()});
			}
		} catch (Exception e) {
			Exito exito = new Exito("Hubo un error, intentelo de nuevo"); 
			exito.setVisible(true);
			dispose();
		}
	}
		
	private void llenarTablaReservaH(JTextField txtBuscar) {
		String apellidoB;
		apellidoB = txtBuscar.getText();
		EntityManager em = JPAUtil.getEntityManager();
		HuestedDao huestedDao = new HuestedDao(em);
		List<Huesped> resultado = huestedDao.consultarPorParametroH(null, null, apellidoB, null, null, null, null);
		try {
			for(Huesped i: resultado) {
				modeloHuesped.addRow(new Object[] {i.getId(),i.getNombre(),i.getApellido(),i.getFechaNacimiento(),i.getNacionalidad(),i.getTelefono(),i.getReserva().getId()});		
			}
		} catch (Exception e) {
			Exito exito = new Exito("Hubo un error, intentelo de nuevo"); 
			exito.setVisible(true);
			dispose();
		}
		
		
	}
	
	private void limpiarTabla() {		
		((DefaultTableModel) tbReservas.getModel()).setRowCount(0);
		((DefaultTableModel) tbHuespedes.getModel()).setRowCount(0);
	}
	
	public void actualizarReserva() {		
		Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
		.ifPresent(fila ->{			
			try {				
				String fechaE = (modelo.getValueAt(tbReservas.getSelectedRow(), 1)).toString();
				String fechaS = (modelo.getValueAt(tbReservas.getSelectedRow(), 2)).toString();
				DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate fechaEN = LocalDate.parse(fechaE.toString(), dateTimeFormatter);
				LocalDate fechaSN = LocalDate.parse(fechaS.toString(), dateTimeFormatter);
				String valor = calcularNuevoValor(fechaEN, fechaSN);
				String formaPago = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 4);
				Integer id = (Integer) (modelo.getValueAt(tbReservas.getSelectedRow(), 0));
				Reserva nuevaReserva = new Reserva(id, java.sql.Date.valueOf(fechaE), java.sql.Date.valueOf(fechaS), valor, formaPago);
				EntityManager em = JPAUtil.getEntityManager();
				ReservaDao reservaDao = new ReservaDao(em);
				em.getTransaction().begin();
				reservaDao.actualizar(nuevaReserva);				
				em.getTransaction().commit();
				em.close();				
				JOptionPane.showMessageDialog(this, String.format("Registro modificado con éxito"));		
			} catch (DateTimeException e) {
				throw new RuntimeException(e);
			}			
		});
	}
	
	public void actualizarHuesped() {
		Optional.ofNullable(modelo.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
		.ifPresent(fila ->{
			try {
				String nombre = (modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1)).toString();
				String apellido = (modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2)).toString();
				String fechaNacimiento = (modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3)).toString();
				String nacionalidad = (modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4)).toString();
				String telefono = (modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5)).toString();
				Integer id = (Integer) (modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0));
				Integer reserva_id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());
				Reserva resultado =new Reserva(reserva_id);
				Huesped nuevoHuestped = new Huesped(id, nombre, apellido, java.sql.Date.valueOf(fechaNacimiento), nacionalidad, telefono, resultado);
				EntityManager em = JPAUtil.getEntityManager();
				HuestedDao huestedDao = new HuestedDao(em);
				em.getTransaction().begin();
				huestedDao.actualizar(nuevoHuestped);				
				em.getTransaction().commit();
				em.close();				
				JOptionPane.showMessageDialog(this, String.format("Huesped modificado con éxito"));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			
		});
	}
	
	public String calcularNuevoValor(LocalDate fechaE, LocalDate fechaS) {
		if (fechaE != null && fechaS != null) {			
			int dias = (int) ChronoUnit.DAYS.between(fechaE, fechaS);
			int precio = 500;
			Integer valor;			
			valor = dias * precio;
			return valor.toString();
		}else {
			return "";
		}
	}

	public void eliminarReserva() {
		Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
		.ifPresent(fila ->{
			try {
				Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
				EntityManager em = JPAUtil.getEntityManager();
				ReservaDao reservaDao = new ReservaDao(em);				
				Reserva resultado = new Reserva(id);				
				em.getTransaction().begin();				
				reservaDao.remover(resultado);				
				em.getTransaction().commit();
				em.close();
			} catch (RollbackException e) {
				JOptionPane.showMessageDialog(this, "Antes de eliminar la reserva debe eliminar el huesped con este número de reserva");
			}
			
		});
	}
	
	public void eliminarHuesped() {
		Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
		.ifPresent(fila ->{
			Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
			EntityManager em = JPAUtil.getEntityManager();
			HuestedDao heHuestedDao = new HuestedDao(em);
			Huesped resultado = new Huesped(id);
			em.getTransaction().begin();
			heHuestedDao.remover(resultado);				
			em.getTransaction().commit();
			em.close();
		});
	}
	
//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
