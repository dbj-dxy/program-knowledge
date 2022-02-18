#概念
####ES#node：
node：即一个 Elasticsearch 的运行实例，使用多播或单播方式发现 cluster 并加入。
####ES#cluster：
cluster：包含一个或多个拥有相同集群名称的 node，其中包含一个master node。
####ES#alias：
alias：可以给 index 添加零个或多个alias，通过 alias 使用index 和根据index name 访问index一样，  
但是，alias给我们提供了一种切换index的能力，比如重建了index。
####ES#index：
index：类比关系型数据库里的DB，是一个逻辑命名空间。
####ES#index#type：
在 5.X 版本中，一个 index 下可以创建多个 type；  
在 6.X 版本中，一个 index 下只能存在一个 type；  
在 7.X 版本中，直接去除了 type 的概念，就是说 index 不再会有 type；  
6开始不被推荐，7开始废弃，一个index可以定义多个type，一般推荐仅配一个type  
文档不依赖于预先定义的模式。也就是说并非所有的文档都需要拥有相同的字段，并不受限于同一个模式
---
1、为何要去除 type 的概念？

答： 因为 Elasticsearch 设计初期，是直接查考了关系型数据库的设计模式，存在了 type（数据表）的概念。
但是，其搜索引擎是基于 Lucene 的，这种 “基因”决定了 type 是多余的。 Lucene 的全文检索功能之所以快，是因为 倒序索引 的存在。
而这种 倒序索引 的生成是基于 index 的，而并非 type。多个type 反而会减慢搜索的速度 。
---
2、为何不是在 6.X 版本开始就直接去除 type，而是要逐步去除type？

答：因为历史原因，前期 Elasticsearch 支持一个 index 下存在多个 type的，而且，有很多项目在使用 Elasticsearch 作为数据库。
如果直接去除 type 的概念，不仅是很多应用 Elasticsearch 的项目将面临 业务、功能和代码的大改，
而且对于 Elasticsearch 官方来说，也是一个巨大的挑战（这个是伤筋动骨的大手术，很多涉及到 type 源码是要修改的）。
所以，权衡利弊，采取逐步过渡的方式，最终，推迟到 7.X 版本才完成 “去除 type” 这个 革命性的变革。
####ES#index#mapping：
mapping：类比关系型数据库中的 schema 概念，mapping 定义了 index 中的 type。mapping 可以显示的定义，也可以在 document 被索引时自动生成，
1. 如果有新的 field，Elasticsearch 会自动推测出 field 的type并加到mapping中。  
   mapping不仅告诉ES一个field中是什么类型的值， 它还告诉ES如何索引数据以及数据是否能被搜索到。
2. 一个mapping由一个或多个analyzer组成，一个analyzer又由一个或多个filter组成的。  
   当ES索引文档的时候，它把字段中的内容传递给相应的analyzer，analyzer再传递给各自的filters。
3. filter的功能很容易理解：一个filter就是一个转换数据的方法， 输入一个字符串，这个方法返回另一个字符串，比如一个将字符串转为小写的方法就是一个filter很好的例子。  
   一个analyzer由一组顺序排列的filter组成，执行分析的过程就是按顺序一个filter一个filter依次调用， ES存储和索引最后得到的结果。
4. 总结来说， mapping的作用就是执行一系列的指令将输入的数据转成可搜索的索引项。
5. 不要把mapping想成是数据类型， 把它想象成是搜索数据的指令集合
####ES#index#setting：
setting：设置索引信息  
number_of_shards：分片数量  
number_of_replicas：副本数量
####ES#index#document：
document：类比关系数据库里的一行记录(record)，document 是 Elasticsearch 里的一个 JSON 对象，包括零个或多个field。
####ES#index#document#field：
field：类比关系数据库里的field，每个field 都有自己的字段类型。
####ES#index#analyzer：
analyzer：默认的analyzer是标准analyzer, 这个标准analyzer有三个filter：token filter, lowercase filter和stop token filter。  
重要的是， 即使ES存储数据的时候仍然存储的是完整的数据， 但是可以搜索到这条数据的关键字只剩下这三个单词了， 其他的都是抛弃掉了。
####ES#index#fields：多字段
fields：处于不同的目的，通过不同的方法索引相同的字段通常非常有用  
例如，一个字符串字段可以映射为text字段用于全文本搜索，也可以映射为keyword字段用于排序或聚合。
---
1. 多字段不能修改原始_source字段。
2. 对于相同索引中具有相同名称的字段，fields设置允许有不同的设置。可以使用PUT映射API将新的多字段添加到已存在的字段中。

##ES数据类型
1. 字段数据类型-自定义字段的属性  
   Alias、Arrays、Binary、Boolean、Date、Date nanoseconds、Dense vector、Histogram、Flattened、Geo-point、Geo-shape、IP、Join、Keyword、Nested、Numeric、Object、Percolator、Range、Rank feature、Rank features、Search-as-you-type、Sparse vector、Text、Token count、Shape、Constant keyword
2. Metadata fields (元属性)-ES生成的默认属性  
   _field_names、 _ignored、 _id、 _index、 _meta、 _routing、 _source、 _type
3. ES字符串String数据类型keyword 和 text 数据类型区别的区别  
   3.1. keyword：简单理解就是 Keyword 数据类型用来建立电子邮箱地址、姓名、邮政编码和标签等数据，不需要进行分词，只能用精准搜素。可以被用来检索过滤、排序和聚合。  
   3.2. text：Text 数据类型被用来索引长文本，这些文本会被分析，在建立索引前会将这些文本进行分词，转化为词的组合，建立索引。允许 ES来检索这些词语。text 数据类型不能用来排序和聚合
