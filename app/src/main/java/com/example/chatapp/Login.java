package com.example.chatapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import kotlin.jvm.internal.Intrinsics;


public final class Login extends AppCompatActivity {
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnLogin;
    private Button btnSignUp;
    private FirebaseAuth mAuth;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(1300023);
        ActionBar var10000 = this.getSupportActionBar();
        if (var10000 != null) {
            var10000.hide();
        }

        FirebaseAuth var10001 = FirebaseAuth.getInstance();
        Intrinsics.checkNotNullExpressionValue(var10001, "FirebaseAuth.getInstance()");
        this.mAuth = var10001;
        View var3 = this.findViewById(1000319);
        Intrinsics.checkNotNullExpressionValue(var3, "findViewById(R.id.edt_email)");
        this.edtEmail = (EditText)var3;
        var3 = this.findViewById(1000040);
        Intrinsics.checkNotNullExpressionValue(var3, "findViewById(R.id.edt_password)");
        this.edtPassword = (EditText)var3;
        var3 = this.findViewById(1000190);
        Intrinsics.checkNotNullExpressionValue(var3, "findViewById(R.id.btnLogin)");
        this.btnLogin = (Button)var3;
        var3 = this.findViewById(1000336);
        Intrinsics.checkNotNullExpressionValue(var3, "findViewById(R.id.btnSingUp)");
        this.btnSignUp = (Button)var3;
        Button var2 = this.btnSignUp;
        if (var2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnSignUp");
        }

        var2.setOnClickListener((OnClickListener)(new OnClickListener() {
            public final void onClick(View it) {
                Intent intent = new Intent((Context)Login.this, SingUp.class);
                Login.this.startActivity(intent);
            }
        }));
        var2 = this.btnLogin;
        if (var2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnLogin");
        }

        var2.setOnClickListener((OnClickListener)(new OnClickListener() {
            public final void onClick(View it) {
                String email = Login.access$getEdtEmail$p(Login.this).getText().toString();
                String password = Login.access$getEdtPassword$p(Login.this).getText().toString();
                Login.this.login(email, password);
            }
        }));
    }

    private final void login(String email, String password) {
        FirebaseAuth var10000 = this.mAuth;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAuth");
        }

        var10000.signInWithEmailAndPassword(email, password).addOnCompleteListener((Activity)this, (OnCompleteListener)(new OnCompleteListener() {
            public final void onComplete(@NotNull Task task) {
                Intrinsics.checkNotNullParameter(task, "task");
                if (task.isSuccessful()) {
                    Intent intent = new Intent((Context)Login.this, MainActivity.class);
                    Login.this.finish();
                    Login.this.startActivity(intent);
                } else {
                    Toast.makeText((Context)Login.this, (CharSequence)"User does not exist", 0).show();
                }

            }
        }));
    }

    // $FF: synthetic method
    public static final EditText access$getEdtEmail$p(Login $this) {
        EditText var10000 = $this.edtEmail;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("edtEmail");
        }

        return var10000;
    }

    // $FF: synthetic method
    public static final void access$setEdtEmail$p(Login $this, EditText var1) {
        $this.edtEmail = var1;
    }

    // $FF: synthetic method
    public static final EditText access$getEdtPassword$p(Login $this) {
        EditText var10000 = $this.edtPassword;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("edtPassword");
        }

        return var10000;
    }


    public static final void access$setEdtPassword$p(Login $this, EditText var1) {
        $this.edtPassword = var1;
    }
}
