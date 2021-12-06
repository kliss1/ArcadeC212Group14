package edu.iu.c212.models;

public enum Item {

    //Kyle Liss

    CANDY("Candy", 2),
    NINTENDOSWITCH("Nintendo Switch", 300),
    YOYO("Yoyo", 5),
    NERFGUN("Nerf Gun", 50),
    FOURWHEELER("Four Wheeler", 10000);


    private String readableName;
    private double value;

    Item(String readableName, double value){
        this.readableName = readableName;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public String getReadableName() {
        return readableName;
    }

    @Override
    public String toString(){
        return "Name: (" + readableName + ") Value: (" + value + ")";
    }

}
