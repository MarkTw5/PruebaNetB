import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Estudiante 
{
    int id;
    String nombre;
    String celular;
    int edad;
    ArrayList <Curso> Cursos;
    int idCurso;
    int idProfe;

    public Estudiante() 
    {
    
    }
    
    public Estudiante(int id, String nombre, String celular, int edad) 
    {
        this.id = id;
        this.nombre = nombre;
        this.celular = celular;
        this.edad = edad;
    }

    public Estudiante(String nombre, String celular, int edad) 
    {
        this.nombre = nombre;
        this.celular = celular;
        this.edad = edad;
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

    public String getCelular() 
    {
        return celular;
    }

    public void setCelular(String celular) 
    {
        this.celular = celular;
    }

    public int getEdad() 
    {
        return edad;
    }

    public void setEdad(int edad) 
    {
        this.edad = edad;
    }

    public ArrayList getCursos() 
    {
        return Cursos;
    }

    public void setCursos(ArrayList Cursos) 
    {
        this.Cursos = Cursos;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getIdProfe() {
        return idProfe;
    }

    public void setIdProfe(int idProfe) {
        this.idProfe = idProfe;
    }
    
    public void crear()
    {
        Conexion con = new Conexion();
        con.conectar();
        String sql = "insert into estudiantes (nombre, celular, edad) values('"+getNombre()+"','"+getCelular()+"','"+getEdad()+"');";
        
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
       
    public static ArrayList <Estudiante> leer()
    {
        ArrayList est = new ArrayList();
        Conexion con = new Conexion();
        con.conectar();
        String sql = "select * from estudiantes;";
        
        try
        {
            Statement st = con.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next())
            {
                est.add(new Estudiante(rs.getInt("id"), rs.getString("nombre"), rs.getString("celular"), rs.getInt("edad")));
            }
            
        }
        catch (Exception e)
        {
                System.out.println("Error: "+ e.getMessage());
        }
        
        con.cerrarConexion();
        return est;
    }
        
    public void Actualizar()
    {
        Conexion con = new Conexion();
        con.conectar();
        
        try
        {
            String sql = "update estudiantes set nombre='"+nombre+"', celular ='"+celular+"', edad='"+edad+"' where id = "+getId()+";";
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
        String sql = "delete from estudiantes where id="+getId()+";";
        
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
    
    public void agregarCurso(int id, int idCurso)
    {
        Conexion con = new Conexion();
        con.conectar();
        String sql = "insert into curso_estudiante (id_curso, id_estudiante) values("+getIdCurso()+","+getId()+");";
        
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
    
    public static ArrayList<Estudiante> extraerRegistrosByProfesor(int idProfe)
    {
        ArrayList est = new ArrayList();
        Conexion con = new Conexion();
        con.conectar();
                
        String sql = "select distinct(e.id), e.nombre, e.celular, e.edad from estudiantes as e join curso_estudiante as ce on e.id=ce.id_estudiante join cursos as c on ce.id_curso=c.id join profesores as p on c.id_profesores = p.id WHERE p.id= "+idProfe+";";
        
        try
        {
            Statement st = con.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next())
            {
                est.add(new Estudiante(rs.getInt("id"), rs.getString("nombre"), rs.getString("celular"), rs.getInt("edad")));
            }
            
        }
        catch (Exception e)
        {
                System.out.println("Error: "+ e.getMessage());
        }
        
        con.cerrarConexion();
        return est;
    }
    
        public static ArrayList<Estudiante> extraerEstudianteByCurso(int idCurso)
    {
        ArrayList est = new ArrayList();
        Conexion con = new Conexion();
        con.conectar();
        String sql = "select distinct(e.id), e.nombre, e.celular, e.edad from estudiantes as e join curso_estudiante as ce on e.id=ce.id_estudiante join cursos as c on ce.id_curso=c.id where c.id= "+idCurso+";";
        
        try
        {
            Statement st = con.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next())
            {
                est.add(new Estudiante(rs.getInt("id"), rs.getString("nombre"), rs.getString("celular"), rs.getInt("edad")));
            }
            
        }
        catch (Exception e)
        {
                System.out.println("Error: "+ e.getMessage());
        }
        
        con.cerrarConexion();
        return est;
    }
        
    public Estudiante lee(int id)
    {
            Conexion con = new Conexion();
            con.conectar();
            Estudiante es = new Estudiante();
            String sql = "select * from estudiantes where id = "+id+";";
            try
            {
                Statement st = con.getConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);
                es = new Estudiante(rs.getInt("id"), rs.getString("nombre"), rs.getString("celular"), rs.getInt("edad"));
            }
            catch (Exception e)
            {
                    System.out.println("Error: "+ e.getMessage());
            }
            con.cerrarConexion();
            return es;
    }
    
}