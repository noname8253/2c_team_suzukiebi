// HelloWorld.java

public class HelloWorld {
    public static String result; // 結果を保存する変数
    private static String inputText; // HTMLから受け取る変数

    public static void setInputText(String text) {
        inputText = text;
    }

    public static void main(String[] args) {
        // inputText の値を使用して処理を行う例
        result = "こんにちは、" + inputText + "!";
    }
}
