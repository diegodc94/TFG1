/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tfgpaso1;

/**
 *
 * @author diegodc94
 */
public class Arista {
    private Nodo nodo1;
    private Nodo nodo2;
    
    public Arista (Nodo nodo1, Nodo nodo2){
        this.nodo1 = nodo1;
        this.nodo2 = nodo2;
    }
    
    public Nodo getNodo1(){
        return this.nodo1;
    }
    
    public Nodo getNodo2(){
        return this.nodo2;
    }

    @Override
    public String toString() {
        return "Arista{" + "nodo1=" + nodo1 + ", nodo2=" + nodo2 + '}';
    }
    
    
    
    
}
