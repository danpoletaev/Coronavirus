package mtnload.project.coronavirus.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mtnload.project.coronavirus.R;
import mtnload.project.coronavirus.adapters.RecyclerViewAdapterSymptoms;

/**
 * A simple {@link Fragment} subclass.
 */
public class SymptomsFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public SymptomsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_symptoms, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.symptoms_recycler_view);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        // specify an adapter (see also next example)
        mAdapter = new RecyclerViewAdapterSymptoms(getContext());
        recyclerView.setAdapter(mAdapter);

        return view;
    }
}
