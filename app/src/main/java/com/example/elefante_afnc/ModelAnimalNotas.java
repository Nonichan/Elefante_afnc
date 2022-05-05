package com.example.elefante_afnc;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class ModelAnimalNotas extends RealmObject {
    @PrimaryKey
    private int id;
    private Date fecha;
    @Required
    private String nota;

    public ModelAnimalNotas(){

    }

    public ModelAnimalNotas(Date fecha, String nota) {
        this.id = MyApplication.notaId.incrementAndGet();
        this.fecha = fecha;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }


    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
