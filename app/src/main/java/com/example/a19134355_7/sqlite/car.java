package com.example.a19134355_7.sqlite;

public class car {
    private int ID;
    private String nombre;
    private String color;

    public car(int ID, String nombre, String color) {
        this.ID = ID;
        this.nombre = nombre;
        this.color = color;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
