package cn.dbj.knowledge.design.creation.prototype;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DBJ
 * @date 2020-08-03
 * 原型模式 需实现 Cloneable 接口
 */
public class Prototype implements Cloneable{
    private String name;
    private Integer age;
    private HashMap<String, String> resources;

    @Override
    protected Prototype clone() throws CloneNotSupportedException {
        // 如果一个类仅包含原始字段或对不可变对象的引用, 可直接按下面注释的方式直接克隆
//        return (Prototype) super.clone();
        // 如果该类有复杂对象，需要对每一个复杂对象分别进行克隆
        Prototype prototype;
        prototype = (Prototype) super.clone();
        prototype.resources = (HashMap<String, String>) this.resources.clone();

        return prototype;
    }

    public Prototype(String name, Integer age, HashMap<String, String> resources) {
        this.name = name;
        this.age = age;
        this.resources = resources;
    }

    @Override
    public String toString() {
        return "Prototype{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", resources=" + resources +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Map<String, String> getResources() {
        return resources;
    }

    public void setResources(HashMap<String, String> resources) {
        this.resources = resources;
    }


}
