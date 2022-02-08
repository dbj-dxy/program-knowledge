import cn.dbj.knowledge.design.structural.proxy.*;
import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestProxy {

    @Test
    public void proxy() {
        Proxy proxy = new Proxy(new Target());
        proxy.doJob();
    }

    @Test
    public void jdkProxy() {
        JdkDynamicProxy<BaseTarget> proxy = new JdkDynamicProxy<>(new Target());
        BaseTarget target = proxy.getProxy();
        target.doJob();
        target.defaultJob();
    }

    @Test
    public void cglibProxy() {
        CglibProxy<TargetNoInterface> proxy = new CglibProxy<>(new TargetNoInterface());
        TargetNoInterface target = proxy.getProxy();
        target.doJob();
//        target.defaultJob();
        target.finalJob();
    }

    @Test
    public void proxyClassFile() {
        String name = "ProxySubject";
        byte[] data = ProxyGenerator.generateProxyClass(name, new Class[]{BaseTarget.class});
        try(FileOutputStream out = new FileOutputStream(name + ".class")) {
            System.out.println((new File(name)).getAbsolutePath());
            out.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
