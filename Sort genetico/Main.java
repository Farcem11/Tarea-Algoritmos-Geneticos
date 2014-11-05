package SortGenetico;

import java.util.ArrayList;
import java.util.Collections;

public class Main
{
    static ArrayList<Lista> NuevosCandidatos = new ArrayList<>();
    static boolean TieneSolucion = true;
    
    //Retorna un numero decimal entre un intervalo
    static double RandomDecimal(double pMin, double pMax)
    {
        return pMin + Math.random() * (pMax - pMin);
    }
    
    //Retorna un numero entero entre un intervalo
    static int RandomEntero(int pMin, int pMax) 
    {
        return pMin + (int)(Math.random() * ((pMax - pMin) + 1));
    }
    
    //Utiliza todos los metodos de la clases para poder crear un nuevo mating pool, basado en el actual
    static void Algoritmo(MatingPool Pool, int Repeticiones)
    {
        while(Repeticiones > 0)
        {
            ArrayList<Lista> NuevaPoblacion = new ArrayList<>();
            
            NuevosCandidatos.clear();
            
            System.out.println("\nNUEVOS CANDIDATOS BASADOS EN EL PORCENTAJE DE FITNESS Y UN RANDOM:\n");
            for(int i = 0; i < Pool.getListas().size(); i++)
            {           
                NuevosCandidatos.add(Pool.getCandidato(RandomDecimal(0, 1)));
                System.out.print("Nuevo candidato: ");
                //Si es nulo significa que no se encontro un intervalo para el resultado del random en la ruleta y esto es solo 
                //posible si todos los porcentajes son 0%, es decir ningun candidato es valido para tomarse en cuenta.
                if(NuevosCandidatos.get(i) != null)
                {
                    System.out.println(NuevosCandidatos.get(i).getList().toString());
                    
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
            for(int i = 0; i < NuevosCandidatos.size(); i++)
            {
                Lista Candidato = NuevosCandidatos.get(i);

                System.out.println("Candidato:");
                System.out.println(Candidato.getList().toString());
                System.out.println();
                
                Candidato.CruceOrden(RandomEntero(0,(Candidato.getList().size()/2)), 
                                     RandomEntero((Candidato.getList().size()/2),Candidato.getList().size()-1));
                NuevaPoblacion.add(Candidato);
                
                System.out.println("Permutacion:");
                System.out.println(Candidato.getList().toString());
                System.out.println();
            }
            System.out.println("\nNUEVA POBLACION GENERADA DE LOS CRUCES\n");
            for(int i = 0; i < NuevaPoblacion.size(); i++)
            {
                System.out.println(NuevaPoblacion.get(i).getList().toString());
                System.out.println();
            }
            System.out.println("\nNUEVA MATING POOL\n");
            Pool.setListas(NuevaPoblacion);
            Pool.Calcular();
            Pool.Show();
            
            Repeticiones--;
        }
    }
    
    public static void main(String args[])
    {
        MatingPool Pool = new MatingPool(20,9,8,3,7,4,5,1,6,2,7);
        Pool.Show();
        Algoritmo(Pool, 100);
    }
}
