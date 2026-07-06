import java.util.Scanner;

/*
 * 税込価格算出プログラム
 * for GitHub Copilot
 */
public class GhSample01 {

    // 定数（税率）
    private static final double STANDARD_TAX = 0.10; // 標準税率
    private static final double REDUCED_TAX = 0.08;  // 軽減税率
  
    // 商品カテゴリの列挙型
    enum Category {
        FOOD,       // 飲食料品（酒類を除く）
        ALCOHOL,    // 酒類
        NEWSPAPER,  // 定期購読の新聞
        OTHER       // その他
    }

    // 軽減税率が適用されるか判定
    public static boolean isReducedTax(Category category) {
        switch (category) {
            case FOOD:
            case NEWSPAPER:
                return true;
            default:
                return false;
        }
    }

    // 税込価格を計算
    public static double calcPriceWithTax(double price, Category category) {
        if (price < 0) {
            throw new IllegalArgumentException("価格は0以上で入力してください。");
        }
        double taxRate = isReducedTax(category) ? REDUCED_TAX : STANDARD_TAX;
        return price * (1 + taxRate);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // 入力
            System.out.print("商品価格（税抜）を入力: ");
            double price = sc.nextDouble();

            System.out.print("カテゴリを入力 (FOOD, ALCOHOL, NEWSPAPER, OTHER): ");
            String categoryInput = sc.next().toUpperCase();

            // カテゴリ変換とバリデーション
            Category category;
            try {
                category = Category.valueOf(categoryInput);
            } catch (IllegalArgumentException e) {
                System.out.println("無効なカテゴリです。FOOD, ALCOHOL, NEWSPAPER, OTHER のいずれかを入力してください。");
                return;
            }

            // 計算
            double total = calcPriceWithTax(price, category);

            // 結果表示
            System.out.printf("税込価格: %.0f 円（税率 %.0f%%）%n",
                    total,
                    isReducedTax(category) ? REDUCED_TAX * 100 : STANDARD_TAX * 100);

        } catch (Exception e) {
            System.out.println("入力エラー: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
