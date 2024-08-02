package com.example.atvroom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;

public class NewUserActivity extends AppCompatActivity {

    private EditText editName, editCourse, editAge;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        editName = findViewById(R.id.et_nome);
        editCourse = findViewById(R.id.et_curso);
        editAge = findViewById(R.id.et_idade);
        Button buttonSave = findViewById(R.id.button_save);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        buttonSave.setOnClickListener(view -> {
            String name = editName.getText().toString().trim();
            String course = editCourse.getText().toString().trim();
            String ageString = editAge.getText().toString().trim();

            if (name.isEmpty() || course.isEmpty() || ageString.isEmpty()) {
                Snackbar.make(view, "Preencha todos os campos", Snackbar.LENGTH_SHORT).show();
                return;
            }

            if (!name.matches("[a-zA-Z\\s]+") || !course.matches("[a-zA-Z\\s]+")) {
                Snackbar.make(view, "Nome e curso não podem conter números", Snackbar.LENGTH_SHORT).show();
                return;
            }

            int age;
            try {
                age = Integer.parseInt(ageString);
            } catch (NumberFormatException e) {
                Snackbar.make(view, "Idade inválida", Snackbar.LENGTH_SHORT).show();

                return;
            }

            User user = new User(name, course, age);
            userViewModel.insert(user);

            Intent intent = new Intent(NewUserActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}

