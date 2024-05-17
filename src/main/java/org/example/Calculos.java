package org.example;

import java.util.ArrayList;
import java.util.Collections;

public class Calculos {

    public static double calcularDesepenho(int numeroDeTestesBemSucedidos, ArrayList<Double> testesBemSucedidos) {        
        double media =  testesBemSucedidos.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        return (media * 51) / numeroDeTestesBemSucedidos;
    }

    public static double calcularTaxaDeSucesso(int numeroDeTestesBemSucedidos) {
        return numeroDeTestesBemSucedidos / 51;
    }

    public static void calcularMediaEDesvio(ArrayList<Double> avaliacoes, String funcao) {
        double media = Calculos.calcularMedia(avaliacoes);
        double desvioPadrao =  Calculos.calcularDesvioPadrao(avaliacoes);
        System.out.printf("%s: %.2f ± %.2f%n", funcao, media, desvioPadrao);
    }

    public static double calcularMelhor(ArrayList<Double> aptidoes) {
        return Collections.min(aptidoes);
    }

    public static double calcularPior(ArrayList<Double> aptidoes) {
        return Collections.max(aptidoes);
    }

    public static double calcularMedia(ArrayList<Double> aptidoes) {
        return aptidoes.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public static double calcularMediana(ArrayList<Double> aptidoes) {
        Collections.sort(aptidoes);
        int tamanho = aptidoes.size();
        if (tamanho % 2 == 0) {
            return (aptidoes.get(tamanho / 2 - 1) + aptidoes.get(tamanho / 2)) / 2.0;
        } else {
            return aptidoes.get(tamanho / 2);
        }
    }

    public static double calcularDesvioPadrao(ArrayList<Double> aptidoes) {
        double media = calcularMedia(aptidoes);
        double soma = 0.0;
        for (double aptidao : aptidoes) {
            soma += Math.pow(aptidao - media, 2);
        }
        return Math.sqrt(soma / aptidoes.size());
    }
}
