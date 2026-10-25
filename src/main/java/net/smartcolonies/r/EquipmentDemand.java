package net.smartcolonies.r;


import java.util.Objects;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;

final class EquipmentDemand {
   public final EquipmentSlot x3v9a3rm2wy1oi;
   public final Item x2b30qxyd0cjni;
   public final Item x6ma4582xqrzt;
   public final int xl7i1w1rdwzb1;

   public EquipmentDemand(EquipmentSlot x3v9a3rm2wy1oi, Item x2b30qxyd0cjni, Item x6ma4582xqrzt, int xl7i1w1rdwzb1) {
      this.x3v9a3rm2wy1oi = x3v9a3rm2wy1oi;
      this.x2b30qxyd0cjni = x2b30qxyd0cjni;
      this.x6ma4582xqrzt = x6ma4582xqrzt;
      this.xl7i1w1rdwzb1 = xl7i1w1rdwzb1;
   }

   public EquipmentSlot x3v9a3rm2wy1oi() {
      return this.x3v9a3rm2wy1oi;
   }

   public Item x2b30qxyd0cjni() {
      return this.x2b30qxyd0cjni;
   }

   public Item x6ma4582xqrzt() {
      return this.x6ma4582xqrzt;
   }

   public int xl7i1w1rdwzb1() {
      return this.xl7i1w1rdwzb1;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof EquipmentDemand other)) return false;
      return Objects.equals(this.x3v9a3rm2wy1oi, other.x3v9a3rm2wy1oi) && Objects.equals(this.x2b30qxyd0cjni, other.x2b30qxyd0cjni) && Objects.equals(this.x6ma4582xqrzt, other.x6ma4582xqrzt) && Objects.equals(this.xl7i1w1rdwzb1, other.xl7i1w1rdwzb1);
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.x3v9a3rm2wy1oi, this.x2b30qxyd0cjni, this.x6ma4582xqrzt, this.xl7i1w1rdwzb1);
   }

   @Override
   public String toString() {
      return "EquipmentDemand[" + "x3v9a3rm2wy1oi=" + this.x3v9a3rm2wy1oi + ", " + "x2b30qxyd0cjni=" + this.x2b30qxyd0cjni + ", " + "x6ma4582xqrzt=" + this.x6ma4582xqrzt + ", " + "xl7i1w1rdwzb1=" + this.xl7i1w1rdwzb1 + "]";
   }
}
