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

public class AgregarPanelSolar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private Casa casa;
	JSpinner spnEnergia;

	public AgregarPanelSolar(Casa casa) {
		setResizable(false);
		setTitle("Agregar nuevo panel");
		setBounds(100, 100, 271, 162);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNombre = new JLabel("Nombre del panel:");
			lblNombre.setBounds(17, 13, 113, 14);
			contentPanel.add(lblNombre);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setBounds(107, 10, 138, 20);
			txtNombre.setHorizontalAlignment(SwingConstants.LEFT);
			contentPanel.add(txtNombre);
			txtNombre.setColumns(20);
		}
		{
			JLabel lblConsumo = new JLabel("Orientacion:");
			lblConsumo.setBounds(17, 38, 78, 14);
			contentPanel.add(lblConsumo);
		}
		{
			spnEnergia = new JSpinner();
			spnEnergia.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spnEnergia.setBounds(118, 63, 78, 20);
			contentPanel.add(spnEnergia);
		}
		{
			JLabel lblNewLabel = new JLabel("Energia a producir:");
			lblNewLabel.setBounds(17, 66, 96, 14);
			contentPanel.add(lblNewLabel);
		}
		
		JComboBox cmbOrientacion = new JComboBox();
		cmbOrientacion.setModel(new DefaultComboBoxModel(new String[] {"norte", "sur"}));
		cmbOrientacion.setSelectedIndex(0);
		cmbOrientacion.setBounds(85, 38, 78, 20);
		contentPanel.add(cmbOrientacion);
		
		JLabel lblNewLabel_1 = new JLabel("kwh");
		lblNewLabel_1.setBounds(199, 66, 46, 14);
		contentPanel.add(lblNewLabel_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						casa.addPanel(txtNombre.getText(), cmbOrientacion.getSelectedItem().toString(), spnEnergia.getValue().toString());
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
