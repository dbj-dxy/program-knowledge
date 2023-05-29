##jvm命令  
-ea 开启断言  
-Xms  堆最小值  
-Xmx  堆最大值    
-Xmn  新生代大小（默认已分配堆内存1/4）  
当 JVM 是默认 8 字节对齐，建议配置最大堆内存不要超过 32 G，否则会影响 JVM 的指针压缩技术，浪费内存。  
-XX:SurvivorRatio  Eden和Survivor的比例（默认8，即E8:S.from1:S.to1）  
-verbose:gc  在控制台输出GC情况  
-XX:+PrintGCDetails   在控制台输出详细的GC情况  
-XX:PretenureSizeThreshold  字节大小大于该值的对象直接分配老年代
-XX:MaxTenuringThreshold  对象进入老年代的年龄阈值  
-XX:HandlePromotionFailure  是否运行担保失败
-XX:+HeapDumpOnOutOfMemoryError  出现内存溢出是dump当前内存堆转储快照  
-XX:HeapDumpPath=/var/java_pid%p.hprof  设置堆内存dump的路径  
-Xoss  设置本地方法栈大小    
-Xss 设置线程栈大小，默认1m    
-XX:PermSize  永久代最小值，jdk7及以下有效  
-XX:MaxPermSize  永久代最大值，jdk7及以下有效  
-XX:MetaspaceSize  指定元空间初始大小(以字节为单位)，达到该值会触发gc进行类型卸载，同时收集器会对该值进行调整
+ 如果释放了大量空间，就适当降低该值；
+ 反之，在不超过 -XX:MaxMetaspaceSize(如果设置了的话) 适当提高该值

-XX:MaxMetaspaceSize  设置元空间最大值，默认是-1(虚拟机不限制, 受限于本地内存大小)建议256m  
-XX:MinMetaspaceFreeRatio  作用是在gc后控制最小的元空间剩余容量的百分比，可减少元空间不足导致的gc频率
-XX:MaxMetaspaceFreeRatio  作用是在gc后控制最大的元空间剩余容量的百分比，可减少元空间不足导致的gc频率  
-XX:MaxDirectMemorySize  设置直接内存大小，默认与java堆最大值(-Xmx)大小一样

###StackOverflowError
+ 线程请求的栈深度大于虚拟机允许的最大深度
+ 栈容量无法容纳新的栈帧

###OutOfMemoryError
+ 如果虚拟机的栈内存允许动态扩展，当扩展栈容量无法申请到足够的内存(Classic)
+ 线程申请栈空间失败(Hotspot)

###String::intern
+ 字符串常量池存在java堆(jdk7开始)，存放的字符串对象的引用(实际的字符串对象在java堆中)  
```java
public void a() {
  String s = new StringBuilder("open").append("jdk").toString();
  // false cause sun.misc.Version
  System.out.println(s.intern() == s);
  String s1 = new StringBuilder("open").append("jdk1").toString();
  // true
  System.out.println(s1.intern() == s1);
}
```