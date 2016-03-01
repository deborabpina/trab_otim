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

//Função do Thiago pra teste
public class FuncaoExtra extends Funcao {
    
    
    @Override
    
    public double calcularFuncao(double x1,double x2) {
       double f = (-1)/(+1 + Math.pow(x1, 2) + Math.pow(x2, 2));
       return f;
    }

    
    @Override
    public double calcularFx1(double x1, double x2){
     double f = (2*x1)/(Math.pow((Math.pow(x1, 2)+Math.pow(x2, 2)+1), 2));
     return f;
    }
    
    @Override
    public double calcularFx2(double x1, double x2){
        double f = (2*x2)/(Math.pow((Math.pow(x1, 2)+Math.pow(x2, 2)+1), 2));
        return f;
    
    }

    @Override
    public double calcularQ(double t) {
        double f = (-1)/(+1 + Math.pow(x1+t*d1, 2) + Math.pow(x2+t*d2, 2));
        return f;
    }
}

