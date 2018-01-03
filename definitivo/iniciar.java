package definitivo;
import java.sql.ResultSet;
import java.sql.Statement;

import controlador.conexion;
import pantalladecarga.carga;

import java.sql.SQLException;


public class iniciar {
	public static void main(String[] args) {
	carga window = new carga();
	window.setSize(793,477);
	window.setBounds(400,100,793,477);
	window.setVisible(true);
	
/*	
	try{
        conexion conn = new conexion();
         Statement consulta = conn.getConnection().createStatement();
         ResultSet resultado = consulta.executeQuery("SELECT * FROM Tabla1");
         while(resultado.next()){
             System.out.println("id: "+resultado.getNString("id"));
             System.out.println("nombre: "+resultado.getNString("nombre"));
             System.out.println("apellido: "+resultado.getNString("apellido"));
             System.out.println("cedula: "+resultado.getNString("cedula"));
             System.out.println("telefono: "+resultado.getNString("telefono"));
             System.out.println("servicio: "+resultado.getNString("servicio"));
             System.out.println("costo: "+resultado.getNString("costo"));

         }

    }catch(SQLException e){
        System.out.println(e);
    }
	}*/
}}
