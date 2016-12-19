package com.example.acer.assignmentcalculator;

/**
 * Created by Acer on 10/12/2016.
 */

public class Memory {
    Integer memory;

    public Memory(){
        memory=0;
    }

    public String addToMemory(String number){
        Integer value = Integer.parseInt(number);
        memory = value + memory;
        String returnNumber = memory.toString();
        return returnNumber;
    }

    public String clearMemory(){
        memory = 0;
        return memory.toString();
    }

    public String saveToMemory(String number){
        Integer value = Integer.parseInt(number);
        memory = value;
        String returnNumber = memory.toString();
        return returnNumber;
    }
}
