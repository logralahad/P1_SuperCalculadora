/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supercalculadora;

import static java.lang.Math.abs;

/**
 *
 * @author logra
 */
public class ListaDoble {
    
    Nodo inicio = null;
    Nodo fin = null;
    
    public Nodo crearNodo(int dato){
        Nodo nuevo = new Nodo();
        nuevo.anterior = null;
        nuevo.siguiente = null;
        nuevo.dato = dato;
        return nuevo;
    }
    
    public boolean listaVacia(){
        return (inicio == null) && (fin == null);
    }
    
    public void insertarInicio(int dato){
        
        Nodo nuevo = crearNodo(dato);
        
        if(listaVacia()){
            inicio = nuevo;
            fin = nuevo;
        }
        else{
            nuevo.siguiente = inicio;
            inicio.anterior = nuevo;
            inicio = nuevo;
        }
    }
    
    public void insertarFinal(int dato){
        Nodo nuevo = crearNodo(dato);
        if(listaVacia()){
            inicio = nuevo;
            fin = nuevo;
        }
        else{
            nuevo.anterior = fin;
            fin.siguiente = nuevo;
            fin = nuevo;
        }
    }
    
    
    public int comparacion(ListaDoble a, ListaDoble b){
        Nodo aux_a = a.inicio;
        Nodo aux_b = b.inicio;
        
        int tam_a = a.tam(a);
        int tam_b = b.tam(b);
        
        if(tam_b < tam_a) return 1;
        else if(tam_a < tam_b) return 2;
        else{
            while(aux_a != null && aux_b != null){
                if(aux_a.dato < aux_b.dato){
                    return 2;
                }else if(aux_b.dato < aux_a.dato){
                        return 1;
                }
                aux_a = aux_a.siguiente;
                aux_b = aux_b.siguiente;
            }
        }
        return 3;
    }
    
    
    public ListaDoble sumar(ListaDoble a, ListaDoble b){
        
        int opc = a.comparacion(a,b);
        int res = 0, carry = 0;
        
        ListaDoble resultado = new ListaDoble();
        Nodo aux1 = a.fin;
        Nodo aux2 = b.fin;
        
        if(opc == 1){
            do{
                if(aux1 != null && aux2 != null){
                    res = (aux1.dato + aux2.dato + carry) % 10;
                    carry = (aux1.dato + aux2.dato + carry) / 10;
                    aux1 = aux1.anterior;
                    aux2 = aux2.anterior;
                }
                else if(aux1 != null && aux2 == null){
                    res = (aux1.dato + carry) % 10;
                    carry = (aux1.dato + carry) / 10;
                    aux1 = aux1.anterior;
                }
                resultado.insertarInicio(res);
            }while(aux1 != null);
            if(carry != 0){
                resultado.insertarInicio(carry);
            }
            
        }else if(opc == 2){
            do{
                if(aux1 != null && aux2 != null){
                    res = (aux1.dato + aux2.dato + carry) % 10;
                    carry = (aux1.dato + aux2.dato + carry) / 10;
                    aux1 = aux1.anterior;
                    aux2 = aux2.anterior;
                }
                else if(aux2 != null && aux1 == null){
                    res = (aux2.dato + carry) % 10;
                    carry = (aux2.dato + carry) / 10;
                    aux2 = aux2.anterior;
                }
                resultado.insertarInicio(res);
            }while(aux2 != null);
            if(carry != 0){
                resultado.insertarInicio(carry);
            }
        }else{
            while(aux1 != null && aux2 != null){
                res = (aux1.dato + aux2.dato + carry) % 10;
                carry = (aux1.dato + aux2.dato + carry) / 10;
                aux1 = aux1.anterior;
                aux2 = aux2.anterior;
                resultado.insertarInicio(res);
            }
            if(carry != 0){
                resultado.insertarInicio(carry);
            }
        }
        resultado.simplificar(resultado);
        return resultado;
    }
    
