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
public class FuncaoF2 extends Funcao {

    @Override
    public double calcularFuncao(double x1, double x2) {
        //double f = Math.log(1+ Math.pow(x1,2) + Math.pow((Math.exp(x1)-x2),2));
        double f = Math.pow((2*x1 - 3*x2), 2) + Math.pow((x2 - 1), 4);
        return f;
    }

    @Override
    public double calcularQ(double h) {
        double f = Math.log(1+ Math.pow(x1+ t*d1,2) + Math.pow((Math.exp(x1+t*d1)-(x2+t*d2)),2));
        return f;
    }

    @Override
    public double calcularFx1(double x1, double x2) {
        //double f = (2*(-1*Math.exp(x1)*x2+ x1 + Math.exp(2*x1)))/(Math.pow(x1, 2) + Math.pow(Math.exp(x1)-x2,2)+1);
        double f = 4*(2*x1 - 3*x2);
        return f;
    }

    @Override
    public double calcularFx2(double x1, double x2) {
        //double f = (-2*(Math.exp(x1)-x2))/(Math.pow(x1,2) + Math.pow(Math.exp(x1) -  x2,2) + 1);
        double f = -12*x1 +18*x2 + 4*Math.pow((x2-1), 3);
        return f;
    }
    
}
