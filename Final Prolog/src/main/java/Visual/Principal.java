package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;

import Logico.Casa;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.border.EtchedBorder;
import java.awt.Window.Type;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;


public class Principal extends JFrame {

	private JPanel MainPanel;
	private DefaultListModel<String> modelListaMiembros;
	private DefaultListModel<String> modelListaPuertas;
	private DefaultListModel<String> modelListaElectronicos;
	private DefaultListModel<String> modelListaZonas;
	private DefaultListModel<String> modelListaMiembrosZona;
	private DefaultListModel<String> modelListaElectronicosZona;
	private DefaultListModel<String> modelListaPaneles;
	private DefaultListModel<String> modelListaBasureros;
	private DefaultListModel<String> modelListaLlaves;
	private String miembro;
	private String puerta;
	private String electronico;
	private String zona;
	private String miembroZona;
	private String electronicoZona;
	private String llaveAgua;
	private String basurero;
	String panelSelected;
	String horaDiaActual;
	JRadioButton radFamiliarDurmiendo;
	JLabel txtTotalDormidos;
	Thread th_dormirPersonas;
	Thread th_verDormidos;
	JRadioButton radEstadoSalir;
	JRadioButton radPuertasAbiertas;
	JRadioButton radPuertasCerradas;
	JButton btnModoManualPuertas;
	JButton btnModoAutomaticoPuertas;
	JButton btnModoManualElectronicos;
	JButton btnModoAutomaticoElectronicos; 
	JButton btnQuitarMiembro;
	JButton btnEntrarZona;
	JLabel txtConsumo;
	JLabel txtConsumoTotal;
	JSpinner spnHoraDia;
	JLabel txtSugerenciaPuerta; 
	JButton btnProgramarElectro;
	JSpinner spnApagadoElectro;
	JSpinner spnTemperatura;
	JLabel txtAguaConsumidaLlave;
	JLabel txtTotalCostoAgua;
	JLabel txtTotalAguaConsumida;
	JButton btnAgregarPanel;
	JButton btnQuitarPanel;
	JButton btnCambiarOrientacionPanel;
	JSpinner spnAnguloPanel;
	JList listPaneles;
	JComboBox cmbOrientacion;
	JLabel txtEnergiaTotalProducida;
	JLabel txtSugerenciaPanelNorte;
	JLabel txtSugerenciaPanelSur;
	JLabel txtAvisoConsumo;
	JList listZafacones;
	JLabel txtCapacidadZafacon;
	JLabel txtBasuraAlmacenada;
	JLabel txtSugerenciaBasura;
	JSpinner spnVolumenBasura;
	JButton btnAgregarBasura;
	JButton btnAgregarBasurero;
	JButton btnQuitarZafacon;
	private JTextField txtNombreBasura;
	JLabel txtPrecioEnergia;
	JButton btnAceptarUsoLlave;
	JSpinner spnConsumoAgua;
	JLabel txtTotalSalieron;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Casa casa = new Casa();
					Principal frame = new Principal(casa);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/*public void revisarDormidos() throws InterruptedException
	{
	
		do {
			
			if(miembro!= null)
			{
				System.out.println(miembro);
				radFamiliarDurmiendo.setSelected(c);
		
				
			}
			Thread.sleep(2000);
		}while(true);
	}
*/
	
	public Principal(Casa casa) throws InterruptedException {
		setResizable(false);
		
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1014, 728);
		MainPanel = new JPanel();
		MainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(MainPanel);
		 this.setLocationRelativeTo(null);
		MainPanel.setLayout(null);
		modelListaMiembros = new DefaultListModel<String>();
		modelListaPuertas = new DefaultListModel<String>();
		modelListaElectronicos = new DefaultListModel<String>();
		modelListaMiembrosZona = new DefaultListModel<String>();
		modelListaZonas = new DefaultListModel<String>();
		modelListaElectronicosZona = new DefaultListModel<String>();
		modelListaPaneles = new DefaultListModel<String>();
		modelListaBasureros = new DefaultListModel<String>();
		modelListaLlaves = new DefaultListModel<String>();
		
		JButton btnAgregarMiembro = new JButton("Agregar");
		btnAgregarMiembro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AgregarMiembro addMiembro = new AgregarMiembro(casa);
				addMiembro.setLocationRelativeTo(null);
				addMiembro.setModal(true);
				addMiembro.setVisible(true);
				casa.getMiembros(modelListaMiembros);
				
