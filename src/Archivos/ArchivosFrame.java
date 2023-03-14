package Archivos;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ArchivosFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArchivosFrame frame = new ArchivosFrame();
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
	public ArchivosFrame() {
		setTitle("Documento");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 359);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel(" Introduzca el texto");
		lblNewLabel.setForeground(new Color(255, 128, 128));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 161, 35);
		contentPane.add(lblNewLabel);

		JTextPane txtTexto = new JTextPane();
		txtTexto.setBounds(10, 50, 308, 259);

		contentPane.add(txtTexto);

		// contentPane.add(file_chooser);
		JFileChooser file_chooser = new JFileChooser();
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnNewButton.setForeground(new Color(0, 0, 160));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Creamos el objeto JFileChooser

				// Creamos el filtro
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.TXT", "txt");
				// Indicamos el filtro
				file_chooser.setFileFilter(filtro);
				// Abrimos la vetana
				int check_status = file_chooser.showSaveDialog(null);
				// Si el usuario no da clic en aceptar
				if (check_status != JFileChooser.APPROVE_OPTION)
					JOptionPane.showMessageDialog(null, "No Se Eligio ningun fichero");
				else {
					// Caso contrario seleccionamos el fichero
					File chosen_file = file_chooser.getSelectedFile();
					if (chosen_file.exists()) {
						int response = JOptionPane.showConfirmDialog(null, //
								"El archivo ya existe, deseas remplazarlo?", //
								"Confirm", JOptionPane.YES_NO_OPTION, //
								JOptionPane.QUESTION_MESSAGE);
						if (response != JOptionPane.YES_OPTION) {
							return;
						} else {
							JOptionPane.showMessageDialog(null, "Remplazado correctamente");
						}
					}
					
					try (FileWriter fw = new FileWriter(chosen_file);) {
						JOptionPane.showMessageDialog(null, "Guardado correctamente");
						fw.write(txtTexto.getText());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

			}
		});
		btnNewButton.setBounds(328, 50, 89, 62);
		contentPane.add(btnNewButton);

		JButton ABRIR = new JButton("Abrir");
		ABRIR.setFont(new Font("Times New Roman", Font.BOLD, 12));
		ABRIR.setForeground(new Color(0, 64, 64));
		ABRIR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Creamos el objeto JFileChooser
				// JFileChooser file_chooser = new JFileChooser();
				// Creamos el filtro
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.TXT", "txt");
				// Indicamos el filtro
				file_chooser.setFileFilter(filtro);
				// Abrimos la vetana
				int check_status = file_chooser.showOpenDialog(null);
				// Si el usuario no da clic en aceptar
				if (check_status != JFileChooser.APPROVE_OPTION)
					JOptionPane.showMessageDialog(null, "No Se Eligio ningun fichero");
				else {
					// Caso contrario seleccionamos el fichero
					File chosen_file = file_chooser.getSelectedFile();

					try (FileReader fr = new FileReader(chosen_file);) {

						String text = "";
						int valor = fr.read();
						while (valor != -1) {
							text = text + (char) valor;
							valor = fr.read();
						}
						txtTexto.setText(text);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		ABRIR.setBounds(328, 121, 89, 62);
		contentPane.add(ABRIR);

		JButton NUEVO = new JButton("Nuevo");
		NUEVO.setFont(new Font("Times New Roman", Font.BOLD, 12));
		NUEVO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTexto.setText("");
			}
		});
		NUEVO.setForeground(new Color(128, 0, 0));
		NUEVO.setBounds(328, 194, 89, 62);
		contentPane.add(NUEVO);

		JButton propiedadesArchivo = new JButton("Ver Propiedades");
		propiedadesArchivo.setFont(new Font("Times New Roman", Font.BOLD, 12));
		propiedadesArchivo.setForeground(new Color(128, 0, 128));
		propiedadesArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				long time;
				String fileType;

				File chosen_file = file_chooser.getSelectedFile();

				if (file_chooser.getSelectedFile() == null)
					JOptionPane.showMessageDialog(null, "No Se Eligio ningun fichero");
				else {
					time = chosen_file.lastModified();

					fileType = file_chooser.getTypeDescription(chosen_file);
					JOptionPane.showMessageDialog(null,
							"Nombre del archivo: " + chosen_file.getName() + "\n" + "Ruta: " + chosen_file.getPath()
									+ "\n" + "Tamaño: " + chosen_file.length() + " bytes \n" + "Tipo:" + fileType + "\n"
									+ "Fecha de modificacion: " + new Date(time));
				}
			}
		});
		propiedadesArchivo.setBounds(427, 51, 118, 60);
		contentPane.add(propiedadesArchivo);

		JButton contarPalabras = new JButton("Contar Palabras");
		contarPalabras.setFont(new Font("Times New Roman", Font.BOLD, 12));
		contarPalabras.setForeground(new Color(0, 128, 128));
		contarPalabras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text;
				text = txtTexto.getText();
				StringTokenizer st = new StringTokenizer(text);
				JOptionPane.showMessageDialog(null, "Número de palabras: " + st.countTokens());

			}
		});
		contarPalabras.setBounds(427, 121, 118, 62);
		contentPane.add(contarPalabras);

		JButton contarCaracteres = new JButton("Contar Caracteres");
		contarCaracteres.setForeground(new Color(64, 0, 0));
		contarCaracteres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Total de caracteres: " + txtTexto.getText().length());
			}
		});
		contarCaracteres.setFont(new Font("Times New Roman", Font.BOLD, 12));
		contarCaracteres.setBounds(427, 194, 118, 62);
		contentPane.add(contarCaracteres);

		JButton cambiarMayuscular = new JButton("Cambiar a Mayusculas");
		cambiarMayuscular.setFont(new Font("Times New Roman", Font.BOLD, 12));
		cambiarMayuscular.setForeground(new Color(128, 128, 192));
		cambiarMayuscular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textoMayus;
				if (txtTexto.getText() == "")
					JOptionPane.showMessageDialog(null, "El cuadro de texto esta vacio");
				else {
					textoMayus = txtTexto.getText().toUpperCase();

					txtTexto.setText(textoMayus);
				}
			}
		});
		cambiarMayuscular.setBounds(555, 50, 156, 62);
		contentPane.add(cambiarMayuscular);

		JButton cambiarMinisculas = new JButton("Cambiar a minusculas");
		cambiarMinisculas.setFont(new Font("Times New Roman", Font.BOLD, 12));
		cambiarMinisculas.setForeground(new Color(0, 128, 0));
		cambiarMinisculas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textoMinus;
				if (txtTexto.getText() == "")
					JOptionPane.showMessageDialog(null, "El cuadro de texto esta vacio");
				else {
					textoMinus = txtTexto.getText().toLowerCase();
					txtTexto.setText(textoMinus);
				}
			}
		});
		cambiarMinisculas.setBounds(555, 121, 156, 62);
		contentPane.add(cambiarMinisculas);

		JButton contarOraciones = new JButton("Contar Oraciones");
		contarOraciones.setFont(new Font("Times New Roman", Font.BOLD, 12));
		contarOraciones.setForeground(new Color(128, 64, 0));
		contarOraciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String texto = txtTexto.getText();
				int posicion, contador = 0;
				// se busca la primera vez que aparece
				posicion = texto.indexOf(".");
				while (posicion != -1) { // mientras se encuentre el caracter
					contador++; // se cuenta
					// se sigue buscando a partir de la posición siguiente a la encontrada
					posicion = texto.indexOf(".", posicion + 1);
				}

				JOptionPane.showMessageDialog(null, "Oraciones: " + contador);
			}
		});
		contarOraciones.setBounds(555, 194, 156, 62);
		contentPane.add(contarOraciones);

	}
}
