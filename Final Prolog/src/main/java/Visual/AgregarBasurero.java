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

public class AgregarBasurero extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private Casa casa;
	JSpinner spnConsumo;

	public AgregarBasurero(Casa casa) {
		setTitle("Agregar nueva puerta");
		setBounds(100, 100, 335, 155);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNombre = new JLabel("Nombre del basurero:");
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
			JLabel lblConsumo = new JLabel("Capacidad:");
			lblConsumo.setBounds(73, 38, 66, 14);
			contentPanel.add(lblConsumo);
		}
		{
			spnConsumo = new JSpinner();
			spnConsumo.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spnConsumo.setBounds(135, 35, 78, 20);
			contentPanel.add(spnConsumo);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						casa.addBasurero(txtNombre.getText(), spnConsumo.getValue().toString());
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
