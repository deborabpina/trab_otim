package otimizacao_v4;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author AndreaD e DeboraP
 */
abstract public class Funcao {
    double o1 = (3-Math.sqrt(5))/2;
    double o2 = 1 - o1;
    double a; //Inicio do intervalo
    double b; //Fim do intervalo
    double u = a + o1*(b-a); //Ponto intermediário da esquerda
    double v = a + o2*(b-a); //Ponto intermediário da direita
    double t; //ponto mínimo
    double e; // tolerancia do intervalo
    double p; //passo
    double x1; //ponto inicial dos métodos
    double x2; // ponto inicial dos métodos
    double d1; // direção de descida
    double d2; // direção de descida
    double h = 10E-5; // número muito próximo de zero usado no calculo da hessiana por diferenças finitas
    double [][] hessiana; //matriz hessiana que vai ser calculada pela funcao
    double [][] inversa; //matriz inversa que vai ser calculada pela funcao
    /*public void setA(double a){
        this.a = a;
    }
    public void setB(double b){
        this.b = b;
    }*/
    public void setE(double e){
        this.e = e;
    }
    
    public void setP(double p){
        this.p = p;
    }
    
    //Função dada pelo exercicio
    abstract public double calcularFuncao(double q1, double q2);
    
    //Função phi usada pela busca 
    abstract public double calcularQ(double h);
        
    //Derivada da função em relação a x1 -> df/dx1
    abstract public double calcularFx1(double x1, double x2); 
  
    //Derivada da função em relação a x2 -> df/dx2
    abstract public double calcularFx2(double x1, double x2);
    
    //vetor gradiente = [Fx1 Fx2] transposto
    
    
    //calculo da hessiana por diferenças finitas
    public double[][] calcularHessiana(double x1, double x2){
        
        // a1 = d^2f/dx1dx1
        double a1 = (this.calcularFuncao(x1 + h, x2) -2*this.calcularFuncao(x1, x2) + this.calcularFuncao(x1 -h, x2))/(h*h);
        
        //a2 = d^2f/dx1dx2
        double a2 = (this.calcularFx2(x1+h, x2)- this.calcularFx2(x1 -h, x2))/2*h;
        
        //b1 = d^2f/dx2dx1
        double b1 = a2;
        
        //b2 = d^2f/dx2dx2;
        double b2 = (this.calcularFuncao(x1, x2 + h) -2*this.calcularFuncao(x1,x2) + this.calcularFuncao(x1, x2 -h))/(h*h);
        
        /*
           hessiana =  | a1 a2 |
                       | b1 b2 |
        */
        
        hessiana = new double[2][2];
        hessiana [0][0] = a1;
        hessiana [0][1] = a2;
        hessiana [1][0] = b1;
        hessiana [1][1] = b2;
        
        return hessiana;
    }
    
    //Calcula a inversa da matriz hessiana baseada na fórmula para calcular inversa de uma matrix 2x2
    public double[][] calcularInversa(){
        //colocar um caso pra quando a inversa não existe
        
        double a1= this.hessiana[0][0];
        double a2 = this.hessiana[0][1];
        double b1 = this.hessiana[1][0];
        double b2 = this.hessiana[1][1];
        
        inversa = new double[2][2];
        inversa[0][0] = b2/(a1*b2 - a2*b1);
        inversa[0][1] = -a2/(a1*b2 - a2*b1);
        inversa[1][0] = b1/(a1*b2 - a2*b1);
        inversa[1][1] = a1/(a1*b2 - a2*b1);
        
        return inversa;
    }
    
    //Método do Gradiente
    public double metodoDoGradiente(double x1, double x2){
        //System.out.println("Começou. Boa sorte!");
        int k = 0;
        while ( this.calcularFx1(x1, x2)!=0 || this.calcularFx2(x1, x2) != 0){ //and ou or??
            //System.out.println("tá tranquilo por enquanto");
            //System.out.println("k="+k);
            
            // Condição de parada
            if (k==1000){
                System.out.println("DEU RUIM NO K");
                break;
            }
                                   
            d1 = -this.calcularFx1(x1, x2);
            d2 = -this.calcularFx2(x1,x2);
            
            double l = this.calcularBuscaAurea(); //não sei se vai dar certo
            /*if(this.calcularFuncao(x1,x2)<= this.calcularFuncao(x1 + t*d1, x2 + t*d2)){
                System.out.println("DEU RUIM");
                break;
            }*/
            System.out.println("f=" + this.calcularFuncao(x1, x2) + " x1=" + x1 + " x2=" + x2);
            
            double y1 = x1;
            double y2 = x2;
            x1 = x1 + l*d1;
            x2 = x2 + l*d2;
            k = k+1;
            
            //Condição de parada
            if (y1==x1 && y2==x2){
                System.out.println("x^k = x^k-1");    
                break;
            }
        }
        //System.out.println("Ffinal=" + this.calcularFuncao(x1,x2) + " x=" + this.x1 + " x2="+ this.x2);
        return this.calcularFuncao(x1, x2); //return errado
    }
    
