package com.example.apkmelali_test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class EditTiketBus_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate dialog layout
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_edit_quantity, null);

        // Initialize views from dialog layout
        EditText quantityEditText = dialogView.findViewById(R.id.inputQuantity);
        Button saveButton = dialogView.findViewById(R.id.saveButton);
        Button cancelButton = dialogView.findViewById(R.id.cancelButton);

        // Create and show dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.show();

        // Set click listeners for buttons
        saveButton.setOnClickListener(v -> {
            // Handle saving new quantity
            String newQuantity = quantityEditText.getText().toString();
            // TODO: Implement saving logic
            dialog.dismiss();
        });

        cancelButton.setOnClickListener(v -> {
            dialog.dismiss();
        });
    }
}