#######遇到字符串类型时候的字端，系统会默认为“text”类型。检索的时候对字符串进行分析。所以要想只通过字段本身来进行检索，还是需要按照上面把该字段改为“keyword”类型。

##Query DSL
1. term：查询之前查询条件不分词（对比 match）
2. terms：跟 term 有点类似，但 terms 允许指定多个匹配条件。 如果某个字段指定了多个值，那么文档需要一起去做匹配(类似 sql 的 in)
3. range：范围 gt :: 大于 gte:: 大于等于 lt :: 小于 lte:: 小于等于
4. exists 和 missing：用于查找文档中是否包含指定字段，类似于SQL语句中的IS_NULL条件，这两个只是针对已经查出一批数据来，但是想区分出某个字段是否存在的时候使用。  
   4.1. missing：ES 5.2.2之后被废弃，原因可以使用 must_not 组合 exists 达到一样效果
5. bool：可以用来合并多个过滤条件查询结果的布尔逻辑  
   5.1. must：多个查询条件的完全匹配,相当于 and。  
   5.2. must_not：多个查询条件的相反匹配，相当于 not。  
   5.3. should：至少有一个查询条件匹配, 相当于 or。   
   5.3.1. 默认情况下，没有 should 语句是必须匹配的，只有一个例外：那就是当没有 must 语句的时候，至少有一个 should 语句必须匹配。  
   5.4. filter：过滤  
   5.4.1. 单独使用时，filter与must基本一样，不同的是filter不计算评分，效率更高  
   5.4.2. 和must、must_not同级，相当于子查询   
   5.4.3. 将must、must_not置于filter下，这种方式是最常用的  
   5.5. es 5.0版本更新后，filtered 的查询将替换为bool查询。
6. match_all：查询到所有文档
7. match：查询之前查询条件分词    
   7.1. match查询是一个标准查询，不管你需要全文本查询还是精确查询基本上都要用到它。  
   7.2. 使用 match 查询一个全文本字段，它会在真正查询之前用分析器先分析match一下查询字符  
   7.3. 用match下指定了一个确切值，在遇到数字，日期，布尔值或者not_analyzed 的字符串时，它将为你搜索你给定的值  
   7.4. match查询只能就指定某个确切字段某个确切的值进行搜索
   7.5. 提示： 做精确匹配搜索时，你最好用过滤语句，因为过滤语句可以缓存数据。  
   7.6. minimum_should_match：表示字段匹配的数量    
   7.6.1. 整数：至少匹配指定个数的词（term）  
   7.6.2. 带百分数：搜索条件分词后个数乘百分比，该值向下取整后，至少匹配该个数的词（term）  
   7.7. query：指定查询关键词  
   7.8. operator：表示单个字段如何匹配查询条件的分词，分词后，用来控制match查询匹配词条的逻辑条件，默认值是or，如果设置为and，表示查询满足所有条件  
   7.9. fuzziness：默认是 2，表示最多可以纠错两次，但是这个值不能很大，不然没效果。一般 AUTO 是自动纠错。  
   7.10. type：boolean / phrase / phrase_prefix
8. multi_match：multi_match查询允许你做match查询的基础上同时搜索多个字段，在多个字段中同时查一个
9. wildcards：使用标准的shell通配符查询
10. regexp：正则
11. prefix：前缀匹配
12. match_phrase：短语匹配，  
    12.1. slop：slop参数告诉match_phrase查询词条能够相隔多远时仍然将文档视为匹配。  
    相隔多远的意思是，你需要移动一个词条（移动搜索词的词条）多少次来让查询和文档匹配
13. boost：查询时权重提升，默认为1
14. weight：过滤集提升权重
15. adjust_pure_negative：
###Query & Filter
1. Query：查询上下文，会计算_score相关性分数。是在使用query进行查询时的执行环境，例如：  
   1.1. query: {}
2. Filter：过滤上下文，不会计算_score分值相关性分数。 是在使用filter参数时候的执行环境，例如：
   2.1. the filter or must_not parameters in the bool query（bool中的filter或must_not）  
   2.2. the filter parameter in the constant_score query（constant_score中的filter）  
   2.3. the filter aggregation（过滤聚合）
3. 经常使用过滤器，ES会自动的缓存过滤器的内容
#####总结
1. 查询上下文中，查询操作不仅仅会进行查询，还会计算分值，用于确定相关度；在过滤器上下文中，查询操作仅判断是否满足查询条件
2. 过滤器上下文中，查询的结果可以被缓存。
##aggregation聚合
使用方式：
```
GET /indexName/_search
{
   "size":20,  #指定返回条数
   "aggs":{  #聚合关键字
      "NAME":{   #返回的名称，类似mysql as 关键字，指定别名
         "AGG_TYPE":{  #哪个聚合函数
            "field":"age"  #聚合字段
         }
      }
   }
}
```
1. max：最大
2. avg：平均
3. sum：求和
4. count：总数
5. cardinality：去重
6. terms：分组

##ES执行逻辑
filter -》 query -》 aggregation
##测试分析器
```
GET _analyze
{
    "analyzer":"ik_smart",
    "text":[
        "牛水奶"
    ]
}
```

es 7.15后（不包括7。15），不推荐使用 RestHighLevelClient