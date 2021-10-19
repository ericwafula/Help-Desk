package com.moringaschool.helpdesk.ui.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.moringaschool.helpdesk.R;

public class PostQuestionDialog extends AppCompatDialogFragment {
    private PostQuestionDialogListener listener;
    EditText questionTitle;
    EditText questionBody;
    Button postQuestionButton;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogue_post_question, null);

        builder.setView(view)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        questionTitle = view.findViewById(R.id.question_title_input);
        questionBody = view.findViewById(R.id.question_body_input);
        postQuestionButton = view.findViewById(R.id.post_question_button);

        postQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = questionTitle.getText().toString().trim();
                String body = questionBody.getText().toString().trim();
                listener.applyQuestion(title, body);

                getDialog().dismiss();
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (PostQuestionDialogListener) getTargetFragment();
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement PostQuestionDialog Listener");
        }
    }

    public interface PostQuestionDialogListener{
        void applyQuestion(String title, String body);
    }
}
