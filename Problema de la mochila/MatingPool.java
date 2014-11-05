package ProblemaMochila;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MatingPool 
{
    final static double _UniformRate = 0.5;
    private ArrayList<Mochila> _ListaDeMochilas;
    private ArrayList<Objeto> _ListaDeObjetos;
    private Poblacion _Poblacion;
    private int _SumatoriaDeFitness;
    private ArrayList<Double> _ListaDePorcentajes;
    
    public MatingPool(int pCantidadDeMochilas, int pCapacidadMaximaDeMochilas, ArrayList<Objeto> pListaDeObjetos) throws IOException
    {
        _Poblacion = new Poblacion(pListaDeObjetos.size(),pCantidadDeMochilas);
        _ListaDeMochilas = new ArrayList<>();
        _ListaDePorcentajes = new ArrayList<>();
        _ListaDeObjetos = pListaDeObjetos;
        
        //Se crea la lista de mochilas, basado en la cantidad y capacidad maxima.
        for(int i = 0; i < pCantidadDeMochilas; i++)
        {
            _ListaDeMochilas.add(new Mochila(pCapacidadMaximaDeMochilas));
        }
        
        //Se toma la poblacion en bits, basado en esta y en la lista de objetos se va a indicar cuales objetos se van a agregar
        //a la mochila, si es 1 se agrega, si es 0 se agrega un null
        for(int i = 0; i < _Poblacion.getIndividuos().size(); i++)
        {
            for(int j = 0; j < _Poblacion.getIndividuos().get(i).getLargo(); j++)
            {
               if(_Poblacion.getIndividuos().get(i).getGenes()[j] == 1)
               {
                   _ListaDeMochilas.get(i).AgregarObjeto(pListaDeObjetos.get(j));
               }
               else
               {
                   _ListaDeMochilas.get(i).AgregarObjeto(null);
               }
            }
        }
        Calcular();
    }
    
    //Una vez creada la lista de mochilas se calcula su fitness, sumatoria de fitness y porcentaje de fitness
    //A la lista de porcentajes se le inserta un 0 al inicio y un 1 al final, despues se ordena, esto es para poder
    //elejir las mochilas con la seleccion por ruleta y un numero random
    public void Calcular()
    {
        _SumatoriaDeFitness = 0;
        _ListaDePorcentajes.clear();
        _ListaDePorcentajes.add(0.0);
        for(int i = 0; i < _ListaDeMochilas.size(); i++)
        {
            _SumatoriaDeFitness += _ListaDeMochilas.get(i).getFitness();
        }
        
        for(int i = 0; i < _ListaDeMochilas.size(); i++)
        {
            _ListaDeMochilas.get(i).setPorcentajeDeFitness((double)_ListaDeMochilas.get(i).getFitness()/_SumatoriaDeFitness);
            _ListaDePorcentajes.add(_ListaDeMochilas.get(i).getPorcentajeDeFitness());
        }
        Collections.sort(_ListaDePorcentajes);
        _ListaDePorcentajes.add(1.0);
    }
    
    //Muestra el mating pool con el siguiente formato:
    //Individuo | Fitness | Sumatoria | Porcentaje | Representacion con mochilas
    public void Show()
    {
        for(int i = 0; i < _ListaDeMochilas.size() ; i++)
        {
            _ListaDeMochilas.get(i).ShowAsBit();
            System.out.printf(" | " + "%3s", _ListaDeMochilas.get(i).getFitness()); 
            System.out.print(" | " +_SumatoriaDeFitness);
            System.out.printf(" | " + "%3.2f", _ListaDeMochilas.get(i).getPorcentajeDeFitness());
            System.out.print("% | ");
            _ListaDeMochilas.get(i).Show();
            System.out.println();
        }
    }

    //El algoritmo de cruce, recibe dos mochilas y basado en el UniformRate crea una mochila nueva
    public Mochila Cruce(Mochila pMochila1 ,Mochila pMochila2)
    {
        Mochila NewMochila = new Mochila(pMochila1.getCapacidadMaxima());
        for(int i = 0; i < _ListaDeObjetos.size(); i++)
        {
            if(i < _ListaDeObjetos.size()*_UniformRate)
            {
                NewMochila.AgregarObjeto(pMochila1.getListaDeObjetos().get(i));
            }
            else
            {
                NewMochila.AgregarObjeto(pMochila2.getListaDeObjetos().get(i));
            }
        }
        return NewMochila;
    }
    
    //Recibe un numero random, busca en la lista de porcentajes en que intervalos se encuentra, si concuerda, toma el intervalo 
    //derecho (i + 1), si este es 1 retorna el izquierdo (i) y si este es 0 retorna un 1, este es el caso en el que solo hay una 
    //solucion y todos los demas con 0%, despues retorna la mochila que concuerde con este porcentaje.
    public Mochila getCandidato(double pRandom)
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
        
        for (int i = 0; i < _ListaDeMochilas.size(); i++) 
        {
            if(_ListaDeMochilas.get(i).getPorcentajeDeFitness() == Porcentaje)
            {
                return _ListaDeMochilas.get(i);
            }
        }        
        return null;
    }
    
    public ArrayList<Mochila> getListaDeMochilas() {
        return _ListaDeMochilas;
    }

    public void setListaDeMochilas(ArrayList<Mochila> _ListaDeMochilas) {
        this._ListaDeMochilas = _ListaDeMochilas;
    }

    public int getSumatoriaDeFitness() {
        return _SumatoriaDeFitness;
    }

    public void setSumatoriaDeFitness(int SumatoriaDeFitness) {
        this._SumatoriaDeFitness = SumatoriaDeFitness;
    }

    public Poblacion getPoblacion() {
        return _Poblacion;
    }

    public void setPoblacion(Poblacion _Poblacion) {
        this._Poblacion = _Poblacion;
    }

    public ArrayList<Double> getListaDePorcentajes() {
        return _ListaDePorcentajes;
    }

    public void setListaDePorcentajes(ArrayList<Double> _ListaDePorcentajes) {
        this._ListaDePorcentajes = _ListaDePorcentajes;
    }

    public ArrayList<Objeto> getListaDeObjetos() {
        return _ListaDeObjetos;
    }

    public void setListaDeObjetos(ArrayList<Objeto> _ListaDeObjetos) {
        this._ListaDeObjetos = _ListaDeObjetos;
    }
}
