





















































































































































































































































































































































































































































































































































































































































































































/**
 * File: UserUpdate.java
 * Purpose: Handle the view of user udpate
 */

package com.mathheals.meajuda.view.users;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mathheals.meajuda.R;
import com.mathheals.meajuda.model.User;
import com.mathheals.meajuda.presenter.UserPresenter;

public class UserUpdate extends Fragment implements View.OnClickListener {

    private EditText firstNameField = null;
    private EditText lastNameField = null;
    private EditText mailField = null;
    private EditText mailConfirmationField = null;
    private EditText usernameField = null;
    private EditText passwordField = null;
    private EditText passwordConfirmField = null;

    private String firstName = "";
    private String lastName = "";
    private String username = "";
    private String mail = "";
    private String password = "";
    private String passwordConfirmation = "";
    private String mailConfirmation = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_user_update, container, false);

        settingEditText(view);

        Button updateButton = (Button) view.findViewById(R.id.updateButton);

        updateButton.setOnClickListener(this);

        return view;
    }

    private void settingEditText(View view){

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences
                (getContext());

        this.firstNameField = (EditText) view.findViewById(R.id.firstNameField);
        this.firstNameField.setText(session.getString(getResources().getString(R.string.key_name)
                , ""));

        this.lastNameField = (EditText) view.findViewById(R.id.lastNameField);
        this.lastNameField.setText(session.getString(getResources().getString
                        (R.string.key_last_name),""));

        this.mailField = (EditText) view.findViewById(R.id.mailField);
        this.mailField.setText(session.getString(getResources().getString(R.string.key_email),""));

        this.usernameField = (EditText) view.findViewById(R.id.loginField);
        this.usernameField.setText(session.getString(getResources().getString(R.string.key_login)
                ,""));

        this.mailConfirmationField = (EditText) view.findViewById(R.id.confirmMailField);
        this.passwordConfirmField = (EditText) view.findViewById(R.id.confirmPasswordField);
        this.passwordField = (EditText) view.findViewById(R.id.passwordField);
    }

    private void getTextTyped(){
        this.firstName = firstNameField.getText().toString();
        this.lastName = lastNameField.getText().toString();
        this.username = usernameField.getText().toString();
        this.mail = mailField.getText().toString();
        this.mailConfirmation = mailConfirmationField.getText().toString();
        this.password = passwordField.getText().toString();
        this.passwordConfirmation = passwordConfirmField.getText().toString();
    }

    @Override
    public void onClick(View v){
        getTextTyped();

        UserPresenter userPresenter = UserPresenter.getInstance(getContext());

        String message = userPresenter.updateUser(this.firstName, this.lastName, this.username,
                this.mail, this.mailConfirmation, this.password, this.passwordConfirmation,
                getContext());

        switch(message){
            case User.USER_SUCCESSFULLY_UPDATED:
                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG)
                        .show();

                getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();

                break;
            case User.FIRST_NAME_CANT_BE_EMPTY_NAME:
                firstNameField.requestFocus();
                firstNameField.setError(message);
                break;
            case User.FIRST_NAME_CANT_BE_HIGHER_THAN_50:
                firstNameField.requestFocus();
                firstNameField.setError(message);
                break;
            case User.LAST_NAME_CANT_BE_EMPTY_NAME:
                lastNameField.requestFocus();
                lastNameField.setError(message);
                break;
            case User.LAST_NAME_CANT_BE_HIGHER_THAN_50:
                lastNameField.requestFocus();
                lastNameField.setError(message);
                break;
            case User.EMAIL_CANT_BE_EMPTY_EMAIL:
                mailField.requestFocus();
                mailField.setError(message);
                break;
            case User.EMAIL_CANT_BE_HIGHER_THAN_150:
                mailField.requestFocus();
                mailField.setError(message);
                break;
            case User.INVALID_EMAIL:
                mailField.requestFocus();
                mailField.setError(message);
                break;
            case User.EMAIL_ARE_NOT_EQUALS:
                mailField.requestFocus();
                mailField.setError(message);
                break;
            case User.USERNAME_CANT_BE_EMPTY_USERNAME:
                usernameField.requestFocus();
                usernameField.setError(message);
                break;
            case User.USERNAME_CANT_BE_HIGHER_THAN_100:
                usernameField.requestFocus();
                usernameField.setError(message);
                break;
            case User.PASSWORD_CANT_BE_EMPTY_PASSWORD:
                passwordField.requestFocus();
                passwordField.setError(message);
                break;
            case User.PASSWORD_CANT_BE_LESS_THAN_6:
                passwordField.requestFocus();
                passwordField.setError(message);
                break;
            case User.PASSWORD_ARE_NOT_EQUALS:
                passwordField.requestFocus();
                passwordField.setError(message);
                break;
            case User.USERNAME_EXISTENT:
                usernameField.requestFocus();
                usernameField.setError(message);
                break;
            case User.CONFIRM_PASSWORD_CANT_BE_EMPTY:
                passwordConfirmField.requestFocus();
                passwordConfirmField.setError(message);
                break;
            case User.EMAIL_CONFIRMATION_CANT_BE_EMPTY:
                mailConfirmationField.requestFocus();
                mailConfirmationField.setError(message);
                break;
            default:
                break;
        }
    }
}
