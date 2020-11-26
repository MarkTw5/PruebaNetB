import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    
    
    public static void main(String[] args) 
    {
        Scanner leer = new Scanner(System.in);
        
        //Profesores
        for (int i = 0; i < 3; i++)
        {
            int id = Integer.parseInt(leer.nextLine());
            //leer.nextLine();
            String nombre = leer.nextLine();
            String correo = leer.nextLine();
            int edad = Integer.parseInt(leer.nextLine());
            
            Profesor pr = new Profesor(id, nombre, correo, edad);
            pr.crear();
        }
 
        //Estudiantes
        for (int i = 0; i < 5; i++)
        {
            int id = Integer.parseInt(leer.nextLine());
            //leer.nextLine();
            String nombre = leer.nextLine();
            String celular = leer.nextLine();
            int edad = Integer.parseInt(leer.nextLine());
            
            Estudiante es = new Estudiante(id, nombre, celular, edad);
            es.crear();
        }
        
        //Cursos
        for (int i = 0; i < 4; i++)
        {
            int id = Integer.parseInt(leer.nextLine());
            //leer.nextLine();
            String nombre = leer.nextLine();
            int capacidad = Integer.parseInt(leer.nextLine());
            
            Curso cu = new Curso(id, nombre, capacidad);
            cu.crear();
        }
        
        //Operación 1 Asignar Docente a curso
        //Operación 2 Asignar Estudiante a curso
        for (int i = 0; i < 16; i++)
        {
            int operacion = Integer.parseInt(leer.nextLine());
            String op = leer.nextLine();
            String [] ids = op.split(" - ");
            
            if (operacion == 1)
            {
                int idProfesor = Integer.parseInt(ids[0]);
                int idCurso = Integer.parseInt(ids[1]);
                
                Curso cu = new Curso();
                cu.setId(idCurso);
                cu.setIdProfe(idProfesor);
                cu.agregarCurso(idCurso, idProfesor);
            }
            else if (operacion == 2)
            {
                int idEstudiante = Integer.parseInt(ids[0]);
                int idCurso = Integer.parseInt(ids[1]);

                Estudiante es = new Estudiante();
                es.setId(idEstudiante);
                es.setIdCurso(idCurso);
                es.agregarCurso(idEstudiante, idCurso);                
             }
        }
        
        //Respuestas al Rector
        
        //1. Listar Cursos del Profesor
        int idProfesor = Integer.parseInt(leer.nextLine());
        ArrayList<Curso> cursos = Curso.extraerRegistrosByProfesor(idProfesor);
        
        for (Curso curso : cursos)
        {
            System.out.println("Curso: "+curso.getId()+" - "+curso.getNombre()+" - "+curso.getCapacidad());
        }
        
        //2. Listar Estudiantes del Profesor
        idProfesor = Integer.parseInt(leer.nextLine());
        ArrayList<Estudiante> est = Estudiante.extraerRegistrosByProfesor(idProfesor);
        
        for (Estudiante estudiante : est)
        {
            System.out.println("Estudiante: "+estudiante.getId()+" - "+estudiante.getNombre()+" - "+estudiante.getEdad());
        }
        
        //3. Listar Estudiantes del Curso
        int idCurso = Integer.parseInt(leer.nextLine());
        est = Estudiante.extraerEstudianteByCurso(idCurso);
        
        for (Estudiante estudiante : est)
        {
            System.out.println("Estudiante: "+estudiante.getId()+" - "+estudiante.getNombre()+" - "+estudiante.getEdad());
        }
    }
}