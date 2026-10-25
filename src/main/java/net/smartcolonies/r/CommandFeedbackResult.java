package net.smartcolonies.r;


import java.util.Objects;
public final class CommandFeedbackResult {
   public final int x3cg1vvxnlossv;
   public final String x2fa4oag1sp0w9;

   public CommandFeedbackResult(int x3cg1vvxnlossv, String x2fa4oag1sp0w9) {
      this.x3cg1vvxnlossv = x3cg1vvxnlossv;
      this.x2fa4oag1sp0w9 = x2fa4oag1sp0w9;
   }

   public int x3cg1vvxnlossv() {
      return this.x3cg1vvxnlossv;
   }

   public String x2fa4oag1sp0w9() {
      return this.x2fa4oag1sp0w9;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof CommandFeedbackResult other)) return false;
      return Objects.equals(this.x3cg1vvxnlossv, other.x3cg1vvxnlossv) && Objects.equals(this.x2fa4oag1sp0w9, other.x2fa4oag1sp0w9);
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.x3cg1vvxnlossv, this.x2fa4oag1sp0w9);
   }

   @Override
   public String toString() {
      return "CommandFeedbackResult[" + "x3cg1vvxnlossv=" + this.x3cg1vvxnlossv + ", " + "x2fa4oag1sp0w9=" + this.x2fa4oag1sp0w9 + "]";
   }
}
