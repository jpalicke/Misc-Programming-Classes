/*
   Tests your implementations of the static Infix2Postfix() and EvaluatePostFix()
   methods in the Expression class.
*/

public class TestExpression
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
/*
2 + 5 * 5                 ==> 2 5 5 * +              ==> 27
( ( 3 + 5 ) * 4 ) - 9     ==> 3 5 + 4 * 9 -          ==> 23
( 1 + 3 ) ^ 4 * 2 ^ 3     ==> 1 3 + 4 ^ 2 3 ^ *      ==> 2048
( 32 * 41 ) / 2 + 12 * 48 ==> 32 41 * 2 / 12 48 * +  ==> 1232
2 ^ 3 ^ 4                 ==> 2 3 ^ 4 ^              ==> 4096
5 - 4 + 3                 ==> 5 4 - 3 +              ==> 4
6 / 2 * 3                 ==> 6 2 / 3 *              ==> 9
1 + 2 - 3 - 4 + 5         ==> 1 2 + 3 - 4 - 5 +      ==> 1
-1 * 12 / -2 / 3 * 5      ==> -1 12 * -2 / 3 / 5 *   ==> 10
2 * ( 9 - ( 3 + 4 ) )     ==> 2 9 3 4 + - *          ==> 4
*/
      for (int i = 0; i < expressions.length; i++)
      {
         int result_1 = Expression.EvaluatePostfix(
                        Expression.Infix2Postfix( expressions[i] ) );
         int result_2 = Expression_Demo.EvaluatePostfix(
                        Expression_Demo.Infix2Postfix( expressions[i] ) );
         if ( result_1 == result_2 )
         {
            System.out.println( expressions[i] + " ==> " + result_1 );
         }
         else
         {
            System.out.println("ERROR: You have");
            System.out.println( expressions[i] + " ==> " + result_1 );
            System.out.println("Should be:");
            System.out.println( expressions[i] + " ==> " + result_2 );
         }
         System.out.println();
      }
   }
}
