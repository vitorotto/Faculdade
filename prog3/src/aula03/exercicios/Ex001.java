package aula03.exercicios;

/**
 * 1) Crie um programa em que o usuário informa o nome de um mês (ex: janeiro) e o programa exibe o número correspondente a esse mesmo mês (ex: 1);
 */
public class Ex001 {
    static void main() {
        String mes = IO.readln("Informe um mês: ");
        switch (mes.toLowerCase()) {
            case "janeiro" -> {
                IO.println("Num: " + 1);
            }
            case "fevereiro" -> {
                IO.println("Num: " + 2);
            }
            case "março" -> {
                IO.println("Num: " + 3);
            }
            case "abril" -> {
                IO.println("Num: " + 4);
            }
            case "maio" -> {
                IO.println("Num: " + 5);
            }
            case "junho" -> {
                IO.println("Num: " + 6);
            }
            case "julho" -> {
                IO.println("Num: " + 7);
            }
            case "agosto" -> {
                IO.println("Num: " + 8);
            }
            case "setembro" -> {
                IO.println("Num: " + 9);
            }
            case "outubro" -> {
                IO.println("Num: " + 10);
            }
            case "novembro" -> {
                IO.println("Num: " + 11);
            }
            case "dezembro" -> {
                IO.println("Num: " + 12);
            }
            default -> {
                IO.println("Mês inválido");
            }
        }
    }
}
