package br.edu.fema.publicacao.dto;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import br.edu.fema.generics.PageResponseDTO;
import br.edu.fema.publicacao.domain.Publicacao;
import br.edu.fema.publicacao.service.PublicacaoService;
import br.edu.fema.publicacaoTag.dto.PublicacaoTagConsultaDTO;
import br.edu.fema.usuario.domain.Usuario;
import lombok.Data;

@Data
public class PublicacaoConsultaDTO {
	private Long id;
    private Usuario usuario; //UsuarioConsultaDTO
    private String titulo;
    private String publicacao;
    private Date dataInicio;
    private Date dataFim;
    private Integer visualizacoes;
    private List<PublicacaoTagConsultaDTO> publicacaoTags;
    private String quantoTempoFoiPostado;
    
    public PublicacaoConsultaDTO(Publicacao publicacao) {
    	this.id = publicacao.getId();
    	this.usuario = publicacao.getUsuario();
    	this.titulo = publicacao.getTitulo();
    	this.publicacao = publicacao.getPublicacao();
    	this.dataInicio = publicacao.getDataInicio();
    	this.dataFim = publicacao.getDataFim();
    	this.visualizacoes = publicacao.getVisualizacoes();
    	this.publicacaoTags = PublicacaoTagConsultaDTO.converterParaListDTO(publicacao.getPublicacaoTags());
    	this.quantoTempoFoiPostado = PublicacaoService.calcularDiferenca(this.dataInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    }
    
    public static List<PublicacaoConsultaDTO> converterParaListDTO(List<Publicacao> publicacoes) {
    	return publicacoes.stream().map(publicacao -> new PublicacaoConsultaDTO(publicacao)).toList();
    }
    
    public static PageResponseDTO<PublicacaoConsultaDTO> converterParaPageResponseDTO(Page<Publicacao> page) {
    	List<PublicacaoConsultaDTO> dto = converterParaListDTO(page.getContent());
    	return new PageResponseDTO<PublicacaoConsultaDTO>(dto, page.hasNext(), page.getTotalElements(), page.getTotalPages());
    }
}
