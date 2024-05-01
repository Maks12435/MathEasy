package com.example.easymath;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class CustomFeedbackDialog {

    public static void showFeedback(Context context, String feedback) {
        // Создаем экземпляр LayoutInflater для загрузки макета диалога
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.custom_feedback_dialog, null);

        // Настраиваем текст обратной связи
        TextView feedbackText = dialogView.findViewById(R.id.feedback_text);
        feedbackText.setText(feedback);

        // Создаем диалоговое окно
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialogView);

        // Показываем диалоговое окно
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
