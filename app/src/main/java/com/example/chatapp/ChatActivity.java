package com.example.chatapp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

 
public final class ChatActivity extends AppCompatActivity {
   private RecyclerView chatRecyclerView;
   private EditText messageBox;
   private ImageView sendButton;
   private MassageAdapter messageAdapter;
   private ArrayList messageList;
   private DatabaseReference mDbref;
   @Nullable
   private String receiverRoom;
   @Nullable
   private String senderRoom;

   @Nullable
   public final String getReceiverRoom() {
      return this.receiverRoom;
   }

   public final void setReceiverRoom(@Nullable String var1) {
      this.receiverRoom = var1;
   }

   @Nullable
   public final String getSenderRoom() {
      return this.senderRoom;
   }

   public final void setSenderRoom(@Nullable String var1) {
      this.senderRoom = var1;
   }

   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      this.setContentView(1300084);
      String name = this.getIntent().getStringExtra("name");
      String receiverUid = this.getIntent().getStringExtra("uid");
      FirebaseAuth var10000 = FirebaseAuth.getInstance();
      Intrinsics.checkNotNullExpressionValue(var10000, "FirebaseAuth.getInstance()");
      FirebaseUser var5 = var10000.getCurrentUser();
      final String senderUid = var5 != null ? var5.getUid() : null;
      DatabaseReference var10001 = FirebaseDatabase.getInstance().getReference();
      Intrinsics.checkNotNullExpressionValue(var10001, "FirebaseDatabase.getInstance().getReference()");
      this.mDbref = var10001;
      this.senderRoom = Intrinsics.stringPlus(receiverUid, senderUid);
      this.receiverRoom = Intrinsics.stringPlus(senderUid, receiverUid);
      ActionBar var6 = this.getSupportActionBar();
      if (var6 != null) {
         var6.setTitle((CharSequence)name);
      }

      View var8 = this.findViewById(1000019);
      Intrinsics.checkNotNullExpressionValue(var8, "findViewById(R.id.chatRecyclerView)");
      this.chatRecyclerView = (RecyclerView)var8;
      var8 = this.findViewById(1000279);
      Intrinsics.checkNotNullExpressionValue(var8, "findViewById(R.id.messageBox)");
      this.messageBox = (EditText)var8;
      var8 = this.findViewById(1000135);
      Intrinsics.checkNotNullExpressionValue(var8, "findViewById(R.id.sentButton)");
      this.sendButton = (ImageView)var8;
      this.messageList = new ArrayList();
      MassageAdapter var10 = new MassageAdapter;
      Context var10003 = (Context)this;
      ArrayList var10004 = this.messageList;
      if (var10004 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("messageList");
      }

      var10.<init>(var10003, var10004);
      this.messageAdapter = var10;
      RecyclerView var7 = this.chatRecyclerView;
      if (var7 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("chatRecyclerView");
      }

      var7.setLayoutManager((LayoutManager)(new LinearLayoutManager((Context)this)));
      var7 = this.chatRecyclerView;
      if (var7 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("chatRecyclerView");
      }

      var10 = this.messageAdapter;
      if (var10 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("messageAdapter");
      }

      var7.setAdapter((Adapter)var10);
      DatabaseReference var9 = this.mDbref;
      if (var9 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mDbref");
      }

      var9 = var9.child("chats");
      String var12 = this.senderRoom;
      Intrinsics.checkNotNull(var12);
      var9.child(var12).child("messages").addValueEventListener((ValueEventListener)(new ValueEventListener() {
         public void onDataChange(@NotNull DataSnapshot snapshot) {
            Intrinsics.checkNotNullParameter(snapshot, "snapshot");
            ChatActivity.access$getMessageList$p(ChatActivity.this).clear();
            Iterator var3 = snapshot.getChildren().iterator();

            while(var3.hasNext()) {
               DataSnapshot postSnapshot = (DataSnapshot)var3.next();
               Message message = (Message)postSnapshot.getValue(Message.class);
               ArrayList var10000 = ChatActivity.access$getMessageList$p(ChatActivity.this);
               Intrinsics.checkNotNull(message);
               var10000.add(message);
            }

            ChatActivity.access$getMessageAdapter$p(ChatActivity.this).notifyDataSetChanged();
         }

         public void onCancelled(@NotNull DatabaseError error) {
            Intrinsics.checkNotNullParameter(error, "error");
         }
      }));
      ImageView var11 = this.sendButton;
      if (var11 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("sendButton");
      }

      var11.setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            String message = ChatActivity.access$getMessageBox$p(ChatActivity.this).getText().toString();
            final Message messageObject = new Message(message, senderUid);
            DatabaseReference var10000 = ChatActivity.access$getMDbref$p(ChatActivity.this).child("chats");
            String var10001 = ChatActivity.this.getSenderRoom();
            Intrinsics.checkNotNull(var10001);
            var10000.child(var10001).child("messages").push().setValue(messageObject).addOnSuccessListener((OnSuccessListener)(new OnSuccessListener() {
               // $FF: synthetic method
               // $FF: bridge method
               public void onSuccess(Object var1) {
                  this.onSuccess((Void)var1);
               }

               public final void onSuccess(Void it) {
                  DatabaseReference var10000 = ChatActivity.access$getMDbref$p(ChatActivity.this).child("chats");
                  String var10001 = ChatActivity.this.getReceiverRoom();
                  Intrinsics.checkNotNull(var10001);
                  var10000.child(var10001).child("messages").push().setValue(messageObject);
               }
            }));
            ChatActivity.access$getMessageBox$p(ChatActivity.this).setText((CharSequence)"");
         }
      }));
   }

   // $FF: synthetic method
   public static final ArrayList access$getMessageList$p(ChatActivity $this) {
      ArrayList var10000 = $this.messageList;
      if (var10000 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("messageList");
      }

      return var10000;
   }

   // $FF: synthetic method
   public static final void access$setMessageList$p(ChatActivity $this, ArrayList var1) {
      $this.messageList = var1;
   }

   // $FF: synthetic method
   public static final MassageAdapter access$getMessageAdapter$p(ChatActivity $this) {
      MassageAdapter var10000 = $this.messageAdapter;
      if (var10000 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("messageAdapter");
      }

      return var10000;
   }

   // $FF: synthetic method
   public static final void access$setMessageAdapter$p(ChatActivity $this, MassageAdapter var1) {
      $this.messageAdapter = var1;
   }

   // $FF: synthetic method
   public static final EditText access$getMessageBox$p(ChatActivity $this) {
      EditText var10000 = $this.messageBox;
      if (var10000 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("messageBox");
      }

      return var10000;
   }

   // $FF: synthetic method
   public static final void access$setMessageBox$p(ChatActivity $this, EditText var1) {
      $this.messageBox = var1;
   }

   // $FF: synthetic method
   public static final DatabaseReference access$getMDbref$p(ChatActivity $this) {
      DatabaseReference var10000 = $this.mDbref;
      if (var10000 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mDbref");
      }

      return var10000;
   }

   // $FF: synthetic method
   public static final void access$setMDbref$p(ChatActivity $this, DatabaseReference var1) {
      $this.mDbref = var1;
   }
}
