package teste;

import dominio.Usuario;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class UsuarioTeste extends BaseTeste{

    private static final String LISTA_USUARIOS_ENDPOINT = "/users";
    private static final String CRIA_USUARIO_ENDPOINT = "/user";

    @Test
    public void testeListaMetadadosDoUsuario(){
        given().
            params("page", "2").
        when().
            get(LISTA_USUARIOS_ENDPOINT).
        then().
            statusCode(200). //alterar o valor para 203 ou outro para garantir que o UsuarioTeste.UsuarioTeste falha
            statusCode(HttpStatus.SC_OK).
            body("page", is(2)). //alterar o valor para 1 ou outro para garantir que o UsuarioTeste.UsuarioTeste falha
            body("per_page", is(6)).
            body("data[0].email", is("michael.lawson@reqres.in")).
            body("data[0].first_name", is("Michael")).
            body("data", is(notNullValue()));
    }

    @Test
    public void criaUsuarioComSucesso(){
        Usuario usuario = new Usuario("rafael", "eng test", "usuario@gmail.com");
        given().
            body(usuario).
        when().
            post(CRIA_USUARIO_ENDPOINT).
        then().
            statusCode(HttpStatus.SC_CREATED).
            body("name", is("rafael"));
    }
}
