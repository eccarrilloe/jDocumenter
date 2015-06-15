import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

class Documenter
{
	public static void main(String[] args) throws Exception
	{
		try
		{

			String filename = args.length > 0 ? args[0] : "Example.java";
			JavaLexer lexer = new JavaLexer(new ANTLRFileStream(filename));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			JavaParser parser = new JavaParser(tokens);
			ParseTree tree = parser.compilationUnit();
			ParseTreeWalker walker = new ParseTreeWalker();
			DocumenterListener listener = new DocumenterListener();

			walker.walk(listener, tree);

			System.out.println("PACKAGE: " + listener.packageName);
			for (CustomImport x : listener.imports) {
				System.out.println("IMPORT: " + x.name);
			}
			for (CustomClass x : listener.classes) {
				System.out.println("CLASS: " + x.name);
			}
			for (CustomInterface x : listener.interfaces) {
				System.out.println("INTERFACE: " + x.name);
			}

	    }
	    catch (Exception e)
	    {
	    	e.printStackTrace();
	    }
	}
}