package com.fag.infra.swing;

import javax.swing.*;

import com.fag.domain.repositories.IUserInterface;
import com.fag.domain.dto.BankslipDTO;
import com.fag.domain.dto.LoginDTO;
import com.fag.domain.dto.UserAccountDTO;

public class SwingUI implements IUserInterface {
    @Override
    public Integer showInitialScreenMenu() {
        String[] options = {"Realizar login", "Criar conta", "Sair"};
        int option = JOptionPane.showOptionDialog(null, "Bem vindo ao Zudra Bank!", "Menu Principal", JOptionPane.DEFAULT_OPTION,   
                                                    JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        return option + 1;
    }

    @Override
    public Integer showHomeMenu(String userName) {
        String[] options = {"Gerar QR Code PIX", "Consultar boleto", "Realizar pagamento boleto"};
        int option = JOptionPane.showOptionDialog(null, "Olá " + userName + "! O que deseja fazer hoje?", "Menu Principal", 
                                                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        return option + 1;
    }

    @Override
    public com.fag.domain.dto.LoginDTO getLoginData() {
        String document = JOptionPane.showInputDialog("Digite seu documento:");
        String password = JOptionPane.showInputDialog("Digite sua senha:");
        
        LoginDTO loginData = new LoginDTO(document, password);

        return loginData;
    }

    @Override
    public com.fag.domain.dto.UserAccountDTO getCreateUserData() {

        String document = JOptionPane.showInputDialog("Digite seu documento:");
        String name = JOptionPane.showInputDialog("Digite seu nome:");
        String email = JOptionPane.showInputDialog("Digite seu email:");
        String password = JOptionPane.showInputDialog("Digite sua senha:");
        String accountNumber = JOptionPane.showInputDialog("Digite sua conta:");

        UserAccountDTO userData = new UserAccountDTO(document, name, email, password, accountNumber);

        return userData;

    }

    @Override
    public String getBarcode() {
        String barcode = JOptionPane.showInputDialog(
                null,
                "Insira o código de barras a ser consultado",
                "Código de barras",
                JOptionPane.INFORMATION_MESSAGE);

        return barcode;
    }

    @Override
    public BankslipDTO getPaymentBankslipInfo() {
        BankslipDTO bankslipDTO = new BankslipDTO();

        String barcode = JOptionPane.showInputDialog(
                null,
                "Insira o código de barras a ser pago",
                "Código de barras",
                JOptionPane.INFORMATION_MESSAGE);
        String transactionId = JOptionPane.showInputDialog(
                null,
                "Insira o identificador de pagamento",
                "Identificador",
                JOptionPane.INFORMATION_MESSAGE);

        bankslipDTO.setBarcode(barcode);
        bankslipDTO.setTransactionId(transactionId);

        return bankslipDTO;
    }

    @Override
    public void showBankslipData(String recipientFinal, String dueDate, String value) {
        JOptionPane.showMessageDialog(
                null,
                "Beneficiário: " + recipientFinal + "\nVencimento: " + dueDate + "\nValor: " + value,
                "Dados do boleto",
                JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    @Override
    public void showPixData(String qrCode) {
        JOptionPane.showMessageDialog(
                null,
                qrCode,
                "Dados PIX",
                JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    @Override
    public Double getPixData() {
        String amount = JOptionPane.showInputDialog(
                null,
                "Insira o valor do PIX",
                "Valor transação",
                JOptionPane.INFORMATION_MESSAGE);

        return Double.parseDouble(amount);
    }

    @Override
    public void showErrorMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg);
        return;
    }

    @Override
    public void showExitMessage() {
        JOptionPane.showMessageDialog(null, "Obrigado por utilizar o Zudra Bank!");
        return;
    }
}
