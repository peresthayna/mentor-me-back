package br.edu.fema.usuario.domain;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "senha")
	private String senha;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "pontuacao")
	private int pontuacao;
	
	@Column(name = "data_cadastro")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime dataCadastro;
	
	@Column(name = "avatar")
	private String avatar;
	
	@Column(name = "roles")
	private String roles;
	
	@Column(name = "total_publicacoes")
	private int totalPublicacoes;
	
	public Usuario(String email, String senha, String nome, String avatar, int totalPublicacoes, String roles) {
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.pontuacao = 0;
		this.dataCadastro = LocalDateTime.now();
		this.avatar = avatar;
		this.totalPublicacoes = totalPublicacoes;
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(roles.contains(",")) {
			return List.of(new SimpleGrantedAuthority(roles.split(",")[0]), 
					new SimpleGrantedAuthority(roles.split(",")[1]));
		} else {
			return List.of(new SimpleGrantedAuthority(roles));
		}
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
