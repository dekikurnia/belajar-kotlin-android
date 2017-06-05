package com.dekikurnia.belajarkotlin;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dekikurnia.belajarkotlin.models.Crud;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;

/**
 * Created by server02 on 05/06/2017.
 */

public class CrudAdapter extends RealmBasedRecyclerViewAdapter<Crud, CrudAdapter.ViewHolder> {

    private CrudItemClickListener clickListener;

    public CrudAdapter(Context context, RealmResults<Crud> realmResults, boolean automaticUpdate,
                       boolean animateResults, CrudItemClickListener clickListener) {
        super(context, realmResults, automaticUpdate, animateResults);
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int i) {
        View v = inflater.inflate(R.layout.crud_item, viewGroup, false);
        return new ViewHolder(v, clickListener);
    }

    @Override
    public void onBindRealmViewHolder(ViewHolder viewHolder, int i) {
        final Crud crud = realmResults.get(i);
        viewHolder.crudTitle.setText(crud.getTitle());
    }

    class ViewHolder extends RealmViewHolder implements View.OnClickListener {
        @Bind(R.id.crud_item_title) public TextView crudTitle;
        private CrudItemClickListener clickListener;

        public ViewHolder(View itemView, CrudItemClickListener clickListener) {
            super(itemView);
            this.clickListener = clickListener;
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(clickListener != null) {
                clickListener.onCrudClick(v, realmResults.get(getAdapterPosition()));
            }
        }
    }

    public interface CrudItemClickListener {
        void onCrudClick(View caller, Crud task);
    }
}
