package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.dto.CreateOrderRequest;
import spring.dto.OrderQueryParams;
import spring.persistence.vo.SpringbootJeremyOrder;
import spring.service.OrderService;
import spring.util.Page;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable Integer userId,
                                        @RequestBody @Valid CreateOrderRequest createOrderRequest){
        Integer orderId = orderService.createOrder(userId,createOrderRequest);
        SpringbootJeremyOrder order = orderService.getOrderById(orderId);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<Page<SpringbootJeremyOrder>> getOrders(
            @PathVariable Integer userId,
            @RequestParam(defaultValue = "10") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "1") @Min(0) Integer offset
    ){
        OrderQueryParams orderQueryParams = new OrderQueryParams();
        orderQueryParams.setUserId(userId);
        orderQueryParams.setLimit(limit);
        orderQueryParams.setOffset(offset);
        //取得order List
        List<SpringbootJeremyOrder> orderList = orderService.getOrders(orderQueryParams);
        //取得 order總數
        Long count = orderService.countOrder(orderQueryParams);
        //分頁
        Page<SpringbootJeremyOrder> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(count);
        page.setResult(orderList);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }



}
