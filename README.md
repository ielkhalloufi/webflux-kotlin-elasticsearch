#Spring WebFlux and Elasticsearch - Kotlin


Small seed example project, built upon the spring webflux framework and uses the non-blocking api of elasticearch to create a seamless non-blocking experience. 

## Requirements

* Docker should already be installed

Docker pull:
```
docker pull docker.elastic.co/elasticsearch/elasticsearch:6.4.2
 ```
 
run docker compose, will startup two elasticsearch instances: 
```
cd es

docker-compose up
```

Create index manually with rest client:

```
PUT {index_name}
{
    "settings" : {
        "index" : {
            "number_of_shards" : 4, 
            "number_of_replicas" : 2 
        }
    }
}
```

Bulk insert dataset into elasticsearch:
```
cat es/dummy/data.json | \
jq -c '.[]  | .id = ._id | del (._id) | {"index": {"_index": "advertisement", "_type": "gps", "_id": .id}}, .' |\
curl  -XPOST 127.0.0.1:9200/_bulk --data-binary @-
```

Your data.json file contains an array of objects that you want to bulk insert into Elasticsearch.

## To do
- Create a mapper from SearchResponse or GetResponse to a domain model (is this directly possible?)
- Example unit tests and Integration tests