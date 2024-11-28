package com.fag.domain.repositories;

import com.fag.domain.dto.UserAccountDTO;
import com.fag.domain.dto.LoginDTO;

public interface IUserInterface {

    String getBarcode();

    BankslipDTO getPaymentBankslipInfo();

    void showBankslipData(String data);

    Double getPixData();

    void showPixData(String data);
    
    Integer showInitialScreenMenu();

    Integer showHomeMenu(String userName);

    LoginDTO getLoginData();

    UserAccountDTO getCreateUserData();

    void showErrorMsg(String msg);

    void showExitMessage();

}