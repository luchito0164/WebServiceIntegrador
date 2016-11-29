/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import clases.Cliente;

import conexion.ActiveMQDAO;
import conexion.MongoDAO;


import java.net.UnknownHostException;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Luis
 */
@WebService(serviceName = "WSIntegrador")
public class WSIntegrador {
  

    /**
     * This is a sample web service operation
     */

    
     @WebMethod(operationName = "buscarNumero")
    public Cliente  buscarNumero(@WebParam(name = "numero") String numero) throws UnknownHostException {
                
         MongoDAO mongo= new MongoDAO();
                 
        return  mongo.buscarSuministro(numero); 
    }
         @WebMethod(operationName = "almacenarCola")
    public void almacenarCola(@WebParam(name = "consumo") String consumo,@WebParam(name = "numeroSumi") String numeroSumi,@WebParam(name = "fecha") String fecha,@WebParam(name = "cliente") String cliente) throws UnknownHostException {
                
         ActiveMQDAO active= new ActiveMQDAO();
         
             
             active.crearMensajeConsumo(cliente,numeroSumi,consumo,fecha);
             
      
    }
            @WebMethod(operationName = "guardarConsumo")
    public void guardarConsumo() throws UnknownHostException {
                
         ActiveMQDAO active= new ActiveMQDAO();
         
             
             active.recibirMensajeConsumo();
             
      
    }
           @WebMethod(operationName = "verificarLogin")
    public boolean verificarLogin(@WebParam(name = "usuario") String usuario,@WebParam(name = "password") String password,@WebParam(name = "tipo") String tipo) throws UnknownHostException {
                
       MongoDAO mongo= new MongoDAO();
      
         
             
            
             return  mongo.verificarLogin(usuario, password,tipo);
      
    }
    
      @WebMethod(operationName = "encontrarTipo")
    public String encontrarTipo(@WebParam(name = "usuario") String usuario) throws UnknownHostException {
                
       MongoDAO mongo= new MongoDAO();
      
         
             
            
             return  mongo.encontrarTipo(usuario);
      
    }
    
       @WebMethod(operationName = "listarClientes")
    public List<Cliente> listarClientes() throws UnknownHostException {
                
       MongoDAO mongo= new MongoDAO();
      
         
             
            
             return  mongo.listarClientes();
      
    }
    
      @WebMethod(operationName = "buscarCliente")
    public List<Cliente> buscarCliente(@WebParam(name = "numSumi") String numSumi) throws UnknownHostException {
                
       MongoDAO mongo= new MongoDAO();
      
         
             
            
             return  mongo.buscaCliente(numSumi);
      
    }
    
       @WebMethod(operationName = "modificarCliente")
    public void modificarCliente(@WebParam(name = "id") String id,@WebParam(name = "cliente") Cliente cliente) throws UnknownHostException {
                
       MongoDAO mongo= new MongoDAO();
      
         
             
            
              mongo.modificarCliente(id, cliente);
      
    }
    
        @WebMethod(operationName = "verificarSuministro")
    public boolean verificarSuministro(@WebParam(name = "numSumi") String numSumi) throws UnknownHostException {
                
       MongoDAO mongo= new MongoDAO();
      
            
         return mongo.verificarSuministro(numSumi);
      
    }
    
        @WebMethod(operationName = "eliminarCliente")
    public void eliminarCliente(@WebParam(name = "numSumi") String numSumi) throws UnknownHostException {
                
       MongoDAO mongo= new MongoDAO();
      
            
          mongo.eliminarCliente(numSumi);
      
    }
     
    
}
