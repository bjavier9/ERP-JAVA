package factura;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Image;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;

import definitivo.form;

import com.itextpdf.text.Paragraph;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class factura extends JFrame{

	private JPanel contentPane;
	private JTextField ruta;
	public static JTextArea rec;
	java.util.Date fecha = new Date();
	public factura() {
		setTitle("COTIZACIÓN HYUNDAI");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/iconop.gif")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 537, 463);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnBuscarActionPerformed(evt);
			}
		});
		btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/buscar.gif")));
		btnBuscar.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnBuscar.setBounds(365, 72, 115, 23);
		contentPane.add(btnBuscar);
		
		ruta = new JTextField();
		ruta.setBounds(72, 72, 302, 20);
		contentPane.add(ruta);
		ruta.setColumns(10);
		
		rec = new JTextArea();
		rec.setBounds(72, 142, 302, 196);
		contentPane.add(rec);
		
		JButton btnImprimir = new JButton("imprimir");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnImprimirActionPerformed(evt);
			}
		});
		btnImprimir.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imprimir.gif")));
		btnImprimir.setBounds(344, 349, 136, 23);
		contentPane.add(btnImprimir);
		
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setFont(new Font("Courier New", Font.PLAIN, 17));
		lblObservaciones.setBounds(72, 117, 154, 14);
		contentPane.add(lblObservaciones);
		
		JLabel lblFactura = new JLabel("Factura");
		lblFactura.setFont(new Font("Courier New", Font.PLAIN, 16));
		lblFactura.setBounds(72, 27, 103, 34);
		contentPane.add(lblFactura);
		
		JButton regresar = new JButton("Regresar");
		regresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				regresarMouseClicked(evt);
			}
		});
		regresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rehacer.gif")));
		regresar.setBounds(56, 349, 119, 23);
		contentPane.add(regresar);
	}
	private void regresarMouseClicked(java.awt.event.MouseEvent evt) {
		form v = new form();
		v.getFrame().setVisible(true);
		this.dispose();
	}
	
	 private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {                                         
	       JFileChooser dlg=new JFileChooser();
	       int option = dlg.showSaveDialog(this);
	       if(option == JFileChooser.APPROVE_OPTION){
	           File f = dlg.getSelectedFile();
	           ruta.setText(f.toString());
	       }
	    }                                        

	  private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {                                         
	        String truta= ruta.getText();
	        String contenido = rec.getText();
	      	        
	        try{
	        	Image foto1 = Image.getInstance("barcode.gif");
	            Image foto = Image.getInstance("factura.jpg");
	            foto.scaleToFit(100, 100);
	            foto.setAlignment(Chunk.ALIGN_LEFT);   
	            FileOutputStream archivo = new FileOutputStream(truta+".pdf");
	            Document doc = new Document();
	            PdfWriter.getInstance(doc, archivo);
	            doc.open();
	            doc.add(foto);
	            doc.add(new Paragraph(contenido,FontFactory.getFont("Courier new",12)));
	            doc.add(foto1);
	            doc.close();
	            
	            JOptionPane.showMessageDialog(null, "El PDF se a guardado correctamente","GUARDADO EXITOSAMENTE", JOptionPane.INFORMATION_MESSAGE);
                
	        }catch(Exception e){
	            System.out.println("error: "+e);
	        }
	            
	            
	    }                          
	
	
	
	
	
}
