/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodos;

import Metodos.DAO;
import clases.Consumo;
import conexion.MongoDAO;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

/**
 *
 * @author Luis
 */
public class Consumir implements MessageListener{
        public void onMessage(Message arg0) {

		try {
			if (arg0 instanceof TextMessage) {
				String cadena= (((TextMessage)arg0).getText());
                                
                               
                              Consumo con= new Consumo();
                              DAO dao= new DAO();
                              
                              con=dao.separarCadena(cadena);
                              
                            try {
                                MongoDAO mongo= new MongoDAO();
                                mongo.insertarConsumo(con);
                            } catch (UnknownHostException ex) {
                                System.out.println("Cai en el mongo");;
                            }
                              
			} 
			else if (arg0 instanceof ObjectMessage) {
                          
				System.out.println("Mensaje Recibido : de mierda ") ;
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
        
        
         
     
    
}
