package cn.dbj.knowledge.design.creation.factory.simplefactory;

/**
 * @author DBJ
 * @date 2020-08-17
 */
public class SimplePhoneFactory {

    private int iPhoneIndex;
    private int miPhoneIndex;

    public Phone makePhone(String phoneName) {
        if ("IPhone".equalsIgnoreCase(phoneName)) {
            return new IPhone("IPhone_" + (++iPhoneIndex));
        } else if ("MiPhone".equalsIgnoreCase(phoneName)) {
            return new MiPhone("MiPhone_" + (++miPhoneIndex));
        }
        throw new IllegalArgumentException();
    }
}
