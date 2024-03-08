package br.edu.fema.tag.dto;

import java.util.Date;

import lombok.Data;

@Data
public class TagCadastroDTO {
	private Long id;
	private String nome;
	private Date data;
	private int numeroPublicacoes;
}
