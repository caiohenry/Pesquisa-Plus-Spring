package br.com.pesquisa_plus.pesquisa_plus.usuario.dto;

import br.com.pesquisa_plus.pesquisa_plus.usuario.UsuarioModelo;
// Importes necessários
import lombok.Data;

// Anotações para o DTO
@Data
// Classe Dto responsável pela exibição de dados do usuário
public class UsuarioListarDTO {

    private Long id;

    // Nome do Usuário
    private String nome_usuario;

    // Email do Usuário
    private String email_usuario;

    // CPF do Usuário
    private String cpf_usuario;

    // Telefone do Usuário
    private String telefone_usuario;

    // Método que converte o Modelo para o DTO usuário
    public void converteModeloParaDto(UsuarioModelo usuario) {
        id = usuario.getId();
        nome_usuario = usuario.getNome();
        email_usuario = usuario.getEmail();
        cpf_usuario = usuario.getCpf();
        telefone_usuario = usuario.getTelefone();
    }
    
}
