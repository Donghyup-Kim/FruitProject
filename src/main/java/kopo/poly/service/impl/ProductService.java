package kopo.poly.service.impl;


import kopo.poly.dto.ProductDTO;
import kopo.poly.dto.SellerInfoDTO;
import kopo.poly.persistance.mapper.IProductMapper;
import kopo.poly.service.IProductService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("ProductService")
public class ProductService implements IProductService {

    @Autowired
    private IProductMapper productMapper;

    //상품 리스트 가져오기 get만해옴
    @Override
    public List<ProductDTO> getProductList() throws Exception {
        log.info(this.getClass().getName() + "ProductService 상품리스트가져오기 시작");

        List pList = new ArrayList<ProductDTO>();

        pList = productMapper.getProductList();


        if(pList == null){

            log.info("pList" + pList);
        }else if(pList != null){
            log.info("pList" + pList);
        }

        log.info(this.getClass().getName() + "ProductService 상품리스트가져오기 끝");

        return pList;
    }

    @Override
    public List<ProductDTO> getProductListByReg_id(ProductDTO pDTO) throws Exception {
        
        log.info(this.getClass().getName() + "REG_ID로 상품리스트가져오기 시작");

        if(pDTO==null){
            pDTO = new ProductDTO();
        }

        List pList = productMapper.getProductListByReg_id(pDTO);


        if(pList == null){
            log.info("pList" + pList);
            pList = new ArrayList<>();
        }else if(pList != null){
            log.info("pList" + pList);
        }

        log.info(this.getClass().getName() + "REG_ID로 상품리스트가져오기 끝");

        return pList;
    }

    @Override
    public void InsertProductInfo(ProductDTO rDTO) throws Exception {

        log.info(this.getClass().getName() + "InsertProductInfo 시작");

        log.info(this.getClass().getName() + "InsertProductInfo 끝");

        productMapper.InsertProductInfo(rDTO);
    }

    @Override
    public ProductDTO getProductInfo(ProductDTO rDTO) throws Exception {

        log.info(this.getClass().getName() + "getProductInfo 시작");

        log.info(this.getClass().getName() + "getProductInfo 끝");

        return productMapper.getProductInfo(rDTO);
    }

    @Override
    public void updatePro(ProductDTO rDTO) throws Exception {

        log.info("rDTO의 값은??" + rDTO);

        productMapper.updatePro(rDTO);
    }

    @Override
    public void deleteProductInfo(ProductDTO rDTO) throws Exception {
        
        log.info(this.getClass().getName() + "deleteProductInfo 시작");

        log.info(this.getClass().getName() + "deleteProductInfo 끝");
        
        productMapper.deleteProductInfo(rDTO);
    }

    @Override
    public int InsertProductFileInfo(ProductDTO rDTO) throws Exception {

        int res = 0;
        int success = productMapper.InsertProductFileInfo(rDTO);

        //db에 데이터가 등록되었다면,
        if (success > 0) {
            res = 1;
            log.info("ProductService : InsertProductInfo, 상품 등록 완료");
        } else {
            res = 0;
        }


        log.info("ProductService : InsertProductInfo(상품 로직) 끝 !");
        return res;
    }

    @Override
    public ProductDTO seqSearch(ProductDTO rDTO) throws Exception {

        if(rDTO == null){
            log.info("rDTO : " + rDTO);
            rDTO = new ProductDTO();
        }
        ProductDTO uDTO = null;
        uDTO = new ProductDTO();
        uDTO = productMapper.seqSearch(rDTO);

        if(uDTO == null){
            uDTO = new ProductDTO();
            log.info("uDTO" + uDTO);
        }else if(uDTO != null){
            log.info("uDTO" + uDTO);
        }


        return uDTO;
    }


}


