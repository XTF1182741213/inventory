package org.daming.inventory.controller;

import org.daming.inventory.pojo.Gift;
import org.daming.inventory.pojo.response.CommonResponse;
import org.daming.inventory.pojo.response.DataResponse;
import org.daming.inventory.pojo.response.PageResponse;
import org.daming.inventory.service.GiftService;
import org.daming.inventory.service.ReactorGiftService;
import org.daming.inventory.service.RedisGiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * 描述：
 *
 * @author daming
 * @date 2018-07-11 21:22
 */
@RestController
@RequestMapping(path ="api/v1")
public class ApiController {

    @Autowired
    private RedisGiftService redisGiftService;
    @Autowired
    private GiftService giftService;
    @Autowired
    private ReactorGiftService reactorGiftService;

    @GetMapping(path = "gift/{id}")
    public Mono<DataResponse<Gift>> get(@PathVariable int id) {
        return reactorGiftService.get(id);
    }

    @GetMapping(path = "gift")
    public Mono<PageResponse<Gift>> list(@RequestParam(name = "name", required = false) String name,
                                         @RequestParam(name = "pageNo", required = false, defaultValue = "1") int pageNo,
                                         @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pagseSize) {
        return  reactorGiftService.list(name, pageNo, pagseSize);
    }

    @PostMapping(path = "gift")
    public Mono<CommonResponse> add(@RequestBody Gift gift) {
        return reactorGiftService.add(gift);
    }

    @DeleteMapping(path = "gift/{id}")
    public Mono<CommonResponse> delete(@PathVariable(name = "id") int id) {
        return reactorGiftService.delete(id);
    }

}