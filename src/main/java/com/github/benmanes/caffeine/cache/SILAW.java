package com.github.benmanes.caffeine.cache;

import org.jspecify.annotations.Nullable;

class SILAW<K, V> extends SILA<K, V> {
   static final LocalCacheFactory FACTORY = SILAW::new;
   final WriteOrderDeque<Node<K, V>> writeOrderDeque = new WriteOrderDeque<>();
   volatile long expiresAfterWriteNanos;

   SILAW(Caffeine<K, V> var1, @Nullable AsyncCacheLoader<? super K, V> var2, boolean var3) {
      super(var1, var2, var3);
      this.expiresAfterWriteNanos = var1.getExpiresAfterWriteNanos();
   }

   @Override
   protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return this.writeOrderDeque;
   }

   @Override
   protected final boolean expiresAfterWrite() {
      return true;
   }

   @Override
   protected final long expiresAfterWriteNanos() {
      return this.expiresAfterWriteNanos;
   }

   @Override
   protected final void setExpiresAfterWriteNanos(long var1) {
      this.expiresAfterWriteNanos = var1;
   }
}
