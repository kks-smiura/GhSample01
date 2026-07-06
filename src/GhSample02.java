import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

/*
 * 原価率算出プログラム
 * for GitHub Copilot
 */
public class GhSample02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // 1. 入力値の受け取り
            System.out.print("原価を入力してください: ");
            BigDecimal cost = new BigDecimal(scanner.next());

            System.out.print("売上高を入力してください: ");
            BigDecimal sales = new BigDecimal(scanner.next());

            // 計算式: (原価 / 売上高) * 100
            // ※除算時は、小数点以下4位で四捨五入して3位まで求める（例: 33.333%）
            BigDecimal dec100 = new BigDecimal("100");
            BigDecimal costRatio = cost.divide(sales, 4, RoundingMode.HALF_UP)
                                       .multiply(dec100);

            // 3. 結果の出力
            System.out.println("--- 計算結果 ---");
            System.out.println("原価率: " + costRatio + "%");

        } catch (NumberFormatException e) {
            System.out.println("エラー：有効な数値を入力してください。");
        } finally {
            scanner.close();
        }
    }
}