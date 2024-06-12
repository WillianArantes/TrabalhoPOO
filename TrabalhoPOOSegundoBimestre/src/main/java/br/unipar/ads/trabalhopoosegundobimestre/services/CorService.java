/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.ads.trabalhopoosegundobimestre.services;

import br.unipar.ads.trabalhopoosegundobimestre.exceptions.NegocioException;
import br.unipar.ads.trabalhopoosegundobimestre.models.Cor;
import br.unipar.ads.trabalhopoosegundobimestre.repository.CorRepository;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author willh
 */
public class CorService {
    
    public Cor insert(Cor cor) throws SQLException, NegocioException{
        //antes de adicionar a cor ao banco de dados eu chamo metodo de validaçao
        validate(cor);
        //estou executando o processo de negocio adicionando ao banco de dados a cor
        CorRepository corRepository = new CorRepository();
        corRepository.insert(cor);
        return cor;
    }
     public Cor findById(int id) throws SQLException {
        
        CorRepository corRepository = new CorRepository();
        Cor cor = corRepository.findById(id);
        
        return cor;
        
    }
    //metodo com as validaçoes
    private void validate(Cor cor) throws NegocioException {
        if (cor.getDescricaoCor() == null) {
            throw new NegocioException("A descrição da Cor deve ser "
                    + "Informada.");
        }
        if (cor.getDescricaoCor().isBlank()) {
            throw new NegocioException("A descrição da Cor "
                    + "deve ser Informada.");
        }
        if (cor.getDescricaoCor().length() <= 2) {
            throw new NegocioException("A descrição da Cor "
                    + "deve possuir 2 "
                    + "ou mais caracteres.");
        }
        if (cor.getDescricaoCor().length() > 100) {
            throw new NegocioException("A descrição da Cor "
                    + "não deve possuir "
                    + "mais do que 100 caracteres");
        }
       
    }
    //Busca todas as Cores cadastradas no banco
    public ArrayList<Cor> findAll() throws SQLException {
        
        CorRepository corRepository = new CorRepository();
        ArrayList<Cor> resultado = corRepository.findAll();
        
        return resultado;
    }
    public void delete(int id) throws SQLException {
        CorRepository corRepository = new CorRepository();
        corRepository.delete(id);
    }
    
}
