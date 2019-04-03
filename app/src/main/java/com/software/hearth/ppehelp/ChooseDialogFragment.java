package com.software.hearth.ppehelp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ChooseDialogFragment extends DialogFragment {

    ChooseDialogFragmentInterface dataInterface;

    public void setDataInterface(ChooseDialogFragmentInterface dataInterface) {
        this.dataInterface = dataInterface;
    }

    public ChooseDialogFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final String[] arrayOfChooses = {"Julien", "Jean", "Yves"};
        builder.setTitle("Choisir un prénom")
                .setItems(arrayOfChooses, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dataInterface.onChooseDone(arrayOfChooses[which]);
                    }
                });
        return builder.create();
    }


    public interface ChooseDialogFragmentInterface {
        void onChooseDone(String choose);
    }
}
