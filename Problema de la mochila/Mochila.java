package ProblemaMochila;

import java.util.ArrayList;

public class Mochila 
{
    private ArrayList<Objeto> _ListaDeObjetos;
    private int _PesoTotal;
    private int _ValorTotal;
    private int _CapacidadMaxima;
    private double _PorcentajeDeFitness;
    
    public Mochila(int pCapacidadMaxima)
    {
        _ListaDeObjetos = new ArrayList<>();
        _PesoTotal = 0;
        _ValorTotal = 0;
        _PorcentajeDeFitness = -1;
        _CapacidadMaxima = pCapacidadMaxima;
    }
    //Muestra todos los objetos contenidos en la mochila, con los nombres de los objetos, en forma de arreglo.
    public void Show()
    {
        System.out.print("[");
        for (int i = 0; i < _ListaDeObjetos.size(); i++) 
        {
            if(_ListaDeObjetos.get(i) != null)
            {
                System.out.print(_ListaDeObjetos.get(i).getNombre());
                if(i + 1 < _ListaDeObjetos.size())
                {
                    System.out.print(", ");
                }
            }
            else
            {
                if(i + 1 < _ListaDeObjetos.size())
                {
                    System.out.print("Vacio, ");
                }
                else
                {
                    System.out.print("Vacio");
                }
            }
        }
        System.out.print("]");
    }
    //Muestra todos los objetos contenidos en la mochila en forma de bits.
    public void ShowAsBit()
    {
        System.out.print("[");
        for (int i = 0; i < _ListaDeObjetos.size(); i++) 
        {
            if(_ListaDeObjetos.get(i) != null)
            {
                System.out.print("1");
                if(i + 1 < _ListaDeObjetos.size())
                {
                    System.out.print(", ");
                }
            }
            else
            {
                if(i + 1 < _ListaDeObjetos.size())
                {
                    System.out.print("0, ");
                }
                else
                {
                    System.out.print("0");
                }
            }
        }
        System.out.print("]");
    }
    
    //Agrega un objeto a la mochila, esto aumenta su valor y peso, no hay validacion por si se pasa de su capacidad maxima de peso,
    //eso se valida despues.
    public void AgregarObjeto(Objeto pObjeto)
    {
        if(pObjeto != null)
        {
            _ListaDeObjetos.add(pObjeto);
            _PesoTotal += pObjeto.getPeso();
            _ValorTotal += pObjeto.getValor();
        }
        else
        {
            _ListaDeObjetos.add(pObjeto);
        }
    }
    
    //Si el peso total de la mochila supera su capacidad maxima, su fitness seria 0, si no este seria su valor.
    public int getFitness()
    {
        if(_PesoTotal <=  _CapacidadMaxima)
        {
            return _ValorTotal;
        }
        else
        {
            return 0;
        }
    }
    
    public ArrayList<Objeto> getListaDeObjetos() {
        return _ListaDeObjetos;
    }

    public void setListaDeObjetos(ArrayList<Objeto> _ListaDeObjetos) {
        this._ListaDeObjetos = _ListaDeObjetos;
    }

    public int getPesoTotal() {
        return _PesoTotal;
    }

    public void setPesoTotal(int _PesoTotal) {
        this._PesoTotal = _PesoTotal;
    }

    public int getValorTotal() {
        return _ValorTotal;
    }

    public void setValorTotal(int _ValorTotal) {
        this._ValorTotal = _ValorTotal;
    }

    public int getCapacidadMaxima() {
        return _CapacidadMaxima;
    }

    public void setCapacidadMaxima(int _CapacidadMaxima) {
        this._CapacidadMaxima = _CapacidadMaxima;
    }

    public double getPorcentajeDeFitness() {
        return _PorcentajeDeFitness;
    }

    public void setPorcentajeDeFitness(double _PorcentajeDeFitness) {
        this._PorcentajeDeFitness = _PorcentajeDeFitness;
    }
    
    public double compareTo(Mochila pMochila) 
    {
        return(_PorcentajeDeFitness - pMochila.getPorcentajeDeFitness());
    }
}
