/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tfgpaso1;

import java.util.Random;

/**
 *
 * @author diegodc94
 */
public class RandomManager {
    
    private static Random rnd;
    
    public static void setSeed(int num){
        RandomManager.rnd = new Random(num);
    }
    
    public Random getRandom(){
        return rnd;
    }
    
}
