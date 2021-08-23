package com.mfacorp.calcie;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import static com.google.android.material.snackbar.Snackbar.LENGTH_LONG;


/**
 * A simple {@link Fragment} subclass.
 */
public class about extends Fragment {

    MaterialButton buMail,insta,telegram,linkedin,donate,twitter;


    public about() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_about, container, false);

        insta=v.findViewById(R.id.insta);
        telegram=v.findViewById(R.id.telegarm);
        linkedin=v.findViewById(R.id.linkedin);
        donate=v.findViewById(R.id.donate);
        buMail=v.findViewById(R.id.buMail);
        twitter=v.findViewById(R.id.twitter);

        buMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:frds717@gmail.com?subject=" +"Mail from CalCie app");
                intent.setData(data);
                startActivity(intent);
            }
        });

        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.instagram.com/firdousalam/"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        telegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.telegram.me/firous95"));
                startActivity(intent);
            }
        });

        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.linkedin.com/in/firdous-alam/"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://twitter.com/FrdsAlm"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                PackageManager manager = getActivity().getPackageManager();
                try {
                    i = manager.getLaunchIntentForPackage("com.google.android.apps.nbu.paisa.user");
                    if (i == null)
                    {  Snackbar.make(requireActivity().findViewById(android.R.id.content),
                            "You don't have google pay :'(", LENGTH_LONG).show();

                           throw new PackageManager.NameNotFoundException();}
                    i.addCategory(Intent.CATEGORY_LAUNCHER);
                    startActivity(i);
                } catch (PackageManager.NameNotFoundException e) {

                }


                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("phone_no","frds717@okhdfcbank");
                clipboard.setPrimaryClip(clip);

                Toast.makeText(getActivity().getApplicationContext(),"UPI donation address copied in clipboard",Toast.LENGTH_SHORT).show();
            }
        });





        return v;
    }

}
