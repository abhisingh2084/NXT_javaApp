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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

 
public final class SingUp extends AppCompatActivity {
   private EditText edtName;
   private EditText edtEmail;
   private EditText edtPassword;
   private Button btnSignUp;
   private FirebaseAuth mAuth;
   private DatabaseReference mDbref;

   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      this.setContentView(1300005);
      ActionBar var10000 = this.getSupportActionBar();
      if (var10000 != null) {
         var10000.hide();
      }

      FirebaseAuth var10001 = FirebaseAuth.getInstance();
      Intrinsics.checkNotNullExpressionValue(var10001, "FirebaseAuth.getInstance()");
      this.mAuth = var10001;
      View var3 = this.findViewById(1000372);
      Intrinsics.checkNotNullExpressionValue(var3, "findViewById(R.id.edt_name)");
      this.edtName = (EditText)var3;
      var3 = this.findViewById(1000319);
      Intrinsics.checkNotNullExpressionValue(var3, "findViewById(R.id.edt_email)");
      this.edtEmail = (EditText)var3;
      var3 = this.findViewById(1000040);
      Intrinsics.checkNotNullExpressionValue(var3, "findViewById(R.id.edt_password)");
      this.edtPassword = (EditText)var3;
      var3 = this.findViewById(1000336);
      Intrinsics.checkNotNullExpressionValue(var3, "findViewById(R.id.btnSingUp)");
      this.btnSignUp = (Button)var3;
      Button var2 = this.btnSignUp;
      if (var2 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("btnSignUp");
      }

      var2.setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            String name = SingUp.access$getEdtName$p(SingUp.this).getText().toString();
            String email = SingUp.access$getEdtEmail$p(SingUp.this).getText().toString();
            String password = SingUp.access$getEdtPassword$p(SingUp.this).getText().toString();
            SingUp.this.signUp(name, email, password);
         }
      }));
   }

   private final void signUp(final String name, final String email, String password) {
      FirebaseAuth var10000 = this.mAuth;
      if (var10000 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mAuth");
      }

      var10000.createUserWithEmailAndPassword(email, password).addOnCompleteListener((Activity)this, (OnCompleteListener)(new OnCompleteListener() {
         public final void onComplete(@NotNull Task task) {
            Intrinsics.checkNotNullParameter(task, "task");
            if (task.isSuccessful()) {
               SingUp var10000 = SingUp.this;
               String var10001 = name;
               String var10002 = email;
               FirebaseUser var10003 = SingUp.access$getMAuth$p(SingUp.this).getCurrentUser();
               String var3 = var10003 != null ? var10003.getUid() : null;
               Intrinsics.checkNotNull(var3);
               Intrinsics.checkNotNullExpressionValue(var3, "mAuth.currentUser?.uid!!");
               var10000.addUserToDatabase(var10001, var10002, var3);
               Intent intent = new Intent((Context)SingUp.this, MainActivity.class);
               SingUp.this.finish();
               SingUp.this.startActivity(intent);
            } else {
               Toast.makeText((Context)SingUp.this, (CharSequence)"some Error occured", 0).show();
            }

         }
      }));
   }

   private final void addUserToDatabase(String name, String email, String uid) {
      DatabaseReference var10001 = FirebaseDatabase.getInstance().getReference();
      Intrinsics.checkNotNullExpressionValue(var10001, "FirebaseDatabase.getInstance().getReference()");
      this.mDbref = var10001;
      DatabaseReference var10000 = this.mDbref;
      if (var10000 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mDbref");
      }

      var10000.child("user").child(uid).setValue(new User(name, email, uid));
   }

   // $FF: synthetic method
   public static final EditText access$getEdtName$p(SingUp $this) {
      EditText var10000 = $this.edtName;
      if (var10000 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("edtName");
      }

      return var10000;
   }

   // $FF: synthetic method
   public static final void access$setEdtName$p(SingUp $this, EditText var1) {
      $this.edtName = var1;
   }

   // $FF: synthetic method
   public static final EditText access$getEdtEmail$p(SingUp $this) {
      EditText var10000 = $this.edtEmail;
      if (var10000 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("edtEmail");
      }

      return var10000;
   }

   // $FF: synthetic method
   public static final void access$setEdtEmail$p(SingUp $this, EditText var1) {
      $this.edtEmail = var1;
   }

   // $FF: synthetic method
   public static final EditText access$getEdtPassword$p(SingUp $this) {
      EditText var10000 = $this.edtPassword;
      if (var10000 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("edtPassword");
      }

      return var10000;
   }

   // $FF: synthetic method
   public static final void access$setEdtPassword$p(SingUp $this, EditText var1) {
      $this.edtPassword = var1;
   }

   // $FF: synthetic method
   public static final FirebaseAuth access$getMAuth$p(SingUp $this) {
      FirebaseAuth var10000 = $this.mAuth;
      if (var10000 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mAuth");
      }

      return var10000;
   }

   // $FF: synthetic method
   public static final void access$setMAuth$p(SingUp $this, FirebaseAuth var1) {
      $this.mAuth = var1;
   }
}

