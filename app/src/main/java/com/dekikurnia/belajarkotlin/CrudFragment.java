package com.dekikurnia.belajarkotlin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dekikurnia.belajarkotlin.models.Crud;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by server02 on 02/06/2017.
 */

public class CrudFragment extends Fragment implements CrudAdapter.CrudItemClickListener {
    @Bind(R.id.crud_recycler_view) protected RealmRecyclerView rv;
    private Realm realm;

    public static CrudFragment newInstance() {
        return new CrudFragment();
    }

    public CrudFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crud, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        realm = Realm.getDefaultInstance();

    }

    @Override
    public void onResume() {
        super.onResume();
        RealmResults<Crud> todos = realm.where(Crud.class).findAll();
        CrudAdapter adapter = new CrudAdapter(getActivity(), todos, true, true, this);
        rv.setAdapter(adapter);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public void onCrudClick(View caller, Crud task) {
        ((MainActivity)getActivity()).hideFab();
        EditFragment editFragment = EditFragment.Companion.newInstance(task.getId());
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_main, editFragment, editFragment.getClass().getSimpleName())
                .addToBackStack(editFragment.getClass().getSimpleName())
                .commit();
    }
}
