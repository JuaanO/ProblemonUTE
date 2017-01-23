package com.problemonute.problemonute.viewmodels;

import android.content.Context;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.problemonute.problemonute.models.Answer;
import com.problemonute.problemonute.models.Question;
import com.problemonute.problemonute.models.User;

import java.util.Random;

/**
 * Created by Hercson on 9/1/2017.
 */

public class ProblemViewModel {

    private static final String TAGLOG = "firebase-db";
    private final ObservableField<User> user = new ObservableField<>();
    private final ObservableField<Question> question = new ObservableField<>();
    private final ObservableField<Answer> answer1 = new ObservableField<>();
    private final ObservableField<Answer> answer2 = new ObservableField<>();
    private final ObservableField<Answer> answer3 = new ObservableField<>();
    private final ObservableField<Answer> answer4 = new ObservableField<>();

    public ProblemViewModel() {

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


    public void loadUser() {
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference()
                .child("users").child("12345");

        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User u = dataSnapshot.getValue(User.class);
                user.set(u);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAGLOG, "Error!", databaseError.toException());
            }
        };
        myRef.addListenerForSingleValueEvent(userListener);
    }


    public void updateScore(long s) {
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference()
                .child("users").child("12345").child("score");
        myRef.setValue(s);
    }

    public void loadQuestion() {
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference()
                .child("questions");

        ValueEventListener questionListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Random r = new Random();
                int n = r.nextInt(((int) dataSnapshot.getChildrenCount())) + 1;
                Question q = dataSnapshot.child("q" + n).getValue(Question.class);
                Answer a1 = dataSnapshot.child("q" + n).child("answers").child("a1").getValue(Answer.class);
                Answer a2 = dataSnapshot.child("q" + n).child("answers").child("a2").getValue(Answer.class);
                Answer a3 = dataSnapshot.child("q" + n).child("answers").child("a3").getValue(Answer.class);
                Answer a4 = dataSnapshot.child("q" + n).child("answers").child("a4").getValue(Answer.class);
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

    public boolean onAnswerClick(Context context, int op) {
        switch (op) {
            case 1:
                if (answer1.get().isRight()) {
                    Toast.makeText(context, "CORRECTO!!!!! ganas " + question.get().getPoints() + " puntos", Toast.LENGTH_SHORT).show();
                    updateScore(user.get().getScore() + question.get().getPoints());
                } else {
                    Toast.makeText(context, "INCORRECTO!!!!! pierdes " + question.get().getPoints() + " puntos", Toast.LENGTH_SHORT).show();
                    if (user.get().getScore() - question.get().getPoints() <= 0)
                        updateScore(0);
                    else
                        updateScore(user.get().getScore() - question.get().getPoints());
                }
                break;
            case 2:
                if (answer2.get().isRight()) {
                    Toast.makeText(context, "CORRECTO!!!!! ganas " + question.get().getPoints() + " puntos", Toast.LENGTH_SHORT).show();
                    updateScore(user.get().getScore() + question.get().getPoints());
                } else {
                    Toast.makeText(context, "INCORRECTO!!!!! pierdes " + question.get().getPoints() + " puntos", Toast.LENGTH_SHORT).show();
                    if (user.get().getScore() - question.get().getPoints() <= 0)
                        updateScore(0);
                    else
                        updateScore(user.get().getScore() - question.get().getPoints());
                }
                break;
            case 3:
                if (answer3.get().isRight()) {
                    Toast.makeText(context, "CORRECTO!!!!! ganas " + question.get().getPoints() + " puntos", Toast.LENGTH_SHORT).show();
                    updateScore(user.get().getScore() + question.get().getPoints());
                } else {
                    Toast.makeText(context, "INCORRECTO!!!!! pierdes " + question.get().getPoints() + " puntos", Toast.LENGTH_SHORT).show();
                    if (user.get().getScore() - question.get().getPoints() <= 0)
                        updateScore(0);
                    else
                        updateScore(user.get().getScore() - question.get().getPoints());
                }
                break;
            case 4:
                if (answer4.get().isRight()) {
                    Toast.makeText(context, "CORRECTO!!!!! ganas " + question.get().getPoints() + " puntos", Toast.LENGTH_SHORT).show();
                    updateScore(user.get().getScore() + question.get().getPoints());
                } else {
                    Toast.makeText(context, "INCORRECTO!!!!! pierdes " + question.get().getPoints() + " puntos", Toast.LENGTH_SHORT).show();
                    if (user.get().getScore() - question.get().getPoints() <= 0)
                        updateScore(0);
                    else
                        updateScore(user.get().getScore() - question.get().getPoints());
                }
                break;
            case 5:
                Toast.makeText(context, "SE ACABÓ EL TIEMPO!!! pierdes " + question.get().getPoints() + " puntos", Toast.LENGTH_LONG).show();
                if (user.get().getScore() - question.get().getPoints() <= 0)
                    updateScore(0);
                else
                    updateScore(user.get().getScore() - question.get().getPoints());
                break;
            default:
                Toast.makeText(context, "HAS ABANDONADO EL JUEGO!!! pierdes " + question.get().getPoints() + " puntos", Toast.LENGTH_LONG).show();
                if (user.get().getScore() - question.get().getPoints() <= 0)
                    updateScore(0);
                else
                    updateScore(user.get().getScore() - question.get().getPoints());
                break;
        }

        return true;
    }


}
