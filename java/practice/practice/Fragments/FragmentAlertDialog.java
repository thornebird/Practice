package practice.practice.Fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import practice.practice.R;

/**
 * Created by ThorneBird on 5/16/2017.
 */

public class FragmentAlertDialog extends DialogFragment {

    private final static String KEY_MESSAGE = "message";
    private final static String KEY_POSITIVE = "positive";
    private final static String KEY_NEGATIVE = "negative";
    private final static String KEY_TITLE = "title";
    private String title;
    private String message;
    private String positiveText;
    private String negativeText;


    public FragmentAlertDialog() {
    }

    public static FragmentAlertDialog newInstance(String title, String message, String positiveText, String negativeText) {
        FragmentAlertDialog fragmentAlertDialog = new FragmentAlertDialog();
        Bundle args = new Bundle();
        args.putString(KEY_MESSAGE, message);
        args.putString(KEY_POSITIVE, positiveText);
        args.putString(KEY_NEGATIVE, negativeText);
        args.putString(KEY_TITLE, title);
        fragmentAlertDialog.setArguments(args);
        return fragmentAlertDialog;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(KEY_TITLE);
            message = getArguments().getString(KEY_MESSAGE);
            positiveText = getArguments().getString(KEY_POSITIVE);
            negativeText = getArguments().getString(KEY_NEGATIVE);
        }
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.fragment_dialog);
        dialog.show();

        TextView tvTitle=(TextView)dialog.findViewById(R.id.tv_title);
        Button btnPositibe=(Button)dialog.findViewById(R.id.btn_positive);
        Button btnNegative = (Button)dialog.findViewById(R.id.btn_negative);


        return dialog;
    }
}
