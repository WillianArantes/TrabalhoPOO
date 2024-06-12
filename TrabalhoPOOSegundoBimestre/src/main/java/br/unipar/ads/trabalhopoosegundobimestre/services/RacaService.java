
package br.unipar.ads.trabalhopoosegundobimestre.services;

import br.unipar.ads.trabalhopoosegundobimestre.exceptions.NegocioException;
import br.unipar.ads.trabalhopoosegundobimestre.models.Cor;
import br.unipar.ads.trabalhopoosegundobimestre.repository.RacaRepository;
import br.unipar.ads.trabalhopoosegundobimestre.models.Raca;
import br.unipar.ads.trabalhopoosegundobimestre.repository.CorRepository;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author willh
 */
public class RacaService {
      public Raca insert(Raca raca) throws SQLException, NegocioException {
        
        validate(raca);
        
          RacaRepository racaRepository = new RacaRepository();
          racaRepository.insert(raca);
        
        return raca;
    }
      public Raca findById(int id) throws SQLException {
        
        RacaRepository racaRepository = new RacaRepository();
        Raca raca = racaRepository.findById(id);
        
        return raca;
        
    }
      

    private void validate(Raca raca) throws NegocioException {
        if (raca.getDescricaoRaca() == null) {
            throw new NegocioException("A descrição da raca deve ser "
                    + "Informada.");
        }
        if (raca.getDescricaoRaca().isBlank()) {
            throw new NegocioException("A descrição da raca "
                    + "deve ser Informada.");
        }
        if (raca.getDescricaoRaca().length() <= 4) {
            throw new NegocioException("A descrição da raca "
                    + "deve possuir 4 "
                    + "ou mais caracteres.");
        }
        if (raca.getDescricaoRaca().length() > 60) {
            throw new NegocioException("A descrição da raca "
                    + "não deve possuir "
                    + "mais do que 60 caracteres");
        }
    }
    //Busca todas as Cores cadastradas no banco
    public ArrayList<Raca> findAll() throws SQLException {
        
        RacaRepository racaRepository = new RacaRepository();
        ArrayList<Raca> resultado = racaRepository.findAll();
        
        return resultado;
    }
    public void delete(int id) throws SQLException {
        RacaRepository racaRepository = new RacaRepository();
        racaRepository.delete(id);
    }
    
}
