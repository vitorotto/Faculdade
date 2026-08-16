package aula03.exercicios;

public class Ex010 {
    static void main() {
        int numPessoas = Integer.parseInt(IO.readln("Quantidade de pessoas: "));
        int[] vetorIdades = new int[numPessoas];
        String[] vetorNomes = new String[numPessoas];
    
        for (int i = 0; i < numPessoas; i++) {
            vetorNomes[i] = IO.readln("Informe o nome da pessoa " + i + ": ");
            vetorIdades[i] = Integer.parseInt(IO.readln("Informe a idade da pessoa " + i + ": "));
        }

        int maior = vetorIdades[0];
        int menor = vetorIdades[0];
        int posMaior = 0;
        int posMenor = 0;
        for (int i = 0; i < vetorIdades.length; i++) {
            if (vetorIdades[i] > maior) {
                maior = vetorIdades[i];
                posMaior = i;
            }
            if (vetorIdades[i] < menor) {
                menor = vetorIdades[i]; 
                posMenor = i;
            }
        }

        IO.println("Maior: " + vetorNomes[posMaior] + " - " + maior);
        IO.println("Menor: " + vetorNomes[posMenor] + " - " + menor);
    }

}
