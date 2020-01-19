package com.dahantc.client;

import com.dahantc.common.DecreaseStockInput;
import com.dahantc.common.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Created by Kevin Zhu on 2018/11/10 22:15 .
 * 测试feign时使用
 */
@Component
@FeignClient(name = "product", fallback = ProductClient.ProductClientFallback.class)
public interface ProductClient {

    @GetMapping("/msg")
    String productMsg();

    /**
     * 获取商品列表(调用产品服务)
     *
     * @param productIdList
     * @return
     */
    @PostMapping("/product/listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);


    @Component
    class ProductClientFallback implements ProductClient {
        @Override
        public String productMsg() {
            return null;
        }

        /**
         * 获取商品列表(调用产品服务)
         *
         * @param productIdList
         * @return
         */
        @Override
        public List<ProductInfoOutput> listForOrder(List<String> productIdList) {
            return null;
        }

        @Override
        public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {

        }
    }

}
