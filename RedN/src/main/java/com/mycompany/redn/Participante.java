/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.redn;

import java.util.List;

/**
 *
 * @author usuario
 */
public class Participante {
    
    String identificacion;
    String nombre;
    String ciudad;
    double aporte;
    boolean pago;
    List<Participante>hijos;
    
    //contructor vacio

    public Participante() {
    }
    
    //contructor lleno

    public Participante(String identificacion, String nombre, String ciudad, double aporte, boolean pago, List<Participante> hijos) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.aporte = aporte;
        this.pago = pago;
        this.hijos = hijos;
    }
    
    //getter and seters

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public double getAporte() {
        return aporte;
    }

    public void setAporte(double aporte) {
        this.aporte = aporte;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public List<Participante> getHijos() {
        return hijos;
    }

    public void setHijos(List<Participante> hijos) {
        this.hijos = hijos;
    }

    // Yo agrego un participante como hijo
    public void agregarHijo(Participante hijo) {
        hijos.add(hijo);
    }
}