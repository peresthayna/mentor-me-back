package br.edu.fema.publicacaoTag.dto;

import java.util.List;

import br.edu.fema.publicacaoTag.domain.PublicacaoTag;

public class PublicacaoTagConsultaDTO {
	private Long id;
	private Long idTag;
	private String nomeTag;
	
	public PublicacaoTagConsultaDTO() {}
	
	public PublicacaoTagConsultaDTO(PublicacaoTag publicacaoTag) {
		this.id = publicacaoTag.getId();
		this.idTag = publicacaoTag.getTag().getId();
		this.nomeTag = publicacaoTag.getTag().getNome();
	}
	
	public static List<PublicacaoTagConsultaDTO> converterParaListDTO(List<PublicacaoTag> publicacaoTag) {
    	return publicacaoTag.stream().map(tag -> new PublicacaoTagConsultaDTO(tag)).toList();
    }
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeTag() {
		return nomeTag;
	}
	public void setNomeTag(String nome) {
		this.nomeTag = nome;
	}
	public Long getIdTag() {
		return idTag;
	}
	public void setIdTag(Long idTag) {
		this.idTag = idTag;
	}
}
