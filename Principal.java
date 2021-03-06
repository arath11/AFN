import javax.swing.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.io.File;
import java.util.Hashtable;
import java.util.Scanner;

public class Principal {

    private String [] lenguajeAceptado;
    private String[] cadenasAVerificar= new String[0];
    private String entrada;

    private String[][] afn;

    private Grafo grafo;

    //ruta a carpeta
    File carpeta;

    public void entrada(){
        Scanner entradaScanner = new Scanner(System.in);
        System.out.println("Ingrese la expresion:");
        entrada = entradaScanner.nextLine();
        lenguajeAceptado=separador(entrada);
      //  imprimirFor(lenguajeAceptado);
    }

    public String [] separador(String cadena) {//SEPARA LA CANDENA Y REGRESA EL LENGUAJE ACEPTADO
        String[] salida = cadena.split("");
        int contador = 0;
        for (int i = 0; i < salida.length; i++) {
            if (!(salida[i].equals("(") || salida[i].equals(")") || salida[i].equals("*") || salida[i].equals("+"))) {
                contador++;
            }
        }
        String[] salidaRecortada = new String[contador];
        int j =0 ;
        //System.out.println(salidaRecortada.length);
        boolean alreadyExits = false;
        for (int i = 0; i < salida.length; i++) {
            if (!(salida[i].equals("(") || salida[i].equals(")") || salida[i].equals("*") || salida[i].equals("+"))) {
                alreadyExits=false;
                for(int z=0;z<salidaRecortada.length;z++){
                    if(salidaRecortada[z]!=null){
                        if (salidaRecortada[z].equals(salida[i])) {
                            alreadyExits=true;
                        }
                    }
                }
                if(alreadyExits==false) {
                    salidaRecortada[j++] = salida[i];
                }
            }
        }
        salida=new String[j];
        for(int i=0;i<j;i++){
            salida[i]=salidaRecortada[i];
        }
        return salida;
    }





    public void imprimirFor(String[] cadenas){
        System.out.println("Imprimiendocadena");
        for(int i=0;i<cadenas.length;i++){
            System.out.println(cadenas[i]);
        }
    }

    //// CONSEGUIR ARCHIVOS recursivamente

    public void conseguirArchivos(String ruta) {
        conseguirArchivosPrivado(ruta);
     //   imprimirFor(cadenasAVerificar);
    }

    private void conseguirArchivosPrivado(String ruta) {
       // String [] archivosRegreso=new String[0];
        File rutaArchivo = new File(ruta);
        if (rutaArchivo.exists()) {
            File[] archivos = rutaArchivo.listFiles();
            if (!(archivos == null)) {
                for (int i = 0; i < archivos.length; i++) {
                    if (archivos[i].isFile()) {
                        agregarArchivo(archivos[i].getName());
                    } else if (archivos[i].isDirectory()) {
                        conseguirArchivosPrivado(archivos[i].getAbsolutePath());
                    }
                }
            } else {
                System.out.println("Esta vacia ");
            }
        } else {
            System.out.println("El directori o la ruta no existeixen.");
        }
    }


    public void agregarArchivo(String nombreArchivo){

        String [] temporarl=new String[cadenasAVerificar.length+1];
        for(int i=0;i<cadenasAVerificar.length;i++){
            temporarl[i]=cadenasAVerificar[i];
        }

        String [] eliminarTipoArchivo=nombreArchivo.split("\\.");

        temporarl[cadenasAVerificar.length]=eliminarTipoArchivo[0];

        cadenasAVerificar=temporarl;
    }



    public void preparar(){
        //primero procesar las sumas
        String [] preparar= entrada.split("\\+");
        afn=new String[preparar.length][];
        //imprimirFor(preparar);
        //System.out.println("sumas");
        if(preparar.length==1 && (0==preparar[0].compareTo(""))){
            //no creamos otro nodo
            //System.out.println("No creamos otro nodo");
        }else{
            //creamos un segundo nodo y por cada esapcio creamos una conexion
            for(int i=0;i<preparar.length;i++){
              //  System.out.println("metemos a "+preparar[i]);
            }
        }

        //metemos
        String [] partido;
        //System.out.println("concatenar");
        if(preparar.length==1 && (0==preparar[0].compareTo(""))){
            //no creamos otro nodo
         //   System.out.println("No creamos otro nodo");
        }else{
            //creamos un segundo nodo y por cada esapcio creamos una conexion
            int a=0;
            for(int i=0;i<preparar.length;i++){
                partido=preparar[i].split("");
                int contador=0;
                for(int j=0;j<partido.length;j++){
                    if(partido[j].equals("*")){
                        contador++;
                    }
                }
                String [] temporal=new String[partido.length-contador];
                int tmp=0;
                for(int j=0;j<partido.length;j++){
                    try {
                        if(!partido[j].equals("*") && partido[j+1].equals("*")) {
                            //System.out.println(partido[j]+partido[j+1]+"if1");
                            //tmp++;
                            temporal[tmp++]=partido[j] + partido[j + 1];
                        }else if(partido[j].equals("*")){
                            //no hacemos nada
                        }else if(!partido[j].equals("*") && !partido[j+1].equals("*")){
                             temporal[tmp++]=partido[j];
                          //  System.out.println(partido[j]+"if2");
                          //  tmp++;
                        }
                        else{
                           // temporal[tmp++]=partido[j];
                            // System.out.println(partido[j]+"if3");
                        }

                    }catch (ArrayIndexOutOfBoundsException e){
                        //caso que termina sin *
                        //System.out.println(partido[j]+"__");
                        //tmp++;
                        temporal[tmp++]=partido[j];

                    }

                }
                partido=temporal;
                afn[a++]=temporal;

            }
        }
    }


