package spring.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import spring.persistence.dao.SpringbootJeremyOrderDao;
import spring.persistence.dao.SpringbootJeremyOrderItemDao;
import spring.persistence.dao.SpringbootJeremyProductDao;
import spring.persistence.dao.SpringbootJeremyUserDao;
import spring.dto.BuyItem;
import spring.dto.CreateOrderRequest;
import spring.dto.OrderQueryParams;
import spring.persistence.vo.SpringbootJeremyOrder;
import spring.persistence.vo.SpringbootJeremyOrderItem;
import spring.persistence.vo.SpringbootJeremyProduct;
import spring.persistence.vo.SpringbootJeremyUser;
import spring.service.OrderService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {
    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    private SpringbootJeremyOrderDao orderDao;
    @Autowired
    private SpringbootJeremyProductDao productDao;
    @Autowired
    private SpringbootJeremyUserDao userDao;
    @Autowired
    private SpringbootJeremyOrderItemDao orderItemDao;

    @Override
    public Long countOrder(OrderQueryParams orderQueryParams) {
        LambdaQueryWrapper<SpringbootJeremyOrder> qw = new LambdaQueryWrapper<>();
        if (orderQueryParams.getUserId() != null) {
            qw.eq(SpringbootJeremyOrder::getUserId, orderQueryParams.getUserId());
        }
        return orderDao.selectCount(qw);
    }

    @Override
    public List<SpringbootJeremyOrder> getOrders(OrderQueryParams orderQueryParams) {
        LambdaQueryWrapper<SpringbootJeremyOrder> qw = new LambdaQueryWrapper<>();
        if (orderQueryParams.getUserId() != null) {
            qw.eq(SpringbootJeremyOrder::getUserId, orderQueryParams.getUserId());
        }
        PageInfo<SpringbootJeremyOrder> empPage = PageHelper.startPage(orderQueryParams.getOffset(),
                        orderQueryParams.getLimit(), "created_date DESC")
                .doSelectPageInfo(() -> orderDao.selectList(qw));

        //List<SpringbootJeremyOrder> orderList = orderDao.getOrders(orderQueryParams);
        for (SpringbootJeremyOrder order : empPage.getList()) {
            List<SpringbootJeremyOrderItem> orderItemList = orderItemDao.getOrderItemsByOrderId(order.getOrderId());
            order.setOrderItemList(orderItemList);
        }
        return empPage.getList();
    }

    @Override
    public SpringbootJeremyOrder getOrderById(Integer orderId) {
        SpringbootJeremyOrder order = orderDao.selectById(orderId);
        List<SpringbootJeremyOrderItem> orderItemList = orderItemDao.getOrderItemsByOrderId(orderId);

        order.setOrderItemList(orderItemList);
        return order;
    }

    @Transactional //確保兩個資料庫同時新增成功失敗
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        //檢查user是否存在
        SpringbootJeremyUser user = userDao.selectById(userId);
        if(user ==null){
            log.warn("該 userId{} 不存在",userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        int totalAmount =0;
        List<SpringbootJeremyOrderItem> orderItemList = new ArrayList<>();

        for(BuyItem buyItem : createOrderRequest.getBuyItemList()){
            SpringbootJeremyProduct product = productDao.selectById(buyItem.getProductId());
            //檢查 product是否存在、庫存是否足夠
            if(product == null){
                log.warn("商品 {} 不存在", buyItem.getProductId());
                throw  new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }else if(product.getStock() < buyItem.getQuantity()){
                log.warn("商品 {} 庫存數量不，無法購買。剩餘庫存 {} ，欲購買數量 {}",
                        buyItem.getProductId(),product.getStock(),buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            product.setStock(product.getStock()- buyItem.getQuantity());
            productDao.updateById(product);
            //扣除商品庫存
            //productDao.updateStock(product.getProductId(),product.getStock()- buyItem.getQuantity());

            //計算總價錢
            int amount = buyItem.getQuantity()* product.getPrice();
            totalAmount += amount;
            SpringbootJeremyOrderItem orderItem = new SpringbootJeremyOrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);
            orderItemList.add(orderItem);
        }

        SpringbootJeremyOrder order = new SpringbootJeremyOrder();
        order.setUserId(userId);
        order.setTotalAmount(totalAmount);
        Date now = new Date();
        order.setCreatedDate(now);
        order.setLastModifiedDate(now);
        orderDao.insert(order);
        //Integer orderId = orderDao.createOrder(userId,totalAmount);
        for(int i=0;i<orderItemList.size();i++){
            SpringbootJeremyOrderItem orderItem =orderItemList.get(i);
            orderItem.setOrderId(order.getOrderId());
            orderItemDao.insert(orderItem);
        }

        //orderItemDao.insert(order.getOrderId(),orderItemList);
        return order.getOrderId();
    }
}
