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
import Logico.MiembroFamilia;

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


public class Principal extends JFrame {

	private JPanel MainPanel;
	private DefaultListModel<String> modelListaMiembros;
	private DefaultListModel<String> modelListaPuertas;
	private DefaultListModel<String> modelListaElectronicos;
	private DefaultListModel<String> modelListaZonas;
	private DefaultListModel<String> modelListaMiembrosZona;
	private DefaultListModel<String> modelListaElectronicosZona;
	private String miembro;
	private String puerta;
	private String electronico;
	private String zona;
	private String miembroZona;
	private String electronicoZona;
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
	JLabel txtLitros;
	JLabel txtTotalCostoAgua;
	JLabel txtTotalGalones;
	JButton btnAgregarPanel;
	JButton btnQuitarPanel;
	JButton btnCambiarOrientacionPanel;
	JSpinner spnAnguloPanel;
	JLabel txtOrientacionPanel;
	
	
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
		setBounds(100, 100, 893, 736);
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
		
		JButton btnAgregarMiembro = new JButton("Agregar");
		btnAgregarMiembro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AgregarMiembro addMiembro = new AgregarMiembro(casa);
				addMiembro.setLocationRelativeTo(null);
				addMiembro.setModal(true);
				addMiembro.setVisible(true);
				casa.getMiembros(modelListaMiembros);
				
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
						
