import utils.MatrizUtils;
import utils.VetorUtils;

public class Vigenere {

    // VALORES CONSTANTES
    final int tamAlfabeto = 26;
    final int tamLinha = tamAlfabeto;
    final int tamColuna = tamAlfabeto;

    /**
     * @return retorna uma matriz de caracteres para a cifra de Vigenere
     */
    public char[][] inicializaMatrizVigenere() {
        char[][] matriz = new char[tamLinha][tamColuna];
        char letra = 'a';
        for (int l = 0; l < tamLinha; l++) {
            for (int c = 0; c < tamColuna; c++) {
                // A posição atual da matriz recebe a letra atual
                matriz[l][c] = letra;
                // Incrementa a letra para cada coluna adicionando na sequência horizontal correta
                letra++;
                // Se a letra for maior que 'z'
                if (letra > 'z') {
                    // Inicializa a letra com 'a' novamente
                    letra = 'a';
                }
            }
            // Incrementa a letra na troca de linha adicionando na sequência vertical correta
            letra++;
        }
        return matriz;
    }

    /**
     * @return retorna um vetor com as letras do alfabeto de A-Z minúsculas
     */
    public char[] inicializaVetorAlfabeto() {
        char letra = 'a';
        char[] vetAlfabeto = new char[tamAlfabeto];
        for (int i = 0; i < tamAlfabeto; i++) {
            vetAlfabeto[i] = letra;
            letra++;
        }
        return vetAlfabeto;
    }

    /**
     * Encontra as posições da letra do texto e da chave
     * @param letraTexto letra do texto para pesquisar no alfabeto
     * @param letraChave letra da chave para pesquisar no alfabeto
     * @return Retorna um vetor de duas posições onde 0 = posição da letra do texto e 1 = posição da letra da chave
     */
    public int[] retornaCoordenadasLetras(char letraTexto, char letraChave) {
        char[] alfabeto = this.inicializaVetorAlfabeto();
        int[] vetCoordenadas = new int[2];
        // Encontra a posição da letra do texto que vai ser cifrado
        for (int i = 0; i < tamAlfabeto; i++) {
            if (letraTexto == alfabeto[i]) {
                vetCoordenadas[0] = i;
            }
        }
        // Encontra a posição da letra da chave usada na cifra
        for (int i = 0; i < tamAlfabeto; i++) {
            if (letraChave == alfabeto[i]) {
                vetCoordenadas[1] = i;
            }
        }
        return vetCoordenadas;
    }

    /**
     * Ajusta a chave da cifra quando ela for menor que o texto
     * @param texto vetor com os caracteres do texto
     * @param chave vetor com os caracteres da chave
     * @return retorna um novo vetor de caracteres com a nova chave do tamanho do texto
     */
    public char[] ajustaChaveCifra (char[] texto, char[] chave) {
        if (texto.length == chave.length)
            return chave;
        else {
            int tamChaveAntiga = chave.length;
            int idxChave = 0;
            char[] novaChave = new char[texto.length];
            for (int i = 0; i < texto.length; i++) {
                novaChave[i] = chave[idxChave];
                idxChave++;
                if (idxChave == tamChaveAntiga)
                    idxChave = 0;
            }
            return novaChave;
        }
    }

    /**
     * Retorna a letra numa coordenada da matriz de Vigenere
     * @param posLinha posição da linha da letra
     * @param posColuna posição da coluna da letra
     * @return Retorna a letra localizada na posição passada como parâmetro
     */
    public char retornaLetraDaMatriz(int posLinha, int posColuna) {
        char[][] matriz = this.inicializaMatrizVigenere();
        char letra = ' ';
        for (int l = 0; l < tamLinha; l++) {
            for (int i = 0; i < tamColuna; i++) {
                letra = matriz[posLinha][posColuna];
            }
        }
        return letra;
    }

    /**
     * Cifra um texto normal usando a cifra de Vigenere
     * @param texto que vai ser cifrado
     * @param chave que vai servir para cifrar o texto
     * @return Retorna uma String com o texo cifrado em letras minúsculas
     */
    public String cifraTextoVigenere(String texto, String chave) {
        // Transforma as 'Strings' recebidas em dois vetores de caracteres
        char[] vetTexto = texto.toLowerCase().toCharArray();
        char[] vetChave = chave.toLowerCase().toCharArray();
        // Declara o vetor de coordenadas que vai receber as posições das letras na matriz
        int[] vetCoordenadas;
        // Guarda o tamanho do texto para o laço ficar legível sem percorrer um vetor em específico
        int tamTexto = vetTexto.length;
        // Cria uma instância do StringBuilder para fazer a concatenação de 'Strings' dentro do laço
        StringBuilder resultado = new StringBuilder();
        // Ajusta a chave da cifra quando for necessário
        vetChave = ajustaChaveCifra(vetTexto, vetChave);
        // Percorre os vetores de caracteres e faz a cifra de cada uma das letras
        for (int i = 0; i < tamTexto; i++) {
            // Busca as respetivas posições das letras atuais do texto e da chave
            vetCoordenadas = this.retornaCoordenadasLetras(vetTexto[i], vetChave[i]);
            // Adiciona a letra encontrada usando as posições do "vetCoordenadas" na String de resultado
            resultado.append(this.retornaLetraDaMatriz(vetCoordenadas[0], vetCoordenadas[1]));
        }
        // Retorna o texto cifrado
        return resultado.toString();
    }
}
    void main() {
        Vigenere vigenere = new Vigenere();

        MatrizUtils.mostraMatrizChar(vigenere.inicializaMatrizVigenere());
        VetorUtils.mostraVetorChar(vigenere.inicializaVetorAlfabeto(), "Alfabeto");
        int[] vetCoordenadas = vigenere.retornaCoordenadasLetras('r', 'd');
        VetorUtils.mostraVetorInt(vetCoordenadas, "Coordenadas");
        IO.println(vigenere.retornaLetraDaMatriz(vetCoordenadas[0], vetCoordenadas[1]));

        IO.println(vigenere.cifraTextoVigenere("RODRIGO", "DINO"));
    }
