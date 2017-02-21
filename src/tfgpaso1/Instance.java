/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tfgpaso1;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author diegodc94
 */
public class Instance {
    
    private RandomManager rnd;
    private String instance_name;
    private int count_nodes;
    private float node_percentage;
    private int count_edges;
    private List<Nodo> terminal_nodes;
    private Nodo[] regenerator_nodes;
    private List<Arista> list_edges;
    private HashSet<Nodo>[] list_nodos;
    

    public Instance(String ruta) throws Exception{
        
        terminal_nodes = new ArrayList<>();
        list_edges = new ArrayList<>();
        File fichero = new File(ruta);
        Scanner s = null;
        
        try {
            System.out.println(".... Leemos el contenido del fichero");
            s = new Scanner(fichero);
            System.out.println("xd");
            int contador = 0;
            int pos = 0;
            
            while (s.hasNextLine()){
                String linea = s.nextLine();
                if (contador == 0){
                    this.instance_name = linea;
                }
                
                else if (contador == 1){
                    String[] second_linea = linea.split(" ");
                    this.count_nodes = Integer.parseInt(second_linea[0]);
                    this.node_percentage = Float.parseFloat(second_linea[1]);
                    this.count_edges = Integer.parseInt(second_linea[2]);
                    this.list_nodos = new HashSet[this.count_nodes];
                }
                
                else if (contador == 2){
                    String[] tercera_linea = linea.split(" ");
                    for (int i = 0; i< tercera_linea.length; i++){
                        Nodo t = new Nodo(Integer.parseInt(tercera_linea[i]));
                        t.setTerminal(true);
                        terminal_nodes.add(t);
                        this.list_nodos[t.getId()-1] = new HashSet<Nodo>();
                    }
                }
                else if (contador == 3){
                    String[] cuarta_linea = linea.split(" ");
                    System.out.println("cuarta linea  " + cuarta_linea.length);
                    regenerator_nodes = new Nodo[cuarta_linea.length];
                    for (int i = 0; i<cuarta_linea.length; i++){
                        Nodo g = new Nodo(Integer.parseInt(cuarta_linea[i]));
                        g.setTerminal(true);
                        regenerator_nodes[i] = g;
                        this.list_nodos[g.getId()-1] = new HashSet<Nodo>();
                    }
                }
                else {
                   
                        String[] aristas = linea.split(" ");
                        int i1 = Integer.parseInt(aristas[0]);
                        int i2 = Integer.parseInt(aristas[1]);
                        Nodo n1 = new Nodo(i1);
                        Nodo n2 = new Nodo(i2);
                        n1.setTerminal(true);
                        n2.setTerminal(true);
                        this.list_nodos[i1-1].add(n2);
                        this.list_nodos[i2-1].add(n1);
                        Arista a = new Arista (n1,n2);
                        list_edges.add(a);
                        this.list_nodos[i1-1].add(n2);
                        this.list_nodos[i2-1].add(n1);                   
                }
                
                contador++;
                
                
            }
            
        }
        
        catch (Exception ex) {
            System.out.println("Mensaje: " + ex.getMessage());
	} finally {
			
            try {
		if (s != null)
			s.close();
            } catch (Exception ex2) {
                System.out.println("Mensaje 2: " + ex2.getMessage());
            }
        
        
        }
    
        System.out.println("Grafo creado correctamente");
    
    }
    
    public HashSet<Nodo> obtenerAdyacentesDir(Nodo n) { 
        return this.list_nodos[n.getId()-1];

    }
    
    public HashSet<Nodo> obtenerAdyacentesSinGen(Nodo n){
        HashSet<Nodo> conj = new HashSet<Nodo>();
        HashSet<Nodo> conj2 = (HashSet<Nodo>) this.list_nodos[n.getId()-1];
        for (Nodo n1 : conj2) {
            if (this.terminal_nodes.contains(n1)){
                conj.add(n1);
            }
        }
        return conj; 
    }
    

    
    public HashSet<Nodo> adyacentes(Nodo n){
        HashSet<Nodo> conj = new HashSet<Nodo>();
        HashSet<Nodo> conj2 = this.obtenerAdyacentesDir(n);
        for (Nodo n1 : conj2){
            if (n1.isTerminal()){
                conj.add(n1);
            }
            else {
                conj.add(n1);
                this.adyacentes_aux(conj, n1);

            }
        }
        HashSet <Nodo> conjFinal = new HashSet<Nodo>();
        conjFinal.addAll(conj);
        for (Nodo n1: conj){
            if (!n1.isTerminal()){
                conjFinal.remove(n1);   
            }
        }
        this.setAdyacentes(n, conjFinal);
        return conjFinal;
      
    }
    
