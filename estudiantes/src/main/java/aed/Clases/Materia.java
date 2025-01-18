package aed.Clases;

import java.util.ArrayList;


//Invariante de representacion:
/*
pred InvRep(profesores: Array<Int>)
    {|profesores| = 4}
    No hay alumno repetidos en inscriptos.
    nombresMaterias y nodoMateria tienen la misma longitud.
    No hay materias repetida en nodoMateria y nombresMaterias.
 */

public class Materia {
    private int[] profesores;
    private ArrayList<Trie<Materia>> nodoMateria;
    private ArrayList<String> nombresMaterias;
    private ArrayList<String> inscriptos;
    int alumnos;

    public Materia() {
        this.profesores = new int[4]; // O(1)
        for (int i = 0; i < 4; i++) {  // O(1)
            this.profesores[i] = 0; // O(1)
        }
        this.nombresMaterias = new ArrayList<String>(); // O(1)
        this.nodoMateria = new ArrayList<Trie<Materia>>(); // O(1)
        inscriptos = new ArrayList<>();  // O(1)
        this.alumnos = 0;  // O(1)
    }

    public ArrayList<String> listaDeAlumnos() {   // O(1)
        return inscriptos; // O(1)
    }

    public void inscribirEstudiantes(int valor, String clave) {  // O(1)
        inscriptos.add(clave);   // O(1)
        this.alumnos++;    // O(1)
    }

    public Boolean hayCupo() {   // O(1)
        int hayCupoMin = Math.min(profesores[0] * 250, profesores[1] * 100);   // O(1)
        hayCupoMin = Math.min(hayCupoMin, profesores[2] * 20);    // O(1)
        hayCupoMin = Math.min(hayCupoMin, profesores[3] * 30);   // O(1)
        return !(alumnos <= hayCupoMin);   // O(1)
    }

    public void guardarPunteroDeCarrera(String nombre, Trie<Materia> pointer) {  // O(1)
        this.nodoMateria.add(pointer);// O(1)
        this.nombresMaterias.add(nombre);// O(1) 
    }

    public int[] obtenerProfesores() {
        return this.profesores; // O(1)
    }

    public int cantidadAlumnos() {
        return alumnos;   // O(1)
    }

    public void agregarAlProfesorado(int cargo) {
        this.profesores[3 - cargo] = this.profesores[3 - cargo] + 1;  // O(1)
    }

    public void borrarMaterias() {
        for (int i=0; i<this.nodoMateria.size(); i++) { // O(sum(|n|) , es la sumatoria de las longitudes de los nombres distintos de la materia (que no es acotado)
            Trie<Materia> carrTrie = this.nodoMateria.get(i); // O(1)
            String nombre = this.nombresMaterias.get(i);  // O(1)
            carrTrie.eliminar(nombre); // O(|n|) , n = nombre de la materia
        }
    }

}