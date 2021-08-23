package com.mfacorp.calcie;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static android.widget.Toast.LENGTH_SHORT;
import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.google.android.material.snackbar.Snackbar.LENGTH_LONG;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class save extends Fragment {

    MaterialButton buView,buSave;
    TextInputLayout LayoutEtSave;
    TextInputEditText etSave;
    DatabaseHelper mDatabaseHelper;
    ListView mListView;
    SwipeRefreshLayout swipeRefreshLayout;



    public save() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragmentsave, container, false);
        super.onCreate(savedInstanceState);
        buView=v.findViewById(R.id.buView);
        buSave=v.findViewById(R.id.buSave);
        LayoutEtSave=v.findViewById(R.id.LayoutSaveEt);
        etSave=v.findViewById(R.id.etSave);
        mDatabaseHelper = new DatabaseHelper(getActivity());
        mListView =v.findViewById(R.id.mListView);
        swipeRefreshLayout=v.findViewById(R.id.fragmentsave);
        populateListView();


        swipeRefreshLayout.setOnRefreshListener(() -> {
            populateListView();
            new Handler().postDelayed(() -> swipeRefreshLayout.setRefreshing(false),500);
        });






        buSave.setOnClickListener(v12 -> {

            String newEntry = Objects.requireNonNull(etSave.getText()).toString();
            if (etSave.length() != 0) {
                AddData(newEntry);

                etSave.setText("");
            } else {
                Snackbar.make(requireActivity().findViewById(android.R.id.content),
                        "Nothing is there to save", LENGTH_LONG).show();
            }
            InputMethodManager inputMethodManager = (InputMethodManager) requireActivity().getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(requireView().getWindowToken(), 0);
            populateListView();
        });

        buView.setOnClickListener(v1 -> populateListView());




        return v;
    }

    public void AddData(String newEntry) {
        boolean insertData = mDatabaseHelper.addData(newEntry);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }



    public void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

        //get the data and append to a list
        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            listData.add(data.getString(1));
        }
        //create the list adapter and set the adapter

        ArrayAdapter<String> adapter=new ArrayAdapter<>(requireActivity(),android.R.layout.simple_list_item_1,listData);
        mListView.setAdapter(adapter);


        //set an onItemClickListener to the ListView
        mListView.setOnItemClickListener((adapterView, view, i, l) -> {
            String name = adapterView.getItemAtPosition(i).toString();
            Log.d(TAG, "onItemClick: You Clicked on " + name);

            Cursor data1 = mDatabaseHelper.getItemID(name); //get the id associated with that name
            int itemID = -1;
            while(data1.moveToNext()){
                itemID = data1.getInt(0);
            }
            if(itemID > -1){
                Log.d(TAG, "onItemClick: The ID is: " + itemID);
                MyCustomDialog dialog = new MyCustomDialog();
                assert getFragmentManager() != null;
                dialog.show(getFragmentManager(),"MyCustomDialog");
                Bundle bundle = new Bundle();
                bundle.putInt("itemID", itemID);
                bundle.putString("name", name);
                dialog.setArguments(bundle);

            }
            else{
                toastMessage("No ID associated with that name");
            }
        });
    }



    private void toastMessage(String message){
        Toast.makeText(getActivity(),message, LENGTH_SHORT).show();
    }



}












