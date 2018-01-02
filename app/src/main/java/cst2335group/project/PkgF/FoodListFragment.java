package cst2335group.project.PkgF;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import cst2335group.project.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodListFragment extends Fragment {


    public FoodListFragment() {
        // Required empty public constructor
    }


    ListView listView;
    DatabaseHandler handler;
    FoodListAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_food_list, container, false);

        listView = root.findViewById(R.id.food_items_lv);
        handler = new DatabaseHandler(getActivity());
        adapter = new FoodListAdapter(getActivity(), handler.getAllFoods());
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {


                final CharSequence options[] = new CharSequence[]{"EDIT", "DELETE"};

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Select Action");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (options[which].equals("EDIT")) {

                            Intent intent = new Intent(getActivity() , AddFoodActivity.class);
                            intent.putExtra("Id"  ,(int) adapter.getItemId(i));

                            startActivity(intent);

                        } else if (options[which].equals("DELETE")) {
                            handler.deleteFoodItem((FoodModel) adapter.getItem(i));
                            refreshList();
                        }
                    }
                });
                builder.show();
            }
        });

        return root;
    }

    public void refreshList() {
        adapter = new FoodListAdapter(getActivity(), handler.getAllFoods());
        listView.setAdapter(adapter);
    }


}
