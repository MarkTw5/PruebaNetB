
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class Curso 
{
    int id;
    String nombre;
    int capacidad;
    Profesor Profesor;
    ArrayList <Estudiante> Estudiantes;
    int idProfe;

    public Curso() 
    {
    
    }

    public Curso(int id, String nombre, int capacidad) 
    {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    public Curso(String nombre, int capacidad) 
    {
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    public Curso(int id, String nombre, int capacidad, int idProfe) 
    {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.idProfe = idProfe;
    }
    
    

    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public String getNombre() 
    {
        return nombre;
    }

    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    public int getCapacidad() 
    {
        return capacidad;
    }

    public void setCapacidad(int capacidad) 
    {
        this.capacidad = capacidad;
    }

    public Profesor getProfesor() 
    {
        return Profesor;
    }

    public void setProfesor(Profesor Profesor) 
    {
        this.Profesor = Profesor;
    }

    public ArrayList getEstudiantes() 
    {
        return Estudiantes;
    }

    public void setEstudiantes(ArrayList Estudiantes) 
    {
        this.Estudiantes = Estudiantes;
    }

    public int getIdProfe() 
    {
        return idProfe;
    }

    public void setIdProfe(int idProfe) 
    {
        this.idProfe = idProfe;
    }
    
    public void crear()
    {
        Conexion con = new Conexion();
        con.conectar();
        String sql = "insert into cursos (nombre, capacidad) values('"+getNombre()+"','"+getCapacidad()+"');";
        
        try
        {
            
            Statement st = con.getConexion().createStatement();
            st.executeUpdate(sql);
        }
        catch (Exception e)
        {
                
        }
        con.cerrarConexion();
    }
       
    public static ArrayList <Curso> leer()
    {
        ArrayList cur = new ArrayList();
        Conexion con = new Conexion();
        con.conectar();
        String sql = "select * from cursos;";
        
        try
        {
            Statement st = con.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next())
            {
                cur.add(new Curso(rs.getString("nombre"), rs.getInt("capacidad")));
            }
            
        }
        catch (Exception e)
        {
                System.out.println("Error: "+ e.getMessage());
        }
        
        con.cerrarConexion();
        return cur;
    }
        
    public void Actualizar()
    {
        Conexion con = new Conexion();
        con.conectar();
        
        try
        {
            String sql = "update cursos set nombre='"+nombre+"', capacidad ='"+capacidad+"' where id = "+getId()+";";
            Statement st = con.getConexion().createStatement();
            st.executeUpdate(sql);
            con.cerrarConexion();
        }
        catch (Exception e)
        {
                
        }       
    }
        
    public void borrar()
    {
        String sql = "delete from cursos where id="+getId()+";";
        
        try
        {
            Conexion con = new Conexion();
            con.conectar();
            Statement st = con.getConexion().createStatement();
            st.executeUpdate(sql);
            con.cerrarConexion();
        }
        catch (Exception e)
        {
                
        }
    }
    
    public void agregarCurso(int id, int idProfe)
    {
        Conexion con = new Conexion();
        con.conectar();
        String sql = "update cursos set id_profesores="+getIdProfe()+" where id ="+getId()+";";
        
        try
        {
            Statement st = con.getConexion().createStatement();
            st.executeUpdate(sql);
        }
        catch (Exception e)
        {
                
        }
        con.cerrarConexion();
    }
    
    public static ArrayList<Curso> extraerRegistrosByProfesor(int idProfe)
    {
        ArrayList cur = new ArrayList();
        Conexion con = new Conexion();
        con.conectar();
        String sql = "select * from cursos where id_profesores= "+idProfe+";";
        
        try
        {
            Statement st = con.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next())
            {
                cur.add(new Curso(rs.getInt("id"), rs.getString("nombre"), rs.getInt("capacidad")));
            }
            
        }
        catch (Exception e)
        {
                System.out.println("Error: "+ e.getMessage());
        }
        
        con.cerrarConexion();
        return cur;
    }
    
    public Curso lee(String nombre)
    {
            Conexion con = new Conexion();
            con.conectar();
            Curso cu = new Curso();
            String sql = "select * from cursos where nombre = '"+nombre+"';";
            try
            {
                Statement st = con.getConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);
                cu = new Curso(rs.getInt("id"), rs.getString("nombre"), rs.getInt("capacidad"), rs.getInt("id_profesores"));
            }
            catch (Exception e)
            {
                    System.out.println("Error: "+ e.getMessage());
            }
            con.cerrarConexion();
            return cu;
    }
    
    //"select distinct(c.nombre) from cursos as c join curso_estudiante as ce on ce.id_curso = c.id join estudiantes as e on e.id = ce.id_estudiante where e.id = "+ide+";";
    
    public static ArrayList<Curso> extraerCursoEstudiante(int ide)
    {
        ArrayList cur = new ArrayList();
        Conexion con = new Conexion();
        con.conectar();
        String sql = "select distinct(c.nombre), c.id, c.capacidad from cursos as c join curso_estudiante as ce on ce.id_curso = c.id join estudiantes as e on e.id = ce.id_estudiante where e.id = "+ide+";";
        
        try
        {
            Statement st = con.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next())
            {
                cur.add(new Curso(rs.getInt("id"), rs.getString("nombre"), rs.getInt("capacidad")));
            }
            
        }
        catch (Exception e)
        {
                System.out.println("Error: "+ e.getMessage());
        }
        
        con.cerrarConexion();
        return cur;
    }
}
