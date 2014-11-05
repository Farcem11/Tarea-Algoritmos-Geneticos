package ProblemaMochila;

import java.util.ArrayList;

public class Poblacion 
{
    private ArrayList<Individuo> _Individuos;

    public Poblacion(int pLargoDeLaPoblacion, int pTamañoDeLaPoblacion)
    {
        _Individuos = new ArrayList<>();
        for (int i = 0; i < pTamañoDeLaPoblacion; i++)
        {
            _Individuos.add(new Individuo(pLargoDeLaPoblacion));
        }
    }
    
    public void Show()
    {
        for (Individuo _Individuo : _Individuos) 
        {
            _Individuo.Show();
            System.out.println();
        }
    }
    
    public ArrayList<Individuo> getIndividuos() {
        return _Individuos;
    }

    public void setIndividuos(ArrayList<Individuo> _Individuos) {
        this._Individuos = _Individuos;
    }
}