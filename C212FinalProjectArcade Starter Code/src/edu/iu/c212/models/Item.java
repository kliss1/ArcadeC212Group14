package edu.iu.c212.models;

public enum Item {;

    private String readableName;
    private double value;

    Item(String readableName, double value){
        this.readableName = readableName;
        this.value = value;
    }

    @Override
    public String toString(){
        return "Name: (" + readableName + ") Value: (" + value + ")";
    }

}
