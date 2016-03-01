/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otimizacao_v4;

/**
 *
 * @author AndreaD e DeboraP
 */
public class Otimizacao_v1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Funcao b = new FuncaoF2();
        
        b.setP(0.01); 
        b.setE(0.0000001);
        
        //double x = b.metodoDeNewton(1.5, 1);
        double hinicial [][] = { {1, 0}, {0, 1} };
        double x = b.metodoDeQuaseNewton(0.0, 2.0, hinicial);
        
        System.out.println("deu isso no final: " + x);
    }
    
}