    public int obtenerNumNodosSinUnir(){
        int i = 0;
        List<Integer> idsTerm = this.getIdsTerminales();
        List<Integer> idsGen = this.getIdsGeneradores();
        for(int x=1; x<=this.list_nodos.length; x++){
            if (idsTerm.contains(x)){
                HashSet<Nodo> cosa = new HashSet<Nodo>();
                for (Nodo n3 : this.list_nodos[x-1]){
                    if (this.terminal_nodes.contains(n3)){
                        cosa.add(n3);
                    }
                }
                i = i + this.terminal_nodes.size()- 1 - cosa.size();
                System.out.print("");
            }
        }
        System.out.print("");
        return i;
    }
    public void setAdyacentes(Nodo n, HashSet<Nodo> conj){
        this.list_nodos[n.getId()-1] = conj;
    }
    
    public HashSet<Nodo> adyacentes_aux(HashSet<Nodo> conj, Nodo n){
        HashSet<Nodo> conj2 = this.obtenerAdyacentesDir(n);
        for (Nodo n1 : conj2){
            if (n1.isTerminal() && !conj.contains(n1)){
                conj.add(n1);
            }
            else if (!conj.contains(n1)){
                this.adyacentes_aux(conj,n1);
            }
        }  
        return conj;
    }
    
    public Nodo activarGenerador(){ //hacerlo con new Random(); 
        int num = RandomManager.getRandom();
        Nodo n = this.regenerator_nodes[num-1];
        if (n.isTerminal()){
            n.setTerminal(false);
            this.regenerator_nodes[num-1] = n;
            return n;
        }
        else {
            return activarGenerador();
        }
    }
    
    public void mostrarGrafo(){
        for (int i = 0; i < this.list_nodos.length; i++){
            System.out.print("Adyacentes del nodo: " + (i+1));
            
            System.out.println(this.list_nodos[i]+" ");
            
            System.out.println();
            
        }
    }
    
    public void despuesActivar(Nodo n){ //n = nodo activado
        HashSet<Nodo> ady = this.list_nodos[n.getId()-1]; //obtengo los ady de mi gen
        for (Nodo n2: ady){ //busco entre los ady de mi generador para añadir luego toda la lista en cada ady.
            this.list_nodos[n2.getId()-1].addAll(this.list_nodos[n.getId()-1]);
            if (this.list_nodos[n2.getId()-1].contains(n2)){
                this.list_nodos[n2.getId()-1].remove(n2);
            }
        }
        
    }
    

     

    public List<Integer> getIdsTerminales(){
        
        List<Integer> lista = new ArrayList<Integer>();
        for (Nodo n : this.terminal_nodes){
            lista.add(n.getId());
        }
        
        return lista;
        
    }
    
    public List<Integer> getIdsGeneradores(){
        
        List<Integer> lista = new ArrayList<Integer>();
        for (Nodo n : this.regenerator_nodes){
            lista.add(n.getId());
        }
        
        return lista;
        
        
    }

    public String getInstance_name() {
        return instance_name;
    }

    public int getCount_nodes() {
        return count_nodes;
    }

    public float getNode_percentage() {
        return node_percentage;
    }

    public int getCount_edges() {
        return count_edges;
    }

    public List<Nodo> getTerminal_nodes() {
        return terminal_nodes;
    }

    public ArrayList<Nodo> getRegenerator_nodes() {
        ArrayList <Nodo> lista = new ArrayList<>();
        for (int o = 0; o<this.regenerator_nodes.length; o++){
            if (this.regenerator_nodes[o].isTerminal() == false){
                lista.add(this.regenerator_nodes[o]);
            }
        }
        return lista;
    }

    public HashSet<Nodo>[] getList_nodos() {
        return list_nodos;
    }
    public Nodo[] getRegenerator_nodes2(){
        return this.regenerator_nodes;
    }
    public List<Arista> getList_edges() {
        return list_edges;
    }
    
    
}
