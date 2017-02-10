/**
   Course: CS 27500
   Name: Joseph Palicke
   Email: jpalicke@sbcglobal.net
   Assignment: 6

   Expression.java for HW6
   
   Create static methods Infix2Postfix() and EvaluatePostfix().
   
*/
import java.util.StringTokenizer;
import java.util.Stack;

public class Expression
{

   public static String Infix2Postfix(String infix)
   {
		infix = infix + " )";
		Stack<String> operators = new Stack<String>();
		StringTokenizer st = new StringTokenizer(infix);
		String postfix = "";
		operators.push( "(" );
		while (st.hasMoreTokens())
		{
			String token = st.nextToken();
			
			/******************************************************************
			  This switch statement implements the steps of the algorithm
			  for converting from infix to postfix from the assignment docs.
			  For ease of understanding and not having to flip back and forth
			  it is:
			  
			  Append a right parenthesis, ")", to the end of infix
              
			  Push a left parenthesis, "(", onto the stack
			  
			  While infix has more tokens, do the following:
			  
			  If the current token is an integer, append it to postfix.
			  
			  If the current token is a left parenthesis, push it on the stack.
			  
			  If the current token is an operator:
			  
			  Pop operators (if there are any) off of the stack while they have 
			  equal or higher precedence than the current operator and append 
			  the popped operators to postfix. Push the current operator on the 
			  stack. 
			  
			  If the current token is a right parenthesis: 
			  
			  Pop operators off the stack (there must be at least one) and 
			  append them to postfix until a left parenthesis is found at the
			  top of the stack. Pop the left parenthesis from the top of the 
			  stack.
			******************************************************************/
			
			switch (token)
			{
				case "(":
					operators.push("(");
					break;
				case "^":
					while (operators.peek().equals("^")) 
					{
						
						postfix = postfix + operators.pop() + " "; 

					}
					operators.push(token);
					break;
				case "*":
				case "/":
				case "%":
					while (operators.peek().equals("^") || operators.peek().equals("*") 
							 || operators.peek().equals("/") || operators.peek().equals("%")) 
					{
				
						postfix = postfix + operators.pop() + " ";
						
					}
					operators.push(token);
					break;
				case "+":
				case "-":
					while (operators.peek().equals("+") || operators.peek().equals("-") 
							 || operators.peek().equals("^") || operators.peek().equals("*") 
							 || operators.peek().equals("/") || operators.peek().equals("%")) 
					{
						postfix = postfix + operators.pop() + " ";
					}
					operators.push(token);
					break;
				case ")":
					while (!operators.peek().equals("("))
					{
						postfix = postfix + operators.pop() + " ";
					}
					operators.pop();
					break;
				default:
					postfix = postfix + token + " ";
					break;
			}

		}
		return postfix;
   }//Infix2Postfix()



   public static int EvaluatePostfix(String postfix)
   {
		int op1;
		int op2;
		int answer = 0;
		Stack<Integer> operands = new Stack<Integer>();
		StringTokenizer st = new StringTokenizer(postfix);
		while (st.hasMoreTokens())
		{
			String token = st.nextToken();

         //Note the non-communative operations have to have
         //operands popped to variables so the proper order can
         //be preserved.

			switch (token)
			{
				case "+":
					answer = operands.pop() + operands.pop();
					operands.push(answer);
					break;
				case "-":
					op2 = operands.pop();
					op1 = operands.pop();
					answer = op1 - op2;
					operands.push(answer);
					break;
				case "*":
					answer = operands.pop() * operands.pop();
					operands.push(answer);
					break;
				case "/":
					op2 = operands.pop();
					op1 = operands.pop();
					answer = op1 / op2;
					operands.push(answer);
					break;
				case "%":
					op2 = operands.pop();
					op1 = operands.pop();
					answer = op1 % op2;
					operands.push(answer);
					break;
				case "^":
					op2 = operands.pop();
					op1 = operands.pop();
					
               /***************************************************************
				 I know this is kind of ugly, but it still seems
                 superior to looping here to do the exponentiation
                 though that would be easy enough to do.
				 
                 Explanation:  Math.pow returns a double.  To convert
                 double to int, I need to use the Double wrapper class
                 So, it calls Double's constructor with math.pow as an argument
                 and then runs Double's intValue function to convert
                 to int and set answer.
               ***************************************************************/
			   
					answer = new Double(Math.pow(op1,op2)).intValue();
					operands.push(answer);
					break;
				default:
					operands.push(Integer.parseInt(token));
					break;
					
			}
		}

		return operands.pop();
   }//EvaluatePostfix()

}//Expression
