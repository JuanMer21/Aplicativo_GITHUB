
package Model;
import java.sql.*;
import javax.swing.JOptionPane;
public class connectionn {
private connectionn(){
    


}
private static Connection conectar;
private static connectionn instancia;

public static final String URL = "jdbc:mysql://localhost:33065/mvcaprendiz";
public static final String USERNAME="root";
public static final String PASSWORD="";

public Connection conectar(){
    try {
        conectar=DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return conectar;
    } catch (SQLException e) {
        JOptionPane.showInternalMessageDialog(null,"error"+e);
    }
return  conectar;
}
public void cerrarConexion()throws SQLException{
    try {
        conectar.close();
    } catch (SQLException e) {
    JOptionPane.showInternalMessageDialog(null,"Error"+e);
    conectar.close();
    }
}
//IMPLEMENTACION PATRON SINGLENTON
public static connectionn getInstance(){
    if (instancia==null) {
        instancia=new connectionn();
    }
   return instancia;
    
}

}
