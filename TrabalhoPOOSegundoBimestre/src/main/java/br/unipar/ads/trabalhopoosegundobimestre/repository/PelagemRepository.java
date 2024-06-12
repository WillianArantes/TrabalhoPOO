/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.ads.trabalhopoosegundobimestre.repository;

import br.unipar.ads.trabalhopoosegundobimestre.infraestructure.ConnectionFactory;
import br.unipar.ads.trabalhopoosegundobimestre.models.Cor;
import br.unipar.ads.trabalhopoosegundobimestre.models.Pelagem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author willh
 */
public class PelagemRepository {
    private static final String INSERT = 
            "INSERT INTO Pelagem(descricaoPelagem) VALUES(?)";
    
    private static final String UPDATE = 
            "UPDATE PELAGEM SET descricaoPelagem=? WHERE id=?";
    
    private static final String DELETE = 
            "DELETE FROM pelagem WHERE id=?";
    
    private static final String FIND_BY_ID = 
            "SELECT id, descricaoPelagem" +
            "FROM pelagem WHERE id = ?";
    
    private static final String FIND_ALL =
            "SELECT id, descricaoPelagem FROM pelagem";
    
     //realizando conexao com banco de dados 
    public Pelagem insert(Pelagem pelagem) throws SQLException {
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            //Pego a conexão de banco de dados
            conn = new ConnectionFactory().getConnection();
            //Crio o preparedStatement
            pstmt = conn.prepareStatement(INSERT, 
                    Statement.RETURN_GENERATED_KEYS);
            //seto os parametros da minha consulta
            pstmt.setString(1, pelagem.getDescricaoPelagem());
            //Executo a query no banco
            pstmt.executeUpdate();
            //recupera para o resultset o id gerado pelo banco
            rs = pstmt.getGeneratedKeys();
            //ativa o cursor
            rs.next();
            //Recupero o id do resultset e atribuo ao objeto cor
            pelagem.setId(rs.getInt(1));
            
        } finally {
            //Fecho os objetos de conexão com banco de dados
            if (rs != null)
                rs.close();
            
            if (pstmt != null)
                pstmt.close();
            
            if (conn != null)
                conn.close();
        }
        
        return pelagem;
    }
    public Pelagem findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Pelagem retorno = null;
        
        try {
            
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_ID);
            pstmt.setInt(1, id);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
               retorno = new Pelagem();
               retorno.setId(rs.getInt("ID"));
               retorno.setDescricaoPelagem(rs.getString(
                       "descricaoPelagem"));
            }
            
        } finally {
            
            if (rs != null)
                rs.close();
            
            if (pstmt != null)
                pstmt.close();
            
            if (conn != null)
                conn.close();
            
        }
        
        return retorno;
        
    }
      //Busca todas as Cores cadastradas no banco
    public ArrayList<Pelagem> findAll() throws SQLException {
        
        ArrayList<Pelagem> retorno = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            
            conn = new ConnectionFactory().getConnection();
            //SELECT id, descricaoCor FROM cor
            pstmt = conn.prepareStatement(FIND_ALL);
            //EXecuta a consulta no banco e recupera os resultados
            //no resultset
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                
                Pelagem pelagem = new Pelagem();
                pelagem.setId(rs.getInt("ID"));
                pelagem.setDescricaoPelagem(rs.getString(
                        "descricaoPelagem"));
                //adiciono no arraylist de retorno
                retorno.add(pelagem);
                
            }
            
        } finally {
            
            if (rs != null)
                rs.close();
            
            if (pstmt != null)
                pstmt.close();
            
            if (conn != null) 
                conn.close();
        }
        
        return retorno;
    }
      public void delete(int id) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
                
        try {

            conn = new ConnectionFactory().getConnection();

            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, id);
            ps.execute();

        } finally {
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }

    }
}
