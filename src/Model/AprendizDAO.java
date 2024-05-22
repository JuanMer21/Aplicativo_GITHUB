
package Model;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import view.form;
public class AprendizDAO {
    
PreparedStatement  ps;  
    ResultSet rs;
    Connection connection;
    connectionn conectar=connectionn.getInstance();
    
   public List Listar(){
    List<Apprentice> datos = new ArrayList<>();
    try {
        connection = conectar.conectar();
        ps = connection.prepareStatement("SELECT*FROM aprendiz");
        rs = ps.executeQuery();
        while (rs.next()) {            
            Apprentice ap = new Apprentice();
            ap.setId(rs.getInt(1));
            ap.setTipDoc(rs.getString(2));
            ap.setNumDoc(rs.getString(3));
            ap.setNombre(rs.getString(4));
            ap.setFechanacim(rs.getDate(5));
            ap.setGenero(rs.getString(6));
            ap.setCiudad(rs.getString(7));
            
            datos.add(ap);
                    
                    
                    
        }
    } catch (SQLException e) {
    }
    return datos;
}
public int Agregar(Apprentice apre){ 
    int r=0;
    String sql="INSERT INTO aprendiz(documentType,documentNum,name,date,Gender,city)VALUES(?,?,?,?,?,?)";
    try {
        form fr = new form();
        connection=conectar.conectar();
        ps=connection.prepareStatement(sql);
        ps.setString(1,getTipoDocIndex(apre.getTipDoc()));
    ps.setString(2,apre.getNumDoc());
    ps.setString(3,apre.getNombre());
    java.util.Date fecha = apre.getFechanacim();
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    String formato=sdf.format(fecha);
    ps.setString(4,formato);
    ps.setString(5,apre.getGenero());
    ps.setString(6,apre.getCiudad());
    
  
    r=ps.executeUpdate();
    
    
    } catch (Exception e) {
    }
return r;
}
public int ACtualizar(Apprentice apre){ 
    int r=0;
    String sql="UPDATE aprendiz SET documentType=?,documentNum=?,name=?,date=?,Gender=?,city=? WHERE id=?";
    try {
        connection=conectar.conectar();
        ps=connection.prepareStatement(sql);
    ps.setString(1,getTipoDocIndex(apre.getTipDoc()));
    ps.setString(2,apre.getNumDoc());
    ps.setString(3,apre.getNombre());
    java.util.Date fecha = apre.getFechanacim();
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    String formato=sdf.format(fecha);
    ps.setString(4,formato);
    ps.setString(5,apre.getGenero());
    ps.setString(6,apre.getCiudad());
    ps.setInt(7,apre.getId());
    
    r=ps.executeUpdate();
        if (r==1) {
            return 1;
        }else{
            return 0;
        }
    
    
    } catch (SQLException e) {
    }
return r;
}
public int Eliminar(int ida){
    int r=0;
    String sql="DELETE FROM aprendiz WHERE id="+ida;
    try {
        connection=conectar.conectar();
        ps=connection.prepareStatement(sql);
        r=ps.executeUpdate();
    } catch (SQLException e) {
    }
return r;
}
private String getTipoDocIndex(String tipoDoc){
    return switch(tipoDoc){
 case"Cedula Ciudadania"->"CC"  ;      
case"Cedula Extrangeria"->"CE";
 case "Tarjeta Identidad"->"TI";
 case "Registro Civil"->"RC";   
 case "Pasaporte"->"PS";
 default->"";
     };

}

  
}
