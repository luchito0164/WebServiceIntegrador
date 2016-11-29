/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import Metodos.Consumir;
import clases.Consumo;
import java.sql.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;

import org.apache.activemq.*;
import javax.jms.*;

/**
 *
 * @author Luis
 */
public class ActiveMQDAO {
   private static ActiveMQConnectionFactory connectionFactory;
	private static javax.jms.Connection connection;
	private static Session session;
	private static Destination destination;
	private static boolean transacted = false;

    public ActiveMQDAO() {
         connectionFactory = new ActiveMQConnectionFactory(
			ActiveMQConnection.DEFAULT_USER,
			ActiveMQConnection.DEFAULT_PASSWORD,
			ActiveMQConnection.DEFAULT_BROKER_URL);
      
    }
    
    public void crearMensajeConsumo(String cliente,String numero,String consumo,String fecha){
        try {
           
              
            
                        connection =  connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(transacted,Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("Consumo");
			
			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
                        String cadena=cliente+"-"+numero+"-"+consumo+"-"+fecha;
			
			TextMessage message = session.createTextMessage(cadena);			
			//Enviamos un mensaje
			producer.send(message);
                        System.out.println("Se envio el mensaje consumo...");
                     
        } catch (JMSException e) {
        }
       
        
        
    }
    public void recibirMensajeConsumo() {
        
        
        try {
        
                        connection = connectionFactory.createConnection();
			connection.start();
			
			session = connection.createSession(false,
			Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("Consumo");
			MessageConsumer consumer = session.createConsumer(destination);
			
			Consumir myConsumo = new Consumir();
                        consumer.setMessageListener(myConsumo);
			
			Thread.sleep(3000);
			session.close();
			connection.close();
    } 
		catch(Exception e) {
			e.printStackTrace();
		}
    
    
   
    }
         
         
         
         
}
