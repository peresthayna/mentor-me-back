package br.edu.fema.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.edu.fema.usuario.domain.Usuario;

public interface UserDetailsRepository extends JpaRepository<Usuario, Long>{
	UserDetails findByEmail(String email);
}
