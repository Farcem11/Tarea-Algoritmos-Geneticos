##Instituto Tecnológico de Costa Rica
##(TEC)

##Escuela de computación

##Curso: Análisis de algoritmos

##Tarea de algoritmos geneticos

##Profesor: Mauricio Rojas

##Estudiante: Fabian Arce Molina

##2013012811

##4/Noviembre/2014
___
###Profe ahi meti el proyecto en netbeans en un .rar, cualquier cosa
___
    Empleando como base la implementación en Java vista en clase, utilice algoritmos genéticos para resolver los siguientes problemas:
    
    a) Resuelva el “problema de la mochila” que ya se ha estudiado en clase.

    b) Implemente un algoritmo de ordenamiento de arreglos numéricos. Recuerde la teoría sobre algoritmos genéticos aplicados a permutaciones.

___
##Problema de la mochila:
___

> Consiste en que se tiene una mochila con una capacidad maxima para poder meter objetos, estos tienen un peso y un valor, la capacidad de la mochila se limita solo por el peso de los objetos.
___
> Se busca tomar la mayor cantidad de objetos y meterlos en la mochila de forma que se obtenga la mayor ganacia posible con el menor peso.
___
###Compilar:
        //Se crea una lista de objetos.
        ArrayList<Objeto> ListaDeObjetos = new ArrayList<>();
        //Se llena la lista con la cantidad de objetos que se desee
        //El constructor recibe el nombre del objeto, valor y peso respectivamente
        ListaDeObjetos.add(new Objeto("Botella",3,6));
        ListaDeObjetos.add(new Objeto("Agua",2,8));
        ListaDeObjetos.add(new Objeto("Bombillo",25,10));
        ListaDeObjetos.add(new Objeto("TV",4,10));
        ListaDeObjetos.add(new Objeto("PC",20,9));
        ListaDeObjetos.add(new Objeto("Zapato",9,12));
        //Se crea el mating pool inicial
        //Recibe la cantidad de mochilas que se quieren crear, su capacidad maxima y la lista de objetos
        MatingPool Pool = new MatingPool(15, 30, ListaDeObjetos);
        System.out.println("MATING POOL INICIAL:\n");
        //Outputs para indicar que contiene cada columna a la hora de mostrar el mating pool
        System.out.println("Individuo | Fitness | Sumatoria | Porcentaje | Representacion con mochilas\n");
        Pool.Show();
        //Se llama al algoritmo genetico que recibe un mating pool y la cantidad de veces se va a ejecutar el algoritmo    
        Algoritmo(Pool, 5);
___
##Aspectos
> Para representar las mochilas como un arreglo de bits se utilizo el siguiente formato:

> Por ejemplo:

> ListaDeObjetos = [Botella, Agua, TV, PC, Bombillo]

> Mochila(en bits) = [1, 0, 0, 1, 1]

> Mochila(represtacion) = [Botella, vacio, vacio, PC, Bombillo]

> Es decir indica, basado en el arreglo de bits, cuales objetos si estan en la mochila, si es un 1 en la posicion 3 del arreglo significa que la mochila contiene al objeto en la posicion 3 de la lista de objetos, si es un 0 significa que no y se pone como vacio.
___
##Algoritmo de ordenamiento:
___
###Compilar:
        //Recibe la cantidad de individuos(listas), que seria el 20, que se van a crear y los numeros que van a contener los arreglos, del 9 en adelante.
        MatingPool Pool = new MatingPool(20,9,8,3,7,4,5,1,6,2,7);
        //Muestra el mating pool inicial en consola
        Pool.Show();
        //Recibe un mating pool y la cantidad de veces que se desea crear generaciones
        Algoritmo(Pool, 100);
___

