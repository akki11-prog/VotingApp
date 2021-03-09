package com.example.Akrosh_C0768336_A3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<candidate_Information> candidateArray;
    private TextView candidate1, candidate2, candidate3;
    private Button vote_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        candidate1 = findViewById(R.id.txtViewCandidate1);
        candidate2 = findViewById(R.id.txtViewCandidate2);
        candidate3 = findViewById(R.id.txtViewCandidate3);

        vote_button = findViewById(R.id.voting_button);

        candidateArray = new ArrayList<candidate_Information>();
        Intent intent = getIntent();

        ArrayList<candidate_Information> candidates = (ArrayList<candidate_Information>) intent.getSerializableExtra("candidates");
        if(candidates == null){
            candidateArray.add(new candidate_Information(1,"BJP",290));
            candidateArray.add(new candidate_Information(2,"Congress",170));
            candidateArray.add(new candidate_Information(3,"AAP",298));
        }
        else{
            candidateArray = candidates;
        }

        candidate1.setText(candidateArray.get(0).getName()+" : " + candidateArray.get(0).getVotes());
        candidate2.setText(candidateArray.get(1).getName()+" : " + candidateArray.get(1).getVotes());
        candidate3.setText(candidateArray.get(2).getName()+" : " + candidateArray.get(2).getVotes());

        vote_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activity_two.class);
                intent.putExtra("candidates", candidateArray);
                startActivity(intent);
            }
        });


    }
}