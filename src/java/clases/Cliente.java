/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author Luis
 */
public class Cliente {
 
    private String numSumi,nombre,direccion,usuario,password,tipo;

    public Cliente(String numSumi, String nombre, String direccion, String usuario, String password, String tipo) {
        this.numSumi = numSumi;
        this.nombre = nombre;
        this.direccion = direccion;
        this.usuario = usuario;
        this.password = password;
        this.tipo = tipo;
    }

    public Cliente() {
    }

    public String getNumSumi() {
        return numSumi;
    }

    public void setNumSumi(String numSumi) {
        this.numSumi = numSumi;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
