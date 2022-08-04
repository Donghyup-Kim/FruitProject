package kopo.poly.service;


import kopo.poly.dto.OrderDTO;
import kopo.poly.dto.ProductDTO;

import java.util.List;


public interface IOrderService {

    int InsertOrder(OrderDTO oDTO) throws Exception;

    int searchorderinfo(OrderDTO oDTO) throws Exception;

    OrderDTO getproductinfo(OrderDTO oDTO) throws Exception;

    OrderDTO searchproseq(OrderDTO oDTO) throws Exception;

    OrderDTO select(OrderDTO oDTO) throws Exception;

    int updateOrder(OrderDTO uDTO) throws Exception;

    OrderDTO selectpro_seq(OrderDTO pDTO)throws Exception;

    void deleteorder(ProductDTO rDTO) throws Exception;

}
