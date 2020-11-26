
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;



public class Conexion 
{
    Connection conexion;
    String url = "colegio.db";
    
    public Connection getConexion() 
    {
        return conexion;
    }

    public void setConexion(Connection conexion) 
    {
        this.conexion = conexion;
    }

    public String getUrl() 
    {
        return url;
    }

    public void setUrl(String url) 
    {
        this.url = url;
    }
    
    public Connection conectar()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection("jdbc:sqlite:"+url);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error: "+ e.getMessage());
        }
        return conexion;
    }
    
    public void cerrarConexion()
    {
        try
        {
            conexion.close();
        }
        catch (Exception e)
        {
                    
        }
    }
}
