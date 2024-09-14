package com.example.contactapp;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView contactListView;
    private Button btnAddContact, btnViewContacts;
    private List<Contact> contactList;
    private ContactAdapter contactAdapter;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        contactListView = findViewById(R.id.contactListView);
        btnAddContact = findViewById(R.id.btnAddContact);
        btnViewContacts = findViewById(R.id.btnViewContacts);

        // Add contact button click listener
        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showContactDialog(false, null, -1);
            }
        });

        // View contacts button click listener
        btnViewContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadContacts();
            }
        });

        // List item click listener for updating or deleting
        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact contact = contactList.get(position);
                showContactDialog(true, contact, position);
            }
        });
    }

    // Load contacts from the database
    private void loadContacts() {
        contactList = db.getAllContacts();
        contactAdapter = new ContactAdapter(this, contactList);
        contactListView.setAdapter(contactAdapter);
    }

    // Show a dialog for adding/updating a contact
    private void showContactDialog(final boolean isUpdate, final Contact contact, final int position) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_contact);

        final EditText etName = dialog.findViewById(R.id.etContactName);
        final EditText etPhone = dialog.findViewById(R.id.etContactPhone);
        Button btnSave = dialog.findViewById(R.id.btnSaveContact);
        Button btnDelete = dialog.findViewById(R.id.btnDeleteContact);

        if (isUpdate && contact != null) {
            etName.setText(contact.getName());
            etPhone.setText(contact.getPhone());
            btnDelete.setVisibility(View.VISIBLE);  // Show delete button
        } else {
            btnDelete.setVisibility(View.GONE);  // Hide delete button for adding
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String phone = etPhone.getText().toString();

                if (name.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (isUpdate && contact != null) {
                    contact.setName(name);
                    contact.setPhone(phone);
                    db.updateContact(contact);
                    contactList.set(position, contact);
                } else {
                    Contact newContact = new Contact(0, name, phone);
                    db.addContact(newContact);
                    contactList.add(newContact);
                }

                contactAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUpdate && contact != null) {
                    db.deleteContact(contact.getId());
                    contactList.remove(position);
                    contactAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            }
        });

        // Adjust the dialog dimensions
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        dialog.show();
    }
}

