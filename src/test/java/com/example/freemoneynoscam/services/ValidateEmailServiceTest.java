package com.example.freemoneynoscam.services;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ValidateEmailServiceTest {
    //Arrange
    ValidateEmailService service = new ValidateEmailService();

    ValidateEmailServiceTest() {
    }

    @Test
    void isEmailValid() {
        String emailValid = "nifr@kea.dk";
        String emailInvalid = "nifr@dk";
        String isThisValid = ".@";
        //Act
        boolean result1 = service.isEmailValid(emailValid);
        boolean result2 = service.isEmailValid(emailInvalid);
        boolean result3 = service.isEmailValid(isThisValid);

        //Assert
        assertEquals(true, result1);
        assertEquals(false, result2);
        assertEquals(false, result3);
    }

}
