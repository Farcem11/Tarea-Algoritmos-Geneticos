package ProblemaMochila;

import java.util.Arrays;
import java.util.Random;

public class Individuo 
{
    private int _Largo;
    private byte[] _Genes;
    private int _Fitness;
    public Individuo(int pLargo) 
    {
        _Largo = pLargo;
        _Fitness = 0;
        _Genes = new byte [_Largo];
        Random rg = new Random();
        for (int i = 0; i < _Largo; i++) 
        {
            byte Gen = (byte) rg.nextInt(2);
            _Genes[i] = Gen;
        }
    }
    public void Show()
    {
        System.out.print(Arrays.toString(_Genes));
    }
  
    public byte[] getGenes() {
        return _Genes;
    }

    public void setGenes(byte[] _Genes) {
        this._Genes = _Genes;
    }

    public int getFitness() {
        return _Fitness;
    }

    public void setFitness(int _Fitness) {
        this._Fitness = _Fitness;
    }

    public int getLargo() {
        return _Largo;
    }

    public void setLargo(int _Largo) {
        this._Largo = _Largo;
    }
}
        



