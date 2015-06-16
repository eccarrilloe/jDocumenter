import java.util.List;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

class Documenter
{
	public static String xml; 
	
	static String separator="\n";
	
	public static void main(String[] args) throws Exception
	{
		try
		{

			String filename = args.length > 0 ? args[0] : "Bank.java";
			JavaLexer lexer = new JavaLexer(new ANTLRFileStream(filename));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			JavaParser parser = new JavaParser(tokens);
			ParseTree tree = parser.compilationUnit();
			ParseTreeWalker walker = new ParseTreeWalker();
			DocumenterListener listener = new DocumenterListener();

			walker.walk(listener, tree);
					
			printimports(listener.imports);
			
			printclasses(listener.classes);
			
			printinterfaces(listener.interfaces);
			
	    }
	    catch (Exception e)
	    {
	    	e.printStackTrace();
	    }
		xml="<code>"+xml+"</code>";
		System.out.println(xml);
	}

	private static void printinterfaces(List<CustomInterface> interfaces) {
	}
	
	private static void printimports(List<CustomImport> imports) {
		if (!imports.isEmpty()){
		xml+="<imports total=\""+imports.size()+"\">"+separator;
		for (CustomImport x : imports) {
			xml+="<import>"+x.name+"</import>"+separator;
		}
		xml+="</imports>"+separator;
		}	
	}
	
	private static void printclasses(List<CustomClass> classes){
		if (!classes.isEmpty()){
		
			xml+="<clases total=\""+classes.size()+"\">"+separator;
		
		for (CustomClass x : classes) {
			
			String opentag="<clase name=\""+x.name+"\" ";
			String content="";
			
			if (x.classModifiers.size()>0){
				for (int j = 0; j < x.classModifiers.size(); j++){
					opentag+="modificador_"+j+"=\""+x.classModifiers.get(j)+"\" ";
				}
			}
			
			if (x.superClass!=null) {
				opentag+="superClass=\""+x.superClass+"\" ";
			}
			
			if (x.superInterfaces.size()>0){
				for (int j = 0; j < x.superInterfaces.size(); j++){
					opentag+="superInterfaz_"+j+"=\""+x.superInterfaces.get(j).replace("<", "&lt;").replace(">", "&gt;")+"\" ";
				}
			}

			if (x.typeParameters.size()>0){
				for (int j = 0; j < x.typeParameters.size(); j++){
					opentag+="typeParameter"+j+"=\""+x.typeParameters.get(j)+"\" ";
				}
			}
			opentag=opentag.trim()+">"+separator;
			
			if (x.constants.size()>0){
				content+="<constants total=\""+x.constants.size()+"\">"+separator;
				for (int j = 0; j < x.constants.size(); j++){
					content+="<constant "+printvariable(x.constants.get(j))+"</constant>"+separator;
				}
				content+="</constants>"+separator;
			}
			
			if (x.variables.size()>0){
				content+="<variables total=\""+x.variables.size()+"\">"+separator;
				for (int j = 0; j < x.variables.size(); j++){
					content+="<variable "+printvariable(x.variables.get(j))+"</variable>"+separator;
				}
				content+="</variables>"+separator;
			}
			
			if (x.constructors.size()>0){
				content+="<constructores total=\""+x.constructors.size()+"\">"+separator;
				for (int j = 0; j < x.constructors.size(); j++){
					content+="<constructor "+printmethod(x.constructors.get(j))+"</constructor>"+separator;
				}
				content+="</constructores>"+separator;
			}
			
			if (x.methods.size()>0){
				content+="<methods total=\""+x.methods.size()+"\">"+separator;
				for (int j = 0; j < x.methods.size(); j++){
					content+="<method "+printmethod(x.methods.get(j))+"</method>"+separator;
				}
				content+="</methods>"+separator;
			}
			
			if (x.classes.size()>0){
				content+="<classes total=\""+x.classes.size()+"\">"+separator;
				for (int j = 0; j < x.classes.size(); j++){
					content+=printclass(x.classes.get(j));
				}
				content+="</classes>"+separator;
			}
			
			if (x.interfaces.size()>0){
				content+="<interfaces total=\""+x.interfaces.size()+"\">"+separator;
				for (int j = 0; j < x.interfaces.size(); j++){
					content+=printinterfas(x.interfaces.get(j));
				}
				content+="</interfaces>"+separator;
			}
						
			xml+=opentag+content+"</clase>"+separator;
		}
		xml+="</clases>"+separator;
		}
	}


