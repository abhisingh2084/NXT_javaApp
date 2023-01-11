 package com.example.chatapp;

import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

 
public final class Message {
   @Nullable
   private String message;
   @Nullable
   private String senderId;

   @Nullable
   public final String getMessage() {
      return this.message;
   }

   public final void setMessage(@Nullable String var1) {
      this.message = var1;
   }

   @Nullable
   public final String getSenderId() {
      return this.senderId;
   }

   public final void setSenderId(@Nullable String var1) {
      this.senderId = var1;
   }

   public Message() {
   }

   public Message(@Nullable String message, @Nullable String senderId) {
      this.message = message;
      this.senderId = senderId;
   }
}
