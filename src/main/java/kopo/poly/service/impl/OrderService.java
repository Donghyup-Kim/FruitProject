package kopo.poly.service.impl;


import kopo.poly.dto.OrderDTO;
import kopo.poly.dto.ProductDTO;
import kopo.poly.persistance.mapper.IOrderMapper;
import kopo.poly.service.IOrderService;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("OrderService")
public class OrderService implements IOrderService {

    @Autowired
    private IOrderMapper orderMapper;

    @Override
    public int InsertOrder(OrderDTO oDTO) throws Exception {
        int res = 0;// 0은 실패 1은 성공

        if(oDTO == null){
            oDTO = new OrderDTO();
        }
        int success = orderMapper.InsertOrder(oDTO);

        if(success > 0){
            res = 1;
            log.info("오더테이블에 값넣기성공");
        }
        else {
            res = 0;
            log.info("오더테이블에 값넣기실패");
        }
        return res;
    }
    //proseq를 디비로 내려줌
    @Override
    public int searchorderinfo(OrderDTO oDTO) throws Exception {

        int res = 0; //오더테이블에서 사용자아이디로 조회하여 사용자주문 내역이 있으면 2 없으면 1

        if(oDTO == null){
            oDTO = new OrderDTO();
        }
        OrderDTO tDTO = null;
        tDTO = new OrderDTO();

        tDTO = orderMapper.getproductuser(oDTO);

        if(CmmUtil.nvl(tDTO.getExists_yn()).equals("Y")){
            log.info("디비조회결과 중복임");
            res = 2;
        }else {
            res = 1;
            log.info("디비조회결과 중복아님");
        }

        return res;
    }

    @Override
    public OrderDTO getproductinfo(OrderDTO oDTO) throws Exception {

        if(oDTO == null){
            oDTO = new OrderDTO();
        }
        OrderDTO pDTO = null;
        pDTO = new OrderDTO();

        pDTO = orderMapper.getproductinfo(oDTO);


        return pDTO;
    }
    @Override
    public OrderDTO searchproseq(OrderDTO oDTO) throws Exception{

        if(oDTO == null){
            oDTO = new OrderDTO();
        }
        OrderDTO tDTO = null;
        tDTO = new OrderDTO();

        tDTO = orderMapper.getproductinfo(oDTO);

        return tDTO;
    }

    @Override
    public OrderDTO select(OrderDTO oDTO) throws Exception {
        if(oDTO == null){
            oDTO = new OrderDTO();

        }

        OrderDTO pDTO = null;
        pDTO = new OrderDTO();

        pDTO = orderMapper.select(oDTO);
        log.info("db에서 가져온 pDTO의 값은?" + pDTO);

        return pDTO;
    }
    @Override
    public int updateOrder(OrderDTO uDTO) throws Exception {
        int res = 0;// 0은 실패 1은 성공

        if(uDTO == null){
            uDTO = new OrderDTO();
        }
        int success = orderMapper.updateOrder(uDTO);

        if(success > 0){
            res = 1;
            log.info("오더테이블에 값넣기성공");
        }
        else {
            res = 0;
            log.info("오더테이블에 값넣기실패");
        }
        return res;
    }

    @Override
    public OrderDTO selectpro_seq(OrderDTO pDTO) throws Exception {



        if(pDTO == null){
            pDTO = new OrderDTO();
        }
        OrderDTO oDTO = null;
        oDTO = new OrderDTO();

        oDTO = orderMapper.selectpro_seq(pDTO);

        if(oDTO == null){
            log.info("오더테이블에서 seq가져오기 실패");
            oDTO = new OrderDTO();


        }
        else {
            log.info("오더테이블에서 seq가져오기 성공");
        }
        return oDTO;
    }
    @Override
    public void deleteorder(ProductDTO rDTO) throws Exception {

        log.info(this.getClass().getName() + "deleteProductInfo 시작");

        log.info(this.getClass().getName() + "deleteProductInfo 끝");

        orderMapper.deleteorder(rDTO);
    }


}
