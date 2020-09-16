import java.util.*;

class Grafo {

    private ArrayList<ArrayList<Nodo> > grafo;

    private String [] datos;

    public Grafo(int v){
        grafo = new ArrayList<ArrayList<Nodo> >(v);

        for (int i = 0; i < v; i++){
            grafo.add(new ArrayList<Nodo>());
        }
    }

    public void modificarString(String dato){
        this.datos=dato.split("");
/*
        for(int i =0;i<datos.length;i++){
            System.out.println(datos[i]);
        }*/
    }

    public void añadir(int origen, int destino,String valor){
        grafo.get(origen).add(new Nodo(valor,destino));
    }

    public void añadir(int origen, int destino,String valor,boolean esFinal){
        grafo.get(origen).add(new Nodo(valor,destino,esFinal));
    }

    public void imprimir(){
        for(int i=0;i<grafo.size();i++){
            System.out.println("Vertice:" + i);
            for (int j = 0; j < grafo.get(i).size(); j++) {
                System.out.print(i+" "+grafo.get(i).get(j).toString()+",");
            }
            System.out.println();
        }
    }

    public boolean funcionPreparacion(){
        return funcionRecursiva(0, this.datos, 0);

    }

    private boolean funcionRecursiva(int numero, String [] a, int posicion) {
        //imprimir();
        ArrayList<Nodo> tmp= (grafo.get(numero));
        for(int i=0;i<tmp.size();i++){

                //ceradura
                if(tmp.get(i).caracter.length()==2){

                    String [] stringTmp=tmp.get(i).caracter.split("");
                    boolean bandera = true;

                    //cuando es el ultimo
                    try {
                        for(int j=posicion;j<a.length && bandera;j++){
                            if(a[j].equals(stringTmp[0])){
                                // System.out.println("Entroooo");
                                posicion++;
                            }else {
                                bandera=false;
                            }
                        }
                        if(tmp.get(i).esFinal){
                            return true;
                        }else {
                            //        return funcionRecursiva(tmp.get(i+1).numero, a, posicion);
                        }
                    }catch (Exception e){
                        int j=0;
                        try{
                            while (a[j++]==stringTmp[0]){
                                posicion++;
                            }
                        }catch (Exception q){

                        }
                    }

                }else {
                    try{
                        if (tmp.get(i).caracter.equals(a[posicion])) {
                            //System.out.println(tmp.get(i));
                            if (posicion == a.length - 1 && tmp.get(i).esFinal) {
                                return true;
                            }else if(  tmp.get(i).esFinal) {
                                return true;
                            }
                            else{
                                return funcionRecursiva(tmp.get(i).numero, a, posicion + 1);
                            }
                        }
                    }catch (Exception e){

                        return false;
                    }
                }



        }
        return false;
    }


    public static void main(String[] args)
    {
        Grafo prueba=new Grafo(10);
        prueba.modificarString("abd");

        prueba.añadir(0,1,"a");
        prueba.añadir(1,2,"b");
        prueba.añadir(2,3,"c",true);
        System.out.println(prueba.funcionPreparacion());

        /*prueba.añadir(0,1,"a");
        prueba.añadir(0,1,"c");
        prueba.añadir(0,1, "x");
        prueba.añadir(1,2, "b");
        prueba.añadir(2,4, "a");
        prueba.añadir(1,5,"b");
        prueba.añadir(5,6,"a");
        prueba.añadir(1,7, "a");
        prueba.añadir(7,8,"b");
        prueba.añadir(8,9,"a");*/



//        prueba.imprimir();

        /*prueba.añadir(0,1);
        prueba.añadir(0,4);
        prueba.añadir(1,2);
        prueba.añadir(1,3);
        prueba.añadir(1,4);
        prueba.añadir(2,3);
        prueba.añadir(3,4);
        //prueba.añadir(2,1);
        prueba.imprimir();

        /*int V = 5;
        ArrayList<ArrayList<Integer> > adj  = new ArrayList<ArrayList<Integer> >(V);

        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<Integer>());

        // Adding edges one by one 
        addEdge(adj, 0, 1);
        addEdge(adj, 0, 4);
        addEdge(adj, 1, 2);
        addEdge(adj, 1, 3);
        addEdge(adj, 1, 4);
        addEdge(adj, 2, 3);
        addEdge(adj, 3, 4);

        printGraph(adj);*/
    }
} 