    public ListaDoble restar(ListaDoble a, ListaDoble b){
        
        int opc = a.comparacion(a,b);
        int res = 0, carry = 0;
        
        ListaDoble resultado = new ListaDoble();
        Nodo aux1 = a.fin;
        Nodo aux2 = b.fin;
        
        if(opc == 1){
            do{
                if(aux1 != null && aux2 != null){
                    if(aux1.dato < aux2.dato){
                        res = 10 + aux1.dato - aux2.dato - carry;
                        carry = 1;
                    }else if(aux1.dato <= aux2.dato && carry == 1){
                        res = 10 + aux1.dato - aux2.dato - carry;
                        carry = 1;
                    }
                    else{
                        res = aux1.dato - aux2.dato - carry;
                        carry = 0;
                    }
                    aux1 = aux1.anterior;
                    aux2 = aux2.anterior;
                }
                else if(aux1 != null && aux2 == null){
                    if(aux1.dato >= 1){
                        res = aux1.dato - carry;
                        carry = 0;
                    }else{
                        if(carry != 0){
                            res = 10 + aux1.dato - carry;
                            carry = 1;
                        }else{
                            res = aux1.dato;
                        }
                    }
                    aux1 = aux1.anterior;
                }
                resultado.insertarInicio(res);
            }while(aux1 != null);

        }else if(opc == 2){
            do{
                if(aux2 != null && aux1 != null){
                    if(aux2.dato < aux1.dato){
                        res = 10 + aux2.dato - aux1.dato - carry;
                        carry = 1;
                    }else if(aux2.dato <= aux1.dato && carry == 1){
                        res = 10 + aux2.dato - aux1.dato - carry;
                        carry = 1;
                    }
                    else{
                        res = aux2.dato - aux1.dato - carry;
                        carry = 0;
                    }
                    aux1 = aux1.anterior;
                    aux2 = aux2.anterior;
                }
                else{
                    if(aux2.dato >= 1){
                        res = aux2.dato - carry;
                        carry = 0;
                    }else{
                        if(carry != 0){
                            res = (10 + aux2.dato) - carry;
                            carry = 1;
                        }else{
                            res = aux2.dato;
                        }
                    }
                    aux2 = aux2.anterior;
                }
                resultado.insertarInicio(res);
            }while(aux2 != null);
        }else{
            resultado.insertarInicio(0);
        }
        resultado.simplificar(resultado);
        return resultado;
    }
    
    public ListaDoble multiplicar(ListaDoble a, ListaDoble b){

        int opc = a.comparacion(a,b);
        int res = 0, carry = 0, cont = 0;
        
        ListaDoble resultado = new ListaDoble();
        resultado.insertarInicio(0);
        
        Nodo aux1 = a.fin;
        Nodo aux2 = b.fin;
        
        if(opc == 1 || opc == 3){
            while(aux2 != null){
                ListaDoble recipiente = new ListaDoble();
                while(aux1 != null){
                    res = ((aux1.dato * aux2.dato) + carry) % 10;
                    carry = ((aux1.dato * aux2.dato) + carry) / 10;
                    recipiente.insertarInicio(res);
                    aux1 = aux1.anterior;
                }
                if(carry != 0){
                    recipiente.insertarInicio(carry);
                    carry = 0;
                }
                
                for(int i = 0; i < cont; i++){
                    recipiente.insertarFinal(0);
                }

                resultado = resultado.sumar(resultado, recipiente);
                cont++;
                aux2 = aux2.anterior;
                aux1 = a.fin;
            }
        }else if(opc == 2){
            while(aux1 != null){
                ListaDoble recipiente = new ListaDoble();
                while(aux2 != null){
                    res = ((aux1.dato * aux2.dato) + carry) % 10;
                    carry = ((aux1.dato * aux2.dato) + carry) / 10;
                    recipiente.insertarInicio(res);
                    aux2 = aux2.anterior;
                }
                if(carry != 0){
                    recipiente.insertarInicio(carry);
                    carry = 0;
                }
                
                for(int i = 0; i < cont; i++){
                    recipiente.insertarFinal(0);
                }

                resultado = resultado.sumar(resultado, recipiente);
                cont++;
                aux1 = aux1.anterior;
                aux2 = b.fin;
            }
        }
        resultado.simplificar(resultado);
        return resultado;   
    }
    