    public int sacarTamañoDelGrafo(){
        int contador=1;
        Boolean banderaSegundo= false;
        for(int i=0;i<afn.length;i++){
            if(afn[i].length>0){
                for(int j=0;j<afn[i].length;j++){
                    if(j>0){
                        if(afn[i][j].length()==1){

                            contador++;
                        }else if(afn[i][j].length()==2){
                        }
                    }else if(j==0){

                        if(afn[i][j].length()==1 && banderaSegundo==false){

                            contador++;
                            banderaSegundo=true;
                        }else if(afn[i][j].length()==2 && banderaSegundo==false){

                        }else if(afn[i][j].length()==1 && banderaSegundo==true) {
                            contador++;
                        }
                    }
                }
            }
        }
        return contador;
    }


    public void elaborarGrafo(){
        int contador=1;
        grafo=new Grafo(sacarTamañoDelGrafo());
        boolean isNode2InUse=false;
        for(int i=0;i<afn.length;i++){
            for (int j=0;j<afn[i].length;j++){
                if(j==0) {//cuando esta en el nodo inicial
                    if (j != afn[i].length - 1){
                        if (afn[i][j].length() == 2) {
                        //    System.out.println("ebtri");
                            contador--;
                            grafo.añadir(0, 0, afn[i][j]);
                        } else if (afn[i][j].length() == 1 && isNode2InUse == false) {
                            grafo.añadir(0, 1, afn[i][j]);
                            isNode2InUse = true;
                        } else if (afn[i][j].length() == 1 && isNode2InUse != false) {
                            grafo.añadir(0, contador + 1, afn[i][j]);
                            contador++;
                        }
                    }else{
                        if (afn[i][j].length() == 2) {


                            grafo.añadir(0, 0, afn[i][j],true);
                        } else if (afn[i][j].length() == 1 && isNode2InUse == false) {
                            grafo.añadir(0, 1, afn[i][j],true);
                            isNode2InUse = true;
                        } else if (afn[i][j].length() == 1 && isNode2InUse != false) {
                            grafo.añadir(0, contador + 1, afn[i][j],true);
                            contador++;
                        }
                    }
                }else{

                    if(j!=afn[i].length-1){

                        if(afn[i][j].length()==2){
                            grafo.añadir(contador,contador,afn[i][j]);

                        }else if(afn[i][j].length()==1) {
                            grafo.añadir(contador,contador+1,afn[i][j]);
                            contador++;
                        }
                    }else{
                        if(afn[i][j].length()==2){
                            grafo.añadir(contador,contador,afn[i][j],true);
                        }else if(afn[i][j].length()==1) {
                            grafo.añadir(contador,contador+1,afn[i][j],true);
                            contador++;
                        }
                    }
                }
            }
        }
        grafo.funcionPreparacion();
    }


    public  void manejoDeCadenas(){
        boolean bandera=true;
        boolean siES=false;
        for (int a=0;a<cadenasAVerificar.length;a++){
            String[] palabra=cadenasAVerificar[a].split("");
            siES=false;
            for(int i=0;i<palabra.length;i++){
                bandera=true;
                String imprimir="";
                for(int j=i;j<=palabra.length && bandera && !siES;j++){
                    try{
                        if(estaEnElLenguaje(palabra[j])){
                            imprimir+=palabra[j];
                        }//no esta en el lenguaje
                        else {
                           bandera=false;
                        }
                    }catch (Exception e){ }

                }
                if(!imprimir.equals("")){
//                    System.out.println(imprimir);
                    grafo.modificarString(imprimir);
                    siES=grafo.funcionPreparacion();
                    if(siES){
                        System.out.println(cadenasAVerificar[a]+" si es ");
                    }else{
                     //   System.out.println(cadenasAVerificar[a]+" no es  ");
                    }

                }else {
                    //System.out.println("la cadena esta vacia ");
                }

            }

        }

    }

    //regresa true si no esta {
    public boolean estaEnElLenguaje(String entrada){
        for(int i=0; i<lenguajeAceptado.length;i++){
            if(lenguajeAceptado[i].equals(entrada)){
                return true;
            }
        }
        return false;
    }

    public void verificarCadenas(){

        for(int i=0;i<cadenasAVerificar.length;i++){
            //vamos a recorrer y si esta en el lenguaje aceptado, lo metemos

            grafo.modificarString(cadenasAVerificar[i]);
            System.out.println(grafo.funcionPreparacion());

        }

    }


    public static void correrPrograma(){
        Principal pruebas = new Principal();
        pruebas.entrada();
        pruebas.conseguirArchivos("C:\\Users\\J4\\Desktop\\CarpetaPrueba");
        //pruebas.conseguirArchivos(".//");
        pruebas.preparar();
        pruebas.elaborarGrafo();
        pruebas.manejoDeCadenas();
    }

    public static void main(String[] args) {
        correrPrograma();
    }
}
