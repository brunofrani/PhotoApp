package com.example.brunofrani.photoapp.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.brunofrani.photoapp.R;
import com.example.brunofrani.photoapp.classes.Friends;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by Bruno Frani on 01/09/2017.
 */

public class FriendsAdapters extends RecyclerView.Adapter<Friends_View_Holder> {




    Context context;
    ArrayList<Friends> friendList;

    public FriendsAdapters(Context context, ArrayList<Friends> friendList) {
        this.context = context;
        this.friendList = friendList;

    }

    @Override
    public Friends_View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_row, parent, false);
       Friends_View_Holder  holder = new Friends_View_Holder(v);
        return holder;

    }


    @Override
    public void onBindViewHolder(Friends_View_Holder holder, final int position) {

        holder.textViewFriends.setText(friendList.get(position).getEmail());
       


    }


    @Override
    public int getItemCount() {
        return friendList.size();
    }
}
