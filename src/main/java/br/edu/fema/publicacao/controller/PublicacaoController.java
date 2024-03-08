package br.edu.fema.publicacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fema.generics.PageResponseDTO;
import br.edu.fema.generics.PaginationRequest;
import br.edu.fema.publicacao.dto.PublicacaoCadastroDTO;
import br.edu.fema.publicacao.dto.PublicacaoConsultaDTO;
import br.edu.fema.publicacao.service.PublicacaoService;

@RestController
@RequestMapping("/publicacao")
public class PublicacaoController {
	@Autowired
	private PublicacaoService publicacaoService;
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping()
	public PageResponseDTO<PublicacaoConsultaDTO> recuperarTodosPaginados(PaginationRequest request) {
		return PublicacaoConsultaDTO.converterParaPageResponseDTO(this.publicacaoService.recuperarTodosPaginados(request));
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/pesquisa/{publicacao}")
	public PageResponseDTO<PublicacaoConsultaDTO> procurarPorPublicacao(PaginationRequest request, @PathVariable("publicacao") String publicacao) {
		return PublicacaoConsultaDTO.converterParaPageResponseDTO(this.publicacaoService.procurarPorPublicacao(request, publicacao));
	}
	
	//mostrar publicacoes sem resposta
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/{id}")
	public PublicacaoConsultaDTO recuperarPorId(@PathVariable Long id) {
		return new PublicacaoConsultaDTO(this.publicacaoService.recuperarPorId(id));
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping
	public void salvar(@RequestBody PublicacaoCadastroDTO publicacaoCadastroDTO) {
		this.publicacaoService.salvar(publicacaoCadastroDTO);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@PutMapping
	public void atualizar(@PathVariable("id") Long id, @RequestBody PublicacaoCadastroDTO publicacaoCadastroDTO) {
		publicacaoCadastroDTO.setId(id);
		this.publicacaoService.atualizar(id, publicacaoCadastroDTO);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@DeleteMapping
	public void deletar(@PathVariable("id") Long id) {
		this.publicacaoService.deletar(id);
	}
}
