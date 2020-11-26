import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Profesor 
{
    int id;
    String nombre;
    String correo;
    int edad;
    ArrayList <Curso> cursos;

    public Profesor() 
    {
    
    }
    
    public Profesor(int id, String nombre, String correo, int edad) 
    {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.edad = edad;
    }

    public Profesor(String nombre, String correo, int edad) 
    {
        this.nombre = nombre;
        this.correo = correo;
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

    public String getCorreo() 
    {
        return correo;
    }

    public void setCorreo(String correo) 
    {
        this.correo = correo;
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
        return cursos;
    }

    public void setCursos(ArrayList cursos) 
    {
        this.cursos = cursos;
    }
    
   public void crear()
   {
        Conexion con = new Conexion();
        con.conectar();
        String sql = "insert into profesores (nombre, correo, edad) values('"+getNombre()+"','"+getCorreo()+"','"+getEdad()+"');";
        
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
   
    public static ArrayList <Profesor> leer()
    {
        ArrayList prof = new ArrayList();
        Conexion con = new Conexion();
        con.conectar();
        String sql = "select * from profesores;";
        
        try
        {
            Statement st = con.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next())
            {
                prof.add(new Profesor(rs.getInt("id"), rs.getString("nombre"), rs.getString("correo"), rs.getInt("edad")));
            }
            
        }
        catch (Exception e)
        {
                System.out.println("Error: "+ e.getMessage());
        }
        
        con.cerrarConexion();
        return prof;
    }
    
    public void Actualizar()
    {
        Conexion con = new Conexion();
        con.conectar();
        
        try
        {
            String sql = "update profesores set nombre='"+nombre+"', correo ='"+correo+"', edad='"+edad+"' where Id = "+getId()+";";
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
        String sql = "delete from profesores where Id="+getId()+";";
        
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
    
    public Profesor lee(int id)
    {
            Conexion con = new Conexion();
            con.conectar();
            Profesor pr = new Profesor();
            String sql = "select * from profesores where id = "+id+";";
            try
            {
                Statement st = con.getConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);
                pr = new Profesor(rs.getInt("id"), rs.getString("nombre"), rs.getString("correo"), rs.getInt("edad"));
            }
            catch (Exception e)
            {
                    System.out.println("Error: "+ e.getMessage());
            }
            con.cerrarConexion();
            return pr;
    }
}