package com.fag.service;

import com.fag.domain.dto.UserAccountDTO;

import com.fag.domain.dto.BankslipDTO;
import com.fag.domain.repositories.IUserInterface;
import com.fag.domain.repositories.IUserRepository;
import com.fag.domain.repositories.IBassRepository;
import com.fag.infra.utils.JsonUtils;

public class BankingService {

    private IUserInterface gui;
    private IUserRepository userDB;
    private IBassRepository bassRepository;

    public BankingService(IUserInterface gui, IUserRepository userDB, IBassRepository bassRepository) {
        this.gui = gui;
        this.userDB = userDB;
        this.bassRepository = bassRepository;
    }

    public void login(UserAccountDTO user) {
        Integer option = gui.showHomeMenu(user.getName());

        switch (option) {
            case 1:
                Double valor = gui.getPixData();
                String qrCode = bassRepository.gerarQrCode(user.getDocument(), valor);
                gui.showPixData(qrCode);
                break;
            case 2:
                String linhaDigitavel = gui.getBarcode();
                String boleto = bassRepository.consultarBoleto(linhaDigitavel);
                String recipientFinal = JsonUtils.getField(boleto, "recipient");
                String dueDate = JsonUtils.getField(boleto, "dueDate");
                String value = JsonUtils.getField(boleto, "value");
                gui.showBankslipData(recipientFinal, dueDate, value);
                break;
            case 3:
                BankslipDTO barcode = gui.getPaymentBankslipInfo();
                String dados_boleto = bassRepository.consultarBoleto(barcode.getBarcode());
                Double original_value = Double.parseDouble(JsonUtils.getField(dados_boleto, "originalValue"));
                Double value_with_discount = Double.parseDouble(JsonUtils.getField(dados_boleto, "totalWithDiscount"));
                Double value_with_additional = Double.parseDouble(JsonUtils.getField(dados_boleto, "totalWithAdditional"));
                String boleto_pagamento = bassRepository.pagarBoleto(user.getDocument(), barcode, original_value, value_with_discount, value_with_additional);

                String status = JsonUtils.getField(boleto_pagamento, "receiptformatted")

                if (status == null) {
                    status = JsonUtils.getField(boleto_pagamento, "message");
                }

                System.out.println(status);
                break;
            case 4:
                gui.showExitMessage();
                break;
            default:
                gui.showErrorMsg("Opção inválida!");
                break;
        }
    }

}