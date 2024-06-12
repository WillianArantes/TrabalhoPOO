
package br.unipar.ads.trabalhopoosegundobimestre;

import br.unipar.ads.trabalhopoosegundobimestre.exceptions.NegocioException;
import br.unipar.ads.trabalhopoosegundobimestre.infraestructure.ConnectionFactory;
import br.unipar.ads.trabalhopoosegundobimestre.models.Cachorro;
import br.unipar.ads.trabalhopoosegundobimestre.models.Cor;
import br.unipar.ads.trabalhopoosegundobimestre.models.Pelagem;
import br.unipar.ads.trabalhopoosegundobimestre.models.Raca;
import br.unipar.ads.trabalhopoosegundobimestre.repository.CachorroRepository;
import br.unipar.ads.trabalhopoosegundobimestre.repository.CorRepository;
import br.unipar.ads.trabalhopoosegundobimestre.repository.PelagemRepository;
import br.unipar.ads.trabalhopoosegundobimestre.services.CachorroService;
import br.unipar.ads.trabalhopoosegundobimestre.services.CorService;
import br.unipar.ads.trabalhopoosegundobimestre.services.PelagemService;
import br.unipar.ads.trabalhopoosegundobimestre.services.RacaService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author willh
 */
public class TrabalhoPOOSegundoBimestre {

    public static void main(String[] args) throws SQLException, NegocioException {
        
        try{
           
            Pelagem grPelagem = new Pelagem();
            grPelagem.setDescricaoPelagem("Longa e ondulada");
            
            Raca goldenRetriever = new Raca();
            goldenRetriever.setDescricaoRaca("Golden Retrieve");
            
            Cor grCor = new Cor();
            
            grCor.setDescricaoCor("Dourado");
            
            Cachorro buddy = new Cachorro();
          

            buddy.setNome("Buddy");
            buddy.setTamanho("Grande");
            buddy.setPelagem(grPelagem);
            buddy.setDtNascimento("24/05/2017");
            buddy.setRaca(goldenRetriever);
            buddy.setCor(grCor);
            
            
            CachorroService cachorroService = new CachorroService();
            PelagemService pelagemService = new PelagemService();
            RacaService racaService = new RacaService();
            CorService corService = new CorService();
            
//          corService.insert(grCor);
           // racaService.insert(goldenRetriever);
//          pelagemService.insert(grPelagem);
            cachorroService.insert(buddy);
            
            System.out.println(cachorroService.findAll());
        
        
        }catch(SQLException ex){
             System.out.println("Ops, algo deu errado com o banco de dados\n"
                     +ex.getMessage());
        }catch (NegocioException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {    
            System.out.println("Ops, algo deu errado contate o suporte\n"
                    +ex.getMessage());
        }

    }
}
