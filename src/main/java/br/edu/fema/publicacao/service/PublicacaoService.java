package br.edu.fema.publicacao.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.edu.fema.generics.PaginationRequest;
import br.edu.fema.publicacao.domain.Publicacao;
import br.edu.fema.publicacao.dto.PublicacaoCadastroDTO;
import br.edu.fema.publicacao.repository.PublicacaoRepository;
import br.edu.fema.tag.service.TagService;
import br.edu.fema.usuario.domain.Usuario;
import br.edu.fema.usuario.service.UsuarioService;

@Service
public class PublicacaoService {
	@Autowired
	private PublicacaoRepository publicacaoRepository;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private TagService tagService;
	
	public Page<Publicacao> recuperarTodosPaginados(PaginationRequest request) {
		return this.publicacaoRepository.findAll(request.toPageable());
	}
	
	public Publicacao recuperarPorId(Long id) {
		return this.publicacaoRepository.findById(id).orElse(null);
	}
	
	public Page<Publicacao> procurarPorPublicacao(PaginationRequest request, String publicacao) {
		return this.publicacaoRepository.findByPublicacaoContaining(request.toPageable(), publicacao);
	}
	
	public Publicacao salvar(PublicacaoCadastroDTO publicacao) {
		publicacao.setIdUsuario(this.usuarioService.getUsuarioDaSession().getId());
        publicacao.setVisualizacoes(0);
        Usuario usuarioAtualizado = this.usuarioService.findById(publicacao.getIdUsuario());
        usuarioAtualizado.setTotalPublicacoes(usuarioAtualizado.getTotalPublicacoes()+1);
        Publicacao pub = new Publicacao(publicacao);
        pub.getPublicacaoTags().forEach(pt -> {
        	this.tagService.salvar(pt.getTag());
        	pt.setPublicacao(pub);
	});
        this.usuarioService.salvar(usuarioAtualizado);
		return this.publicacaoRepository.save(pub);
    }
    
    public Publicacao atualizar(Long id, PublicacaoCadastroDTO publicacao) {
    	publicacao.setId(id);
    	publicacao.setIdUsuario(this.usuarioService.getUsuarioDaSession().getId());
        return this.publicacaoRepository.save(new Publicacao(publicacao));
    }
    
    public void deletar(Long id) {
    	Optional<Publicacao> publicacao = this.publicacaoRepository.findById(id);
    	if(publicacao.isPresent()) {    		
    		this.publicacaoRepository.delete(publicacao.get());
    	}
    }
    
    public static String calcularDiferenca(LocalDateTime horaPostada) {
        LocalDateTime horaAtual = LocalDateTime.now();
        
        Duration duracao = Duration.between(horaPostada, horaAtual);
        long segundos = duracao.getSeconds();
        
        if (segundos < 60) {
            return "Publicado há alguns segundos atrás";
        } else if (segundos < 3600) {
            long minutos = segundos / 60;
            return String.format("Publicado há %d minutos atrás", minutos);
        }
        
        Period periodo = Period.between(horaPostada.toLocalDate(), horaAtual.toLocalDate());
        int anos = periodo.getYears();
        int meses = periodo.getMonths();
        int dias = periodo.getDays();
        
        switch (anos) {
            case 0:
                switch (meses) {
                    case 0:
                        switch (dias) {
                            case 0:
                                return "Publicado hoje";
                            case 1:
                                return "Publicado há 1 dia atrás";
                            default:
                                return String.format("Publicado há %d dias atrás", dias);
                        }
                    case 1:
                        switch (dias) {
                            case 0:
                                return "Publicado há 1 mês atrás";
                            case 1:
                                return "Publicado há 1 mês e 1 dia atrás";
                            default:
                                return String.format("Publicado há 1 mês e %d dias atrás", dias);
                        }
                    default:
                        switch (dias) {
                            case 0:
                                return String.format("Publicado há %d meses atrás", meses);
                            case 1:
                                return String.format("Publicado há %d meses e 1 dia atrás", meses);
                            default:
                                return String.format("Publicado há %d meses e %d dias atrás", meses, dias);
                        }
                }
            default:
                switch (meses) {
                    case 0:
                        return String.format("Publicado há %d anos atrás", anos);
                    case 1:
                        return String.format("Publicado há %d anos e 1 mês atrás", anos);
                    default:
                        return String.format("Publicado há %d anos e %d meses atrás", anos, meses);
                }
        }
    }
}
