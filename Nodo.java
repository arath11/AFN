public class Nodo {
    String caracter;
    Integer numero;
    Boolean esFinal;

    public Nodo(String valor, int numero){
        this.caracter=valor;
        this.numero=numero;
        esFinal=false;
    }


    public Nodo(String valor, int numero,boolean esFinal){
        this.caracter=valor;
        this.numero=numero;
        this.esFinal=esFinal;
    }

    public String toString(){
        String regreso= (this.caracter+"-"+this.numero);
        if(esFinal){
            regreso+=" Es final";
        }
        return regreso;
    }

    public int verificarUltimo(String valor){
        if(caracter.equals(valor)){
            return this.numero;
        }
        return -1;
    }

}
