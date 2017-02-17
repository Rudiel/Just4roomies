package com.gloobe.just4roomies.Actividades.Creators;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;

import com.gloobe.just4roomies.R;

/**
 * Created by rudielavilaperaza on 2/16/17.
 */

public class AlertDialog_Creator implements DialogInterface.OnClickListener {

    private IDialogCreator dialogCreator;

    public void showAlert(Context context, @Nullable String title, @Nullable String message, final IDialogCreator delegate) {

        dialogCreator = delegate;

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setMessage(message)
                .setTitle(title);

        builder.setNeutralButton("Ok", this);

        final android.app.AlertDialog dialog = builder.create();

        dialog.setCancelable(false);

        dialog.show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        dialogCreator.didOK(dialog);
    }
}
