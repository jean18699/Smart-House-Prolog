package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logico.Casa;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AgregarElectronico extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private Casa casa;
	JSpinner spnConsumo;

	public AgregarElectronico(Casa casa) {
		setTitle("Agregar nuevo electronico");
		setBounds(100, 100, 335, 174);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNombre = new JLabel("Nombre del electronico:");
			lblNombre.setBounds(17, 13, 113, 14);
			contentPanel.add(lblNombre);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setBounds(135, 10, 166, 20);
			txtNombre.setHorizontalAlignment(SwingConstants.LEFT);
			contentPanel.add(txtNombre);
			txtNombre.setColumns(20);
		}
		{
			JLabel lblConsumo = new JLabel("Consumo:");
			lblConsumo.setBounds(82, 38, 48, 14);
			contentPanel.add(lblConsumo);
		}
		{
			spnConsumo = new JSpinner();
			spnConsumo.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spnConsumo.setBounds(135, 35, 78, 20);
			contentPanel.add(spnConsumo);
		}
		
		JLabel Kw = new JLabel("Kwh");
		Kw.setBounds(219, 38, 46, 14);
		contentPanel.add(Kw);
		
		JLabel lblNewLabel = new JLabel("Tiempo de uso diario:");
		lblNewLabel.setBounds(17, 69, 113, 14);
		contentPanel.add(lblNewLabel);
		
		JSpinner spnTiempo = new JSpinner();
		spnTiempo.setBounds(135, 66, 48, 20);
		contentPanel.add(spnTiempo);
		
		JComboBox<String> cmbTiempo = new JComboBox<String>();
		cmbTiempo.setModel(new DefaultComboBoxModel(new String[] {"Horas", "Minutos"}));
		cmbTiempo.setSelectedIndex(0);
		cmbTiempo.setBounds(193, 66, 108, 20);
		contentPanel.add(cmbTiempo);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						//casa.addElectronico(txtNombre.getText(), );
						System.out.println((int) spnConsumo.getValue());
						
						
						if(cmbTiempo.getSelectedIndex() == 0)
						{
							casa.addElectronicoHoras(txtNombre.getText(),(int) spnConsumo.getValue(),(int) spnTiempo.getValue());
								
						}else
						{
							casa.addElectronicoMinutos(txtNombre.getText(),(int) spnConsumo.getValue(),(int) spnTiempo.getValue());
						}
						//casa.addPuerta(txtNombre.getText());
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
