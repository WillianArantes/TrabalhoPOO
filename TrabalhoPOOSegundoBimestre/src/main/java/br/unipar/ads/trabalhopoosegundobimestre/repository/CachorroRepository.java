/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.ads.trabalhopoosegundobimestre.repository;

import br.unipar.ads.trabalhopoosegundobimestre.infraestructure.ConnectionFactory;
import br.unipar.ads.trabalhopoosegundobimestre.models.Cachorro;
import br.unipar.ads.trabalhopoosegundobimestre.models.Raca;
import br.unipar.ads.trabalhopoosegundobimestre.models.Pelagem;
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
public class CachorroRepository {

    // Constantes com os SQLs
   private static final String INSERT =
            "INSERT INTO ACHORRO(nome, tamanho , dtnascimento, "
            + "racaid, corId, pelagemId) VALUES(?,?,?,?,?,?)";
    private static final String UPDATE =
            "UPDATE cachorro SET nome=?, tamanho=?, dtnascimento=?, racaId=?, "
            + "corId=?, pelagemId=? WHERE id=?";

    private static final String DELETE =
            "DELETE FROM cachorro WHERE id=?";

    private static final String FIND_BY_ID =
            "SELECT id, nome, racaid, pelagemid, corid, tamanho, dtNascimento " +
            "FROM cachorro WHERE id = ?";

    private static final String FIND_ALL =
            "SELECT id, nome, racaid, pelagemid, corid, tamanho, "
            + "dtNascimento FROM cachorro";

    // Realizando conexao com banco de dados
    public Cachorro insert(Cachorro cachorro) throws SQLException {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Pego a conexão de banco de dados
            conn = new ConnectionFactory().getConnection();
            // Crio o preparedStatement
            pstmt = conn.prepareStatement(INSERT,
                    Statement.RETURN_GENERATED_KEYS);
            // Seta os parametros da minha consulta
            pstmt.setString(1, cachorro.getNome());
            pstmt.setString(2, cachorro.getTamanho());
            pstmt.setString(3, cachorro.getDtNascimento());
            pstmt.setInt(4, cachorro.getRaca().getId());
            pstmt.setInt(5, cachorro.getCor().getId());
            pstmt.setInt(6, cachorro.getPelagem().getId());
            // Executa a query no banco
            pstmt.executeUpdate();
            // Recupera para o resultset o id gerado pelo banco
            rs = pstmt.getGeneratedKeys();
            // Ativa o cursor
            rs.next();
            // Recupero o id do resultset e atribuo ao objeto cachorro
            cachorro.setId(rs.getInt(1));

        } finally {
            // Fecho os objetos de conexão com banco de dados
            if (rs != null)
                rs.close();

            if (pstmt != null)
                pstmt.close();

            if (conn != null)
                conn.close();
        }

        return cachorro;
    }
    

    // Busca todos os Cachorros cadastrados no banco
    public ArrayList<Cachorro> findAll() throws SQLException {

        ArrayList<Cachorro> retorno = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            conn = new ConnectionFactory().getConnection();
            // SELECT id, nome, raca_id, pelagem_id, cor_id, tamanho, dtNascimento FROM cachorro
            pstmt = conn.prepareStatement(FIND_ALL);
            // Executa a consulta no banco e recupera os resultados
            // no resultset
            rs = pstmt.executeQuery();

            while (rs.next()) {

                Cachorro cachorro = new Cachorro();
                cachorro.setId(rs.getInt("id"));
                cachorro.setNome(rs.getString("nome"));
                // Aqui você precisa buscar as entidades relacionadas (raca, pelagem, cor)
                cachorro.setRaca(new RacaRepository().findById(rs.getInt("raca_id")));
                cachorro.setPelagem(new PelagemRepository().findById(rs.getInt("pelagem_id")));
                cachorro.setCor(new CorRepository().findById(rs.getInt("cor_id")));
                cachorro.setTamanho(rs.getString("tamanho"));
                cachorro.setDtNascimento(rs.getString("dtNascimento"));
                // Adiciono no arraylist de retorno
                retorno.add(cachorro);

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

    public Cachorro findById(int id) throws SQLException {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Cachorro cachorro = null;

        try {

            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                cachorro = new Cachorro();
                cachorro.setId(rs.getInt("id"));
                cachorro.setNome(rs.getString("nome"));
                cachorro.setRaca(new RacaRepository().findById(rs.getInt("raca_id")));
                cachorro.setPelagem(new PelagemRepository().findById(rs.getInt("pelagem_id")));
                cachorro.setCor(new CorRepository().findById(rs.getInt("cor_id")));
                cachorro.setTamanho(rs.getString("tamanho"));
                cachorro.setDtNascimento(rs.getString("dtNascimento"));
            }

        } finally {

            if (rs != null)
                rs.close();

            if (pstmt != null)
                pstmt.close();

            if (conn != null)
                conn.close();
        }

        return cachorro;
    }

    public Cachorro update(Cachorro cachorro) throws SQLException {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {

            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(UPDATE);
            pstmt.setString(1, cachorro.getNome());
            pstmt.setInt(2, cachorro.getRaca().getId());
            pstmt.setInt(3, cachorro.getPelagem().getId());
            pstmt.setInt(4, cachorro.getCor().getId());
            pstmt.setString(5, cachorro.getTamanho());
            pstmt.setString(6, cachorro.getDtNascimento());
            pstmt.setInt(7, cachorro.getId());
            pstmt.executeUpdate();

        } finally {

            if (pstmt != null)
                pstmt.close();

            if (conn != null)
                conn.close();
        }

        return cachorro;
    }
}
