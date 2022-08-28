package com.example.football.core.stadium.web;

import com.example.football.core.stadium.Stadium;
import com.example.football.core.stadium.StadiumService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/stadium")
public class StadiumController {
    private final StadiumService service;

    public StadiumController(StadiumService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public StadiumView getStadium(@PathVariable Long id) {
        return service.getStadium(id);
    }

    @GetMapping
    @ResponseBody
    public Page<StadiumView> getAllStadium(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return service.findAllStadium(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public StadiumView create(@RequestBody @Valid StadiumBaseReq req) {
        return service.create(req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStadium(@PathVariable Long id){
        service.delete(id);
    }

    @PutMapping("/{id}")
    public StadiumView updateStadium(@PathVariable(name = "id") Long id,
                                           @RequestBody @Valid StadiumBaseReq req){
        Stadium stadium = service.findStadiumOrThrow(id);
        return service.update(stadium, req);
    }
}
