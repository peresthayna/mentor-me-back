package br.edu.fema.usuario.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.edu.fema.generics.PaginationRequest;
import br.edu.fema.usuario.domain.Usuario;
import br.edu.fema.usuario.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Page<Usuario> recuperarTodosPaginados(PaginationRequest request) {
		return this.usuarioRepository.findAllByOrderByPontuacao(request.toPageable());
	}
	
	public Usuario findById(Long id) {
		return this.usuarioRepository.findById(id).orElse(null);
	}
	
	public Usuario getUsuarioDaSession() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email;    
		if (principal instanceof UserDetails) {
			email = ((UserDetails)principal).getUsername();
		} else {
			email = principal.toString();
		}
		return this.usuarioRepository.findByEmail(email);
	}
	
	public Usuario salvar(Usuario usuario) {
		return this.usuarioRepository.save(usuario);
    }
    
    public void deletar(Long id) {
    	Optional<Usuario> usuario = this.usuarioRepository.findById(id);
    	if(usuario.isPresent()) {    		
    		this.usuarioRepository.delete(usuario.get());
    	}
    }
}
