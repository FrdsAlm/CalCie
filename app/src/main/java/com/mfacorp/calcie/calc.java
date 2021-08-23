package com.mfacorp.calcie;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.ramotion.fluidslider.FluidSlider;
import org.jetbrains.annotations.NotNull;
import java.util.Objects;
import androidx.fragment.app.Fragment;
import kotlin.Unit;

import static android.content.Context.INPUT_METHOD_SERVICE;



/**
 * A simple {@link Fragment} subclass.
 */
public class calc extends Fragment {


     FluidSlider sliderQ1,sliderQ2,sliderQ3,sliderQ4,sliderAss;
     TextView tvQ1,tvQ2,tvQ3,tvQ4,tvAss,tvT1,tvT2;
     EditText etT1,etT2;
     TextInputLayout layoutT1,layoutT2;
     MaterialButton buRefresh,fab;
    private Bundle savedState = null;






    public calc() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View v=inflater.inflate(R.layout.fragment_calc, container, false);





        tvQ1 =v.findViewById(R.id.tvQ1);
        tvQ2=v. findViewById(R.id.tvQ2);
        tvQ3 =v. findViewById(R.id.tvQ3);
        tvQ4 =v. findViewById(R.id.tvQ4);
        tvAss =v. findViewById(R.id.tvAss);
        tvT1 =v. findViewById(R.id.tvTest1);
        tvT2 =v. findViewById(R.id.tvTest2);
        sliderAss=v.findViewById(R.id.sliderAss);
        sliderQ1=v.findViewById(R.id.sliderQ1);
        sliderQ2=v.findViewById(R.id.sliderQ2);
        sliderQ3=v.findViewById(R.id.sliderQ3);
        sliderQ4=v.findViewById(R.id.sliderQ4);
        fab=v.findViewById(R.id.buSave);
        buRefresh=v.findViewById(R.id.buRef);
        etT1=v.findViewById(R.id.etTest1);
        etT2=v.findViewById(R.id.etTest2);
        layoutT1=v.findViewById(R.id.layoutT1);
        layoutT2=v.findViewById(R.id.layoutT2);








        //code for test 1 edittext

