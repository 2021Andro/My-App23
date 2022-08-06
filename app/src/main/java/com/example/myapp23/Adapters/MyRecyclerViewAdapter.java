package com.example.myapp23.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp23.Classes.Person;
import com.example.myapp23.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MyRecyclerViewAdapter extends FirebaseRecyclerAdapter<Person, MyRecyclerViewAdapter.MyViewHolder> {

    private MyRecyclerViewEvent myEvent;

    public MyRecyclerViewAdapter(MyRecyclerViewEvent myEvent, @NonNull FirebaseRecyclerOptions<Person> options) {
        super(options);

        this.myEvent = myEvent;

    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Person person) {


        holder.tvName.setText(person.getPersonName());
        holder.tvAge.setText(person.getPersonAge());
        
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myEvent.setOnItemClickListener(person);

            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                myEvent.setOnItemLongClickListener(position);

                return true;
            }
        });
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_recycle_layout, parent, false);
        return new MyViewHolder(view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvAge;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);


        }
    }

    public interface MyRecyclerViewEvent{
        void setOnItemClickListener(Person person);
        void setOnItemLongClickListener(int position);
    }
}
