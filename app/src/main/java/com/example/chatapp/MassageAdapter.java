package com.example.chatapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

 
public final class MassageAdapter extends Adapter {
   private final int ITEM_RECEIVE;
   private final int ITEM_SENT;
   @NotNull
   private final Context context;
   @NotNull
   private final ArrayList messageList;

   public final int getITEM_RECEIVE() {
      return this.ITEM_RECEIVE;
   }

   public final int getITEM_SENT() {
      return this.ITEM_SENT;
   }

   @NotNull
   public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
      Intrinsics.checkNotNullParameter(parent, "parent");
      View var10000;
      View view;
      if (viewType == 1) {
         var10000 = LayoutInflater.from(this.context).inflate(1300047, parent, false);
         Intrinsics.checkNotNullExpressionValue(var10000, "LayoutInflater.from(cont…t.receive, parent, false)");
         view = var10000;
         return (ViewHolder)(new MassageAdapter.ReceiveViewHolder(view));
      } else {
         var10000 = LayoutInflater.from(this.context).inflate(1300079, parent, false);
         Intrinsics.checkNotNullExpressionValue(var10000, "LayoutInflater.from(cont…yout.sent, parent, false)");
         view = var10000;
         return (ViewHolder)(new MassageAdapter.SentViewHolder(view));
      }
   }

   public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
      Intrinsics.checkNotNullParameter(holder, "holder");
      Object var10000 = this.messageList.get(position);
      Intrinsics.checkNotNullExpressionValue(var10000, "messageList[position]");
      Message currentMessage = (Message)var10000;
      TextView var5;
      if (Intrinsics.areEqual(holder.getClass(), MassageAdapter.SentViewHolder.class)) {
         MassageAdapter.SentViewHolder viewHolder = (MassageAdapter.SentViewHolder)holder;
         var5 = ((MassageAdapter.SentViewHolder)holder).getSentMessage();
         Intrinsics.checkNotNullExpressionValue(var5, "holder.sentMessage");
         var5.setText((CharSequence)currentMessage.getMessage());
      } else {
         MassageAdapter.ReceiveViewHolder viewHolder = (MassageAdapter.ReceiveViewHolder)holder;
         var5 = ((MassageAdapter.ReceiveViewHolder)holder).getReceiveMessage();
         Intrinsics.checkNotNullExpressionValue(var5, "holder.receiveMessage");
         var5.setText((CharSequence)currentMessage.getMessage());
      }

   }

   public int getItemViewType(int position) {
      Object var10000 = this.messageList.get(position);
      Intrinsics.checkNotNullExpressionValue(var10000, "messageList[position]");
      Message currentMessage = (Message)var10000;
      FirebaseAuth var3 = FirebaseAuth.getInstance();
      Intrinsics.checkNotNullExpressionValue(var3, "FirebaseAuth.getInstance()");
      FirebaseUser var4 = var3.getCurrentUser();
      return StringsKt.equals$default(var4 != null ? var4.getUid() : null, currentMessage.getSenderId(), false, 2, (Object)null) ? this.ITEM_SENT : this.ITEM_RECEIVE;
   }

   public int getItemCount() {
      return this.messageList.size();
   }

   @NotNull
   public final Context getContext() {
      return this.context;
   }

   @NotNull
   public final ArrayList getMessageList() {
      return this.messageList;
   }

   public MassageAdapter(@NotNull Context context, @NotNull ArrayList messageList) {
      Intrinsics.checkNotNullParameter(context, "context");
      Intrinsics.checkNotNullParameter(messageList, "messageList");
      super();
      this.context = context;
      this.messageList = messageList;
      this.ITEM_RECEIVE = 1;
      this.ITEM_SENT = 2;
   }

   @Metadata(
      mv = {1, 6, 0},
      k = 1,
      d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0019\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"},
      d2 = {"Lcom/example/chatapp/MassageAdapter$SentViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "sentMessage", "Landroid/widget/TextView;", "kotlin.jvm.PlatformType", "getSentMessage", "()Landroid/widget/TextView;", "ChatApp.app.main"}
   )
   public static final class SentViewHolder extends ViewHolder {
      private final TextView sentMessage;

      public final TextView getSentMessage() {
         return this.sentMessage;
      }

      public SentViewHolder(@NotNull View itemView) {
         Intrinsics.checkNotNullParameter(itemView, "itemView");
         super(itemView);
         this.sentMessage = (TextView)itemView.findViewById(1000180);
      }
   }

  
   public static final class ReceiveViewHolder extends ViewHolder {
      private final TextView receiveMessage;

      public final TextView getReceiveMessage() {
         return this.receiveMessage;
      }

      public ReceiveViewHolder(@NotNull View itemView) {
         Intrinsics.checkNotNullParameter(itemView, "itemView");
         super(itemView);
         this.receiveMessage = (TextView)itemView.findViewById(1000209);
      }
   }
}
