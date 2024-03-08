package br.edu.fema.usuario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fema.generics.PageResponseDTO;
import br.edu.fema.generics.PaginationRequest;
import br.edu.fema.usuario.dto.UsuarioConsultaDTO;
import br.edu.fema.usuario.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping()
	public PageResponseDTO<UsuarioConsultaDTO> recuperarTodosPaginados(PaginationRequest request) {
		return UsuarioConsultaDTO.converterParaPageResponseDTO(this.usuarioService.recuperarTodosPaginados(request));
	}
	
	@GetMapping("/usuario-ativo")
	public UsuarioConsultaDTO recuperarUsuarioSession() {
		return new UsuarioConsultaDTO(usuarioService.getUsuarioDaSession());
	}
	
}
