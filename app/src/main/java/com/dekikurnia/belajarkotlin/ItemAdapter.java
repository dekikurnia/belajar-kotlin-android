package com.dekikurnia.belajarkotlin;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.dekikurnia.belajarkotlin.models.Item;
import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;

/**
 * Created by server02 on 05/06/2017.
 */

public class ItemAdapter extends RealmBasedRecyclerViewAdapter<Item, ItemAdapter.ViewHolder> {

    private ItemClickListener clickListener;

    public ItemAdapter(Context context, RealmResults<Item> realmResults, boolean automaticUpdate,
                       boolean animateResults, ItemClickListener clickListener) {
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
        final Item item = realmResults.get(i);
        viewHolder.ItemTitle.setText(item.getTitle());
    }

    class ViewHolder extends RealmViewHolder implements View.OnClickListener {
        @Bind(R.id.crud_item_title) public TextView ItemTitle;
        private ItemClickListener clickListener;

        public ViewHolder(View itemView, ItemClickListener clickListener) {
            super(itemView);
            this.clickListener = clickListener;
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(clickListener != null) {
                clickListener.onItemClick(v, realmResults.get(getAdapterPosition()));
            }
        }
    }

    public interface ItemClickListener {
        void onItemClick(View caller, Item task);
    }
}
