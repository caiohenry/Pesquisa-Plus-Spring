package br.com.pesquisa_plus.pesquisa_plus.validations;

// Importações necessárias
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import br.com.pesquisa_plus.pesquisa_plus.usuario.UsuarioModelo;
import br.com.pesquisa_plus.pesquisa_plus.usuario.UsuarioRepositorio;

// Anotações para a validação
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueUsuario.UniqueValidator.class)
public @interface UniqueUsuario {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String coluna();

    public class UniqueValidator implements ConstraintValidator<UniqueUsuario, String> {

        private String coluna;

        // Adição de dependências
        @Autowired
        private UsuarioRepositorio usuarioRepositorio;

        @Override
        public void initialize(UniqueUsuario constraintAnnotation) {
            coluna = constraintAnnotation.coluna();
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {

            try {

                String methodName = "findBy" + Character.toUpperCase(coluna.charAt(0)) + coluna.substring(1);

                UsuarioModelo usuario = (UsuarioModelo) usuarioRepositorio.getClass()
                        .getMethod(methodName, String.class).invoke(usuarioRepositorio, value);
                if (usuario != null) {
                    context.disableDefaultConstraintViolation();
                    context.buildConstraintViolationWithTemplate(coluna + " já existente na base de dados!")
                            .addConstraintViolation();
                    return false;
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false; // Em caso de erro, considera inválido
            }
        }

    }

}
