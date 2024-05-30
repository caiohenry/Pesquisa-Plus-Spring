package br.com.pesquisa_plus.pesquisa_plus.usuario;

// Importes necessários
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// Annotations for the model
@Entity
@Table(name = "usuario")
@Getter
@Setter
// Class model for the User entity
public class UsuarioModelo implements UserDetails {

    // ID do usuário ( Primary Key )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome do usuário
    @Column(name = "nome_usuario", length = 255, nullable = false, unique = false)
    @JsonProperty("nome_usuario")
    private String nome;

    // Email do usuário
    @Column(name = "email_usuario", length = 255, nullable = false, unique = true)
    @JsonProperty("email_usuario")
    private String email;

    // CPF do usuário
    @Column(name = "cpf_usuario", length = 14, nullable = false, unique = true)
    @JsonProperty("cpf_usuario")
    private String cpf;

    // Telefone do usuário
    @Column(name = "telefone_usuario", length = 15, nullable = false, unique = false)
    @JsonProperty("telefone_usuario")
    private String telefone;

    // Senha do usuário
    @Column(name = "senha_usuario", length = 255, nullable = false, unique = false)
    @JsonProperty("senha_usuario")
    private String senha;

    // Foto do usuário
    @Column(name = "foto_usuario", nullable = true, unique = false)
    @JsonProperty("foto_usuario")
    private String foto;

    // Tipo do usuário
    @Column(name = "tipo_usuario", nullable = false, unique = false)
    @JsonProperty("tipo_usuario")
    private Integer tipo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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

    @Override
    public String getPassword() {
        return senha;
    }


    
}
