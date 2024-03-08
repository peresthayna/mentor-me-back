package br.edu.fema.tag.dto;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import br.edu.fema.generics.PageResponseDTO;
import br.edu.fema.tag.domain.Tag;
import lombok.Data;

@Data
public class TagConsultaDTO {
	private Long id;
	private String nome;
	private Date data;
	private int numeroPublicacoes;
	
	public TagConsultaDTO(Tag tag) {
		this.id = tag.getId();
		this.nome = tag.getNome();
		this.data = tag.getData();
		this.numeroPublicacoes = tag.getNumeroPublicacoes();
	}
	
	public static List<TagConsultaDTO> converterParaListDTO(List<Tag> tags) {
		return tags.stream().map(tag -> new TagConsultaDTO(tag)).toList();
	}
	
	 public static PageResponseDTO<TagConsultaDTO> converterParaPageResponseDTO(Page<Tag> page) {
	    List<TagConsultaDTO> dto = converterParaListDTO(page.getContent());
	    return new PageResponseDTO<TagConsultaDTO>(dto, page.hasNext(), page.getTotalElements(), page.getTotalPages());
	 }
}
