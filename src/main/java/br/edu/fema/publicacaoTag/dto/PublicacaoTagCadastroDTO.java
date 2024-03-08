package br.edu.fema.publicacaoTag.dto;


import br.edu.fema.publicacao.domain.Publicacao;
import br.edu.fema.tag.domain.Tag;
import lombok.Getter;

@Getter
public class PublicacaoTagCadastroDTO {
	private Long id;
	private Publicacao publicacao;
	private Tag tag;
}
