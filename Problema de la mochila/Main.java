package ProblemaMochila;

import java.io.IOException;
import java.util.ArrayList;

public class Main 
{
    static ArrayList<Mochila> NuevosCandidatos = new ArrayList<>();
    static boolean TieneSolucion = true;
    
    //Retorna un numero real random entre dos numeros, en este algorimo siempre van a ser 0 y 1 
    static double Random(double pMin, double pMax)
    {
        return pMin + Math.random() * (pMax - pMin);
    }
    
    //Utiliza todos los metodos de la clases para poder crear un nuevo mating pool, basado en el actual
    static void Algoritmo(MatingPool Pool, int Repeticiones)
    {
        while(Repeticiones > 0 && TieneSolucion)
        {
            ArrayList<Mochila> NuevaPoblacion = new ArrayList<>();
            
            NuevosCandidatos.clear();
            
            System.out.println("\nNUEVOS CANDIDATOS BASADOS EN EL PORCENTAJE DE FITNESS Y UN RANDOM:\n");
            for(int i = 0; i < Pool.getListaDeMochilas().size(); i++)
            {           
                NuevosCandidatos.add(Pool.getCandidato(Random(0, 1)));
                System.out.print("Nuevo candidato: ");
                //Si es nulo significa que no se encontro un intervalo para el resultado del random en la ruleta y esto es solo 
                //posible si todos los porcentajes son 0%, es decir ningun candidato es valido para tomarse en cuenta.
                if(NuevosCandidatos.get(i) != null)
                {
                    NuevosCandidatos.get(i).ShowAsBit();
                    
                    System.out.println();
                    System.out.println();
                }
                else
                {
                    System.out.println();
                    System.out.println("ERROR: TODOS LOS PORCENTAJES SON 0%");
                    TieneSolucion = false;
                    break;
                }
            }
            
            if(!TieneSolucion)
            {
                break;
            }
            System.out.println("\nCRUCES DE LOS CANDIDATOS\n");
            for(int i = 0; i <= NuevosCandidatos.size()/2; i++)
            {
                Mochila Candidato1 = NuevosCandidatos.get(i);
                Mochila Candidato2 = NuevosCandidatos.get(i+1);

                Mochila Cruce1 = Pool.Cruce(Candidato1, Candidato2);

                System.out.println("Candidato 1:");
                Candidato1.ShowAsBit();
                System.out.println();

                System.out.println("Candidato 2:");
                Candidato2.ShowAsBit();          
                System.out.println();

                NuevaPoblacion.add(Cruce1);

                System.out.println("Cruce 1:");
                Cruce1.ShowAsBit();
                System.out.println();

                if(NuevaPoblacion.size() < NuevosCandidatos.size())
                {
                    Mochila Cruce2 = Pool.Cruce(Candidato2, Candidato1);
                    NuevaPoblacion.add(Cruce2);
                    System.out.println("Cruce 2:");
                    Cruce2.ShowAsBit();
                    System.out.println();
                }
                System.out.println();
            }
            System.out.println("\nNUEVA POBLACION GENERADA DE LOS CRUCES\n");
            for(int i = 0; i < NuevaPoblacion.size(); i++)
            {
                NuevaPoblacion.get(i).ShowAsBit();
                System.out.println();
            }
            System.out.println("\nNUEVA MATING POOL\n");
            Pool.setListaDeMochilas(NuevaPoblacion);
            Pool.Calcular();
            Pool.Show();
            
            Repeticiones--;
        }
    }
    
    public static void main(String[] args) throws IOException 
    {
        ArrayList<Objeto> ListaDeObjetos = new ArrayList<>();
        
        ListaDeObjetos.add(new Objeto("Botella",3,6));
        ListaDeObjetos.add(new Objeto("Agua",2,8));
        ListaDeObjetos.add(new Objeto("Bombillo",25,10));
        ListaDeObjetos.add(new Objeto("TV",4,10));
        ListaDeObjetos.add(new Objeto("PC",20,9));
        ListaDeObjetos.add(new Objeto("Zapato",9,12));
        //Se inicializa con 100 mochilas de 25 de capacidad
        MatingPool Pool = new MatingPool(100, 25, ListaDeObjetos);
        
        System.out.println("MATING POOL INICIAL:\n");
        System.out.println("Individuo | Fitness | Sumatoria | Porcentaje | Representacion con mochilas\n");
        Pool.Show();
        
        Algoritmo(Pool, 5);
        
        if(!TieneSolucion)
        {
            System.out.println("\nNO TIENE SOLUCION");
        }
    }
}
