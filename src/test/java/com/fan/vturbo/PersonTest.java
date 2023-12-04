package com.fan.vturbo;

import com.fan.vturbo.dao.PersonDao;
import com.fan.vturbo.entity.info.Person;
import com.fan.vturbo.service.MailService;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

@SpringBootTest
public class PersonTest {

    @Resource
//    StringRedisTemplate redis;
//    private RedisTemplate redis;

//    @Autowired
//    private MongoTemplate mongo;

//    @Autowired
//    private ElasticsearchRestTemplate elasticsearch;

    @Autowired
    private MailService mailService;

    @Test
    public void test() {

    }

    @Test
    public void mail() {
        mailService.sendMail();
    }

    @Test
    public void getRedis() {
        // value.set("name","chenwen");
        // value.set("age",24);
        /*ValueOperations value = redis.opsForValue();
        System.out.println(value.get("name"));*/

    }

    @Test
    public void getMongo() {
        /*Person per1 = new Person(1, "步惊云", "男", 27, "无求意决");
        mongo.save(per1);*/
        /*Query query=new Query();
        query.addCriteria(Criteria.where("name").is("wukong"));*/
//        List<Person> pers=mongo.findAll(Person.class);
        System.out.println();
    }

    @Test
    public void elasticsearch() throws IOException {
        HttpHost host=HttpHost.create("http://localhost:9200");
        RestClientBuilder builder=RestClient.builder(host);
        // spring没有整合，只能手动创建
        RestHighLevelClient client=new RestHighLevelClient(builder);

        CreateIndexRequest request=new CreateIndexRequest("person");
        client.indices().create(request, RequestOptions.DEFAULT);

        String json="";
        request.source(json, XContentType.JSON);

        client.close();
        System.out.println();

    }

}
