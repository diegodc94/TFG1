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
public class Terminal extends Nodo{
    
    
    
    public Terminal(int id) {
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
        return "Terminal" + super.toString();
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
        final Terminal other = (Terminal) obj;
        if (this.getId() != other.getId()) {
            return false;
        }
        return true;
    }
    
    
    
    public boolean isTerminal(){
        return true;
    }
    
    
}
