/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package neuron;

import javax.swing.plaf.metal.MetalIconFactory;

/**
 *
 * @author abner
 */
public class Perceptron {
    double resultado, umbral_act = .5, coef_aprendizaje = 0.1, error = 0, salidaR=0, salida=0;
    double w1, w2, w3, w4, w5, x1=0, x2=0, x3=0, x4=0, x5=0;
    int epocas, patrones, epoc=0, cont=0, i=0;
    
    //MATRIZ de datos para entrenar el perceptron
    
                    //Forma     Consistencia        Apariencia      Peso        Color   Naranja=1 y Manzana=-1
    double[][] datos ={{1           ,1                  ,1          ,145        ,1          ,1},
                       {1           ,1                  ,1          ,140        ,.25        ,1},
                       {1           ,1                  ,1          ,150        ,1          ,1},
                       {1           ,-1                 ,1          ,170        ,1          ,1},
                       {1           ,1                  ,-1         ,170        ,-.25       ,1},
                       {1           ,1                  ,1          ,150        ,.25        ,1},
                       {1           ,-1                 ,-1         ,120        ,1          ,1},
                       {-1          ,1                  ,1          ,130        ,.25        ,1},
                       
                       {-1          ,-1                 ,-1         ,180        ,-.25       ,0},
                       {-1          ,-1                 ,-1         ,160        ,1          ,0},
                       {-1          ,1                  ,-1         ,190        ,.25        ,0},
                       {-1          ,1                  ,-1         ,200        ,-.25       ,0},
                       {-1          ,1                  ,-1         ,190        ,-.25       ,0},
                       {-1          ,1                  ,-1         ,185        ,-1         ,0},
                       {-1          -1                  ,1          ,175        ,-1         ,0},
                       {1           ,-1                 ,-1         ,180        ,-.25       ,0}
    };
    
   public double proceso(double x1, double x2, double x3, double x4, double x5, int epoc){
       
       //Le damos el valor de las epocas para repetir el entrenamiento
       epocas=epoc;
       
       //El valor de los pesos se establecio en  base a lo que nos parecio mas relevante para la determinacion de las frutas
       w1 = 1; //forma
       w2 = 1; //consistencia
       w3 = 1; //apariencia
       w4 = .4; //peso (gramos)
       w5 = .25; //color
       
       /***** E N T R E N A M I E N T O *****/
       
       //Recorremos la matriz para hacer la operacion (La sumatoria de cada entrada por el peso correspondiente)
       //E
       
       for(int i=0; i == epocas; i++){
           while (cont <= datos.length) {               
               salida = Math.tanh(((datos[cont][0] * w1) + (datos[cont][1] * w2) + (datos[cont][2] * w3) + (datos[cont][3] * w4) + (datos[cont][4] * w5)) - umbral_act);
               if (salida < 0) {
                   salida = 0;
               }
               System.out.println("Salida resultado "+salida);
               System.out.println("Salida esperada: "+datos[cont][5]);
               
               //Verificamos que nos haya arrojado un 0 o un 1, comparando el resultado de la sumatoria con la posicion de nuestra 5 fila del areglo
               //En el caso de no ser asi, calcula el error , restandole a la salida esperada el resultado obtenido
               if (salida == datos[cont][5]) {
                   cont++;
               }else{
                   //Calcular el error (Salida Deseada - Salida Real)
                   error = (datos[cont][5] - salida);
                   System.out.println("error "+error);
                   
                   //Una vez obtenido el error se aplica a la formula de aprendizaje para adaptar los pesos                   
                   //Modificacion de los pesos añadiendo el error
                   w1 = w1 + (coef_aprendizaje * datos[cont][0] * error);
                   w2 = w2 + (coef_aprendizaje * datos[cont][1] * error);
                   w3 = w3 + (coef_aprendizaje * datos[cont][2] * error);
                   w4 = w4 + (coef_aprendizaje * datos[cont][3] * error);
                   w5 = w5 + (coef_aprendizaje * datos[cont][4] * error);
               }
               
           }//fin while
       }//fin for
       
        //Una vez acabada la fase de entrenamiento y de haber obtenidos los nuevos pesos
        //Ya se pueden ingresar los valores deseados para la determinacion
        //Y procedemos a aplicar la formula de separacion lineal (Funcion de activacion) para calcular nuestra respuesta
        //Por ultimo, se crea un if para dar la respuesta como un 0 o un 1 t no en decimales
        
        salidaR = ((x1 * w1) + (x2 * w2) + (x3 * w3) + (x4 * w4) * (x5 * w5));
        
        salida=Math.tanh(salidaR);
        System.out.println("salida "+salidaR);
        if (salida >= -0.3) {
           return 1;
        }else{
            return 0;
        }
   }    
}
