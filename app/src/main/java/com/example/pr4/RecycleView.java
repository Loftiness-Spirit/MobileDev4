package com.example.pr4;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pr4.databinding.FragmentRecycleViewBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecycleView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecycleView extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private ArrayList<String> mParam1;
    private String mParam2;

    public RecycleView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecycleView.
     */
    // TODO: Rename and change types and number of parameters
    public static RecycleView newInstance(ArrayList<String> param1, String param2) {
        RecycleView fragment = new RecycleView();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getStringArrayList(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentRecycleViewBinding recycleViewBinding = FragmentRecycleViewBinding.inflate(inflater);
        recycleViewBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        CustomAdapter adapter =new CustomAdapter(getActivity(), mParam1);
        recycleViewBinding.recyclerView.setAdapter(adapter);
        return recycleViewBinding.getRoot();
    }
    class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

        private ArrayList<String> items;
        private LayoutInflater inflater;

        CustomAdapter(Context context, ArrayList<String> items){
            this.inflater = LayoutInflater.from(context);
            this.items = items;
        }
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            String item = items.get(position);
            holder.textView.setText(item);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("recycle","item clicked");
                    Toast toast = Toast.makeText(getContext(),"item" + (holder.getAdapterPosition()+1) + "clicked",Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
        class ViewHolder extends RecyclerView.ViewHolder{
            TextView textView;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.username);
            }
        }
    }
}