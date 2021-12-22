package com.markinaksenia.pipeline;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RaitingFragment extends Fragment {
    View myFragment;

    RecyclerView raitingList;

    LinearLayoutManager layoutManager;
    FirebaseRecyclerAdapter<Rait, RaitingViewHolder> adapter;

    FirebaseDatabase database;
    DatabaseReference questionScore, raitingTbl;

    int sum = 0;

    public static RaitingFragment newInstance(){
        RaitingFragment raitingFragment = new RaitingFragment();
        return raitingFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        questionScore = database.getReference("Score");
        raitingTbl = database.getReference("Raiting");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_raiting,container,false);

        raitingList = (RecyclerView) myFragment.findViewById(R.id.raitingList) ;
        layoutManager = new LinearLayoutManager(getActivity());
        raitingList.setHasFixedSize(true);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        raitingList.setLayoutManager(layoutManager);


        updateScore(Common.currentUser.getUserName(), new RaitingCallBack<Rait>() {
            @Override
            public void callBack(Rait rait) {
                raitingTbl.child(rait.getUserName()).setValue(rait);
                // showRaiting();
            }
        });

        adapter = new FirebaseRecyclerAdapter<Rait, RaitingViewHolder>(Rait.class, R.layout.layout_raiting,RaitingViewHolder.class, raitingTbl.orderByChild("score")) {
            @Override
            protected void populateViewHolder(RaitingViewHolder raitingViewHolder, Rait rait, int i) {
                raitingViewHolder.txt_name.setText(rait.getUserName());
                raitingViewHolder.txt_score.setText(String.valueOf(rait.getScore()));

                raitingViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                    }
                });
            }
        };

        adapter.notifyDataSetChanged();
        raitingList.setAdapter(adapter);

        return myFragment;
    }

    private void showRaiting() {
        raitingTbl.orderByChild("score").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    Rait local = data.getValue(Rait.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void updateScore(final String userName, final RaitingCallBack<Rait> callback) {

        questionScore.orderByChild("user").equalTo(userName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    QuestionScore quest = data.getValue(QuestionScore.class);
                    sum+= Integer.parseInt(quest.getScore());
                }
                Rait rait = new Rait (userName, sum);
                callback.callBack(rait);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
