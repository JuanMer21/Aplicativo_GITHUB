package Model;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import view.form;

public class fichaDAO {

    PreparedStatement ps;
    ResultSet rs;
    Connection connection;
    connectionn conectar = connectionn.getInstance();

    public int Agregarfi(ficha fic) {
        int r = 0;
        String sql = "INSERT INTO ficha(numeroficha,nombreficha,sede,id_aprendiz) VALUES (?,?,?,?)";

        try {

            connection = conectar.conectar();
            ps = connection.prepareStatement(sql);

            ps.setString(1, fic.getNumFicha());
            ps.setString(2, fic.getNomFich());
            ps.setString(3, fic.getNomSed());

            ps.setInt(4, fic.getIdapre());

            r = ps.executeUpdate();

        } catch (SQLException e) {
        }
        return r;
    }

    public List Listarficha() {
        List<ficha> datos = new ArrayList<>();
        try {
            connection = conectar.conectar();
            ps = connection.prepareStatement("SELECT*FROM ficha");
            rs = ps.executeQuery();
            while (rs.next()) {

                ficha fichh = new ficha();

                fichh.setIdFich(rs.getInt(1));
                fichh.setNumFicha(rs.getString(2));
                fichh.setNomFich(rs.getString(3));
                fichh.setNomSed(rs.getString(4));
                fichh.setIdapre(rs.getInt(5));

                datos.add(fichh);
            }
        } catch (SQLException e) {
        }
        return datos;
    }

    public List<Object[]> Listartodo() {
        List<Object[]> datosCompletos = new ArrayList<>();

        try {
            connection = conectar.conectar();
            ps = connection.prepareStatement("SELECT ficha.numeroficha, ficha.nombreficha, aprendiz.name, ficha.sede, ficha.id_aprendiz FROM ficha  INNER JOIN aprendiz  ON ficha.id_aprendiz = aprendiz.id");
            rs = ps.executeQuery();

            while (rs.next()) {
                ficha fi = new ficha();
                Apprentice ap = new Apprentice();

                fi.setNumFicha(rs.getString(1));
                fi.setNomFich(rs.getString(2));
                ap.setNombre(rs.getString(3));
                fi.setNomSed(rs.getString(4));
                fi.setIdapre(rs.getInt(5));

                datosCompletos.add(new Object[]{fi, ap});
            }
        } catch (SQLException e) {
        }

        return datosCompletos;
    }
    public int ActualizarFi (ficha fic){
     
      int r = 0;
    String sql = "UPDATE ficha SET numeroficha=?,nombreficha=?,sede=?,id_aprendiz=? WHERE id_ficha=?";
    
      try {
        connection = conectar.conectar();
        ps = connection.prepareStatement(sql);
        
        ps.setString(1,fic.getNumFicha());
        ps.setString(2,fic.getNomFich());
        ps.setString(3,fic.getNomSed());
        ps.setInt(4, fic.getIdapre());
        ps.setInt(5, fic.getIdFich());
        
        
        r= ps.executeUpdate();
          if (r==1) {
              return 1;
          } else {
              return 0;
          }
        
        
    }
         catch (SQLException e) {
        }
        return r;
        
    }
 public int EliminarFi (int ida) {
    int r = 0;
    String sql = "DELETE FROM ficha WHERE id_ficha=" + ida;
    try {
        connection = conectar.conectar();
        ps = connection.prepareStatement(sql);
        r = ps.executeUpdate();
        
    } catch (SQLException e) {
    }
    return r;
} 
}
