import org.junit.Test;

/**
 * 对象实例化过程：
 * 1、静态属性
 * 2、静态方法
 * 3、构造器
 * 4、非静态属性
 */
public class TestClassLoader {

    @Test
    public void test() {
        new test("1", "2");
    }



    static class test {
        private String a;
        private static String b;
        private String c = "c";   // 第四步
        private static String d = "d";  // 第一步

        {   // 第六步
            a = "a";  // 第四步
            b = "b1";  // 第五步
        }

        static {
            b = "b2"; // 第二步
        }

        public test(String a, String c) {  // 第三步
            this.a = a;  // 第六步
            this.c = c;  // 第七步
        }
    }
}
