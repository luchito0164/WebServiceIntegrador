/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import clases.Cliente;
import clases.Consumo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luis
 */
public class MongoDAO {
    
    
     MongoClient mc = null;
    DB db = null;
    DBCollection coll1 = null;
    DBCollection coll2 = null;
     DBCollection coll3 = null;

    public MongoDAO() throws UnknownHostException {

        MongoClientURI uri = new MongoClientURI("mongodb://luchito0164:luchito0164@ds159237.mlab.com:59237/bd_integrador");
         
        mc = new MongoClient(uri);
        db = mc.getDB("bd_integrador");
        coll1 = db.getCollection("administrador");
        coll2=db.getCollection("cliente");
        coll3=db.getCollection("consumo");
       
    }
    
     public Cliente buscarSuministro(String numero) {
          DBCursor rs = coll2.find();

        Cliente cliente = new Cliente();

        while (rs.hasNext()) {
            DBObject dbo = rs.next();

            if (dbo.get("numSumi").equals(numero)) {
                
                cliente.setDireccion("" + dbo.get("direccion"));
                cliente.setNombre("" + dbo.get("nombre"));
                cliente.setNumSumi("" + dbo.get("numSumi"));
              }

        }
         return cliente;
     }
     
      public boolean verificarLogin(String usuario, String password,String tipo) {

        boolean entrada = false;
        
        if(tipo.equalsIgnoreCase("administrador")){
             DBCursor rs = coll1.find();
        while (rs.hasNext()) {
            //Creando el DBObject
            DBObject dbo = rs.next();
            if ((dbo.get("usuario").equals(usuario)) && (dbo.get("password").equals(password))) {
                entrada = true;
            }
        }
            
        }else if(tipo.equalsIgnoreCase("cliente")){
             DBCursor rs = coll2.find();
        while (rs.hasNext()) {
            //Creando el DBObject
            DBObject dbo = rs.next();
            if ((dbo.get("usuario").equals(usuario)) && (dbo.get("password").equals(password))) {
                entrada = true;
            }
        }
            
        }else{
            System.out.println("El usuario no esta en la base de datos");
        }

        //creando el cursor
       

        return entrada;

    }
         public String encontrarTipo(String usuario) {
        String tipo ="";
        DBCursor rs = coll1.find();
        DBCursor ra=coll2.find();
        while (rs.hasNext()) {
            DBObject dbo = rs.next();
            if (dbo.get("usuario").equals(usuario)) {
                tipo = dbo.get("tipo").toString();
            }
        }
        if(tipo.equalsIgnoreCase("")){
            while (ra.hasNext()) {
            DBObject dbo = ra.next();
            if (dbo.get("usuario").equals(usuario)) {
                tipo = dbo.get("tipo").toString();
            }
        }
        }
        
             System.out.println("El usuario antes de llegar es "+tipo);
        
        //cliente u operario
        return tipo;
    }
         
          public void insertarConsumo(Consumo con) {
        BasicDBObject doc = new BasicDBObject();
        doc.put("codSumi", con.getNumero());
        doc.put("cliente", con.getNombre());
        doc.put("consumo", con.getConsumo());
        doc.put("fecha", con.getFecha());
        

        System.out.println("insertado el libro");
        coll3.insert(doc);
    }
          
              public boolean verificarSuministro(String numero) {

        boolean existe = false;

        //creando el cursor
        DBCursor rs = coll2.find();
        while (rs.hasNext()) {
            //Creando el DBObject
            DBObject dbo = rs.next();
            if ((dbo.get("numSumi").equals(numero))) {
                existe=true;
            }
        }

        return existe;

    }
              
     public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();

        DBCursor rs = coll2.find();

        while (rs.hasNext()) {
            DBObject dbo = rs.next();
            Cliente cli = new Cliente();
            cli.setDireccion("" + dbo.get("direccion"));
            cli.setNombre("" + dbo.get("nombre"));
            cli.setNumSumi("" + dbo.get("numSumi"));
            cli.setPassword("" + dbo.get("password"));
            cli.setUsuario("" + dbo.get("usuario"));
            

            clientes.add(cli);

        }

        return clientes;
    }
     
      public List<Cliente> buscaCliente(String id) {

        DBCursor rs = coll2.find();

        List<Cliente> clientes = new ArrayList<>();

        while (rs.hasNext()) {
            DBObject dbo = rs.next();

            if (dbo.get("numSumi").equals(id)) {
                Cliente cli = new Cliente();
                  cli.setDireccion("" + dbo.get("direccion"));
            cli.setNombre("" + dbo.get("nombre"));
            cli.setNumSumi("" + dbo.get("numSumi"));
            cli.setPassword("" + dbo.get("password"));
            cli.setUsuario("" + dbo.get("usuario"));
                clientes.add(cli);

            }

        }

        return clientes;
    }
      
       public void modificarCliente(String id,Cliente cliente){
         
    
    DBCursor rs= coll2.find();    
    
       while (rs.hasNext()) {
            DBObject dbo = rs.next();

            if (dbo.get("numSumi").equals(id)) {
             
        dbo.put("nombre",cliente.getNombre());
        dbo.put("direccion",cliente.getDireccion());
        dbo.put("numSumi",cliente.getNumSumi());
        dbo.put("password",cliente.getPassword());
        dbo.put("usuario",cliente.getUsuario());
        coll2.save(dbo);

            }

        }
    
    }
       
         public void eliminarCliente(String numSumi) {

        BasicDBObject doc = new BasicDBObject("numSumi", numSumi);

        coll2.remove(doc);

    }
              
              
              
}
