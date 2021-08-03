package DAO;

import DTO.ProdutoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProdutoDAO {
    
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    
    ArrayList<ProdutoDTO> lista = new ArrayList<>();
    
    public void cadastrarProduto(ProdutoDTO objcadastrardto) {
        
        String sql = "insert into produto (codigo, nome, idCategoria, quantidade) values (?,?,?,?)";
        
        conn = new ConexaoDAO().conectaBD();
        
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objcadastrardto.getCodigo());            
            pstm.setString(2, objcadastrardto.getNome());
            pstm.setInt(3, objcadastrardto.getIdCategoria());
            pstm.setInt(4, objcadastrardto.getQuantidade());
            
            pstm.execute();
            pstm.close();
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, " Cadastro Dao" + erro);
        }
        
    }
    
    public ArrayList<ProdutoDTO> listarProduto() {
        String sql = "select * from produto inner join categoria on produto.idCategoria = categoria.idCategoria";
        
        conn = new ConexaoDAO().conectaBD();
        
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while(rs.next()){
               ProdutoDTO objprodutodto = new ProdutoDTO();
               objprodutodto.setId(rs.getInt("id"));              
               objprodutodto.setCodigo(rs.getString("codigo"));
               objprodutodto.setNome(rs.getString("nome"));
               objprodutodto.setNomeCategoria(rs.getString("nomeCategoria"));
               objprodutodto.setQuantidade(rs.getInt("quantidade"));
               
               lista.add(objprodutodto);
            }
          
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Listar Produto" + erro);
        }
          return lista;

    }

    public void alterarProduto(ProdutoDTO objprodutodto){
        String sql = "update produto set nome = ?, quantidade = ? where id = ?";
        
       conn = new ConexaoDAO().conectaBD();
        
        try {
            pstm = conn.prepareStatement(sql);            
            pstm.setString(1, objprodutodto.getNome());
            pstm.setInt(2, objprodutodto.getQuantidade());
            pstm.setInt(3, objprodutodto.getId());
            pstm.execute();
            pstm.close();
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, " Alterar Dao" + erro);
        }
        
        
    }

    public void excluirProduto(ProdutoDTO objprodutodto) {
        String sql = "delete from produto where id = ?";
        
       conn = new ConexaoDAO().conectaBD();
        
        try {
            pstm = conn.prepareStatement(sql);            
            pstm.setInt(1, objprodutodto.getId());
            pstm.execute();
            pstm.close();
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, " Excluir Dao" + erro);
        }
        
        
    }

    public ResultSet listarCategoria(){
        
        conn = new ConexaoDAO().conectaBD();
        
        String sql = "select * from categoria order by nomeCategoria;";
        
        try {
            pstm = conn.prepareStatement(sql);
            return pstm.executeQuery();
            
        } catch ( SQLException erro){
            JOptionPane.showMessageDialog(null, "listar categoria "+ erro.getMessage());
            return null;
        }
    }
}


