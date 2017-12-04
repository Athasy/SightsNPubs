package com.bme.athasy.sightsnpubs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by Athasy on 2017. 11. 23..
 */

public class NewItemDialogFragment extends AppCompatDialogFragment {
    public static final String TAG = "NewItemDialogFragment";
    private EditText nameEditText;
    private EditText descriptionEditText;
    private Spinner categorySpinner;
    private EditText latitudeEditText;
    private EditText longitudeEditText;

    private View getContentView() {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_new_item, null);

        nameEditText = (EditText) contentView.findViewById(R.id.SightItemNameEditText);
        descriptionEditText = (EditText) contentView.findViewById(R.id.SightItemDescriptionEditText);
        categorySpinner = (Spinner) contentView.findViewById(R.id.SightItemCategorySpinner);
        categorySpinner.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.category_list)));
        latitudeEditText = (EditText) contentView.findViewById(R.id.SightItemLatitude);
        longitudeEditText = (EditText) contentView.findViewById(R.id.SightItemLongitude);

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
                        if(isValid()){
                            listener.onItemCreated(getSightItem());
                        }
                    }
                }).setNegativeButton(R.string.cancel, null).create();
    }

    private boolean isValid(){
        if(nameEditText.getText().length() > 0 &&
                Float.parseFloat(latitudeEditText.getText().toString()) > (float)-90.0 &&
                Float.parseFloat(latitudeEditText.getText().toString()) < (float)90.0 &&
                Float.parseFloat(longitudeEditText.getText().toString()) > (float)-180.0 &&
                Float.parseFloat(longitudeEditText.getText().toString()) < (float)180.0){
            return true;
        }
        return false;
    }

    private SightItem getSightItem(){
        SightItem sightItem = new SightItem();

        sightItem.name = nameEditText.getText().toString();
        sightItem.description = descriptionEditText.getText().toString();
        try {
        sightItem.latitudeCoord = Float.parseFloat(latitudeEditText.getText().toString());
        sightItem.longitudeCoord = Float.parseFloat(longitudeEditText.getText().toString());
        } catch (NumberFormatException e) {
            sightItem.latitudeCoord = 0;
            sightItem.longitudeCoord = 0;
        }

        sightItem.category = SightItem.Category.getByOrdinal(categorySpinner.getSelectedItemPosition());
        sightItem.save();
        return sightItem;
    }
}
