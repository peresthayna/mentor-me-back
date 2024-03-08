package br.edu.fema.tag.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fema.generics.PageResponseDTO;
import br.edu.fema.generics.PaginationRequest;
import br.edu.fema.tag.domain.Tag;
import br.edu.fema.tag.dto.TagCadastroDTO;
import br.edu.fema.tag.dto.TagConsultaDTO;
import br.edu.fema.tag.service.TagService;

@RestController
@RequestMapping("/tag")
@CrossOrigin()
public class TagController {
	@Autowired
	private TagService tagService;
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping()
	public PageResponseDTO<TagConsultaDTO> recuperarTodosPorData(PaginationRequest request) {
		return TagConsultaDTO.converterParaPageResponseDTO(this.tagService.recuperarTodosPorData(request));
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/popularidade")
	public PageResponseDTO<TagConsultaDTO> recuperarTodosPorPopularidade(PaginationRequest request) {
		return TagConsultaDTO.converterParaPageResponseDTO(this.tagService.recuperarTodosPorOrdemDeQuantidade(request));
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/nome-asc")
	public PageResponseDTO<TagConsultaDTO> recuperarTodosPorOrdemAlfabetica(PaginationRequest request) {
		return TagConsultaDTO.converterParaPageResponseDTO(this.tagService.recuperarTodosPorOrdemAlfabetica(request));
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/pesquisa/{nome}")
	public List<TagConsultaDTO> buscarPorNome(@PathVariable String nome) {
		return TagConsultaDTO.converterParaListDTO(this.tagService.buscarPorNome(nome));
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/{id}")
	public TagConsultaDTO recuperarPorId(@PathVariable Long id) {
		return new TagConsultaDTO(this.tagService.recuperarPorId(id));
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping()
	public void salvar(@RequestBody TagCadastroDTO tagCadastroDTO) {
		this.tagService.salvar(new Tag(tagCadastroDTO));
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@PutMapping
	public void atualizar(@PathVariable("id") Long id, @RequestBody TagCadastroDTO tagCadastroDTO) {
		tagCadastroDTO.setId(id);
		this.tagService.atualizar(id, new Tag(tagCadastroDTO));
	}

}
