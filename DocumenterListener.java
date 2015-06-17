import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Stack;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class DocumenterListener extends JavaBaseListener {

	/** Symbol Table **/
	public Map<String, String> symbols = new HashMap<>();
	public Stack<Object> tracer = new Stack<>();
	private Object data;

	/** Documenter Data **/
	public String packageName;
	public List<CustomImport> imports = new ArrayList<>();
	public List<CustomClass> classes = new ArrayList<>();
	public List<CustomInterface> interfaces = new ArrayList<>();

	/** Event Listeners **/
	@Override public void enterLiteral(JavaParser.LiteralContext ctx) { }
	@Override public void exitLiteral(JavaParser.LiteralContext ctx) { }
	@Override public void enterType(JavaParser.TypeContext ctx) { }
	@Override public void exitType(JavaParser.TypeContext ctx) { }
	@Override public void enterPrimitiveType(JavaParser.PrimitiveTypeContext ctx) { }
	@Override public void exitPrimitiveType(JavaParser.PrimitiveTypeContext ctx) { }
	@Override public void enterNumericType(JavaParser.NumericTypeContext ctx) { }
	@Override public void exitNumericType(JavaParser.NumericTypeContext ctx) { }
	@Override public void enterIntegralType(JavaParser.IntegralTypeContext ctx) { }
	@Override public void exitIntegralType(JavaParser.IntegralTypeContext ctx) { }
	@Override public void enterFloatingPointType(JavaParser.FloatingPointTypeContext ctx) { }
	@Override public void exitFloatingPointType(JavaParser.FloatingPointTypeContext ctx) { }
	@Override public void enterReferenceType(JavaParser.ReferenceTypeContext ctx) { }
	@Override public void exitReferenceType(JavaParser.ReferenceTypeContext ctx) { }
	@Override public void enterClassOrInterfaceType(JavaParser.ClassOrInterfaceTypeContext ctx) { }
	@Override public void exitClassOrInterfaceType(JavaParser.ClassOrInterfaceTypeContext ctx) { }
	@Override public void enterClassType(JavaParser.ClassTypeContext ctx) { }
	@Override public void exitClassType(JavaParser.ClassTypeContext ctx) { }
	@Override public void enterClassType_lf_classOrInterfaceType(JavaParser.ClassType_lf_classOrInterfaceTypeContext ctx) { }
	@Override public void exitClassType_lf_classOrInterfaceType(JavaParser.ClassType_lf_classOrInterfaceTypeContext ctx) { }
	@Override public void enterClassType_lfno_classOrInterfaceType(JavaParser.ClassType_lfno_classOrInterfaceTypeContext ctx) { }
	@Override public void exitClassType_lfno_classOrInterfaceType(JavaParser.ClassType_lfno_classOrInterfaceTypeContext ctx) { }
	@Override public void enterInterfaceType(JavaParser.InterfaceTypeContext ctx) { }
	@Override public void exitInterfaceType(JavaParser.InterfaceTypeContext ctx) { }
	@Override public void enterInterfaceType_lf_classOrInterfaceType(JavaParser.InterfaceType_lf_classOrInterfaceTypeContext ctx) { }
	@Override public void exitInterfaceType_lf_classOrInterfaceType(JavaParser.InterfaceType_lf_classOrInterfaceTypeContext ctx) { }
	@Override public void enterInterfaceType_lfno_classOrInterfaceType(JavaParser.InterfaceType_lfno_classOrInterfaceTypeContext ctx) { }
	@Override public void exitInterfaceType_lfno_classOrInterfaceType(JavaParser.InterfaceType_lfno_classOrInterfaceTypeContext ctx) { }
	@Override public void enterTypeVariable(JavaParser.TypeVariableContext ctx) { }
	@Override public void exitTypeVariable(JavaParser.TypeVariableContext ctx) { }
	@Override public void enterArrayType(JavaParser.ArrayTypeContext ctx) { }
	@Override public void exitArrayType(JavaParser.ArrayTypeContext ctx) { }
	@Override public void enterDims(JavaParser.DimsContext ctx) { }
	@Override public void exitDims(JavaParser.DimsContext ctx) { }
	@Override public void enterTypeParameter(JavaParser.TypeParameterContext ctx) { }
	@Override public void exitTypeParameter(JavaParser.TypeParameterContext ctx) { }
	@Override public void enterTypeParameterModifier(JavaParser.TypeParameterModifierContext ctx) { }
	@Override public void exitTypeParameterModifier(JavaParser.TypeParameterModifierContext ctx) { }
	@Override public void enterTypeBound(JavaParser.TypeBoundContext ctx) { }
	@Override public void exitTypeBound(JavaParser.TypeBoundContext ctx) { }
	@Override public void enterAdditionalBound(JavaParser.AdditionalBoundContext ctx) { }
	@Override public void exitAdditionalBound(JavaParser.AdditionalBoundContext ctx) { }
	@Override public void enterTypeArguments(JavaParser.TypeArgumentsContext ctx) { }
	@Override public void exitTypeArguments(JavaParser.TypeArgumentsContext ctx) { }
	@Override public void enterTypeArgumentList(JavaParser.TypeArgumentListContext ctx) { }
	@Override public void exitTypeArgumentList(JavaParser.TypeArgumentListContext ctx) { }
	@Override public void enterTypeArgument(JavaParser.TypeArgumentContext ctx) { }
	@Override public void exitTypeArgument(JavaParser.TypeArgumentContext ctx) { }
	@Override public void enterWildcard(JavaParser.WildcardContext ctx) { }
	@Override public void exitWildcard(JavaParser.WildcardContext ctx) { }
	@Override public void enterWildcardBounds(JavaParser.WildcardBoundsContext ctx) { }
	@Override public void exitWildcardBounds(JavaParser.WildcardBoundsContext ctx) { }
	@Override public void enterPackageName(JavaParser.PackageNameContext ctx) { }
	@Override public void exitPackageName(JavaParser.PackageNameContext ctx) { }
	@Override public void enterTypeName(JavaParser.TypeNameContext ctx) { }
	@Override public void exitTypeName(JavaParser.TypeNameContext ctx) { }
	@Override public void enterPackageOrTypeName(JavaParser.PackageOrTypeNameContext ctx) { }
	@Override public void exitPackageOrTypeName(JavaParser.PackageOrTypeNameContext ctx) { }
	@Override public void enterExpressionName(JavaParser.ExpressionNameContext ctx) { }
	@Override public void exitExpressionName(JavaParser.ExpressionNameContext ctx) { }
	@Override public void enterMethodName(JavaParser.MethodNameContext ctx) { }
	@Override public void exitMethodName(JavaParser.MethodNameContext ctx) { }
	@Override public void enterAmbiguousName(JavaParser.AmbiguousNameContext ctx) { }
	@Override public void exitAmbiguousName(JavaParser.AmbiguousNameContext ctx) { }
	@Override public void enterCompilationUnit(JavaParser.CompilationUnitContext ctx) {  }
	@Override public void exitCompilationUnit(JavaParser.CompilationUnitContext ctx) { }
	@Override public void enterPackageDeclaration(JavaParser.PackageDeclarationContext ctx) { }
	@Override public void exitPackageDeclaration(JavaParser.PackageDeclarationContext ctx) {
		List<TerminalNode> packageChunks = ctx.Identifier();
		packageName = packageChunks.get(0).getText();
		for (int i = 1; i < packageChunks.size(); i++) packageName += "." + packageChunks.get(i).getText();
	}
	@Override public void enterPackageModifier(JavaParser.PackageModifierContext ctx) { }
	@Override public void exitPackageModifier(JavaParser.PackageModifierContext ctx) { }
	@Override public void enterImportDeclaration(JavaParser.ImportDeclarationContext ctx) { }
	@Override public void exitImportDeclaration(JavaParser.ImportDeclarationContext ctx) { }
	@Override public void enterSingleTypeImportDeclaration(JavaParser.SingleTypeImportDeclarationContext ctx) { }
	@Override public void exitSingleTypeImportDeclaration(JavaParser.SingleTypeImportDeclarationContext ctx) {
		imports.add(new CustomImport(ctx.typeName().getText(), "Importacion de tipo unico"));
	}
	@Override public void enterTypeImportOnDemandDeclaration(JavaParser.TypeImportOnDemandDeclarationContext ctx) {}
	@Override public void exitTypeImportOnDemandDeclaration(JavaParser.TypeImportOnDemandDeclarationContext ctx) {
		imports.add(new CustomImport(ctx.packageOrTypeName().getText() + ".*", "Importacion por demanda"));
	}
	@Override public void enterSingleStaticImportDeclaration(JavaParser.SingleStaticImportDeclarationContext ctx) { }
	@Override public void exitSingleStaticImportDeclaration(JavaParser.SingleStaticImportDeclarationContext ctx) {
		imports.add(new CustomImport(ctx.typeName().getText() + "." + ctx.Identifier().getText(), "Importacion statica de tipo unico"));
	}
	@Override public void enterStaticImportOnDemandDeclaration(JavaParser.StaticImportOnDemandDeclarationContext ctx) { }
	@Override public void exitStaticImportOnDemandDeclaration(JavaParser.StaticImportOnDemandDeclarationContext ctx) {
		imports.add(new CustomImport(ctx.typeName().getText() + ".*", "Importacion statica de tipo unico"));
	}
	@Override public void enterTypeDeclaration(JavaParser.TypeDeclarationContext ctx) { }
	@Override public void exitTypeDeclaration(JavaParser.TypeDeclarationContext ctx) { }
	@Override public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx) { }
	@Override public void exitClassDeclaration(JavaParser.ClassDeclarationContext ctx) { }
	@Override public void enterNormalClassDeclaration(JavaParser.NormalClassDeclarationContext ctx) {
		List<String> classModifiers = new ArrayList<>();
		List<String> typeParameters = new ArrayList<>();
		List<String> superInterfaces = new ArrayList<>();
		String className = ctx.Identifier().getText();
		String superClass = ctx.superclass() != null ? ctx.superclass().classType().getText() : null;
		Integer numeroLineas = ctx.stop.getLine() - ctx.start.getLine();

		if (ctx.classModifier() != null)
			for (JavaParser.ClassModifierContext x : ctx.classModifier())
				classModifiers.add(x.getText());

		if (ctx.typeParameters() != null)
			for (JavaParser.TypeParameterContext x : ctx.typeParameters().typeParameterList().typeParameter())
				typeParameters.add(x.getText());

		if (ctx.superinterfaces() != null)
			for (JavaParser.InterfaceTypeContext x : ctx.superinterfaces().interfaceTypeList().interfaceType())
				superInterfaces.add(x.getText());

		CustomClass currentClass = new CustomClass(className, classModifiers, typeParameters, superClass, superInterfaces, numeroLineas);
		if (tracer.size() == 0)
			classes.add(currentClass);
		else if (tracer.peek() instanceof CustomClass)
			((CustomClass) tracer.peek()).classes.add(currentClass);
		else if (tracer.peek() instanceof CustomInterface)
			((CustomInterface) tracer.peek()).classes.add(currentClass);

		tracer.push((Object) currentClass);
	}
	@Override public void exitNormalClassDeclaration(JavaParser.NormalClassDeclarationContext ctx) {
		tracer.pop();
	}
	@Override public void enterClassModifier(JavaParser.ClassModifierContext ctx) { }
	@Override public void exitClassModifier(JavaParser.ClassModifierContext ctx) { }
	@Override public void enterTypeParameters(JavaParser.TypeParametersContext ctx) { }
	@Override public void exitTypeParameters(JavaParser.TypeParametersContext ctx) { }
	@Override public void enterTypeParameterList(JavaParser.TypeParameterListContext ctx) { }
	@Override public void exitTypeParameterList(JavaParser.TypeParameterListContext ctx) { }
	@Override public void enterSuperclass(JavaParser.SuperclassContext ctx) { }
	@Override public void exitSuperclass(JavaParser.SuperclassContext ctx) { }
	@Override public void enterSuperinterfaces(JavaParser.SuperinterfacesContext ctx) { }
	@Override public void exitSuperinterfaces(JavaParser.SuperinterfacesContext ctx) { }
	@Override public void enterInterfaceTypeList(JavaParser.InterfaceTypeListContext ctx) { }
	@Override public void exitInterfaceTypeList(JavaParser.InterfaceTypeListContext ctx) { }
	@Override public void enterClassBody(JavaParser.ClassBodyContext ctx) { }
	@Override public void exitClassBody(JavaParser.ClassBodyContext ctx) { }
	@Override public void enterClassBodyDeclaration(JavaParser.ClassBodyDeclarationContext ctx) { }
	@Override public void exitClassBodyDeclaration(JavaParser.ClassBodyDeclarationContext ctx) { }
	@Override public void enterClassMemberDeclaration(JavaParser.ClassMemberDeclarationContext ctx) { }
	@Override public void exitClassMemberDeclaration(JavaParser.ClassMemberDeclarationContext ctx) { }
	@Override public void enterFieldDeclaration(JavaParser.FieldDeclarationContext ctx) {
		List<String> fieldModifiers = new ArrayList<>();
		String fieldType = ctx.unannType().getText();
		CustomClass currentClass = (CustomClass) tracer.peek();
		boolean isConstant = false;

		if (ctx.unannType().unannReferenceType() != null && ctx.unannType().unannReferenceType().unannArrayType() != null)
			ctx.unannType().unannReferenceType().unannArrayType();

		if (ctx.fieldModifier() != null)
			for (JavaParser.FieldModifierContext x : ctx.fieldModifier())
				fieldModifiers.add(x.getText());

		for (String modifier : fieldModifiers)
			if (modifier.toLowerCase().equals("final")) {
				isConstant = true;
				break;
			}

		for (JavaParser.VariableDeclaratorContext x : ctx.variableDeclaratorList().variableDeclarator()) {
			String name = x.variableDeclaratorId().Identifier().getText();
			String value = x.variableInitializer() != null ? x.variableInitializer().getText() : null;
			String varType = String.valueOf(fieldType);

			if (x.variableDeclaratorId().dims() != null)
				varType += x.variableDeclaratorId().dims().getText();

			CustomVariable var = new CustomVariable(name, fieldModifiers, varType, value);

			if (isConstant) currentClass.constants.add(var);
			else currentClass.variables.add(var);
		}
	}
	@Override public void exitFieldDeclaration(JavaParser.FieldDeclarationContext ctx) { }
	@Override public void enterFieldModifier(JavaParser.FieldModifierContext ctx) { }
	@Override public void exitFieldModifier(JavaParser.FieldModifierContext ctx) { }
	@Override public void enterVariableDeclaratorList(JavaParser.VariableDeclaratorListContext ctx) { }
	@Override public void exitVariableDeclaratorList(JavaParser.VariableDeclaratorListContext ctx) { }
	@Override public void enterVariableDeclarator(JavaParser.VariableDeclaratorContext ctx) { }
	@Override public void exitVariableDeclarator(JavaParser.VariableDeclaratorContext ctx) { }
	@Override public void enterVariableDeclaratorId(JavaParser.VariableDeclaratorIdContext ctx) { }
	@Override public void exitVariableDeclaratorId(JavaParser.VariableDeclaratorIdContext ctx) { }
	@Override public void enterVariableInitializer(JavaParser.VariableInitializerContext ctx) { }
	@Override public void exitVariableInitializer(JavaParser.VariableInitializerContext ctx) { }
	@Override public void enterUnannType(JavaParser.UnannTypeContext ctx) { }
	@Override public void exitUnannType(JavaParser.UnannTypeContext ctx) { }
	@Override public void enterUnannPrimitiveType(JavaParser.UnannPrimitiveTypeContext ctx) { }
	@Override public void exitUnannPrimitiveType(JavaParser.UnannPrimitiveTypeContext ctx) { }
	@Override public void enterUnannReferenceType(JavaParser.UnannReferenceTypeContext ctx) { }
	@Override public void exitUnannReferenceType(JavaParser.UnannReferenceTypeContext ctx) { }
	@Override public void enterUnannClassOrInterfaceType(JavaParser.UnannClassOrInterfaceTypeContext ctx) { }
	@Override public void exitUnannClassOrInterfaceType(JavaParser.UnannClassOrInterfaceTypeContext ctx) { }
	@Override public void enterUnannClassType(JavaParser.UnannClassTypeContext ctx) { }
	@Override public void exitUnannClassType(JavaParser.UnannClassTypeContext ctx) { }
	@Override public void enterUnannClassType_lf_unannClassOrInterfaceType(JavaParser.UnannClassType_lf_unannClassOrInterfaceTypeContext ctx) { }
	@Override public void exitUnannClassType_lf_unannClassOrInterfaceType(JavaParser.UnannClassType_lf_unannClassOrInterfaceTypeContext ctx) { }
	@Override public void enterUnannClassType_lfno_unannClassOrInterfaceType(JavaParser.UnannClassType_lfno_unannClassOrInterfaceTypeContext ctx) { }
	@Override public void exitUnannClassType_lfno_unannClassOrInterfaceType(JavaParser.UnannClassType_lfno_unannClassOrInterfaceTypeContext ctx) { }
	@Override public void enterUnannInterfaceType(JavaParser.UnannInterfaceTypeContext ctx) { }
	@Override public void exitUnannInterfaceType(JavaParser.UnannInterfaceTypeContext ctx) { }
	@Override public void enterUnannInterfaceType_lf_unannClassOrInterfaceType(JavaParser.UnannInterfaceType_lf_unannClassOrInterfaceTypeContext ctx) { }
	@Override public void exitUnannInterfaceType_lf_unannClassOrInterfaceType(JavaParser.UnannInterfaceType_lf_unannClassOrInterfaceTypeContext ctx) { }
	@Override public void enterUnannInterfaceType_lfno_unannClassOrInterfaceType(JavaParser.UnannInterfaceType_lfno_unannClassOrInterfaceTypeContext ctx) { }
	@Override public void exitUnannInterfaceType_lfno_unannClassOrInterfaceType(JavaParser.UnannInterfaceType_lfno_unannClassOrInterfaceTypeContext ctx) { }
	@Override public void enterUnannTypeVariable(JavaParser.UnannTypeVariableContext ctx) { }
	@Override public void exitUnannTypeVariable(JavaParser.UnannTypeVariableContext ctx) { }
	@Override public void enterUnannArrayType(JavaParser.UnannArrayTypeContext ctx) { }
	@Override public void exitUnannArrayType(JavaParser.UnannArrayTypeContext ctx) { }
	@Override public void enterMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
		List<String> methodModifiers = new ArrayList<>();
		List<String> methodExceptions = new ArrayList<>();
		List<CustomVariable> methodParameters = new ArrayList<>();
		String name = ctx.methodHeader().methodDeclarator().Identifier().getText();
		String result = ctx.methodHeader().result().getText();
		Integer numeroLineas = ctx.stop.getLine() - ctx.start.getLine();

		if (ctx.methodModifier() != null)
			for (JavaParser.MethodModifierContext x : ctx.methodModifier())
				methodModifiers.add(x.getText());

		if (ctx.methodHeader().throws_() != null)
			for (JavaParser.ExceptionTypeContext x : ctx.methodHeader().throws_().exceptionTypeList().exceptionType())
				methodExceptions.add(x.getText());

		if (ctx.methodHeader().methodDeclarator().formalParameterList() != null) {
			/** Parameters **/
			if (ctx.methodHeader().methodDeclarator().formalParameterList().formalParameters() != null) {
				for (JavaParser.FormalParameterContext x : ctx.methodHeader().methodDeclarator().formalParameterList().formalParameters().formalParameter()) {
					List<String> parameterModifiers = new ArrayList<>();
					String parameterName = x.variableDeclaratorId().Identifier().getText();
					String type = x.unannType().getText();

					if (x.variableModifier() != null)
						for (JavaParser.VariableModifierContext y : x.variableModifier())
							parameterModifiers.add(y.getText());

					CustomVariable parameter = new CustomVariable(parameterName, parameterModifiers, type, null);
					methodParameters.add(parameter);
				}
			}

			/** Last Parameter **/
			JavaParser.LastFormalParameterContext lastParameter = ctx.methodHeader().methodDeclarator().formalParameterList().lastFormalParameter();
			CustomVariable parameter;
			List<String> modifiers = new ArrayList<>();
			String parameterName, type;
			if (lastParameter.formalParameter() != null) {
				parameterName = lastParameter.formalParameter().variableDeclaratorId().Identifier().getText();
				type = lastParameter.formalParameter().unannType().getText();

				if (lastParameter.formalParameter().variableModifier() != null)
					for (JavaParser.VariableModifierContext x : lastParameter.formalParameter().variableModifier())
						modifiers.add(x.getText());
			} else {
				parameterName = lastParameter.variableDeclaratorId().Identifier().getText();
				type = lastParameter.unannType().getText();
				if (lastParameter.variableModifier() != null)
					for (JavaParser.VariableModifierContext x : lastParameter.variableModifier())
						modifiers.add(x.getText());
			}
			parameter = new CustomVariable(name, modifiers, type, null);
			methodParameters.add(parameter);
		}

		CustomMethod currentMethod = new CustomMethod(name, methodModifiers, result, methodParameters, methodExceptions, numeroLineas);
		CustomClass currentClass = (CustomClass) tracer.peek();
		currentClass.methods.add(currentMethod);
		tracer.push((Object) currentMethod);
	}
	@Override public void exitMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
		tracer.pop();
	}
	@Override public void enterMethodModifier(JavaParser.MethodModifierContext ctx) { }
	@Override public void exitMethodModifier(JavaParser.MethodModifierContext ctx) { }
	@Override public void enterMethodHeader(JavaParser.MethodHeaderContext ctx) { }
	@Override public void exitMethodHeader(JavaParser.MethodHeaderContext ctx) { }
	@Override public void enterResult(JavaParser.ResultContext ctx) { }
	@Override public void exitResult(JavaParser.ResultContext ctx) { }
	@Override public void enterMethodDeclarator(JavaParser.MethodDeclaratorContext ctx) { }
	@Override public void exitMethodDeclarator(JavaParser.MethodDeclaratorContext ctx) { }
	@Override public void enterFormalParameterList(JavaParser.FormalParameterListContext ctx) { }
	@Override public void exitFormalParameterList(JavaParser.FormalParameterListContext ctx) { }
	@Override public void enterFormalParameters(JavaParser.FormalParametersContext ctx) { }
	@Override public void exitFormalParameters(JavaParser.FormalParametersContext ctx) { }
	@Override public void enterFormalParameter(JavaParser.FormalParameterContext ctx) { }
	@Override public void exitFormalParameter(JavaParser.FormalParameterContext ctx) { }
	@Override public void enterVariableModifier(JavaParser.VariableModifierContext ctx) { }
	@Override public void exitVariableModifier(JavaParser.VariableModifierContext ctx) { }
	@Override public void enterLastFormalParameter(JavaParser.LastFormalParameterContext ctx) { }
	@Override public void exitLastFormalParameter(JavaParser.LastFormalParameterContext ctx) { }
	@Override public void enterReceiverParameter(JavaParser.ReceiverParameterContext ctx) { }
	@Override public void exitReceiverParameter(JavaParser.ReceiverParameterContext ctx) { }
	@Override public void enterThrows_(JavaParser.Throws_Context ctx) { }
	@Override public void exitThrows_(JavaParser.Throws_Context ctx) { }
	@Override public void enterExceptionTypeList(JavaParser.ExceptionTypeListContext ctx) { }
	@Override public void exitExceptionTypeList(JavaParser.ExceptionTypeListContext ctx) { }
	@Override public void enterExceptionType(JavaParser.ExceptionTypeContext ctx) { }
	@Override public void exitExceptionType(JavaParser.ExceptionTypeContext ctx) { }
	@Override public void enterMethodBody(JavaParser.MethodBodyContext ctx) { }
	@Override public void exitMethodBody(JavaParser.MethodBodyContext ctx) { }
	@Override public void enterInstanceInitializer(JavaParser.InstanceInitializerContext ctx) { }
	@Override public void exitInstanceInitializer(JavaParser.InstanceInitializerContext ctx) { }
	@Override public void enterStaticInitializer(JavaParser.StaticInitializerContext ctx) { }
	@Override public void exitStaticInitializer(JavaParser.StaticInitializerContext ctx) { }
	@Override public void enterConstructorDeclaration(JavaParser.ConstructorDeclarationContext ctx) {
		List<String> modifiers = new ArrayList<>();
		List<CustomVariable> parameters = new ArrayList<>();
		List<String> exceptions = new ArrayList<>();
		String name = ctx.constructorDeclarator().simpleTypeName().Identifier().getText();
		Integer numeroLineas = ctx.stop.getLine() - ctx.start.getLine();

		if (ctx.constructorModifier() != null)
			for (JavaParser.ConstructorModifierContext x : ctx.constructorModifier())
				modifiers.add(x.getText());

		if (ctx.throws_() != null)
			for (JavaParser.ExceptionTypeContext x : ctx.throws_().exceptionTypeList().exceptionType())
				exceptions.add(x.getText());

		if (ctx.constructorDeclarator().formalParameterList() != null) {
			/** Parameters **/
			if (ctx.constructorDeclarator().formalParameterList().formalParameters() != null) {
				for (JavaParser.FormalParameterContext x : ctx.constructorDeclarator().formalParameterList().formalParameters().formalParameter()) {
					List<String> parameterModifiers = new ArrayList<>();
					String parameterName = x.variableDeclaratorId().Identifier().getText();
					String type = x.unannType().getText();

					if (x.variableModifier() != null)
						for (JavaParser.VariableModifierContext y : x.variableModifier())
							parameterModifiers.add(y.getText());

					CustomVariable parameter = new CustomVariable(parameterName, parameterModifiers, type, null);
					parameters.add(parameter);
				}
			}
		}

		CustomMethod currentConstructor = new CustomMethod(name, modifiers, null, parameters, exceptions, numeroLineas);
		CustomClass currentClass = (CustomClass) tracer.peek();
		currentClass.constructors.add(currentConstructor);
		tracer.push((Object) currentConstructor);
	}
	@Override public void exitConstructorDeclaration(JavaParser.ConstructorDeclarationContext ctx) {
		tracer.pop();
	}
	@Override public void enterConstructorModifier(JavaParser.ConstructorModifierContext ctx) { }
	@Override public void exitConstructorModifier(JavaParser.ConstructorModifierContext ctx) { }
	@Override public void enterConstructorDeclarator(JavaParser.ConstructorDeclaratorContext ctx) { }
	@Override public void exitConstructorDeclarator(JavaParser.ConstructorDeclaratorContext ctx) { }
	@Override public void enterSimpleTypeName(JavaParser.SimpleTypeNameContext ctx) { }
	@Override public void exitSimpleTypeName(JavaParser.SimpleTypeNameContext ctx) { }
	@Override public void enterConstructorBody(JavaParser.ConstructorBodyContext ctx) { }
	@Override public void exitConstructorBody(JavaParser.ConstructorBodyContext ctx) { }
	@Override public void enterExplicitConstructorInvocation(JavaParser.ExplicitConstructorInvocationContext ctx) { }
	@Override public void exitExplicitConstructorInvocation(JavaParser.ExplicitConstructorInvocationContext ctx) { }
	@Override public void enterEnumDeclaration(JavaParser.EnumDeclarationContext ctx) { }
	@Override public void exitEnumDeclaration(JavaParser.EnumDeclarationContext ctx) { }
	@Override public void enterEnumBody(JavaParser.EnumBodyContext ctx) { }
	@Override public void exitEnumBody(JavaParser.EnumBodyContext ctx) { }
	@Override public void enterEnumConstantList(JavaParser.EnumConstantListContext ctx) { }
	@Override public void exitEnumConstantList(JavaParser.EnumConstantListContext ctx) { }
	@Override public void enterEnumConstant(JavaParser.EnumConstantContext ctx) { }
	@Override public void exitEnumConstant(JavaParser.EnumConstantContext ctx) { }
	@Override public void enterEnumConstantModifier(JavaParser.EnumConstantModifierContext ctx) { }
	@Override public void exitEnumConstantModifier(JavaParser.EnumConstantModifierContext ctx) { }
	@Override public void enterEnumBodyDeclarations(JavaParser.EnumBodyDeclarationsContext ctx) { }
	@Override public void exitEnumBodyDeclarations(JavaParser.EnumBodyDeclarationsContext ctx) { }
	@Override public void enterInterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx) { }
	@Override public void exitInterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx) { }
	@Override public void enterNormalInterfaceDeclaration(JavaParser.NormalInterfaceDeclarationContext ctx) {

		List<String> interfaceModifiers = new ArrayList<>();
		List<String> typeParameters = new ArrayList<>();
		List<String> superInterfaces = new ArrayList<>();
		String name = ctx.Identifier().getText();
		Integer numeroLineas = ctx.stop.getLine() - ctx.start.getLine();

		if (ctx.interfaceModifier() != null)
			for (JavaParser.InterfaceModifierContext x : ctx.interfaceModifier())
				interfaceModifiers.add(x.getText());

		if (ctx.typeParameters() != null)
			for (JavaParser.TypeParameterContext x : ctx.typeParameters().typeParameterList().typeParameter())
				typeParameters.add(x.getText());

		if (ctx.extendsInterfaces() != null)
			for (JavaParser.InterfaceTypeContext x : ctx.extendsInterfaces().interfaceTypeList().interfaceType())
				superInterfaces.add(x.getText());

		CustomInterface currentInterface = new CustomInterface(name, interfaceModifiers, superInterfaces, typeParameters, numeroLineas);
		if (tracer.size() == 0)
			interfaces.add(currentInterface);
		else if (tracer.peek() instanceof CustomClass)
			((CustomClass) tracer.peek()).interfaces.add(currentInterface);
		else if (tracer.peek() instanceof CustomInterface)
			((CustomInterface) tracer.peek()).interfaces.add(currentInterface);
		tracer.add((Object) currentInterface);
	}
	@Override public void exitNormalInterfaceDeclaration(JavaParser.NormalInterfaceDeclarationContext ctx) {
		tracer.pop();
	}
	@Override public void enterInterfaceModifier(JavaParser.InterfaceModifierContext ctx) { }
	@Override public void exitInterfaceModifier(JavaParser.InterfaceModifierContext ctx) { }
	@Override public void enterExtendsInterfaces(JavaParser.ExtendsInterfacesContext ctx) { }
	@Override public void exitExtendsInterfaces(JavaParser.ExtendsInterfacesContext ctx) { }
	@Override public void enterInterfaceBody(JavaParser.InterfaceBodyContext ctx) { }
	@Override public void exitInterfaceBody(JavaParser.InterfaceBodyContext ctx) { }
	@Override public void enterInterfaceMemberDeclaration(JavaParser.InterfaceMemberDeclarationContext ctx) { }
	@Override public void exitInterfaceMemberDeclaration(JavaParser.InterfaceMemberDeclarationContext ctx) { }
	@Override public void enterConstantDeclaration(JavaParser.ConstantDeclarationContext ctx) {
		List<String> constantModifiers = new ArrayList<>();
		String type = ctx.unannType().getText();

		if (ctx.constantModifier() != null)
			for (JavaParser.ConstantModifierContext x : ctx.constantModifier())
				constantModifiers.add(x.getText());

		for (JavaParser.VariableDeclaratorContext x : ctx.variableDeclaratorList().variableDeclarator()) {
			String name = x.variableDeclaratorId().Identifier().getText();
			String value = x.variableInitializer() == null ? null : x.variableInitializer().getText();
			CustomVariable constant = new CustomVariable(name, constantModifiers, type, value);
			CustomInterface currentInterface = (CustomInterface) tracer.peek();
			currentInterface.constants.add(constant);
		}
	}
	@Override public void exitConstantDeclaration(JavaParser.ConstantDeclarationContext ctx) { }
	@Override public void enterConstantModifier(JavaParser.ConstantModifierContext ctx) { }
	@Override public void exitConstantModifier(JavaParser.ConstantModifierContext ctx) { }
	@Override public void enterInterfaceMethodDeclaration(JavaParser.InterfaceMethodDeclarationContext ctx) {
		List<String> methodModifiers = new ArrayList<>();
		List<String> methodExceptions = new ArrayList<>();
		List<CustomVariable> methodParameters = new ArrayList<>();
		String name = ctx.methodHeader().methodDeclarator().Identifier().getText();
		String result = ctx.methodHeader().result().getText();
		Integer numeroLineas = ctx.stop.getLine() - ctx.start.getLine();

		if (ctx.interfaceMethodModifier() != null)
			for (JavaParser.InterfaceMethodModifierContext x : ctx.interfaceMethodModifier())
				methodModifiers.add(x.getText());

		if (ctx.methodHeader().throws_() != null)
			for (JavaParser.ExceptionTypeContext x : ctx.methodHeader().throws_().exceptionTypeList().exceptionType())
				methodExceptions.add(x.getText());

		if (ctx.methodHeader().methodDeclarator().formalParameterList() != null) {
			/** Parameters **/
			if (ctx.methodHeader().methodDeclarator().formalParameterList().formalParameters() != null) {
				for (JavaParser.FormalParameterContext x : ctx.methodHeader().methodDeclarator().formalParameterList().formalParameters().formalParameter()) {
					List<String> parameterModifiers = new ArrayList<>();
					String parameterName = x.variableDeclaratorId().Identifier().getText();
					String type = x.unannType().getText();

					if (x.variableModifier() != null)
						for (JavaParser.VariableModifierContext y : x.variableModifier())
							parameterModifiers.add(y.getText());

					CustomVariable parameter = new CustomVariable(parameterName, parameterModifiers, type, null);
					methodParameters.add(parameter);
				}
			}

			/** Last Parameter **/
			JavaParser.LastFormalParameterContext lastParameter = ctx.methodHeader().methodDeclarator().formalParameterList().lastFormalParameter();
			CustomVariable parameter;
			List<String> modifiers = new ArrayList<>();
			String parameterName, type;
			if (lastParameter.formalParameter() != null) {
				parameterName = lastParameter.formalParameter().variableDeclaratorId().Identifier().getText();
				type = lastParameter.formalParameter().unannType().getText();

				if (lastParameter.formalParameter().variableModifier() != null)
					for (JavaParser.VariableModifierContext x : lastParameter.formalParameter().variableModifier())
						modifiers.add(x.getText());
			} else {
				parameterName = lastParameter.variableDeclaratorId().Identifier().getText();
				type = lastParameter.unannType().getText();
				if (lastParameter.variableModifier() != null)
					for (JavaParser.VariableModifierContext x : lastParameter.variableModifier())
						modifiers.add(x.getText());
			}
			parameter = new CustomVariable(name, modifiers, type, null);
			methodParameters.add(parameter);
		}

		CustomMethod currentMethod = new CustomMethod(name, methodModifiers, result, methodParameters, methodExceptions, numeroLineas);
		CustomInterface currentInterface = (CustomInterface) tracer.peek();
		currentInterface.methods.add(currentMethod);
		tracer.push((Object) currentMethod);
	}
	@Override public void exitInterfaceMethodDeclaration(JavaParser.InterfaceMethodDeclarationContext ctx) {
		tracer.pop();
	}
	@Override public void enterInterfaceMethodModifier(JavaParser.InterfaceMethodModifierContext ctx) { }
	@Override public void exitInterfaceMethodModifier(JavaParser.InterfaceMethodModifierContext ctx) { }
	@Override public void enterAnnotationTypeDeclaration(JavaParser.AnnotationTypeDeclarationContext ctx) { }
	@Override public void exitAnnotationTypeDeclaration(JavaParser.AnnotationTypeDeclarationContext ctx) { }
	@Override public void enterAnnotationTypeBody(JavaParser.AnnotationTypeBodyContext ctx) { }
	@Override public void exitAnnotationTypeBody(JavaParser.AnnotationTypeBodyContext ctx) { }
	@Override public void enterAnnotationTypeMemberDeclaration(JavaParser.AnnotationTypeMemberDeclarationContext ctx) { }
	@Override public void exitAnnotationTypeMemberDeclaration(JavaParser.AnnotationTypeMemberDeclarationContext ctx) { }
	@Override public void enterAnnotationTypeElementDeclaration(JavaParser.AnnotationTypeElementDeclarationContext ctx) { }
	@Override public void exitAnnotationTypeElementDeclaration(JavaParser.AnnotationTypeElementDeclarationContext ctx) { }
	@Override public void enterAnnotationTypeElementModifier(JavaParser.AnnotationTypeElementModifierContext ctx) { }
	@Override public void exitAnnotationTypeElementModifier(JavaParser.AnnotationTypeElementModifierContext ctx) { }
	@Override public void enterDefaultValue(JavaParser.DefaultValueContext ctx) { }
	@Override public void exitDefaultValue(JavaParser.DefaultValueContext ctx) { }
	@Override public void enterAnnotation(JavaParser.AnnotationContext ctx) { }
	@Override public void exitAnnotation(JavaParser.AnnotationContext ctx) { }
	@Override public void enterNormalAnnotation(JavaParser.NormalAnnotationContext ctx) { }
	@Override public void exitNormalAnnotation(JavaParser.NormalAnnotationContext ctx) { }
	@Override public void enterElementValuePairList(JavaParser.ElementValuePairListContext ctx) { }
	@Override public void exitElementValuePairList(JavaParser.ElementValuePairListContext ctx) { }
	@Override public void enterElementValuePair(JavaParser.ElementValuePairContext ctx) { }
	@Override public void exitElementValuePair(JavaParser.ElementValuePairContext ctx) { }
	@Override public void enterElementValue(JavaParser.ElementValueContext ctx) { }
	@Override public void exitElementValue(JavaParser.ElementValueContext ctx) { }
	@Override public void enterElementValueArrayInitializer(JavaParser.ElementValueArrayInitializerContext ctx) { }
	@Override public void exitElementValueArrayInitializer(JavaParser.ElementValueArrayInitializerContext ctx) { }
	@Override public void enterElementValueList(JavaParser.ElementValueListContext ctx) { }
	@Override public void exitElementValueList(JavaParser.ElementValueListContext ctx) { }
	@Override public void enterMarkerAnnotation(JavaParser.MarkerAnnotationContext ctx) { }
	@Override public void exitMarkerAnnotation(JavaParser.MarkerAnnotationContext ctx) { }
	@Override public void enterSingleElementAnnotation(JavaParser.SingleElementAnnotationContext ctx) { }
	@Override public void exitSingleElementAnnotation(JavaParser.SingleElementAnnotationContext ctx) { }
	@Override public void enterArrayInitializer(JavaParser.ArrayInitializerContext ctx) { }
	@Override public void exitArrayInitializer(JavaParser.ArrayInitializerContext ctx) { }
	@Override public void enterVariableInitializerList(JavaParser.VariableInitializerListContext ctx) { }
	@Override public void exitVariableInitializerList(JavaParser.VariableInitializerListContext ctx) { }
	@Override public void enterBlock(JavaParser.BlockContext ctx) { }
	@Override public void exitBlock(JavaParser.BlockContext ctx) { }
	@Override public void enterBlockStatements(JavaParser.BlockStatementsContext ctx) { }
	@Override public void exitBlockStatements(JavaParser.BlockStatementsContext ctx) { }
	@Override public void enterBlockStatement(JavaParser.BlockStatementContext ctx) { }
	@Override public void exitBlockStatement(JavaParser.BlockStatementContext ctx) { }
	@Override public void enterLocalVariableDeclarationStatement(JavaParser.LocalVariableDeclarationStatementContext ctx) { }
	@Override public void exitLocalVariableDeclarationStatement(JavaParser.LocalVariableDeclarationStatementContext ctx) { }
	@Override public void enterLocalVariableDeclaration(JavaParser.LocalVariableDeclarationContext ctx) {
		List<String> variableModifiers = new ArrayList<>();
		String type = ctx.unannType().getText();

		if (ctx.unannType().unannReferenceType() != null && ctx.unannType().unannReferenceType().unannArrayType() != null)
			ctx.unannType().unannReferenceType().unannArrayType();

		if (ctx.variableModifier() != null)
			for (JavaParser.VariableModifierContext x : ctx.variableModifier())
				variableModifiers.add(x.getText());

		for (JavaParser.VariableDeclaratorContext x : ctx.variableDeclaratorList().variableDeclarator()) {
			String name = x.variableDeclaratorId().Identifier().getText();
			String value = x.variableInitializer() == null ? null : x.variableInitializer().getText();

			CustomVariable localVar = new CustomVariable(name, variableModifiers, type, value);
			if (tracer.peek() instanceof CustomClass) {
				CustomClass currentClass = (CustomClass) tracer.peek();
				currentClass.variables.add(localVar);
			} else if (tracer.peek() instanceof CustomMethod) {
				CustomMethod currentMethod = (CustomMethod) tracer.peek();
				currentMethod.variables.add(localVar);
			}
		}
	}
	@Override public void exitLocalVariableDeclaration(JavaParser.LocalVariableDeclarationContext ctx) { }
	@Override public void enterStatement(JavaParser.StatementContext ctx) { }
	@Override public void exitStatement(JavaParser.StatementContext ctx) { }
	@Override public void enterStatementNoShortIf(JavaParser.StatementNoShortIfContext ctx) { }
	@Override public void exitStatementNoShortIf(JavaParser.StatementNoShortIfContext ctx) { }
	@Override public void enterStatementWithoutTrailingSubstatement(JavaParser.StatementWithoutTrailingSubstatementContext ctx) { }
	@Override public void exitStatementWithoutTrailingSubstatement(JavaParser.StatementWithoutTrailingSubstatementContext ctx) { }
	@Override public void enterEmptyStatement(JavaParser.EmptyStatementContext ctx) { }
	@Override public void exitEmptyStatement(JavaParser.EmptyStatementContext ctx) { }
	@Override public void enterLabeledStatement(JavaParser.LabeledStatementContext ctx) { }
	@Override public void exitLabeledStatement(JavaParser.LabeledStatementContext ctx) { }
	@Override public void enterLabeledStatementNoShortIf(JavaParser.LabeledStatementNoShortIfContext ctx) { }
	@Override public void exitLabeledStatementNoShortIf(JavaParser.LabeledStatementNoShortIfContext ctx) { }
	@Override public void enterExpressionStatement(JavaParser.ExpressionStatementContext ctx) { }
	@Override public void exitExpressionStatement(JavaParser.ExpressionStatementContext ctx) { }
	@Override public void enterStatementExpression(JavaParser.StatementExpressionContext ctx) { }
	@Override public void exitStatementExpression(JavaParser.StatementExpressionContext ctx) { }
	@Override public void enterIfThenStatement(JavaParser.IfThenStatementContext ctx) {
		CustomMethod currentMethod = (CustomMethod) tracer.peek();
		Integer count = currentMethod.statementTypes.getOrDefault("If-Then", new Integer(0));
		currentMethod.statementTypes.put("If-Then", count + 1);
	}
	@Override public void exitIfThenStatement(JavaParser.IfThenStatementContext ctx) { }
	@Override public void enterIfThenElseStatement(JavaParser.IfThenElseStatementContext ctx) {
		CustomMethod currentMethod = (CustomMethod) tracer.peek();
		Integer count = currentMethod.statementTypes.getOrDefault("If-Then-Else", new Integer(0));
		currentMethod.statementTypes.put("If-Then-Else", count + 1);
	}
	@Override public void exitIfThenElseStatement(JavaParser.IfThenElseStatementContext ctx) { }
	@Override public void enterIfThenElseStatementNoShortIf(JavaParser.IfThenElseStatementNoShortIfContext ctx) {
		CustomMethod currentMethod = (CustomMethod) tracer.peek();
		Integer count = currentMethod.statementTypes.getOrDefault("If-Then", new Integer(0));
		currentMethod.statementTypes.put("If-Then", count + 1);
	}
	@Override public void exitIfThenElseStatementNoShortIf(JavaParser.IfThenElseStatementNoShortIfContext ctx) { }
	@Override public void enterAssertStatement(JavaParser.AssertStatementContext ctx) { }
	@Override public void exitAssertStatement(JavaParser.AssertStatementContext ctx) { }
	@Override public void enterSwitchStatement(JavaParser.SwitchStatementContext ctx) {
		CustomMethod currentMethod = (CustomMethod) tracer.peek();
		Integer count = currentMethod.statementTypes.getOrDefault("Switch", new Integer(0));
		currentMethod.statementTypes.put("Switch", count + 1);
	}
	@Override public void exitSwitchStatement(JavaParser.SwitchStatementContext ctx) { }
	@Override public void enterSwitchBlock(JavaParser.SwitchBlockContext ctx) { }
	@Override public void exitSwitchBlock(JavaParser.SwitchBlockContext ctx) { }
	@Override public void enterSwitchBlockStatementGroup(JavaParser.SwitchBlockStatementGroupContext ctx) { }
	@Override public void exitSwitchBlockStatementGroup(JavaParser.SwitchBlockStatementGroupContext ctx) { }
	@Override public void enterSwitchLabels(JavaParser.SwitchLabelsContext ctx) { }
	@Override public void exitSwitchLabels(JavaParser.SwitchLabelsContext ctx) { }
	@Override public void enterSwitchLabel(JavaParser.SwitchLabelContext ctx) { }
	@Override public void exitSwitchLabel(JavaParser.SwitchLabelContext ctx) { }
	@Override public void enterEnumConstantName(JavaParser.EnumConstantNameContext ctx) { }
	@Override public void exitEnumConstantName(JavaParser.EnumConstantNameContext ctx) { }
	@Override public void enterWhileStatement(JavaParser.WhileStatementContext ctx) {
		CustomMethod currentMethod = (CustomMethod) tracer.peek();
		Integer count = currentMethod.statementTypes.getOrDefault("While-Loop", new Integer(0));
		currentMethod.statementTypes.put("While-Loop", count + 1);
	}
	@Override public void exitWhileStatement(JavaParser.WhileStatementContext ctx) { }
	@Override public void enterWhileStatementNoShortIf(JavaParser.WhileStatementNoShortIfContext ctx) {
		CustomMethod currentMethod = (CustomMethod) tracer.peek();
		Integer count = currentMethod.statementTypes.getOrDefault("While-Loop", new Integer(0));
		currentMethod.statementTypes.put("While-Loop", count + 1);
	}
	@Override public void exitWhileStatementNoShortIf(JavaParser.WhileStatementNoShortIfContext ctx) { }
	@Override public void enterDoStatement(JavaParser.DoStatementContext ctx) {
		CustomMethod currentMethod = (CustomMethod) tracer.peek();
		Integer count = currentMethod.statementTypes.getOrDefault("Do-While-Loop", new Integer(0));
		currentMethod.statementTypes.put("Do-While-Loop", count + 1);
	}
	@Override public void exitDoStatement(JavaParser.DoStatementContext ctx) { }
	@Override public void enterForStatement(JavaParser.ForStatementContext ctx) {
		CustomMethod currentMethod = (CustomMethod) tracer.peek();
		Integer count = currentMethod.statementTypes.getOrDefault("For-Loop", new Integer(0));
		currentMethod.statementTypes.put("For-Loop", count + 1);
	}
	@Override public void exitForStatement(JavaParser.ForStatementContext ctx) { }
	@Override public void enterForStatementNoShortIf(JavaParser.ForStatementNoShortIfContext ctx) {
		CustomMethod currentMethod = (CustomMethod) tracer.peek();
		Integer count = currentMethod.statementTypes.getOrDefault("For-Loop", new Integer(0));
		currentMethod.statementTypes.put("For-Loop", count + 1);
	}
	@Override public void exitForStatementNoShortIf(JavaParser.ForStatementNoShortIfContext ctx) { }
	@Override public void enterBasicForStatement(JavaParser.BasicForStatementContext ctx) { }
	@Override public void exitBasicForStatement(JavaParser.BasicForStatementContext ctx) { }
	@Override public void enterBasicForStatementNoShortIf(JavaParser.BasicForStatementNoShortIfContext ctx) { }
	@Override public void exitBasicForStatementNoShortIf(JavaParser.BasicForStatementNoShortIfContext ctx) { }
	@Override public void enterForInit(JavaParser.ForInitContext ctx) { }
	@Override public void exitForInit(JavaParser.ForInitContext ctx) { }
	@Override public void enterForUpdate(JavaParser.ForUpdateContext ctx) { }
	@Override public void exitForUpdate(JavaParser.ForUpdateContext ctx) { }
	@Override public void enterStatementExpressionList(JavaParser.StatementExpressionListContext ctx) { }
	@Override public void exitStatementExpressionList(JavaParser.StatementExpressionListContext ctx) { }
	@Override public void enterEnhancedForStatement(JavaParser.EnhancedForStatementContext ctx) { }
	@Override public void exitEnhancedForStatement(JavaParser.EnhancedForStatementContext ctx) { }
	@Override public void enterEnhancedForStatementNoShortIf(JavaParser.EnhancedForStatementNoShortIfContext ctx) { }
	@Override public void exitEnhancedForStatementNoShortIf(JavaParser.EnhancedForStatementNoShortIfContext ctx) { }
	@Override public void enterBreakStatement(JavaParser.BreakStatementContext ctx) { }
	@Override public void exitBreakStatement(JavaParser.BreakStatementContext ctx) { }
	@Override public void enterContinueStatement(JavaParser.ContinueStatementContext ctx) { }
	@Override public void exitContinueStatement(JavaParser.ContinueStatementContext ctx) { }
	@Override public void enterReturnStatement(JavaParser.ReturnStatementContext ctx) {
		CustomMethod currentMethod = (CustomMethod) tracer.peek();
		Integer count = currentMethod.statementTypes.getOrDefault("Return", new Integer(0));
		currentMethod.statementTypes.put("Return", count + 1);
	}
	@Override public void exitReturnStatement(JavaParser.ReturnStatementContext ctx) { }
	@Override public void enterThrowStatement(JavaParser.ThrowStatementContext ctx) {
		CustomMethod currentMethod = (CustomMethod) tracer.peek();
		Integer count = currentMethod.statementTypes.getOrDefault("Throw-Exception", new Integer(0));
		currentMethod.statementTypes.put("Throw-Exception", count + 1);
	}
	@Override public void exitThrowStatement(JavaParser.ThrowStatementContext ctx) { }
	@Override public void enterSynchronizedStatement(JavaParser.SynchronizedStatementContext ctx) { }
	@Override public void exitSynchronizedStatement(JavaParser.SynchronizedStatementContext ctx) { }
	@Override public void enterTryStatement(JavaParser.TryStatementContext ctx) {
		CustomMethod currentMethod = (CustomMethod) tracer.peek();
		Integer count = currentMethod.statementTypes.getOrDefault("While", new Integer(0));
		currentMethod.statementTypes.put("While", count + 1);
	}
	@Override public void exitTryStatement(JavaParser.TryStatementContext ctx) {
		CustomMethod currentMethod = (CustomMethod) tracer.peek();

		if (ctx.catches() == null && ctx.finally_() == null) {
			Integer count = currentMethod.statementTypes.getOrDefault("Try", new Integer(0));
			currentMethod.statementTypes.put("Try", count + 1);
		} else if (ctx.catches() != null && ctx.finally_() != null) {
			Integer count = currentMethod.statementTypes.getOrDefault("Try-Catch-Finally", new Integer(0));
			currentMethod.statementTypes.put("Try-Catch-Finally", count + 1);
		} else if (ctx.catches() != null) {
			Integer count = currentMethod.statementTypes.getOrDefault("Try-Catch", new Integer(0));
			currentMethod.statementTypes.put("Try-Catch", count + 1);
		} else if (ctx.finally_() != null) {
			Integer count = currentMethod.statementTypes.getOrDefault("Try-Finally", new Integer(0));
			currentMethod.statementTypes.put("Try-Finally", count + 1);
		}
	}
	@Override public void enterCatches(JavaParser.CatchesContext ctx) { }
	@Override public void exitCatches(JavaParser.CatchesContext ctx) { }
	@Override public void enterCatchClause(JavaParser.CatchClauseContext ctx) { }
	@Override public void exitCatchClause(JavaParser.CatchClauseContext ctx) { }
	@Override public void enterCatchFormalParameter(JavaParser.CatchFormalParameterContext ctx) { }
	@Override public void exitCatchFormalParameter(JavaParser.CatchFormalParameterContext ctx) { }
	@Override public void enterCatchType(JavaParser.CatchTypeContext ctx) { }
	@Override public void exitCatchType(JavaParser.CatchTypeContext ctx) { }
	@Override public void enterFinally_(JavaParser.Finally_Context ctx) { }
	@Override public void exitFinally_(JavaParser.Finally_Context ctx) { }
	@Override public void enterTryWithResourcesStatement(JavaParser.TryWithResourcesStatementContext ctx) { }
	@Override public void exitTryWithResourcesStatement(JavaParser.TryWithResourcesStatementContext ctx) { }
	@Override public void enterResourceSpecification(JavaParser.ResourceSpecificationContext ctx) { }
	@Override public void exitResourceSpecification(JavaParser.ResourceSpecificationContext ctx) { }
	@Override public void enterResourceList(JavaParser.ResourceListContext ctx) { }
	@Override public void exitResourceList(JavaParser.ResourceListContext ctx) { }
	@Override public void enterResource(JavaParser.ResourceContext ctx) { }
	@Override public void exitResource(JavaParser.ResourceContext ctx) { }
	@Override public void enterPrimary(JavaParser.PrimaryContext ctx) { }
	@Override public void exitPrimary(JavaParser.PrimaryContext ctx) { }
	@Override public void enterPrimaryNoNewArray(JavaParser.PrimaryNoNewArrayContext ctx) { }
	@Override public void exitPrimaryNoNewArray(JavaParser.PrimaryNoNewArrayContext ctx) { }
	@Override public void enterPrimaryNoNewArray_lf_arrayAccess(JavaParser.PrimaryNoNewArray_lf_arrayAccessContext ctx) { }
	@Override public void exitPrimaryNoNewArray_lf_arrayAccess(JavaParser.PrimaryNoNewArray_lf_arrayAccessContext ctx) { }
	@Override public void enterPrimaryNoNewArray_lfno_arrayAccess(JavaParser.PrimaryNoNewArray_lfno_arrayAccessContext ctx) { }
	@Override public void exitPrimaryNoNewArray_lfno_arrayAccess(JavaParser.PrimaryNoNewArray_lfno_arrayAccessContext ctx) { }
	@Override public void enterPrimaryNoNewArray_lf_primary(JavaParser.PrimaryNoNewArray_lf_primaryContext ctx) { }
	@Override public void exitPrimaryNoNewArray_lf_primary(JavaParser.PrimaryNoNewArray_lf_primaryContext ctx) { }
	@Override public void enterPrimaryNoNewArray_lf_primary_lf_arrayAccess_lf_primary(JavaParser.PrimaryNoNewArray_lf_primary_lf_arrayAccess_lf_primaryContext ctx) { }
	@Override public void exitPrimaryNoNewArray_lf_primary_lf_arrayAccess_lf_primary(JavaParser.PrimaryNoNewArray_lf_primary_lf_arrayAccess_lf_primaryContext ctx) { }
	@Override public void enterPrimaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primary(JavaParser.PrimaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primaryContext ctx) { }
	@Override public void exitPrimaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primary(JavaParser.PrimaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primaryContext ctx) { }
	@Override public void enterPrimaryNoNewArray_lfno_primary(JavaParser.PrimaryNoNewArray_lfno_primaryContext ctx) { }
	@Override public void exitPrimaryNoNewArray_lfno_primary(JavaParser.PrimaryNoNewArray_lfno_primaryContext ctx) { }
	@Override public void enterPrimaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primary(JavaParser.PrimaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primaryContext ctx) { }
	@Override public void exitPrimaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primary(JavaParser.PrimaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primaryContext ctx) { }
	@Override public void enterPrimaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primary(JavaParser.PrimaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primaryContext ctx) { }
	@Override public void exitPrimaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primary(JavaParser.PrimaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primaryContext ctx) { }
	@Override public void enterClassInstanceCreationExpression(JavaParser.ClassInstanceCreationExpressionContext ctx) { }
	@Override public void exitClassInstanceCreationExpression(JavaParser.ClassInstanceCreationExpressionContext ctx) { }
	@Override public void enterClassInstanceCreationExpression_lf_primary(JavaParser.ClassInstanceCreationExpression_lf_primaryContext ctx) { }
	@Override public void exitClassInstanceCreationExpression_lf_primary(JavaParser.ClassInstanceCreationExpression_lf_primaryContext ctx) { }
	@Override public void enterClassInstanceCreationExpression_lfno_primary(JavaParser.ClassInstanceCreationExpression_lfno_primaryContext ctx) { }
	@Override public void exitClassInstanceCreationExpression_lfno_primary(JavaParser.ClassInstanceCreationExpression_lfno_primaryContext ctx) { }
	@Override public void enterTypeArgumentsOrDiamond(JavaParser.TypeArgumentsOrDiamondContext ctx) { }
	@Override public void exitTypeArgumentsOrDiamond(JavaParser.TypeArgumentsOrDiamondContext ctx) { }
	@Override public void enterFieldAccess(JavaParser.FieldAccessContext ctx) { }
	@Override public void exitFieldAccess(JavaParser.FieldAccessContext ctx) { }
	@Override public void enterFieldAccess_lf_primary(JavaParser.FieldAccess_lf_primaryContext ctx) { }
	@Override public void exitFieldAccess_lf_primary(JavaParser.FieldAccess_lf_primaryContext ctx) { }
	@Override public void enterFieldAccess_lfno_primary(JavaParser.FieldAccess_lfno_primaryContext ctx) { }
	@Override public void exitFieldAccess_lfno_primary(JavaParser.FieldAccess_lfno_primaryContext ctx) { }
	@Override public void enterArrayAccess(JavaParser.ArrayAccessContext ctx) { }
	@Override public void exitArrayAccess(JavaParser.ArrayAccessContext ctx) { }
	@Override public void enterArrayAccess_lf_primary(JavaParser.ArrayAccess_lf_primaryContext ctx) { }
	@Override public void exitArrayAccess_lf_primary(JavaParser.ArrayAccess_lf_primaryContext ctx) { }
	@Override public void enterArrayAccess_lfno_primary(JavaParser.ArrayAccess_lfno_primaryContext ctx) { }
	@Override public void exitArrayAccess_lfno_primary(JavaParser.ArrayAccess_lfno_primaryContext ctx) { }
	@Override public void enterMethodInvocation(JavaParser.MethodInvocationContext ctx) { }
	@Override public void exitMethodInvocation(JavaParser.MethodInvocationContext ctx) { }
	@Override public void enterMethodInvocation_lf_primary(JavaParser.MethodInvocation_lf_primaryContext ctx) { }
	@Override public void exitMethodInvocation_lf_primary(JavaParser.MethodInvocation_lf_primaryContext ctx) { }
	@Override public void enterMethodInvocation_lfno_primary(JavaParser.MethodInvocation_lfno_primaryContext ctx) { }
	@Override public void exitMethodInvocation_lfno_primary(JavaParser.MethodInvocation_lfno_primaryContext ctx) { }
	@Override public void enterArgumentList(JavaParser.ArgumentListContext ctx) { }
	@Override public void exitArgumentList(JavaParser.ArgumentListContext ctx) { }
	@Override public void enterMethodReference(JavaParser.MethodReferenceContext ctx) { }
	@Override public void exitMethodReference(JavaParser.MethodReferenceContext ctx) { }
	@Override public void enterMethodReference_lf_primary(JavaParser.MethodReference_lf_primaryContext ctx) { }
	@Override public void exitMethodReference_lf_primary(JavaParser.MethodReference_lf_primaryContext ctx) { }
	@Override public void enterMethodReference_lfno_primary(JavaParser.MethodReference_lfno_primaryContext ctx) { }
	@Override public void exitMethodReference_lfno_primary(JavaParser.MethodReference_lfno_primaryContext ctx) { }
	@Override public void enterArrayCreationExpression(JavaParser.ArrayCreationExpressionContext ctx) { }
	@Override public void exitArrayCreationExpression(JavaParser.ArrayCreationExpressionContext ctx) { }
	@Override public void enterDimExprs(JavaParser.DimExprsContext ctx) { }
	@Override public void exitDimExprs(JavaParser.DimExprsContext ctx) { }
	@Override public void enterDimExpr(JavaParser.DimExprContext ctx) { }
	@Override public void exitDimExpr(JavaParser.DimExprContext ctx) { }
	@Override public void enterConstantExpression(JavaParser.ConstantExpressionContext ctx) { }
	@Override public void exitConstantExpression(JavaParser.ConstantExpressionContext ctx) { }
	@Override public void enterExpression(JavaParser.ExpressionContext ctx) { }
	@Override public void exitExpression(JavaParser.ExpressionContext ctx) { }
	@Override public void enterLambdaExpression(JavaParser.LambdaExpressionContext ctx) { }
	@Override public void exitLambdaExpression(JavaParser.LambdaExpressionContext ctx) { }
	@Override public void enterLambdaParameters(JavaParser.LambdaParametersContext ctx) { }
	@Override public void exitLambdaParameters(JavaParser.LambdaParametersContext ctx) { }
	@Override public void enterInferredFormalParameterList(JavaParser.InferredFormalParameterListContext ctx) { }
	@Override public void exitInferredFormalParameterList(JavaParser.InferredFormalParameterListContext ctx) { }
	@Override public void enterLambdaBody(JavaParser.LambdaBodyContext ctx) { }
	@Override public void exitLambdaBody(JavaParser.LambdaBodyContext ctx) { }
	@Override public void enterAssignmentExpression(JavaParser.AssignmentExpressionContext ctx) {
		if (tracer.peek() instanceof CustomMethod) {
			CustomMethod currentMethod = (CustomMethod) tracer.peek();
			Integer count = currentMethod.statementTypes.getOrDefault("Assignment", new Integer(0));
			currentMethod.statementTypes.put("Assignment", count + 1);
		}
	}
	@Override public void exitAssignmentExpression(JavaParser.AssignmentExpressionContext ctx) { }
	@Override public void enterAssignment(JavaParser.AssignmentContext ctx) { }
	@Override public void exitAssignment(JavaParser.AssignmentContext ctx) { }
	@Override public void enterLeftHandSide(JavaParser.LeftHandSideContext ctx) { }
	@Override public void exitLeftHandSide(JavaParser.LeftHandSideContext ctx) { }
	@Override public void enterAssignmentOperator(JavaParser.AssignmentOperatorContext ctx) { }
	@Override public void exitAssignmentOperator(JavaParser.AssignmentOperatorContext ctx) { }
	@Override public void enterConditionalExpression(JavaParser.ConditionalExpressionContext ctx) { }
	@Override public void exitConditionalExpression(JavaParser.ConditionalExpressionContext ctx) { }
	@Override public void enterConditionalOrExpression(JavaParser.ConditionalOrExpressionContext ctx) { }
	@Override public void exitConditionalOrExpression(JavaParser.ConditionalOrExpressionContext ctx) { }
	@Override public void enterConditionalAndExpression(JavaParser.ConditionalAndExpressionContext ctx) { }
	@Override public void exitConditionalAndExpression(JavaParser.ConditionalAndExpressionContext ctx) { }
	@Override public void enterInclusiveOrExpression(JavaParser.InclusiveOrExpressionContext ctx) { }
	@Override public void exitInclusiveOrExpression(JavaParser.InclusiveOrExpressionContext ctx) { }
	@Override public void enterExclusiveOrExpression(JavaParser.ExclusiveOrExpressionContext ctx) { }
	@Override public void exitExclusiveOrExpression(JavaParser.ExclusiveOrExpressionContext ctx) { }
	@Override public void enterAndExpression(JavaParser.AndExpressionContext ctx) { }
	@Override public void exitAndExpression(JavaParser.AndExpressionContext ctx) { }
	@Override public void enterEqualityExpression(JavaParser.EqualityExpressionContext ctx) { }
	@Override public void exitEqualityExpression(JavaParser.EqualityExpressionContext ctx) { }
	@Override public void enterRelationalExpression(JavaParser.RelationalExpressionContext ctx) { }
	@Override public void exitRelationalExpression(JavaParser.RelationalExpressionContext ctx) { }
	@Override public void enterShiftExpression(JavaParser.ShiftExpressionContext ctx) { }
	@Override public void exitShiftExpression(JavaParser.ShiftExpressionContext ctx) { }
	@Override public void enterAdditiveExpression(JavaParser.AdditiveExpressionContext ctx) { }
	@Override public void exitAdditiveExpression(JavaParser.AdditiveExpressionContext ctx) { }
	@Override public void enterMultiplicativeExpression(JavaParser.MultiplicativeExpressionContext ctx) { }
	@Override public void exitMultiplicativeExpression(JavaParser.MultiplicativeExpressionContext ctx) { }
	@Override public void enterUnaryExpression(JavaParser.UnaryExpressionContext ctx) { }
	@Override public void exitUnaryExpression(JavaParser.UnaryExpressionContext ctx) { }
	@Override public void enterPreIncrementExpression(JavaParser.PreIncrementExpressionContext ctx) { }
	@Override public void exitPreIncrementExpression(JavaParser.PreIncrementExpressionContext ctx) { }
	@Override public void enterPreDecrementExpression(JavaParser.PreDecrementExpressionContext ctx) { }
	@Override public void exitPreDecrementExpression(JavaParser.PreDecrementExpressionContext ctx) { }
	@Override public void enterUnaryExpressionNotPlusMinus(JavaParser.UnaryExpressionNotPlusMinusContext ctx) { }
	@Override public void exitUnaryExpressionNotPlusMinus(JavaParser.UnaryExpressionNotPlusMinusContext ctx) { }
	@Override public void enterPostfixExpression(JavaParser.PostfixExpressionContext ctx) { }
	@Override public void exitPostfixExpression(JavaParser.PostfixExpressionContext ctx) { }
	@Override public void enterPostIncrementExpression(JavaParser.PostIncrementExpressionContext ctx) { }
	@Override public void exitPostIncrementExpression(JavaParser.PostIncrementExpressionContext ctx) { }
	@Override public void enterPostIncrementExpression_lf_postfixExpression(JavaParser.PostIncrementExpression_lf_postfixExpressionContext ctx) { }
	@Override public void exitPostIncrementExpression_lf_postfixExpression(JavaParser.PostIncrementExpression_lf_postfixExpressionContext ctx) { }
	@Override public void enterPostDecrementExpression(JavaParser.PostDecrementExpressionContext ctx) { }
	@Override public void exitPostDecrementExpression(JavaParser.PostDecrementExpressionContext ctx) { }
	@Override public void enterPostDecrementExpression_lf_postfixExpression(JavaParser.PostDecrementExpression_lf_postfixExpressionContext ctx) { }
	@Override public void exitPostDecrementExpression_lf_postfixExpression(JavaParser.PostDecrementExpression_lf_postfixExpressionContext ctx) { }
	@Override public void enterCastExpression(JavaParser.CastExpressionContext ctx) { }
	@Override public void exitCastExpression(JavaParser.CastExpressionContext ctx) { }

	@Override public void enterEveryRule(ParserRuleContext ctx) { }
	@Override public void exitEveryRule(ParserRuleContext ctx) { }
	@Override public void visitTerminal(TerminalNode node) { }
	@Override public void visitErrorNode(ErrorNode node) {
		System.out.println(node.getText());
	}

}

