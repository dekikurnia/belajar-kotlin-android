package com.dekikurnia.belajarkotlin;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by server02 on 05/06/2017.
 */

public class KotlinMix extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        RealmConfiguration config = new RealmConfiguration.Builder(this)
                .name("kotlinmix.realm")
                .build();

        Realm.setDefaultConfiguration(config);

        Realm.deleteRealm(config);
    }
}
