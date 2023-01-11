 package com.example.chatapp;

import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

 
public final class User {
   @Nullable
   private String name;
   @Nullable
   private String email;
   @Nullable
   private String uid;

   @Nullable
   public final String getName() {
      return this.name;
   }

   public final void setName(@Nullable String var1) {
      this.name = var1;
   }

   @Nullable
   public final String getEmail() {
      return this.email;
   }

   public final void setEmail(@Nullable String var1) {
      this.email = var1;
   }

   @Nullable
   public final String getUid() {
      return this.uid;
   }

   public final void setUid(@Nullable String var1) {
      this.uid = var1;
   }

   public User() {
   }

   public User(@Nullable String name, @Nullable String email, @Nullable String uid) {
      this.name = name;
      this.email = email;
      this.uid = uid;
   }
}
