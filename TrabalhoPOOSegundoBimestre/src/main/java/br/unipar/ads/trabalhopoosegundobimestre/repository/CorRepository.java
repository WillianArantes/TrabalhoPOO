/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.ads.trabalhopoosegundobimestre.repository;

import br.unipar.ads.trabalhopoosegundobimestre.infraestructure.ConnectionFactory;
import br.unipar.ads.trabalhopoosegundobimestre.models.Cor;
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
public class CorRepository {
    
    //Constantes com os SQLs
    private static final String INSERT = 
            "INSERT INTO COR(descricaoCor) VALUES(?)";
    
    private static final String UPDATE = 
            "UPDATE cor SET descricaoCor=? WHERE id=?";
    
    private static final String DELETE = 
            "DELETE FROM cor WHERE id=?";
    
    private static final String FIND_BY_ID = 
            "SELECT id, descricaoCor " +
            "FROM cor WHERE id = ?";
    
    private static final String FIND_ALL =
            "SELECT id, descricaoCor FROM cor";
    
    //realizando conexao com banco de dados 
    public Cor insert(Cor cor) throws SQLException {
        
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
            pstmt.setString(1, cor.getDescricaoCor());
            //Executo a query no banco
            pstmt.executeUpdate();
            //recupera para o resultset o id gerado pelo banco
            rs = pstmt.getGeneratedKeys();
            //ativa o cursor
            rs.next();
            //Recupero o id do resultset e atribuo ao objeto cor
            cor.setId(rs.getInt(1));
            
        } finally {
            //Fecho os objetos de conexão com banco de dados
            if (rs != null)
                rs.close();
            
            if (pstmt != null)
                pstmt.close();
            
            if (conn != null)
                conn.close();
        }
        
        return cor;
    }
     public Cor findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Cor retorno = null;
        
        try {
            
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_ID);
            pstmt.setInt(1, id);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
               retorno = new Cor();
               retorno.setId(rs.getInt("ID"));
               retorno.setDescricaoCor(rs.getString("descricaoCor"));
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
    public ArrayList<Cor> findAll() throws SQLException {
        
        ArrayList<Cor> retorno = new ArrayList<>();
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
                
                Cor cor = new Cor();
                cor.setId(rs.getInt("ID"));
                cor.setDescricaoCor(rs.getString("descricaoCor"));
                //adiciono no arraylist de retorno
                retorno.add(cor);
                
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