    //Calcula t pelo método de busca da seção áurea
    public double calcularBuscaAurea(){
        System.out.println("BuscaAurea entrando");
        a=0;
        double s = p;
        b = 2*p;
        while (this.calcularQ(b)<this.calcularQ(s)){
            a = s;
            s = b;
            b = 2*b;
        }
        while ( (b-a)>e){
            if(this.calcularQ(u)<this.calcularQ(v)){
                b = v;
                v = u;
                u = a + o1*(b-a);
                
            }
            else{
                a = u;
                u = v;
                v = a + o2*(b-a);
            }
        }
        t = (u+v)/2;
        //System.out.println("t=" +t);
        return t;
    }
    
    
        
    //Método de Newton
    public double metodoDeNewton (double x1, double x2){
    
        int k = 0;
        
        while ( this.calcularFx1(x1, x2)!=0 || this.calcularFx2(x1, x2) != 0){
            
            this.calcularHessiana(x1, x2);
            this.calcularInversa();
            
            d1 = -(this.inversa[0][0]*this.calcularFx1(x1, x2) + this.inversa[0][1]*this.calcularFx2(x1, x2));
            d2 = -(this.inversa[1][0]*this.calcularFx1(x1, x2)+ this.inversa[1][1]*this.calcularFx2(x1, x2));
            System.out.println("d1=" + d1 + " d2=" +d2);
            double l = this.calcularBuscaAurea();
            double y1 = x1;
            double y2 = x2;
            System.out.println("x1="+ x1 + " x2=" + x2 + " f=" + this.calcularFuncao(x1, x2) + " t=" + l);
            x1 = x1 + l*d1;
            x2 = x2 + l*d2;
            k = k+1;
            if (k==11){
                System.out.println("Deu ruim no k="+ k);
                break;
            }
    
        }
        return this.calcularFuncao(x1, x2);
    }    
    
    
    
    
      
    //Método de Quase Newton
    public double metodoDeQuaseNewton(double x1, double x2, double[][] HInicial){
        int k=0;
        
        double p1, p2, q1, q2;
        double a1, a2, b1, b2; 
        
        //hessiana inicial, que é matriz identidade
        a1 = HInicial[0][0];
        a2 = HInicial[0][1];
        b1 = HInicial[1][0];
        b2 = HInicial[1][1];
           
        while (this.calcularFx1(x1, x2)!=0 || this.calcularFx2(x1, x2) != 0)
        { 
        System.out.println("a derivada e: " + this.calcularFx2(x1, x2));
        //variáveis para guardar o valor usado agora
        double y1 = x1;
        double y2 = x2;              
        
        d1 = - (a1*this.calcularFx1(x1, x2) + a2*this.calcularFx2(x1, x2));
        d2 = - (b1*this.calcularFx1(x1, x2) + b2*this.calcularFx2(x1, x2));
           
        System.out.println("d1=" + d1 + " d2=" +d2);
            
        double l = this.calcularBuscaAurea();
            
        System.out.println("x1="+ x1 + " x2=" + x2 + " f=" + this.calcularFuncao(x1, x2) + " t=" + l);
            
        //variáveis que guardam os próximos valores a serem utilizados
        x1 = y1 + l*d1; //aqui pode ser y1 ou x1, o mesmo serve pra x2
        x2 = y2 + l*d2;
           
        //cálculo de p e q para encontrar hessiana iterativamente utilizando BFGS
        p1 = x1 - y1;
        p2 = x2 - y2;

        q1 = this.calcularFx1(x1, x2) - this.calcularFx1(y1, y2);
        q2 = this.calcularFx2(x1, x2) - this.calcularFx2(y1, y2);

        //cálculo dos termos da BFGS
        double termo1 = 1 + ((a1 * q1 * q1 + b1 * q2 * q2 + a2 * q1 * q1 + b2 * q2 * q2) / (p1 * q1 + p2 * q2));
        double termo21 = p1 * p1 / (p1 * q1 + p2 * q2);
        double termo22 = p1 * p2 / (p1 * q1 + p2 * q2);
        double termo23 = termo22;
        double termo24 = p2 * p2 / (p1 * q1 + p2 * q2);
        double termo31 = (p1 * q1 + a1 * p1 * q1 + a2 * p1 * q2) / (p1 * q1 + p2 * q2);
        double termo32 = (p1 * q2 + a1 * p2 * q1 + a2 * p2 * q2) / (p1 * q1 + p2 * q2);
        double termo33 = (p2 * q1 + b1 * p1 * q1 + b2 * p1 * q2) / (p1 * q1 + p2 * q2);
        double termo34 = (p2 * q2 + b1 * p2 * q1 + b2 * p2 * q2) / (p1 * q1 + p2 * q2);

        //cada um dos termos da nova hessiana           
        a1 = a1 + termo1 * termo21 + termo31;
        a2 = a2 + termo1 * termo22 + termo32;
        b1 = b1 + termo1 * termo23 + termo33;
        b2 = b2 + termo1 * termo24 + termo34;

        System.out.println("a função dá isso na iteração k : " + k + " e valor da fç: " + this.calcularFuncao(x1, x2));
        
        k = k+1;

        //limitando o número de iterações
        if (k==12)
        {
            System.out.println("Deu ruim no k="+ k);
            break;
        }
        }
    return this.calcularFuncao(x1, x2);
    }   
}
