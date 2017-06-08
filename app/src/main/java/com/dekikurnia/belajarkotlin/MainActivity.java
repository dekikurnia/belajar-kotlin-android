package com.dekikurnia.belajarkotlin;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by server02 on 05/06/2017.
 */

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditFragment editFragment = EditFragment.Companion.newInstance();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_main, editFragment, editFragment.getClass().getSimpleName())
                        .addToBackStack(editFragment.getClass().getSimpleName())
                        .commit();
                hideFab();
            }
        });

        ItemFragment fragment = ItemFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_main, fragment, fragment.getClass().getSimpleName())
                .commit();

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                int backStackCount = getSupportFragmentManager().getBackStackEntryCount();
                if(backStackCount == 0) {
                    fab.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    public void hideFab() {
        fab.setVisibility(View.GONE);
    }

}
