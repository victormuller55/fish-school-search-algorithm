package org.example;

import java.util.ArrayList;
import java.util.Random;

public class FishSchoolSearch {

    // Parametros do Algoritimo
    private static final int NUMERO_DE_PEIXES = 200;
    private static final double TAMANHO_DE_PASSOS = 0.1;
    private static final double COEFICIENTE_DE_ATRACAO = 0.01;
    private static final double COEFICIENTE_DE_REPULSAO = 0.1;

    // Espaço de Busca
    private static final double LIMITE_INFERIOR = -100.0;
    private static final double LIMITE_SUPERIOR = 100.0;

    // Função de Rosenbrock (Função objetivo)

    public static double funcaoObjetivo(double[] x) {

        ArrayList<Double> f = new ArrayList<>();

        for (int i = 0; i < x.length - 1; i++) {
            f.add(100 * (Math.pow(x[i], 2) - x[i + 1]) * (Math.pow(x[i], 2) - x[i + 1]) + Math.pow(x[i] - 1, 2));
        }

        double sum = 0;

        for (double val : f) {
            sum += val;
        }

        return sum;
    }

    // Função de Rastrigin (Função objetivo)

    // public static double funcaoObjetivo(double[] x) {
    //
    //     private static final double A = 0;
    //
    //     int n = x.length;
    //     double sum = A * n;
    //
    //      for (int i = 0; i < n; i++) {
    //          sum += x[i] * x[i] - A * Math.cos(2 * Math.PI * x[i]);
    //      }
    //
    //      return sum;
    // }  

    // Função de Weierstrass (Função objetivo)
    
    // public static double funcaoObjetivo(double[] x) {

    //     private static final int N = 20;
    //     private static final double a = 0.5;
    //     private static final double b = 3.0;

    //     double sum = 0.0;
    //
    //     for (double xi : x) {
    //         double sum1 = 0.0;
    //         double sum2 = 0.0;
    //
    //         for (int i = 0; i <= N; i++) {
    //             sum1 += Math.pow(a, i) * Math.cos(2 * Math.PI * Math.pow(b, i) * (xi + 0.5));
    //             sum2 += Math.pow(a, i) * Math.cos(2 * Math.PI * Math.pow(b, i) * 0.5);
    //         }
    //
    //         sum += sum1 - N * sum2;
    //     }
    //
    //     return sum;
    //  }

    // O objetivo dessa função é criar um cardume de peixes em diferentes coordenadas.

    static Peixe[] iniciaCardumeDePeixes(int dimensao) {

        Random numeroAleatorio = new Random();

        // Cria o vetor com a quantidade de peixes designada
        Peixe[] cardumeDePeixes = new Peixe[NUMERO_DE_PEIXES];

        // Esse for vai criar cada peixe do cardume
        for (int i = 0; i < NUMERO_DE_PEIXES; i++) {

            Peixe peixe = new Peixe(dimensao);

            // Esse for vai criar a posição de cada peixe do cardume
            for (int j = 0; j < dimensao; j++) {
                peixe.posicao[j] = LIMITE_INFERIOR + (LIMITE_SUPERIOR - LIMITE_INFERIOR) * numeroAleatorio.nextDouble();
            }

            // Aqui é criado a aptidão do peixe, utilizando a função objetivo.
            peixe.aptidao = funcaoObjetivo(peixe.posicao);

            // Aqui é adicionado o peixe criado dentro do cardume.
            cardumeDePeixes[i] = peixe;
        }

        return cardumeDePeixes;
    }

