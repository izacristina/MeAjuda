package com.mathheals.meajuda.view.topics;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mathheals.meajuda.R;
import com.mathheals.meajuda.model.Topic;
import com.mathheals.meajuda.view.MainActivity;
import com.mathheals.meajuda.view.SearchActivity;

import java.util.List;
import android.support.v7.widget.CardView;

public class TopicListAdapter extends RecyclerView.Adapter<TopicListAdapter.ViewHolder> {

    private List<Topic> data;
    private AppCompatActivity currentActivity;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView idTopic;
        public TextView title;
        public TextView description;
        public TextView author;

        public ViewHolder(CardView card) {
            super(card);

            this.idTopic = (TextView) card.findViewById(R.id.idTopic);
            this.title = (TextView) card.findViewById(R.id.title);
            this.description = (TextView) card.findViewById(R.id.description);
            this.author = (TextView) card.findViewById(R.id.author);
            card.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //Gets the selected topic to be open on the list
            Topic selectedItem = data.get(this.getAdapterPosition());

            int idTopic = selectedItem.getIdTopic();

            Bundle args = new Bundle();
            args.putInt("idTopic", idTopic);

            TopicView topicView = new TopicView();
            topicView.setArguments(args);

            if(currentActivity instanceof MainActivity){
                openFragment(topicView);
            }
            else if(currentActivity instanceof SearchActivity){
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("whichFragment", "topic");
                intent.putExtra("idTopic", idTopic);
                context.startActivity(intent);
            }
        }
    }

    private void openFragment(Fragment fragmentToBeOpen){
        android.support.v4.app.FragmentTransaction fragmentTransaction = currentActivity.
                getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.layout_main, fragmentToBeOpen, "TopicViewFragment");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public TopicListAdapter(List<Topic> data, AppCompatActivity activity, Context context) {
        this.data = data;
        this.currentActivity = activity;
        this.context = context;
    }

    @Override
    public TopicListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CardView view = (CardView) inflater.inflate(R.layout.card_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Topic rowData = this.data.get(position);

        holder.idTopic.setText(rowData.getIdCategory() + "");
        holder.title.setText(rowData.getTitle());
        holder.description.setText(rowData.getDescription());
        holder.author.setText(rowData.getNameOwner());
    }

    @Override
    public int getItemCount(){
        return data.size();
    }

    public void updateList(List data) {
        this.data = data;
        notifyDataSetChanged();
    }
}