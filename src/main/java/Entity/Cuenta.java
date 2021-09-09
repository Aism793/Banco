/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;

/**
 *
 * @author AISM
 */
public class Cuenta implements Serializable{
    private String numCuenta;
    private String propietario;
    private float saldo;

    public String getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
    
    public float retirar(float valor){
        this.saldo = this.saldo - valor;
        return this.saldo;
    }
    
    public float consignar(float valor){
        this.saldo = this.saldo + valor;
        return this.saldo;
    }
}
