package com.Spring.BillHub.dto;
import java.time.LocalDateTime;
public class UserTransactionDto {
    private String[] newValues;

    public UserTransactionDto(String[] values){
        newValues[0] = values[0];
        newValues[1] = values[1];
        newValues[2] = values[2];
        //date = values[3];

    }



}
