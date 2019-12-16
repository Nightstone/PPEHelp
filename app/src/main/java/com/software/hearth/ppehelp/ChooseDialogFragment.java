package com.software.hearth.ppehelp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


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
