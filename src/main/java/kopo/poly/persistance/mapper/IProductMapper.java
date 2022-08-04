package kopo.poly.persistance.mapper;

import kopo.poly.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IProductMapper {

    //상품 리스트
    List<ProductDTO> getProductList() throws Exception;

    List<ProductDTO> getProductListByReg_id(ProductDTO pDTO) throws Exception;

    //상품 글 등록
    void InsertProductInfo(ProductDTO rDTO) throws Exception;

    //상품 상세보기
    ProductDTO getProductInfo(ProductDTO rDTO) throws Exception;

    //상품 글 수정
    void updatePro(ProductDTO rDTO) throws Exception;

    //상품 글 삭제
    void deleteProductInfo(ProductDTO rDTO) throws Exception;

    int InsertProductFileInfo(ProductDTO rDTO) throws Exception;

    ProductDTO seqSearch(ProductDTO rDTO) throws Exception;

}
