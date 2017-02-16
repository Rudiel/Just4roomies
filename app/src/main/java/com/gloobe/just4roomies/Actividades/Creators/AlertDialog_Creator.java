package com.gloobe.just4roomies.Actividades.Creators;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;

import com.gloobe.just4roomies.R;

/**
 * Created by rudielavilaperaza on 2/16/17.
 */

public class AlertDialog_Creator {

    public void showAlert(Context context, @Nullable String title, @Nullable String message, final IDialogCreator delegate) {

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setMessage(message)
                .setTitle(title);

        final android.app.AlertDialog dialog = builder.create();
        dialog.setCancelable(false);


        builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                delegate.didOK(dialog);
            }
        });
        dialog.show();
    }
}
