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
public class Nodo {
    
    
    private int id;
    private boolean terminal; //true si terminal, false si no.
    
    public Nodo(int id){
        this.id = id;
    }
    
    public int getId(){
        return this.id;
    }

    public void setId(int id) {
       this.id = id;
    }

    @Override
    public String toString(){
        return "{" + "id=" + id + '}';
    }
    
    public void setTerminal(boolean terminal){
        this.terminal = terminal;
    }
    
    public boolean isTerminal(){
        return this.terminal;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.id;
        return hash;
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
        final Nodo other = (Nodo) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }


    
}
