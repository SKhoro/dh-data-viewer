package me.khoro.viewer.controller;

import me.khoro.viewer.model.dto.DataDto;
import me.khoro.viewer.service.GetDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static me.khoro.viewer.constants.BaseConst.API_V1_PREFIX;

/**
 * Контроллер, обрабатывающий запросы к сервису
 */
@RestController
@RequestMapping(API_V1_PREFIX)
public class DataViewController {

    private final GetDataService getDataService;

    public DataViewController(GetDataService getDataService) {
        this.getDataService = getDataService;
    }

    @GetMapping("/getData")
    public Set<DataDto> getData2(@RequestParam("start") long start, @RequestParam("stop") long stop) {
        return getDataService.getData(start, stop);
    }
}
