package aula03.exercicios;

/**
 * 3) Usando o comando de seleção condicional simples “IF”, faça um programa que leia uma senha de 8 caracteres inteiros
 * e verifique se a senha informada equivale a senha predefinida no programa. Se for válida informe na tela
 * "Acesso Autorizado. Caso a senha não estiver correta informe "Acesso negado".
 */
public class Ex003 {
    static void main() {
        String senhaPredefinida = "abcd1234";
        String senha = IO.readln("Informe a sua senha: ");
        if (senha.equals(senhaPredefinida))
            IO.println("Acesso Autorizado");
        else
            IO.println("Acesso Negado");
    }
}
