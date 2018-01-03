package definitivo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import controlador.conexion;
import factura.factura;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class form {
 //declaracion de objetos
	public static JFrame frame;
	public static JTextField nombre;
	public static JTextField apellido;
	public static JTextField cedula;
	public static JTextField telefono;
	public static JTable tabla1;
	public static JTable tabla2;
	public static JTextField resultado;
	public static JComboBox servicio, costo;
	public static JButton agregar;
	public static JLabel cost; 
	public static JTextArea info, temp;
	public static JTextPane area;
	public static JPopupMenu popupMenu;
	java.util.Date fecha = new Date();

	String mj;
	String num;
	DefaultTableModel mode;
	//constructor del jframe
	public form() {
		initialize();
		cargartabla();        
	    String [] enca={"Servicio","costo"};
	    mode =new DefaultTableModel(null,enca);
	    tabla2.setModel(mode);
	    
	    JLabel hora = new JLabel("");
	    hora.setBounds(809, 25, 46, 14);
	    frame.getContentPane().add(hora);
	    
	    temp = new JTextArea();
	   
	   
	    
	}

	/**
	 * Inicializamos el contenido del Jframe
	 */
	//methodo inicializar
	private void initialize() {
		
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 1080, 720);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().setTitle("COTIZACIÓN HYUNDAI");
		getFrame().getContentPane().setLayout(null);
		//etiqueta del nombre
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setFont(new Font("Courier New", Font.PLAIN, 13));
		lblNewLabel.setBounds(68, 91, 68, 14);
		getFrame().getContentPane().add(lblNewLabel);
		
		//cuadro de texto del nombre 
		nombre = new JTextField();
		nombre.setBounds(153, 88, 144, 20);
		getFrame().getContentPane().add(nombre);
		nombre.setColumns(10);
		
		//boton de agregar 
		agregar = new JButton("Agregar");
		agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add.gif")));
		agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                agregarActionPerformed(e);
            }
        });
		agregar.setFont(new Font("Courier New", Font.PLAIN, 13));
		agregar.setBounds(339, 324, 144, 49);
		getFrame().getContentPane().add(agregar);
		
		
		//label del apelido 
		JLabel lblNewLabel_1 = new JLabel("Apellido");
		lblNewLabel_1.setFont(new Font("Courier New", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(68, 138, 68, 14);
		getFrame().getContentPane().add(lblNewLabel_1);
		
		//cuadro de texto del apellido
		apellido = new JTextField();
		apellido.setBounds(153, 135, 144, 20);
		getFrame().getContentPane().add(apellido);
		apellido.setColumns(10);
		
		//etiqueta de la cedula
		JLabel lblCedula = new JLabel("cedula");
		lblCedula.setFont(new Font("Courier New", Font.PLAIN, 13));
		lblCedula.setBounds(68, 184, 68, 14);
		getFrame().getContentPane().add(lblCedula);
		
		//cuadro de texto de la cedula 
		cedula = new JTextField();
		cedula.setBounds(153, 178, 144, 20);
		getFrame().getContentPane().add(cedula);
		cedula.setColumns(10);
		
		//text field del telefono 
		telefono = new JTextField();
		telefono.setBounds(153, 220, 144, 20);
		getFrame().getContentPane().add(telefono);
		telefono.setColumns(10);
		
		//label del telefono 
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Courier New", Font.PLAIN, 13));
		lblTelefono.setBounds(68, 223, 68, 14);
		getFrame().getContentPane().add(lblTelefono);
		
		//combobox del servicio 
		/*contiene una lista de servicios que estan disponibles esto esta vinculado con el combox llamado
		 * costo cada vez que seleccione un boton en servicio cambiara el precio en costo
		 */
		
	    servicio = new JComboBox();
	    servicio.addItemListener(new java.awt.event.ItemListener(){
	    	public void itemStateChanged(java.awt.event.ItemEvent e) {
	    		servicioItemStateChanged(e);
	    	}
	    });
		servicio.setModel(new DefaultComboBoxModel(new String[] {"Seleccione uno", "Cambio de aceite", "Cambio de maletero", "Cambio de rines", "Cambio de suspensi\u00F3n", "Compra de bater\u00EDa", "Compra de motor", "Compra de puerta ", "Compra de rines", "Compra de Spoiler", "Compra de vidrio", "Lavar auto", "Mantenimiento de aire acondicionado", "Mantenimiento general"}));
		servicio.setFont(new Font("Courier New", Font.PLAIN, 11));
		servicio.setBounds(153, 293, 218, 20);
		getFrame().getContentPane().add(servicio);
		
		// costo combobox
	    costo = new JComboBox();
		costo.setFont(new Font("Courier New", Font.PLAIN, 11));
		costo.setBounds(153, 341, 144, 20);
		getFrame().getContentPane().add(costo);
		
		//tabla 1
		/* esta tabla esta vinculada a la bd de access, cada vez q se inicie el jframe se actualizara
		 * automaticamente con los datos que esten en la base de datos.
		 */
		tabla1 = new JTable();
		tabla1.setFont(new Font("Courier New", Font.PLAIN, 13));
		tabla1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"id", "Nombre", "Apellido", "Cedula", "Telefono", "Servicios", "costo"
			}
		));
		//se agrega un scroll o barra de desplazamiento a la tabla
		JScrollPane scrollpane = new JScrollPane(tabla1);

		JPopupMenu popupMenu_1 = new JPopupMenu();
		tabla1.setComponentPopupMenu(popupMenu_1);
		//popupmenu o menu emergente de la tabla 
		//editar edita una fila seleccionada
		JMenuItem editar = new JMenuItem("Editar");
		editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rehacer.gif")));
		editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarActionPerformed(e);
			}
		});/*
		*aqui se añaden los itemsmenu al popupmenu 
		*se cargan las diferentes funciones que tendran
		*/
		popupMenu_1.add(editar);
		
		//elimina una fila dependiendo de la seleccion
		JMenuItem eliminar = new JMenuItem("Eliminar");
		eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eliminar.gif")));
		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarActionPerformed(e);
			}
		});
		popupMenu_1.add(eliminar);
		scrollpane.setBounds(419, 84, 620, 210);
		getFrame().getContentPane().add(scrollpane);
		
		//tabla 2 sirve para saber que se esta comprando y el costo
		
		tabla2 = new JTable();
		tabla2.setFont(new Font("Courier New", Font.PLAIN, 13));
		tabla2.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Servicio", "Costo"
			}
		));
		JScrollPane scrollpane1 = new JScrollPane(tabla2);
		scrollpane1.setBounds(68, 418, 265, 129);
		getFrame().getContentPane().add(scrollpane1);
		
		//etiqueta de servicio
		JLabel lblNewLabel_2 = new JLabel("Servicio");
		lblNewLabel_2.setFont(new Font("Courier New", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(68, 296, 68, 14);
		getFrame().getContentPane().add(lblNewLabel_2);
		
		//Etiqueta de costo
		JLabel lblNewLabel_3 = new JLabel("Costo");
		lblNewLabel_3.setFont(new Font("Courier New", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(68, 344, 54, 14);
		getFrame().getContentPane().add(lblNewLabel_3);
		
		//boton de factura genera una factura
		JButton facturat = new JButton("Facturar");
		facturat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				facturaMouseClicked(evt);
			}
		});
		facturat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/factura.gif")));
		facturat.setFont(new Font("Courier New", Font.PLAIN, 13));
		facturat.setBounds(886, 545, 153, 49);
		getFrame().getContentPane().add(facturat);
		
		//etiqueta del cliente 
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Courier New", Font.PLAIN, 20));
		lblCliente.setBounds(419, 38, 116, 25);
		getFrame().getContentPane().add(lblCliente);
		
		//etiqueta de registro 
		JLabel lblRegistro = new JLabel("Registro");
		lblRegistro.setFont(new Font("Courier New", Font.PLAIN, 20));
		lblRegistro.setBounds(68, 38, 116, 25);
		getFrame().getContentPane().add(lblRegistro);
		
		//cuadro de tecto resultado de lo que se fue sumando en costo
		resultado = new JTextField();
		resultado.setBounds(476, 422, 86, 20);
		getFrame().getContentPane().add(resultado);
		resultado.setColumns(10);
		
		JLabel lblTotalAPagar = new JLabel("Total a pagar:");
		lblTotalAPagar.setFont(new Font("Courier New", Font.PLAIN, 11));
		lblTotalAPagar.setBounds(358, 425, 125, 14);
		getFrame().getContentPane().add(lblTotalAPagar);
		
		cost = new JLabel("...");
	    cost.setBounds(68, 567, 46, 14);
	   
	    
	    info = new JTextArea("...");
	    info.setBounds(68, 589, 46, 14);
	    
	    
	    area = new JTextPane();
	    area.setBounds(124, 567, 38, 59);
	    
		
		JMenuBar menuBar = new JMenuBar();
		getFrame().setJMenuBar(menuBar);
		
		JMenu mnNuevo = new JMenu("Archivo");
		menuBar.add(mnNuevo);
		
		JMenuItem mntmNuevo = new JMenuItem("Nuevo");
		mntmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				nuevoActionPerformed(evt);
			}
		});
		mnNuevo.add(mntmNuevo);
		
		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarActionPerformed(e);
			}
		});
		mnNuevo.add(mntmGuardar);
		
		JMenuItem cerrar = new JMenuItem("Cerrar");
		cerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarActionPerformed(e);
			}
		});
		mnNuevo.add(cerrar);
		
		JMenu mnAcercaDe = new JMenu("Acerca de...");
		menuBar.add(mnAcercaDe);
		
		JMenuItem mntmWelcome = new JMenuItem("Welcome");
		mntmWelcome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcomeActionPerformed(e);
			}
		});
		mnAcercaDe.add(mntmWelcome);
	}
	//Constructores, metodos, etc...
	private void facturaMouseClicked(java.awt.event.MouseEvent evt) {
		llenarbasededatos();
		factura v = new factura();
		v.setVisible(true);
		this.getFrame().dispose();
		factura.rec.setText(temp.getText());
		
		
	}
	
	private void welcomeActionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "Esta app a sido creada por : ","Welcome", JOptionPane.INFORMATION_MESSAGE);
        
	}
	private void cerrarActionPerformed(ActionEvent e) {
		getFrame().dispose();
	}
	private void guardarActionPerformed(ActionEvent e) {
		llenarbasededatos();
	}
	private void nuevoActionPerformed(ActionEvent e) {
		limpiarcampos();
	}
    private void agregarActionPerformed(ActionEvent e) {
       agregar();

    }
    private void eliminarActionPerformed(java.awt.event.ActionEvent e) {                                         
        
        eliminar();
    }    
    private void editarActionPerformed(java.awt.event.ActionEvent e) {                                       
    	editar();
    	    }                 
    private void servicioItemStateChanged(ItemEvent e) {

        if(!(e.getStateChange() != ItemEvent.SELECTED))
            if (this.servicio.getSelectedIndex() > 0) {
                this.costo.setModel(new DefaultComboBoxModel(this.getcosto(this.servicio.getSelectedItem().toString())));
            }
    }
 
    public void agregar() {
    	 mj =servicio.getSelectedItem().toString();
         area.setText(mj);
         num= costo.getSelectedItem().toString();
         cost.setText(num);
         String[] dato = new String[3];
         dato[0]=area.getText();
         dato[1]=cost.getText();
         mode.addRow(dato);
         tabla2.setModel(mode);
         resultado.setText("0");
         int n;
         n = tabla2.getRowCount();
         int c=0;
         do
             try{
                 int f=c++;
                 int n1=Integer.parseInt(tabla2.getValueAt(f, 1).toString());
                 String nu=resultado.getText();
                 int n2=Integer.parseInt(nu);
                 long r=n1+n2;
                 resultado.setText(String.valueOf(r));


             }catch(Exception evt){
                 System.out.println(evt.getMessage());

             }while(c<n);

    }
 
    public String[] getcosto(String servicio)//costo de los servicios
    {
        String[] costo = new String[2];
        if(servicio.equalsIgnoreCase("Seleccione uno"))
        {
            costo[0] = "0";

        }
        if(servicio.equalsIgnoreCase("Cambio de aceite"))
        {
            costo[0] = "25";

        }


        if(servicio.equalsIgnoreCase("Cambio de maletero"))
        {
            costo[0] = "285";

        }

        if(servicio.equalsIgnoreCase("Cambio de rines"))
        {
            costo[0] = "1285";

        }
        if(servicio.equalsIgnoreCase("Cambio de suspensión"))
        {
            costo[0] = "550";

        }

        if(servicio.equalsIgnoreCase("Compra de batería"))
        {
            costo[0] = "85";

        }
        if(servicio.equalsIgnoreCase("Compra de motor"))
        {
            costo[0] = "4000";

        }
        if(servicio.equalsIgnoreCase("Compra de puerta "))
        {
            costo[0] = "400";

        }
        if(servicio.equalsIgnoreCase("Compra de rines"))
        {
            costo[0] = "600";

        }
        if(servicio.equalsIgnoreCase("Compra de Spoiler"))
        {
            costo[0] = "180";

        }
        if(servicio.equalsIgnoreCase("Compra de vidrio"))
        {
            costo[0] = "200";

        }
        if(servicio.equalsIgnoreCase("Lavar auto"))
        {
            costo[0] = "15";

        }
        if(servicio.equalsIgnoreCase("Mantenimiento de aire acondicionado"))
        {
            costo[0] = "115";

        }
        if(servicio.equalsIgnoreCase("Mantenimiento general"))
        {
            costo[0] = "60";

        }
        return costo;}
    public void obtener(){
        int fila = tabla2.getRowCount();
        int i;
        String valores="";
        for (i = 0; i < fila; i++) {
            String valor = (String) tabla2.getValueAt(i, 0);
            valores += valor;
            // Con esta condiciÃ³n solo ponemos comas hasta el penÃºltimo valor :)
            if (i < (fila-1)) {
                valores += ",\n ";
            }
        }
        info.setText( "servicios: \n" + valores);
    }
	
    public void llenarbasededatos(){
        if(nombre.getText().equals("")||apellido.getText().equals("")||cedula.getText().equals("")||telefono.getText().equals("")||servicio.getSelectedItem().toString().equals("...") ){
                JOptionPane.showMessageDialog(null, this,"FALTA COMPLETAR CAMPOS", 0);
            }else{
                obtener();
                temp.setText(""+fecha+"\n+++++++++++++++++++++++++++++++++"+"\nNombre: "+nombre.getText()+"\nApellido: "+apellido.getText()+"\nCedula: "+cedula.getText()+"\nTelefono: "+telefono.getText()+"\nServicios: "+info.getText()+("\n+++++++++++++++++++++++++++++++++")+"\nTotal a Pagar: "+resultado.getText()+" dolares");
        		
                String sql_query= "INSERT INTO Tabla1([id],[nombre], [apellido], [cedula], [telefono], [servicio],[costo])"+ "VALUES(NULL,?,?,?,?,?,?)";

                try{
                    conexion conn = new conexion();
                    PreparedStatement consulta = conn.getConnection().prepareStatement(sql_query);
                    consulta.setString(1, nombre.getText());
                    consulta.setString(2, apellido.getText());
                    consulta.setString(3, cedula.getText());
                    consulta.setString(4, telefono.getText());
                    consulta.setString(5, info.getText() );
                    consulta.setString(6, resultado.getText() );

                    int resul = consulta.executeUpdate();
                    if(resul==1){
                        JOptionPane.showMessageDialog(null, "se a guardado exitozamente en la base de datos","GUARDADO EXITOSAMENTE", JOptionPane.INFORMATION_MESSAGE);
                        cargartabla();
                        agregar.removeAll();
                        limpiarcampos();
                        

                    }
                    else{
                        JOptionPane.showMessageDialog(null,"error al guardar en base de datos","ERROR",JOptionPane.INFORMATION_MESSAGE, null);
                    }
                    conn.desconectar();
                }catch(SQLException ex){
                    System.out.println(ex.getMessage());
                }
            }        
    }
    
    public void cargartabla(){
        String[] titulo ={"ID","NOMBRE","APELLIDO","CEDULA","TELEFONO","SERVICIO","COSTO"};
        DefaultTableModel modelo= new DefaultTableModel(null, titulo);
        String [] fila = new String[8];
        
        try{
            conexion conn = new conexion();
            PreparedStatement consulta = conn.getConnection().prepareStatement("SELECT * FROM Tabla1");
            ResultSet resultado = consulta.executeQuery();
            
            while(resultado.next()){
            fila[0] = resultado.getNString("id");
            fila[1] = resultado.getNString("nombre");
            fila[2] = resultado.getNString("apellido");
            fila[3] = resultado.getNString("cedula");
            fila[4] = resultado.getNString("telefono");
            fila[5] = resultado.getNString("servicio");
            fila[6] = resultado.getNString("costo");
            modelo.addRow(fila);
        }
          tabla1.setModel(modelo);
        }catch (SQLException ex) {
                java.util.logging.Logger.getLogger(form.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    public void limpiarcampos(){
    	   agregar.removeAll();      
    	do { 
    	mode.removeRow(0);

    	} while (tabla2.getRowCount() !=0);

    	nombre.setText(null);
    	apellido.setText(null);
    	cedula.setText(null);
    	telefono.setText(null);
    	servicio.setSelectedItem("...");
    	costo.setSelectedItem("0");
    	resultado.setText(null);
    	        
    	    }
    
    public void eliminar(){
        int row=tabla1.getSelectedRow();
                if(row!=-1){
                System.out.println();
                try{
                    conexion conn= new conexion();
                    PreparedStatement consulta = conn.getConnection().prepareStatement("DELETE FROM [Tabla1] WHERE id=?");
                    consulta.setString(1,tabla1.getValueAt(row, 0). toString());
                    int resul = consulta.executeUpdate();
                    if(resul>=1){
                    	JOptionPane.showMessageDialog(null, "eliminado correctamente","ELIMINADO", JOptionPane.INFORMATION_MESSAGE);
                        cargartabla();
                    }
                    else{
                    	JOptionPane.showMessageDialog(null, "No se a podido eliminar","ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE);
                    }
                    conn.desconectar();
                }catch(SQLException ex){
                   java.util.logging.Logger.getLogger(form.class.getName()).log(Level.SEVERE, null, ex);
                    
                }
            }
                else{
                	JOptionPane.showMessageDialog(null, "Seleccione Fila","ERROR", JOptionPane.ERROR_MESSAGE);
                        }
            
    }
    
    //Metodo editar
    public void editar(){
        int row = tabla1.getSelectedRow();
    if(row!=-1){
        nombre.setText(tabla1.getValueAt(row,1).toString());
        apellido.setText(tabla1.getValueAt(row, 2).toString());
        cedula.setText(tabla1.getValueAt(row, 3).toString());
        telefono.setText(tabla1.getValueAt(row, 4).toString());
        
         try{
              conexion conn= new conexion();
              PreparedStatement consulta = conn.getConnection().prepareStatement("DELETE FROM [Tabla1] WHERE id=?");
              consulta.setString(1,tabla1.getValueAt(row, 0). toString());
              int resul = consulta.executeUpdate();
          if(resul>=1){
        	  	  JOptionPane.showMessageDialog(null, "Ingrese nuevamente los objetos comprados","ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                 
                  cargartabla();
              }
              else{
            	  JOptionPane.showMessageDialog(null, "ADVERTENCIA no se pudo editar","ERROR", JOptionPane.ERROR_MESSAGE);
                  
              }
              conn.desconectar();
          }catch(SQLException ex){
             java.util.logging.Logger.getLogger(form.class.getName()).log(Level.SEVERE, null, ex);
              
          }
      }     
         else{
        	 JOptionPane.showMessageDialog(null, "Seleccione Fila","ERROR", JOptionPane.ERROR_MESSAGE);
    }
}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/sf.png")));
	}
}
