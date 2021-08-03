package DAO;

import DTO.UsuarioDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class UsuarioDAO {

    Connection conn;
    PreparedStatement pstm;

    public ResultSet autenticUsuario(UsuarioDTO objusuariodto) {
        conn = new ConexaoDAO().conectaBD();

        try {
            String sql = "select * from usuario where nome = ? and senha = ?  ";

            pstm = conn.prepareStatement(sql);
           
            pstm.setString(1, objusuariodto.getUsuario());
            pstm.setString(2, objusuariodto.getSenha());
            
            ResultSet rs = pstm.executeQuery();
            
            return rs;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "usuario Dao" + erro);
            return null;
        }

    }
}
