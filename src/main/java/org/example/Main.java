package org.example;

import static org.example.FishSchoolSearch.atualizaPosicaoDoPeixe;
import static org.example.FishSchoolSearch.iniciaCardumeDePeixes;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // Coloca um valor maximo de interações
        final int MAXIMO_DE_INTERACOES = 500;

        int dimensoes = 10;
        
        // Aqui é inicializado o cardume de peixes em posições aleatórias
        Peixe[] cardumeDePeixes = iniciaCardumeDePeixes(dimensoes);
        Peixe melhorPeixe = cardumeDePeixes[0];

        ArrayList<Double> aptidoes = new ArrayList<>();

        int successfulTests = 0;
        ArrayList<Double> arraySuccess = new ArrayList<>();
        double successThreshold = 0.001;
        

        for (int j = 1; j <= 51; j++) {

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

                System.out.println(
                        "Vez: " + j + ", Interação " + (interacoes + 1) + ": Melhor Aptidão = " + melhorPeixe.aptidao);

                if (melhorPeixe.aptidao < successThreshold) {
                    
                    arraySuccess.add(melhorPeixe.aptidao);
                
                    successfulTests++;
                }
                
                if (melhorPeixe.aptidao < 10e-8) {
                    break;
                }

                
            }

            aptidoes.add(melhorPeixe.aptidao);
        }
        
        System.out.println("Desempenho: " + Calculos.calcularDesepenho(successfulTests, arraySuccess));
        System.out.println("Taxa de Sucesso: " + Calculos.calcularTaxaDeSucesso(successfulTests));
        System.out.println("Melhor: " + Calculos.calcularMelhor(aptidoes));
        System.out.println("Pior: " + Calculos.calcularPior(aptidoes));
        System.out.println("Média: " + Calculos.calcularMedia(aptidoes));
        System.out.println("Mediana: " + Calculos.calcularMediana(aptidoes));
        System.out.println("Desvio Padrão: " + Calculos.calcularDesvioPadrao(aptidoes));
        Calculos.calcularMediaEDesvio(aptidoes, "F1");    

        System.out.println("Melhor solução encontrada:");

        for (int i = 0; i < dimensoes; i++) {
            System.out.println("x[" + i + "] = " + melhorPeixe.posicao[i]);
        }

        System.out.println("Aptidão = " + melhorPeixe.aptidao);
    }
}