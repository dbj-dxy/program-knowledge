```shell
docker run -d 
--name kibana7.15.2 
--net elastic 
-p 5601:5601 
-e "ELASTICSEARCH_HOSTS=http://es7.15.2:9200" 
-v /docker/volumes/kibana/config/kibana.yml:/usr/share/kibana/config/kibana.yml 
-v /docker/volumes/kibana/data:/usr/share/kibana/data 
docker.elastic.co/kibana/kibana:7.15.2
```

```shell
docker run  -d 
--name es7.15.2 
--net elastic 
-p 9200:9200 
-p 9300:9300 
-e "discovery.type=single-node" 
-e ES_JAVA_OPTS="-Xms512m -Xmx512m" 
-v /docker/volumes/elasticsearch/config/elasticsearch.yml:/usr/share/elasticsearch/config/elasticsearch.yml 
-v /docker/volumes/elasticsearch/data:/usr/share/elasticsearch/data 
-v /docker/volumes/elasticsearch/plugins:/usr/share/elasticsearch/plugins 
docker.elastic.co/elasticsearch/elasticsearch:7.15.2
```

```shell
docker network create elastic
```

```shell
docker run -d 
--name mysql8.0 
-p 3306:3306 
-e MYSQL_ROOT_PASSWORD=123456 
-v /docker/volumes/mysql/config:/etc/mysql/conf.d
-v /docker/volumes/mysql/data:/var/lib/mysql 
arm64v8/mysql:8.0
```

```shell
docker run -d
--name redis6.2 
-p 6379:6379 
-v /docker/volumes/redis/config/redis.config:/usr/local/etc/redis/redis.config 
-v /docker/volumes/redis/data:/data 
arm64v8/redis:6.2
```

```shell
docker run -d 
--hostname my-rabbit 
--name rabbit3 
-p 5672:5672 
-p 15672:15672 
rabbitmq:3-management
```

