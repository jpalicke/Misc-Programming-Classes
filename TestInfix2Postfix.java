/*
   Tests your implementation of the static Infix2Postfix()
   method in the Expression class.
*/

public class TestInfix2Postfix
{
   public static void main(String[] args)
   {
      String[] expressions = {
                          "2 + 5 * 5",
                          "( ( 3 + 5 ) * 4 ) - 9",
                          "( 1 + 3 ) ^ 4 * 2 ^ 3",
                          "( 32 * 41 ) / 2 + 12 * 48",
                          "2 ^ 3 ^ 4",
                          "5 - 4 + 3",
                          "6 / 2 * 3",
                          "1 + 2 - 3 - 4 + 5",
                          "-1 * 12 / -2 / 3 * 5",
                          "2 * ( 9 - ( 3 + 4 ) )"
                         };

      for (int i = 0; i < expressions.length; i++)
      {
         String postfix_1 = Expression.Infix2Postfix( expressions[i] );
         String postfix_2 = Expression_Demo.Infix2Postfix( expressions[i] );
         if ( postfix_1.equals(postfix_2) )
         {
            System.out.println( expressions[i] + " ==> " + postfix_1 );
         }
         else
         {
            System.out.println("ERROR: You have");
            System.out.println( expressions[i] + " ==> " + postfix_1 );
            System.out.println("Should be:");
            System.out.println( expressions[i] + " ==> " + postfix_2 );
         }
         System.out.println();
      }
   }
}
