package br.edu.fema.usuario.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;

import br.edu.fema.generics.PageResponseDTO;
import br.edu.fema.usuario.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioConsultaDTO {
	private Long id;
	private String nome;
	private int pontuacao;
	private LocalDateTime dataCadastro;
	private String avatar;
	private String roles;
	
	public UsuarioConsultaDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.pontuacao = usuario.getPontuacao();
		this.dataCadastro = usuario.getDataCadastro();
		this.avatar = usuario.getAvatar();
		this.roles = usuario.getRoles();
	}
	
	public static List<UsuarioConsultaDTO> converterParaListDTO(List<Usuario> usuarios) {
    	return usuarios.stream().map(usuario -> new UsuarioConsultaDTO(usuario)).toList();
    }
    
    public static PageResponseDTO<UsuarioConsultaDTO> converterParaPageResponseDTO(Page<Usuario> page) {
    	List<UsuarioConsultaDTO> dto = converterParaListDTO(page.getContent());
    	return new PageResponseDTO<UsuarioConsultaDTO>(dto, page.hasNext(), page.getTotalElements(), page.getTotalPages());
    }
}
