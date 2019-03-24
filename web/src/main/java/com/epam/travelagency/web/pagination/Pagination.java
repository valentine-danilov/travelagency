package com.epam.travelagency.web.pagination;

import com.epam.travelagency.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Pagination<T extends AbstractEntity> {
    Integer page;
    Integer pageSize;
    Long pageNumber;
    List<T> content;

    public Integer getOffset(){
        return (page-1)*pageSize;
    }
}
