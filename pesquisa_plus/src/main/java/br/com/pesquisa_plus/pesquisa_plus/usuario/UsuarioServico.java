package br.com.pesquisa_plus.pesquisa_plus.usuario;

// Importes necessários
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import br.com.pesquisa_plus.pesquisa_plus.dto.RespostaDTO;
import br.com.pesquisa_plus.pesquisa_plus.usuario.dto.UsuarioCadastrarDTO;
import br.com.pesquisa_plus.pesquisa_plus.usuario.dto.UsuarioListarDTO;
import jakarta.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

// Anotações para o serviço
@Service
@Validated
// Classe da interface de acesso entre as regras de negócio e as consultas do banco
public class UsuarioServico {

    // Adição de dependências
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    private final Validator validator;
    public UsuarioServico(Validator validator) {
        this.validator = validator;
    }

    // Método que lista todos os usuários do sistema
    public ResponseEntity<?> usuarioListar() {

        List<UsuarioListarDTO> usuario = new ArrayList<>();

        for (UsuarioModelo u : usuarioRepositorio.findAll()) {
            UsuarioListarDTO dto = new UsuarioListarDTO();
            dto.converteModeloParaDto(u);
            usuario.add(dto);
        }

        return new ResponseEntity<List<UsuarioListarDTO>>(usuario, HttpStatus.OK);

    }

    // Método que cadastra o usuário no sistema
    public ResponseEntity<?> usuarioCadastrar(UsuarioCadastrarDTO usuario) {

        // Validando os campos do Usuário
        var violations = validator.validate(usuario);
        if (!violations.isEmpty()) {

            List<String> errorMessage = violations.stream()
                    .map(violation -> violation.getMessage())
                    .collect(Collectors.toList());

            return new ResponseEntity<RespostaDTO>(new RespostaDTO(errorMessage), HttpStatus.BAD_REQUEST);
        }
        UsuarioModelo usuarioCadastrado = usuarioRepositorio.save(usuario.converteDtoParaModelo());
        UsuarioListarDTO usuarioDto = new UsuarioListarDTO();
        usuarioDto.converteModeloParaDto(usuarioCadastrado);

        return new ResponseEntity<UsuarioListarDTO>(usuarioDto, HttpStatus.CREATED);
    }

}
