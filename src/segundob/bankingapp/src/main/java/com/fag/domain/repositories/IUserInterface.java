package com.fag.domain.repositories;

import com.fag.domain.dto.UserAccountDTO;
import com.fag.domain.dto.BankslipDTO;
import com.fag.domain.dto.LoginDTO;
import com.fag.domain.dto.PixDTO;

public interface IUserInterface {

    Integer showInitialScreenMenu();
    
    Integer showHomeMenu(String userName);

    LoginDTO getLoginData();

    UserAccountDTO getCreateUserData();

    void showErrorMsg(String msg);

    void showExitMessage();

    String getBarcode();

    BankslipDTO getPaymentBankslipInfo();

    void showBankslipData(String recipientFinal, String dueDate, String value);

    PixDTO getPixData();

    void showPixData(String qrCode);
}