    public ListaDoble dividir(ListaDoble a, ListaDoble b){
        
        int opc = a.comparacion(a,b);

        ListaDoble temporal_a = new ListaDoble();
        ListaDoble temporal_b = new ListaDoble();
        ListaDoble cociente = new ListaDoble();
        ListaDoble divisor = new ListaDoble();
        ListaDoble resultado = new ListaDoble();
        ListaDoble residuo = new ListaDoble();
        ListaDoble finales = new ListaDoble();
        
        if(opc == 2){
            ListaDoble aux = a;
            a = b;
            b = aux;           
        }
        else if(opc == 3){
            finales.insertarInicio(1);
            return finales;
        }
        
        Nodo aux_a = a.inicio;
        Nodo aux_b = b.inicio;
        
            while(aux_b != null){
                temporal_a.insertarFinal(aux_a.dato);
                aux_a = aux_a.siguiente;
                aux_b = aux_b.siguiente;
            }          
            
            int  i = 0;
            int comparador;
            while(i <= 10){
                temporal_b = b;
                divisor.insertarFinal(i);
                cociente = cociente.multiplicar(temporal_b, divisor);
                if((comparador = cociente.comparacion(cociente, temporal_a)) == 1)break;
                cociente = new ListaDoble();
                divisor = new ListaDoble();
                i++;
            }
            
            if(i == 11 || i == 0){
                resultado.insertarFinal(0);
                finales.insertarFinal(0);
            }else{
                resultado.insertarFinal(i-1);
                finales.insertarFinal(i-1);
            }
           
           residuo = residuo.multiplicar(resultado, temporal_b);
           
           temporal_a = temporal_a.restar(temporal_a, residuo);

           
           residuo = new ListaDoble();
           divisor = new ListaDoble();
           resultado = new ListaDoble();
           
           
           while(aux_a != null){
               temporal_a.insertarFinal(aux_a.dato);
               while(temporal_a.inicio.dato == 0 && temporal_a.inicio.siguiente != null){
                   temporal_a.inicio = temporal_a.inicio.siguiente;
               }

               int bandera;
               int  j = 0;
               while(j <= 10){
                   temporal_b = b;
                   divisor.insertarFinal(j);
                   cociente = cociente.multiplicar(temporal_b, divisor);
                   bandera = cociente.comparacion(cociente, temporal_a);
                   if(bandera == 1) break;
                   
                   cociente = new ListaDoble();
                   divisor = new ListaDoble();
                   j++;
               }
               
                if(j == 11 || j == 0){
                    resultado.insertarFinal(0);
                    finales.insertarFinal(0);
                }else{
                    resultado.insertarFinal(j-1);
                    finales.insertarFinal(j-1);
                }
               
               residuo = residuo.multiplicar(resultado, temporal_b);
               temporal_a = temporal_a.restar(temporal_a, residuo);
               
               residuo = new ListaDoble();
               divisor = new ListaDoble();
               resultado = new ListaDoble();
               
               aux_a = aux_a.siguiente;
           }
        
        finales.simplificar(finales);
        return finales;
    }
    
    
    public ListaDoble residuo(ListaDoble a, ListaDoble b){
        
        int opc = a.comparacion(a,b);

        ListaDoble temporal_a = new ListaDoble();
        ListaDoble temporal_b = new ListaDoble();
        ListaDoble cociente = new ListaDoble();
        ListaDoble divisor = new ListaDoble();
        ListaDoble resultado = new ListaDoble();
        ListaDoble residuo = new ListaDoble();
        ListaDoble finales = new ListaDoble();
        
        if(opc == 2){
            ListaDoble aux = a;
            a = b;
            b = aux;           
        }
        else if(opc == 3){
            residuo.insertarInicio(0);
            return residuo;
        }
        
        Nodo aux_a = a.inicio;
        Nodo aux_b = b.inicio;
        
            while(aux_b != null){
                temporal_a.insertarFinal(aux_a.dato);
                aux_a = aux_a.siguiente;
                aux_b = aux_b.siguiente;
            }          
            
            int  i = 0;
            int comparador;
            while(i <= 10){
                temporal_b = b;
                divisor.insertarFinal(i);
                cociente = cociente.multiplicar(temporal_b, divisor);
                if((comparador = cociente.comparacion(cociente, temporal_a)) == 1)break;
                cociente = new ListaDoble();
                divisor = new ListaDoble();
                i++;
            }
            
            if(i == 11 || i == 0){
                resultado.insertarFinal(0);
                finales.insertarFinal(0);
            }else{
                resultado.insertarFinal(i-1);
                finales.insertarFinal(i-1);
            }
           
           residuo = residuo.multiplicar(resultado, temporal_b);
           
           temporal_a = temporal_a.restar(temporal_a, residuo);

           
           residuo = new ListaDoble();
           divisor = new ListaDoble();
           resultado = new ListaDoble();
           
           
           while(aux_a != null){
               temporal_a.insertarFinal(aux_a.dato);
               while(temporal_a.inicio.dato == 0 && temporal_a.inicio.siguiente != null){
                   temporal_a.inicio = temporal_a.inicio.siguiente;
               }

               int bandera;
               int  j = 0;
               while(j <= 10){
                   temporal_b = b;
                   divisor.insertarFinal(j);
                   cociente = cociente.multiplicar(temporal_b, divisor);
                   bandera = cociente.comparacion(cociente, temporal_a);
                   if(bandera == 1) break;
                   
                   cociente = new ListaDoble();
                   divisor = new ListaDoble();
                   j++;
               }
               
                if(j == 11 || j == 0){
                    resultado.insertarFinal(0);
                    finales.insertarFinal(0);
                }else{
                    resultado.insertarFinal(j-1);
                    finales.insertarFinal(j-1);
                }
               
               residuo = residuo.multiplicar(resultado, temporal_b);
               temporal_a = temporal_a.restar(temporal_a, residuo);
               
               residuo = new ListaDoble();
               divisor = new ListaDoble();
               resultado = new ListaDoble();
               
               aux_a = aux_a.siguiente;
           }
        temporal_a.simplificar(temporal_a);
        return temporal_a;
    }
    
       
    
    public void recorrer(){
        Nodo aux = inicio;
        while(aux != null){
            System.out.print(String.format("%d", aux.dato));
            aux = aux.siguiente;
        }
    }
    
    public int tam(ListaDoble a){
        Nodo aux = a.inicio;
        int cont = 1;
        while(aux != null){
            aux = aux.siguiente;
            cont++;
        }
        return cont;
        
    }
    
    public void simplificar(ListaDoble a){
        while(a.inicio.dato == 0 && a.inicio.siguiente != null){
            a.inicio = a.inicio.siguiente;
        }
    }
       
}
