package com.acccenture.banktrialprojectbed.helperClasses;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class AccountDetails {
    private int accountNumber;
    private int accountPasscode;
    private int receiverAccountNumber;
    private double amount;
}
