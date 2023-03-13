/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;


/**
 *
 * @author AlbertoMoralesGálvez
 */


public class asignaturas implements Serializable {

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getAD() {
        return AD;
    }

    public void setAD(int AD) {
        this.AD = AD;
    }

    public int getSGE() {
        return SGE;
    }

    public void setSGE(int SGE) {
        this.SGE = SGE;
    }

    @Override
    public String toString() {
        return "asignaturas{" + "nombre=" + nombre + ", apellidos=" + apellidos + ", AD=" + AD + ", SGE=" + SGE + ", DI=" + DI + ", PMDM=" + PMDM + ", PSP=" + PSP + ", EIE=" + EIE + ", HLC=" + HLC + '}';
    }

    public int getDI() {
        return DI;
    }

    public void setDI(int DI) {
        this.DI = DI;
    }

    public int getPMDM() {
        return PMDM;
    }

    public void setPMDM(int PMDM) {
        this.PMDM = PMDM;
    }

    public int getPSP() {
        return PSP;
    }

    public void setPSP(int PSP) {
        this.PSP = PSP;
    }

    public int getEIE() {
        return EIE;
    }

    public void setEIE(int EIE) {
        this.EIE = EIE;
    }

    public int getHLC() {
        return HLC;
    }

    public void setHLC(int HLC) {
        this.HLC = HLC;
    }
    
   
	
	private String nombre;
     private String apellidos;
     private int AD;
     private int SGE;
     private int DI;
     private int PMDM;
     private int PSP;
     private int EIE;
     private int HLC;
     
            
}


