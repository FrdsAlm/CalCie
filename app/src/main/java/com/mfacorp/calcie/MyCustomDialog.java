package com.mfacorp.calcie;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.material.snackbar.Snackbar;
import org.jetbrains.annotations.NotNull;
import java.util.Objects;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import static android.content.Context.INPUT_METHOD_SERVICE;
import static com.google.android.material.snackbar.Snackbar.LENGTH_LONG;

public class MyCustomDialog extends DialogFragment {

    EditText editText;
    Button buSaveAgain,buDelete;
    DatabaseHelper mDatabaseHelper;
    private String selectedName;
    private int selectedID;


    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.dialog_my_custom, container, false);

        editText = v.findViewById(R.id.etSaveAgain);
        buDelete = v.findViewById(R.id.buDelete);
        buSaveAgain = v.findViewById(R.id.buSaveAgain);
        mDatabaseHelper = new DatabaseHelper(getActivity());

        Bundle bundle=getArguments();
        assert bundle != null;
        selectedID=bundle.getInt("itemID");
        selectedName=bundle.getString("name");
        editText.setText(selectedName);
        editText.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(INPUT_METHOD_SERVICE);


        buSaveAgain.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                String item = editText.getText().toString();
                if(!item.equals("")){
                    mDatabaseHelper.updateName(item,selectedID,selectedName);
                }else{
                    Snackbar.make(Objects.requireNonNull(getActivity()).findViewById(android.R.id.content),
                            "You must enter something", LENGTH_LONG).show();
                }

                inputMethodManager.hideSoftInputFromWindow(Objects.requireNonNull(getView()).getWindowToken(), 0);
                Snackbar.make(Objects.requireNonNull(getActivity()).findViewById(android.R.id.content),
                        "Item updated Pull down or click refresh button to refresh the List", LENGTH_LONG).show();
                dismiss();


            }
        });

        buDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteName(selectedID,selectedName);
                editText.setText("");
                inputMethodManager.hideSoftInputFromWindow(Objects.requireNonNull(getView()).getWindowToken(), 0);
                Snackbar.make(Objects.requireNonNull(getActivity()).findViewById(android.R.id.content),
                        "Item deleted pull down or click refresh button to refresh the List", LENGTH_LONG).show();
                dismiss();
            }
        });




        return v;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Objects.requireNonNull(Objects.requireNonNull(getDialog()).getWindow()).setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }


}
