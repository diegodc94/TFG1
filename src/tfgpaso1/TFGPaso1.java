/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tfgpaso1;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook; 
import org.apache.poi.ss.usermodel.Cell;

/**
 *
 * @author diegodc94
 */
public class TFGPaso1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here

        String ruta = "/home/diegodc94/Escritorio/TFG/GRLP_instances/set1/N50S13/steiner_dataN50S13M253.dat.txt";

//        Instance g = new Instance(ruta);
//        System.out.println(g.toString());
//        List<Nodo> term = new ArrayList<Nodo>();
//        term = g.getTerminal_nodes();
//        Nodo n1 = term.get(15);
//        System.out.println("Nodo a probar:" + n1);
//        System.out.println("Tamaño adyacentes originales " + g.adyacentes(n1).size());
//        System.out.println(g.adyacentes(n1));
//        Nodo n2 = g.activarGenerador();
//        g.despuesActivar(n2);
//        System.out.println("Adyacentes del generador 1 : " + g.adyacentes(n2));
//        System.out.println("Tamaño adyacentes generador 1: " + g.adyacentes(n2).size());
//        System.out.println("Nuevo nodo generador 1:" + n2);
//        System.out.println();
//        System.out.println("Tamaño adyacentes posterior: " + g.adyacentes(n1).size());
//        System.out.println(g.adyacentes(n1));
//        Nodo n3 = g.activarGenerador();
//        g.despuesActivar(n3);
//        System.out.println("Adyacentes del generador 2 : " + g.adyacentes(n3));
//        System.out.println("Tamaño adyacentes generador 2: " + g.adyacentes(n3).size());
//        System.out.println("Nuevo nodo generador 2:" + n3);
//        System.out.println();
//        System.out.println("Tamaño adyacentes posterior: " + g.adyacentes(n1).size());
//        System.out.println(g.adyacentes(n1));

        Instance grafo = new Instance(ruta);
        RandomManager.setSeed(1234);
        System.out.println("La ruta a imprimir es: " + grafo.toString());
        System.out.println(grafo.getList_nodos()[2]);
        int i = 1;
        while ((i<=grafo.getRegenerator_nodes2().length) && (grafo.obtenerNumNodosSinUnir() > 0)){
            System.out.println("-------------INICIO CICLO----------------");

            System.out.println("Iteración número: "+ i);
            System.out.println("------------------------------------------");
            System.out.println("Nodos sin unir antes activar: " + grafo.obtenerNumNodosSinUnir());
            Nodo n2 = grafo.activarGenerador();
            System.out.println("Nodo escogido: "+n2);
            grafo.despuesActivar(n2);
            grafo.mostrarGrafo();
            System.out.println("-------------FIN CICLO----------------");
            i++;
            System.out.println("Nodos sin unir tras activar generador: "+grafo.obtenerNumNodosSinUnir());
        }
        
        System.out.print("Grafo completamente unido con los nodos generadores:  ");
        for (Nodo x: grafo.getRegenerator_nodes()){
            System.out.print(x + " ");
        }
        System.out.println();
        grafo.mostrarGrafo();
//        Nodo n = new Nodo(2);
//   
//        System.out.println(grafo.obtenerAdyacentesDir(n));
        
        HSSFWorkbook libro = new HSSFWorkbook();
        HSSFSheet hoja = libro.createSheet();
        HSSFRow fila = hoja.createRow(0);
        HSSFCell celda = fila.createCell((short) 0);
        
        HSSFRichTextString texto = new HSSFRichTextString("hola mundo");
        celda.setCellValue(texto);
        
        try {
            FileOutputStream elFichero = new FileOutputStream("holamundo.xls");
            libro.write(elFichero);
            elFichero.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        
    }
    

}
