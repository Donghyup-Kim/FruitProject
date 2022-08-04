package kopo.poly.persistance.mapper;

import com.amazonaws.services.glue.model.Order;
import kopo.poly.dto.BoardDTO;
import kopo.poly.dto.FileInfoDTO;
import kopo.poly.dto.OrderDTO;
import kopo.poly.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators;

import java.util.List;

@Mapper()
public interface IOrderMapper {

    int InsertOrder(OrderDTO oDTO) throws Exception;

    OrderDTO getproductuser(OrderDTO tDTO) throws Exception;

    OrderDTO getproductinfo(OrderDTO oDTO) throws Exception;

    OrderDTO select(OrderDTO oDTO) throws Exception;

    int updateOrder(OrderDTO uDTO) throws Exception;

    OrderDTO selectpro_seq(OrderDTO pDTO) throws Exception;


    void deleteorder(ProductDTO rDTO)throws Exception;
}
