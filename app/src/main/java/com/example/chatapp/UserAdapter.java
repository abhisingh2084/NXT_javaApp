 package com.example.chatapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import java.util.ArrayList;
 
import org.jetbrains.annotations.NotNull;

 
public final class UserAdapter extends Adapter {
   @NotNull
   private final Context context;
   @NotNull
   private final ArrayList userList;

   @NotNull
   public UserAdapter.UserViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
      Intrinsics.checkNotNullParameter(parent, "parent");
      View var10000 = LayoutInflater.from(this.context).inflate(1300006, parent, false);
      Intrinsics.checkNotNullExpressionValue(var10000, "LayoutInflater.from(cont…er_layout, parent, false)");
      View view = var10000;
      return new UserAdapter.UserViewHolder(view);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
      return (ViewHolder)this.onCreateViewHolder(var1, var2);
   }

   public void onBindViewHolder(@NotNull UserAdapter.UserViewHolder holder, int position) {
      Intrinsics.checkNotNullParameter(holder, "holder");
      Object var10000 = this.userList.get(position);
      Intrinsics.checkNotNullExpressionValue(var10000, "userList[position]");
      final User currentUser = (User)var10000;
      TextView var4 = holder.getTextName();
      Intrinsics.checkNotNullExpressionValue(var4, "holder.textName");
      var4.setText((CharSequence)currentUser.getName());
      holder.itemView.setOnClickListener((OnClickListener)(new OnClickListener() {
         public final void onClick(View it) {
            Intent intent = new Intent(UserAdapter.this.getContext(), ChatActivity.class);
            intent.putExtra("name", currentUser.getName());
            intent.putExtra("uid", currentUser.getUid());
            UserAdapter.this.getContext().startActivity(intent);
         }
      }));
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void onBindViewHolder(ViewHolder var1, int var2) {
      this.onBindViewHolder((UserAdapter.UserViewHolder)var1, var2);
   }

   public int getItemCount() {
      return this.userList.size();
   }

   @NotNull
   public final Context getContext() {
      return this.context;
   }

   @NotNull
   public final ArrayList getUserList() {
      return this.userList;
   }

   public UserAdapter(@NotNull Context context, @NotNull ArrayList userList) {
      Intrinsics.checkNotNullParameter(context, "context");
      Intrinsics.checkNotNullParameter(userList, "userList");
      super();
      this.context = context;
      this.userList = userList;
   }

   
   public static final class UserViewHolder extends ViewHolder {
      private final TextView textName;

      public final TextView getTextName() {
         return this.textName;
      }

      public UserViewHolder(@NotNull View itemView) {
         Intrinsics.checkNotNullParameter(itemView, "itemView");
         super(itemView);
         this.textName = (TextView)itemView.findViewById(1000311);
      }
   }
}
