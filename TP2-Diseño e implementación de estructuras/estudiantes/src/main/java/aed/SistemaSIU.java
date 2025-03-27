package aed;

import aed.Clases.*;

import java.util.ArrayList;

/*
 * InvRep: Los tries no pueden ser nulos.
 * Que para todos los alumnos de SistemaSIU.est tienen guardado la cantidad de materias
 * en las que se encuentra inscripto.
 *  
*/


public class SistemaSIU {
    // Completar atributos privados
    private Trie<Carrera> carreras;
    private Trie<Integer> est;

    enum CargoDocente {
        AY2,
        AY1,
        JTP,
        PROF
    }

    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias) {
        this.carreras = new Trie<>();
        this.est = new Trie<Integer>();
        this.insertarEstudiantes(libretasUniversitarias);
        for (InfoMateria materia : infoMaterias) {
            Materia nuevaMateria = new Materia();
            ParCarreraMateria[] pares = materia.getParesCarreraMateria();
            for (ParCarreraMateria par : pares) {
                String carrera = par.getCarrera();
                String nomMateriaCarrera = par.getNombreMateria();
                Carrera carreraAux = carreras.obtener(carrera);
                // accedo a las materias de carrera si es que
                // existen
                if (carreraAux != null) {// carreras y objeto Materia existen -> inserto la materia con
                                         // valor=nuevaMateria
                    nuevaMateria.guardarPunteroDeCarrera(nomMateriaCarrera, carreraAux.adquirirMaterias());
                    carreraAux.asignarMaterias(nomMateriaCarrera, nuevaMateria);
                } else {// no existe la carrera y no existe un objeto Materia para esta materia-> la
                        // creo y guardo la materia con el valor=nuevaMateria
                    carreraAux = new Carrera(carrera);
                    carreraAux.asignarMaterias(nomMateriaCarrera, nuevaMateria); // guardo la referencai
                    Trie<Materia> pointer = carreraAux.adquirirMaterias();// inserto la carrera y
                                                                     // al Trie de sus
                                                                     // materias
                    nuevaMateria.guardarPunteroDeCarrera(nomMateriaCarrera, pointer);// guardo el nombre de la materia en esta
                                                                                    // carrera y donde esta guardado
                    pointer.insertar(nomMateriaCarrera, nuevaMateria);// inserto la materia y guardo el valor
                    carreras.insertar(carrera,carreraAux);
                }

            }
        }
    }

    private void insertarEstudiantes(String[] libretasUniversitarias) {  // O(|libretasUniversitarias|)  
        for (String estudiante : libretasUniversitarias) {   // O(|libretasUniversitarias|)  
             est.insertar(estudiante, 0);   // O(1) porque los estudiantes son identificados con un string 
                                                  // (su libreta universitaria, o LU) de longitud acotada.
        }
    }

    public void inscribir(String estudiante, String carrera, String materia) { // O(|carrera|) + O(|materia|) = O(|carrera| + |materia|)  
        int cantMaterias = est.obtener(estudiante);  // O(1) porque los estudiantes son identificados con un string de long acotada. 
        cantMaterias++;    // O(1)
        int cant = est.insertar(estudiante, cantMaterias);  // O(1)
        Carrera carreraAux = carreras.obtener(carrera);
        Trie<Materia> materiasCarrera = carreraAux.adquirirMaterias();  // O(|carrera|)
        if (materiasCarrera != null) {     // O(1)
            Materia mat = materiasCarrera.obtener(materia); // O(|materia|)
            if (mat != null) {  // O(1)
                mat.inscribirEstudiantes(cant, estudiante);  // O(1)
            }
        }
    }

    public void agregarDocente(CargoDocente cargo, String carrera, String materia) {// O(|carrera|+|materia|)
        int car = cargo.ordinal();//O(1)
        // obtine la materias de la carrera + obtiene el Objeto Materia de la materia + mas verifica si exede hayCupo 
        // = O(|carrera|)+O(|materia|)+O(1) = O(|carrera|+|materia|)
        carreras.obtener(carrera).adquirirMaterias().obtener(materia).agregarAlProfesorado(car);
    }

    public int[] plantelDocente(String materia, String carrera) {// O(|carrera|+|materia|)
        // obtine la materias de la carrera + obtiene el Objeto Materia de la materia + acceder al plantel docente
        // = O(|carrera|)+O(|materia|)+O(1) = O(|carrera|+|materia|)
        Carrera carreraAux = this.carreras.obtener(carrera);
        return carreraAux.adquirirMaterias().obtener(materia).obtenerProfesores();
    }

    public void cerrarMateria(String materia, String carrera) {
        Carrera carreraAux = this.carreras.obtener(carrera);// O(|carrera|)
        ArrayList<String> alumnado = carreraAux.adquirirMaterias().obtener(materia).listaDeAlumnos();  // O(|materia|) , ya que carrera y materia no son acotados
        for (String alumno : alumnado) {   // O(|alumnado|) = O(|Em|) , donde alumnado es cantidad de alumnos inscriptos en la materia.                  
                                         //la cantidad de alumnos esta acotada por el hayCupo.
            est.insertar(alumno, est.obtener(alumno) - 1); // O(1) porque alumno es un string (LU) acotado, entonces el insertar y obtener son O(1)
        }
        
        carreraAux.adquirirMaterias().obtener(materia).borrarMaterias();// O(|carrera| + |materia|) , ya que carrera y materia no son acotados
       /*  La complejidad final nos quedaria: 
            O(|carrera| + |materia|) + O(|carrera| + |materia|) +  O(|Em|) + O(sum(|n|) )  =
            = O(|carrera| + |materia|) + O(sum(|n|) + O(|Em|) )
            
       */

    }

    
    public int inscriptos(String materia, String carrera) {// O(|carrera|+|materia|)
        // obtine la materias de la carrera + obtiene el Objeto Materia de la materia + acceder a los alumnos inscriptos
        // = O(|carrera|)+O(|materia|)+O(1) = O(|carrera|+|materia|)
        Carrera carreraAux = this.carreras.obtener(carrera);
        return carreraAux.adquirirMaterias().obtener(materia).cantidadAlumnos();
    }

    public boolean excedehayCupo(String materia, String carrera) {// O(|carrera|+|materia|)
        // obtine la materias de la carrera + obtiene el Objeto Materia de la materia +  verificar si exede hayCupo 
        // = O(|carrera|)+O(|materia|)+O(1) = O(|carrera|+|materia|)
        Carrera carreraAux = this.carreras.obtener(carrera);
        return carreraAux.adquirirMaterias().obtener(materia).hayCupo();
    }

    public String[] carreras() {// O(sum(|materaCarrera|))
        return carreras.listaDeClaves();// O(sum(|materaCarrera|))
    }

    public String[] materias(String carrera) {// O(|clave| + sum(|materiaCarrera|))
        Carrera carreraAux = this.carreras.obtener(carrera);
        Trie<Materia> materias = carreraAux.adquirirMaterias();// O(|carrera|), accede las materias de la carrera
        return materias.listaDeClaves();// O(sum(|materaCarrera|))
    }

    public int materiasInscriptas(String estudiante) {// O(1)
        return est.obtener(estudiante); // O(1)
    }

}
