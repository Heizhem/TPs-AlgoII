package aed.Clases;

public class Carrera {
    /* InvRep
        La misma materia puede exitir en varias carreras
     *  Las claves de "materias" se encuentran ordenadas
        lexicograficamente
     */

    private Trie<Materia> materias; // O(1)

    public Carrera(String nombre) { // O(1)
        //this.nombre = nombre; // O(1)
        this.materias = new Trie<Materia>(); // O(1)
    }

    public Trie<Materia> adquirirMaterias() { // O(1)
        return materias; // O(1)
    }

    public void asignarMaterias(String nombre, Materia materia) { // O(|m|)
        this.materias.insertar(nombre, materia); // O(|m|)
    }

}