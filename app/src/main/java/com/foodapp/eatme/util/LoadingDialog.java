package com.foodapp.eatme.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.TextView;

import com.foodapp.eatme.R;

public class LoadingDialog {
    Context context;
    Dialog dialog;

    public LoadingDialog(Context context) {
        this.context = context;
    }

    public void showDialog(String title) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView tvTitleDialog = dialog.findViewById(R.id.tv_title_dialog);
        if (title != null) {
            tvTitleDialog.setText(title);
        }
        dialog.create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
    }

    public void hideDialog() {
        dialog.dismiss();
    }
}
