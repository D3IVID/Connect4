/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Guillermo David Loyo
 */



public class Connect4 extends JFrame implements ActionListener {

    /**
     * @param args the command line arguments
     */
    
    //Interfaz Grafica
    JMenuBar menus=new JMenuBar();
    JMenu menuArchivo=new JMenu("Archivo");
    JMenuItem subMenuNuevo=new JMenuItem("Nueva Partida");
    JMenuItem subMenuSalir=new JMenuItem("Salir");
    
    JButton boton[][]=new JButton[7][7];
    
    //Fichas
    ImageIcon fichaJugador1;
    ImageIcon fichaJugador2;
    
    //Variables auxiliares
    int turnoJugador;
    boolean finDelJuego;
    
    public Connect4() {
        
         //Panel Principal 
        JPanel Principal=new JPanel();
        Principal.setLayout(new GridLayout(7,7));
        
        
        //Cargar imagenes
        fichaJugador1=new ImageIcon(getClass().getResource("RED.gif"));
        fichaJugador2=new ImageIcon(getClass().getResource("BLACK.gif"));

        //Construimos la barra de tareas
        subMenuNuevo.addActionListener(this);
        menuArchivo.add(subMenuNuevo);
        menuArchivo.addSeparator();
        subMenuSalir.addActionListener(this);
        menuArchivo.add(subMenuSalir);
        menus.add(menuArchivo);
        setJMenuBar(menus);


        //Inicializamos el tablero con botones
        for(int i=0;i<7;i++)
        {
            for(int j=0;j<7;j++)
            {
                boton[i][j]=new JButton();
                boton[i][j].addActionListener(this);
                boton[i][j].setBackground(Color.GRAY); 
                Principal.add(boton[i][j]);
            }
                add(Principal,"Center");
        }

        //Cerrar el programa
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent we)
            {
                    System.exit(0);
            }
        });

        //Dimensiones del JFrame
        setSize (600,600);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("CONECTA 4");
        setVisible(true);
        
        turnoJugador=0;
    }
    
    void validarPartida(int x,int y){

        finDelJuego=false;
        
        //Validar partida horizontal	
        int ganaJugador1=0;
        int ganaJugador2=0;
        for(int j=0;j<7;j++)
        {
            //Comprobamos si el espacio ya ha sido seleccionado por algun jugador
            if(boton[y][j].getIcon()!=null){
                //Incrementamos en 1 si existe una ficha del jugador 1
                if(boton[y][j].getIcon().equals(fichaJugador1)){
                       ganaJugador1++;
                }else{
                    ganaJugador1=0;
                }
                //Si el jugador 1 tiene 4 fichas, entonces gana
                if(ganaJugador1==4){
                   JOptionPane.showMessageDialog(this,"Gana Jugador Rojo","Conecta 4",JOptionPane.INFORMATION_MESSAGE,fichaJugador1);
                   VolverEmpezar();
                   finDelJuego=true;
                }
                //Si no ha se ha terminado el juago
                if(finDelJuego!=true){
                    //Incrementamos en 1 si existe una ficha del jugador 2
                   if(boton[y][j].getIcon().equals(fichaJugador2)){
                        ganaJugador2++;
                   }else{                       
                       ganaJugador2=0;
                   }
                   //Si el jugador 2 tiene 4 fichas, entonces gana
                   if(ganaJugador2==4){
                     JOptionPane.showMessageDialog(this,"Gana Jugador Negro","Conecta 4",JOptionPane.INFORMATION_MESSAGE,fichaJugador2);	
                     VolverEmpezar();
                   }
                }
            }else{
                ganaJugador1=0;
                ganaJugador1=0;
            }
       }
    

        //Validar partida vertical
        ganaJugador1=0;
        ganaJugador2=0;
        for(int i=0;i<7;i++){
            
            //Comprobamos si el espacio ya ha sido seleccionado por algun jugador
            if(boton[i][x].getIcon()!=null){
                //Incrementamos en 1 si existe una ficha del jugador 1
                if(boton[i][x].getIcon().equals(fichaJugador1)){
                    ganaJugador1++;
                }else{
                    ganaJugador1=0;
                }
                //Si el jugador 1 tiene 4 fichas, entonces gana
                if(ganaJugador1==4){
                    JOptionPane.showMessageDialog(this,"Gana Jugador Rojo","Conecta 4",JOptionPane.INFORMATION_MESSAGE,fichaJugador1);
                    VolverEmpezar();
                    finDelJuego=true;
                }
                if(finDelJuego!=true){
                    //Incrementamos en 1 si existe una ficha del jugador 2
                   if(boton[i][x].getIcon().equals(fichaJugador2)){
                        ganaJugador2++;
                   }else{
                       ganaJugador2=0;
                   }
                   //Si el jugador 2 tiene 4 fichas, entonces gana
                   if(ganaJugador2==4){
                     JOptionPane.showMessageDialog(this,"Gana Jugador Negro","Conecta 4",JOptionPane.INFORMATION_MESSAGE,fichaJugador2);
                     VolverEmpezar();	
                   }
                }
            }
        }
        
        // Validar partida diagonal de izquierda a derecha
        ganaJugador1=0;
        ganaJugador2=0;
        
        //Utilizamos variables auxiliares
        int a=y;
        int b=x;
        
        //Nos colocamos en la parte origen de la diagonal
        while(b>0 && a>0){
                a--;
                b--;   		
        }
        
        //Recorremos diagonalmente (de izquierda a derecha) las fichas
        while(b<7 && a<7){	
            //Comprobamos si el espacio ya ha sido seleccionado por algun jugador
            if(boton[a][b].getIcon()!=null){		
                //Incrementamos en 1 si existe una ficha del jugador 1
                if(boton[a][b].getIcon().equals(fichaJugador1)){
                    ganaJugador1++;
                }else{
                    ganaJugador1=0;
                }
                //Si el jugador 1 tiene 4 fichas, entonces gana
                if(ganaJugador1==4){
                    JOptionPane.showMessageDialog(this,"Gana Jugador Rojo","Conecta 4",JOptionPane.INFORMATION_MESSAGE,fichaJugador1);
                    VolverEmpezar();
                    finDelJuego=true;	    
                }
                if(finDelJuego!=true){
                    //Incrementamos en 1 si existe una ficha del jugador 2
                    if(boton[a][b].getIcon().equals(fichaJugador2)){
                        ganaJugador2++;
                    }else{
                        ganaJugador2=0;
                    }
                    //Si el jugador 2 tiene 4 fichas, entonces gana
                    if(ganaJugador2==4){
                      JOptionPane.showMessageDialog(this,"Gana Jugador Negro","Conecta 4",JOptionPane.INFORMATION_MESSAGE,fichaJugador2);
                      VolverEmpezar();	
                    }   
                }
             }else {
                ganaJugador1=0;
                ganaJugador2=0;
             } 
            a++;
            b++;  
         } 
        
        
        // Validar partida diagonal de derecha a izquierda 
        ganaJugador1=0;
        ganaJugador2=0;
        a=y;
        b=x;
        //Nos colocamos en la parte origen de la diagonal
        while(b<6 && a>0){
               a--;
               b++;

        }
        
        //Recorremos diagonalmente (de derecha a izquuierda) las fichas
        while(b>-1 && a<7 ){
            //Comprobamos si el espacio ya ha sido seleccionado por algun jugador
            if(boton[a][b].getIcon()!=null){	
                //Incrementamos en 1 si existe una ficha del jugador 1
                if(boton[a][b].getIcon().equals(fichaJugador1)){
                        ganaJugador1++;
                 }else{
                     ganaJugador1=0;
                 }
                //Si el jugador 1 tiene 4 fichas, entonces gana
                 if(ganaJugador1==4){
                          JOptionPane.showMessageDialog(this,"Gana Jugador Rojo","Conecta 4",JOptionPane.INFORMATION_MESSAGE,fichaJugador1);
                          VolverEmpezar();
                          finDelJuego=true;	    
                 }
                 if(finDelJuego!=true){
                     //Incrementamos en 1 si existe una ficha del jugador 2
                    if(boton[a][b].getIcon().equals(fichaJugador2)){
                            ganaJugador2++;
                    }else{
                        ganaJugador2=0;
                    }
                    //Si el jugador 2 tiene 4 fichas, entonces gana
                    if(ganaJugador2==4){
                      JOptionPane.showMessageDialog(this,"Gana Jugador Negro","Conecta 4",JOptionPane.INFORMATION_MESSAGE,fichaJugador2);
                      VolverEmpezar();	
                    }
                 }
            } else {
                ganaJugador1=0;
                ganaJugador2=0;
            } 

            a++;
            b--;
        } 
    }
    
    // Reiniciamos el tablero	
    void VolverEmpezar(){
        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                boton[i][j].setIcon(null);
            }
        }
        turnoJugador=0;
    }

    //Eventos
     public void actionPerformed(ActionEvent ae){
        
         //Se agregan los eventos a la barra de tareas
        if(ae.getSource()==subMenuSalir){	
          dispose();
        }
        if(ae.getSource()==subMenuNuevo){
          VolverEmpezar();
        }
        
        //Se coloca la ficha en la posicion mas baja de la columna
        int x=0;
        int y=0;
        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                if (ae.getSource()==boton[i][j]){	
                    //Recorremos la columna hasta encontrar la celda vacia mas baja
                    int k=7;
                    do{
                        k--;
                    }
                    while(boton[k][j].getIcon()!=null & k!=0);
                    if(boton[k][j].getIcon()==null){	
                        //Se identifica el jugador que coloco la ficha
                        if(turnoJugador %2==0){
                            boton[k][j].setIcon(fichaJugador1);
                        }else{ 
                            boton[k][j].setIcon(fichaJugador2);
                        }
                     }
                    turnoJugador++;
                    x=j;
                    y=k;
                } 
            }
        }
        //Se valida la partida con el nuevo moviento realziado del jugador
        validarPartida(x,y);


        //Si se acaban los turnos, se declara un empate
        if(turnoJugador==49){
            JOptionPane.showMessageDialog(this,"Has Empatado","Conecta 4",JOptionPane.INFORMATION_MESSAGE);
            VolverEmpezar();
        }
        finDelJuego=false;
    }


    public static void main(String[] args) {
        new Connect4();
    }
}

