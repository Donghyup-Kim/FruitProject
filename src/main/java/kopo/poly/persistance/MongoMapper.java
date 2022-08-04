package kopo.poly.persistance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import kopo.poly.dto.MongoDTO;
import kopo.poly.persistance.mongodb.AbstractMongoDBComon;
import kopo.poly.persistance.mongodb.IMongoMapper;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.security.acl.LastOwnerException;

@Slf4j
@Component
public class MongoMapper extends AbstractMongoDBComon implements IMongoMapper {

    @Override
    public int insertPro(MongoDTO pDTO, String colNm) throws Exception {

        log.info(this.getClass().getName() + "MongoMapper start");


        super.createCollection(colNm);

        MongoCollection<Document> col = mongodb.getCollection(colNm);

//        Document query = new Document();

        Document updateQuery = new Document();
        updateQuery.append("title", pDTO.getTitle());
        updateQuery.append("titlePrice1", pDTO.getTitle_price());
        updateQuery.append("titlePrice2", pDTO.getTitle_price2());



        InsertOneResult rs = col.insertOne(updateQuery);
        int res = rs.hashCode();
        return res;
    }
}
