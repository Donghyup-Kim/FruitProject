package kopo.poly.service;

import kopo.poly.dto.ProductDTO;

import java.util.List;

public interface IProductService {

    List<ProductDTO> getProductList() throws Exception;

    List<ProductDTO> getProductListByReg_id(ProductDTO pDTO) throws Exception;

    void InsertProductInfo(ProductDTO rDTO) throws Exception;

    ProductDTO getProductInfo(ProductDTO rDTO) throws Exception;

    void updatePro(ProductDTO rDTO) throws Exception;

    void deleteProductInfo(ProductDTO rDTO) throws Exception;

    int InsertProductFileInfo(ProductDTO rDTO) throws Exception;

    ProductDTO seqSearch(ProductDTO rDTO) throws Exception;
}
