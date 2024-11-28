package com.fag.domain.repositories;

import com.fag.domain.dto.BankslipDTO;

public interface IBassRepository {
    
    String consultarBoleto(String linhaDigitavel);

    String pagarBoleto(String document, BankslipDTO dadosBoletoConsultado, Double originalValue, 
                        Double valueWithDiscount, Double valueWithAdditional);

    String gerarQrCode(String document, Double valorPix);
}
