package com.mfacorp.calcie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static com.google.android.material.snackbar.Snackbar.LENGTH_LONG;

public class bottomSheetDialog extends BottomSheetDialogFragment {

    TextView t1;
    MaterialButton save,close;
    TextInputEditText etSubName;
    DatabaseHelper mDatabaseHelper;
    TextInputLayout layBottomSheet;





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.bottom_sheet_layout, container, false);

       t1=v.findViewById(R.id.textView4);
       close=v.findViewById(R.id.buClose);
       save=v.findViewById(R.id.buSave);
       etSubName=v.findViewById(R.id.etSubName);
       mDatabaseHelper = new DatabaseHelper(getActivity());
       layBottomSheet=v.findViewById(R.id.layBottomSheet);

        Bundle bundle=getArguments();
        assert bundle != null;
        String total=bundle.getString("result");
        t1.setText("Your CIE is "+total);


        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String newEntry = Objects.requireNonNull(etSubName.getText()).toString()+" "+ total;
                if (etSubName.length() == 0) {

                    layBottomSheet.setErrorEnabled(true);
                    layBottomSheet.setError("Cant be empty");

                } else {

                    AddData(newEntry);
                    etSubName.setText("");
                    dismiss();



                }
                InputMethodManager inputMethodManager = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(Objects.requireNonNull(getView()).getWindowToken(), 0);
            }

        });

        close.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dismiss();

            }
        });


        return v;
    


    }

    public void AddData(String newEntry) {
        boolean insertData = mDatabaseHelper.addData(newEntry);

        if (insertData) {
            Snackbar.make(Objects.requireNonNull(getActivity()).findViewById(android.R.id.content),
                    "CIE saved successfully", Snackbar.LENGTH_SHORT).show();
        } else {
            Snackbar.make(Objects.requireNonNull(getActivity()).findViewById(android.R.id.content),
                    "Something went wrong", Snackbar.LENGTH_SHORT).show();
        }
    }
}
