package SortGenetico;

import java.util.ArrayList;
import java.util.Collections;

public class MatingPool 
{
    private int[] _Numeros; 
    private int _SumatoriaDeFitness;
    private ArrayList<Lista> _Listas;
    private ArrayList<Double> _ListaDePorcentajes;
    static ArrayList<ArrayList<Integer>> Cambios = new ArrayList<>();
    
    //Recibe el tamaño del mating pool (la cantidad de individuos a generar) y una secuencia de numeros para hacer las listas
    public MatingPool(int pTamaño, int ... pNumeros)
    {
        _Numeros = pNumeros;
        _SumatoriaDeFitness = 0;
        _ListaDePorcentajes = new ArrayList<>();
        _Listas = new ArrayList<>();
        while(pTamaño > 0)
        {
            _Listas.add(new Lista(pNumeros));
            pTamaño--;
        }
        Calcular();
    }

    //Muestra el mating pool con el siguiente formato:
    //Lista | Fitness | Sumatoria | Porcentaje
    public void Show()
    {
        for(int i = 0; i < _Listas.size() ; i++)
        {
            System.out.print(_Listas.get(i).getList().toString());
            System.out.printf(" | " + "%3s", _Listas.get(i).getFitness()); 
            System.out.print(" | " +getSumatoriaDeFitness());
            System.out.printf(" | " + "%3.2f", _Listas.get(i).getPorcentaje());
            System.out.print("%");
            System.out.println();
        }
    }
    
    //Una vez creada la lista de mochilas se calcula su fitness, sumatoria de fitness y porcentaje de fitness
    //A la lista de porcentajes se le inserta un 0 al inicio y un 1 al final, despues se ordena, esto es para poder
    //elejir las mochilas con la seleccion por ruleta y un numero random
    public void Calcular()
    {
        _ListaDePorcentajes.clear();
        _ListaDePorcentajes.add(0.0);
        _SumatoriaDeFitness = 0;
        for(int i = 0; i < _Listas.size(); i++)
        {
            _SumatoriaDeFitness += _Listas.get(i).getFitness();
        }
        
        for(int i = 0; i < _Listas.size(); i++)
        {
            _Listas.get(i).setPorcentaje((double)_Listas.get(i).getFitness()/_SumatoriaDeFitness);
            _ListaDePorcentajes.add(_Listas.get(i).getPorcentaje());
        }
        Collections.sort(_ListaDePorcentajes);
        _ListaDePorcentajes.add(1.0);        
    }
    
    //Recibe un numero random, busca en la lista de porcentajes en que intervalos se encuentra, si concuerda, toma el intervalo 
    //derecho (i + 1), si este es 1 retorna el izquierdo (i) y si este es 0 retorna un 1, este es el caso en el que solo hay una 
    //solucion y todos los demas con 0%, despues retorna la mochila que concuerde con este porcentaje.
    public Lista getCandidato(double pRandom)
    {
        System.out.printf("Numero entre 0 y 1: " + "%3.2f",pRandom);
        System.out.println();
        double Porcentaje = -1;
        for(int i = 0; i < _ListaDePorcentajes.size()-1 && Porcentaje == -1; i++)
        {
            if(_ListaDePorcentajes.get(i) <= pRandom && pRandom <= _ListaDePorcentajes.get(i+1))
            {
                if(_ListaDePorcentajes.get(i+1) != 1)
                {
                    Porcentaje = _ListaDePorcentajes.get(i+1);
                }
                else if(_ListaDePorcentajes.get(i) == 0)
                {
                    Porcentaje = _ListaDePorcentajes.get(i+1);
                }   
                else
                {
                    Porcentaje = _ListaDePorcentajes.get(i);
                }
            }
        }
        
        for (int i = 0; i < _Listas.size(); i++) 
        {
            if(_Listas.get(i).getPorcentaje() == Porcentaje)
            {
                return _Listas.get(i);
            }
        }        
        return null;
    }
 
    //Permutacion de PMX, no se utilizo para resolver este ejercicio, pero no lo quiero borrar, lo puedo usar en un futuro
    public Lista CrucePermutacionPMX(Lista pLista1, Lista pLista2, int pIndex1, int pIndex2)
    {
        Cambios.clear();
        int index1 = Math.min(pIndex1, pIndex2);
        int index2 = Math.max(pIndex1, pIndex2);
        
        int index1Temp = index1;
        int NumeroTemp;

        boolean indexYaInsertado = false;
        for(int i = 0; i < (index2 - index1)+1; i++)
        {
            Cambios.add(new ArrayList<Integer>());   
            NumeroTemp = pLista1.getList().get(index1Temp);
            for(int j = 0; j < pLista2.getList().size(); j++)
            {
                if(pLista2.getList().get(j) == NumeroTemp)
                {
                    if(index1Temp != j)
                    {
                        if(index1 <= j && j <= index2)
                        {
                            NumeroTemp = pLista1.getList().get(j);
                            j = 0;
                        }
                        else
                        {
                            for(int k = 0; k < Cambios.size(); k++)
                            {
                                if(!Cambios.get(k).isEmpty())
                                {
                                    if(Cambios.get(k).get(0) == j)
                                    {
                                        indexYaInsertado = true;
                                    }
                                }
                            }
                            if(!indexYaInsertado)
                            {
                                Cambios.get(i).add(j);
                                Cambios.get(i).add(index1Temp);
                            }
                            indexYaInsertado = false;
                        }
                    }  
                }
            }
            index1Temp++;
        }
        
        ArrayList<Integer> Lista3 = (ArrayList<Integer>) pLista2.getList().clone();
        
        for(int i = index1; i <= index2; i++)
        {
            Lista3.set(i,pLista1.getList().get(i));
        }
        for(int i = 0; i < Cambios.size(); i++)
        {
            if(!Cambios.get(i).isEmpty())
            {
                Lista3.set(Cambios.get(i).get(0), pLista2.getList().get(Cambios.get(i).get(1)));
            }
        }
        Lista NewLista = new Lista(0);
        NewLista.setList(Lista3);
        return NewLista;
    }

    public int getSumatoriaDeFitness() {
        return _SumatoriaDeFitness;
    }

    public void setSumatoriaDeFitness(int _SumatoriaDeFitness) {
        this._SumatoriaDeFitness = _SumatoriaDeFitness;
    }

    public ArrayList<Lista> getListas() {
        return _Listas;
    }

    public void setListas(ArrayList<Lista> _Listas) {
        this._Listas = _Listas;
    }

    public ArrayList<Double> getListaDePorcentajes() {
        return _ListaDePorcentajes;
    }

    public void setListaDePorcentajes(ArrayList<Double> _ListaDePorcentajes) {
        this._ListaDePorcentajes = _ListaDePorcentajes;
    }

    public int[] getNumeros() {
        return _Numeros;
    }

    public void setNumeros(int[] _Numeros) {
        this._Numeros = _Numeros;
    }
}