						spnTemperatura.setValue(casa.getTemperatura());
						txtConsumoTotal.setText(String.valueOf(casa.getConsumoElectronicoTotal()));
						
					}else
					{
						casa.despertar(miembro);
					}
					
					txtTotalDormidos.setText(String.valueOf(casa.getTotalDormidos()));
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
						
					}else
					{
						casa.volver(miembro);
					}
					
					txtTotalDormidos.setText(String.valueOf(casa.getTotalDormidos()));
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
		
		JLabel lblTotalDormidos = new JLabel("Dormidos:");
		lblTotalDormidos.setBounds(155, 133, 62, 14);
		MainPanel.add(lblTotalDormidos);
		
		txtTotalDormidos = new JLabel("total");
		txtTotalDormidos.setBounds(207, 133, 48, 14);
		MainPanel.add(txtTotalDormidos);
		txtTotalDormidos.setText(String.valueOf(casa.getTotalDormidos()));
		
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
        		txtConsumoTotal.setText(String.valueOf(casa.getConsumoElectronicoTotal()));
        	}
        });
        radElectroEncendido.setEnabled(false);
        
        JRadioButton radElectroApagado = new JRadioButton("Apagado");
        radElectroApagado.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		casa.nuevoQuery("apagar",electronico);
        		txtConsumoTotal.setText(String.valueOf(casa.getConsumoElectronicoTotal()));
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
						txtConsumo.setText(String.valueOf(casa.getConsumoElectronico(electronico)));
						
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
        txtConsumo.setBounds(207, 425, 62, 14);
        MainPanel.add(txtConsumo);
        
        JLabel lblTotalConsumido = new JLabel("Total consumido:");
        lblTotalConsumido.setBounds(155, 441, 81, 14);
        MainPanel.add(lblTotalConsumido);
        
        txtConsumoTotal = new JLabel("total");
        txtConsumoTotal.setBounds(242, 441, 46, 14);
        MainPanel.add(txtConsumoTotal);
        
    	DefaultListCellRenderer renderer = (DefaultListCellRenderer)listMiembros.getCellRenderer();
		renderer.setHorizontalAlignment(JLabel.CENTER);
		
		DefaultListCellRenderer renderer2 = (DefaultListCellRenderer)listPuertas.getCellRenderer();
		renderer2.setHorizontalAlignment(JLabel.CENTER);
		
		DefaultListCellRenderer renderer3 = (DefaultListCellRenderer)listElectronicos.getCellRenderer();
		renderer3.setHorizontalAlignment(JLabel.CENTER);
		
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
        				btnAgregarPanel.setBounds(10, 644, 89, 23);
        				lblHoraDia.add(btnAgregarPanel);
        				
        				btnQuitarPanel = new JButton("X");
        				btnQuitarPanel.setBounds(107, 644, 42, 23);
        				lblHoraDia.add(btnQuitarPanel);
        				
        				JScrollPane scrollPane_1_1_1 = new JScrollPane();
        				scrollPane_1_1_1.setBounds(10, 552, 135, 81);
        				lblHoraDia.add(scrollPane_1_1_1);
        				
        				JList listPaneles = new JList();
        				scrollPane_1_1_1.setViewportView(listPaneles);
        				listPaneles.setVisibleRowCount(2);
        				listPaneles.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        				
        				JLabel lblNewLabel_11 = new JLabel("Orientacion actual del panel:");
        				lblNewLabel_11.setBounds(158, 554, 142, 14);
        				lblHoraDia.add(lblNewLabel_11);
        				
        				btnCambiarOrientacionPanel = new JButton("Cambiar orientacion");
        				btnCambiarOrientacionPanel.setBounds(158, 571, 127, 23);
        				lblHoraDia.add(btnCambiarOrientacionPanel);
        				
        				JLabel lblNewLabel_12 = new JLabel("Energia total producida:");
        				lblNewLabel_12.setBounds(10, 678, 135, 14);
        				lblHoraDia.add(lblNewLabel_12);
        				
        				JLabel txtEnergiaTotalProducida = new JLabel("EnergiaTotal");
        				txtEnergiaTotalProducida.setBounds(128, 678, 65, 14);
        				lblHoraDia.add(txtEnergiaTotalProducida);
        				
        				JLabel lblNewLabel_11_1 = new JLabel("Angulo actual del panel:");
        				lblNewLabel_11_1.setBounds(158, 605, 142, 14);
        				lblHoraDia.add(lblNewLabel_11_1);
        				
        				spnAnguloPanel = new JSpinner();
        				spnAnguloPanel.setBounds(158, 624, 46, 20);
        				lblHoraDia.add(spnAnguloPanel);
        				spnAnguloPanel.setModel(new SpinnerNumberModel(15, 15, 90, 1));
        				
        				txtOrientacionPanel = new JLabel("Orientacion");
        				txtOrientacionPanel.setBounds(301, 554, 65, 14);
        				lblHoraDia.add(txtOrientacionPanel);
        				
        				JCheckBox checkBox = new JCheckBox("Modo visitantes");
        				checkBox.setBounds(3, 7, 107, 23);
        				lblHoraDia.add(checkBox);
        				
        				JCheckBox chckbxModoEnergiaRenovable = new JCheckBox("Modo energia renovable");
        				chckbxModoEnergiaRenovable.setBounds(116, 6, 147, 23);
        				lblHoraDia.add(chckbxModoEnergiaRenovable);
        				
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
        					}
        				});
        				btnSalirDeLa.setBounds(549, 142, 135, 23);
        				MainPanel.add(btnSalirDeLa);
        				
        				JButton btnQuitar = new JButton("Quitar");
        				btnQuitar.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent e) {
        						
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
        				panel_3.setBounds(364, 674, 523, 27);
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
        					}
        				});
        				GroupLayout gl_panel_3 = new GroupLayout(panel_3);
        				gl_panel_3.setHorizontalGroup(
        					gl_panel_3.createParallelGroup(Alignment.LEADING)
        						.addGroup(gl_panel_3.createSequentialGroup()
        							.addGap(10)
        							.addComponent(lblTemperatura)
        							.addGap(4)
        							.addComponent(spnTemperatura, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
        							.addGap(4)
        							.addComponent(lblNewLabel_6)
        							.addGap(18)
        							.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
        							.addGap(4)
        							.addComponent(spnHoraDia, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
        							.addGap(6)
        							.addComponent(btnAceptarHora, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        							.addGap(24))
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
        							.addGap(4)
        							.addComponent(lblNewLabel_4))
        						.addGroup(gl_panel_3.createSequentialGroup()
        							.addGap(1)
        							.addComponent(spnHoraDia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        						.addComponent(btnAceptarHora)
        				);
        				panel_3.setLayout(gl_panel_3);
        				
        				JPanel panel_4 = new JPanel();
        				panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Consumo de agua", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        				panel_4.setBounds(376, 212, 428, 108);
        				MainPanel.add(panel_4);
        				panel_4.setLayout(null);
        				
        				JLabel lblNewLabel_7 = new JLabel("Cantidad estimada de agua a consumir por todos los miembros en litros:");
        				lblNewLabel_7.setBounds(10, 39, 352, 14);
        				panel_4.add(lblNewLabel_7);
        				
        				txtLitros = new JLabel("Total");
        				txtLitros.setBounds(358, 39, 46, 14);
        				panel_4.add(txtLitros);
        				
        				JLabel lblNewLabel_8 = new JLabel("Costo mensual estimado de agua:");
        				lblNewLabel_8.setBounds(10, 78, 164, 14);
        				panel_4.add(lblNewLabel_8);
        				
        				txtTotalCostoAgua = new JLabel("Total");
        				txtTotalCostoAgua.setBounds(174, 78, 46, 14);
        				panel_4.add(txtTotalCostoAgua);
        				
        				JLabel lblNewLabel_9 = new JLabel("Cantidad estimada de agua a consumir por todos los miembros en galones:");
        				lblNewLabel_9.setBounds(10, 58, 363, 14);
        				panel_4.add(lblNewLabel_9);
        				
        				txtTotalGalones = new JLabel("Total");
        				txtTotalGalones.setBounds(368, 58, 46, 14);
        				panel_4.add(txtTotalGalones);
        				
        				JPanel panel_2 = new JPanel();
        				panel_2.setBorder(new TitledBorder(null, "Informacion de energia", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        				panel_2.setBounds(376, 569, 428, 94);
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
        btnAgregarElectronico.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		AgregarElectronico addElectronico = new AgregarElectronico(casa);
        		addElectronico.setLocationRelativeTo(null);
        		addElectronico.setModal(true);
        		addElectronico.setVisible(true);
				casa.getElectronicos(modelListaElectronicos);
				txtConsumoTotal.setText(String.valueOf(casa.getConsumoElectronicoTotal()));
				

        	}
        });
        
		
	}
}
