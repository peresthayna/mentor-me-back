package br.edu.fema.usuario.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fema.usuario.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	Page<Usuario> findAllByOrderByPontuacao(Pageable pageable);
	Usuario findByEmail(String email);
}