        etT1.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable mEdit)
            {
                String text = mEdit.toString();
                int marks,credits;
                if (text.length()==0)
                { tvT1.setText("Empty");
                    layoutT1.setErrorEnabled(true);
                    layoutT1.setError("T1 marks empty");

                }

                else
                {
                    marks=Integer.parseInt(text);
                    if(marks<=50)
                    {
                        layoutT1.setErrorEnabled(false);
                        credits= (int) Math.ceil(marks/2.94);
                        tvT1.setText(String.valueOf(credits +" Credits"));
                    }
                    else
                    {
                        tvT1.setText("Error");
                        layoutT1.setError("T1 marks > 50");

                    }
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after){}

            public void onTextChanged(CharSequence s, int start, int before, int count){

                if(etT1.getText().toString().length()==2)     //size as per your requirement
                {
                    etT2.requestFocus();
                }
            }
        });

        //code for test 2 edittext

        etT2.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable mEdit)
            {
                String text = mEdit.toString();
                int marks,credits;
                if (text.length()==0)
                { tvT2.setText("Empty");
                    layoutT2.setErrorEnabled(true);
                    layoutT2.setError("T1 marks empty");

                }

                else
                {
                    marks=Integer.parseInt(text);
                    if(marks<=50)
                    {
                        layoutT2.setErrorEnabled(false);
                        credits= (int) Math.ceil(marks/2.94);
                        tvT2.setText(String.valueOf(credits +" Credits"));
                    }
                    else
                    {
                        tvT2.setText("Error");
                        layoutT2.setError("T1 marks > 50");

                    }
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after){}

            public void onTextChanged(CharSequence s, int start, int before, int count){

                if(etT2.getText().toString().length()==2)
                {

                    InputMethodManager inputMethodManager = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(Objects.requireNonNull(getView()).getWindowToken(), 0);
                }
            }
        });


        //first slider for quiz 1

        final int maxq1 = 15;
        final int minq1 = 0;
        final int totalq1 = maxq1 - minq1;


        sliderQ1.setBeginTrackingListener(() -> {
            tvQ1.setVisibility(View.INVISIBLE);
            return Unit.INSTANCE;
        });

        sliderQ1.setEndTrackingListener(() -> {
            tvQ1.setVisibility(View.VISIBLE);
            return Unit.INSTANCE;
        });

        sliderQ1.setPositionListener(pos -> {
            final String value = String.valueOf( (int)(minq1 + totalq1 * pos) );
            sliderQ1.setBubbleText(value);
            float marks =Integer.parseInt(value);
            int credits = (int) Math.ceil(marks/5);
            tvQ1.setText("Q1 \n"+String.valueOf(credits)+ " Credits");
            return Unit.INSTANCE;

        });

        sliderQ1.setPosition(0);
        sliderQ1.setStartText(String.valueOf(minq1));
        sliderQ1.setEndText(String.valueOf(maxq1));
        tvQ1.setText("Quiz 1");


        //for slider 2

        final int maxq2 = 15;
        final int minq2 = 0;
        final int totalq2 = maxq2 - minq2;


        sliderQ2.setBeginTrackingListener(() -> {
            tvQ2.setVisibility(View.INVISIBLE);
            return Unit.INSTANCE;
        });

        sliderQ2.setEndTrackingListener(() -> {
            tvQ2.setVisibility(View.VISIBLE);
            return Unit.INSTANCE;
        });

        sliderQ2.setPositionListener(pos -> {
            final String value = String.valueOf( (int)(minq2 + totalq2 * pos));
            sliderQ2.setBubbleText(value);
            float marks =Integer.parseInt(value);
            int credits = (int) Math.ceil(marks/5);
            tvQ2.setText("Q2 \n"+String.valueOf(credits)+ " Credits");
            return Unit.INSTANCE;

        });

        sliderQ2.setPosition(0);
        sliderQ2.setStartText(String.valueOf(minq2));
        sliderQ2.setEndText(String.valueOf(maxq2));
        tvQ2.setText("Quiz 2");

        //for slider 3

        final int maxq3 = 15;
        final int minq3 = 0;
        final int totalq3 = maxq3 - minq3;


        sliderQ3.setBeginTrackingListener(() -> {
            tvQ3.setVisibility(View.INVISIBLE);
            return Unit.INSTANCE;
        });

        sliderQ3.setEndTrackingListener(() -> {
            tvQ3.setVisibility(View.VISIBLE);
            return Unit.INSTANCE;
        });

        sliderQ3.setPositionListener(pos -> {
            final String value = String.valueOf( (int)(minq3 + totalq3 * pos) );
            sliderQ3.setBubbleText(value);
            float marks =Integer.parseInt(value);
            int credits = (int) Math.ceil(marks/5);
            tvQ3.setText("Q3 \n"+String.valueOf(credits)+ " Credits");
            return Unit.INSTANCE;

        });

        sliderQ3.setPosition(0);
        sliderQ3.setStartText(String.valueOf(minq2));
        sliderQ3.setEndText(String.valueOf(maxq2));
        tvQ3.setText("Quiz 3");

        //slider slider q4

        final int maxq4 = 15;
        final int minq4 = 0;
        final int totalq4 = maxq4 - minq4;


        sliderQ4.setBeginTrackingListener(() -> {
            tvQ4.setVisibility(View.INVISIBLE);
            return Unit.INSTANCE;
        });

        sliderQ4.setEndTrackingListener(() -> {
            tvQ4.setVisibility(View.VISIBLE);
            return Unit.INSTANCE;
        });

        sliderQ4.setPositionListener(pos -> {
            final String value = String.valueOf( (int)(minq4 + totalq4 * pos) );
            sliderQ4.setBubbleText(value);
            float marks =Integer.parseInt(value);
            int credits = (int) Math.ceil(marks/5);
            tvQ4.setText("Q4 \n"+String.valueOf(credits)+ " Credits");
            return Unit.INSTANCE;

        });

        sliderQ4.setPosition(0);
        sliderQ4.setStartText(String.valueOf(minq2));
        sliderQ4.setEndText(String.valueOf(maxq2));
        tvQ4.setText("Quiz 4");

        //slider assignment

        final int maxq5 = 4;
        final int minq5 = 0;
        final int totalq5 = maxq5 - minq5;


        sliderAss.setBeginTrackingListener(() -> {
            tvAss.setVisibility(View.INVISIBLE);
            return Unit.INSTANCE;
        });

        sliderAss.setEndTrackingListener(() -> {
            tvAss.setVisibility(View.VISIBLE);
            return Unit.INSTANCE;
        });

        sliderAss.setPositionListener(pos -> {
            final String value = String.valueOf( (int)(minq5 + totalq5 * pos) );
            sliderAss.setBubbleText(value);
            tvAss.setText("Assignment \n"+String.valueOf(value)+ " Credits");
            return Unit.INSTANCE;

        });

        sliderAss.setPosition(0);
        sliderAss.setStartText(String.valueOf(minq5));
        sliderAss.setEndText(String.valueOf(maxq5));
        tvAss.setText("Assignment");

        //end of all views now calculation of buttons


        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String q1=sliderQ1.getBubbleText();
                String q2=sliderQ2.getBubbleText();
                String q3=sliderQ3.getBubbleText();
                String q4=sliderQ4.getBubbleText();
                String Ass=sliderAss.getBubbleText();
                String t1 = etT1.getText().toString();
                String t2=etT2.getText().toString();
                float tm1,tm2;
                int t1cr,t2cr,total;

                float qm1=Float.parseFloat(q1);
                int q1cr= (int) Math.ceil(qm1/5);
                float qm2=Float.parseFloat(q2);
                int q2cr= (int) Math.ceil(qm2/5);
                float qm3=Float.parseFloat(q3);
                int q3cr= (int) Math.ceil(qm3/5);
                float qm4=Float.parseFloat(q4);
                int q4cr= (int) Math.ceil(qm4/5);
                int AssM=Integer.parseInt(Ass);

                if(t1.isEmpty())

                { layoutT1.setError("T1 marks empty!!");
                    t1cr=0;
                }
                else
                {
                    tm1=Integer.parseInt(t1);
                    if(tm1>50)
                    {
                        layoutT1.setError("T1 marks >50");
                        t1cr=0;
                    }
                    else
                    {
                        tm1=Float.parseFloat(t1);
                        t1cr= (int) Math.ceil(tm1*.34);
                    }
                }


                if(t2.isEmpty())

                { layoutT2.setError("T2 marks empty!!");
                    t2cr=0;
                }
                else
                {
                    tm2=Float.parseFloat(t2);
                    if(tm2>50)
                    {
                        layoutT2.setError("T2 marks >50");
                        t2cr=0;
                    }
                    else
                    {  tm2=Float.parseFloat(t2);
                        t2cr= (int) Math.ceil(tm2*.34);
                    }
                }

                total=q1cr+q2cr+q3cr+q4cr+t1cr+t2cr+AssM;
                bottomSheetDialog bottomsheet=new bottomSheetDialog();
                bottomsheet.show(getFragmentManager(),"bottom_sheet");
                Bundle bundle = new Bundle();
                String result=String.valueOf(total);
                bundle.putString("result",result);
                bottomsheet.setArguments(bundle);

            }
        });

        buRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defaultState();
            }
        });

        return v;




    }

    private void defaultState() {

        sliderAss.setPosition(0);
        sliderQ1.setPosition(0);
        sliderQ2.setPosition(0);
        sliderQ3.setPosition(0);
        sliderQ4.setPosition(0);
        etT1.setText("");
        etT2.setText("");
        layoutT1.setErrorEnabled(false);
        layoutT2.setErrorEnabled(false);
        tvT1.setText("Test 1");
        tvT2.setText("Test 2");
        tvAss.setText("Assignment");
        tvQ1.setText("Quiz 1");
        tvQ2.setText("Quiz 2");
        tvQ3.setText("Quiz 3");
        tvQ4.setText("Quiz 4");
        etT1.requestFocus();
    }
}
