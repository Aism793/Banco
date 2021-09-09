/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.ManejoDeArchivos;
import Entity.Cuenta;
import java.util.ArrayList;

/**
 *
 * @author AISM
 */
public class CuentaService {
    public static void guardarCuenta(ArrayList<Cuenta> lista){
        ManejoDeArchivos.guardarCuenta(lista);
    }
    
    public static ArrayList<Cuenta> leerCuentas(){
      
        ArrayList<Cuenta> lista = ManejoDeArchivos.leerCuentas();
        return lista;
    }
}
