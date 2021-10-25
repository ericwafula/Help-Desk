package com.moringaschool.helpdesk.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.moringaschool.helpdesk.R;

public class PostQuestionDialogSheet extends BottomSheetDialogFragment {
    private BottomSheetListener mListener;
    String title;
    String body;
    String category;
    String language;
    String screenshot;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_dialogue_post_question, container, false);
        EditText questionTitle = view.findViewById(R.id.question_title_input);
        EditText questionBody = view.findViewById(R.id.question_body_input);
        Spinner questionCategory = view.findViewById(R.id.spinner);
        Spinner languageCategory = view.findViewById(R.id.language);
        Button submit = view.findViewById(R.id.post_question_button);
        TextView dismiss = view.findViewById(R.id.dismiss);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.category, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> languageAdapter = ArrayAdapter.createFromResource(getContext(), R.array.language, android.R.layout.simple_spinner_item);
        screenshot = "screenshot";

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        questionCategory.setAdapter(adapter);
        languageCategory.setAdapter(languageAdapter);

        questionCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        languageCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                language = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = questionTitle.getText().toString();
                body = questionBody.getText().toString();
                mListener.onFloatingActionButtonSubmit(category, title, body, language, screenshot);
                dismiss();
            }
        });
        return view;
    }

    public interface BottomSheetListener{
        void onFloatingActionButtonSubmit(String category, String title, String body, String language, String screenshot);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (BottomSheetListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement BottomSheetListener");
        }
    }
}