/** DOCUMENTING DATA CLASSES **/

/** Custom Import **/
class CustomImport {
	String name;
	String type;

	public CustomImport(String name, String type) {
		this.name = name;
		this.type = type;
	}
}

/** Custom Class **/
class CustomClass {

	/* Class Metadata */
	public List<String> classModifiers;
	public String name;
	public List<String> typeParameters;
	public String superClass;
	public List<String> superInterfaces;
	public Integer numeroLineas;

	/** Class Attributes **/
	public List<CustomVariable> constants;
	public List<CustomVariable> variables;
	public List<CustomMethod> constructors;
	public List<CustomMethod> methods;
	public List<CustomClass> classes;
	public List<CustomInterface> interfaces;

	public CustomClass() {
		constants = new ArrayList<>();
		variables = new ArrayList<>();
		constructors = new ArrayList<>();
		methods = new ArrayList<>();
		classes = new ArrayList<>();
		interfaces = new ArrayList<>();
	}

	public CustomClass(String className, List<String> classModifiers, List<String> typeParameters, String superClass, List<String> superInterfaces, Integer numeroLineas) {
		this();
		this.classModifiers = classModifiers;
		this.name = className;
		this.typeParameters = typeParameters;
		this.superClass = superClass;
		this.superInterfaces = superInterfaces;
		this.numeroLineas = numeroLineas;
	}

}

