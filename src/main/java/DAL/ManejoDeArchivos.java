/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Entity.Cuenta;

public class ManejoDeArchivos {
    public static void guardarCuenta(ArrayList<Cuenta> lista){
        ObjectOutputStream escritor =null;
        try{
            escritor = new ObjectOutputStream(new FileOutputStream("cuentas"));
            escritor.writeObject(lista);
            
        }catch(IOException error){
        }finally{
            try{
                if(escritor!=null){
                    escritor.close();
                }
            }catch(IOException err){
                
            }    
        }
    }
    
    public static ArrayList<Cuenta> leerCuentas(){
      
        ObjectInputStream lector=null;
        ArrayList<Cuenta> lista= new ArrayList<>();
        try{
            try{
                lector=new ObjectInputStream(new FileInputStream("cuentas"));
                lista= (ArrayList<Cuenta>) lector.readObject();
            }catch(FileNotFoundException error){
            }
        }catch(Exception e){
            System.out.print(e);
        }finally{
            try{
                if(lector!=null){
                    lector.close();
                }
            }catch(Exception error){
            }
        
        } 
        return lista;
    }
}
