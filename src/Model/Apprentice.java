
package Model;

import java.util.Date;

public class Apprentice {
    
int id;
String tipDoc;
String numDoc;
String nombre;
Date fechanacim;
String genero;
String ciudad;

public Apprentice(){
    
}

//    public String getNomFich() {
//        return nomFich;
//    }
//
//    public void setNomFich(String nomFich) {
//        this.nomFich = nomFich;
//    }
//
//    public String getNumFicha() {
//        return numFicha;
//    }
//
//    public void setNumFicha(String numFicha) {
//        this.numFicha = numFicha;
//    }
//
//    public String getIdFich() {
//        return idFich;
//    }
//
//    public void setIdFich(String idFich) {
//        this.idFich = idFich;
//    }
//    
//    public String getNomSed() {
//        return nomSed;
//    }
//
//    public void setNomSed(String nomSed) {
//        this.nomSed = nomSed;
//    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipDoc() {
        return tipDoc;
    }

    public void setTipDoc(String tipDoc) {
        this.tipDoc = tipDoc;
    }

    public String getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechanacim() {
        return fechanacim;
    }

    public void setFechanacim(Date fechanacim) {
        this.fechanacim = fechanacim;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

}