/** Custom Interface **/
class CustomInterface {

	/* Interface Metadata */
	public String name;
	public List<String> interfaceModifiers;
	public List<String> superInterfaces;
	public List<String> typeParameters;
	public Integer numeroLineas;

	/** Interface Attributes **/
	public List<CustomVariable> constants;
	public List<CustomMethod> methods;
	public List<CustomClass> classes;
	public List<CustomInterface> interfaces;

	public CustomInterface() {
		constants = new ArrayList<>();
		methods = new ArrayList<>();
		classes = new ArrayList<>();
		interfaces = new ArrayList<>();
	}

	public CustomInterface(String name, List<String> interfaceModifiers, List<String> superInterfaces, List<String> typeParameter, Integer numeroLineas) {
		this();
		this.name = name;
		this.interfaceModifiers = interfaceModifiers;
		this.superInterfaces = superInterfaces;
		this.typeParameters = typeParameters;
		this.numeroLineas = numeroLineas;
	}

}

/** Custom Method **/
class CustomMethod {

	/** Method Metadata **/
	public String name;
	public String result;
	public List<String> exceptions;
	public List<String> methodModifiers;
	public Map<String, Integer> statementTypes;
	public int numeroLineas;

	/** Method Attributes **/
	public List<CustomVariable> parameters;
	public List<CustomVariable> variables;

	public CustomMethod() {
		variables = new ArrayList<>();
		statementTypes = new HashMap<>();
	}

	public CustomMethod(String name, List<String> methodModifiers, String result, List<CustomVariable> parameters, List<String> exceptions, Integer numeroLineas) {
		this();
		this.name = name;
		this.result = result;
		this.exceptions = exceptions;
		this.parameters = parameters;
		this.methodModifiers = methodModifiers;
		this.numeroLineas = numeroLineas;
	}

}

/** Custom Variable **/
class CustomVariable {

	/** Variable Metadata **/
	public String name;
	public String type;
	public List<String> modifiers;
	public String value;

	public CustomVariable(String name, List<String> modifiers, String type, String value) {
		this.name = name;
		this.type = type;
		this.modifiers = modifiers;
		this.value = value;
	}

}
