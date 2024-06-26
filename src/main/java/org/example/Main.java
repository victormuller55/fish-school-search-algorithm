package org.example;

import static org.example.FishSchoolSearch.atualizaPosicaoDoPeixe;
import static org.example.FishSchoolSearch.iniciaCardumeDePeixes;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println("\n---------------- INICIANDO ALGORITIMO FISH SCHOOL SEARCH ----------------\n");

        // Coloca um valor maximo de interações
        final int MAXIMO_DE_INTERACOES = 500;

        int dimensoes = 10;
        
        // Aqui é inicializado o cardume de peixes em posições aleatórias
        Peixe[] cardumeDePeixes = iniciaCardumeDePeixes(dimensoes);
        Peixe melhorPeixe = cardumeDePeixes[0];

        ArrayList<Double> aptidoes = new ArrayList<>();

        int numeroDeResultadosComSucesso = 0;
        int numeroDeCiclos = 5;
        ArrayList<Double> melhoresResultados = new ArrayList<>();
        double melhoresResultado = 0.001;
        

        for (int j = 1; j <= numeroDeCiclos; j++) {

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

                System.out.println("Ciclo N°: " + j + ", Interação: " + (interacoes + 1) + ", Melhor Aptidão = "
                        + melhorPeixe.aptidao);

                // Adiciona os melhores resultados em uma lista e soma o número de resultados bons
                if (melhorPeixe.aptidao < melhoresResultado) {
                    melhoresResultados.add(melhorPeixe.aptidao);
                    numeroDeResultadosComSucesso++;
                }

                // Para a execução caso a aptidão seja menor que 10 elevado a -8
                if (melhorPeixe.aptidao < 10e-8) {
                    break;
                }
            }

            // Adiciona as aptdões em uma lista
            aptidoes.add(melhorPeixe.aptidao);
        }

        System.out.println("\n---------------- DADOS DE ENTRADA ----------------\n");

        System.out.println("N° de dimensões = " + dimensoes);
        System.out.println("N° de ciclos = " + numeroDeCiclos);
        System.out.println("N° máximo de interações = " + MAXIMO_DE_INTERACOES);
        System.out.println("N° peixes (possiveis soluções) = 200");
        System.out.println("Espaço de busca = 100 e -100");

        System.out.println("\n---------------- RESULTADOS ----------------\n");

        System.out.println("Aptidão = " + melhorPeixe.aptidao);
        System.out.println("Melhor = " + Calculos.calcularMelhor(aptidoes));
        System.out.println("Pior = " + Calculos.calcularPior(aptidoes));
        System.out.println("Média = " + Calculos.calcularMedia(aptidoes));
        System.out.println("Mediana = " + Calculos.calcularMediana(aptidoes));
        System.out.println("Desvio Padrão = " + Calculos.calcularDesvioPadrao(aptidoes));
        System.out.println("Desempenho = " + Calculos.calcularDesepenho(numeroDeResultadosComSucesso, melhoresResultados));
        System.out.println("Taxa de Sucesso = " + Calculos.calcularTaxaDeSucesso(numeroDeResultadosComSucesso));
        System.out.println("Média e desvio = " + Calculos.calcularMediaEDesvio(aptidoes));
        
        System.out.println("\n---------------- FIM DO ALGORITIMO ----------------\n");

    }
}