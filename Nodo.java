public class Nodo {
    String caracter;
    Integer numero;

    public Nodo(String valor, int numero){
        this.caracter=valor;
        this.numero=numero;
    }

    public String toString(){
        String regreso= (this.caracter+"-"+this.numero);
        return regreso;
    }

    public int verificar(String valor){
        if(caracter.equals(valor)){
            return this.numero;
        }
        return -1;
    }

}
