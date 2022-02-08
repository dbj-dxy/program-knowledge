import cn.dbj.knowledge.design.behavioral.chain.ConcreteHandlerA;
import cn.dbj.knowledge.design.behavioral.chain.ConcreteHandlerB;
import cn.dbj.knowledge.design.behavioral.chain.Handler;
import org.junit.Test;

public class TestChain {

    @Test
    public void chain() {
        Handler handlerA = new ConcreteHandlerA();
        Handler handlerB = new ConcreteHandlerB();
        handlerA.setNext(handlerB);
        //提交请求
        handlerA.handleRequest("two");
    }

}
