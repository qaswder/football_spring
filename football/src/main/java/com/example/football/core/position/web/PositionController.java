package com.example.football.core.position.web;

import com.example.football.core.position.Position;
import com.example.football.core.position.PositionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/position")
public class PositionController {

    private final PositionService service;

    public PositionController(final PositionService service){
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public PositionView getPosition(@PathVariable Long id){
        return service.getPosition(id);
    }

    @GetMapping
    @ResponseBody
    public Page<PositionView> getAllEvent(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return service.findAllPosition(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public PositionView create(@RequestBody @Valid PositionBaseReq req) {
        return service.create(req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable Long id){
        service.delete(id);
    }

    @PutMapping("/{id}")
    public PositionView updatePosition(@PathVariable(name = "id") Long id,
                                 @RequestBody @Valid PositionBaseReq req){
        Position position = service.findPositionOrThrow(id);
        return service.update(position, req);
    }
}
