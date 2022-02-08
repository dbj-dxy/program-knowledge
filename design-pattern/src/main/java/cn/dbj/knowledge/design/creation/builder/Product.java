package cn.dbj.knowledge.design.creation.builder;

/**
 * @author DBJ
 * @date 2020-08-20
 * 具体产品，可以抽象出同一类产品接口
 */
public class Product {

    public Product() {
        System.out.println("I`m a PC");
    }

    private String screen;
    private String keyboard;
    private String mouse;

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public void setKeyboard(String keyboard) {
        this.keyboard = keyboard;
    }

    public void setMouse(String mouse) {
        this.mouse = mouse;
    }

    public void show() {
        System.out.println(this.screen);
        System.out.println(this.keyboard);
        System.out.println(this.mouse);
    }
}
