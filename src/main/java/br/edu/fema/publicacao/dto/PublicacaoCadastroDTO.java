package br.edu.fema.publicacao.dto;

import java.util.List;

import br.edu.fema.publicacaoTag.dto.PublicacaoTagCadastroDTO;
import lombok.Data;

@Data
public class PublicacaoCadastroDTO {
	private Long id;
    private Long idUsuario;
    private String titulo;
    private String publicacao;
    private Integer visualizacoes;
    private List<PublicacaoTagCadastroDTO> publicacaoTags;
}
