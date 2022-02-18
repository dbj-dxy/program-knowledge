###@Retention()：定义注解的保留策略
* RetentionPolicy.SOURCE  ：注解仅存在于源码中，在class字节码文件中不包含
* RetentionPolicy.CLASS   ：默认的保留策略，注解会在class字节码文件中存在，但运行时无法获得
* RetentionPolicy.RUNTIME ：注解会在class字节码文件中存在，在运行时可以通过反射获取到  
&nbsp; &nbsp; &nbsp; &nbsp;首先要明确生命周期长度 `SOURCE < CLASS < RUNTIME` ，所以前者能作用的地方后者一定也能作用。    
一般如果需要在运行时去动态获取注解信息，那只能用 RUNTIME 注解；  
如果要在编译时进行一些预处理操作，比如生成一些辅助代码（如 ButterKnife），就用 CLASS注解  
如果只是做一些检查性的操作，比如 @Override 和 @SuppressWarnings，则可选用 SOURCE 注解

###@Target()：定义注解的作用目标
* ElementType.TYPE：接口、类、枚举、注解
* ElementType.FIELD：字段、枚举的常量  
* ElementType.METHOD：方法  
* ElementType.PARAMETER：方法参数  
* ElementType.CONSTRUCTOR：构造函数  
* ElementType.LOCAL_VARIABLE：局部变量  
* ElementType.ANNOTATION_TYPE：注解  
* ElementType.PACKAGE：包

###@Document ：说明该注解将被包含在javadoc中

###@Inherited ：说明子类可以继承父类中的该注解