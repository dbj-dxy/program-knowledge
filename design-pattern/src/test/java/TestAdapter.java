import cn.dbj.knowledge.design.structural.adapter.AdapterInterface;
import cn.dbj.knowledge.design.structural.adapter.classadapter.ClassAdapter;
import cn.dbj.knowledge.design.structural.adapter.objectadapter.ObjectAdapterOne;
import cn.dbj.knowledge.design.structural.adapter.objectadapter.ObjectAdapterTwo;
import cn.dbj.knowledge.design.structural.adapter.oldsystem.OldServiceOneImpl;
import cn.dbj.knowledge.design.structural.adapter.twowayadapter.TwoWayAdapter;
import org.junit.Test;

public class TestAdapter {

    @Test
    public void classAdapter() {
        new ClassAdapter().newJob();
    }

    @Test
    public void objectAdapter() {
        AdapterInterface adapter = new ObjectAdapterOne();
        adapter.newJob();
        adapter = new ObjectAdapterTwo();
        adapter.newJob();
    }

    @Test
    public void twoWayAdapter() {
        TwoWayAdapter adapter = new TwoWayAdapter(new OldServiceOneImpl());
        adapter.newJob();
        adapter = new TwoWayAdapter(new ObjectAdapterOne());
        adapter.oldJob();
        adapter = new TwoWayAdapter(new ObjectAdapterTwo());
        adapter.oldJob();
    }
}
