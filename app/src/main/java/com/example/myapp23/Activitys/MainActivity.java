package com.example.myapp23.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapp23.Adapters.MyRecyclerViewAdapter;
import com.example.myapp23.Classes.MyApp;
import com.example.myapp23.Classes.Person;
import com.example.myapp23.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.MyRecyclerViewEvent {

    private EditText etPersonName, etPersonAge;
    private String personName, personAge;

    private ProgressBar progressBar;

    private Button btnAddPerson;

    private RecyclerView rvPersonList;
    private RecyclerView.LayoutManager layoutManager;
    private MyRecyclerViewAdapter myAdapter;
    private DatabaseReference personDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialViews();

        btnAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                personName = etPersonName.getText().toString().trim();
                personAge = etPersonAge.getText().toString().trim();

                if (personName.isEmpty() || personAge.isEmpty()) {

                    Toast.makeText(MainActivity.this, "Empty filed", Toast.LENGTH_SHORT).show();
                    return;

                } else {

                    Toast.makeText(MainActivity.this, "Name: " + personName + " || Age: " + personAge, Toast.LENGTH_SHORT).show();

                    personAddData();


                }
            }
        });


    }

    private void personAddData() {

        progressBar.setVisibility(View.VISIBLE);
        btnAddPerson.setEnabled(false);

        personDbRef = MyApp.myDB.getReference("Person Record List");

        String keyID = personDbRef.push().getKey().toString();

        Person person = new Person(keyID, personName, personAge);

        personDbRef
                .child(keyID)
                .setValue(person)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {

                            Toast.makeText(MainActivity.this, "Person Data Is Save", Toast.LENGTH_SHORT).show();
                            resetViews();

                            progressBar.setVisibility(View.GONE);
                            btnAddPerson.setEnabled(true);


                        } else {


                            Toast.makeText(MainActivity.this, "Person Data Is Not Save", Toast.LENGTH_SHORT).show();

                            progressBar.setVisibility(View.GONE);
                            btnAddPerson.setEnabled(true);

                        }

                    }
                });


    }

    private void resetViews() {

        etPersonName.setText(null);
        etPersonAge.setText(null);

        personName = null;
        personAge = null;

    }

    private void initialViews() {

        etPersonName = findViewById(R.id.etPersonName);
        etPersonAge = findViewById(R.id.etPersonAge);
        btnAddPerson = findViewById(R.id.btnAddPerson);
        rvPersonList = findViewById(R.id.rvPersonList);
        progressBar = findViewById(R.id.progressBar);

        layoutManager = new LinearLayoutManager(this);

        FirebaseRecyclerOptions<Person> options =
                new FirebaseRecyclerOptions.Builder<Person>()
                        .setQuery(MyApp.myDB.getReference("Person Record List"), Person.class)
                        .build();

        myAdapter = new MyRecyclerViewAdapter(this, options);

        rvPersonList.setLayoutManager(layoutManager);
        rvPersonList.setAdapter(myAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        myAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myAdapter.stopListening();
    }

    @Override
    public void setOnItemClickListener(Person person) {

        Toast.makeText(this, "Name: "+person.getPersonName(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void setOnItemLongClickListener(int position) {


            Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();

            myAdapter.getRef(position).removeValue();

            myAdapter.notifyDataSetChanged();



    }
}