package br.com.pesquisa_plus.pesquisa_plus.usuario;

// Importes necessários
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Anotações para o repositório
@Repository
// Interface de acesso ao banco de dados
public interface UsuarioRepositorio extends CrudRepository<UsuarioModelo, Long> {

    // Recuperando o usuário de acordo com o email
    UsuarioModelo findByEmail(String email);

    // Recuperando o usuário de acordo com o CPF
    UsuarioModelo findByCpf(String cpf);
    
}
