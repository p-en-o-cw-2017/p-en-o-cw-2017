package datatypes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

class Util {
	static String capitalize(String s) {
		return s.substring(0,  1).toUpperCase() + s.substring(1);
	}
}

enum FieldType {
	FLOAT("float"),
	INT("int"),
	BYTE_ARRAY("byte[]") {
		public String getReaderCall() { return "readByteArray(stream)"; }
		public String getWriterCall() { return "writeByteArray(stream, "; }
	};

	public final String name;
	
	private FieldType(String name) {
		this.name = name;
	}
	
	public String getReaderCall() {
		return "stream.read" + Util.capitalize(name) + "()";
	}
	
	public String getWriterCall() {
		return "stream.write" + Util.capitalize(name) + "(";
	}
}

class Field {
	public final String name;
	public final FieldType type;
	
	public Field(String name, FieldType type) {
		this.name = name;
		this.type = type;
	}
	
	public String getGetterName() {
		return "get" + Util.capitalize(name);
	}
}

class Datatype {
	public final String name;
	public final List<Field> fields;
	
	public Datatype(String name, List<Field> fields) {
		this.name = name;
		this.fields = fields;
	}
}

class CompilationUnit {
	public final String packageName;
	public final List<Datatype> datatypes;
	
	public CompilationUnit(String packageName, List<Datatype> datatypes) {
		this.packageName = packageName;
		this.datatypes = datatypes;
	}
}

class ParserException extends RuntimeException {
	int lineno;
	String message;
	
	public ParserException(int lineno, String message) {
		super("line "+lineno+": "+message);
		this.lineno = lineno;
		this.message = message;
	}
}

class Parser extends StreamTokenizer {
	private static final HashSet<String> keywords = new HashSet<>(Arrays.asList("package", "datatype", "float", "int", "byte"));
	
	public Parser(Reader reader) {
		super(reader);
		ordinaryChar('/');
		slashStarComments(true);
		slashSlashComments(true);
		wordChars('_', '_');
	}
	
	void error(String message) {
		throw new ParserException(lineno(), message);
	}
	
	void expectKeyword(String keyword) throws IOException {
		if (ttype != TT_WORD || !sval.equals(keyword))
			error("Keyword '"+keyword+"' expected");
		nextToken();
	}
	
	String expectIdent() throws IOException {
		if (ttype != TT_WORD || keywords.contains(sval))
			error("Identifier expected");
		String x = sval;
		nextToken();
		return x;
	}
	
	void expectChar(char c) throws IOException {
		if (ttype != c)
			error("'"+c+"' expected");
		nextToken();
	}
	
	String parsePackageName() throws IOException {
		StringBuilder builder = new StringBuilder(expectIdent());
		while (ttype == '.') {
			nextToken();
			builder.append('.');
			builder.append(expectIdent());
		}
		return builder.toString();
	}
	
	FieldType parseFieldType() throws IOException {
		if (ttype == TT_WORD && sval.equals("float")) {
			nextToken();
			return FieldType.FLOAT;
		} else if (ttype == TT_WORD && sval.equals("int")) {
			nextToken();
			return FieldType.INT;
		} else if (ttype == TT_WORD && sval.equals("byte")) {
			nextToken();
			expectChar('[');
			expectChar(']');
			return FieldType.BYTE_ARRAY;
		} else {
			error("Field type ('float', 'int', or 'byte[]') expected");
			throw new AssertionError();
		}
	}
	
	public CompilationUnit parseCompilationUnit() throws IOException {
		nextToken();
		expectKeyword("package");
		String packageName = parsePackageName();
		expectChar(';');
		List<Datatype> datatypes = new ArrayList<>();
		while (ttype != TT_EOF) {
			expectKeyword("public");
			expectKeyword("datatype");
			String name = expectIdent();
			expectChar('{');
			List<Field> fields = new ArrayList<>();
			while (ttype != '}') {
				FieldType type = parseFieldType();
				String fieldName = expectIdent();
				expectChar(';');
				fields.add(new Field(fieldName, type));
			}
			nextToken();
			datatypes.add(new Datatype(name, fields));
		}
		return new CompilationUnit(packageName, datatypes);
	}
}

