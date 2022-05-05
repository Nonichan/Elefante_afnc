package com.example.elefante_afnc;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class ModelAnimal extends RealmObject {
    @PrimaryKey
    private int id;
    @Required
    private String nombre;
    private String color;
    private String especie;
    private String habitad;
    private RealmList<ModelAnimalNotas> notas;

    public ModelAnimal(){

    }

    public ModelAnimal(String nombre, String color){
        this.id = MyApplication.animalId.incrementAndGet();
        this.nombre = nombre;
        this.color = color;
        this.notas = new RealmList<ModelAnimalNotas>();
    }

    public int getId() {
        return id;
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

    public RealmList<ModelAnimalNotas> getNotas() {
        return notas;
    }

    public void setNotas(RealmList<ModelAnimalNotas> notas) {
        this.notas = notas;
    }
}
