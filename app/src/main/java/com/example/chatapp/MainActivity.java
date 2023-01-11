package com.example.chatapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Iterator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import kotlin.jvm.internal.Intrinsics;


public final class MainActivity extends AppCompatActivity {
    private RecyclerView userRecyclerView;
    private ArrayList userList;
    private UserAdapter adapter;
    private FirebaseAuth mAuth;
    private DatabaseReference mDbRef;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        FirebaseAuth var10001 = FirebaseAuth.getInstance();
        Intrinsics.checkNotNullExpressionValue(var10001, "FirebaseAuth.getInstance()");
        this.mAuth = var10001;
        DatabaseReference var3 = FirebaseDatabase.getInstance().getReference();
        Intrinsics.checkNotNullExpressionValue(var3, "FirebaseDatabase.getInstance().getReference()");
        this.mDbRef = var3;
        this.userList = new ArrayList();
        UserAdapter var4 = new UserAdapter;
        Context var10003 = (Context)this;
        ArrayList var10004 = this.userList;
        if (var10004 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userList");
        }

        var4.<init>(var10003, var10004);
        this.adapter = var4;
        View var5 = this.findViewById(1000227);
        Intrinsics.checkNotNullExpressionValue(var5, "findViewById(R.id.userRecylerView)");
        this.userRecyclerView = (RecyclerView)var5;
        RecyclerView var10000 = this.userRecyclerView;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userRecyclerView");
        }

        var10000.setLayoutManager((LayoutManager)(new LinearLayoutManager((Context)this)));
        var10000 = this.userRecyclerView;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userRecyclerView");
        }

        var4 = this.adapter;
        if (var4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        }

        var10000.setAdapter((Adapter)var4);
        DatabaseReference var2 = this.mDbRef;
        if (var2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDbRef");
        }

        var2.child("user").addValueEventListener((ValueEventListener)(new ValueEventListener() {
            public void onDataChange(@NotNull DataSnapshot snapshot) {
                Intrinsics.checkNotNullParameter(snapshot, "snapshot");
                Iterator var3 = snapshot.getChildren().iterator();

                while(var3.hasNext()) {
                    DataSnapshot postSnapshot = (DataSnapshot)var3.next();
                    User currentUser = (User)postSnapshot.getValue(User.class);
                    FirebaseUser var10000 = MainActivity.access$getMAuth$p(MainActivity.this).getCurrentUser();
                    if (Intrinsics.areEqual(var10000 != null ? var10000.getUid() : null, currentUser != null ? currentUser.getUid() : null) ^ true) {
                        ArrayList var5 = MainActivity.access$getUserList$p(MainActivity.this);
                        Intrinsics.checkNotNull(currentUser);
                        var5.add(currentUser);
                    }
                }

                MainActivity.access$getAdapter$p(MainActivity.this).notifyDataSetChanged();
            }

            public void onCancelled(@NotNull DatabaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }
        }));
    }

    public boolean onCreateOptionsMenu(@Nullable Menu menu) {
        this.getMenuInflater().inflate(1400000, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == 1000208) {
            FirebaseAuth var10000 = this.mAuth;
            if (var10000 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mAuth");
            }

            var10000.signOut();
            Intent intent = new Intent((Context)this, Login.class);
            this.finish();
            this.startActivity(intent);
            return true;
        } else {
            return true;
        }
    }

    // $FF: synthetic method
    public static final FirebaseAuth access$getMAuth$p(MainActivity $this) {
        FirebaseAuth var10000 = $this.mAuth;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAuth");
        }

        return var10000;
    }

    // $FF: synthetic method
    public static final void access$setMAuth$p(MainActivity $this, FirebaseAuth var1) {
        $this.mAuth = var1;
    }

    // $FF: synthetic method
    public static final ArrayList access$getUserList$p(MainActivity $this) {
        ArrayList var10000 = $this.userList;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userList");
        }

        return var10000;
    }

    // $FF: synthetic method
    public static final void access$setUserList$p(MainActivity $this, ArrayList var1) {
        $this.userList = var1;
    }

    // $FF: synthetic method
    public static final UserAdapter access$getAdapter$p(MainActivity $this) {
        UserAdapter var10000 = $this.adapter;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        }

        return var10000;
    }

    // $FF: synthetic method
    public static final void access$setAdapter$p(MainActivity $this, UserAdapter var1) {
        $this.adapter = var1;
    }
}

