package com.example.elefante_afnc;

import android.app.Application;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class MyApplication extends Application {
    public static AtomicInteger animalId = new AtomicInteger();
    public static AtomicInteger notaId = new AtomicInteger();

    @Override
    public void onCreate(){
        super.onCreate();
        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded().build();

        Realm.setDefaultConfiguration(config);
        Realm realm = Realm.getDefaultInstance();
        animalId = getIdByTable(realm, ModelAnimal.class);
        notaId = getIdByTable(realm, ModelAnimalNotas.class);
    }

    private <T extends RealmObject> AtomicInteger getIdByTable
            (Realm realm, Class<T> unaClase){
        RealmResults<T> results = realm.where(unaClase).findAll();

        if (results.size()>0 ){
            return  new AtomicInteger(results.max("id").intValue());
        }
        else {
            return new AtomicInteger();
        }
    }
}
