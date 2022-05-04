package kpi.third.term.java.lab.customers.models.repositories;

import kpi.third.term.java.lab.customers.models.entities.Customer;

import java.util.Random;

class CustomerFactory {

    private static final String[] POSSIBLE_FIRST_NAMES;
    private static final String[] POSSIBLE_SECOND_NAMES;
    private static final String[] POSSIBLE_FATHERS_NAMES;
    private static final String[] POSSIBLE_ADDRESSES;
    private static final int BANK_CARD_NUMBER_LENGTH = 3;
    private static final int UNIQUE_USER_BANK_ACCOUNT_CODE_LENGTH = 14;


    static{
        POSSIBLE_FIRST_NAMES = new String[]{
                "Богдан", "Юрий", "Максим",
                "Дмитрий", "Иван", "Владислав",
                "Игорь", "Сергей", "Петр", "Николай"
        };

        POSSIBLE_SECOND_NAMES = new String[]{
                "Зараник", "Омелян", "Москаленко",
                "Пашковский", "Корниенко", "Баринов",
                "Андриенко", "Коваленко"
        };

        POSSIBLE_FATHERS_NAMES = new String[]{
                "Юрьевич", "Владимирович", "Игоревич",
                "Сергеевич", "Валерьевич", "Иванович",
                "Викторович"
        };

        POSSIBLE_ADDRESSES = new String[]{
                "Киев", "Днепр", "Одесса",
                "Николаев", "Черноморск", "Харьков",
                "Запорожье"
        };
    }


    public static Customer getCustomerInstance(){
        Random random = new Random();
        String firstName;
        String secondName;
        String fathersName;
        String address;
        long bankCardNumber = 0;
        String bankAccountNumber;

        int firstNameIndex = Math.abs( random.nextInt() ) % POSSIBLE_FIRST_NAMES.length;
        int secondNameIndex = Math.abs( random.nextInt() ) % POSSIBLE_SECOND_NAMES.length;
        int fathersNameIndex = Math.abs( random.nextInt() ) % POSSIBLE_FATHERS_NAMES.length;
        int addressIndex = Math.abs( random.nextInt() ) % POSSIBLE_ADDRESSES.length;

        firstName = POSSIBLE_FIRST_NAMES[ firstNameIndex ];
        secondName = POSSIBLE_SECOND_NAMES[ secondNameIndex ];
        fathersName = POSSIBLE_FATHERS_NAMES[ fathersNameIndex ];
        address = POSSIBLE_ADDRESSES[ addressIndex ];

        for(int i = 0; i < BANK_CARD_NUMBER_LENGTH; i++){
            if(  i == 0 ){
                bankCardNumber = Math.abs( random.nextInt() ) % 9 + 1;
            }
            else{
                bankCardNumber *= 10;
                bankCardNumber += Math.abs( random.nextInt() ) % 10;
            }
        }

        long uniqueUserBankAccountCode = 0;
        for(int i = 0; i < UNIQUE_USER_BANK_ACCOUNT_CODE_LENGTH; i++){
            if( i == 0 ){
                uniqueUserBankAccountCode = Math.abs( random.nextInt() ) % 9 + 1;
            }
            else {
                uniqueUserBankAccountCode *= 10;
                uniqueUserBankAccountCode += Math.abs(random.nextInt()) % 10;
            }
        }


        bankAccountNumber = "UA2532820900000" + uniqueUserBankAccountCode;

        return new Customer(
                firstName,
                secondName,
                fathersName,
                address,
                bankCardNumber,
                bankAccountNumber
        );
    }


}
