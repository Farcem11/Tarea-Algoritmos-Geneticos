package ProblemaMochila;

public class Objeto 
{
    private int _Valor;
    private int _Peso;
    private String _Nombre;
    
    public Objeto(String pNombre, int pValor, int pPeso)
    {
        _Nombre = pNombre;
        _Peso = pPeso;
        _Valor = pValor;
    }

    public int getValor() {
        return _Valor;
    }

    public void setValor(int Valor) {
        this._Valor = Valor;
    }

    public int getPeso() {
        return _Peso;
    }

    public void setPeso(int Peso) {
        this._Peso = Peso;
    }

    public String getNombre() {
        return _Nombre;
    }

    public void setNombre(String _Nombre) {
        this._Nombre = _Nombre;
    }
    
}
