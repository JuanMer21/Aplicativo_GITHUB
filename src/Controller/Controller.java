package Controller;

import Model.Apprentice;
import Model.AprendizDAO;
import Model.ficha;
import Model.fichaDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer.Form;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import view.form;

public class Controller implements ActionListener {

    AprendizDAO dao = new AprendizDAO();
    fichaDAO fao = new fichaDAO();
    ficha fi = new ficha();
    Apprentice ap = new Apprentice();
    form form;

    public Controller(form f) {
        this.form = f;
        this.form.btnList.addActionListener(this);
        this.form.btnSave.addActionListener(this);
        this.form.btnEliminate.addActionListener(this);
        this.form.btnSearch.addActionListener(this);
        this.form.btnUpdate.addActionListener(this);
        this.form.btnExit.addActionListener(this);
        this.form.btnElimFich.addActionListener(this);
        this.form.btnListFicha.addActionListener(this);
        this.form.btnSavefich.addActionListener(this);
        this.form.btnUpdateFich.addActionListener(this);
        this.form.btnSearchFich.addActionListener(this);
        this.form.btnList.addActionListener(this);
        this.form.btnListAprendiz.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == form.btnListAprendiz) {
            limpiarTabla();
            listar(form.tbnAprendiz);
            limpiar();
        }
        if (e.getSource() == form.btnListFicha) {
            limpiarTablaFI();
            listarfi(form.tblFich);
            limpiarrr();
        }
        if (e.getSource() == form.btnList) {
            limpiarTodo();
            listartodoEnTabla(form.tblTodo);

        }

        if (e.getSource() == form.btnSave) {
            guardar();
            listar(form.tbnAprendiz);
            limpiar();
        }
        if (e.getSource() == form.btnSavefich) {
            guardarfi();
            listarfi(form.tblFich);
            limpiarrr();
        }

        if (e.getSource() == form.btnSearch) {
            int fila = form.tbnAprendiz.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(form, "Please select a row!!!");
            } else {
                int id = Integer.parseInt(form.tbnAprendiz.getValueAt(fila, 0).toString());
                String tipodoc = form.tbnAprendiz.getValueAt(fila, 1).toString();
                String numDoc = form.tbnAprendiz.getValueAt(fila, 2).toString();
                String nomb = form.tbnAprendiz.getValueAt(fila, 3).toString();
                String nac = form.tbnAprendiz.getValueAt(fila, 4).toString();
                String gener = form.tbnAprendiz.getValueAt(fila, 5).toString();
                String ciudad = form.tbnAprendiz.getValueAt(fila, 6).toString();

                form.txtid.setText(String.valueOf(id));
                form.cvxDoc.setSelectedItem(tipodoc);
                form.txtNumdoc.setText(numDoc);
                form.txtName.setText(nomb);
                form.jdcDate.setDate(java.sql.Date.valueOf(nac));

                if (gener.equalsIgnoreCase("Male")) {
                    form.rbMasculino.setSelected(true);
                } else if (gener.equalsIgnoreCase("Female")) {
                    form.rbFemenino.setSelected(true);
                }
                form.txtCity.setText(ciudad);
            }

        }

        if (e.getSource() == form.btnSearchFich) {
            int filas = form.tblFich.getSelectedRow();
            if (filas == -1) {
                JOptionPane.showMessageDialog(form, "Please select a row!!!");
            } else {
                int id = Integer.parseInt(form.tblFich.getValueAt(filas, 0).toString());

                String numficha = form.tblFich.getValueAt(filas, 1).toString();
                String nomficha = form.tblFich.getValueAt(filas, 2).toString();
                String sede = form.tblFich.getValueAt(filas, 3).toString();
                int idapre = Integer.parseInt(form.tblFich.getValueAt(filas, 4).toString());

              form.txtNumficha.setText(numficha);
                form.txtnomfich.setText(nomficha);
                form.cvxSede.setSelectedItem(sede);
                form.txtidProgram.setText(String.valueOf(id));
                form.txtIdAprrrrr.setText(String.valueOf(idapre));

            }
        }
        if (e.getSource() == form.btnUpdate) {
            actualizar();
            listar(form.tbnAprendiz);
            limpiar();
        }
        if (e.getSource() == form.btnUpdateFich) {
            actualizarfi();
            listarfi(form.tblFich);
            limpiarrr();
        }
        if (e.getSource() == form.btnEliminate) {
            eliminar();
            listar(form.tbnAprendiz);
            limpiar();
        }

        if (e.getSource() == form.btnExit) {
            int confirmar = JOptionPane.showConfirmDialog(null, "¿Desa salir del programa!!!!!?", "Mensaje", JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.OK_OPTION) {
                System.exit(0);
            }

        }
        if (e.getSource() == form.btnElimFich) {
            eliminarfi();
            listarfi(form.tblFich);
            limpiarrr();
        }

    }
   