				//txtLitros.setText(casa.imprimir("get_consumo_total_agua", "Litro"));
				
			
				
			}
		});
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(404, 52, 135, 81);
		MainPanel.add(scrollPane_2);
		
		JList<String> listZonas = new JList<String>();
		listZonas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount() == 1)
				{
					int index = listZonas.locationToIndex(arg0.getPoint());
					zona = (String) listZonas.getModel().getElementAt(index);
					
					if(zona!=null)
					{
						casa.getMiembrosZona(modelListaMiembrosZona,zona);
						casa.getElectronicosZona(modelListaElectronicosZona,zona);
						
					}
				}
			}
		});
		listZonas.setVisibleRowCount(2);
		listZonas.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scrollPane_2.setViewportView(listZonas);
		listZonas.setModel(modelListaZonas);

		
		btnAgregarMiembro.setBounds(10, 142, 89, 23);
		MainPanel.add(btnAgregarMiembro);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 52, 135, 81);
		MainPanel.add(scrollPane);
		
		JList<String> listMiembros = new JList();
		scrollPane.setViewportView(listMiembros);
		listMiembros.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listMiembros.setVisibleRowCount(2);
		
		listMiembros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount() == 1)
				{
					
					int index = listMiembros.locationToIndex(arg0.getPoint());
					miembro = listMiembros.getModel().getElementAt(index);
					
					if(miembro!=null)
					{
						btnEntrarZona.setEnabled(true);
						radFamiliarDurmiendo.setEnabled(true);
						radFamiliarDurmiendo.setSelected(casa.nuevoQuery("dormido", miembro));
						
						radEstadoSalir.setEnabled(true);
						radEstadoSalir.setSelected(casa.nuevoQuery("salio", miembro));
						
						btnQuitarMiembro.setEnabled(true);
						
					
					}else
					{

						btnEntrarZona.setEnabled(false);
						radFamiliarDurmiendo.setEnabled(false);
						radFamiliarDurmiendo.setSelected(false);
						
						radEstadoSalir.setEnabled(false);
						radEstadoSalir.setSelected(false);
						

						btnQuitarMiembro.setEnabled(false);
					}
					
			
				}
			}
		});
		listMiembros.setModel(modelListaMiembros);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Estado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(155, 52, 184, 44);
		MainPanel.add(panel);
		
		radFamiliarDurmiendo = new JRadioButton("Durmiendo");
		radFamiliarDurmiendo.setEnabled(false);
		radFamiliarDurmiendo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(miembro != null)
				{
					
					if(radFamiliarDurmiendo.isSelected())
					{
						casa.dormir(miembro);
						casa.nuevoQuery("alerta_puertas", "");
						casa.nuevoQuery("alerta_electronicos", "");
						casa.nuevoQuery("regular_temperatura", "");
						txtTotalDormidos.setText(casa.getTotalDormidos());
						
						spnTemperatura.setValue(casa.getTemperatura());
						txtConsumoTotal.setText(String.valueOf(casa.getConsumoElectronicoTotal()));
						
					}else
					{
						
						
						casa.despertar(miembro);
						txtTotalDormidos.setText(casa.getTotalDormidos());
					
					}
					
					
				}
				
			}
		});
		/*radFamiliarDurmiendo.setEnabled(true);
		
		/*th_verDormidos = new Thread(() -> {
            try {
				revisarDormidos();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
		
		JRadioButton radEstadoSalir = new JRadioButton("Fuera");
		radEstadoSalir.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
			}
		});
		//th_verDormidos.start();*/
		
		 
	       
		
		GroupLayout gl_panel = new GroupLayout(panel);
		radEstadoSalir = new JRadioButton();
		radEstadoSalir.setEnabled(false);
		radEstadoSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(miembro != null)
				{
					if(radEstadoSalir.isSelected())
					{
						casa.salir(miembro);
						casa.nuevoQuery("alerta_puertas", "");
						casa.nuevoQuery("alerta_electronicos", "");
						txtConsumoTotal.setText(String.valueOf(casa.getConsumoElectronicoTotal()));
						txtTotalSalieron.setText(casa.getTotalFuera());
						
					}else
					{
						casa.volver(miembro);
						txtTotalSalieron.setText(casa.getTotalFuera());
					}
					
					
				}
				
			}
		});
		radEstadoSalir.setText("Fuera");
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(radFamiliarDurmiendo)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(radEstadoSalir)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(radFamiliarDurmiendo)
						.addComponent(radEstadoSalir))
					.addContainerGap(35, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 210, 135, 81);
		MainPanel.add(scrollPane_1);
		
		JList<String> listPuertas = new JList();
		listPuertas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount() == 1)
				{
					int index = listPuertas.locationToIndex(arg0.getPoint());
					puerta = listPuertas.getModel().getElementAt(index);
					
					if(puerta!=null)
					{
						//radPuertasAbiertas.setEnabled(true);
						//radPuertasCerradas.setEnabled(true);
						
						btnModoAutomaticoPuertas.setEnabled(!casa.nuevoQuery("automatico", puerta));
						btnModoManualPuertas.setEnabled(!casa.nuevoQuery("manual", puerta));
						
						radPuertasAbiertas.setSelected(casa.nuevoQuery("puerta_abierta", puerta));
						radPuertasCerradas.setSelected(casa.nuevoQuery("puerta_cerrada", puerta));
						
						if(!btnModoAutomaticoPuertas.isEnabled())
						{
							radPuertasAbiertas.setEnabled(false);
							radPuertasCerradas.setEnabled(false);
						}else if(!btnModoManualPuertas.isEnabled())
						{
							radPuertasAbiertas.setEnabled(true);
							radPuertasCerradas.setEnabled(true);
						}
						
						
						/*radFamiliarDurmiendo.setEnabled(true);
						radFamiliarDurmiendo.setSelected(casa.isDormido(miembro));
						
						radEstadoSalir.setEnabled(true);
						radEstadoSalir.setSelected(casa.isFuera(miembro));*/
						//System.out.println("todos dormidos" + casa.todosDormidos());
					
					}else
					{
						/*radFamiliarDurmiendo.setEnabled(false);
						radFamiliarDurmiendo.setSelected(false);
						
						radEstadoSalir.setEnabled(false);
						radEstadoSalir.setSelected(false);*/
					}
					
			
				}
			}
		});
		listPuertas.setVisibleRowCount(2);
		listPuertas.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scrollPane_1.setViewportView(listPuertas);
		listPuertas.setModel(modelListaPuertas);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Estado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(155, 210, 184, 81);
		MainPanel.add(panel_1);
		
		radPuertasAbiertas = new JRadioButton("Abierta");
		radPuertasAbiertas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				casa.nuevoQuery("abrir_puerta",puerta);
			}
		});
		radPuertasAbiertas.setEnabled(false);
		
		radPuertasCerradas = new JRadioButton("Cerrada");
		radPuertasCerradas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				casa.nuevoQuery("cerrar_puerta",puerta);
			}
		});
		radPuertasCerradas.setEnabled(false);
		
		btnModoManualPuertas = new JButton("Manual");
		btnModoManualPuertas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				casa.nuevoQuery("modo_manual_puerta", puerta);
				//casa.setPuertaManual(puerta);
				btnModoAutomaticoPuertas.setEnabled(!casa.isAutomatico(puerta));
				btnModoManualPuertas.setEnabled(!casa.isManual(puerta));
				radPuertasAbiertas.setEnabled(true);
				radPuertasCerradas.setEnabled(true);
				
				//casa.setManual()
				
			}
		});
		
		btnModoAutomaticoPuertas = new JButton("Automatico");
		btnModoAutomaticoPuertas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				casa.nuevoQuery("modo_automatico_puerta", puerta);
				casa.nuevoQuery("alerta_puertas", "");
				//casa.setPuertaAutomatico(puerta);
				btnModoAutomaticoPuertas.setEnabled(!casa.nuevoQuery("automatico", puerta));
				btnModoManualPuertas.setEnabled(!casa.nuevoQuery("manual", puerta));
				radPuertasAbiertas.setEnabled(false);
				radPuertasCerradas.setEnabled(false);
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(radPuertasAbiertas)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(radPuertasCerradas, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(btnModoManualPuertas)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnModoAutomaticoPuertas, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(radPuertasAbiertas)
						.addComponent(radPuertasCerradas))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnModoManualPuertas)
						.addComponent(btnModoAutomaticoPuertas))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JButton btnAgregarPuerta = new JButton("Agregar");
		btnAgregarPuerta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AgregarPuerta addPuerta = new AgregarPuerta(casa);
				addPuerta.setLocationRelativeTo(null);
				addPuerta.setModal(true);
				addPuerta.setVisible(true);

				casa.getPuertas(modelListaPuertas);
				
			}
		});
		btnAgregarPuerta.setBounds(10, 297, 89, 23);
		MainPanel.add(btnAgregarPuerta);
		
	
        
        btnQuitarMiembro = new JButton("X");
        btnQuitarMiembro.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(miembro!=null)
        		{
        			casa.nuevoQuery("quitar_miembro", miembro);
        			casa.getMiembros(modelListaMiembros);
    	
    				txtTotalSalieron.setText(casa.getTotalFuera());
    				txtTotalDormidos.setText(casa.getTotalDormidos());
        		}
        		
        	}
        });
        btnQuitarMiembro.setBounds(109, 142, 38, 23);
        MainPanel.add(btnQuitarMiembro);
        
        JPanel panel_1_1 = new JPanel();
        panel_1_1.setBorder(new TitledBorder(null, "Estado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_1_1.setBounds(155, 345, 184, 81);
        MainPanel.add(panel_1_1);
        
        JRadioButton radElectroEncendido = new JRadioButton("Encendido");
        radElectroEncendido.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		casa.nuevoQuery("encender",electronico);
        		txtConsumoTotal.setText(String.valueOf(casa.getConsumoElectronicoTotal() + " Kwh/mes"));
        		txtAvisoConsumo.setText(casa.getAvisoConsumo());
        		txtPrecioEnergia.setText(String.valueOf(casa.getPrecioElectricoTotal()+ " $RD"));
        	}
        });
        radElectroEncendido.setEnabled(false);
        
        JRadioButton radElectroApagado = new JRadioButton("Apagado");
        radElectroApagado.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		casa.nuevoQuery("apagar",electronico);
        		txtConsumoTotal.setText(String.valueOf(casa.getConsumoElectronicoTotal() + " Kwh/mes"));
        		txtAvisoConsumo.setText(casa.getAvisoConsumo());
        		txtPrecioEnergia.setText(String.valueOf(casa.getPrecioElectricoTotal()+ " $RD"));
        	}
        });
        radElectroApagado.setEnabled(false);
        
        btnModoManualElectronicos = new JButton("Manual");
        btnModoManualElectronicos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		casa.nuevoQuery("modo_manual", electronico);
				//casa.setPuertaManual(puerta);
				btnModoAutomaticoElectronicos.setEnabled(!casa.isAutomatico(electronico));
				btnModoManualElectronicos.setEnabled(!casa.isManual(electronico));
				radElectroEncendido.setEnabled(true);
				radElectroApagado.setEnabled(true);
        	}
        });
        
        btnModoAutomaticoElectronicos = new JButton("Automatico");
        btnModoAutomaticoElectronicos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		casa.nuevoQuery("modo_automatico", electronico);
			//	casa.nuevoQuery("alerta_puertas", "");
				//casa.setPuertaAutomatico(puerta);
				btnModoAutomaticoElectronicos.setEnabled(!casa.nuevoQuery("automatico", electronico));
				btnModoManualElectronicos.setEnabled(!casa.nuevoQuery("manual", electronico));
				radElectroEncendido.setEnabled(false);
				radElectroApagado.setEnabled(false);
        	}
        });
        GroupLayout gl_panel_1_1 = new GroupLayout(panel_1_1);
        gl_panel_1_1.setHorizontalGroup(
        	gl_panel_1_1.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 184, Short.MAX_VALUE)
        		.addGroup(gl_panel_1_1.createSequentialGroup()
        			.addGroup(gl_panel_1_1.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panel_1_1.createSequentialGroup()
        					.addComponent(radElectroEncendido)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(radElectroApagado, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_panel_1_1.createSequentialGroup()
        					.addComponent(btnModoManualElectronicos)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnModoAutomaticoElectronicos, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)))
        			.addContainerGap())
        );
        gl_panel_1_1.setVerticalGroup(
        	gl_panel_1_1.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 81, Short.MAX_VALUE)
        		.addGroup(gl_panel_1_1.createSequentialGroup()
        			.addGroup(gl_panel_1_1.createParallelGroup(Alignment.BASELINE)
        				.addComponent(radElectroEncendido)
        				.addComponent(radElectroApagado))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(gl_panel_1_1.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnModoManualElectronicos)
        				.addComponent(btnModoAutomaticoElectronicos))
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_1_1.setLayout(gl_panel_1_1);
        
        JScrollPane scrollPane_1_1 = new JScrollPane();
        scrollPane_1_1.setBounds(10, 343, 135, 81);
        MainPanel.add(scrollPane_1_1);
        
        JList listElectronicos = new JList();
        scrollPane_1_1.setViewportView(listElectronicos);
        listElectronicos.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		if(arg0.getClickCount() == 1)
				{
					int index = listElectronicos.locationToIndex(arg0.getPoint());
					electronico = (String) listElectronicos.getModel().getElementAt(index);
					
					if(electronico!=null)
					{
						//radPuertasAbiertas.setEnabled(true);
						//radPuertasCerradas.setEnabled(true);
						txtConsumo.setText(String.valueOf(casa.getConsumoElectronico(electronico) + " Kwh/mes"));
						
						btnModoAutomaticoElectronicos.setEnabled(!casa.nuevoQuery("automatico", electronico));
						btnModoManualElectronicos.setEnabled(!casa.nuevoQuery("manual", electronico));
						
						radElectroEncendido.setSelected(casa.nuevoQuery("encendido", electronico));
						radElectroApagado.setSelected(casa.nuevoQuery("apagado", electronico));
						
						if(!btnModoAutomaticoElectronicos.isEnabled())
						{
							radElectroEncendido.setEnabled(false);
							radElectroApagado.setEnabled(false);
						}else if(!btnModoManualElectronicos.isEnabled())
						{
							radElectroEncendido.setEnabled(true);
							radElectroApagado.setEnabled(true);
						}
						
						
						/*radFamiliarDurmiendo.setEnabled(true);
						radFamiliarDurmiendo.setSelected(casa.isDormido(miembro));
						
						radEstadoSalir.setEnabled(true);
						radEstadoSalir.setSelected(casa.isFuera(miembro));*/
						//System.out.println("todos dormidos" + casa.todosDormidos());
					
					}else
					{
						/*radFamiliarDurmiendo.setEnabled(false);
						radFamiliarDurmiendo.setSelected(false);
						
						radEstadoSalir.setEnabled(false);
						radEstadoSalir.setSelected(false);*/
					}
				}
        	}
        });
        listElectronicos.setVisibleRowCount(2);
        listElectronicos.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listElectronicos.setModel(modelListaElectronicos);
        
        JLabel lblConsumo = new JLabel("Consumo:");
        lblConsumo.setBounds(155, 425, 62, 14);
        MainPanel.add(lblConsumo);
        
        txtConsumo = new JLabel("consumido");
        txtConsumo.setBounds(207, 425, 150, 14);
        MainPanel.add(txtConsumo);
        
        JLabel lblTotalConsumido = new JLabel("Total consumido:");
        lblTotalConsumido.setBounds(155, 441, 81, 14);
        MainPanel.add(lblTotalConsumido);
        
        txtConsumoTotal = new JLabel("total");
        txtConsumoTotal.setBounds(242, 441, 115, 14);
        MainPanel.add(txtConsumoTotal);
        
    	
		/*ButtonGroup bgroup1 = new ButtonGroup();
        bgroup1.add(radEstadoSalir);
        bgroup1.add(radFamiliarDurmiendo);
        */
        ButtonGroup bgroupPuertas = new ButtonGroup();
        bgroupPuertas.add(radPuertasAbiertas);
        bgroupPuertas.add(radPuertasCerradas);
        
        ButtonGroup bgroupElectronicos = new ButtonGroup();
        bgroupPuertas.add(radElectroEncendido);
        bgroupPuertas.add(radElectroApagado);
        
        JLabel lblNewLabel_1 = new JLabel("Electronicos");
        lblNewLabel_1.setBounds(10, 330, 89, 14);
        MainPanel.add(lblNewLabel_1);
        		
        		JButton btnAgregarZona = new JButton("Agregar");
        		btnAgregarZona.addActionListener(new ActionListener() {
        			public void actionPerformed(ActionEvent arg0) {
        				AgregarZona addZona = new AgregarZona(casa);
        				addZona.setLocationRelativeTo(null);
        				addZona.setModal(true);
        				addZona.setVisible(true);
        				casa.getZonas(modelListaZonas);
        			}
        		});
        		btnAgregarZona.setBounds(404, 142, 89, 23);
        		MainPanel.add(btnAgregarZona);
        		
        		JPanel lblHoraDia = new JPanel();
        		lblHoraDia.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        		lblHoraDia.setBounds(0, 0, 366, 711);
        		MainPanel.add(lblHoraDia);
        		lblHoraDia.setLayout(null);
        		
        		JButton btnAgregarElectronico = new JButton("Agregar");
        		btnAgregarElectronico.setBounds(10, 433, 89, 23);
        		lblHoraDia.add(btnAgregarElectronico);
        		
        				JLabel lblPuertas = new JLabel("Puertas y ventanas del hogar");
        				lblPuertas.setBounds(10, 194, 147, 14);
        				lblHoraDia.add(lblPuertas);
        				
        				JLabel lblNewLabel = new JLabel("Miembros de la familia");
        				lblNewLabel.setBounds(10, 36, 135, 14);
        				lblHoraDia.add(lblNewLabel);
        				
        				JButton btnNewButton = new JButton("Configurar para una zona especifica");
        				btnNewButton.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent arg0) {
        						AgregarElectronicoZona addElectronicoZona = new AgregarElectronicoZona(electronico,casa);
                				addElectronicoZona.setLocationRelativeTo(null);
                				addElectronicoZona.setModal(true);
                				addElectronicoZona.setVisible(true);
                				casa.getElectronicosZona(modelListaElectronicosZona, zona);
                				casa.getMiembrosZona(modelListaMiembrosZona, zona);
        					}
        				});
        				btnNewButton.setBounds(10, 467, 337, 23);
        				lblHoraDia.add(btnNewButton);
        				
        				btnEntrarZona = new JButton("Entrar en zona de la casa");
        				btnEntrarZona.setEnabled(false);
        				btnEntrarZona.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent arg0) {
        						AgregarMiembroZona addMiembroZona = new AgregarMiembroZona(miembro,casa);
                				addMiembroZona.setLocationRelativeTo(null);
                				addMiembroZona.setModal(true);
                				addMiembroZona.setVisible(true);
                				casa.getElectronicosZona(modelListaElectronicosZona, zona);
                				casa.getMiembrosZona(modelListaMiembrosZona, zona);
        					}
        				});
        				btnEntrarZona.setBounds(158, 98, 177, 23);
        				lblHoraDia.add(btnEntrarZona);
        				
        				JButton btnQuitarPuerta = new JButton("X");
        				btnQuitarPuerta.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent arg0) {
        						casa.nuevoQuery("quitar_puerta", puerta);
        						casa.getPuertas(modelListaPuertas);
        					}
        				});
        				btnQuitarPuerta.setBounds(107, 298, 38, 23);
        				lblHoraDia.add(btnQuitarPuerta);
        				
        				JButton button_1 = new JButton("X");
        				button_1.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent e) {
        						casa.nuevoQuery("quitar_electronico", electronico);
        						casa.getElectronicos(modelListaElectronicos);
        						casa.getElectronicosZona(modelListaElectronicosZona, electronicoZona);
        						txtAvisoConsumo.setText(casa.getAvisoConsumo());
        					}
        				});
        				button_1.setBounds(109, 433, 38, 23);
        				lblHoraDia.add(button_1);
        				
        				JLabel lblSugerenciaPuertas = new JLabel("Sugerencia:");
        				lblSugerenciaPuertas.setBounds(158, 290, 70, 14);
        				lblHoraDia.add(lblSugerenciaPuertas);
        				
        				txtSugerenciaPuerta = new JLabel("Aqui sugerencia");
        				txtSugerenciaPuerta.setBounds(157, 307, 209, 14);
        				lblHoraDia.add(txtSugerenciaPuerta);
        				
        				JLabel lblNewLabel_5 = new JLabel("Programar apagado:");
        				lblNewLabel_5.setBounds(10, 501, 107, 14);
        				lblHoraDia.add(lblNewLabel_5);
        				
        				spnApagadoElectro = new JSpinner();
        				spnApagadoElectro.setModel(new SpinnerNumberModel(0, 0, 23, 1));
        				spnApagadoElectro.setBounds(116, 498, 48, 20);
        				lblHoraDia.add(spnApagadoElectro);
        				
        				btnProgramarElectro = new JButton("Programar");
        				btnProgramarElectro.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent e) {
        						casa.programarApagado(electronico, spnApagadoElectro.getValue().toString());
        					}
        				});
        				btnProgramarElectro.setBounds(169, 497, 83, 23);
        				lblHoraDia.add(btnProgramarElectro);
        				
        				JLabel lblNewLabel_10 = new JLabel("Paneles solares");
        				lblNewLabel_10.setBounds(10, 537, 94, 14);
        				lblHoraDia.add(lblNewLabel_10);
        				
        				btnAgregarPanel = new JButton("Agregar");
        				btnAgregarPanel.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent arg0) {
        						AgregarPanelSolar addPanel = new AgregarPanelSolar(casa);
        						addPanel.setLocationRelativeTo(null);
                				addPanel.setModal(true);
                				addPanel.setVisible(true);
                				casa.getPaneles(modelListaPaneles);
                				txtEnergiaTotalProducida.setText(casa.getEnergiaTotalProducida()+ " Kwh");
                				txtAvisoConsumo.setText(casa.getAvisoConsumo());
        					}
        				});
        				btnAgregarPanel.setBounds(10, 644, 89, 23);
        				lblHoraDia.add(btnAgregarPanel);
        				
        				btnQuitarPanel = new JButton("X");
        				btnQuitarPanel.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent e) {
        						casa.nuevoQuery("quitar_panel",panelSelected);
        						casa.getPaneles(modelListaPaneles);
        						txtEnergiaTotalProducida.setText(casa.getEnergiaTotalProducida());
        						txtAvisoConsumo.setText(casa.getAvisoConsumo());
        					}
        				});
        				btnQuitarPanel.setBounds(107, 644, 42, 23);
        				lblHoraDia.add(btnQuitarPanel);
        				
        				JScrollPane scrollPane_1_1_1 = new JScrollPane();
        				scrollPane_1_1_1.setBounds(10, 552, 135, 81);
        				lblHoraDia.add(scrollPane_1_1_1);
        				
        				listPaneles = new JList();
        				listPaneles.addMouseListener(new MouseAdapter() {
        					@Override
        					public void mouseClicked(MouseEvent arg0) {
        						if(arg0.getClickCount() == 1)
        						{
        							int index = listPaneles.locationToIndex(arg0.getPoint());
        							panelSelected = (String) listPaneles.getModel().getElementAt(index);
        							
        							if(panelSelected!=null)
        							{
        								System.out.println(casa.getOrientacionPanel(panelSelected));
        								cmbOrientacion.setSelectedItem(casa.getOrientacionPanel(panelSelected));
        								spnAnguloPanel.setValue(casa.getAnguloPanel(panelSelected));
        							}else
        							{
        								/*radFamiliarDurmiendo.setEnabled(false);
        								radFamiliarDurmiendo.setSelected(false);
        								
        								radEstadoSalir.setEnabled(false);
        								radEstadoSalir.setSelected(false);*/
        							}
        						}
        		        	}
        					
        				});
        				scrollPane_1_1_1.setViewportView(listPaneles);
        				listPaneles.setVisibleRowCount(2);
        				listPaneles.setModel(modelListaPaneles);
        				listPaneles.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        				
        				JLabel lblNewLabel_11 = new JLabel("Orientacion actual del panel");
        				lblNewLabel_11.setBounds(158, 554, 142, 14);
        				lblHoraDia.add(lblNewLabel_11);
        				
        				JLabel lblNewLabel_12 = new JLabel("Energia total producida:");
        				lblNewLabel_12.setBounds(10, 678, 135, 14);
        				lblHoraDia.add(lblNewLabel_12);
        				
        				txtEnergiaTotalProducida = new JLabel("0");
        				txtEnergiaTotalProducida.setBounds(128, 678, 238, 14);
        				lblHoraDia.add(txtEnergiaTotalProducida);
        				
        				JLabel lblNewLabel_11_1 = new JLabel("Angulo actual del panel:");
        				lblNewLabel_11_1.setBounds(158, 605, 142, 14);
        				lblHoraDia.add(lblNewLabel_11_1);
        				
        				spnAnguloPanel = new JSpinner();
        				spnAnguloPanel.addChangeListener(new ChangeListener() {
        					public void stateChanged(ChangeEvent arg0) {
        						casa.cambiarAnguloPanel(panelSelected, spnAnguloPanel.getValue().toString());
        					}
        				});
        				spnAnguloPanel.setBounds(158, 624, 46, 20);
        				lblHoraDia.add(spnAnguloPanel);
        				spnAnguloPanel.setModel(new SpinnerNumberModel(15, 15, 90, 1));
        				
        				JCheckBox chkVisitas = new JCheckBox("Modo visitantes");
        				chkVisitas.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent arg0) {
        						if(chkVisitas.isSelected())
        						{
        							casa.ActivarModoVisita();						
        						}else
        						{
        							casa.DesactivarModoVisita();						
        						}
        						
        					}
        				});
        				chkVisitas.setBounds(3, 7, 107, 23);
        				lblHoraDia.add(chkVisitas);
        				
        				JCheckBox chckbxModoEnergiaRenovable = new JCheckBox("Modo energia renovable");
        				chckbxModoEnergiaRenovable.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent arg0) {
        						if(chckbxModoEnergiaRenovable.isSelected())
        						{
        							casa.nuevoQuery("usar_paneles", "on");
        							txtAvisoConsumo.setText(casa.getAvisoConsumo());
        						}else
        						{
        							casa.nuevoQuery("usar_paneles", "off");
        							txtAvisoConsumo.setText(casa.getAvisoConsumo());
        						}
        						
        					}
        				});
        				chckbxModoEnergiaRenovable.setBounds(116, 6, 147, 23);
        				lblHoraDia.add(chckbxModoEnergiaRenovable);
        				
        				cmbOrientacion = new JComboBox();
        				cmbOrientacion.setModel(new DefaultComboBoxModel(new String[] {"norte", "sur"}));
        				cmbOrientacion.setBounds(158, 574, 105, 20);
        				lblHoraDia.add(cmbOrientacion);
        				
        				btnCambiarOrientacionPanel = new JButton("Cambiar");
        				btnCambiarOrientacionPanel.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent e) {
        						casa.cambiarOrientacionPanel(panelSelected, cmbOrientacion.getSelectedItem().toString());
        					}
        				});
        				btnCambiarOrientacionPanel.setBounds(273, 573, 71, 23);
        				lblHoraDia.add(btnCambiarOrientacionPanel);
        				
        				JButton btnOptimizarPaneles = new JButton("Optimizar paneles");
        				btnOptimizarPaneles.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent e) {
        						casa.nuevoQuery("optimizar_paneles", "");
        						spnAnguloPanel.setValue(casa.getAnguloPanel(panelSelected));
        					}
        				});
        				btnOptimizarPaneles.setBounds(158, 655, 189, 23);
        				lblHoraDia.add(btnOptimizarPaneles);
        				
        				txtTotalSalieron = new JLabel("0");
        				txtTotalSalieron.setBounds(260, 143, 48, 14);
        				lblHoraDia.add(txtTotalSalieron);
        				
        				JLabel lblFueraDeLa = new JLabel("Fuera de la casa:");
        				lblFueraDeLa.setBounds(169, 143, 120, 14);
        				lblHoraDia.add(lblFueraDeLa);
        				
        				txtTotalDormidos = new JLabel("0");
        				txtTotalDormidos.setBounds(225, 127, 48, 14);
        				lblHoraDia.add(txtTotalDormidos);
        				
        				JLabel lblTotalDormidos = new JLabel("Dormidos:");
        				lblTotalDormidos.setBounds(168, 127, 62, 14);
        				lblHoraDia.add(lblTotalDormidos);
        				
        				JLabel lblNewLabel_2 = new JLabel("Zonas de la casa");
        				lblNewLabel_2.setBounds(404, 34, 89, 14);
        				MainPanel.add(lblNewLabel_2);
        				
        				JScrollPane scrollPane_2_1 = new JScrollPane();
        				scrollPane_2_1.setBounds(549, 52, 135, 81);
        				MainPanel.add(scrollPane_2_1);
        				
        				JList listMiembrosZona = new JList();
        				listMiembrosZona.addMouseListener(new MouseAdapter() {
        					@Override
        					public void mouseClicked(MouseEvent e) {
        						if(e.getClickCount() == 1)
                				{
                					int index = listMiembrosZona.locationToIndex(e.getPoint());
                					miembroZona = (String) listMiembrosZona.getModel().getElementAt(index);
                					
                					if(miembroZona!=null)
                					{
                						
                					}
                				}
        					}
        				});
        				
        				listMiembrosZona.setVisibleRowCount(2);
        				listMiembrosZona.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        				scrollPane_2_1.setViewportView(listMiembrosZona);
        				listMiembrosZona.setModel(modelListaMiembrosZona);
        				JLabel lblNewLabel_3 = new JLabel("Miembros en la zona");
        				lblNewLabel_3.setBounds(549, 34, 97, 14);
        				MainPanel.add(lblNewLabel_3);
        				
        				JScrollPane scrollPane_2_1_1 = new JScrollPane();
        				scrollPane_2_1_1.setBounds(694, 52, 135, 81);
        				MainPanel.add(scrollPane_2_1_1);
        				
        				JList listElectronicosZona = new JList();
        				listElectronicosZona.addMouseListener(new MouseAdapter() {
        					@Override
        					public void mouseClicked(MouseEvent e) {
        						if(e.getClickCount() == 1)
                				{
                					int index = listElectronicosZona.locationToIndex(e.getPoint());
                					electronicoZona = (String) listElectronicosZona.getModel().getElementAt(index);
                					
                					if(electronicoZona!=null)
                					{
                						
                					}
                				}
        					}
        				});
        				listElectronicosZona.setVisibleRowCount(2);
        				listElectronicosZona.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        				scrollPane_2_1_1.setViewportView(listElectronicosZona);
        				listElectronicosZona.setModel(modelListaElectronicosZona);
        				JLabel lblNewLabel_3_1 = new JLabel("Electronicos controlados");
        				lblNewLabel_3_1.setBounds(694, 34, 183, 14);
        				MainPanel.add(lblNewLabel_3_1);
        				
        				JButton btnSalirDeLa = new JButton("Salir de la zona");
        				btnSalirDeLa.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent e) {
        						casa.quitarMiembroZona(miembroZona, zona);
        						casa.getMiembrosZona(modelListaMiembrosZona, zona);
        						casa.nuevoQuery("alerta_nadie_lugar", zona);
        						txtConsumoTotal.setText(String.valueOf(casa.getConsumoElectronicoTotal() + " Kw/mes"));
        		        		txtAvisoConsumo.setText(casa.getAvisoConsumo());
        		        		txtPrecioEnergia.setText(String.valueOf(casa.getPrecioElectricoTotal()+ " $RD"));
        					}
        				});
        				btnSalirDeLa.setBounds(549, 142, 135, 23);
        				MainPanel.add(btnSalirDeLa);
        				
        				JButton btnQuitar = new JButton("Quitar");
        				btnQuitar.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent e) {
        						casa.quitarElectronicoZona(electronico, zona);
        						casa.getElectronicosZona(modelListaElectronicosZona, zona);
        					}
        				});
        				btnQuitar.setBounds(694, 142, 135, 23);
        				MainPanel.add(btnQuitar);
        				
        				JButton btnQuitarZona = new JButton("X");
        				btnQuitarZona.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent e) {
        						casa.nuevoQuery("quitar_lugar", zona);
        						casa.getZonas(modelListaZonas);
        						casa.getMiembrosZona(modelListaMiembrosZona, zona);
        						casa.getElectronicosZona(modelListaElectronicosZona, zona);
        					}
        				});
        				btnQuitarZona.setBounds(501, 142, 38, 23);
        				MainPanel.add(btnQuitarZona);
        				
        				JPanel panel_3 = new JPanel();
        				panel_3.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        				panel_3.setBounds(364, 674, 644, 27);
        				MainPanel.add(panel_3);
        				
        				JLabel lblTemperatura = new JLabel("Temperatura actual de la casa:");
        				
        				spnTemperatura = new JSpinner();
        				spnTemperatura.setModel(new SpinnerNumberModel(25, 15, 38, 1));
						casa.nuevoQuery("nueva_temperatura", spnTemperatura.getValue().toString());
        				spnTemperatura.addChangeListener(new ChangeListener() {
        					public void stateChanged(ChangeEvent arg0) {
        						casa.nuevoQuery("nueva_temperatura", spnTemperatura.getValue().toString());
        					}
        				});
        				
        				JLabel lblNewLabel_6 = new JLabel("Grados");
        				
        				JLabel lblNewLabel_4 = new JLabel("Indicar la hora del dia:");
        				
        				spnHoraDia = new JSpinner();
        				spnHoraDia.setModel(new SpinnerNumberModel(0, 0, 23, 1));
        				
        				JButton btnAceptarHora = new JButton("OK");
        				btnAceptarHora.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent e) {
        						
        						horaDiaActual = spnHoraDia.getValue().toString();
        						casa.nuevoQuery("cambiar_hora", horaDiaActual);
        						txtSugerenciaPuerta.setText(casa.getSugerenciaPuertas());
        						txtSugerenciaPanelNorte.setText(casa.getSugerenciaPanelesNorte());
        						txtSugerenciaPanelSur.setText(casa.getSugerenciaPanelesSur());
        						//casa.nuevoQuery(query, arg)
        					}
        				});
        				GroupLayout gl_panel_3 = new GroupLayout(panel_3);
        				gl_panel_3.setHorizontalGroup(
        					gl_panel_3.createParallelGroup(Alignment.LEADING)
        						.addGroup(gl_panel_3.createSequentialGroup()
        							.addGap(10)
        							.addComponent(lblTemperatura)
        							.addGap(4)
        							.addComponent(spnTemperatura, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
        							.addGap(4)
        							.addComponent(lblNewLabel_6)
        							.addGap(82)
        							.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(spnHoraDia, GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(btnAceptarHora, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
        							.addGap(52))
        				);
        				gl_panel_3.setVerticalGroup(
        					gl_panel_3.createParallelGroup(Alignment.LEADING)
        						.addGroup(gl_panel_3.createSequentialGroup()
        							.addGap(4)
        							.addComponent(lblTemperatura))
        						.addGroup(gl_panel_3.createSequentialGroup()
        							.addGap(1)
        							.addComponent(spnTemperatura, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        						.addGroup(gl_panel_3.createSequentialGroup()
        							.addGap(4)
        							.addComponent(lblNewLabel_6))
        						.addGroup(gl_panel_3.createSequentialGroup()
        							.addGap(1)
        							.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
        								.addComponent(lblNewLabel_4)
        								.addComponent(spnHoraDia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        								.addComponent(btnAceptarHora)))
        				);
        				panel_3.setLayout(gl_panel_3);
        				
        				JPanel panel_4 = new JPanel();
        				panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Consumo de agua", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        				panel_4.setBounds(376, 425, 622, 87);
        				MainPanel.add(panel_4);
        				panel_4.setLayout(null);
        				
        				JLabel lblNewLabel_7 = new JLabel("Cantidad de agua consumida por esta llave:");
        				lblNewLabel_7.setBounds(10, 22, 216, 14);
        				panel_4.add(lblNewLabel_7);
        				
        				txtAguaConsumidaLlave = new JLabel("Total");
        				txtAguaConsumidaLlave.setBounds(225, 22, 387, 14);
        				panel_4.add(txtAguaConsumidaLlave);
        				
        				JLabel lblNewLabel_8 = new JLabel("Costo mensual estimado de agua:");
        				lblNewLabel_8.setBounds(10, 62, 164, 14);
        				panel_4.add(lblNewLabel_8);
        				
        				txtTotalCostoAgua = new JLabel("Total");
        				txtTotalCostoAgua.setBounds(174, 62, 438, 14);
        				panel_4.add(txtTotalCostoAgua);
        				
        				JLabel lblNewLabel_9 = new JLabel("Cantidad de agua consumida total:");
        				lblNewLabel_9.setBounds(10, 42, 175, 14);
        				panel_4.add(lblNewLabel_9);
        				
        				txtTotalAguaConsumida = new JLabel("Total");
        				txtTotalAguaConsumida.setBounds(187, 42, 425, 14);
        				panel_4.add(txtTotalAguaConsumida);
        				
        				JPanel panel_2 = new JPanel();
        				panel_2.setBorder(new TitledBorder(null, "Informacion de energia", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        				panel_2.setBounds(376, 523, 622, 118);
        				MainPanel.add(panel_2);
        				panel_2.setLayout(null);
        				
        				JLabel lblMejorAnguloPara = new JLabel("Mejor angulo para los paneles solares norte:");
        				lblMejorAnguloPara.setBounds(10, 23, 227, 14);
        				panel_2.add(lblMejorAnguloPara);
        				
        				JLabel lblMejorAnguloPara_1 = new JLabel("Mejor angulo para los paneles solares sur:");
        				lblMejorAnguloPara_1.setBounds(10, 48, 227, 14);
        				panel_2.add(lblMejorAnguloPara_1);
        				
        				JLabel lblAvisoDeConsumo = new JLabel("Aviso de consumo:");
        				lblAvisoDeConsumo.setBounds(10, 73, 90, 14);
        				panel_2.add(lblAvisoDeConsumo);
        				
        				txtSugerenciaPanelNorte = new JLabel("");
        				txtSugerenciaPanelNorte.setBounds(225, 23, 387, 14);
        				panel_2.add(txtSugerenciaPanelNorte);
        				
        				txtSugerenciaPanelSur = new JLabel("");
        				txtSugerenciaPanelSur.setBounds(216, 48, 396, 14);
        				panel_2.add(txtSugerenciaPanelSur);
        				
        				txtAvisoConsumo = new JLabel("'Ningun problema de energia'");
        				txtAvisoConsumo.setBounds(105, 73, 496, 14);
        				panel_2.add(txtAvisoConsumo);
        				
        				JLabel lblNewLabel_18 = new JLabel("Facturacion de energia estimada de este mes:");
        				lblNewLabel_18.setBounds(10, 93, 221, 14);
        				panel_2.add(lblNewLabel_18);
        				
        				txtPrecioEnergia = new JLabel("0");
        				txtPrecioEnergia.setBounds(236, 93, 318, 14);
        				panel_2.add(txtPrecioEnergia);
        				
        				JPanel panel_5 = new JPanel();
        				panel_5.setBounds(376, 167, 632, 244);
        				MainPanel.add(panel_5);
        				panel_5.setLayout(null);
        				
        				JLabel lblZafacones = new JLabel("Basureros");
        				lblZafacones.setBounds(28, 11, 97, 23);
        				panel_5.add(lblZafacones);
        				
        				JScrollPane scrollPane_1_1_2 = new JScrollPane();
        				scrollPane_1_1_2.setBounds(28, 35, 135, 81);
        				panel_5.add(scrollPane_1_1_2);
        				
        				listZafacones = new JList();
        				listZafacones.addMouseListener(new MouseAdapter() {
        					@Override
        					public void mouseClicked(MouseEvent arg0) {
        						if(arg0.getClickCount() == 1)
        						{
        							int index =listZafacones.locationToIndex(arg0.getPoint());
        							basurero = (String) listZafacones.getModel().getElementAt(index);
        							
        							if(basurero!=null)
        							{
        								System.out.println(basurero);
        								txtCapacidadZafacon.setText(casa.getCapacidadBasurero(basurero));
        								txtBasuraAlmacenada.setText(casa.totalAlmacenadoBasurero(basurero));
        								txtSugerenciaBasura.setText(casa.getSugerenciaBasurero(basurero));
        								
        							}
        						}
        					}
        				});
        				listZafacones.setVisibleRowCount(2);
        				listZafacones.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        				scrollPane_1_1_2.setViewportView(listZafacones);
        				listZafacones.setModel(modelListaBasureros);
        				
        				btnAgregarBasurero = new JButton("Agregar basurero");
        				btnAgregarBasurero.setBounds(28, 124, 135, 23);
        				panel_5.add(btnAgregarBasurero);
        				
        				JButton btnVaciarZafacon = new JButton("Vaciar");
        				btnVaciarZafacon.setBounds(28, 149, 89, 23);
        				panel_5.add(btnVaciarZafacon);
        				
        				btnQuitarZafacon = new JButton("X");
        				btnQuitarZafacon.setBounds(125, 149, 38, 23);
        				panel_5.add(btnQuitarZafacon);
        				
        				JLabel lblNewLabel_16 = new JLabel("Agregar basura:");
        				lblNewLabel_16.setBounds(10, 187, 106, 14);
        				panel_5.add(lblNewLabel_16);
        				
        				txtNombreBasura = new JTextField();
        				txtNombreBasura.setBounds(95, 183, 68, 20);
        				panel_5.add(txtNombreBasura);
        				txtNombreBasura.setColumns(10);
        				
        				JLabel lblNewLabel_17 = new JLabel("Volumen:");
        				lblNewLabel_17.setBounds(173, 187, 46, 14);
        				panel_5.add(lblNewLabel_17);
        				
        				spnVolumenBasura = new JSpinner();
        				spnVolumenBasura.setBounds(220, 184, 53, 20);
        				panel_5.add(spnVolumenBasura);
        				spnVolumenBasura.setModel(new SpinnerNumberModel(0, 0, 1000, 1));
        				
        				btnAgregarBasura = new JButton("Agregar basura");
        				btnAgregarBasura.setBounds(10, 212, 115, 23);
        				panel_5.add(btnAgregarBasura);
        				
        				txtSugerenciaBasura = new JLabel("");
        				txtSugerenciaBasura.setBounds(197, 221, 358, 14);
        				panel_5.add(txtSugerenciaBasura);
        				
        				txtBasuraAlmacenada = new JLabel("0");
        				txtBasuraAlmacenada.setBounds(266, 58, 81, 14);
        				panel_5.add(txtBasuraAlmacenada);
        				
        				txtCapacidadZafacon = new JLabel("0");
        				txtCapacidadZafacon.setBounds(235, 37, 112, 14);
        				panel_5.add(txtCapacidadZafacon);
        				
        				JLabel lblNewLabel_13 = new JLabel("Capacidad:");
        				lblNewLabel_13.setBounds(173, 37, 68, 14);
        				panel_5.add(lblNewLabel_13);
        				
        				JLabel lblNewLabel_14 = new JLabel("Total almacenado:");
        				lblNewLabel_14.setBounds(173, 58, 89, 14);
        				panel_5.add(lblNewLabel_14);
        				
        				JLabel lblNewLabel_15 = new JLabel("Sugerencia:");
        				lblNewLabel_15.setBounds(135, 221, 75, 14);
        				panel_5.add(lblNewLabel_15);
        				
        				JButton btnAgregarPuerta_1 = new JButton("Agregar");
        				btnAgregarPuerta_1.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent arg0) {
        						AgregarLlaveAgua addLlave = new AgregarLlaveAgua(casa);
        						addLlave.setLocationRelativeTo(null);
        						addLlave.setModal(true);
        						addLlave.setVisible(true);
        						casa.getLlavesAgua(modelListaLlaves);
        						
        					}
        				});
        				btnAgregarPuerta_1.setBounds(357, 123, 89, 23);
        				panel_5.add(btnAgregarPuerta_1);
        				
        				JButton btnQuitarPuerta_1 = new JButton("X");
        				btnQuitarPuerta_1.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent e) {
        						casa.nuevoQuery("quitar_llave", llaveAgua);
        						casa.getLlavesAgua(modelListaLlaves);
        					}
        				});
        				btnQuitarPuerta_1.setBounds(454, 124, 38, 23);
        				panel_5.add(btnQuitarPuerta_1);
        				
        				JLabel lblLlavesDeAgua = new JLabel("Llaves de agua");
        				lblLlavesDeAgua.setBounds(357, 15, 97, 23);
        				panel_5.add(lblLlavesDeAgua);
        				
        				JScrollPane scrollPane_1_1_2_1 = new JScrollPane();
        				scrollPane_1_1_2_1.setBounds(357, 35, 135, 81);
        				panel_5.add(scrollPane_1_1_2_1);
        				
        				JList listLlavesAgua = new JList();
        				listLlavesAgua.addMouseListener(new MouseAdapter() {
        					@Override
        					public void mouseClicked(MouseEvent arg0) {
        						if(arg0.getClickCount() == 1)
        						{
        							int index =listLlavesAgua.locationToIndex(arg0.getPoint());
        							llaveAgua = (String) listLlavesAgua.getModel().getElementAt(index);
        							
        							if(llaveAgua!=null)
        							{
        								
        								txtAguaConsumidaLlave.setText(String.valueOf(casa.getConsumoAgua(llaveAgua)));
        								spnConsumoAgua.setValue(casa.getTiempoLlave(llaveAgua));
        								
        							}
        						}
        					}
        				});
        				listLlavesAgua.setVisibleRowCount(2);
        				listLlavesAgua.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        				scrollPane_1_1_2_1.setViewportView(listLlavesAgua);
        				
        				listLlavesAgua.setModel(modelListaLlaves);
        				
        				spnConsumoAgua = new JSpinner();
        				spnConsumoAgua.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
        				spnConsumoAgua.setBounds(502, 55, 53, 20);
        				panel_5.add(spnConsumoAgua);
        				
        				JLabel lblNewLabel_17_1 = new JLabel("Tiempo de uso");
        				lblNewLabel_17_1.setBounds(502, 34, 106, 14);
        				panel_5.add(lblNewLabel_17_1);
        				
        				JLabel lblNewLabel_19 = new JLabel("Minutos");
        				lblNewLabel_19.setBounds(565, 58, 46, 14);
        				panel_5.add(lblNewLabel_19);
        				
        			    btnAceptarUsoLlave = new JButton("Aceptar");
        			    btnAceptarUsoLlave.addActionListener(new ActionListener() {
        			    	public void actionPerformed(ActionEvent e) {
        			    		
        			    		txtTotalCostoAgua.setText(casa.getFacturaAgua()+" RD$");
        			    		casa.setConsumoAgua(llaveAgua, Integer.parseInt(spnConsumoAgua.getValue().toString()));
        			    		txtAguaConsumidaLlave.setText(String.valueOf(casa.getConsumoAgua(llaveAgua) + " m3"));
        			    		txtTotalAguaConsumida.setText(String.valueOf(casa.getConsumoTotalAgua(llaveAgua) + " m3"));
        			    		//txtTotalCostoAgua.setText(text);
        			    	}
        			    });
        				btnAceptarUsoLlave.setBounds(502, 83, 89, 23);
        				panel_5.add(btnAceptarUsoLlave);
        				
        				btnAgregarBasura.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent e) {
        						if(basurero!=null)
        						{
        							casa.addBasura(basurero, txtNombreBasura.getText(), spnVolumenBasura.getValue().toString());
            						txtBasuraAlmacenada.setText(casa.totalAlmacenadoBasurero(basurero));
            						txtSugerenciaBasura.setText(casa.getSugerenciaBasurero(basurero));
        						}
        						
        					}
        				});
        				btnQuitarZafacon.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent arg0) {
        						casa.nuevoQuery("quitar_zafacon", basurero);
        						casa.getBasureros(modelListaBasureros);
        					}
        				});
        				btnVaciarZafacon.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent e) {
        						if(basurero!=null)
        						{
        							casa.nuevoQuery("vaciar_zafacon", basurero);
        							txtSugerenciaBasura.setText(casa.getSugerenciaBasurero(basurero));
        							txtBasuraAlmacenada.setText(casa.totalAlmacenadoBasurero(basurero));
        							
        						}
        						
        					}
        				});
        				btnAgregarBasurero.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent arg0) {
        						AgregarBasurero addBasurero = new AgregarBasurero(casa);
				        		addBasurero.setLocationRelativeTo(null);
				        		addBasurero.setModal(true);
				        		addBasurero.setVisible(true);
								casa.getBasureros(modelListaBasureros);
				        		 
        					}
        				});
        				
        				DefaultListCellRenderer renderer7 = (DefaultListCellRenderer)listZafacones.getCellRenderer();
        
        				btnAgregarElectronico.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent arg0) {
					        		AgregarElectronico addElectronico = new AgregarElectronico(casa);
					        		addElectronico.setLocationRelativeTo(null);
					        		addElectronico.setModal(true);
					        		addElectronico.setVisible(true);
									casa.getElectronicos(modelListaElectronicos);
									txtConsumoTotal.setText(String.valueOf(casa.getConsumoElectronicoTotal() + " Kwh/mes"));
									txtAvisoConsumo.setText(casa.getAvisoConsumo());
									txtPrecioEnergia.setText(String.valueOf(casa.getPrecioElectricoTotal()+ " $RD"));

        					}
        				});
        
        DefaultListCellRenderer renderer = (DefaultListCellRenderer)listMiembros.getCellRenderer();
		renderer.setHorizontalAlignment(JLabel.CENTER);
		
		DefaultListCellRenderer renderer2 = (DefaultListCellRenderer)listPuertas.getCellRenderer();
		renderer2.setHorizontalAlignment(JLabel.CENTER);
		
		DefaultListCellRenderer renderer3 = (DefaultListCellRenderer)listElectronicos.getCellRenderer();
		renderer3.setHorizontalAlignment(JLabel.CENTER);
		
		DefaultListCellRenderer renderer4 = (DefaultListCellRenderer)listPaneles.getCellRenderer();
		renderer4.setHorizontalAlignment(JLabel.CENTER);
		
		DefaultListCellRenderer renderer5 = (DefaultListCellRenderer)listMiembrosZona.getCellRenderer();
		renderer5.setHorizontalAlignment(JLabel.CENTER);
		
		DefaultListCellRenderer renderer6 = (DefaultListCellRenderer)listElectronicosZona.getCellRenderer();
		renderer6.setHorizontalAlignment(JLabel.CENTER);
		
		renderer7.setHorizontalAlignment(JLabel.CENTER);
		
		DefaultListCellRenderer renderer8 = (DefaultListCellRenderer)listLlavesAgua.getCellRenderer();
		renderer8.setHorizontalAlignment(JLabel.CENTER);
		
	}
}
