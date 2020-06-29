package mtnload.project.coronavirus.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mtnload.project.coronavirus.R;
import mtnload.project.coronavirus.adapters.RecyclerViewAdapterAdvice;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdviceFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;

    public AdviceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_advice, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.advice_recycler_view);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new RecyclerViewAdapterAdvice(getContext());
        recyclerView.setAdapter(mAdapter);
        return view;
    }
}
