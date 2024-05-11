package org.example;

import static org.example.FishSchoolSearch.atualizaPosicaoDoPeixe;
import static org.example.FishSchoolSearch.iniciaCardumeDePeixes;

public class Main {

    public static void main(String[] args) {

        // Coloca um valor maximo de interações
        final int MAXIMO_DE_INTERACOES = 500;

        int dimensoes = 10;
        
        // Aqui é inicializado o cardume de peixes em posições aleatórias
        Peixe[] cardumeDePeixes = iniciaCardumeDePeixes(dimensoes);
        Peixe melhorPeixe = cardumeDePeixes[0];

        // Aqui é inicializado as interações utilizando o for
        for (int interacoes = 0; interacoes < MAXIMO_DE_INTERACOES; interacoes++) {

            // Para cada peixe do cardume de peixes é atualizado a sua posição.
            for (Peixe peixe : cardumeDePeixes) {
                atualizaPosicaoDoPeixe(peixe, cardumeDePeixes);
            }

            // Aqui o melhor peixe é atualizado confome o seu valor de aptidão
            for (Peixe peixe : cardumeDePeixes) {
                if (peixe.aptidao < melhorPeixe.aptidao) {
                    melhorPeixe = peixe;
                }
            }

            System.out.println("Interação " + (interacoes + 1) + ": Melhor Aptidão = " + melhorPeixe.aptidao);
            
            if (melhorPeixe.aptidao < 10e-8) {
                break;
            }
        }

        System.out.println("Melhor solução encontrada:");

        for (int i = 0; i < dimensoes; i++) {
            System.out.println("x[" + i + "] = " + melhorPeixe.posicao[i]);
        }

        System.out.println("Aptidão = " + melhorPeixe.aptidao);
    }
}


