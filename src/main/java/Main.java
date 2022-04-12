public class Main {

    public static void main(String[] args) {

        int a[] = { 16, 3, 7, 11, 9, 26, 18, 14, 15 };

        RedNBlack<Integer, Integer> t = new RedNBlack<>();

        for (int i = 0; i < a.length; i++) {

            t.insert(a[i], a[i]);

        }

        t.testSOUT(t.getMain());

    }

}
