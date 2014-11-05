package SortGenetico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Lista
{
    private ArrayList<Integer> List;
    private double Porcentaje;
    
    public Lista(int ... pNumeros)
    {
        List = new ArrayList<>();
        for(int Numero : pNumeros)
        {
            List.add(Numero);
        }
        Collections.shuffle(List);  
        
        Porcentaje = 0.0;
    }
    
    //Si el de la izquierda es menor al de la derecha o igual, suma un uno al fitness
    public int getFitness()
    {
        int cont = 0;
        for(int i = 0; i < List.size()-1; i++)
        {
            if(List.get(i) < List.get(i+1) || Objects.equals(List.get(i), List.get(i+1)))
            {
                cont++;
            }
        }
        return cont;
    }
    
    //Recibe dos index de la lista, pone el segundo valor idexado a la izquierda del primero y hace un shift de los demas
    //Ejemplo: [3,4,5,6,1,2].CruceOrden(0,5) = [2,3,4,5,6,1], pone el 2 con index 5 a la izquierda 3 con index 0 y hace un shift 
    public void CruceOrden(int pIndex1, int pIndex2)
    {
        if(pIndex1 != pIndex2)
        {
            int index1 = Math.min(pIndex1, pIndex2);
            int index2 = Math.max(pIndex1, pIndex2);

            int fitness1 = getFitness();
            
            ArrayList<Integer> Lista = (ArrayList<Integer>) List.clone();

            List.set(index1, Lista.get(index2));

            for(int i = index1+1; i <= index2; i++)
            {
                List.set(i, Lista.get(i-1));
            }
            int fitness2 = getFitness();

            if(fitness1 > fitness2)
            {
                List = Lista;
            }
        }
    }
    
    public ArrayList<Integer> getList() {
        return List;
    }

    public void setList(ArrayList<Integer> List) {
        this.List = List;
    }

    public double getPorcentaje() {
        return Porcentaje;
    }

    public void setPorcentaje(double Porcentaje) {
        this.Porcentaje = Porcentaje;
    }
}
