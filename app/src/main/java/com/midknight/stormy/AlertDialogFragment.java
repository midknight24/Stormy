package com.midknight.stormy;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by midknight on 6/21/16.
 */
public class AlertDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.error_dialog_title)
                .setMessage(R.string.error_dialog_msg)
                .setPositiveButton("OK",null);
        AlertDialog dialog = builder.create();
        return dialog;
    }
}
