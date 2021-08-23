package com.mfacorp.calcie;

import static com.google.android.material.snackbar.Snackbar.LENGTH_LONG;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;


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

        buMail.setOnClickListener(v1 -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri data = Uri.parse("mailto:frds717@gmail.com?subject=" +"Feedback for CalCie app");
            intent.setData(data);
            startActivity(intent);
        });

        insta.setOnClickListener(v12 -> {
            Uri uri = Uri.parse("https://www.instagram.com/firdousalam/"); // missing 'http://' will cause crashed
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

        telegram.setOnClickListener(v13 -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.telegram.me/FrdsAlm"));
            startActivity(intent);
        });

        linkedin.setOnClickListener(v14 -> {
            Uri uri = Uri.parse("https://www.linkedin.com/in/firdous-alam/"); // missing 'http://' will cause crashed
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

        twitter.setOnClickListener(v15 -> {
            Uri uri = Uri.parse("https://twitter.com/FrdsAlm"); // missing 'http://' will cause crashed
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

        donate.setOnClickListener(v16 -> {
            Intent i;
            PackageManager manager = requireActivity().getPackageManager();
            try {
                i = manager.getLaunchIntentForPackage("com.google.android.apps.nbu.paisa.user");
                if (i == null)
                {  Snackbar.make(requireActivity().findViewById(android.R.id.content),
                        "You don't have google pay :'(", LENGTH_LONG).show();

                       throw new PackageManager.NameNotFoundException();}
                i.addCategory(Intent.CATEGORY_LAUNCHER);
                startActivity(i);
            } catch (PackageManager.NameNotFoundException ignored) {

            }

            ClipboardManager clipboard = (ClipboardManager) requireActivity().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("phone_no","firdousa@fbl");
            clipboard.setPrimaryClip(clip);

            Toast.makeText(requireActivity().getApplicationContext(),"UPI donation address copied in clipboard",Toast.LENGTH_SHORT).show();
        });

        return v;
    }

}
