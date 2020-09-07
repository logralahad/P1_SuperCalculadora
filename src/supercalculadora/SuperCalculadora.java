/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supercalculadora;
import java.io.*;
import static java.lang.Math.abs;
import java.util.Scanner;

/**
 *
 * @author logra
 */

public class SuperCalculadora {

    /**
     * @param args the command line arguments
     */
    
    public static final Integer EOF = -1;
    public static final Integer EOL = 10;
    public static final Integer SPACE = 32;
    
    public static String convertirLista(ListaDoble a){
        StringBuilder str = new StringBuilder();
        Nodo aux = a.inicio;
        
        while(aux != null){
            str.append(aux.dato);
            aux = aux.siguiente;
        }
        str.append("\n");
        String nuevo = str.toString();
        return nuevo;
    } 
    
    public static void cargarArchivo(ListaDoble num1, ListaDoble num2){
        
        try{           
            Scanner str = new Scanner(System.in);
            System.out.print("\nEscriba la direccion del archivo: ");
            String direccion = str.nextLine();
            
            File origen = new File(direccion, "INPUT.TXT");
            FileReader reader = new FileReader(origen);
            
            int byteInfo = 0;
            int i = 0;
            int byteEscritura;
            
           
            while( (byteInfo = reader.read()) != EOL){
                if(byteInfo == 13) continue;
                byteEscritura = byteInfo - '0';
                num1.insertarFinal(byteEscritura);
            }
            
            while( (byteInfo = reader.read()) != EOF){
                byteEscritura = byteInfo - '0';
                num2.insertarFinal(byteEscritura);
            }

            reader.close();
            
        }
        catch(Exception e){
            System.out.println("Hubo un error");
        }
    }
    
    public static void descargarArchivo(ListaDoble num1, ListaDoble num2){
        
        ListaDoble res = new ListaDoble();
        int comparador = res.comparacion(num1, num2);

        String suma = convertirLista(res.sumar(num1, num2));
        String resta = convertirLista(res.restar(num1, num2));
        String mux = convertirLista(res.multiplicar(num1, num2));
        String div = convertirLista(res.dividir(num1, num2));
        String residuo = convertirLista(res.residuo(num1, num2));
        
        
        try{
            Scanner str = new Scanner(System.in);
            System.out.print("\nEscriba en donde quiere guardarlo: ");
            String direccion = str.nextLine();
            
            File destino = new File(direccion, "OUTPUT.TXT");
            FileWriter writer = new FileWriter(destino, false);
            
            writer.write(comparador + '0');
            writer.write("\n" + suma);
            writer.write(resta);
            writer.write(mux);
            writer.write(div);
            writer.write(residuo);
            
            writer.close();
            
        }
        catch(Exception e){
            System.out.println("Hubo un error");
        }
    }
    
    public static void convertirString(ListaDoble num1, ListaDoble num2){
        Scanner sc = new Scanner(System.in);
        String numero = new String();
        
        System.out.print("Escriba el primer numero: ");
        numero = sc.nextLine();
        for(int i = 0; i < numero.length(); i++){
            int ascii = numero.charAt(i) - '0';
            num1.insertarFinal(ascii);
        }
               
        
        System.out.print("Escriba el segundo numero: ");
        numero = sc.nextLine();
        for(int i = 0; i < numero.length(); i++){
            int ascii = numero.charAt(i) - '0';
            num2.insertarFinal(ascii);
        }
    }
    
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        ListaDoble num1 = new ListaDoble();
        ListaDoble num2 = new ListaDoble();
        ListaDoble res = new ListaDoble();
        Scanner sr = new Scanner(System.in);
        int modo;
        
        System.out.println("Bienvenido a la SuperCalculadora");
        System.out.println("[0]Archivo\t[1]Consola");
        System.out.print("Seleccione el modo: ");
        
        modo = sr.nextInt();
        
        if(modo == 0){
            cargarArchivo(num1, num2);
            descargarArchivo(num1, num2);
        }else{
            convertirString(num1, num2);
            int mayor = res.comparacion(num1, num2);
            if(mayor == 3) mayor -= 2;

            System.out.print("\nNumero mayor: ");
            System.out.print(mayor + "\n");
            
            System.out.print("Suma: ");
            res = res.sumar(num1, num2);
            res.recorrer();
            System.out.print("\n");

            System.out.print("Resta: ");
            res = res.restar(num1, num2);
            res.recorrer();
            System.out.print("\n");

            System.out.print("Multiplicacion: ");
            res = res.multiplicar(num1, num2);
            res.recorrer();
            System.out.print("\n");

            System.out.print("Division: ");
            res = res.dividir(num1, num2);
            res.recorrer();
            System.out.print("\n");

            System.out.print("Residuo: ");
            res = res.residuo(num1, num2);
            res.recorrer();
        }
        
    }
    
}

//C:\Users\logra\Desktop