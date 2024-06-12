/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.ads.trabalhopoosegundobimestre.repository;

import br.unipar.ads.trabalhopoosegundobimestre.infraestructure.ConnectionFactory;
import br.unipar.ads.trabalhopoosegundobimestre.models.Cor;
import br.unipar.ads.trabalhopoosegundobimestre.models.Raca;
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
public class RacaRepository {
    
    //Constantes com os SQLs
    private static final String INSERT = 
            "INSERT INTO RACA(descricaoRaca) VALUES(?)";
    
    private static final String UPDATE = 
            "UPDATE raca SET descricaoRaca=? WHERE id=?";
    
    private static final String DELETE = 
            "DELETE FROM raca WHERE id=?";
    
    private static final String FIND_BY_ID = 
            "SELECT id, descricaoRaca " +
            "FROM raca WHERE id = ?";
    
    private static final String FIND_ALL =
            "SELECT id, descricaoRaca FROM raca";
    
    //realizando conexao com banco de dados 
    public Raca insert(Raca raca) throws SQLException {
        
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
            pstmt.setString(1, raca.getDescricaoRaca());
            //Executo a query no banco
            pstmt.executeUpdate();
            //recupera para o resultset o id gerado pelo banco
            rs = pstmt.getGeneratedKeys();
            //ativa o cursor
            rs.next();
            //Recupero o id do resultset e atribuo ao objeto cor
            raca.setId(rs.getInt(1));
            
        } finally {
            //Fecho os objetos de conexão com banco de dados
            if (rs != null)
                rs.close();
            
            if (pstmt != null)
                pstmt.close();
            
            if (conn != null)
                conn.close();
        }
        
        return raca;
    }
     //Busca todas as Cores cadastradas no banco
    public ArrayList<Raca> findAll() throws SQLException {
        
        ArrayList<Raca> retorno = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            
            conn = new ConnectionFactory().getConnection();
            //SELECT id, descricaoRaca FROM cor
            pstmt = conn.prepareStatement(FIND_ALL);
            //EXecuta a consulta no banco e recupera os resultados
            //no resultset
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                
                Raca raca = new Raca();
                raca.setId(rs.getInt("ID"));
                raca.setDescricaoRaca(rs.getString(
                        "descricaoRaca"));
                //adiciono no arraylist de retorno
                retorno.add(raca);
                
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
     public Raca findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Raca retorno = null;
        
        try {
            
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_ID);
            pstmt.setInt(1, id);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
               retorno = new Raca();
               retorno.setId(rs.getInt("ID"));
               retorno.setDescricaoRaca(rs.getString(
                       "descricaoRaca"));
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
