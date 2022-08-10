package com.wallace.viewpagerandroid;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;

import com.wallace.viewpagerandroid.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initRecyclerPager();
    }

    private void initRecyclerPager() {
        ArrayList<String> listName = new ArrayList<>();
        listName.add("AAA");
        listName.add("BBB");
        listName.add("CCC");
        listName.add("DDD");
        listName.add("EEE");

        ListNameAdapter adapter = new ListNameAdapter(listName);
        binding.rcvProposalPager.setAdapter(adapter);
        binding.rcvProposalPager.setLayoutManager(new LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
        ));

        binding.rcvProposalPager.addItemDecoration(
                new PageIndicatorDecoration(
                        ContextCompat.getColor(this, R.color.teal_200),
                        ContextCompat.getColor(this, R.color.purple_500)
                )
        );
        new PagerSnapHelper().attachToRecyclerView(binding.rcvProposalPager);
    }
}