    static void atualizaPosicaoDoPeixe(Peixe peixe, Peixe[] cardumeDePeixes) {

        Random numeroAleatorio = new Random();
        double[] novaPosicao = new double[peixe.posicao.length]; // Nessa variavel será armazenada a nova posição do peixe

        // Aqui o for passa por todas as coordenadas do peixe, onde será calculada a atração e a repulsão em relação aos outros peixes.
        for (int i = 0; i < peixe.posicao.length; i++) {

            double atracao = 0;
            double repulsao = 0;

            // Aqui o for calcula a distancia de cada cardume de peixes do proprio peixe.
            for (Peixe outroPeixe : cardumeDePeixes) {
                if (outroPeixe != peixe) {

                    double distancia = 0;

                    for (int j = 0; j < peixe.posicao.length; j++) {
                        distancia = distancia + Math.pow(peixe.posicao[j] - outroPeixe.posicao[j], 2);
                    }

                    distancia = Math.sqrt(distancia);
                    atracao = atracao + distancia;
                    repulsao = repulsao + (1.0 / distancia);

                }
            }

            // Aquo é calculado a atração e repulsão, multiplicando pelos coeficientes de atraçao e repulsão
            atracao = atracao * COEFICIENTE_DE_ATRACAO;
            repulsao = repulsao * COEFICIENTE_DE_REPULSAO;

            double componenteAleatorio = numeroAleatorio.nextGaussian();

            // Calcula a diferença entre a posição atual do peixe e o efeito da repulsão.
            double diferencaPeixeRepulcao = peixe.posicao[i] - repulsao;

            // Multiplica essa diferença pela atração.
            double multiplicaoDeAtracao = atracao * diferencaPeixeRepulcao;

            // Multiplica a soma anterior pelo tamanho do passo, que determina a distância que o peixe irá se mover. Finalmente, adiciona um componente aleatório para introduzir diversidade no movimento.
            novaPosicao[i] = TAMANHO_DE_PASSOS * multiplicaoDeAtracao + componenteAleatorio;

            // Aqui é verificado se a nova posição está dentro dos limites do espaço de busca.
            if (novaPosicao[i] < LIMITE_INFERIOR) {
                novaPosicao[i] = LIMITE_INFERIOR;
            } else if (novaPosicao[i] > LIMITE_SUPERIOR) {
                novaPosicao[i] = LIMITE_SUPERIOR;
            }

            peixe.posicao = novaPosicao; // Atuliza a posição do peixe
            peixe.aptidao = funcaoObjetivo(novaPosicao); // Calcula a aptidão do peixe para a nova posição usando a função objetivo.
        }
    }

    static void atualizaPosicaoDoPeixes(Peixe peixe, Peixe[] cardumeDePeixes) {

        Random numeroAleatorio = new Random();
        double[] novaPosicao = new double[peixe.posicao.length]; // Nessa variavel será armazenada a nova posição do peixe

        // Aqui o for passa por todas as coordenadas do peixe, onde será calculada a atração e a repulsão em relação aos outros peixes.
        for (int i = 0; i < peixe.posicao.length; i++) {

            double atracao = 0;
            double repulsao = 0;

            // Aqui o for calcula a distancia de cada cardume de peixes do proprio peixe.
            for (Peixe outroPeixe : cardumeDePeixes) {
                if (outroPeixe != peixe) {

                    double distancia = 0;

                    for (int j = 0; j < peixe.posicao.length; j++) {
                        distancia = distancia + Math.pow(peixe.posicao[j] - outroPeixe.posicao[j], 2);
                    }

                    distancia = Math.sqrt(distancia);
                    atracao = atracao + distancia;
                    repulsao = repulsao + (1.0 / distancia);

                }
            }

            // Aquo é calculado a atração e repulsão, multiplicando pelos coeficientes de atraçao e repulsão
            atracao = atracao * COEFICIENTE_DE_ATRACAO;
            repulsao = repulsao * COEFICIENTE_DE_REPULSAO;

            double componenteAleatorio = numeroAleatorio.nextGaussian();

            // Calcula a diferença entre a posição atual do peixe e o efeito da repulsão.
            double diferencaPeixeRepulcao = peixe.posicao[i] - repulsao;

            // Multiplica essa diferença pela atração.
            double multiplicaoDeAtracao = atracao * diferencaPeixeRepulcao;

            // Multiplica a soma anterior pelo tamanho do passo, que determina a distância que o peixe irá se mover. Finalmente, adiciona um componente aleatório para introduzir diversidade no movimento.
            novaPosicao[i] = TAMANHO_DE_PASSOS * multiplicaoDeAtracao + componenteAleatorio;

            // Aqui é verificado se a nova posição está dentro dos limites do espaço de busca.
            if (novaPosicao[i] < LIMITE_INFERIOR) {
                novaPosicao[i] = LIMITE_INFERIOR;
            } else if (novaPosicao[i] > LIMITE_SUPERIOR) {
                novaPosicao[i] = LIMITE_SUPERIOR;
            }

            peixe.posicao = novaPosicao; // Atuliza a posição do peixe
            peixe.aptidao = funcaoObjetivo(novaPosicao); // Calcula a aptidão do peixe para a nova posição usando a função objetivo.
        }
    }
}