package com.fag.domain.repositories;

import com.fag.domain.dto.BankslipDTO;

public interface IBassRepository {

    String consultarBoleto(String barcode);

    String payBoleto(BankslipDTO payload);

    String generatedQRCode(Double payload);
}