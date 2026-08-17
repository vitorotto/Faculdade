package aula03.exercicios;

/**
 * 2) Faça um programa que leia o nome e a idade de 1 nadador e exiba na tela o nome da categoria a qual este nadador pertence. A categoria dos nadadores é dada pela seguinte tabela:
 */
public class Ex002 {
    static void main() {
        String nome = IO.readln("Insira o nome do atleta: ");
        int idade = Integer.parseInt(IO.readln("Insira a idade do atleta: "));
        // Seleciona a categoria
        String categoria;
        if (idade >= 5 && idade <= 7)
            categoria = "Infantil A";
        else if (idade >= 8 && idade <= 10)
            categoria = "Infantil B";
        else if (idade >= 11 && idade <= 13)
            categoria = "Juvenil A";
        else if (idade >= 14 && idade <= 17)
            categoria = "Juvenil B";
        else
            categoria = "Adulto";

        IO.println("Categoria: " + categoria);
    }
}