public class CodeGenerator {
	
	public static void main(String[] args) throws IOException {
		String compilationUnitPath = args[0];
		File targetDir = new File(args.length < 2 ? "." : args[1]);
		generateJavaSourceFiles(targetDir, compilationUnitPath);
	}
	
	public static void generateJavaSourceFiles(File targetDir, String compilationUnitPath) throws IOException {
		CompilationUnit compilationUnit;
		try (
				FileReader fis = new FileReader(compilationUnitPath);
				BufferedReader br = new BufferedReader(fis)
			) {
			compilationUnit = new Parser(br).parseCompilationUnit();
		}
		generateJavaSourceFiles(targetDir, compilationUnit);
	}
	
	public static void generateJavaSourceFiles(File targetDir, CompilationUnit compilationUnit) throws IOException {
		CodeGenerator generator = new CodeGenerator(targetDir, compilationUnit.packageName);
		for (Datatype datatype : compilationUnit.datatypes) {
			generator.generateJavaSourceFiles(datatype);
		}
	}
	
	File targetDir;
	String packageName;
	
	CodeGenerator(File targetDir, String packageName) {
		this.targetDir = targetDir;
		this.packageName = packageName;
	}
	
	void generateJavaSourceFiles(Datatype datatype) throws IOException {
		generateInterface(datatype);
		generateReader(datatype);
		generateWriter(datatype);
	}

	void generateInterface(Datatype datatype) throws IOException {
		File file = new File(targetDir, datatype.name + ".java");
		try (PrintWriter writer = new PrintWriter(file)) {
			writer.format("package %s;%n%n", packageName);
			writer.format("public interface %s {%n", datatype.name);
			for (Field field : datatype.fields) {
				writer.format("    %s %s();%n", field.type.name, field.getGetterName());
			}
			writer.format("}%n");
		}
	}
	
	void generateReader(Datatype datatype) throws IOException {
		File file = new File(targetDir, datatype.name+"Reader.java");
		try (PrintWriter writer = new PrintWriter(file)) {
			writer.format("package %s;%n%n", packageName);
			writer.format("public class %sReader {%n", datatype.name);
			writer.format(
					"    private static byte[] readByteArray(java.io.DataInputStream stream) throws java.io.IOException {%n"+
					"        int length = stream.readInt();%n"+
					"        byte[] array = new byte[length];%n"+
					"        stream.readFully(array);%n"+
					"        return array;%n"+
					"    }%n");
			writer.format("    public static %s read(java.io.DataInputStream stream) throws java.io.IOException {%n", datatype.name);
			for (Field field : datatype.fields) {
				writer.format("        %s %s = %s;%n", field.type.name, field.name, field.type.getReaderCall());
			}
			writer.format("        return new %s() {%n", datatype.name);
			for (Field field : datatype.fields) {
				writer.format("            public %s %s() { return %s; }%n", field.type.name, field.getGetterName(), field.name);
			}
			writer.format(
					"        };%n);"+
					"    }%n"+
					"}%n");
		}
	}
	
	void generateWriter(Datatype datatype) throws IOException {
		File file = new File(targetDir, datatype.name+"Writer.java");
		try (PrintWriter writer = new PrintWriter(file)) {
			writer.format("package %s;%n%n", packageName);
			writer.format("public class %sWriter {%n", datatype.name);
			writer.format(
					"    private static void writeByteArray(java.io.DataOutputStream stream, byte[] array) throws java.io.IOException {%n"+
				    "        stream.writeInt(array.length);%n"+
					"        stream.write(array);%n"+
				    "    }%n");
			writer.format("    public static void write(java.io.DataOutputStream stream, %s value) throws java.io.IOException {%n", datatype.name);
			for (Field field : datatype.fields) {
				writer.format("        %svalue.%s());%n", field.type.getWriterCall(), field.getGetterName());
			}
			writer.format(
					"    }%n"+
					"}%n");
		}
	}
}
