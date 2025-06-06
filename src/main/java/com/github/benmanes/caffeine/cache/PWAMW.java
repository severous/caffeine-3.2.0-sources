package com.github.benmanes.caffeine.cache;

import java.lang.ref.ReferenceQueue;

final class PWAMW<K, V> extends PWA<K, V> {
   int queueType;
   int weight;
   int policyWeight;

   PWAMW() {
   }

   PWAMW(K var1, ReferenceQueue<K> var2, V var3, ReferenceQueue<V> var4, int var5, long var6) {
      super((K)var1, var2, (V)var3, var4, var5, var6);
      this.weight = var5;
   }

   PWAMW(Object var1, V var2, ReferenceQueue<V> var3, int var4, long var5) {
      super(var1, (V)var2, var3, var4, var5);
      this.weight = var4;
   }

   @Override
   public int getQueueType() {
      return this.queueType;
   }

   @Override
   public void setQueueType(int var1) {
      this.queueType = var1;
   }

   @Override
   public int getWeight() {
      return this.weight;
   }

   @Override
   public void setWeight(int var1) {
      this.weight = var1;
   }

   @Override
   public int getPolicyWeight() {
      return this.policyWeight;
   }

   @Override
   public void setPolicyWeight(int var1) {
      this.policyWeight = var1;
   }

   @Override
   public Node<K, V> newNode(K var1, ReferenceQueue<K> var2, V var3, ReferenceQueue<V> var4, int var5, long var6) {
      return new PWAMW<>((K)var1, var2, (V)var3, var4, var5, var6);
   }

   @Override
   public Node<K, V> newNode(Object var1, V var2, ReferenceQueue<V> var3, int var4, long var5) {
      return new PWAMW<>(var1, (V)var2, var3, var4, var5);
   }
}
