import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

class Documenter
{
	public static void main(String[] args) throws Exception
	{
		try
		{

			Java8Lexer lexer = new Java8Lexer(new ANTLRFileStream("Bank.java"));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			Java8Parser parser = new Java8Parser(tokens);
			ParseTree tree = parser.compilationUnit();
			ParseTreeWalker walker = new ParseTreeWalker();
			DocumenterListener listener = new DocumenterListener();

			walker.walk(listener, tree);
	    }
	    catch (Exception e)
	    {
			System.err.println("Error (Documenter): " + e);
	    }
	}
}