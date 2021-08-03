package com.example.exemploJunit.classes;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraTest {
    static Calculadora calculadora;

    @BeforeAll // É executado antes dos testes. Outros: @BeforeEach, @AfterEach, @AfterAll
    public static void setup() {
        calculadora = new Calculadora();
    }

    //Primeira maneira de testar: Testando somas únicas
    @Test
    public void deveResultarQuatroAoSomarDoisEDois() {
        assertEquals(4.0, calculadora.soma(2, 2));
    }

    @Test
    public void deveResultarZeroAoSomarDoisEMenosDois() {
        assertEquals(0.0, calculadora.soma(2, -2));
    }

    //Segunda maneira de testar: Testando múltiplas somas
    @DisplayName("Valida múltiplas somas com informações em CSV")//descreve o teste
    @ParameterizedTest
    @CsvSource({ "1.0, 1.0, 2.0", "2.0, 3.0, 5.0" })
    void validaMultiplasSomasCSV(double parcela1, double parcela2, double resultadoEsperado) {
        assertEquals(resultadoEsperado, calculadora.soma(parcela1, parcela2));
    }

    //Terceira maneira de testar: Testando múltiplas somas com arquivo externo
    @DisplayName("Valida múltiplas somas com informações em arquivo CSV")
    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void validaMultiplasSomasArqCSV(double parcela1, double parcela2, double resultadoEsperado) {
        assertEquals(resultadoEsperado, calculadora.soma(parcela1, parcela2));
    }

    @Test
    public void testaExcecao() {
        assertThrows(ArithmeticException.class, () -> {
            int retorno = 4 / 0;
            System.out.println(retorno);
        });
    }

}
