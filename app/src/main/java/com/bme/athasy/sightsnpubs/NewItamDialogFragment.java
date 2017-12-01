package com.bme.athasy.sightsnpubs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Athasy on 2017. 11. 23..
 */

public class NewItamDialogFragment extends AppCompatDialogFragment {
    public static final String TAG = "NewItemDialogFragment";

    private View getContentView() {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_new_item, null);
        return contentView;
    }

    public interface INewItemDialogListener{
        void onItemCreated(SightItem newItem);
    }

    private INewItemDialogListener listener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        FragmentActivity activity = getActivity();
        if(activity instanceof INewItemDialogListener){
            listener = (INewItemDialogListener) activity;
        } else {
            throw new RuntimeException("Activity must implement the INewItemDialogListener interface.");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        return new AlertDialog.Builder(getContext()).setTitle(R.string.new_sight_item)
                .setView(getContentView())
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){

                    }
                }).setNegativeButton(R.string.cancel, null).create();
    }
}
