package com.example.Akrosh_C0768336_A3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class activity_two extends AppCompatActivity {
    private Spinner candidate_spinner;
    ToggleButton terms_button;
    Button submit_vote;
    EditText get_name, get_id;
    private ArrayList<candidate_Information> candidateArray;
    ArrayList<Voter_details> votersArray;
    private boolean accepted;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        votersArray = new ArrayList<Voter_details>();
        candidate_spinner = findViewById(R.id.spinner);
        terms_button = findViewById(R.id.toggleButton);
        submit_vote = findViewById(R.id.button);
        get_name = findViewById(R.id.editTextTextPersonName);
        get_id = findViewById(R.id.editTextTextPersonID);


        Intent intent = getIntent();
        ArrayList<candidate_Information> candidates = (ArrayList<candidate_Information>) intent.getSerializableExtra("candidates");
        candidateArray = candidates;
        ArrayAdapter<candidate_Information> adapter = new ArrayAdapter<candidate_Information>(this,
                android.R.layout.simple_spinner_item, candidateArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        candidate_spinner.setAdapter(adapter);


        submit_vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(get_name.getText().toString().isEmpty()){
                    Toast.makeText(activity_two.this, "Please fill the name field", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(get_id.getText().toString().isEmpty()){
                    Toast.makeText(activity_two.this, "Please fill the Id field", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (Voter_details V : votersArray) {
                    if(V.getId() == Integer.parseInt(get_id.getText().toString())){
                        Toast.makeText(activity_two.this, "Opps!! Id already present", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                if(!terms_button.isChecked()){
                    Toast.makeText(activity_two.this, "Please accept the terms and condition first", Toast.LENGTH_SHORT).show();
                    return;
                }

                votersArray.add(new Voter_details(Integer.parseInt(get_id.getText().toString()), get_name.getText().toString()));
                int selectedCandidateIndex = candidate_spinner.getSelectedItemPosition();
                candidate_Information selectedCandidate = candidateArray.get(selectedCandidateIndex);
                selectedCandidate.setVotes(selectedCandidate.getVotes() + 1);

                Toast.makeText(activity_two.this, "Your vote has been casted !!", Toast.LENGTH_SHORT).show();


            }
        });

        terms_button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {

                    terms_button.setTextOn("Refuse");

                } else {

                    terms_button.setTextOff("Accept Terms");
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(activity_two.this, MainActivity.class);
        intent.putExtra("candidates", candidateArray);
        startActivity(intent);
    }
}
