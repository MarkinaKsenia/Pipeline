package com.markinaksenia.pipeline;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class CategoryFragment extends Fragment {

    View myFragment;
    RecyclerView listCategory;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Category, CategoryViewHolder> adapter;

    FirebaseDatabase database;
    DatabaseReference categories;


    public static CategoryFragment newInstance(){
        CategoryFragment categoryFragment = new CategoryFragment();
        return categoryFragment;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance();
        categories = database.getReference("Category");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_category,container,false);

        listCategory = (RecyclerView) myFragment.findViewById(R.id.listCategory);
        listCategory.setHasFixedSize(true);
        //      layoutManager  = new LinearLayoutManager(container.getContext())     ;
        //      listCategory.setLayoutManager(layoutManager);

        listCategory.setLayoutManager(new GridLayoutManager(container.getContext(),1));

        loadcategories();

        return myFragment;
    }

    private void loadcategories() {

        adapter =  new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(Category.class, R.layout.category_cardview, CategoryViewHolder.class, categories) {
            @Override
            protected void populateViewHolder(CategoryViewHolder categoryViewHolder, final Category category, int i) {
                categoryViewHolder.category_name.setText(category.getName());
                Picasso.with(getActivity()).load(category.getImage()).into(categoryViewHolder.category_image);

                categoryViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //    Toast.makeText(getActivity(),String.format("%s|%s",adapter.getRef(position).getKey(),category.getName()),Toast.LENGTH_SHORT).show();
                        Intent startGame = new Intent(getActivity(), Start.class);
                        String a = adapter.getRef(position).getKey();
                        if ( a.equals("Легкий уровень")){
                            startGame.putExtra("inform", "Для прохождения будет достаточно общих знаний и представлений о нефтегазовом деле");
                        }
                        if(a.equals("Средний уровень")){
                            startGame.putExtra("inform", "Для прохождения необходимы знания из дисциплины Основы нефтегазового дела");
                        }
                        if (a.equals("Тяжелый уровень")){
                            startGame.putExtra("inform", "Вопросы,в решении которых пригодится при прохождении игры. Нетрудно ответить, если применить свои знания и логику");
                        }
                        Common.categoryId = adapter.getRef(position).getKey();
                        startActivity(startGame);

                    }
                });
            }
        };

        adapter.notifyDataSetChanged();
        listCategory.setAdapter(adapter);
    }
}
