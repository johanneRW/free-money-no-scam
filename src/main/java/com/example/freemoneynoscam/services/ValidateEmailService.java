package com.example.freemoneynoscam.services;

import org.springframework.util.StringUtils;

public class ValidateEmailService {
    FreeMoneyDB fdb = new FreeMoneyDB();

    public ValidateEmailService() {
    }

    public boolean isEmailValid(String email) {
        if (countOccurrencesOfAt(email)) {
            if (checkDots(email)) {
                if (checkEmailLength(email)) {
                    if (hasUserName(email)) {
                        if (hasMailServer(email)) {
                            if (hasDomain(email)) {
                                fdb.addEmail(email);
                                return true;
                            }
                        }
                    }

                }
            }
        }
        return false;
    }


    public boolean checkDuplicate(String email) {
        return fdb.hasEmail(email);
    }

    //Der må være præsis et @ i en email adresse
    private boolean countOccurrencesOfAt(String email) {
        int occurrences = StringUtils.countOccurrencesOf(email, "@");
        return occurrences == 1;
    }

    //Der skal som minimum være et punktum efter @
    private boolean checkDots(String email) {
        int placementOfAt = email.indexOf('@');
        return email.lastIndexOf('.') > placementOfAt;
    }

    //En gyldig email adresse skal være mindst 6 tegn, for at indeholde minimum for oplysninger
    private boolean checkEmailLength(String email) {
        return email.length() > 5;
    }

    //Der skal være mindst et tegn før @
    private boolean hasUserName(String email) {
        return email.indexOf('@') != 0;
    }

    //Der skal være mindst et tegn mellem @ og det sidste punktum
    private boolean hasMailServer(String email) {
        return email.indexOf('@') < (email.lastIndexOf('.') - 1);
    }

    //Der skal være minimum to tegn efter det sidste punktum i adressen
    private boolean hasDomain(String email) {
        return email.lastIndexOf('.') < email.length() - 2;
    }
}
