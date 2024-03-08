package br.edu.fema.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioCadastroDTO {
	private Long id;
	private String nome;
	private int pontuacao;
	private String avatar;
	private int totalPublicacoes;
}
