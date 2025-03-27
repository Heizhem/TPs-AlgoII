# Diseño e implementacion de estructuras: Sistema de Inscripción a materias

## Descripción

Este proyecto implementa un sistema simplificado de inscripciones universitarias basado en el SIU Guaraní. Permite definir carreras de grado con sus respectivas materias y planteles docentes, así como gestionar la inscripción de estudiantes y verificar la capacidad de las materias en función de los docentes disponibles.

## Funcionalidades

Gestión de carreras y materias: Permite definir carreras y materias con múltiples nombres según la carrera.

Inscripción de estudiantes: Registro de estudiantes en materias específicas.

Gestión de docentes: Agregado de docentes a materias con reglas de cupo.

Control de cupo: Verificación de si una materia excede su capacidad permitida.

Cierre de materias: Permite cerrar materias cuando no hay suficientes docentes.

Consultas: Listado de carreras, materias e inscripciones por estudiante.

## Operaciones Implementadas

nuevoSistema: Inicializa el sistema con las materias y libretas universitarias.

inscribir: Inscribe a un estudiante en una materia de una carrera.

inscriptos: Retorna la cantidad de estudiantes inscritos en una materia.

agregarDocente: Agrega un docente al plantel de una materia y sus equivalentes.

plantelDocente: Devuelve la cantidad de docentes por tipo en una materia.

excedeCupo?: Indica si la cantidad de inscriptos supera el cupo disponible.

carreras: Retorna la lista de carreras disponibles.

materias: Lista las materias de una carrera específica.

materiasInscriptas: Devuelve la cantidad de materias en las que está inscrito un estudiante.

cerrarMateria: Cierra una materia por falta de docentes.

## Complejidades Temporales

Cada operación tiene restricciones de complejidad temporal definidas para garantizar un rendimiento óptimo en escenarios de gran escala. La complejidad de cada operación se encuentra detallada en la documentación técnica.
Sean:
C: conjunto de las carreras de grado.
M : conjunto de todas las materias.
E: cantidad total de estudiantes en todas las carreras de grado.
Mc: conjunto de materias de la carrera de grado c.
Nm: conjunto de nombres de la materia m.
Em: cantidad de estudiantes inscriptos en la materia m.

nuevoSistema: $O(\sum_{c \in C}^{} {|c|*|M_c|}+\sum_{m\in M}^{} {\sum_{n \in N_m} { |n|}}+ E)$

inscribir: $ O(|c|+|m|)$

inscriptos: $O(|c|+|m|)$

agregarDocente: $O(|c|+|m|)$

plantelDocente: $O(|c|+|m|)$

excedeCupo?: $O(|c|+|m|)$

carreras: $O(\sum_{c \in C}^{} {|c|})$

materias: $O(|c|+ \sum_{m \in M_c}^{} {|m_c|})$

materiasInscriptas: $O(1)$

cerrarMateria: $O(|c| + |m| + \sum_{n \in N_m}^{} {|n|}+E_m)$

## Propuesta de Estructura y TAD

### TADs
- Trie<Nodo<T>>: Trie de nodos que acepta tipo genericos
- Materia<int[4],ArrayList<Trie<Materia>>,ArrayList<String>,ArrayList<String>,int>: 
    - Lista fija de 4 elementos con la cantidad de profesores/jtp/ayudantes de primera/ayudantes de segunda
    - Lista que guarda un trie que guarda un objeto tipo Materias
    - Lista que guarda los nombres de las materias
    - Lista que guarda cantidad de inscriptos por materia con el mismo orden que estan guardadas en la lista anterior
    - cantidad de alumnos totales
- Carrera<Trie<Materia>>: una estructura que usa un trie pra gurdas objetetos tipo Materia en sus nodos.
### Estructura
Para el sistema de inscripciones se utilizo la siguiente estrutura
- SistemaSIU<Trie<Carrera>,Trie<Integrer>>:
    - el pirmer trie guarda objetos de tipo Carrera
    - el segundo trie guarda la libreta del estudiante.
### Aclaraciones

Se propuso esta solución teniendo en cuenta que:

- Los estudiantes son identicados con un string (su libreta universitaria, o LU) de longitud acotada.
- Las materias y carreras son identicadas con un string (su nombre) de longitud no acotada.
- El orden lexicograco sigue el orden ASCII, donde las mayúsculas preceden a las minúsculas y los números a las letras.

Por lo que el orden de complejidad O para crear, insertar, obtener y consultar eran acordes a las complejidades pedidas.