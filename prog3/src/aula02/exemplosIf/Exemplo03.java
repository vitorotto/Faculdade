package aula02.exemplosIf;

public class Exemplo03 {
    static void main() {
        // Ler um usuário e uma senha do teclado e verificar se é admin com a senha 0123456

        // Usuário admin válido
        String usuarioValido = "admin";
        String senhaValida = "0123456";

        // Entrada dos dados
        String usuario = IO.readln("Usuário: ");
        String senha = IO.readln("Senha: ");

        // Status de sucesso
        boolean usuarioOk = usuario.equals(usuarioValido);
        boolean senhaOk = senha.equals(senhaValida);

        if (usuarioOk && senhaOk)
            IO.println("Logado com sucesso");
        else
            IO.println("Acesso inválido");
    }
}
