/*
   Tests your implementation of the static EvaluatePostfix()
   method in the Expression class.
*/

public class TestEvaluatePostfix
{
   public static void main(String[] args)
   {
      String[] postfix = {
                          "2 5 5 * +",
                          "3 5 + 4 * 9 -",
                          "1 3 + 4 ^ 2 3 ^ *",
                          "32 41 * 2 / 12 48 * +",
                          "2 3 ^ 4 ^",
                          "5 4 - 3 +",
                          "6 2 / 3 *",
                          "1 2 + 3 - 4 - 5 +",
                          "-1 12 * -2 / 3 / 5 *",
                          "2 9 3 4 + - *"
                         };

      for (int i = 0; i < postfix.length; i++)
      {
         int result_1 = Expression.EvaluatePostfix( postfix[i] );
         int result_2 = Expression_Demo.EvaluatePostfix( postfix[i] );
         if ( result_1 == result_2 )
         {
            System.out.println( postfix[i] + " ==> " + result_1 );
         }
         else
         {
            System.out.println("ERROR: You have");
            System.out.println( postfix[i] + " ==> " + result_1 );
            System.out.println("Should be:");
            System.out.println( postfix[i] + " ==> " + result_2 );
         }
         System.out.println();
      }
   }
}
