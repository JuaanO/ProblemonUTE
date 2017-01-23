package com.problemonute.problemonute.viewmodels;


import android.databinding.ObservableField;
import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.problemonute.problemonute.models.Answer;
import com.problemonute.problemonute.models.Question;

import java.util.Random;


/**
 * Created by Hercson on 6/1/2017.
 */

public class TestViewModel {

    private static final String TAGLOG = "firebase-db";

    private final ObservableField<Question> question = new ObservableField<>();
    private final ObservableField<Answer> answer1 = new ObservableField<>();
    private final ObservableField<Answer> answer2 = new ObservableField<>();
    private final ObservableField<Answer> answer3 = new ObservableField<>();
    private final ObservableField<Answer> answer4 = new ObservableField<>();

    public TestViewModel() {

    }

    public ObservableField<Question> getQuestion() {
        return question;
    }

    public ObservableField<Answer> getAnswer1() {
        return answer1;
    }

    public ObservableField<Answer> getAnswer2() {
        return answer2;
    }

    public ObservableField<Answer> getAnswer3() {
        return answer3;
    }

    public ObservableField<Answer> getAnswer4() {
        return answer4;
    }



    public void loadQuestion()
    {
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference()
                .child("questions");

        ValueEventListener questionListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Random r = new Random();
                int n = r.nextInt(((int) dataSnapshot.getChildrenCount()))+1;
                Log.e("Random:", String.valueOf(n));
                Question q = dataSnapshot.child("q"+n).getValue(Question.class);
                Answer a1 = dataSnapshot.child("q"+n).child("answers").child("a1").getValue(Answer.class);
                Answer a2 = dataSnapshot.child("q"+n).child("answers").child("a2").getValue(Answer.class);
                Answer a3 = dataSnapshot.child("q"+n).child("answers").child("a3").getValue(Answer.class);
                Answer a4 = dataSnapshot.child("q"+n).child("answers").child("a4").getValue(Answer.class);
                question.set(q);
                answer1.set(a1);
                answer2.set(a2);
                answer3.set(a3);
                answer4.set(a4);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAGLOG, "Error!", databaseError.toException());
            }
        };

        myRef.addListenerForSingleValueEvent(questionListener);
    }
}