public void listar(JTable tbnAprendiz) {
        centrarCeldad(tbnAprendiz);
        DefaultTableModel modelo = (DefaultTableModel) form.tbnAprendiz.getModel();
        modelo.setRowCount(0);
        List<Apprentice> lista = dao.Listar();

        for (Apprentice apre : lista) {
            Object[] objeto = {apre.getId(), apre.getTipDoc(), apre.getNumDoc(), apre.getNombre(), apre.getFechanacim(), apre.getGenero(), apre.getCiudad()};
            modelo.addRow(objeto);
        }

        tbnAprendiz.setModel(modelo);
   
}
    public void listartodoEnTabla(JTable tbltodo) {
        centrarultimo(tbltodo);
        DefaultTableModel modelo = (DefaultTableModel) form.tblTodo.getModel();
        modelo.setRowCount(0);
        List<Object[]> lista = fao.Listartodo();

        for (Object[] datos : lista) {
            ficha fi = (ficha) datos[0];
            Apprentice ap = (Apprentice) datos[1];

            Object[] fila = {
                fi.getNumFicha(), fi.getNomFich(), ap.getNombre(), fi.getNomSed(), fi.getIdapre()
            };

            modelo.addRow(fila);
        }

        tbltodo.setModel(modelo);
    }

    public void listarfi(JTable tblToken) {

        centrarceldaFI(tblToken);
        DefaultTableModel modelo = (DefaultTableModel) form.tblFich.getModel();
        modelo.setRowCount(0);
        List<ficha> lista = fao.Listarficha();

        for (ficha fi : lista) {
            Object[] objeto = {fi.getIdFich(), fi.getNumFicha(), fi.getNomFich(), fi.getNomSed(),fi.getIdapre()};
            modelo.addRow(objeto);
        }

        tblToken.setModel(modelo);

    }

    public void guardarfi() {
        String numFi = form.txtNumficha.getText();
        String nomFi = form.txtnomfich.getText();
        String sede = form.cvxSede.getSelectedItem().toString();
        int idapre;
        try {
            idapre=Integer.parseInt(form.txtIdAprrrrr.getText());
        } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(form,"Datos vacios,Ficha no registrada");
       return;
        }
        
        
        ficha fil = new ficha();
        fi.setNumFicha(numFi);
        fi.setNomFich(nomFi);
        fi.setNomSed(sede);
        fi.setIdapre(idapre);

        int r = fao.Agregarfi(fil);
        if (r == 1) {
            JOptionPane.showMessageDialog(form, "Ficha Registrado");
        } else {
            JOptionPane.showMessageDialog(form, "Ficha NO Registrado");
        }

    }

    public void guardar() {
        String tipoDoc = form.cvxDoc.getSelectedItem().toString();
        String numDoc = form.txtNumdoc.getText();
        String nombre = form.txtName.getText();
        java.util.Date fechanac = form.jdcDate.getDate();
        String genero = form.rbMasculino.isSelected() ? "Male" : "Female";
        String ciudad = form.txtCity.getText();

        Apprentice apre = new Apprentice();
        apre.setTipDoc(tipoDoc);
        apre.setNumDoc(numDoc);
        apre.setNombre(nombre);
        apre.setFechanacim(fechanac);
        apre.setGenero(genero);
        apre.setCiudad(ciudad);

        int r = dao.Agregar(apre);
        if (r == 1) {
            JOptionPane.showMessageDialog(form, "Usuario Registrado");
        } else {
            JOptionPane.showMessageDialog(form, "Usuario NO Registrado");
        }

    }

    public void actualizarfi() {
        if (form.txtIdAprrrrr.getText().equals("")) {
            JOptionPane.showMessageDialog(form, "Id no encontrado \n\nSeleccione un registro existente");

        } else {
            int id = Integer.parseInt(form.txtidProgram.getText());

            String numFi = form.txtNumficha.getText();
            String nomFi = form.txtnomfich.getText();
            String sede = form.cvxSede.getSelectedItem().toString();
            int idapre = Integer.parseInt(form.txtIdAprrrrr.getText());

            fi.setIdFich(id);
            fi.setNumFicha(numFi);
            fi.setNomFich(nomFi);
            fi.setNomSed(sede);
            fi.setIdapre(idapre);

            int r = fao.ActualizarFi(fi);
            if (r == 1) {
                JOptionPane.showMessageDialog(form, "User Update");
            } else {
                JOptionPane.showMessageDialog(form, "User not Updated");
            }

        }
    }

    public void actualizar() {
        if (form.txtid.getText().equals("")) {
            JOptionPane.showMessageDialog(form, "Id no encontrado \n\nSeleccione un registro existente");
        } else {
            int id = Integer.parseInt(form.txtid.getText());
            String tipoDoc = form.cvxDoc.getSelectedItem().toString();
            String numDoc = form.txtNumdoc.getText();
            String nombre = form.txtName.getText();
            java.util.Date fechanac = form.jdcDate.getDate();
            String genero = form.rbMasculino.isSelected() ? "Male" : "Female";
            String ciudad = form.txtCity.getText();

            ap.setId(id);
            ap.setTipDoc(tipoDoc);
            ap.setNumDoc(numDoc);
            ap.setNombre(nombre);
            ap.setFechanacim(fechanac);
            ap.setGenero(genero);
            ap.setCiudad(ciudad);

            int r = dao.ACtualizar(ap);
            if (r == 1) {
                JOptionPane.showMessageDialog(form, "User Update");
            } else {
                JOptionPane.showMessageDialog(form, "User not Updated");
            }

        }

    }

    public void eliminar() {
        int fila = form.tbnAprendiz.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(form, "Please select a row!!!");
        } else {
            int id = Integer.parseInt(form.tbnAprendiz.getValueAt(fila, 0).toString());
            dao.Eliminar(id);
            JOptionPane.showMessageDialog(form, "Deleted Record");
        }

    }

    public void eliminarfi() {

        int fila = form.tblFich.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(form, "Please select a row!!!");
        } else {
            int id = Integer.parseInt(form.tblFich.getValueAt(fila, 0).toString());
            fao.EliminarFi(id);
            JOptionPane.showMessageDialog(form, "Deleted Record");
        }

    }

    public void centrarCeldad(JTable tblApprentice) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < form.tbnAprendiz.getColumnCount(); i++) {
            tblApprentice.getColumnModel().getColumn(i).setCellRenderer(tcr);

        }
    }

    public void centrarceldaFI(JTable tblToken) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < form.tblFich.getColumnCount(); i++) {
            tblToken.getColumnModel().getColumn(i).setCellRenderer(tcr);

        }
    }

    public void centrarultimo(JTable tbltodo) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < form.tblTodo.getColumnCount(); i++) {
            tbltodo.getColumnModel().getColumn(i).setCellRenderer(tcr);

        }
    }

    void limpiarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) form.tbnAprendiz.getModel();
        modelo.setRowCount(0);
    }

    void limpiarTablaFI() {

        DefaultTableModel modelo = (DefaultTableModel) form.tblFich.getModel();
        modelo.setRowCount(0);

    }

    void limpiarTodo() {

        DefaultTableModel modelo = (DefaultTableModel) form.tblTodo.getModel();
        modelo.setRowCount(0);

    }

    public void limpiar() {
        form.txtid.setText("");
        form.cvxDoc.setSelectedIndex(0);
        form.txtNumdoc.setText("");
        form.txtCity.setText("");
        form.txtName.setText("");
        form.jdcDate.setDate(null);
        form.rbMasculino.setSelected(false);
        form.rbFemenino.setSelected(false);

    }

    public void limpiarrr() {
        form.txtIdAprrrrr.setText("");
        form.txtidProgram.setText("");
        form.txtnomfich.setText("");
        form.txtNumficha.setText("");
        form.cvxSede.setSelectedItem(0);

    }

    private List<Object[]> Listartodo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
