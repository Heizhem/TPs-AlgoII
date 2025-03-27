package aed.Clases;

import java.util.ArrayList;

public class Trie<T> {
    
    /* 
    InvRep: La raiz del nodo debe existir
            Se cumple el invariante del nodo. 
            Luego, en un nodo hoja (no tiene mas hijos), su definicion no puede ser nula. 
            Tampoco hay ciclos, es decir, 2 claves distintas no llevan al mismo nodo.
            Una rama no puede ser inutil, es decir, que una rama no puede terminar en una hoja inutil (con definicion=null).
            * raiz
            * / | \
            * c  d (otras letras)
            * / \
            *  a  (otras letras)
            * / \
            * s  r   a partir de "r" es una rama inutil, ya que k es una hoja inutil(no tiene mas nada).
            * /  \
            * a   k
            *      \
                    null

    */ 
    private Nodo raiz;

    private class Nodo { 
        private ArrayList<Trie<T>> siguientes;
        private T definicion;
        /*
         * InvRep (n: Nodo<T>){
         *     |n.siguientes| == 256
         *     Si la definicion del nodo no es nula significa el fin de una clave
         * }
         */
    
    
        public Nodo() {// O(1)
            this.siguientes = new ArrayList<>(256);// O(1)
            this.setearLista();// O(1)
            this.definicion = null;// O(1)
        }
    
        private void setearLista() {// O(1)
            for (int i = 0; i < 256; i++) {// O(256) = O(1) 
                this.siguientes.add(null);
            }
        }
    
        public void setDef(T valor) {// O(1)
            definicion = valor;
        }
    
        public T getDef() {// O(1)
            return this.definicion;
        }
    
        public ArrayList<Trie<T>> getHijos() {//O(1)
            return this.siguientes;
        }
    }
    
    public Trie() { // O(1)
        this.raiz = null;  // O(1)
    }

    private Nodo obtenerRaiz() {  // O(1)
        return this.raiz;    // O(1)
    }

    public T insertar(String clave, T valor) {  // O(|clave|)
        if (this.raiz == null) {   // O(1)
            this.raiz = new Nodo();
        }

        ArrayList<Trie<T>> lista = this.raiz.getHijos();  // O(1)
        Nodo actual = this.raiz;   // O(1)

        for (int i = 0; i < clave.length(); i++) { // O(|clave|)
            char charValue = clave.charAt(i);   // O(1)
            int ascii = (int) charValue;
            if (lista.get(ascii) == null) {   // O(1)
                Trie<T> nuevoTrie = new Trie<>();
                nuevoTrie.raiz = new Nodo(); 
                lista.set(ascii, nuevoTrie);  // O(1)

            }
            actual = lista.get(ascii).raiz;   // O(1)
            lista = actual.getHijos();     // O(1)

        }
        actual.setDef(valor);  // O(1)

        return actual.getDef();   // O(1)
    }

    public T obtener(String clave) {   // O(|Clave|)
        if (this.raiz == null) {
            return null;
        }
        Nodo actual = this.raiz;   // O(1) 

        for (int i = 0; i < clave.length(); i++) {  // O(|clave|)
            char charValue = clave.charAt(i);   // O(1)
            int ascii =  charValue;    // O(1)
            ArrayList<Trie<T>> pos_letra = actual.getHijos(); // O(1)
            if (ascii >= pos_letra.size() || pos_letra.get(ascii) == null) {   // O(1)  
                return null;    // O(1)
            }
            actual = pos_letra.get(ascii).raiz;   // O(1)
        }
        return actual.getDef(); 
    }

    public String[] listaDeClaves() {  // O(sum|claves|) es la suma de la longitud de cada clave                                       
        ArrayList<String> claves = this.obtenerClaves(); // O(sum|claves|)                                                   
        String[] respuesta = new String[claves.size()];  // claves.size() = O(1)                                              
        for (int i = 0; i < claves.size(); i++) { // O(1)
            respuesta[i] = claves.get(i); // O(1)
        }
        return respuesta; // O(1)
    }

    // obtiene las claves de manera privada, es una funcion auxiliar de listaDeClaves
    private ArrayList<String> obtenerClaves() {   // O(sum|claves|) es la suma de la longitud de cada clave                           
        ArrayList<String> claves = new ArrayList<>(); // O(1)
        obtenerClavesRecursivo(raiz, new StringBuffer(), claves);        // O(sum|claves|) es la suma de la longitud de cada clave                
        return claves; // O(1)
    }

    private void obtenerClavesRecursivo(Nodo nodo, StringBuffer prefijo, ArrayList<String> claves) {     // O(sum|claves|) es la suma de la longitud de cada clave                           
                                                                                                                                                                            
        if (nodo == null) {      // O(1)
            return;   
        } // O(1)
        if (nodo.getDef() != null) {    // O(1)
            claves.add(prefijo.toString());// O(|prefijo|)
        } 
        for (int i = 0; i < 256; i++) { // O(1) es acotado  *  // O(sum|claves|) es la suma de la longitud de cada clave   
            if (nodo.getHijos().get(i) != null) { // O(1)
                Trie<T> n = nodo.getHijos().get(i); // O(1)
                char letra = Character.toChars(i)[0];
                prefijo.append(letra);
                obtenerClavesRecursivo(n.raiz,prefijo , claves);  // O(sum|claves|) es la suma de la longitud de cada clave                    
                prefijo.deleteCharAt(prefijo.length()-1);
            }
        }
    }

    public void eliminar(String palabra) { // O(|palabra|)
        eliminarRecursivo(raiz, palabra, 0); // O(|palabra|)
    }

    private void eliminarRecursivo(Nodo nodo, String palabra, int index) { // O(|palabra|)
        if (nodo == null) {   // O(1)
            return;
        }

        if (index == palabra.length()) {   // O(1)
            if (nodo.getDef() != null) {   // O(1)
                nodo.setDef(null);   // O(1)
            } // O(1)
            return; // O(1)
        }

        int posicion = (int) (palabra.charAt(index)); // O(1)
        eliminarRecursivo((nodo.getHijos().get(posicion)).obtenerRaiz(), palabra, index + 1); // O(|m|)

        // Después de eliminar el nodo hijo, revisa si se debe eliminar este nodo
        if (nodo.getHijos().get(posicion) != null   // O(1)                                              
                && nodo.getHijos().get(posicion) == null) {// O(1)
            nodo.getHijos().set(posicion, null); // O(1)
        }
    }

}