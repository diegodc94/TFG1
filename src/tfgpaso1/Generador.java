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
public class Generador extends Nodo {
   
    
    public Generador(int id) {
        super(id);
    }

    public int getId() {
        return super.getId();
    }

    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public String toString() {
        return "Generador" + super.toString();
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Generador other = (Generador) obj;
        if (this.getId() != other.getId()) {
            return false;
        }
        return true;
    }
    
    
    
    public boolean isTerminal(){
        return false;
    }
    
    
    
}
