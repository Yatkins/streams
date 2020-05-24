package edu.ti.caih313.collections.dataobj;

import java.util.HashMap;

public class EmailAddress {

    public HashMap<Email, String> emailMap = new HashMap<Email, String>();;

    public enum Email {HOME, WORK, SCHOOL};

    String primaryEmailAddress(Email email){
        String returnEmail;
        switch (email){
            case HOME : returnEmail = emailMap.get(Email.HOME);
                 break;
            case WORK : returnEmail = emailMap.get(Email.WORK);
                 break;
            case SCHOOL: returnEmail = emailMap.get(Email.SCHOOL);
                 break;
            default : returnEmail = null;
        }return returnEmail;
    }
}
