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
public class FuncaoF1 extends Funcao{
    
    @Override
    public double calcularQ(double t){ //ok
        
        
        double numerador = ((d1*Math.exp(x1+d1*t) - d2)*(-d2*t + Math.exp(x1+d1*t) - x2)+2*d1*(d1*t + x1));
        double denominador = (Math.sqrt(Math.pow((-d2*t + Math.exp(d1*t+x1) - x2),2) + Math.pow((d1*t + x1),2)));
        double f = numerador/denominador;
        
        return f;       
    }
    
    
    @Override
    public double calcularFuncao(double x1, double x2){
        double f= Math.sqrt(Math.pow(x1, 2)+Math.pow((Math.exp(x1)-x2),2));
        return f;
    }
    
    @Override
    public double calcularFx1(double x1,double x2){
        //System.out.println("FX1 funcionando");
        double fx1 = (x1 + (-x2 + Math.exp(x1))*Math.exp(x1))/(Math.sqrt(Math.pow(x1, 2)+Math.pow(-x2+Math.exp(x1),2)));//df/dx1
        //System.out.println("Fx1="+ fx1);
        return fx1;
    };    
        
    @Override
    public double calcularFx2(double x1, double x2){
        //System.out.println("FX2 funcionando");
        double fx2 = ((x2-Math.exp(x1))/(Math.sqrt(Math.pow(x1, 2)+Math.pow(-x2+Math.exp(x1),2)))); //df/dx2
        //System.out.println("Fx2="+ fx2);
        return fx2;
    }

    //@Override
    //public double[] calcularHessiana(double x1, double x2) {
      //  OctaveEngine octave = new OctaveEngineFactory().getScriptEngine();
        
//double parte1 = (2*Math.exp(2*x1)-x2*Math.exp(x1)+ 1)/(Math.sqrt((Math.pow(x1, 2) + Math.pow(Math.exp(x1)-x2,2))));
        //double parcial1 = 2*Math.exp(x1)*(Math.exp(x1)-x2)+2*x1;
        //double parcial2 = Math.pow(x1,2)+Math.pow(Math.exp(x1) -x2);
        //double parte2 = Math.pow(parcial,2)/(4*Math.pow(partial,3/4);    
    
    
}
