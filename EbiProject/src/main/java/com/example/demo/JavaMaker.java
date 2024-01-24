package com.example.demo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.Arrays;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

import org.springframework.stereotype.Component;

@Component
public class JavaMaker {

    private String result; // 結果を保存する変数
    private byte[] byteCode; // バイトコードを保持する変数

    public String getResult() {
        return result;
    }

    public void javaCodeStart(String javaCode) {
        // コンパイルされたクラスをロード
    	Class<?> clazz = defineClass("HelloWorld", compileJavaCode(javaCode));
    	
        // 標準出力をキャプチャする
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        System.setOut(ps);

        // メインメソッドを実行
        try {
            Method mainMethod = clazz.getMethod("main", String[].class);
            mainMethod.invoke(null, (Object) new String[] {});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 標準出力の元に戻す
            System.out.flush();
            System.setOut(oldOut);

            // 結果を取得
            result = baos.toString();}
        
        }
    

    private byte[] compileJavaCode(String javaCode) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        StringWriter writer = new StringWriter();

        // メモリ内にソースコードを保持するJavaFileObjectを作成
        JavaFileObject fileObject = new SimpleJavaFileObject(URI.create("string:///"
                + "HelloWorld.java"), JavaFileObject.Kind.SOURCE) {
            @Override
            public CharSequence getCharContent(boolean ignoreEncodingErrors) {
                return javaCode;
            }
        };

        // コンパイルオプションとバイナリ出力先を設定
        String[] compileOptions = new String[] { "-d", "./bin" };
        Iterable<String> compilationOptions = Arrays.asList(compileOptions);
        JavaCompiler.CompilationTask task = compiler.getTask(writer, null, diagnostics, compilationOptions, null,
                Arrays.asList(fileObject));

        // コンパイル実行
        boolean success = task.call();
        if (!success) {
            System.out.println("コンパイルエラー:");
            StringBuilder errorBuilder = new StringBuilder();
            for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics()) {
                System.out.println(diagnostic.toString());
                errorBuilder.append(diagnostic.toString()).append("\n");
            }
            return null;
        }

        // コンパイルされたクラスファイルを読み込む
        try {
            File classFile = new File("./bin/HelloWorld.class");
            FileInputStream fileInputStream = new FileInputStream(classFile);
            byte[] buffer = new byte[(int) classFile.length()];
            fileInputStream.read(buffer);
            fileInputStream.close();
            return buffer;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // カスタムのクラスローダー
    static class CustomClassLoader extends ClassLoader {
        public Class<?> defineClass(String name, byte[] byteCode) {
            return super.defineClass(name, byteCode, 0, byteCode.length);
        }
    }

    // バイトコードを使ってクラスを定義
    private Class<?> defineClass(String name, byte[] byteCode) {
        return new CustomClassLoader().defineClass(name, byteCode);
    }
}
