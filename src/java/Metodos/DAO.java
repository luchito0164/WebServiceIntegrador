/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodos;

import clases.Consumo;

/**
 *
 * @author Luis
 */
public class DAO {
      public Consumo separarCadena(String cadena) {
        Consumo con= new Consumo();
        
         String delimitador="-";
        String [] temp;
        
        
        temp=cadena.split(delimitador);
      
     
            
            
              for(int i=0; i<temp.length;i++){
                  if(i==0){
                      
                    con.setNombre(temp[i]);  
                  }else if(i==1){
                       con.setNumero(temp[i]);
                  }else if (i==2){
                       con.setConsumo(temp[i]);
                  }else{
                       con.setFecha(temp[i]);
                      
                  }
          
        }
        
        
        
        
        return con;
    }
}
