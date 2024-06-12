/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.ads.trabalhopoosegundobimestre.services;

import br.unipar.ads.trabalhopoosegundobimestre.exceptions.NegocioException;
import br.unipar.ads.trabalhopoosegundobimestre.models.Cor;
import br.unipar.ads.trabalhopoosegundobimestre.models.Pelagem;
import br.unipar.ads.trabalhopoosegundobimestre.models.Raca;
import br.unipar.ads.trabalhopoosegundobimestre.repository.CorRepository;
import br.unipar.ads.trabalhopoosegundobimestre.repository.PelagemRepository;
import br.unipar.ads.trabalhopoosegundobimestre.repository.RacaRepository;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author willh
 */
public class PelagemService {
     public Pelagem insert(Pelagem pelagem) throws SQLException, NegocioException{
        //antes de adicionar a cor ao banco de dados eu chamo metodo de validaçao
        validate(pelagem);
        //estou executando o processo de negocio adicionando ao banco de dados a cor
        PelagemRepository pelagemRepository = new PelagemRepository();
        pelagemRepository.insert(pelagem);
      
        return pelagem;
    }
      public Pelagem findById(int id) throws SQLException {
        
        PelagemRepository pelagemRepository = new PelagemRepository();
        Pelagem pelagem = pelagemRepository.findById(id);
        
        return pelagem;
        
    }
      
    //metodo com as validaçoes
    private void validate(Pelagem pelagem) throws NegocioException {
        if (pelagem.getDescricaoPelagem() == null) {
            throw new NegocioException("A descrição da pelagem deve ser "
                    + "Informada.");
        }
        if (pelagem.getDescricaoPelagem().isBlank()) {
            throw new NegocioException("A descrição da pelagem "
                    + "deve ser Informada.");
        }
        if (pelagem.getDescricaoPelagem().length() >= 50) {
            throw new NegocioException("A descrição da pelagem"
                    + "não deve conter mais de 50 caracteres.");
        }

       
    }
    //Busca todas as Pelagem cadastradas no banco
    public ArrayList<Pelagem> findAll() throws SQLException {
        
         PelagemRepository pelagemRepository = new  PelagemRepository();
         ArrayList<Pelagem> resultado = pelagemRepository.findAll();
        
        return resultado;
    }
    public void delete(int id) throws SQLException {
        PelagemRepository pelagemRepository = new PelagemRepository();
        pelagemRepository.delete(id);
    }
    
}
