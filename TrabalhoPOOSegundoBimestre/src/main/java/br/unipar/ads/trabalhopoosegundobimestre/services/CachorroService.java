   /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.ads.trabalhopoosegundobimestre.services;

import br.unipar.ads.trabalhopoosegundobimestre.exceptions.NegocioException;
import java.sql.SQLException;
import br.unipar.ads.trabalhopoosegundobimestre.models.Cachorro;
import br.unipar.ads.trabalhopoosegundobimestre.models.Cor;
import br.unipar.ads.trabalhopoosegundobimestre.repository.CachorroRepository;
import br.unipar.ads.trabalhopoosegundobimestre.repository.CorRepository;
import java.util.ArrayList;
/**
 *
 * @author willh
 */
public class CachorroService {
    public Cachorro insert(Cachorro cachorro) throws SQLException,
            NegocioException {
        
        validate(cachorro);
        
          CachorroRepository cachorroRepository = new CachorroRepository();
          cachorro = cachorroRepository.insert(cachorro);
        
        return cachorro;
    }
     public Cachorro findById(int id) throws SQLException {
        
        CachorroRepository cachorroRepository = new CachorroRepository();
        Cachorro cachorro = cachorroRepository.findById(id);
        
        return cachorro;
        
    }
     public ArrayList<Cachorro> findAll() throws SQLException {
        
        CachorroRepository cachorroRepository = new CachorroRepository();
        ArrayList<Cachorro> resultado = cachorroRepository.findAll();
        
        return resultado;
     }
    private void validate(Cachorro cachorro) throws NegocioException {
        if (cachorro.getNome() == null) {
            throw new NegocioException("O nome deve ser "
                    + "Informado.");
        }
        if (cachorro.getDtNascimento() == null) {
            throw new NegocioException("A descrição da data de "
                    + "nascimento deve ser Informada.");
        }
        if (cachorro.getTamanho() == null) {
            throw new NegocioException("A descrição do tamanho deve ser "
                    + "Informada.");
        }
        if (cachorro.getNome().isBlank()) {
            throw new NegocioException("O nome "
                    + "deve ser Informado.");
            
        }
    }
}