	private static String printinterfas(CustomInterface customInterface) {
		String content="";
		
		content+="name=\""+customInterface.name+"\" ";
		for (int j = 0; j < customInterface.interfaceModifiers.size(); j++){
			content+="modificador_"+j+"=\""+customInterface.interfaceModifiers.get(j)+"\" ";
		}
		for (int j = 0; j < customInterface.superInterfaces.size(); j++){
			content+="superInterface_"+j+"=\""+customInterface.superInterfaces.get(j)+"\" ";
		}
		for (int j = 0; j < customInterface.typeParameters.size(); j++){
			content+="typeParameter_"+j+"=\""+customInterface.typeParameters.get(j)+"\" ";
		}
		content+=">";
		
		if (customInterface.constants.size()>0){
			content+="<constants total=\""+customInterface.constants.size()+"\">"+separator;
			for (int j = 0; j < customInterface.constants.size(); j++){
				content+="<constant "+printvariable(customInterface.constants.get(j))+"</constant>"+separator;
			}
			content+="</constants>"+separator;
		}		
		
		if (customInterface.methods.size()>0){
			content+="<methods total=\""+customInterface.methods.size()+"\">"+separator;
			for (int j = 0; j < customInterface.methods.size(); j++){
				content+="<method "+printmethod(customInterface.methods.get(j))+"</method>"+separator;
			}
			content+="</methods>"+separator;
		}

		if (customInterface.classes.size()>0){
			content+="<classes total=\""+customInterface.classes.size()+"\">"+separator;
			for (int j = 0; j < customInterface.classes.size(); j++){
				content+=printclass(customInterface.classes.get(j));
			}
			content+="</classes>"+separator;
		}
		
		if (customInterface.interfaces.size()>0){
			content+="<interfaces total=\""+customInterface.interfaces.size()+"\">"+separator;
			for (int j = 0; j < customInterface.interfaces.size(); j++){
				content+=printinterfas(customInterface.interfaces.get(j));
			}
			content+="</interfaces>"+separator;
		}
		return content;
	}


	private static String printclass(CustomClass customClass) {
		// TODO Auto-generated method stub
		return null;
	}

	private static String printmethod(CustomMethod customMethod) {
		String content="";
		content+="name=\""+customMethod.name+"\" resultado=\""+customMethod.result+"\" ";
		for (int j = 0; j < customMethod.exceptions.size(); j++){
		content+="excepcion_"+j+"=\""+customMethod.exceptions.get(j)+"\" ";
		}
		for (int j = 0; j < customMethod.methodModifiers.size(); j++){
		content+="modificador_"+j+"=\""+customMethod.methodModifiers.get(j)+"\" ";
		}
		content+=">";
		
		if (customMethod.parameters.size()>0){
			content+="<parametros total=\""+customMethod.parameters.size()+"\">";
			for (int j = 0; j < customMethod.parameters.size(); j++){
				content+="<parametro "+printvariable(customMethod.parameters.get(j))+"</parametro>"+separator;
			}
			content+="</parametros>";
		}
		
		if (customMethod.variables.size()>0){
			content+="<variables total=\""+customMethod.variables.size()+"\">";
			for (int j = 0; j < customMethod.variables.size(); j++){
				content+="<variable "+printvariable(customMethod.variables.get(j))+"</variable>"+separator;
			}
			content+="</variables>";
		}
		
		return content;
	}


	private static String printvariable(CustomVariable customVariable) {
		String content="name=\""+customVariable.name.replace("<", "&lt;").replace(">", "&gt;")+"\" "
				+ "type=\""+customVariable.type.replace("<", "&lt;").replace(">", "&gt;")+"\" ";
		
		for (int j = 0; j < customVariable.modifiers.size(); j++){
			content+="modificador_"+j+"=\""+customVariable.modifiers.get(j)+"\" ";
		}
		
		content+=">"+customVariable.value;
		
		return content;
	}

}