package com.fag.domain.repositories;

public interface IBassRepository {

    String consultarBoleto(String linhaDigitavel);

    String gerarGrCode(String dadosPix);

    String pagarBoleto (BankslipDTO dadosBoletoConsultado);
}