import java.io.File;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Iterator;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

class Documenter {

	public static String xml = "";
	Boolean imports_printed = false;
	static String separator = "\n";

	public static void main(String[] args) throws Exception {
		try {

			String filename = args.length > 0 ? args[0] : "Example.java";
			JavaLexer lexer = new JavaLexer(new ANTLRFileStream(filename));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			JavaParser parser = new JavaParser(tokens);
			ParseTree tree = parser.compilationUnit();
			ParseTreeWalker walker = new ParseTreeWalker();
			DocumenterListener listener = new DocumenterListener();

			walker.walk(listener, tree);

			xml += "<package>" + listener.packageName + "</package>";

			if (! listener.imports.isEmpty()) {
				xml += "<imports total=\"" + listener.imports.size() + "\">" + separator;

				for (CustomImport x : listener.imports)
					xml += printimport(x);

				xml += "</imports>"+separator;
			}

			if (! listener.classes.isEmpty()) {
				xml += "<clases total=\"" + listener.classes.size() + "\">" + separator;

				for (CustomClass x : listener.classes)
					xml += printclass(x);

				xml += "</clases>" + separator;
			}

			if (! listener.interfaces.isEmpty()) {
				xml += "<interfaces total=\"" + listener.interfaces.size() + "\">" + separator;
				for (CustomInterface x : listener.interfaces)
					xml += printinterface(x);

				xml += "</interfaces>"+separator;
			}
			String errores="";

			if (! listener.errors.isEmpty()) {
				errores+="<errores total=\""+listener.errors.size()+"\">";
				for (TerminalNode x : listener.errors)
					errores += "<error fila=\"" + x.getSymbol().getLine() +"\" columna=\""+ x.getSymbol().getCharPositionInLine()+"\"> No se esperaba: \"" + x.getText()+"\"</error>";
				errores += "</errores>"+separator;
			}

			xml = "<codigo archivo=\"" + filename + "\">" + xml + errores + "</codigo>";

			File out = new File(System.getProperty("user.dir") + "/out");
			if (!out.exists())
       		out.mkdirs();

			PrintWriter output = new PrintWriter(out + "/" + filename + ".xml");
			output.println(XMLFormat.format(xml));
			output.close();

	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}

	private static String printimport(CustomImport x) {
		String content = "";
		content += "<import>" + x.name + "</import>" + separator;
		return content;
	}

	private static String printclass(CustomClass x) {

			String opentag = "<clase nombre=\"" + x.name + "\" ";
			String content = "";

			if (x.superClass != null)
				opentag += "superClase=\"" + x.superClass + "\" ";

			opentag = opentag.trim() + " numerolineas=\"" + x.numeroLineas + "\">" + separator;

			if (x.classModifiers.size()>0) {
				content += "<modificadores total=\"" + x.classModifiers.size() + "\">" + separator;

				for (int j = 0; j < x.classModifiers.size(); j++)
					content += "<modificador>" + x.classModifiers.get(j) + "</modificador>" + separator;

				content += "</modificadores>";
			}

			if (x.superInterfaces.size() > 0) {
				content += "<superInterfaces total=\"" + x.superInterfaces.size() + "\">" + separator;

				for (int j = 0; j < x.superInterfaces.size(); j++)
					content += "<superInterfaz>" + x.superInterfaces.get(j).replace("&","&amp;").replace("<", "&lt;").replace(">", "&gt;") + "</superInterfaz>" + separator;

				content += "</superInterfaces>";
			}

			if (x.typeParameters.size() > 0) {
				content += "<typeParameters total=\"" + x.typeParameters.size() + "\">" + separator;

				for (int j = 0; j < x.typeParameters.size(); j++)
					content += "<typeParameter>" + x.typeParameters.get(j) + "</typeParameter>" + separator;

				content += "</typeParameters>";
			}

			if (x.constants.size() > 0) {
				content += "<constantes total=\"" + x.constants.size() + "\">" + separator;

				for (int j = 0; j < x.constants.size(); j++)
					content += "<constante " + printvariable(x.constants.get(j)) + "</constante>" + separator;

				content += "</constantes>" + separator;
			}

			if (x.variables.size() > 0) {
				content += "<atributos total=\"" + x.variables.size() + "\">" + separator;

				for (int j = 0; j < x.variables.size(); j++)
					content += "<atributos " + printvariable(x.variables.get(j)) + "</atributos>" + separator;

				content += "</atributos>" + separator;
			}

			if (x.constructors.size()>0) {
				content += "<constructores total=\"" + x.constructors.size() + "\">" + separator;

				for (int j = 0; j < x.constructors.size(); j++)
					content += "<constructor " + printmethod(x.constructors.get(j)) + "</constructor>" + separator;

				content += "</constructores>" + separator;
			}

			if (x.methods.size() > 0) {
				content += "<metodos total=\"" + x.methods.size() + "\">" + separator;

				for (int j = 0; j < x.methods.size(); j++)
					content += "<metodo " + printmethod(x.methods.get(j)) + "</metodo>" + separator;

				content += "</metodos>" + separator;
			}

			if (x.classes.size() > 0) {
				content += "<clases total=\"" + x.classes.size() + "\">" + separator;

				for (int j = 0; j < x.classes.size(); j++)
					content += printclass(x.classes.get(j));

				content += "</clases>" + separator;
			}

			if (x.interfaces.size() > 0) {
				content += "<interfaces total=\"" + x.interfaces.size() + "\">" + separator;

				for (int j = 0; j < x.interfaces.size(); j++)
					content += printinterface(x.interfaces.get(j));

				content += "</interfaces>"+separator;
			}

			content = opentag + content + "</clase>" + separator;

		return content;
	}

	private static String printinterface(CustomInterface x) {

		String content = "<interfaz nombre=\"" + x.name + "\" numerolineas=\"" + x.numeroLineas + "\">";

		if (x.interfaceModifiers.size()  > 0) {
			content += "<modificadores total=\"" + x.interfaceModifiers.size() + "\">" + separator;

			for (int j = 0; j < x.interfaceModifiers.size(); j++)
				content += "<modificador>" + x.interfaceModifiers.get(j) + "</modificador>" + separator;

			content += "</modificadores>";
		}

		if (x.superInterfaces.size() > 0) {
			content += "<superInterfaces total=\"" + x.superInterfaces.size() + "\">" + separator;

			for (int j = 0; j < x.superInterfaces.size(); j++)
				content += "<superInterfaz>" + x.superInterfaces.get(j).replace("&","&amp;").replace("<", "&lt;").replace(">", "&gt;") + "</superInterfaz>" + separator;

			content += "</superInterfaces>";
		}

		if (x.typeParameters.size() > 0) {
			content += "<typeParameters total=\"" + x.typeParameters.size() + "\">" + separator;

			for (int j = 0; j < x.typeParameters.size(); j++)
				content += "<typeParameter>" + x.typeParameters.get(j) + "</typeParameter>" + separator;

			content += "</typeParameters>";
		}

		if (x.constants.size() > 0) {
			content += "<constantes total=\"" + x.constants.size() + "\">" + separator;

			for (int j = 0; j < x.constants.size(); j++)
				content += "<constante " + printvariable(x.constants.get(j)) + "</constante>" + separator;

			content += "</constantes>" + separator;
		}

		if (x.methods.size() > 0) {
			content += "<metodos total=\"" + x.methods.size() + "\">" + separator;

			for (int j = 0; j < x.methods.size(); j++)
				content += "<metodo " + printmethod(x.methods.get(j)) + "</metodo>" + separator;

			content += "</metodos>" + separator;
		}

		if (x.classes.size() > 0) {
			content += "<clases total=\"" + x.classes.size() + "\">" + separator;
			for (int j = 0; j < x.classes.size(); j++) {
				content += printclass(x.classes.get(j));
			}
			content += "</clases>" + separator;
		}

		if (x.interfaces.size() > 0) {
			content += "<interfaces total=\"" + x.interfaces.size() + "\">" + separator;

			for (int j = 0; j < x.interfaces.size(); j++)
				content += printinterface(x.interfaces.get(j));

			content += "</interfaces>" + separator;
		}
			content += "</interfaz>";
		return content;
	}

	private static String printmethod(CustomMethod x) {

		String content = "nombre=\"" + x.name + "\" ";

		if (x.result != null)
			content += "resultado=\"" + x.result + "\" ";

		content += "numerolineas=\"" + x.numeroLineas + "\">";

		if (x.methodModifiers.size() > 0) {
			content += "<modificadores total=\"" + x.methodModifiers.size() + "\">" + separator;

			for (int j = 0; j < x.methodModifiers.size(); j++)
				content += "<modificador>" + x.methodModifiers.get(j) + "</modificador>" + separator;

			content += "</modificadores>";
		}

		if (x.parameters.size() > 0) {
			content += "<parametros total=\"" + x.parameters.size() + "\">";

			for (int j = 0; j < x.parameters.size(); j++)
				content += "<parametro " + printvariable(x.parameters.get(j)) + "</parametro>" + separator;

			content += "</parametros>";
		}

		if (x.exceptions.size() > 0) {
			content += "<excepciones total=\"" + x.exceptions.size() + "\">" + separator;

			for (int j = 0; j < x.exceptions.size(); j++)
				content += "<excepcion>" + x.exceptions.get(j) + "</excepcion>" + separator;

			content += "</excepciones>";
		}

		if (x.variables.size() > 0) {
			content += "<variables total=\"" + x.variables.size() + "\">";

			for (int j = 0; j < x.variables.size(); j++)
				content += "<variable " + printvariable(x.variables.get(j)) + "</variable>" + separator;

			content += "</variables>";
		}

		if (x.statementTypes.size() > 0) {

			content += "<sentencias total=\"" + x.statementTypes.size() + "\">";

			Iterator iterate = x.statementTypes.entrySet().iterator();
			while (iterate.hasNext()) {
				Map.Entry statement = (Map.Entry) iterate.next();
				content += "<sentencia " + printstatement(statement) + "</sentencia>";
				iterate.remove();
			}

			content += "</sentencias>";
		}

		return content;
	}

	private static String printvariable(CustomVariable x) {

		String content = "nombre=\"" + x.name.replace("&","&amp;").replace("<", "&lt;").replace(">", "&gt;") + "\" " + "tipo=\"" + x.type.replace("&","&amp;").replace("<", "&lt;").replace(">", "&gt;") + "\" ";

		if (x.value != null)
			content +=  "valor=\"" + x.value.replace("\"", "&quot;").replace("&","&amp;").replace("<", "&lt;").replace(">", "&gt;") + "\">";
		else content += ">";

		if (x.modifiers.size() > 0) {
			content += "<modificadores total=\"" + x.modifiers.size() + "\">";

			for (int j = 0; j < x.modifiers.size(); j++)
				content += "<modificador>" + x.modifiers.get(j) + "</modificador>";

			content += "</modificadores>";
		}
		return content;
	}

	private static String printstatement(Map.Entry statement) {
		String content = "Nombre=\"" + String.valueOf(statement.getKey()).replace("&","&amp;").replace("<", "&lt;").replace(">", "&gt;") + "\" " + "apariciones=\"" + String.valueOf(statement.getValue()).replace("&","&amp;").replace("<", "&lt;").replace(">", "&gt;") + "\" >";
		return content;
	}

}
