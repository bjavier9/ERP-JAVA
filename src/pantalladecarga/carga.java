package pantalladecarga;
 
import javax.swing.Timer;

import definitivo.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class carga extends javax.swing.JFrame {
    private Timer t;
    private ActionListener al;
       public carga() {
           al= new ActionListener(){
               public void actionPerformed(ActionEvent ae){
               if(jProgressBar1.getValue()<100){
                   jProgressBar1.setValue(jProgressBar1.getValue()+10);
               }else{
                   t.stop();
                   mostrar();
               }
                   }
           };
           t= new Timer(600,al);
        initComponents();
        
         t.start();
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);
        getContentPane().add(jProgressBar1);
        jProgressBar1.setBounds(70, 400, 146, 14);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/carga.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0,0, 793, 477);

        pack();
    }// </editor-fold>                        

    public void mostrar(){
      form v = new form();
		v.getFrame().setVisible(true);
		this.dispose();
  }

                      
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar jProgressBar1;
                   
}